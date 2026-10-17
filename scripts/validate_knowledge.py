#!/usr/bin/env python3
"""Deterministic validator for platform knowledge releases.

Usage:
  python scripts/validate_knowledge.py \
    --platform knowledge/platforms/analytics-foundation

  python scripts/validate_knowledge.py \
    --platform knowledge/platforms/analytics-foundation \
    --release-version 1.0.0

Exit codes:
  0 validation passed
  1 validation failed
  2 configuration/usage error
"""
from __future__ import annotations

import argparse
import hashlib
import json
import re
import sys
from collections import Counter
from dataclasses import dataclass, field
from pathlib import Path
from typing import Any, Iterable

try:
    import yaml
except ImportError:
    print("ERROR: PyYAML is required: python -m pip install PyYAML jsonschema", file=sys.stderr)
    raise SystemExit(2)

try:
    from jsonschema import Draft202012Validator
except ImportError:
    print("ERROR: jsonschema is required: python -m pip install PyYAML jsonschema", file=sys.stderr)
    raise SystemExit(2)

SHA256_RE = re.compile(r"^[0-9a-f]{64}$")
MAPPING_STATES = {
    "verified", "partially-verified", "conflict", "inferred", "unresolved", "deprecated"
}
NON_RUNTIME_STATES = MAPPING_STATES - {"verified"}


@dataclass
class Result:
    errors: list[str] = field(default_factory=list)
    warnings: list[str] = field(default_factory=list)
    notes: list[str] = field(default_factory=list)
    counts: Counter = field(default_factory=Counter)

    def error(self, message: str) -> None:
        self.errors.append(message)

    def warn(self, message: str) -> None:
        self.warnings.append(message)

    def note(self, message: str) -> None:
        self.notes.append(message)


def sha256_file(path: Path) -> str:
    h = hashlib.sha256()
    with path.open("rb") as stream:
        for block in iter(lambda: stream.read(1024 * 1024), b""):
            h.update(block)
    return h.hexdigest()


def load_yaml(path: Path) -> dict[str, Any]:
    data = yaml.safe_load(path.read_text(encoding="utf-8"))
    if not isinstance(data, dict):
        raise ValueError(f"YAML root is not an object: {path}")
    return data


def load_json(path: Path) -> dict[str, Any]:
    data = json.loads(path.read_text(encoding="utf-8"))
    if not isinstance(data, dict):
        raise ValueError(f"JSON root is not an object: {path}")
    return data


def iter_jsonl(path: Path, result: Result) -> Iterable[tuple[int, dict[str, Any]]]:
    with path.open("r", encoding="utf-8") as stream:
        for line_no, raw in enumerate(stream, 1):
            if not raw.strip():
                continue
            try:
                item = json.loads(raw)
            except json.JSONDecodeError as exc:
                result.error(f"{path}:{line_no}: invalid JSON: {exc}")
                continue
            if not isinstance(item, dict):
                result.error(f"{path}:{line_no}: JSONL record is not an object")
                continue
            yield line_no, item


def resolve_repo_root(platform_root: Path) -> Path:
    current = platform_root.resolve()
    for candidate in [current, *current.parents]:
        if (candidate / ".github").exists() or (candidate / "schemas" / "knowledge").exists():
            return candidate
    # Expected platform path is repo/knowledge/platforms/<id>.
    try:
        return platform_root.resolve().parents[2]
    except IndexError as exc:
        raise ValueError("Cannot determine repository root") from exc


def deep_get(data: dict[str, Any], dotted: str) -> Any:
    value: Any = data
    for key in dotted.split("."):
        if not isinstance(value, dict) or key not in value:
            return None
        value = value[key]
    return value


def find_config(platform_root: Path) -> tuple[dict[str, Any], Path]:
    path = platform_root / "platform.yaml"
    if not path.is_file():
        raise FileNotFoundError(f"Missing platform configuration: {path}")
    return load_yaml(path), path


def configured_path(config: dict[str, Any], dotted: str, default: str) -> Path:
    return Path(deep_get(config, dotted) or default)


def load_schema(repo_root: Path, configured: Any, default: str) -> tuple[dict[str, Any], Path]:
    if isinstance(configured, dict):
        configured = configured.get("path")
    rel = Path(configured or default)
    path = rel if rel.is_absolute() else repo_root / rel
    if not path.is_file():
        raise FileNotFoundError(f"Missing JSON Schema: {path}")
    return load_json(path), path


