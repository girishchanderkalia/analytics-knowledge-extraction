---
name: generate-evaluation-cases
description: Generate prompt-to-capability and prompt-to-plan evaluation cases from approved intents, filters, defaults, identifier semantics, limitations, and negative cases. Use when processing application knowledge sources or releases.
---

Generate prompt-to-capability and prompt-to-plan evaluation cases from approved intents, filters, defaults, identifier semantics, limitations, and negative cases. Do not create expected behavior beyond approved knowledge.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
