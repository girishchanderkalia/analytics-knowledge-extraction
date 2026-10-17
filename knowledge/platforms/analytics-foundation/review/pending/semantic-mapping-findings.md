# Semantic mapping review findings — analytics-foundation

Policy: `review/config/semantic-mapping-review-policy.yaml` (SHA-256 `1e0815c4da94a1484825b9aa508f12497d055420ef5935a3f94359c88845bc77`)  
Recommendation: **ACCEPT_WITH_COMMENTS** — 0 blocking findings, 3 nonblocking finding groups.  
Human approval/promotion/release: not performed.

## Corrected blocking findings

### AF-SM-001 — policy state mismatch — corrected

All 357 former `declared` semantic states were normalized using record-level evidence. Physical declarations remain in `physicalEvidenceState: declared`; partial, conflict, and unresolved states reflect evidence. No mapping was bulk-verified.

### AF-SM-002 — missing approved view lineage — corrected

All 117 applicable view-column mappings cite the exact approved projection lineage. Output identity, approved lineage and approval reference, and 52 unique source-column IDs validate.

### AF-SM-003 — invalid or absent canonical path — corrected by unresolved quarantine

Four source path tokens remain unresolvable and seven approved documented-view-base claims have no canonical path or column. All eleven records were removed from the active canonical-path output and retained under their original stable IDs in `unresolved-mappings.jsonl`, preserving source claims, base-object/physical facts, and reasons. No path was synthesized or spelling silently corrected. The 88 active paths and 235 segments all resolve to approved domain names.

### AF-SM-004 — conflict propagation — corrected

All 17 conflict-linked mapping and unresolved records carry both evidence sides, are classified `conflict`, and require human disposition. No rotation or machine target was selected. Identifier categories remain independent, and `SIMULATED` remains canonical while `SIMULATION` remains wording-only/nonexecutable.

## Remaining nonblocking findings

- **AF-SM-005 — documented physical conflicts.** Both conflicts remain for human disposition; runtime is denied.
- **AF-SM-006 — source-model issues.** Six other deferred schema findings remain unchanged.
- **AF-SM-007 — insufficient evidence.** Unsupported mappings and all 48 relationship joins remain unresolved with reasons.

The former `semantic-mapping-review-findings.md` is preserved unchanged as historical input; this is the configured findings output.
