# Domain extraction findings

This is an extraction self-check, not an independent reviewer verdict or human approval.

Structural validation: passed. Semantic disposition: pending review.

## Open source findings

- `analytics-foundation:unresolved:oi02` (PDF page 2): Shortest unambiguous context path algorithm remains an open extensibility issue.
- `analytics-foundation:unresolved:oi05` (PDF page 2): Flow execution attributes, generated context, settings location, and naming remain open.
- `analytics-foundation:unresolved:oi06` (PDF page 2): Additional compute-job use cases need explicit model support or inventory queries.
- `analytics-foundation:unresolved:oi07` (PDF page 2): Die-to-intrafield-point context is needed to avoid frontend coordinate recalculation.
- `analytics-foundation:unresolved:oi09` (PDF page 2): LISJob classification and naming need architectural alignment.
- `analytics-foundation:unresolved:oi10` (PDF page 2): A proposed Flow anchor for dataset references conflicts with Flow removal in model 2.1.3.
- `analytics-foundation:unresolved:oi13` (PDF page 2): Virtual wafers from multiple jobs and cascaded processing require a defined model.
- `analytics-foundation:unresolved:oi14` (PDF page 3): OTASJob is temporary and may be removed pending better plugin extensibility.
- `analytics-foundation:unresolved:oi15` (PDF page 3): Canonical keys alone are not universally unique; document type and composite LISJob identity require clarification.
- `analytics-foundation:unresolved:oi16` (PDF page 3): MeasurementType introduction/reversion requires domain-expert resolution.
- `analytics-foundation:unresolved:document-version` (PDF page 1): Cover prints D001047842/09 while page headers print D001047842/099; header version is retained without asserting correction.
- `analytics-foundation:unresolved:strategy-simulated` (PDF page 27): Prose uses SIMULATION while Figure 2.2 declares SIMULATED; do not silently alias these values.
- `analytics-foundation:unresolved:pointlayout-waferpoint` (PDF page 25): Prose PointLayout is an abstract set-of-points pattern, while the diagram uses abstract WaferPoint; retain both without asserting equivalence.
- `analytics-foundation:unresolved:wafercompute-onhold` (PDF page 27): WaferComputeJob is on hold; attributes are not defined and the cEPE structural replacement is pending.
- `analytics-foundation:unresolved:measure-rotation-column` (PDF page 35): Mapping table says wafer_load_orientation but Figure 2.4-4 labels the view column wafer_rotation.
- `analytics-foundation:unresolved:exposure-output-prefix` (PDF page 37): EXPOSURE_DOCUMENT output row is printed as measureprocessjob_outputdocuments; mapping direction is not corrected.
- `analytics-foundation:unresolved:measure-machine-name` (PDF page 37): Equipment prose names METROLOGY_MACHINE but its column mapping uses MEASURE_MACHINE.
- `analytics-foundation:unresolved:wafer-measures-direction` (PDF page 35): Mapping table lists wafermeasureprocessjob_measures although Figure 2.1 places Measures on wafer exposure jobs.
- `analytics-foundation:unresolved:reworked-path-spelling` (PDF page 34): Canonical mapping paths use isreworked/islatestreworked whereas model attributes are reworked/latestReworked.
- `analytics-foundation:unresolved:otas-controlfunctional` (PDF page 36): Mapping table uses controlfunctional while the model attribute is controlFunction.
- `analytics-foundation:unresolved:schema-unverified` (PDF page 28): All database mappings remain documented; no physical schema extraction or verification was performed.
- `analytics-foundation:unresolved:layer-context-gap` (PDF page 35): The document explicitly says no additional Layer context mapping is available for measurement.
- `analytics-foundation:unresolved:frontend-placeholder` (PDF page 38): Domain-extension frontend-label guidelines are a placeholder with a broken section link.
- `analytics-foundation:unresolved:canonical-path-draft` (PDF page 41): CO_04.B canonical-path uniqueness validation has draft source status, unlike accepted neighboring requirements.
- `analytics-foundation:unresolved:path-example-mismatch` (PDF page 43): Appendix example starts measureprocessjob_waferexposureprocessjob_chuck_id; do not treat it as a validated traversal.
- `analytics-foundation:unresolved:flow-history` (PDF page 4): Flow removal in 2.1.3 leaves older Flow-related open issues in the document.
- `analytics-foundation:unresolved:storage-vs-reticle` (PDF page 26): Reticle and Image appear in the sub-wafer model while general storage rules distinguish wafer-up from sub-wafer context; do not infer extra inventory storage.
- `analytics-foundation:unresolved:composite-vs-internal` (PDF page 17): Computed ProcessJobId/WaferProcessJobId domain keys must not be conflated with numeric relational id fields.

## Validation limitations

- No repository JSON Schemas or executable validator are supplied. A local structural schema was used; no external runtime compatibility is claimed.
- Source DDL was not read or executed. Database qualification and physical correctness require a later schema/mapping stage.
- Required review outputs only were written. Human approval remains absent and promotion/release are blocked.
