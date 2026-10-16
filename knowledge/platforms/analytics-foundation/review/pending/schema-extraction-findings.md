# Physical schema review findings

Recommendation: **ACCEPT_WITH_COMMENTS**. Zero open blocking findings; three resolved parser defects; six nonblocking comments.
No human approval, promotion, runtime authorization or release was performed.

## analytics-foundation:physical-schema-review:dynamic-sql-false-positive-check_repack_privileges

- Category: `parser-defect`
- Blocking: `false`
- Resolution status: `resolved-parser-defect`
- Original observation: "lisa"."check_repack_privileges" is incorrectly classified as containing dynamic SQL.

```json
{
  "id": "analytics-foundation:physical-schema-review:dynamic-sql-false-positive-check_repack_privileges",
  "category": "parser-defect",
  "blocking": false,
  "summary": "\"lisa\".\"check_repack_privileges\" is incorrectly classified as containing dynamic SQL.",
  "affectedRecordIds": [
    "analytics-foundation:schema:functions:%22lisa%22.%22check_repack_privileges%22%28%29"
  ],
  "sourceRefs": [
    {
      "sourceId": "lanadb-schema",
      "sourcePath": "sources/schema/lanadb-schema.sql",
      "sha256": "032b22660a1902c41242da8824f2ac0cebaa90ccefc3e6915479eb9b1dddd6ad",
      "lineStart": 33,
      "lineEnd": 90,
      "statementType": "CreateFunctionStmt"
    }
  ],
  "details": {
    "field": "hasDynamicSql",
    "actual": true,
    "expected": false,
    "sourceEvidence": "EXECUTE occurs in a comment and privilege-description string literals, not a procedural statement.",
    "impact": "Incorrect physical routine metadata; this is a parser classification defect, not an exploitable vulnerability finding.",
    "requiredCorrection": "Classify procedural tokens rather than keyword occurrences in comments or string literals; add a comment-only EXECUTE regression test."
  },
  "resolutionStatus": "resolved-parser-defect",
  "resolution": {
    "previousBlocking": true,
    "field": "hasDynamicSql",
    "before": true,
    "after": false,
    "method": "offline-pglast-PLpgSQL-procedural-tree",
    "verification": "regression-tests-and-full-source-re-review-passed",
    "reportPath": "review/pending/schema-extraction-resolution-report.md"
  }
}
```

## analytics-foundation:physical-schema-review:dynamic-sql-false-positive-grant_repack_privileges

- Category: `parser-defect`
- Blocking: `false`
- Resolution status: `resolved-parser-defect`
- Original observation: "lisa"."grant_repack_privileges" is incorrectly classified as containing dynamic SQL.

```json
{
  "id": "analytics-foundation:physical-schema-review:dynamic-sql-false-positive-grant_repack_privileges",
  "category": "parser-defect",
  "blocking": false,
  "summary": "\"lisa\".\"grant_repack_privileges\" is incorrectly classified as containing dynamic SQL.",
  "affectedRecordIds": [
    "analytics-foundation:schema:functions:%22lisa%22.%22grant_repack_privileges%22%28%29"
  ],
  "sourceRefs": [
    {
      "sourceId": "lanadb-schema",
      "sourcePath": "sources/schema/lanadb-schema.sql",
      "sha256": "032b22660a1902c41242da8824f2ac0cebaa90ccefc3e6915479eb9b1dddd6ad",
      "lineStart": 219,
      "lineEnd": 241,
      "statementType": "CreateFunctionStmt"
    }
  ],
  "details": {
    "field": "hasDynamicSql",
    "actual": true,
    "expected": false,
    "sourceEvidence": "EXECUTE is a privilege keyword in GRANT EXECUTE ON ALL FUNCTIONS, not a procedural dynamic-SQL statement.",
    "impact": "Incorrect physical routine metadata; this is a parser classification defect, not an exploitable vulnerability finding.",
    "requiredCorrection": "Classify procedural tokens rather than keyword occurrences in comments or string literals; add a comment-only EXECUTE regression test."
  },
  "resolutionStatus": "resolved-parser-defect",
  "resolution": {
    "previousBlocking": true,
    "field": "hasDynamicSql",
    "before": true,
    "after": false,
    "method": "offline-pglast-PLpgSQL-procedural-tree",
    "verification": "regression-tests-and-full-source-re-review-passed",
    "reportPath": "review/pending/schema-extraction-resolution-report.md"
  }
}
```

