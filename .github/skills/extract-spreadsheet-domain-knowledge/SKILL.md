---
name: extract-spreadsheet-domain-knowledge
description: Extract workbook/sheet/table structure, headers, data dictionaries, named ranges, and documented enumerations. Use when processing application knowledge sources or releases.
---

Extract workbook/sheet/table structure, headers, data dictionaries, named ranges, and documented enumerations. Preserve cell ranges. Never treat example row values as universal domain rules.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