def collect_layer(
    platform_root: Path,
    relative_dir: Path,
    schema: dict[str, Any],
    layer_name: str,
    result: Result,
    global_ids: dict[str, tuple[str, Path, int]],
) -> tuple[list[dict[str, Any]], dict[str, dict[str, Any]]]:
    directory = platform_root / relative_dir
    records: list[dict[str, Any]] = []
    index: dict[str, dict[str, Any]] = {}
    if not directory.is_dir():
        result.error(f"Missing approved {layer_name} directory: {directory}")
        return records, index
    files = sorted(directory.glob("*.jsonl"))
    if not files:
        result.error(f"Approved {layer_name} layer is empty: {directory}")
        return records, index

    validator = Draft202012Validator(schema)
    for path in files:
        file_count = 0
        for line_no, record in iter_jsonl(path, result):
            file_count += 1
            for error in sorted(validator.iter_errors(record), key=lambda e: list(e.path)):
                location = ".".join(map(str, error.path)) or "$"
                result.error(f"{path}:{line_no}:{location}: schema violation: {error.message}")
            rid = record.get("id")
            if isinstance(rid, str):
                if rid in global_ids:
                    previous_layer, previous_path, previous_line = global_ids[rid]
                    result.error(
                        f"{path}:{line_no}: duplicate id {rid!r}; first in "
                        f"{previous_layer} at {previous_path}:{previous_line}"
                    )
                else:
                    global_ids[rid] = (layer_name, path, line_no)
                    index[rid] = record
            records.append(record)
            result.counts[f"{layer_name}.records"] += 1
        result.counts[f"{layer_name}.files"] += 1
        result.note(f"{layer_name}: {path.name}: {file_count} records")
    return records, index


def validate_common(records: list[dict[str, Any]], layer: str, platform_id: str, result: Result) -> None:
    for record in records:
        rid = record.get("id", "<missing-id>")
        if record.get("platformId") != platform_id:
            result.error(f"{layer}:{rid}: platformId must be {platform_id!r}")
        if record.get("status") != "approved":
            result.error(f"{layer}:{rid}: status must be 'approved'")
        refs = record.get("sourceRefs")
        if not isinstance(refs, list) or not refs:
            result.error(f"{layer}:{rid}: sourceRefs must be a non-empty array")
        else:
            for pos, ref in enumerate(refs):
                if not isinstance(ref, dict):
                    result.error(f"{layer}:{rid}: sourceRefs[{pos}] is not an object")
                    continue
                for key in ("sourceId", "sourcePath", "sha256"):
                    if not ref.get(key):
                        result.error(f"{layer}:{rid}: sourceRefs[{pos}] missing {key}")
                value = ref.get("sha256")
                if value and (not isinstance(value, str) or not SHA256_RE.fullmatch(value)):
                    result.error(f"{layer}:{rid}: sourceRefs[{pos}].sha256 is invalid")
        approval = record.get("approvalRef")
        if not isinstance(approval, dict):
            result.error(f"{layer}:{rid}: approvalRef missing")
        else:
            for key in ("approvalId", "path", "sha256"):
                if not approval.get(key):
                    result.error(f"{layer}:{rid}: approvalRef missing {key}")


def validate_approval_refs(platform_root: Path, records: Iterable[dict[str, Any]], layer: str, result: Result) -> None:
    # Aggregate by (path, expected checksum). Thousands of approved records normally
    # share one approvalRef; report a broken chain once, not once per record.
    groups: dict[tuple[str, str], list[str]] = {}
    malformed = 0
    for record in records:
        rid = str(record.get("id", "<missing-id>"))
        approval = record.get("approvalRef")
        if not isinstance(approval, dict):
            continue
        rel = approval.get("path")
        expected = approval.get("sha256")
        if not isinstance(rel, str) or not isinstance(expected, str):
            malformed += 1
            continue
        groups.setdefault((rel, expected), []).append(rid)

    if malformed:
        result.error(f"{layer}: {malformed} record(s) have malformed approvalRef path/checksum")

    for (rel, expected), record_ids in sorted(groups.items()):
        path = platform_root / rel
        if not path.is_file():
            result.error(
                f"{layer}: approvalRef path does not exist: {rel}; "
                f"referenced by {len(record_ids)} record(s), e.g. {record_ids[0]}"
            )
            continue
        actual = sha256_file(path)
        if actual != expected:
            result.error(
                f"{layer}: approvalRef checksum mismatch for {rel}: expected {expected}, "
                f"got {actual}; referenced by {len(record_ids)} record(s), e.g. {record_ids[0]}"
            )


