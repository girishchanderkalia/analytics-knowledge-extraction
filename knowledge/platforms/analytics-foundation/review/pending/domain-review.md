# Analytics Foundation domain extraction review

Status: pending human review. No approval is recorded.

## Source and scope

- Configuration: `../../sources/platform.yaml` (the configuration is under sources, not the platform root).
- Source: `../../sources/domain/Lana-ContextModel.pdf`, 44 pages.
- EPS Context Model; model 2.1.3; header document D001047842/099; last update 2025-11-27.
- SHA-256: `77fd528d1ef8ff5257e203d76aa8b3fa8ebb952fa062b08e6a1924573b7bf51f`.
- Initial extraction: no prior draft, source hash, approval, or release was present.
- Text reviewed with page-indexed extraction; model figures 2.1 and 2.2 and database figures 2.4-1 through 2.4-6 reviewed visually.

## Draft outputs

| Category | Records |
|---|---:|
| concepts | 35 |
| attributes | 73 |
| relationships | 48 |
| identifiers | 16 |
| enumerations | 23 |
| rules | 77 |
| documentedMappings | 99 |
| unresolved | 28 |

Total: 399 atomic draft records in `../../knowledge/draft/domain`.

## Review focus

- Confirm canonical IDs versus source associating IDs, composite job keys, and numeric database IDs.
- Confirm diagram cardinalities; null means not stated for that association, not an inferred optional relationship.
- Retain temporary OTASJob and on-hold WaferComputeJob states.
- Removed Field.Image and MeasureProcessJob.latestExposureProcessJob associations are retained as removed, not active.
- Store enum values exactly, especially chuck values; resolve SIMULATED versus SIMULATION.
- Review every unresolved record, especially contradictory database paths and names.
- Database claims are documented evidence only. Schema names are unknown here; no `lisa` qualification was inferred.
- Source requirements describe intended behavior, not proof of implemented runtime capabilities.

## Validation and gate

Serialized JSONL, local structural JSON Schema, configured fields, IDs, concept references, source hash/pages, identifier categories, cardinality syntax, draft status and evidence boundaries passed. No repository-wide validator or record schemas are present; the workflow's referenced validator is absent. Runtime/query-plan checks are not applicable to this domain-only extraction.

Human domain review and recorded approval are required before promotion. No approval file, approved record, schema extraction, semantic mapping release, or runtime release was created.
