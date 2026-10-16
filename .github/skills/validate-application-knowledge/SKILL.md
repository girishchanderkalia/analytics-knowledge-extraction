---
name: validate-application-knowledge
description: Run deterministic validation for JSON syntax, JSON Schema, unique IDs, references, statuses, evidence, approvals, allowed runtime objects, query-plan compatibility, and source hashes. Use when processing application knowledge sources or releases.
---

Run deterministic validation for JSON syntax, JSON Schema, unique IDs, references, statuses, evidence, approvals, allowed runtime objects, query-plan compatibility, and source hashes. Fail closed.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
