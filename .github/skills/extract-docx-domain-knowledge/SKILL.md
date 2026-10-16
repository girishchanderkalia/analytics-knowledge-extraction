---
name: extract-docx-domain-knowledge
description: Extract headings, paragraphs, tables, captions, comments, and document metadata. Use when processing application knowledge sources or releases.
---

Extract headings, paragraphs, tables, captions, comments, and document metadata. Produce source-addressable fragments. Preserve revision and section provenance. Do not infer missing relations.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