## analytics-foundation:physical-schema-review:dynamic-sql-false-positive-pg_repack_reinstall

- Category: `parser-defect`
- Blocking: `false`
- Resolution status: `resolved-parser-defect`
- Original observation: "lisa"."pg_repack_reinstall" is incorrectly classified as containing dynamic SQL.

```json
{
  "id": "analytics-foundation:physical-schema-review:dynamic-sql-false-positive-pg_repack_reinstall",
  "category": "parser-defect",
  "blocking": false,
  "summary": "\"lisa\".\"pg_repack_reinstall\" is incorrectly classified as containing dynamic SQL.",
  "affectedRecordIds": [
    "analytics-foundation:schema:functions:%22lisa%22.%22pg_repack_reinstall%22%28%29"
  ],
  "sourceRefs": [
    {
      "sourceId": "lanadb-schema",
      "sourcePath": "sources/schema/lanadb-schema.sql",
      "sha256": "032b22660a1902c41242da8824f2ac0cebaa90ccefc3e6915479eb9b1dddd6ad",
      "lineStart": 248,
      "lineEnd": 303,
      "statementType": "CreateFunctionStmt"
    }
  ],
  "details": {
    "field": "hasDynamicSql",
    "actual": true,
    "expected": false,
    "sourceEvidence": "EXECUTE is a privilege keyword in GRANT EXECUTE ON ALL FUNCTIONS, not a procedural dynamic-SQL statement.",
    "impact": "Incorrect physical routine metadata; this is a parser classification defect, not an exploitable vulnerability finding.",
    "requiredCorrection": "Classify procedural tokens rather than keyword occurrences in comments or string literals; add a comment-only EXECUTE regression test."
  },
  "resolutionStatus": "resolved-parser-defect",
  "resolution": {
    "previousBlocking": true,
    "field": "hasDynamicSql",
    "before": true,
    "after": false,
    "method": "offline-pglast-PLpgSQL-procedural-tree",
    "verification": "regression-tests-and-full-source-re-review-passed",
    "reportPath": "review/pending/schema-extraction-resolution-report.md"
  }
}
```

## analytics-foundation:physical-schema-review:unknown-type-exposure_process_job-rework_context

- Category: `unknown-computed-type`
- Blocking: `false`
- Resolution status: `accepted-parser-limitation`
- Original observation: Computed type is explicitly unknown for "lisa"."exposure_process_job"."rework_context".

```json
{
  "id": "analytics-foundation:physical-schema-review:unknown-type-exposure_process_job-rework_context",
  "category": "unknown-computed-type",
  "blocking": false,
  "summary": "Computed type is explicitly unknown for \"lisa\".\"exposure_process_job\".\"rework_context\".",
  "affectedRecordIds": [
    "analytics-foundation:schema:columns:lisa:exposure_process_job:rework_context"
  ],
  "sourceRefs": [
    {
      "sourceId": "lanadb-schema",
      "sourcePath": "sources/schema/lanadb-schema.sql",
      "sha256": "032b22660a1902c41242da8824f2ac0cebaa90ccefc3e6915479eb9b1dddd6ad",
      "lineStart": 2629,
      "lineEnd": 2652,
      "statementType": "ViewStmt"
    }
  ],
  "details": {
    "expressionSql": "CASE WHEN CAST(CAST(reworked AS integer) AS boolean) IS FALSE THEN CAST('NON_REWORKED' AS text) WHEN CAST(CAST(latest_reworked AS integer) AS boolean) IS TRUE THEN CAST('LATEST_REWORKED' AS text) ELSE CAST(NULL AS text) END",
    "typeResolution": "not-resolved-without-server-type-analysis",
    "requiredBeforeRuntime": "Resolve the actual PostgreSQL expression result type before typed runtime use; do not guess."
  },
  "resolutionStatus": "accepted-parser-limitation",
  "runtimePolicy": "type-must-be-resolved-before-typed-runtime-use"
}
```

## analytics-foundation:physical-schema-review:unknown-type-lis_job-application_id

