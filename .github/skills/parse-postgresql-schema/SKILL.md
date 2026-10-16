---
name: parse-postgresql-schema
description: Parse schema-only PostgreSQL SQL without executing it. Use when processing application knowledge sources or releases.
---

Parse schema-only PostgreSQL SQL without executing it. Produce JSONL for schemas, tables, views, columns, constraints, foreign keys, indexes, comments, functions, sequences, and dependencies. Resolve ALTER TABLE constraints. Preserve quoted identifiers. Mark parse failures rather than guessing.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
