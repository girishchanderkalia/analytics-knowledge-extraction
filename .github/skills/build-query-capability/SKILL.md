---
name: build-query-capability
description: Combine approved domain knowledge, schema, query, and contract evidence. Use when processing application knowledge sources or releases.
---

Combine approved domain knowledge, schema, query, and contract evidence. Define intents, synonyms, required/optional inputs, default sources, metrics, dimensions, grain, approved join path, output contract, limitations, compatibility, and source evidence.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