- Category: `unknown-computed-type`
- Blocking: `false`
- Resolution status: `accepted-parser-limitation`
- Original observation: Computed type is explicitly unknown for "lisa"."lis_job"."application_id".

```json
{
  "id": "analytics-foundation:physical-schema-review:unknown-type-lis_job-application_id",
  "category": "unknown-computed-type",
  "blocking": false,
  "summary": "Computed type is explicitly unknown for \"lisa\".\"lis_job\".\"application_id\".",
  "affectedRecordIds": [
    "analytics-foundation:schema:columns:lisa:lis_job:application_id"
  ],
  "sourceRefs": [
    {
      "sourceId": "lanadb-schema",
      "sourcePath": "sources/schema/lanadb-schema.sql",
      "sha256": "032b22660a1902c41242da8824f2ac0cebaa90ccefc3e6915479eb9b1dddd6ad",
      "lineStart": 62714,
      "lineEnd": 62734,
      "statementType": "ViewStmt"
    }
  ],
  "details": {
    "expressionSql": "split_part(CAST(type AS text), CAST('/' AS text), 1)",
    "typeResolution": "not-resolved-without-server-type-analysis",
    "requiredBeforeRuntime": "Resolve the actual PostgreSQL expression result type before typed runtime use; do not guess."
  },
  "resolutionStatus": "accepted-parser-limitation",
  "runtimePolicy": "type-must-be-resolved-before-typed-runtime-use"
}
```

## analytics-foundation:physical-schema-review:unknown-type-lis_job-request_type

- Category: `unknown-computed-type`
- Blocking: `false`
- Resolution status: `accepted-parser-limitation`
- Original observation: Computed type is explicitly unknown for "lisa"."lis_job"."request_type".

```json
{
  "id": "analytics-foundation:physical-schema-review:unknown-type-lis_job-request_type",
  "category": "unknown-computed-type",
  "blocking": false,
  "summary": "Computed type is explicitly unknown for \"lisa\".\"lis_job\".\"request_type\".",
  "affectedRecordIds": [
    "analytics-foundation:schema:columns:lisa:lis_job:request_type"
  ],
  "sourceRefs": [
    {
      "sourceId": "lanadb-schema",
      "sourcePath": "sources/schema/lanadb-schema.sql",
      "sha256": "032b22660a1902c41242da8824f2ac0cebaa90ccefc3e6915479eb9b1dddd6ad",
      "lineStart": 62714,
      "lineEnd": 62734,
      "statementType": "ViewStmt"
    }
  ],
  "details": {
    "expressionSql": "split_part(CAST(type AS text), CAST('/' AS text), 2)",
    "typeResolution": "not-resolved-without-server-type-analysis",
    "requiredBeforeRuntime": "Resolve the actual PostgreSQL expression result type before typed runtime use; do not guess."
  },
  "resolutionStatus": "accepted-parser-limitation",
  "runtimePolicy": "type-must-be-resolved-before-typed-runtime-use"
}
```

## analytics-foundation:physical-schema-review:restricted-runtime-objects

- Category: `restricted-runtime-object`
- Blocking: `false`
- Resolution status: `runtime-restricted`
- Original observation: 148 physical objects are retained but excluded from runtime access.

