# Domain extraction findings

Status: resolutions applied; pending human domain approval.
This is a policy-based resolution application, not human approval.

**Recommendation: ACCEPT_WITH_COMMENTS.** Blocking: 0. Nonblocking comments: 25. Resolved by policy: 3.

Original source findings are preserved below; accepting a source issue does not resolve the source itself.

| Finding | Category | Resolution status | Blocking | Runtime policy |
|---|---|---|---|---|
| `analytics-foundation:unresolved:oi02` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:oi05` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:oi06` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:oi07` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:oi09` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:oi10` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:oi13` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:oi14` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:oi15` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:oi16` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:document-version` | resolved-by-policy | resolved | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:strategy-simulated` | resolved-by-policy | resolved | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:pointlayout-waferpoint` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:wafercompute-onhold` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:measure-rotation-column` | deferred-schema-verification | deferred-to-schema-mapping | false | do-not-use-until-verified |
| `analytics-foundation:unresolved:exposure-output-prefix` | deferred-schema-verification | deferred-to-schema-mapping | false | do-not-use-until-verified |
| `analytics-foundation:unresolved:measure-machine-name` | deferred-schema-verification | deferred-to-schema-mapping | false | do-not-use-until-verified |
| `analytics-foundation:unresolved:wafer-measures-direction` | deferred-schema-verification | deferred-to-schema-mapping | false | do-not-use-until-verified |
| `analytics-foundation:unresolved:reworked-path-spelling` | deferred-schema-verification | deferred-to-schema-mapping | false | do-not-use-until-verified |
| `analytics-foundation:unresolved:otas-controlfunctional` | deferred-schema-verification | deferred-to-schema-mapping | false | do-not-use-until-verified |
| `analytics-foundation:unresolved:schema-unverified` | deferred-schema-verification | deferred-to-schema-mapping | false | do-not-use-until-verified |
| `analytics-foundation:unresolved:layer-context-gap` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:frontend-placeholder` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:canonical-path-draft` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:path-example-mismatch` | deferred-schema-verification | deferred-to-schema-mapping | false | do-not-use-until-verified |
| `analytics-foundation:unresolved:flow-history` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:storage-vs-reticle` | accepted-source-issue | accepted-source-issue | false | exclude-from-asserted-facts |
| `analytics-foundation:unresolved:composite-vs-internal` | resolved-by-policy | resolved | false | exclude-from-asserted-facts |

## Preserved findings and explicit decisions

### analytics-foundation:unresolved:oi02

PDF page 2: Shortest unambiguous context path algorithm remains an open extensibility issue.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:oi05

PDF page 2: Flow execution attributes, generated context, settings location, and naming remain open.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:oi06

PDF page 2: Additional compute-job use cases need explicit model support or inventory queries.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:oi07

PDF page 2: Die-to-intrafield-point context is needed to avoid frontend coordinate recalculation.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:oi09

PDF page 2: LISJob classification and naming need architectural alignment.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:oi10

PDF page 2: A proposed Flow anchor for dataset references conflicts with Flow removal in model 2.1.3.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:oi13

PDF page 2: Virtual wafers from multiple jobs and cascaded processing require a defined model.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:oi14

PDF page 3: OTASJob is temporary and may be removed pending better plugin extensibility.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:oi15

PDF page 3: Canonical keys alone are not universally unique; document type and composite LISJob identity require clarification.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:oi16

PDF page 3: MeasurementType introduction/reversion requires domain-expert resolution.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:document-version

PDF page 1: Cover prints D001047842/09 while page headers print D001047842/099; header version is retained without asserting correction.

Disposition: `resolved-by-extraction-policy`.

Use document version 099 as the extracted version because it appears in repeated page headers. Preserve cover value 09 as a source metadata discrepancy.

### analytics-foundation:unresolved:strategy-simulated

PDF page 27: Prose uses SIMULATION while Figure 2.2 declares SIMULATED; do not silently alias these values.

Disposition: `resolved-with-source-variant`.

Preserve SIMULATED as the canonical enum literal declared in the model figure. Preserve SIMULATION as a source wording variant. Do not use SIMULATION as an executable enum value unless separately verified.

