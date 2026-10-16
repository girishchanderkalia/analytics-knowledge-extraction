---
name: extract-result-contract
description: Extract request/response fields from Java records/DTOs, OpenAPI, JSON Schema, or TypeScript. Use when processing application knowledge sources or releases.
---

Extract request/response fields from Java records/DTOs, OpenAPI, JSON Schema, or TypeScript. Capture name, type, nullability, collection meaning, description, units only when documented, and pagination/truncation semantics.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
