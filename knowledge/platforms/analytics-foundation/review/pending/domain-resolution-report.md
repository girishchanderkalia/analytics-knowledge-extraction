# Analytics Foundation domain resolution report

Status: resolved-pending-human-approval. Recommendation: **ACCEPT_WITH_COMMENTS**.

## Inputs

- [Platform configuration](../../platform.yaml)
- [Domain policy](../config/domain-review-policy.yaml)
- [Explicit resolution set](domain-review-resolutions.yaml)
- Resolution SHA-256: `9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9`
- Policy SHA-256: `12aeac842466706e2bddfb9f96ba659231790fc17e201acc1abac37f4054f8c0`

## Policy interpretation

- `accepted-source-issue` is the permitted resolutionStatus of category `accepted-source-issue`.
- `deferred-to-schema-mapping` is the permitted resolutionStatus of category `deferred-schema-verification`.
- `resolved-by-extraction-policy`, `resolved-with-source-variant`, and `promoted-to-domain-rule` are explicit decision dispositions, not new policy categories. Their deterministic representation changes are classified as `resolved-by-policy`, with resolutionStatus `resolved`.
- The new domain rule remains draft: the allowed mutation `add-approved-domain-rule` does not authorize promotion or approval.
- Blocking and runtime treatments come from policy, not merely the resolution set's stated outcome.
- Resolved finding records remain excluded from asserted facts; the enum representation and draft domain rule carry the explicit decisions.
- All 99 documented mappings receive the schema-unverified runtime restriction; no database facts or spellings are repaired during this stage.

## Result

- 17 accepted source issues; eight deferred schema/mapping findings; three resolved-by-policy findings.
- 128 existing records updated, one draft rule added, zero removed; 271 records unchanged.
- Total draft records: 399 -> 400. Rules: 77 -> 78. Other category counts unchanged.
- Zero blocking findings; 25 nonblocking comments. Recommendation derived from the policy: ACCEPT_WITH_COMMENTS.

## Exact record changes

Only changed fields are shown. Omitted fields are unchanged; `previouslyAbsentFields` distinguishes absent fields from null. Full new records are shown for additions.

### analytics-foundation:unresolved:oi02

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "oi02"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:oi05

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "oi05"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:oi06

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "oi06"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:oi07

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "oi07"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:oi09

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "oi09"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:oi10

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "oi10"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:oi13

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "oi13"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:oi14

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "oi14"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:oi15

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "oi15"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:oi16

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "oi16"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:document-version

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "resolved-by-policy",
    "decision": "Use document version 099 as the extracted version because it appears in repeated page headers. Preserve cover value 09 as a source metadata discrepancy.",
    "disposition": "resolved-by-extraction-policy",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "document-version"
    },
    "resolutionStatus": "resolved",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "decision",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Use document version 099 as the extracted version because it appears in repeated page headers. Preserve cover value 09 as a source metadata discrepancy."
}
```

### analytics-foundation:unresolved:strategy-simulated

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "resolved-by-policy",
    "decision": "Preserve SIMULATED as the canonical enum literal declared in the model figure. Preserve SIMULATION as a source wording variant. Do not use SIMULATION as an executable enum value unless separately verified.",
    "disposition": "resolved-with-source-variant",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "strategy-simulated"
    },
    "resolutionStatus": "resolved",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "decision",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Preserve SIMULATED as the canonical enum literal declared in the model figure. Preserve SIMULATION as a source wording variant. Do not use SIMULATION as an executable enum value unless separately verified."
}
```

### analytics-foundation:unresolved:pointlayout-waferpoint

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "pointlayout-waferpoint"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:wafercompute-onhold

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "wafercompute-onhold"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:measure-rotation-column

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "deferred-schema-verification",
    "disposition": "deferred-to-schema-mapping",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "measure-rotation-column"
    },
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "Explicit deferredToSchemaMapping resolution; treatment derived from deferred-schema-verification policy."
}
```

### analytics-foundation:unresolved:exposure-output-prefix

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "deferred-schema-verification",
    "disposition": "deferred-to-schema-mapping",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "exposure-output-prefix"
    },
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "Explicit deferredToSchemaMapping resolution; treatment derived from deferred-schema-verification policy."
}
```

### analytics-foundation:unresolved:measure-machine-name

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "deferred-schema-verification",
    "disposition": "deferred-to-schema-mapping",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "measure-machine-name"
    },
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "Explicit deferredToSchemaMapping resolution; treatment derived from deferred-schema-verification policy."
}
```

### analytics-foundation:unresolved:wafer-measures-direction

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "deferred-schema-verification",
    "disposition": "deferred-to-schema-mapping",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "wafer-measures-direction"
    },
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "Explicit deferredToSchemaMapping resolution; treatment derived from deferred-schema-verification policy."
}
```

### analytics-foundation:unresolved:reworked-path-spelling

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "deferred-schema-verification",
    "disposition": "deferred-to-schema-mapping",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "reworked-path-spelling"
    },
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "Explicit deferredToSchemaMapping resolution; treatment derived from deferred-schema-verification policy."
}
```

### analytics-foundation:unresolved:otas-controlfunctional

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "deferred-schema-verification",
    "disposition": "deferred-to-schema-mapping",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "otas-controlfunctional"
    },
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "Explicit deferredToSchemaMapping resolution; treatment derived from deferred-schema-verification policy."
}
```