def validate_runtime_boundaries(
    domain: list[dict[str, Any]],
    physical: list[dict[str, Any]],
    mappings: list[dict[str, Any]],
    result: Result,
) -> None:
    for layer_name, records in (("domain", domain), ("physicalSchema", physical)):
        for record in records:
            if record.get("runtimeAuthorized") is not False:
                result.error(f"{layer_name}:{record.get('id')}: runtimeAuthorized must be false before runtime release")

    for record in physical:
        rid = record.get("id")
        if "runtimePolicy" in record and record.get("runtimePolicy") == "exclude-from-runtime":
            if record.get("runtimeAuthorized") is not False:
                result.error(f"physicalSchema:{rid}: excluded object is runtime authorized")

    for record in mappings:
        rid = record.get("id")
        classification = record.get("classification")
        evidence = record.get("evidenceState")
        if classification not in MAPPING_STATES:
            result.error(f"semanticMappings:{rid}: unsupported classification {classification!r}")
        if evidence not in MAPPING_STATES:
            result.error(f"semanticMappings:{rid}: unsupported evidenceState {evidence!r}")
        if classification != evidence:
            result.error(f"semanticMappings:{rid}: classification {classification!r} != evidenceState {evidence!r}")
        eligible = record.get("runtimeEligible")
        authorized = record.get("runtimeAuthorized")
        if not isinstance(eligible, bool) or not isinstance(authorized, bool):
            result.error(f"semanticMappings:{rid}: runtimeEligible and runtimeAuthorized must be booleans")
            continue
        if classification in NON_RUNTIME_STATES and (eligible or authorized):
            result.error(f"semanticMappings:{rid}: {classification} mapping must remain runtime denied")
        if authorized and not eligible:
            result.error(f"semanticMappings:{rid}: runtimeAuthorized=true requires runtimeEligible=true")
        if eligible and classification != "verified":
            result.error(f"semanticMappings:{rid}: runtimeEligible=true requires classification='verified'")
        if record.get("identifierCategoriesAreInterchangeable") is True:
            result.error(f"semanticMappings:{rid}: identifier categories may not be interchangeable")
        if record.get("keyConstraintVerified") is False and (eligible or authorized):
            result.error(f"semanticMappings:{rid}: unverified key constraint must remain runtime denied")
        if record.get("traversalVerified") is False and (eligible or authorized):
            result.error(f"semanticMappings:{rid}: unverified traversal must remain runtime denied")
        path_assessment = record.get("canonicalPathSegmentAssessment")
        if isinstance(path_assessment, dict) and path_assessment.get("state") == "unresolved" and (eligible or authorized):
            result.error(f"semanticMappings:{rid}: unresolved canonical path must remain runtime denied")


def validate_mapping_refs(
    mappings: list[dict[str, Any]],
    domain_index: dict[str, dict[str, Any]],
    physical_index: dict[str, dict[str, Any]],
    result: Result,
) -> None:
    restricted_ids = {
        str(record.get("objectId"))
        for record in physical_index.values()
        if record.get("runtimePolicy") == "exclude-from-runtime" and record.get("objectId")
    }
    for record in mappings:
        rid = record.get("id")
        for ref in record.get("domainRecordRefs", []):
            if ref not in domain_index:
                result.error(f"semanticMappings:{rid}: unresolved domainRecordRef {ref!r}")
        for ref in record.get("physicalRecordRefs", []):
            if ref not in physical_index:
                result.error(f"semanticMappings:{rid}: unresolved physicalRecordRef {ref!r}")
            elif physical_index[ref].get("status") != "approved":
                result.error(f"semanticMappings:{rid}: physicalRecordRef {ref!r} is not approved")
            if ref in restricted_ids and (record.get("runtimeEligible") or record.get("runtimeAuthorized")):
                result.error(f"semanticMappings:{rid}: mapping to restricted physical object is runtime enabled")


def validate_restricted_refs(physical_records: list[dict[str, Any]], physical_index: dict[str, dict[str, Any]], result: Result) -> None:
    for record in physical_records:
        if record.get("runtimePolicy") != "exclude-from-runtime":
            continue
        rid = record.get("id")
        if record.get("runtimeAuthorized") is not False:
            result.error(f"physicalSchema:{rid}: restricted object runtimeAuthorized must be false")
        object_id = record.get("objectId")
        if not object_id or object_id not in physical_index:
            result.error(f"physicalSchema:{rid}: restricted objectId does not resolve: {object_id!r}")


