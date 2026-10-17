---
name: map-domain-to-schema
description: Build evidence-based mappings among approved concepts, physical objects, query parameters, and result fields. Use when processing application knowledge sources or releases.
---

Build evidence-based mappings among approved concepts, physical objects, query parameters, and result fields. Read the platform's mapping rules and the configured semantic-mapping review policy before writing output. Keep output in draft; never promote or authorize it.

## Output rules

- Write draft outputs only under the target application's `knowledge/draft`.
- Use stable IDs and source references.
- Keep each JSONL record atomic.
- State unresolved issues explicitly.
- Do not auto-approve generated content.
- Distinguish the physical declaration from the semantic mapping state. Preserve a source/extraction value such as `declared` in a physical-evidence field; the mapping's `classification` and `evidenceState` must use a state allowed by the configured policy.
- Use `partially-verified` only when an exact portion of the mapping is supported by approved domain and physical-schema records while semantic meaning, traversal, identifier, or lineage aspects remain unverified. Use `unresolved` when evidence is insufficient. Use `conflict` when approved claims disagree and retain both claims and their evidence. Never promote name similarity or a physical declaration alone to `verified`.
- Use `implemented` only with direct, cited query/code implementation evidence. Use `verified` only when approved domain evidence and approved physical-schema evidence independently establish the complete semantic mapping. Keep inferred mappings explicit, runtime-ineligible, and subject to human review.
- For each mapped view output column, add the exact approved projection-lineage record whose `outputColumnId` equals the mapped output-column record. Validate the lineage's approved status and approval reference, its output-column identity, and every referenced source-column ID. Do not treat lineage as proof of domain equivalence.
- A canonical path is active only when every segment resolves to an approved domain record. If any segment is unsupported or misspelled, preserve the exact path claim and physical facts in an unresolved mapping with the unresolved segment and reason; do not silently correct the token or substitute a similar attribute.
- Propagate documented physical conflicts to each affected entity, attribute, identifier (only when actually implicated), canonical-path, storage, and unresolved output. Keep the competing physical facts in a separate conflict-evidence field rather than presenting a disputed object as the resolved target. Mark the conflict runtime-ineligible and require human disposition.
- Preserve identifier categories independently (`canonical`, `associating`, `internal-database`, and `composite`). Do not use one category as a substitute for another. Preserve canonical `SIMULATED`; wording such as `SIMULATION` is not an executable canonical value.
- Set every mapping record to `status: draft`, `runtimeAuthorized: false`, and `runtimeEligible: false` unless an explicit, policy-valid verified state is independently established. Never grant runtime eligibility to unresolved, conflicting, partial, inferred, deprecated, or restricted mappings.