### analytics-foundation:unresolved:schema-unverified

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "deferred-schema-verification",
    "disposition": "deferred-to-schema-mapping",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "schema-unverified"
    },
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "Explicit deferredToSchemaMapping resolution; treatment derived from deferred-schema-verification policy."
}
```

### analytics-foundation:unresolved:layer-context-gap

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "layer-context-gap"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:frontend-placeholder

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "frontend-placeholder"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:canonical-path-draft

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "canonical-path-draft"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:path-example-mismatch

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "deferred-schema-verification",
    "disposition": "deferred-to-schema-mapping",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "path-example-mismatch"
    },
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "Explicit deferredToSchemaMapping resolution; treatment derived from deferred-schema-verification policy."
}
```

### analytics-foundation:unresolved:flow-history

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "flow-history"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:storage-vs-reticle

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "accepted-source-issue",
    "disposition": "accepted-source-issue",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "storage-vs-reticle"
    },
    "resolutionStatus": "accepted-source-issue",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "Explicit acceptedSourceIssues resolution; treatment derived from accepted-source-issue policy."
}
```

### analytics-foundation:unresolved:composite-vs-internal

File: `knowledge/draft/domain/unresolved.jsonl`

```json
{
  "before": {
    "resolutionStatus": "open"
  },
  "after": {
    "blocking": false,
    "category": "resolved-by-policy",
    "decision": "ProcessJobId and WaferProcessJobId are composite domain identifiers. Numeric relational id columns are internal database identifiers. These identifier categories are not interchangeable.",
    "disposition": "promoted-to-domain-rule",
    "resolutionRef": {
      "path": "review/pending/domain-review-resolutions.yaml",
      "sha256": "9734c28a063b43090e570e188ad496e0f4bcb19964620337030b16cf27647ef9",
      "findingKey": "composite-vs-internal"
    },
    "resolutionStatus": "resolved",
    "runtimePolicy": "exclude-from-asserted-facts"
  },
  "previouslyAbsentFields": [
    "blocking",
    "category",
    "decision",
    "disposition",
    "resolutionRef",
    "runtimePolicy"
  ],
  "reason": "ProcessJobId and WaferProcessJobId are composite domain identifiers. Numeric relational id columns are internal database identifiers. These identifier categories are not interchangeable."
}
```

### analytics-foundation:enumerations:strategytype-simulated

File: `knowledge/draft/domain/enumerations.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:strategy-simulated",
    "sourceVariants": [
      {
        "literal": "SIMULATION",
        "sourceRefs": [
          {
            "sourceId": "lana-context-model",
            "documentId": "D001047842",
            "documentVersion": "099",
            "modelVersion": "2.1.3",
            "sourcePath": "sources/domain/Lana-ContextModel.pdf",
            "sha256": "77fd528d1ef8ff5257e203d76aa8b3fa8ebb952fa062b08e6a1924573b7bf51f",
            "page": 27,
            "section": "Source inconsistency or limitation"
          }
        ],
        "executable": false,
        "runtimePolicy": "do-not-use-until-verified",
        "interpretation": "Source wording variant, not an executable enum alias."
      }
    ]
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "sourceVariants"
  ],
  "reason": "Preserve SIMULATED as the canonical enum literal declared in the model figure. Preserve SIMULATION as a source wording variant. Do not use SIMULATION as an executable enum value unless separately verified."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-reticle-keyreticleid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-isreworked

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-processjobid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-lotid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferlot-keylotid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-layerid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-processstep-layer-keyphysicallayerid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-processstep-productid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-equipment-id

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-starttime

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-endtime

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-recipename

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-softwarerelease

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-islatestreworked

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferexposureprocessjob-waferid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferexposureprocessjob-waferprocessingorder

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferexposureprocessjob-starttime

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferexposureprocessjob-endtime

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferexposureprocessjob-status

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferexposureprocessjob-wafersequencenr

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferexposureprocessjob-chuck-id

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferexposureprocessjob-wafer-keywaferid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferexposureprocessjob-isreworked

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferexposureprocessjob-processjobid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-waferexposureprocessjob-waferprocessjobid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-processjobid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-lotid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-waferlot-keylotid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-layerid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-processstep-layer-keyphysicallayerid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-processstep-productid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-equipment-id

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-starttime

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-endtime

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-recipename

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-softwarerelease

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-waferrotation

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-waferlot-waferdiameter

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-wafermeasureprocessjob-waferid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-wafermeasureprocessjob-waferprocessingorder

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-wafermeasureprocessjob-starttime

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-wafermeasureprocessjob-status

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-wafermeasureprocessjob-wafer-keywaferid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-wafermeasureprocessjob-processjobid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-wafermeasureprocessjob-waferprocessjobid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-applicationid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-requesttype

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-id

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-applicationname

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-createdtime

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-starttime

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-endtime

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-status

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-softwarerelease

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-outputdocuments-keydocumentid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-recipename

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-machineid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:otasjob-controlfunctional

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:otasjob-controlstrategy

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposure-process-job-base

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:wafer-exposure-process-job-base

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measure-process-job-base

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:wafer-measure-process-job-base

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lis-job-base

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:latest-exposure-base

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:wafer-latest-exposure-base

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-measures

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-latestexposure

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:wafermeasureprocessjob-latestexposure

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:wafermeasureprocessjob-measures

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-inputdocuments-exposure

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-inputdocuments-measure

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-input

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:lisjob-output

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-inputdocuments-keydocumentid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-inputdocuments-type

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-inputdocuments-subconfiguration

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-inputdocuments-servicedescriptor

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-outputdocuments-keydocumentid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-outputdocuments-type

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-outputdocuments-subconfiguration

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-outputdocuments-servicedescriptor

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-inputdocuments-keydocumentid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-inputdocuments-type

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-inputdocuments-subconfiguration

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-inputdocuments-servicedescriptor

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-outputdocuments-source-exposure-keydocumentid

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-outputdocuments-source-exposure-type

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-outputdocuments-source-exposure-subconfiguration

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-outputdocuments-source-exposure-servicedescriptor

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-equipment-id-exposure-machine

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:exposureprocessjob-equipment-namebycustomer-exposure-machine

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-equipment-id-measure-machine

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:measureprocessjob-equipment-namebycustomer-measure-machine

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:unmapped-exposureprocessjob-waferexposureprocessjob-wafer-waferscribe

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:unmapped-measureprocessjob-wafermeasureprocessjob-wafer-waferscribe

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:unmapped-measureprocessjob-processstep-layer-classification

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:unmapped-equipment-equipmenttype

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:documentedMappings:unmapped-equipment-classification

