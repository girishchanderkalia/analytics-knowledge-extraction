# Semantic mapping review findings

Status: draft validation only; no human approval or promotion.

## Blocking review gates

- Configured policy `review/config/schema-mapping-review-policy.yaml` is absent.
- `review/config/semantic-mapping-review-policy.yaml` exists but is empty; no usable semantic review policy is available.
- Configured schema-mapping pending filenames collide with historical physical extraction artifacts. Those artifacts remain unchanged; semantic artifacts use distinct names.
- Semantic mappings are not approved; runtime authorization remains false.

## Evidence findings

- Verified semantic equivalences: 0.
- Implemented/query evidence claims: 0.
- Inferred mappings: 0.
- Exact approved physical declarations are reported only as `declared`, not `verified`.
- Missing or ambiguous physical targets remain `unresolved`; no nearest-name substitute was selected.
- All eight deferred findings are retained unresolved, without silent resolution.
- No SQL was run; no query capability or runtime authorization is claimed.

## Detailed counts

```yaml
documentedDomainMappingsAssessed: 99
entityObject:
  declared: 92
  unresolved: 7
attributeColumn:
  declared: 76
  unresolved: 3
identifier:
  unresolved: 11
  declared: 5
relationshipJoin:
  unresolved: 48
canonicalPath:
  declared: 92
  unresolved: 7
storageBoundary:
  declared: 92
  unresolved: 7
deferredSchemaFindings: 8
deferredFindingIds:
- analytics-foundation:unresolved:measure-rotation-column
- analytics-foundation:unresolved:exposure-output-prefix
- analytics-foundation:unresolved:measure-machine-name
- analytics-foundation:unresolved:wafer-measures-direction
- analytics-foundation:unresolved:reworked-path-spelling
- analytics-foundation:unresolved:otas-controlfunctional
- analytics-foundation:unresolved:schema-unverified
- analytics-foundation:unresolved:path-example-mismatch
implementationEvidenceRecords: 0
verifiedSemanticMappings: 0
inferredMappings: 0
documentedMappingPhysicalCommentRefs: 20
```

The change-set enumerates the complete 99 documented-mapping coverage and all eight deferred finding IDs, with input/output SHA-256 hashes and unresolved dispositions.