def validate_promotion(platform_root: Path, rel: str, expected_stage: str, result: Result) -> dict[str, Any] | None:
    path = platform_root / rel
    if not path.is_file():
        result.error(f"Missing promotion manifest: {path}")
        return None
    try:
        data = load_yaml(path)
    except Exception as exc:
        result.error(f"Cannot read promotion manifest {path}: {exc}")
        return None
    if deep_get(data, "metadata.platformId") != "analytics-foundation":
        result.error(f"{path}: unexpected platformId")
    if deep_get(data, "metadata.stage") != expected_stage:
        result.error(f"{path}: expected stage {expected_stage!r}")
    if deep_get(data, "spec.status") != "promoted":
        result.error(f"{path}: promotion status must be 'promoted'")
    approval = deep_get(data, "spec.approvalRef")
    if isinstance(approval, dict):
        approval_path = platform_root / str(approval.get("path", ""))
        expected = approval.get("sha256")
        if not approval_path.is_file():
            result.error(f"{path}: approval file missing: {approval_path}")
        elif expected != sha256_file(approval_path):
            result.error(f"{path}: approval checksum mismatch for {approval_path}")
    else:
        result.error(f"{path}: approvalRef missing")

    outputs = deep_get(data, "spec.outputs")
    total = 0
    if not isinstance(outputs, dict) or not outputs:
        result.error(f"{path}: outputs missing")
        return data
    for name, output in outputs.items():
        if not isinstance(output, dict):
            result.error(f"{path}: output {name!r} is not an object")
            continue
        approved_rel = output.get("approvedPath")
        expected_hash = output.get("approvedSha256")
        expected_records = output.get("records")
        if not approved_rel or not expected_hash or not isinstance(expected_records, int):
            result.error(f"{path}: output {name!r} missing approvedPath, approvedSha256, or records")
            continue
        approved_path = platform_root / approved_rel
        if not approved_path.is_file():
            result.error(f"{path}: approved output missing: {approved_path}")
            continue
        actual_hash = sha256_file(approved_path)
        if actual_hash != expected_hash:
            result.error(f"{path}: checksum mismatch for {approved_rel}: expected {expected_hash}, got {actual_hash}")
        actual_records = sum(1 for line in approved_path.read_text(encoding="utf-8").splitlines() if line.strip())
        if actual_records != expected_records:
            result.error(f"{path}: record count mismatch for {approved_rel}: expected {expected_records}, got {actual_records}")
        total += actual_records
    expected_total = deep_get(data, "spec.records")
    if isinstance(expected_total, int) and total != expected_total:
        result.error(f"{path}: total record count mismatch: expected {expected_total}, got {total}")
    result.note(f"promotion {expected_stage}: {total} records verified")
    return data


def validate_semantic_evidence_chain(platform_root: Path, semantic_promotion: dict[str, Any] | None, result: Result) -> None:
    if not semantic_promotion:
        return
    evidence = deep_get(semantic_promotion, "spec.approvedEvidence")
    if not isinstance(evidence, dict):
        result.error("Semantic promotion missing spec.approvedEvidence")
        return
    for key in ("domain", "physicalSchema"):
        item = evidence.get(key)
        if not isinstance(item, dict):
            result.error(f"Semantic promotion missing approvedEvidence.{key}")
            continue
        pairs = [
            ("approvalPath", "approvalSha256"),
            ("promotionPath", "promotionSha256"),
        ]
        for path_key, hash_key in pairs:
            rel = item.get(path_key)
            expected = item.get(hash_key)
            if not rel or not expected:
                result.error(f"Semantic promotion approvedEvidence.{key} missing {path_key}/{hash_key}")
                continue
            path = platform_root / rel
            if not path.is_file():
                result.error(f"Semantic promotion evidence file missing: {path}")
            elif sha256_file(path) != expected:
                result.error(f"Semantic promotion evidence checksum mismatch: {path}")


