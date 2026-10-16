# Analytics Foundation domain extraction review

Status: resolutions applied; pending human domain approval. No approval is recorded.

**Recommendation: ACCEPT_WITH_COMMENTS.** Zero blocking findings; 25 nonblocking comments remain; three findings are resolved by explicit representation policy.

## Source and scope

- Configuration: [platform.yaml](../../platform.yaml).
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
| rules | 78 |
| documentedMappings | 99 |
| unresolved | 28 |

Total: 400 atomic draft records in `../../knowledge/draft/domain`.

## Review focus

- Confirm canonical IDs versus source associating IDs, composite job keys, and numeric database IDs.
- Confirm diagram cardinalities; null means not stated for that association, not an inferred optional relationship.
- Retain temporary OTASJob and on-hold WaferComputeJob states.
- Removed Field.Image and MeasureProcessJob.latestExposureProcessJob associations are retained as removed, not active.
- Preserve SIMULATED as the declared enum value. SIMULATION is retained as a source wording variant only, not an executable alias unless separately verified.
- Review every unresolved record, especially contradictory database paths and names.
- Database claims are documented evidence only. Schema names are unknown here; no `lisa` qualification was inferred.
- Source requirements describe intended behavior, not proof of implemented runtime capabilities.

## Validation and gate

Serialized JSONL, local structural JSON Schema, configured fields, IDs, concept references, source hash/pages, identifier categories, cardinality syntax, draft status and evidence boundaries passed. No repository-wide validator or record schemas are present; the workflow's referenced validator is absent. Runtime/query-plan checks are not applicable to this domain-only extraction.

Human domain review and recorded approval are required before promotion. No approval file, approved record, schema extraction, semantic mapping release, or runtime release was created.

## Applied domain review resolutions

- 17 accurately preserved source issues are accepted as nonblocking and excluded from runtime asserted facts.
- Eight findings are deferred to semantic mapping. All 99 documented mappings are barred from runtime use until verified; none was verified here.
- Document version 099 is retained; cover version 09 remains an explicit discrepancy.
- SIMULATED remains canonical; SIMULATION is preserved as a non-executable source wording variant.
- One draft rule now explicitly prohibits interchange of composite domain identifiers and internal database identifiers.
- All original statements, source references, identifier categories, and enum literals are preserved.
- Resolution disposition names are retained for audit; the three explicit decisions are classified under the policy category `resolved-by-policy`.

See [resolution report](domain-resolution-report.md), [findings](domain-review-findings.md), and [change set](domain-change-set.yaml) for exact record changes and validation evidence.

The recommendation is not approval. Stop here for recorded human approval; approved knowledge and releases remain untouched.
