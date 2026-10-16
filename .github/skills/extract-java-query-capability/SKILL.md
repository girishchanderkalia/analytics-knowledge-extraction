---
name: extract-java-query-capability
description: Inspect Java JDBC, Spring JDBC, JPA, QueryDSL, request records, and result records. Use when processing application knowledge sources or releases.
---

Inspect Java JDBC, Spring JDBC, JPA, QueryDSL, request records, and result records. Extract SQL text, dynamic predicates, parameter binding, aliases, joins, grouping, ordering, limits, types, truncation, and unsupported semantics. Flag unvalidated identifier interpolation.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
