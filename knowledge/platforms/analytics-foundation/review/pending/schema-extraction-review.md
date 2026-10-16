# Analytics Foundation physical schema review

**Recommendation: ACCEPT_WITH_COMMENTS (blocking parser defects corrected).**
This is a review recommendation, not a human rejection or approval record.

## Scope and evidence

- [Configuration](../../platform.yaml)
- [Physical schema review policy](../config/schema-extraction-review-policy.yaml)
- [Source DDL](../../sources/schema/lanadb-schema.sql)
- Source SHA-256: `032b22660a1902c41242da8824f2ac0cebaa90ccefc3e6915479eb9b1dddd6ad`.
- Reviewed all 31,341 draft records across the 13 configured output files against 16,197 parsed PostgreSQL statements.
- Reparsed SQL offline. No database connection or SQL execution occurred.
- Review filenames were taken from the explicitly supplied sibling `stages.inputs` configuration; misplaced nesting is recorded as a repository-improvement finding.

## Outcome

- Zero remaining blockers. All three parser-defect findings are resolved; the original REJECT outcome is retained in the change-set history.
- Six nonblocking findings: three unknown computed types, restricted runtime objects, absent repository validation, and physical-stage configuration nesting.
- Zero parser syntax errors, broken references, or inventory mismatches detected.
- All seven parser test methods pass, including 13 dynamic-SQL classification subcases and invalid-body fail-closed coverage. The false positives failed before the fix and pass afterward.
- Exactly three draft fields changed: hasDynamicSql true -> false. All 31,341 regenerated records match the saved draft. Nothing is approved, promoted, or released.

## Resolved blocking corrections

| Routine | Incorrect field | Expected | Reason |
|---|---|---|---|
| `check_repack_privileges` | `hasDynamicSql: true` | `false` | EXECUTE occurs in a comment and privilege-description string literals, not a procedural statement. |
| `grant_repack_privileges` | `hasDynamicSql: true` | `false` | EXECUTE is a privilege keyword in GRANT EXECUTE ON ALL FUNCTIONS, not a procedural dynamic-SQL statement. |
| `pg_repack_reinstall` | `hasDynamicSql: true` | `false` | EXECUTE is a privilege keyword in GRANT EXECUTE ON ALL FUNCTIONS, not a procedural dynamic-SQL statement. |

Applied fix: classify actual procedural dynamic-SQL nodes with offline pglast.parse_plpgsql, excluding comments, literals and GRANT privilege keywords. RETURN QUERY EXECUTE, dynamic FOR loops, and dynamic OPEN cursors are also recognized. Checksums refreshed and source review rerun.
These functions remain restricted. This review finding concerns metadata correctness, not a newly discovered exploitable vulnerability.

## Validation performed

| Check | Compared |
|---|---:|
| record-reference-checks | 31341 |
| exact-function-bodies | 8 |
| procedural-dynamic-SQL-classifications | 8 |
| table-columns | 14892 |
| table-names | 308 |
| exact-comments | 14680 |
| identity-sequences | 15 |
| view-query-AST-equivalence | 13 |
| sequence-options | 102 |
| sequence-ownership | 4 |
| alter-defaults | 4 |
| unique-constraints | 24 |
| primary-keys | 106 |
| index-definition-AST-equivalence | 456 |
| trigger-definition-AST-equivalence | 4 |
| foreign-keys | 415 |

- All 89 projection records resolve their referenced source columns; both UNION operands are represented.
- Three result types are explicitly unknown: exposure_process_job.rework_context, lis_job.application_id, lis_job.request_type. The policy allows these as nonblocking parser limitations; resolve before typed runtime use.
- Views, indexes and trigger definitions are compared by PostgreSQL AST equivalence; comments and routine bodies are compared exactly.
- Unknown view nullability is not guessed.
- All runtime authorization flags remain false; administrative metadata is retained with runtime restrictions.
- No domain meaning or verified domain mappings are derived in this review.

## Next action

Blocking defects are corrected and re-review passed. Human physical-schema approval is still required; this recommendation is not approval. Correct physicalSchema configuration nesting before relying on stage-specific approval/promotion automation. Repository-wide validation remains required before release. See [findings](schema-extraction-findings.md) and [review change set](schema-extraction-change-set.yaml).

See [resolution report](schema-extraction-resolution-report.md) for the exact delta and reproducible regression cases.