File: `knowledge/draft/domain/documented-mappings.jsonl`

```json
{
  "before": {},
  "after": {
    "resolutionFindingId": "analytics-foundation:unresolved:schema-unverified",
    "resolutionStatus": "deferred-to-schema-mapping",
    "runtimePolicy": "do-not-use-until-verified",
    "targetStage": "semantic-mapping"
  },
  "previouslyAbsentFields": [
    "resolutionFindingId",
    "resolutionStatus",
    "runtimePolicy",
    "targetStage"
  ],
  "reason": "The schema-unverified resolution and resolution-set policy prohibit runtime use of all unverified mappings."
}
```

### analytics-foundation:rules:composite-vs-internal-identifiers

File: `knowledge/draft/domain/rules.jsonl`

```json
{
  "before": null,
  "after": {
    "evidenceState": "documented",
    "id": "analytics-foundation:rules:composite-vs-internal-identifiers",
    "platformId": "analytics-foundation",
    "prohibitedEquivalence": [
      {
        "left": "composite",
        "right": "internal-database"
      }
    ],
    "resolutionFindingId": "analytics-foundation:unresolved:composite-vs-internal",
    "sourceRefs": [
      {
        "sourceId": "lana-context-model",
        "documentId": "D001047842",
        "documentVersion": "099",
        "modelVersion": "2.1.3",
        "sourcePath": "sources/domain/Lana-ContextModel.pdf",
        "sha256": "77fd528d1ef8ff5257e203d76aa8b3fa8ebb952fa062b08e6a1924573b7bf51f",
        "page": 17,
        "section": "Source inconsistency or limitation",
        "figure": "2.1"
      }
    ],
    "statement": "ProcessJobId and WaferProcessJobId are composite domain identifiers. Numeric relational id columns are internal database identifiers. These identifier categories are not interchangeable.",
    "status": "draft"
  },
  "previouslyAbsentFields": [],
  "reason": "ProcessJobId and WaferProcessJobId are composite domain identifiers. Numeric relational id columns are internal database identifiers. These identifier categories are not interchangeable."
}
```

## Review artifact changes

- Review: initial extraction/pending-review -> applied resolutions/pending-human-approval; counts and current configuration path updated.
- Findings: 28 open findings -> 17 accepted source issues, eight deferrals, three policy-resolved findings; source statements unchanged.
- Change set: refreshed counts and SHA-256 checksums, recorded policy/resolution hashes, exact resolution delta, validation and derived outcome.
- Initial-extraction changes remain a comparison to the absent extraction baseline: 400 additions. `resolutionChanges` separately records this stage's 128 updated records and one addition.

## Verification

- All resolution keys exist in both findings and draft records; every finding is covered exactly once.
- Required fields, draft status, unique IDs, source hashes/page bounds, concept references, and preserved sourceRefs checked.
- All original statements, identifier categories, enum constants/values and existing domain rules preserved.
- SIMULATION is non-executable metadata only; SIMULATED remains the declared value.
- All mapping evidence remains documented; physical verification remains not-performed.
- Source PDF/SQL, approved/rejected review artifacts, approved knowledge, and releases protected by before/after hashes.
- No repository JSON Schemas or repository validator exist; validation is deterministic structural/policy validation, not runtime certification.

## Human gate

No approval file is created and no existing approval is modified. Next action: human domain approval. No promotion or release is performed.
