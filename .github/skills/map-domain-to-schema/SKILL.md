---
name: map-domain-to-schema
description: Build evidence-based mappings among approved concepts, physical objects, query parameters, and result fields. Use when processing application knowledge sources or releases.
---

Build evidence-based mappings among approved concepts, physical objects, query parameters, and result fields. Use declared, implemented, verified, inferred, and unresolved evidence states. Require human review for inferred mappings.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