### analytics-foundation:unresolved:pointlayout-waferpoint

PDF page 25: Prose PointLayout is an abstract set-of-points pattern, while the diagram uses abstract WaferPoint; retain both without asserting equivalence.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:wafercompute-onhold

PDF page 27: WaferComputeJob is on hold; attributes are not defined and the cEPE structural replacement is pending.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:measure-rotation-column

PDF page 35: Mapping table says wafer_load_orientation but Figure 2.4-4 labels the view column wafer_rotation.

Disposition: `deferred-to-schema-mapping`.

Deferred stage: `semantic-mapping`.

### analytics-foundation:unresolved:exposure-output-prefix

PDF page 37: EXPOSURE_DOCUMENT output row is printed as measureprocessjob_outputdocuments; mapping direction is not corrected.

Disposition: `deferred-to-schema-mapping`.

Deferred stage: `semantic-mapping`.

### analytics-foundation:unresolved:measure-machine-name

PDF page 37: Equipment prose names METROLOGY_MACHINE but its column mapping uses MEASURE_MACHINE.

Disposition: `deferred-to-schema-mapping`.

Deferred stage: `semantic-mapping`.

### analytics-foundation:unresolved:wafer-measures-direction

PDF page 35: Mapping table lists wafermeasureprocessjob_measures although Figure 2.1 places Measures on wafer exposure jobs.

Disposition: `deferred-to-schema-mapping`.

Deferred stage: `semantic-mapping`.

### analytics-foundation:unresolved:reworked-path-spelling

PDF page 34: Canonical mapping paths use isreworked/islatestreworked whereas model attributes are reworked/latestReworked.

Disposition: `deferred-to-schema-mapping`.

Deferred stage: `semantic-mapping`.

### analytics-foundation:unresolved:otas-controlfunctional

PDF page 36: Mapping table uses controlfunctional while the model attribute is controlFunction.

Disposition: `deferred-to-schema-mapping`.

Deferred stage: `semantic-mapping`.

### analytics-foundation:unresolved:schema-unverified

PDF page 28: All database mappings remain documented; no physical schema extraction or verification was performed.

Disposition: `deferred-to-schema-mapping`.

Deferred stage: `semantic-mapping`.

### analytics-foundation:unresolved:layer-context-gap

PDF page 35: The document explicitly says no additional Layer context mapping is available for measurement.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:frontend-placeholder

PDF page 38: Domain-extension frontend-label guidelines are a placeholder with a broken section link.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:canonical-path-draft

PDF page 41: CO_04.B canonical-path uniqueness validation has draft source status, unlike accepted neighboring requirements.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:path-example-mismatch

PDF page 43: Appendix example starts measureprocessjob_waferexposureprocessjob_chuck_id; do not treat it as a validated traversal.

Disposition: `deferred-to-schema-mapping`.

Deferred stage: `semantic-mapping`.

### analytics-foundation:unresolved:flow-history

PDF page 4: Flow removal in 2.1.3 leaves older Flow-related open issues in the document.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:storage-vs-reticle

PDF page 26: Reticle and Image appear in the sub-wafer model while general storage rules distinguish wafer-up from sub-wafer context; do not infer extra inventory storage.

Disposition: `accepted-source-issue`.

### analytics-foundation:unresolved:composite-vs-internal

PDF page 17: Computed ProcessJobId/WaferProcessJobId domain keys must not be conflated with numeric relational id fields.

Disposition: `promoted-to-domain-rule`.

ProcessJobId and WaferProcessJobId are composite domain identifiers. Numeric relational id columns are internal database identifiers. These identifier categories are not interchangeable.

## Validation limitations

- Checks cover JSONL, required fields, stable IDs, references, hashes, policy coverage, runtime exclusions, and preservation of source facts.
- No repository record schemas or executable repository validator are supplied; external runtime compatibility is not certified.
- Database mappings remain documented and unverified. No source PDF or SQL was modified or executed.
- Human approval remains absent. No approved knowledge or runtime release was written.
