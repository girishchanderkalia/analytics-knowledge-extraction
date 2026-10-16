---
name: extract-sql-query-capability
description: Parse approved SQL into projection, source objects, joins, filters, parameter contract, aggregation, grouping, ordering, grain, and output aliases. Use when processing application knowledge sources or releases.
---

Parse approved SQL into projection, source objects, joins, filters, parameter contract, aggregation, grouping, ordering, grain, and output aliases. Never infer analytical behavior absent from SQL.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
