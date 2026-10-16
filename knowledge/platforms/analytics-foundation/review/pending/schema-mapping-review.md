# Analytics Foundation physical schema extraction

Status: draft extracted. Scope: physical metadata only; not a completed semantic-mapping review.

- Source: [lanadb-schema.sql](../../sources/schema/lanadb-schema.sql); SHA-256 `032b22660a1902c41242da8824f2ac0cebaa90ccefc3e6915479eb9b1dddd6ad`.
- Source database: 17.11 - Percona Server for PostgreSQL 17.11.1. Offline parser: pglast v8.6, PostgreSQL grammar (18, 6).
- No source SQL, function, session statement, or psql directive was executed; no database connection was made.
- Initial schema extraction; no previous schema outputs or hash exist.

| Category | Records |
|---|---:|
| schemas | 1 |
| tables | 308 |
| views | 13 |
| columns | 14981 |
| primaryKeys | 106 |
| foreignKeys | 415 |
| indexes | 456 |
| comments | 14680 |
| functions | 8 |
| sequences | 117 |
| viewLineage | 108 |
| restrictedObjects | 148 |
| parseErrors | 0 |

Total: 31341 draft records in [schema drafts](../../knowledge/draft/schema).

## Coverage and representation

- All 16197 top-level SQL statements parsed. Session settings and psql guards are accounted for in the change set, not executed.
- 106 primary keys and 415 foreign keys resolved from ALTER TABLE.
- 24 unique constraints are preserved in table records; four triggers are embedded in their tables.
- Identity-generated sequences, sequence ownership, column defaults, FK actions, index keys and predicates are preserved.
- PostgreSQL-folded identifiers retain semantic names; qualified names are rendered with explicit quoting to preserve case and punctuation.
- Direct view dependencies and output expressions are preserved. Full query SQL is retained, including joins, filters and UNION branches.
- Computed view types requiring PostgreSQL expression resolution are null, not guessed; UNION projection lineage includes every operand.
- All functions and sequences, plus conservatively identified administrative tables, are restricted. This policy classification is not inferred business meaning.
- No object is authorized for runtime use and no documented domain mapping was marked verified.

## Review boundary

- The configured schema-mapping review policy is missing; this does not prevent physical parsing, but policy-based combined review cannot be completed.
- Next stage: build semantic mappings against the approved domain evidence, evaluate all eight deferred domain/schema findings, then complete schema-mapping review.
- Repository-level validation remains required before an immutable release.
- No approval, approved schema knowledge, release, or domain record was changed.

## Additional verification

Five parser fixture tests passed. All 89 view projections resolve to physical source column IDs, including both operands of UNION views. Unknown computed data types remain explicit rather than guessed.