```json
{
  "id": "analytics-foundation:physical-schema-review:restricted-runtime-objects",
  "category": "restricted-runtime-object",
  "blocking": false,
  "summary": "148 physical objects are retained but excluded from runtime access.",
  "affectedRecordIds": [
    "analytics-foundation:schema:functions:%22lisa%22.%22check_repack_privileges%22%28%29",
    "analytics-foundation:schema:functions:%22lisa%22.%22check_repack_schema_exists%22%28%29",
    "analytics-foundation:schema:functions:%22lisa%22.%22extract_job_moy%22%28%29",
    "analytics-foundation:schema:functions:%22lisa%22.%22extract_lot_step_moy%22%28%29",
    "analytics-foundation:schema:functions:%22lisa%22.%22get_data_directory_disk_usage%22%28%29",
    "analytics-foundation:schema:functions:%22lisa%22.%22get_fragmentation_stat%22%28%29",
    "analytics-foundation:schema:functions:%22lisa%22.%22grant_repack_privileges%22%28%29",
    "analytics-foundation:schema:functions:%22lisa%22.%22pg_repack_reinstall%22%28%29",
    "analytics-foundation:schema:tables:lisa:application_patch",
    "analytics-foundation:schema:tables:lisa:application_patch_switch",
    "analytics-foundation:schema:tables:lisa:config_parameter",
    "analytics-foundation:schema:tables:lisa:data_entitlement_group",
    "analytics-foundation:schema:tables:lisa:data_entitlement_user",
    "analytics-foundation:schema:tables:lisa:data_entitlement_user_group",
    "analytics-foundation:schema:tables:lisa:data_synchronizer_task",
    "analytics-foundation:schema:tables:lisa:databasechangelog",
    "analytics-foundation:schema:tables:lisa:databasechangeloglock",
    "analytics-foundation:schema:tables:lisa:document_audit_stacktrace",
    "analytics-foundation:schema:tables:lisa:document_audit_trail",
    "analytics-foundation:schema:tables:lisa:exposure_machine_table_clean",
    "analytics-foundation:schema:tables:lisa:job_audit_stacktrace",
    "analytics-foundation:schema:tables:lisa:job_audit_trail",
    "analytics-foundation:schema:tables:lisa:job_repair",
    "analytics-foundation:schema:tables:lisa:job_triggers",
    "analytics-foundation:schema:tables:lisa:lis_subscriptions",
    "analytics-foundation:schema:tables:lisa:ols_compartment_rule",
    "analytics-foundation:schema:tables:lisa:ols_default_security_level",
    "analytics-foundation:schema:tables:lisa:ols_group_rule",
    "analytics-foundation:schema:tables:lisa:ols_security_level_rule",
    "analytics-foundation:schema:sequences:lisa:sq_activity_hierarchy_id",
    "analytics-foundation:schema:sequences:lisa:sq_activity_image_id",
    "analytics-foundation:schema:sequences:lisa:sq_activity_task_id",
    "analytics-foundation:schema:sequences:lisa:sq_activity_wafer_id",
    "analytics-foundation:schema:sequences:lisa:sq_appli_patch_switch_id",
    "analytics-foundation:schema:sequences:lisa:sq_application_patch_id",
    "analytics-foundation:schema:sequences:lisa:sq_cd_elements_id",
    "analytics-foundation:schema:sequences:lisa:sq_cd_parameters_id",
    "analytics-foundation:schema:sequences:lisa:sq_config_parameter_id",
    "analytics-foundation:schema:sequences:lisa:sq_ctx_shadow_mode",
    "analytics-foundation:schema:sequences:lisa:sq_data_entitlement_group_id",
    "analytics-foundation:schema:sequences:lisa:sq_data_entitlement_user_id",
    "analytics-foundation:schema:sequences:lisa:sq_data_sync_task_id",
    "analytics-foundation:schema:sequences:lisa:sq_doc_audit_stacktrace_id",
    "analytics-foundation:schema:sequences:lisa:sq_doc_audit_trail_id",
    "analytics-foundation:schema:sequences:lisa:sq_euv_drlaser_id",
    "analytics-foundation:schema:sequences:lisa:sq_euv_dropletgen_table_id",
    "analytics-foundation:schema:sequences:lisa:sq_euv_dropsteer_id",
    "analytics-foundation:schema:sequences:lisa:sq_euv_encontrol_id",
    "analytics-foundation:schema:sequences:lisa:sq_euv_env_ctrl_id",
    "analytics-foundation:schema:sequences:lisa:sq_euv_laser_beam_id",
    "analytics-foundation:schema:sequences:lisa:sq_euv_plasma_gen_id",
    "analytics-foundation:schema:sequences:lisa:sq_euv_pure_collect_id",
    "analytics-foundation:schema:sequences:lisa:sq_euv_seedtable_id",
    "analytics-foundation:schema:sequences:lisa:sq_euv_tin_capture_id",
    "analytics-foundation:schema:sequences:lisa:sq_euv_tin_catch_id",
    "analytics-foundation:schema:sequences:lisa:sq_exp_machine_const_chang_id",
    "analytics-foundation:schema:sequences:lisa:sq_exp_machine_smash_laser_id",
    "analytics-foundation:schema:sequences:lisa:sq_exp_machine_table_clean_id",
    "analytics-foundation:schema:sequences:lisa:sq_fp_job_mapping_id",
    "analytics-foundation:schema:sequences:lisa:sq_fp_lkm_mapping_id",
    "analytics-foundation:schema:sequences:lisa:sq_fp_parameters_id",
    "analytics-foundation:schema:sequences:lisa:sq_ibpq_parameters_id",
    "analytics-foundation:schema:sequences:lisa:sq_icot_foup_id",
    "analytics-foundation:schema:sequences:lisa:sq_idat_readout_documents_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_all_almt_mon_kpis_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_audit_stacktrace_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_audit_trail_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_cdu_est_job_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_cdu_est_job_kpis_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_documents_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_lot_ariamon_ctx_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_lot_chk_ariamon_ctx_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_mtd_est_recipe_set_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_ovl_est_recipe_set_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_report_kpis_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_trg_lot_exp_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_trg_lot_metr_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_triggers_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_tsss_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_wafer_level_grouping_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_wfr_ariamon_ctx_id",
    "analytics-foundation:schema:sequences:lisa:sq_layer_trees_id",
    "analytics-foundation:schema:sequences:lisa:sq_layers_id",
    "analytics-foundation:schema:sequences:lisa:sq_lens_aberr_ctl_lot_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_lens_aberr_imp_mon_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_lot_ebeam_overlay_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_lot_exposure_documents_id",
    "analytics-foundation:schema:sequences:lisa:sq_lot_exposure_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_lot_idat_readout_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_lot_kpi_mapping_id",
    "analytics-foundation:schema:sequences:lisa:sq_lot_metrology_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_lot_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_lot_wms_readout_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_machine_application_id",
    "analytics-foundation:schema:sequences:lisa:sq_machine_commercial_opt_id",
    "analytics-foundation:schema:sequences:lisa:sq_machine_config_item_id",
    "analytics-foundation:schema:sequences:lisa:sq_machine_configuration_id",
    "analytics-foundation:schema:sequences:lisa:sq_machine_event_id",
    "analytics-foundation:schema:sequences:lisa:sq_machine_event_log_id",
    "analytics-foundation:schema:sequences:lisa:sq_machine_part_info_id",
    "analytics-foundation:schema:sequences:lisa:sq_machine_productive_time_id",
    "analytics-foundation:schema:sequences:lisa:sq_machine_state_id",
    "analytics-foundation:schema:sequences:lisa:sq_machine_variant_param_id",
    "analytics-foundation:schema:sequences:lisa:sq_met_machine_hw_kpis_id",
    "analytics-foundation:schema:sequences:lisa:sq_mro_failure_reason_id",
    "analytics-foundation:schema:sequences:lisa:sq_mro_grating_set_id",
    "analytics-foundation:schema:sequences:lisa:sq_mro_grating_set_stats_id",
    "analytics-foundation:schema:sequences:lisa:sq_mro_optimization_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_mro_output_report_id",
    "analytics-foundation:schema:sequences:lisa:sq_mro_pol_selection_id",
    "analytics-foundation:schema:sequences:lisa:sq_mro_profile_id",
    "analytics-foundation:schema:sequences:lisa:sq_mro_profile_statistics_id",
    "analytics-foundation:schema:sequences:lisa:sq_mro_target_id",
    "analytics-foundation:schema:sequences:lisa:sq_mro_wavelength_sel_range_id",
    "analytics-foundation:schema:sequences:lisa:sq_mtd_correction_kpis_id",
    "analytics-foundation:schema:sequences:lisa:sq_ols_compartment_rule_id",
    "analytics-foundation:schema:sequences:lisa:sq_ols_group_rule_id",
    "analytics-foundation:schema:sequences:lisa:sq_ols_security_level_rule_id",
    "analytics-foundation:schema:sequences:lisa:sq_process_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_reticle_inspection_id",
    "analytics-foundation:schema:sequences:lisa:sq_wafer_ebeam_overlay_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_wafer_exposure_doc_id",
    "analytics-foundation:schema:sequences:lisa:sq_wafer_exposure_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_wafer_kpi_mapping_id",
    "analytics-foundation:schema:sequences:lisa:sq_wafer_metrology_docs_id",
    "analytics-foundation:schema:sequences:lisa:sq_wafer_metrology_mtd_fps_id",
    "analytics-foundation:schema:sequences:lisa:sq_wafer_metrology_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_wafer_step_id",
    "analytics-foundation:schema:sequences:lisa:sq_wfr_met_idm_prof_extadv_ctx",
    "analytics-foundation:schema:sequences:lisa:sq_wms_readout_documents_id",
    "analytics-foundation:schema:tables:lisa:sync_bookmarks",
    "analytics-foundation:schema:tables:lisa:unpublished_event",
    "analytics-foundation:schema:sequences:lisa:exposure_document_id_seq",
    "analytics-foundation:schema:sequences:lisa:measure_document_id_seq",
    "analytics-foundation:schema:sequences:lisa:sq_coarse_data_selection_id",
    "analytics-foundation:schema:sequences:lisa:sq_exposure_dataset_partition_id",
    "analytics-foundation:schema:sequences:lisa:sq_exposure_lot_data_record_id",
    "analytics-foundation:schema:sequences:lisa:sq_flow_exec_dsp_id",
    "analytics-foundation:schema:sequences:lisa:sq_flow_execution_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_data_record_id",
    "analytics-foundation:schema:sequences:lisa:sq_job_dataset_partition_id",
    "analytics-foundation:schema:sequences:lisa:sq_measure_dataset_partition_id",
    "analytics-foundation:schema:sequences:lisa:sq_measure_lot_data_record_id",
    "analytics-foundation:schema:sequences:lisa:sq_performance_domain_id",
    "analytics-foundation:schema:sequences:lisa:sq_processing_dr_id",
    "analytics-foundation:schema:sequences:lisa:sq_processing_dsp_id",
    "analytics-foundation:schema:sequences:lisa:sq_table_reference_id"
  ],
  "sourceRefs": [],
  "details": {
    "counts": {
      "functions": 8,
      "tables": 23,
      "sequences": 117
    },
    "scope": "Restrictions retain physical metadata and do not block approval; every record also has runtimeAuthorized=false."
  },
  "resolutionStatus": "runtime-restricted",
  "runtimePolicy": "exclude-from-runtime"
}
```

