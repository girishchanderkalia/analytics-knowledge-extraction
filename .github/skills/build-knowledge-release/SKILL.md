---
name: build-knowledge-release
description: Build an immutable release from approved records only. Use when processing application knowledge sources or releases.
---

Build an immutable release from approved records only. Generate manifest, JSONL bundle, retrieval index, checksums, changelog, and evaluation summary. Refuse overwrite of an existing release.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
