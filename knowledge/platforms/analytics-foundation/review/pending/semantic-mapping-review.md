# Semantic mapping draft review — analytics-foundation

Status: draft only; human review is required. No mapping approval, promotion, or release was performed.

## Scope and evidence

- Assessed all 99 approved documented-mapping records and all eight approved deferred schema findings.
- Inputs: checksum-bound approved domain knowledge (400 records) and approved physical schema knowledge (31,341 records across 13 files); approval and promotion chains were checked before drafting.
- Classification is explicit on every mapping record. Exact physical object/column declarations are `declared`; unsupported mappings are `unresolved`. No `implemented`, `verified`, or `inferred` claims were made.
- A matching name or source-declared mapping does not establish semantic equivalence. No query/code implementation evidence was available. No SQL was executed.
- Domain and physical evidence references are preserved as record IDs and source references. Every output record is `draft` and `runtimeAuthorized: false`.
- Physical database comments on exact mapped records are retained with their comment refs and untrusted-source flag; they are review context only, not semantic verification.
- Canonical, associating, internal-database, and composite identifiers are retained as separate categories. Internal numeric identifiers are not substituted for canonical/composite identifiers.
- The approved canonical enum literal remains `SIMULATED`; `SIMULATION` remains wording-only and nonexecutable.
- Restricted objects and unknown computed data types in the approved physical layer were left untouched.

## Coverage result

- 99/99 documented mappings appear in canonical-path assessment and are also individually assessed for exact object, column (where stated), and storage target evidence.
- Exact physical declaration does not certify the meaning or traversal of a canonical path.
- Entity/object, attribute/column, identifier, relationship/join, canonical-path and storage-boundary output counts and per-classification dispositions are recorded in `semantic-mapping-change-set.yaml`.
- All eight deferred findings remain individually unresolved for human disposition. In addition, exact target absences, all unmapped domain identifiers and all domain relationships without direct FK evidence are retained in `unresolved-mappings.jsonl`.
- No relationship-to-FK association was inferred from names or cardinality; no generic name similarity was promoted into a semantic mapping.

## Review gate and filename collision

`platform.yaml` configures `schema-mapping-review.md`, `schema-mapping-review-findings.md`, and `schema-mapping-change-set.yaml`, but those exact pending files are pre-existing historical physical-extraction artifacts. They were preserved byte-for-byte. This semantic review is therefore written under distinctive `semantic-mapping-*` pending filenames and does not replace or reinterpret those historical artifacts.

The configured `review/config/schema-mapping-review-policy.yaml` is absent. The newly present `review/config/semantic-mapping-review-policy.yaml` is empty and unusable. This is an explicit policy-based review limitation. No policy was invented, no automatic approval was performed, and this package is not approval-ready until a human resolves the policy/configuration gate and reviews these drafts.

## Validation boundary

Local deterministic checks cover JSONL syntax, stable unique IDs, required metadata, source references, domain/physical record references, checksums, approved scope, per-output coverage, evidence classifications, runtime restrictions, and the retained identifier/enum/restriction invariants. This is not repository-wide schema validation, does not establish semantic truth, and does not authorize query use or runtime access.