def validate_release_version(platform_root: Path, version: str | None, result: Result) -> None:
    if not version:
        return
    if not re.fullmatch(r"0|[1-9]\d*\.0|[1-9]\d*\.[1-9]\d*|0\.[1-9]\d*|[1-9]\d*\.\d+\.\d+", version):
        # Accept standard numeric SemVer x.y.z; message remains explicit.
        if not re.fullmatch(r"(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)", version):
            result.error(f"Invalid release version {version!r}; expected semantic version x.y.z")
    release_path = platform_root / "knowledge" / "releases" / version
    if release_path.exists():
        result.error(f"Immutable release already exists: {release_path}")
    else:
        result.note(f"release version {version}: available")


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--platform", required=True, type=Path, help="Platform root containing platform.yaml")
    parser.add_argument("--release-version", help="Optional proposed immutable release version")
    parser.add_argument("--report", type=Path, help="Optional JSON report path")
    args = parser.parse_args()

    result = Result()
    platform_root = args.platform.resolve()
    try:
        config, config_path = find_config(platform_root)
        repo_root = resolve_repo_root(platform_root)
    except Exception as exc:
        print(f"ERROR: {exc}", file=sys.stderr)
        return 2

    platform_id = deep_get(config, "metadata.id")
    if not isinstance(platform_id, str):
        result.error(f"{config_path}: metadata.id missing")
        platform_id = "analytics-foundation"

    schema_cfg = deep_get(config, "spec.release.validation.schemas") or {}
    try:
        domain_schema, _ = load_schema(repo_root, schema_cfg.get("domain"), "schemas/knowledge/domain-record.schema.json")
        physical_schema, _ = load_schema(repo_root, schema_cfg.get("physicalSchema"), "schemas/knowledge/physical-schema-record.schema.json")
        mapping_schema, _ = load_schema(repo_root, schema_cfg.get("semanticMapping"), "schemas/knowledge/semantic-mapping-record.schema.json")
    except Exception as exc:
        result.error(str(exc))
        domain_schema = physical_schema = mapping_schema = {"type": "object"}

    domain_path = configured_path(config, "spec.knowledge.domain.approvedPath", "knowledge/approved/domain")
    physical_path = configured_path(config, "spec.knowledge.schema.approvedPath", "knowledge/approved/schema")
    mapping_path = configured_path(config, "spec.knowledge.mappings.approvedPath", "knowledge/approved/mappings")

    all_ids: dict[str, tuple[str, Path, int]] = {}
    domain_records, domain_index = collect_layer(platform_root, domain_path, domain_schema, "domain", result, all_ids)
    physical_records, physical_index = collect_layer(platform_root, physical_path, physical_schema, "physicalSchema", result, all_ids)
    mapping_records, mapping_index = collect_layer(platform_root, mapping_path, mapping_schema, "semanticMappings", result, all_ids)

    validate_common(domain_records, "domain", platform_id, result)
    validate_common(physical_records, "physicalSchema", platform_id, result)
    validate_common(mapping_records, "semanticMappings", platform_id, result)
    validate_approval_refs(platform_root, domain_records, "domain", result)
    validate_approval_refs(platform_root, physical_records, "physicalSchema", result)
    validate_approval_refs(platform_root, mapping_records, "semanticMappings", result)
    validate_runtime_boundaries(domain_records, physical_records, mapping_records, result)
    validate_mapping_refs(mapping_records, domain_index, physical_index, result)
    validate_restricted_refs(physical_records, physical_index, result)

    domain_promo = validate_promotion(platform_root, "review/approved/domain-promotion.yaml", "domain", result)
    schema_promo = validate_promotion(platform_root, "review/approved/schema-extraction-promotion.yaml", "physicalSchema", result)
    semantic_promo = validate_promotion(platform_root, "review/approved/semantic-mapping-promotion.yaml", "semanticMappings", result)
    validate_semantic_evidence_chain(platform_root, semantic_promo, result)
    validate_release_version(platform_root, args.release_version, result)

    result.counts["all.unique_ids"] = len(all_ids)
    result.counts["semanticMappings.verified"] = sum(r.get("classification") == "verified" for r in mapping_records)
    result.counts["semanticMappings.partially_verified"] = sum(r.get("classification") == "partially-verified" for r in mapping_records)
    result.counts["semanticMappings.unresolved"] = sum(r.get("classification") == "unresolved" for r in mapping_records)
    result.counts["semanticMappings.conflict"] = sum(r.get("classification") == "conflict" for r in mapping_records)
    result.counts["semanticMappings.runtime_eligible"] = sum(r.get("runtimeEligible") is True for r in mapping_records)

    report = {
        "platformId": platform_id,
        "platformRoot": str(platform_root),
        "releaseVersion": args.release_version,
        "passed": not result.errors,
        "counts": dict(sorted(result.counts.items())),
        "errors": result.errors,
        "warnings": result.warnings,
        "notes": result.notes,
    }
    if args.report:
        args.report.parent.mkdir(parents=True, exist_ok=True)
        args.report.write_text(json.dumps(report, indent=2) + "\n", encoding="utf-8")

    print(f"Platform: {platform_id}")
    if args.release_version:
        print(f"Candidate release: {args.release_version}")
    print("Counts:")
    for key, value in sorted(result.counts.items()):
        print(f"  {key}: {value}")
    if result.warnings:
        print("Warnings:")
        for item in result.warnings:
            print(f"  WARN: {item}")
    if result.errors:
        print("Validation errors:")
        for item in result.errors:
            print(f"  ERROR: {item}")
        print(f"FAILED: {len(result.errors)} error(s)")
        return 1
    print("PASSED: knowledge integrity and release prerequisites are valid")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