## analytics-foundation:physical-schema-review:repository-validator

- Category: `repository-improvement`
- Blocking: `false`
- Resolution status: `deferred-repository-improvement`
- Original observation: Repository-level record schemas and an executable knowledge validator are absent.

```json
{
  "id": "analytics-foundation:physical-schema-review:repository-validator",
  "category": "repository-improvement",
  "blocking": false,
  "summary": "Repository-level record schemas and an executable knowledge validator are absent.",
  "affectedRecordIds": [],
  "sourceRefs": [],
  "details": {
    "requiredBeforeRelease": "Supply deterministic repository-level validation before the first immutable platform release."
  },
  "resolutionStatus": "deferred-repository-improvement",
  "targetStage": "before-platform-release"
}
```

## analytics-foundation:physical-schema-review:physical-stage-configuration

- Category: `repository-improvement`
- Blocking: `false`
- Resolution status: `deferred-repository-improvement`
- Original observation: Physical-schema input/output/approval settings are siblings of physicalSchema rather than nested within it.

```json
{
  "id": "analytics-foundation:physical-schema-review:physical-stage-configuration",
  "category": "repository-improvement",
  "blocking": false,
  "summary": "Physical-schema input/output/approval settings are siblings of physicalSchema rather than nested within it.",
  "affectedRecordIds": [],
  "sourceRefs": [],
  "details": {
    "configurationPath": "platform.yaml",
    "actualPaths": [
      "spec.review.stages.inputs",
      "spec.review.stages.outputs",
      "spec.review.stages.approval"
    ],
    "expectedPaths": [
      "spec.review.stages.physicalSchema.inputs",
      "spec.review.stages.physicalSchema.outputs",
      "spec.review.stages.physicalSchema.approval"
    ],
    "reviewPathHandling": "The explicitly listed schema-extraction filenames are used for this review only. No configuration was silently rewritten.",
    "impact": "Stage-specific automation cannot reliably discover its review or approval fields; correct before approval/promotion automation."
  },
  "resolutionStatus": "deferred-repository-improvement",
  "targetStage": "before-platform-release"
}
```
