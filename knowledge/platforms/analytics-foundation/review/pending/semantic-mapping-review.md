# Semantic mapping review — analytics-foundation

**Recommendation: ACCEPT_WITH_COMMENTS.** The four blocking generator/evidence findings are corrected. This is a policy-grounded recommendation only; human approval, promotion, and release have not been performed. Draft mappings remain runtime-ineligible.

## Scope and immutable evidence

- Configured stage: `spec.review.stages.semanticMappings`; draft path `knowledge/draft/mappings`.
- Policy: `review/config/semantic-mapping-review-policy.yaml`, SHA-256 `1e0815c4da94a1484825b9aa508f12497d055420ef5935a3f94359c88845bc77`.
- Platform configuration SHA-256: `50ca1b51f144fbfab4aaa3f97e8f0c908b6c6af8ee9cf6d22162a0a720d72096`.
- Domain PDF SHA-256: `77fd528d1ef8ff5257e203d76aa8b3fa8ebb952fa062b08e6a1924573b7bf51f`; schema SQL SHA-256: `032b22660a1902c41242da8824f2ac0cebaa90ccefc3e6915479eb9b1dddd6ad`. **No SQL was executed.**
- Approved domain: 400 records across 8 checksum-bound outputs; approved physical schema: 31,341 records across 13 checksum-bound outputs. Both approved output inventories matched their existing approval/promotion snapshots before and after repair. Approved knowledge, approvals, promotions, and source artifacts were not modified.
- Assessed all 99 approved documented mappings and all eight deferred schema findings. All seven configured draft mapping outputs retain 517 total records and every stable ID. Four invalid-segment paths and seven documented view-base claims without a path were relocated intact to unresolved mappings, not deleted or renamed.
- The archived prior generation remains unchanged in `semantic-mapping-generation-review.md`, `semantic-mapping-generation-change-set.yaml`, and `semantic-mapping-generation-validation.json`. The historical `semantic-mapping-review-findings.md`, physical `schema-mapping-*` review artifacts, and dirty `knowledge/platforms/prompts.md` are preserved byte-for-byte.
- No semantic mapping resolution set exists. The deterministic corrections address explicit state/lineage/parser propagation defects only; no human semantic choice was invented.

## Result

| Measure | Result |
|---|---:|
| Documented mappings assessed | 99 / 99 |
| Deferred schema findings assessed | 8 / 8 |
| Mapping records retained | 517 |
| Stable IDs added / removed | 0 / 0 |
| Records changed | 517 |
| Records relocated from active canonical paths to unresolved | 11 |
| Records added / removed by relocation | 0 / 0 |
| State counts | {"conflict": 17, "partially-verified": 348, "unresolved": 152} |
| Runtime-authorized / runtime-eligible records | 0 / 0 |
| Relationship joins verified | 0 (48 remain unresolved) |

## Finding dispositions

1. **AF-SM-001 — corrected.** Every record now uses a policy verification state. Exact physical declarations are preserved separately as `physicalEvidenceState: declared`; 348 partial mappings are not promoted to verified.
2. **AF-SM-002 — corrected.** All 117 eligible view-column mappings cite the exact approved projection-lineage record. Output-column identity, lineage approval reference, and source-column IDs validate.
3. **AF-SM-003 — blocking defect corrected; underlying path discrepancies unresolved.** Four path claims retain their original strings and physical facts, and seven documented view-base claims retain their null path and base-object facts. All eleven were removed from the active canonical-path output and relocated under the same stable IDs to unresolved mappings with reasons and references. `isreworked` was not changed to `reworked`/`latestReworked`; `controlfunctional` was not changed to `controlFunction`.
4. **AF-SM-004 — propagation defect corrected; human conflict remains.** All 17 affected entity/attribute/path/storage/unresolved records are `conflict`, preserve the original domain mapping and cited finding, and carry the conflicting approved physical facts. No `wafer_rotation`/`wafer_load_orientation` interpretation or `MEASURE_MACHINE`/`METROLOGY_MACHINE` substitution was selected. Human disposition remains required. No identifier record was implicated by these attribute/object claims; all identifier categories remain independent.

Identifier categories were preserved independently (`canonical` 5, `associating` 4, `composite` 2, `internal-database` 5). The approved `SIMULATED` enum remains canonical; `SIMULATION` remains wording-only and nonexecutable.
5. **AF-SM-005 — nonblocking documented physical conflicts.** Both conflicts remain explicit, runtime-denied, and pending human disposition.
6. **AF-SM-006 — nonblocking source-model limitations.** The six other deferred schema findings remain assessed and unchanged.
7. **AF-SM-007 — nonblocking insufficient evidence.** Unsupported mappings and all 48 relationship joins remain unresolved with reasons; no guessed FK/join or identifier substitution was added.

## Validation

All deterministic checks passed: JSONL syntax and stable IDs; approved domain/schema reference integrity; policy-state consistency; approved view-lineage source/output identity and approval refs; active canonical-path segment validation across 88 paths and 235 segments; conflict propagation; identifier-category and enum preservation; unresolved relationships; and draft/runtime denial. Check-level counts and output hashes are in `semantic-mapping-validation.json`. Regression tests reproduced the original defects before correction and passed against the repaired dataset.

## Recommendation and boundary

`ACCEPT_WITH_COMMENTS` follows policy: no blocking findings remain; nonblocking source limitations, four unresolved path spellings, seven pathless base claims, and two human-disposition conflicts remain. This is **not** mapping approval. Every record remains draft and runtime-denied. No approval, promotion, release, or SQL execution occurred.

See `semantic-mapping-resolution-report.md` for per-record before/after hashes, exact changed outputs, checksums, regression results, and protected-file hash verification.
