# Analytics Foundation knowledge release 1.0.0

- Initial immutable platform knowledge release; no earlier release exists for semantic diff comparison.
- Included approved records only: 400 domain, 31341 physical-schema, and 517 semantic-mapping records (32258 unique record IDs).
- Semantic mapping states are preserved as approved: verified=0, partially-verified=348, conflict=17, inferred=0, unresolved=152, deprecated=0.
- Runtime denial is preserved: 0 mappings are runtime eligible and 0 are runtime authorized. Promotion does not grant runtime authorization.
- All 148 restricted physical objects remain excluded from runtime. Source limitations and unresolved/conflicting evidence remain unchanged.
- Release validation: PASS. Checksum chains were not calculated or validated; legacy checksum fields were not evaluated.