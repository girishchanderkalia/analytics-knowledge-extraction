package com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.repository;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.asml.sara.feature.ui.monitoring.selector.service.app.common.domain.dataentitlement.ExcludedProducts;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.Job;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QExposureMachine;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QJob;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QJobLotExposureStep;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QJobLotMetrologyStep;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QJobLotOvlEstimationKpis;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QJobOvlEstRecipeSettings;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QJobWaferOvlEstimationKpis;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QLotExposureStep;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QLotJobKpis;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QLotKpiMapping;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QLotMetrologyStep;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QMetrologyMachine;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QWaferExposureStep;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QWaferKpiMapping;
import com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.domain.R2rEstimationKpiRecord;
import com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.repository.util.R2rCommonConstraints;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.MathExpressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;

import lombok.extern.slf4j.Slf4j;

/**
 * The repository for performing 'estimation kpi detail table' related queries.
 */
@Repository
@Slf4j
public class R2rEstimationKpiDetailTableRepository extends QuerydslRepositorySupport {

    private static final QJob JOB = QJob.job;
    private static final QJobLotOvlEstimationKpis JOB_LOT_OVL_ESTIMATION_KPIS = QJobLotOvlEstimationKpis.jobLotOvlEstimationKpis;
    private static final QJobWaferOvlEstimationKpis JOB_WAFER_OVL_ESTIMATION_KPIS = QJobWaferOvlEstimationKpis.jobWaferOvlEstimationKpis;
    private static final QLotExposureStep LOT_EXPOSURE_STEP = QLotExposureStep.lotExposureStep;
    private static final QLotKpiMapping LOT_KPI_MAPPING = QLotKpiMapping.lotKpiMapping;
    private static final QLotMetrologyStep LOT_METROLOGY_STEP = QLotMetrologyStep.lotMetrologyStep;
    private static final QWaferExposureStep WAFER_EXPOSURE_STEP = QWaferExposureStep.waferExposureStep;
    private static final QLotJobKpis LOT_JOB_KPIS = QLotJobKpis.lotJobKpis;
    private static final QJobOvlEstRecipeSettings JOB_OVL_EST_RECIPE_SETTINGS = QJobOvlEstRecipeSettings.jobOvlEstRecipeSettings;
    private static final QExposureMachine EXPOSURE_MACHINE = QExposureMachine.exposureMachine;
    private static final QMetrologyMachine METROLOGY_MACHINE = QMetrologyMachine.metrologyMachine;
    private static final QJobLotExposureStep JOB_LOT_EXPOSURE_STEP = QJobLotExposureStep.jobLotExposureStep;
    private static final QJobLotMetrologyStep JOB_LOT_METROLOGY_STEP = QJobLotMetrologyStep.jobLotMetrologyStep;
    private static final QWaferKpiMapping WAFER_KPI_MAPPING = QWaferKpiMapping.waferKpiMapping;

    private static final String JOB_TYPE_OVERLAY_OPTIMIZER_ESTIMATION = "overlayOptimizer/estimation";
    private static final int ROUNDING = 2;
    private static final String DOC_NAME_SIMULATED_OUTPUT_METROLOGY = "Simulated output metrology";
    private static final String LOT_LEVEL_CHUCK_1 = "CHUCK1";
    private static final String LOT_LEVEL_CHUCK_2 = "CHUCK2";
    private static final String WAFER_LEVEL_CHUCK_1 = "Chuck1";
    private static final String WAFER_LEVEL_CHUCK_2 = "Chuck2";
    private static final String RECIPE_TYPE_GLOBAL = "Global";

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance.
     */
    public R2rEstimationKpiDetailTableRepository() {
        super(Job.class);
    }

    /**
     * Retrieve estimation kpi values based on the bean class {@link R2rEstimationKpiRecord}
     *
     * @param jobIds
     *            the list of job id
     * @param excludedProducts
     *            the list of excluded product id
     * @param timeZone
     *            The time zone for which timestamps will be formatted
     * @param limit
     *            the query limit
     * @return the stream of {@link R2rEstimationKpiRecord}
     */
    public Stream<R2rEstimationKpiRecord> getR2rEstimationKpiDetailTable(final List<String> jobIds, final ExcludedProducts excludedProducts, ZoneId timeZone, final Integer limit) {
        // constraint to selected job ids
        var jobIdConstraint = JOB.keyJobId.in(jobIds);

        // constraint to job related fields
        var jobTypeConstraint = JOB.type.eq(JOB_TYPE_OVERLAY_OPTIMIZER_ESTIMATION)
                .and(JOB.estimationJobType.eq(RECIPE_TYPE_GLOBAL))
                .or(JOB.estimationJobType.isNull()
                        .or(JOB.estimationJobType.eq("Refinement")
                                .or(JOB_LOT_OVL_ESTIMATION_KPIS.fpSeqNr.isNull())));

        // additional constraints to prevent abuse of the HTTP endpoint
        var productIdJpqlConstraint = R2rCommonConstraints.getDataEntitlementConstraint(excludedProducts);

        // document name constraint
        var documentNameConstraint = LOT_METROLOGY_STEP.documentName.ne(DOC_NAME_SIMULATED_OUTPUT_METROLOGY)
                .or(LOT_METROLOGY_STEP.documentName.isNull());

        // job ctx requester constraint
        var jobCtxRequesterConstraint = JOB.ctxRequester.notLike("%LIS Analytics%")
                .or(JOB.ctxRequester.isNull());

        // primary metrology constraint
        var primaryMetrologyConstraint = JOB_LOT_METROLOGY_STEP.primaryMetrology.isTrue();

        // lot and wafer level chuck constraint
        var aggregationLevelConstraint = getLotAndWaferLevelChuckConstraint(LOT_LEVEL_CHUCK_1, WAFER_LEVEL_CHUCK_1)
                .or(getLotAndWaferLevelChuckConstraint(LOT_LEVEL_CHUCK_2, WAFER_LEVEL_CHUCK_2));

        // subsample constraint
        var subSampledConstraint = JOB_LOT_OVL_ESTIMATION_KPIS.subSampled
                .eq(JOB_WAFER_OVL_ESTIMATION_KPIS.subSampled)
                .and(JOB_LOT_OVL_ESTIMATION_KPIS.subSampled
                        .eq(LOT_JOB_KPIS.subSampled))
                .or(JOB_LOT_OVL_ESTIMATION_KPIS.subSampled.isNull()
                        .and(JOB_WAFER_OVL_ESTIMATION_KPIS.subSampled.isNull())
                        .and(LOT_JOB_KPIS.subSampled.isNull()));

        // fp_seq_nr constraint
        var fpSeqNrConstraint = JOB_LOT_OVL_ESTIMATION_KPIS.fpSeqNr
                .eq(JOB_WAFER_OVL_ESTIMATION_KPIS.fpSeqNr)
                .and(JOB_LOT_OVL_ESTIMATION_KPIS.fpSeqNr
                        .eq(LOT_JOB_KPIS.fpSeqNr));

        // job_ovl_est_recipe_settings recipe type constraint
        var estRecipeSettingsRecipeTypeConstraint = JOB_OVL_EST_RECIPE_SETTINGS.recipeType.eq(RECIPE_TYPE_GLOBAL)
                .or(JOB_OVL_EST_RECIPE_SETTINGS.sepHealthFilterSettings.isTrue());

        var constraints = jobIdConstraint
                .and(jobTypeConstraint)
                .and(productIdJpqlConstraint)
                .and(documentNameConstraint)
                .and(jobCtxRequesterConstraint)
                .and(primaryMetrologyConstraint)
                .and(aggregationLevelConstraint)
                .and(subSampledConstraint)
                .and(fpSeqNrConstraint)
                .and(estRecipeSettingsRecipeTypeConstraint);

        var queryToReturn = from(JOB)
                .innerJoin(LOT_JOB_KPIS).on(JOB.id.eq(LOT_JOB_KPIS.jobId))
                .innerJoin(JOB_LOT_EXPOSURE_STEP).on(JOB_LOT_EXPOSURE_STEP.jobId.eq(JOB.id))
                .innerJoin(JOB_LOT_METROLOGY_STEP).on(JOB_LOT_METROLOGY_STEP.jobId.eq(JOB.id))
                .innerJoin(LOT_EXPOSURE_STEP).on(JOB_LOT_EXPOSURE_STEP.lotExposureStepId.eq(LOT_EXPOSURE_STEP.id))
                .innerJoin(LOT_METROLOGY_STEP).on(JOB_LOT_METROLOGY_STEP.lotMetrologyStepId.eq(LOT_METROLOGY_STEP.id))
                .innerJoin(JOB_LOT_OVL_ESTIMATION_KPIS).on(JOB.id.eq(JOB_LOT_OVL_ESTIMATION_KPIS.jobId))
                .innerJoin(JOB_WAFER_OVL_ESTIMATION_KPIS).on(JOB.id.eq(JOB_WAFER_OVL_ESTIMATION_KPIS.jobId))
                .innerJoin(JOB_OVL_EST_RECIPE_SETTINGS).on(JOB.id.eq(JOB_OVL_EST_RECIPE_SETTINGS.jobId))
                .innerJoin(LOT_KPI_MAPPING).on(LOT_JOB_KPIS.id.eq(LOT_KPI_MAPPING.id))
                .innerJoin(LOT_KPI_MAPPING).on(JOB_LOT_OVL_ESTIMATION_KPIS.id.eq(LOT_KPI_MAPPING.id))
                .innerJoin(LOT_EXPOSURE_STEP).on(LOT_KPI_MAPPING.lotExposureStepId.eq(LOT_EXPOSURE_STEP.id))
                .innerJoin(WAFER_KPI_MAPPING).on(JOB_WAFER_OVL_ESTIMATION_KPIS.id.eq(WAFER_KPI_MAPPING.id))
                .innerJoin(WAFER_EXPOSURE_STEP).on(WAFER_EXPOSURE_STEP.id.eq(WAFER_KPI_MAPPING.waferExposureStepId))
                .leftJoin(EXPOSURE_MACHINE).on(LOT_EXPOSURE_STEP.machineId.eq(EXPOSURE_MACHINE.id))
                .leftJoin(METROLOGY_MACHINE).on(LOT_METROLOGY_STEP.machineId.eq(METROLOGY_MACHINE.id))
                .where(constraints)
                .distinct()
                .select(getEstimationKpisDetailProjection());

        if (limit != null) {
            queryToReturn = queryToReturn.limit(limit);
        }
        return queryToReturn.stream()
                .map(record -> R2rEstimationKpiRecordPostProcessor.updateCalculatedFields(record, timeZone));
    }

    private static QBean<R2rEstimationKpiRecord> getEstimationKpisDetailProjection() {
        // This is the formatting currently applied in Spotfire.
        // Doing the formatting in the db because otherwise we would need to move from using Instant to using
        // a type that preserves the time zone information. That would be a bit disruptive since job.finishedTime
        // is being used elsewhere with other fields mapped to Instant.
        var job_jobFinish_epoch = Expressions.dslTemplate(
                Long.class, "extract(epoch from {0})",
                JOB.finishedTime);

        var lotJobKpis_invalidPercentage = Expressions.numberTemplate(
                BigDecimal.class,
                "100 - {0}", JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdPercentage);

        var jobLotOvlEstimationKpis_fingerPrint = Expressions.stringTemplate(
                "CONCAT('FP', COALESCE({0}, ''), COALESCE({1}, ''))",
                JOB_LOT_OVL_ESTIMATION_KPIS.fpSeqNr,
                JOB_LOT_OVL_ESTIMATION_KPIS.subSampled);

        var jobWaferOvlEstimationKpis_job_modeledResidualM3sX = calculateModeledResidualM3s(
                JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdMaMrM3sX,
                JOB_WAFER_OVL_ESTIMATION_KPIS.kpiMaM3sX);

        var jobWaferOvlEstimationKpis_job_modeledResidualM3sY = calculateModeledResidualM3s(
                JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdMaMrM3sY,
                JOB_WAFER_OVL_ESTIMATION_KPIS.kpiMaM3sY);

        Function<NumberExpression<BigDecimal>, NumberExpression<Double>> roundKpi = number -> MathExpressions.round(number, ROUNDING).doubleValue();

        return Projections.bean(R2rEstimationKpiRecord.class,
                job_jobFinish_epoch.as("finishedTimeEpoch"),
                JOB.fpUpdated,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statDwsrMinY).as("statDwsrMinY"),
                LOT_METROLOGY_STEP.keyLotId,
                JOB.fpStartNewTimeFilter.as("fpStartNewTimeFilterSequence"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMrMaxX).as("statMrMaxX"),
                JOB.status,
                LOT_JOB_KPIS.waferIdsExpGroup,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMrMaxY).as("statMrMaxY"),
                JOB.keyJobId,
                LOT_JOB_KPIS.waferIdsExpGroupCount,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMrMinX).as("statMrMinX"),
                LOT_EXPOSURE_STEP.productId,
                JOB.globalModelId.as("modelAppliedGl"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMrMinY).as("statMrMinY"),
                LOT_EXPOSURE_STEP.keyLayerId,
                JOB.refinementModelId.as("modelAppliedRf"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMrPointsInvOutlierX).as("statMrPointsInvOutlierX"),
                JOB.estimationRecipeGl,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMrPointsInvOutlierY).as("statMrPointsInvOutlierY"),
                JOB.estimationRecipeRf,
                roundKpi.apply(jobWaferOvlEstimationKpis_job_modeledResidualM3sX).as("modeledResidualM3sX"),
                LOT_JOB_KPIS.fpIdsGlobalCreated,
                roundKpi.apply(jobWaferOvlEstimationKpis_job_modeledResidualM3sY).as("modeledResidualM3sY"),
                LOT_JOB_KPIS.fpIdsRefinementCreated,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprNrOfFieldsX,
                LOT_JOB_KPIS.fpGlobalAveraged,
                LOT_JOB_KPIS.fpIdsGlobalUsed,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprNrOfFieldsY,
                LOT_JOB_KPIS.fpRefinementAveraged,
                LOT_JOB_KPIS.fpIdsRefinementUsed,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobM3sX).as("kpiMprOobM3sX"),
                LOT_JOB_KPIS.customContext.as("fpCtxCustom"),
                roundKpi.apply(lotJobKpis_invalidPercentage).as("invalidPercentWaferLevel"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobM3sY).as("kpiMprOobM3sY"),
                WAFER_EXPOSURE_STEP.keyWaferId,
                JOB.message,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobMaxX).as("kpiMprOobMaxX"),
                WAFER_EXPOSURE_STEP.chuckId,
                JOB.fpType,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobMaxLimitX).as("kpiMprOobMaxLimitX"),
                jobLotOvlEstimationKpis_fingerPrint.as("fingerPrint"),
                JOB.instanceId,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobMaxY).as("kpiMprOobMaxY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdM3sX).as("kpiFdM3sX"),
                JOB.subsampledMetDocId.as("subSampledMetDocId"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobMaxLimitY).as("kpiMprOobMaxLimitY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdM3sLimitX).as("kpiFdM3sLimitX"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMdM3sX).as("statMdM3sX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobNrOfFieldsX,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdM3sY).as("kpiFdM3sY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMdM3sY).as("statMdM3sY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobNrOfFieldsLY,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdM3sLimitY).as("kpiFdM3sLimitY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMdMaxX).as("statMdMaxX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobNrOfFieldsY,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdMaMrM3sX).as("kpiFdMaMrM3sX"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMdMaxY).as("statMdMaxY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobNrOfFieldsLX,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdMaMrM3sLimitX).as("kpiFdMaMrM3sLimitX"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMdMinX).as("statMdMinX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprNrOfFieldsX,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdMaMrM3sY).as("kpiFdMaMrM3sY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMdMinY).as("statMdMinY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprNrOfFieldsY,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdMaMrM3sLimitY).as("kpiFdMaMrM3sLimitY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiMdPercentageX).as("kpiMdPercentageX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobM3sX).as("kpiInnrMprOobM3sX"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiMaM3sX).as("kpiMaM3sX"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiMdPercentageY).as("kpiMdPercentageY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobM3sY).as("kpiInnrMprOobM3sY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiMaM3sLimitX).as("kpiMaM3sLimitX"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMdPointsInvMetrX).as("statMdPointsInvMetrX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobMaxX).as("kpiInnrMprOobMaxX"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiMaM3sY).as("kpiMaM3sY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMdPointsInvMetrY).as("statMdPointsInvMetrY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobMaxLimitX).as("kpiInnrMprOobMaxLimitX"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiMaM3sLimitY).as("kpiMaM3sLimitY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMdPointsTotalX).as("statMdPointsTotalX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobMaxY).as("kpiInnrMprOobMaxY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMHalfrangeX).as("halfRangePerChuckM3sX"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statMdPointsTotalY).as("statMdPointsTotalY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobMaxLimitY).as("kpiInnrMprOobMaxLimitY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMHalfRangeLimitX).as("kpiMHalfRangeLimitX"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statFdMaxX).as("statFdMaxX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobNrFieldsX,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMHalfrangeY).as("halfRangePerChuckM3sY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statFdMaxY).as("statFdMaxY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobNrFieldsLX,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMHalfRangeLimitY).as("kpiMHalfRangeLimitY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statFdMinX).as("statFdMinX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobNrFieldsY,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiFuXM3sLimit).as("kpiFuXM3sLimit"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statFdMinY).as("statFdMinY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobNrFieldsLY,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiFuXM3s).as("fpUpdatePerChuckM3sX"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdPercentageX).as("kpiFdPercentageX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMprOobM3sX).as("kpiEdgeMprOobM3sX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiFuYM3sLimit).as("kpiFuYM3sLimit"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdPercentageLimitX).as("kpiFdPercentageLimitX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMprOobM3sY).as("kpiEdgeMprOobM3sY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiFuYM3s).as("fpUpdatePerChuckM3sY"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdPercentageY).as("kpiFdPercentageY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMprOobMaxX).as("kpiEdgeMprOobMaxX"),
                EXPOSURE_MACHINE.customerName.as("exposureCustomerName"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.kpiFdPercentageLimitY).as("kpiFdPercentageLimitY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMprOobMaxLimitX).as("kpiEdgeMprOobMaxLimitX"),
                METROLOGY_MACHINE.customername.as("metrologyCustomerName"),
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statFdPointsInvHealthX).as("statFdPointsInvHealthX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMprOobMaxY).as("kpiEdgeMprOobMaxY"),
                LOT_METROLOGY_STEP.recipeName,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statFdPointsInvHealthY).as("statFdPointsInvHealthY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMprOobMaxLimitY).as("kpiEdgeMprOobMaxLimitY"),
                LOT_JOB_KPIS.metWaferIds,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statDwsrM3sX).as("statDwsrM3sX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMprOobNrFieldsX,
                LOT_JOB_KPIS.secMetCustMachineId,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statDwsrM3sY).as("statDwsrM3sY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMprOobNrFieldsLX,
                LOT_JOB_KPIS.secMetRecipe,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statDwsrMaxX).as("statDwsrMaxX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMprOobNrFieldsY,
                LOT_JOB_KPIS.secMetWaferIds,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statDwsrMaxY).as("statDwsrMaxY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMprOobNrFieldsLY,
                LOT_EXPOSURE_STEP.reticleId,
                roundKpi.apply(JOB_WAFER_OVL_ESTIMATION_KPIS.statDwsrMinX).as("statDwsrMinX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrNmuMaxX).as("statInnrNmuMaxX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrNmuMaxY).as("statInnrNmuMaxY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMaMrValueOfNY).as("kpiInnrMaMrValueOfNY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrMeanX).as("statInnrFdMaMrMeanX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMaMuMaxX).as("kpiEdgeMaMuMaxX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMdMaxNPX).as("kpiInnrMdMaxNPX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrMeanY).as("statInnrFdMaMrMeanY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMaMuMaxY).as("kpiEdgeMaMuMaxY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMdMaxNPY).as("kpiInnrMdMaxNPY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrMinX).as("statInnrFdMaMrMinX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statNmuMaxX).as("statNmuMaxX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMdNrMeasOosX,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrMinY).as("statInnrFdMaMrMinY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statNmuMaxY).as("statNmuMaxY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMdNrMeasOosY,
                JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrPioX,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMaMuMax).as("kpiEdgeMaMuMax"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrM3sX).as("statEdgeFdMaMrM3sX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrPioY,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statNmuMaxX).as("nmuPerChuckMaxX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrM3sY).as("statEdgeFdMaMrM3sY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrSigmaX).as("statInnrFdMaMrSigmaX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMaMuMaxLimitX).as("kpiMaMuMaxLimitX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrMaxX).as("statEdgeFdMaMrMaxX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrSigmaY).as("statInnrFdMaMrSigmaY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statNmuMaxY).as("nmuPerChuckMaxY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrMaxY).as("statEdgeFdMaMrMaxY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcM3sX).as("statInnrPpFcM3sX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMaMuMaxLimitY).as("kpiMaMuMaxLimitY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrMeanX).as("statEdgeFdMaMrMeanX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcM3sY).as("statInnrPpFcM3sY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeFcM3sX).as("kpiEdgeFcM3sX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrMeanY).as("statEdgeFdMaMrMeanY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcMaxX).as("statInnrPpFcMaxX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeFcM3sY).as("kpiEdgeFcM3sY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrMinX).as("statEdgeFdMaMrMinX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcMaxY).as("statInnrPpFcMaxY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeFcMeanX).as("kpiEdgeFcMeanX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrMinY).as("statEdgeFdMaMrMinY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcMeanX).as("statInnrPpFcMeanX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeFcMeanY).as("kpiEdgeFcMeanY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrPioX,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcMeanY).as("statInnrPpFcMeanY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeFcSigmaX).as("kpiEdgeFcSigmaX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrPioY,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcMinX).as("statInnrPpFcMinX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeFcSigmaY).as("kpiEdgeFcSigmaY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrSigmaX).as("statEdgeFdMaMrSigmaX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcMinY).as("statInnrPpFcMinY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeFdMaxNPX).as("kpiEdgeFdMaxNPX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgeFdMaMrSigmaY).as("statEdgeFdMaMrSigmaY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcPegX,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeFdMaxNPY).as("kpiEdgeFdMaxNPY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcM3sX).as("statEdgePpFcM3sX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcPegY,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeFdNrMeasOosX,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcM3sY).as("statEdgePpFcM3sY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcSigmaX).as("statInnrPpFcSigmaX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeFdNrMeasOosY,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcMaxX).as("statEdgePpFcMaxX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFcSigmaY).as("statInnrPpFcSigmaY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMaMrMaxNPX).as("kpiEdgeMaMrMaxNPX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcMaxY).as("statEdgePpFcMaxY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuM3sX).as("statInnrPpFuM3sX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMaMrMaxNPY).as("kpiEdgeMaMrMaxNPY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcMeanX).as("statEdgePpFcMeanX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuM3sY).as("statInnrPpFuM3sY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMaMrValueOfNX).as("kpiEdgeMaMrValueOfNX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcMeanY).as("statEdgePpFcMeanY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuMaxX).as("statInnrPpFuMaxX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMaMrValueOfNY).as("kpiEdgeMaMrValueOfNY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcMinX).as("statEdgePpFcMinX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuMaxY).as("statInnrPpFuMaxY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMdMaxNPX).as("kpiEdgeMdMaxNPX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcMinY).as("statEdgePpFcMinY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuMeanX).as("statInnrPpFuMeanX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMdMaxNPY).as("kpiEdgeMdMaxNPY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcPegX,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuMeanY).as("statInnrPpFuMeanY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMdNrMeasOosX,
                JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcPegY,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuMinX).as("statInnrPpFuMinX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeMdNrMeasOosY,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcSigmaX).as("statEdgePpFcSigmaX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuMinY).as("statInnrPpFuMinY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrFcM3sX).as("kpiInnrFcM3sX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFcSigmaY).as("statEdgePpFcSigmaY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuPegX,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrFcM3sY).as("kpiInnrFcM3sY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuM3sX).as("statEdgePpFuM3sX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuPegY,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrFcMeanX).as("kpiInnrFcMeanX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuM3sY).as("statEdgePpFuM3sY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuSigmaX).as("statInnrPpFuSigmaX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrFcMeanY).as("kpiInnrFcMeanY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuMaxX).as("statEdgePpFuMaxX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrPpFuSigmaY).as("statInnrPpFuSigmaY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrFcSigmaX).as("kpiInnrFcSigmaX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuMaxY).as("statEdgePpFuMaxY"),
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.residualOutlierRmNsigma).as("residualOutlierRmNsigma"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrFcSigmaY).as("kpiInnrFcSigmaY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuMeanX).as("statEdgePpFuMeanX"),
                JOB_OVL_EST_RECIPE_SETTINGS.useMeasuredOvl,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiFcM3sX).as("fpChangePerChuckM3sX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuMeanY).as("statEdgePpFuMeanY"),
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.healthFilterEdgeClearance).as("healthFilterEdgeClearance"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuMinX).as("statEdgePpFuMinX"),
                JOB_OVL_EST_RECIPE_SETTINGS.healthFilterGranularity.as("healthFilterGranularity"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiFcM3sY).as("fpChangePerChuckM3sY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuMinY).as("statEdgePpFuMinY"),
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.healthFilterMax).as("healthFilterMax"),
                JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuPegX,
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.healthFilterNsigma).as("healthFilterNsigma"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrFdMaxNPX).as("kpiInnrFdMaxNPX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuPegY,
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.healthFilterXMax).as("healthFilterXMax"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrFdMaxNPY).as("kpiInnrFdMaxNPY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuSigmaX).as("statEdgePpFuSigmaX"),
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.healthFilterXNsigma).as("healthFilterXNsigma"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrFdNrMeasOosX,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statEdgePpFuSigmaY).as("statEdgePpFuSigmaY"),
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.healthFilterYMax).as("healthFilterYMax"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrFdNrMeasOosY,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrM3sX).as("statInnrFdMaMrM3sX"),
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.healthFilterYNsigma).as("healthFilterYNsigma"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMaMrMaxNPX).as("kpiInnrMaMrMaxNPX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrM3sY).as("statInnrFdMaMrM3sY"),
                JOB_OVL_EST_RECIPE_SETTINGS.layoutParamReduct,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMaMrMaxNPY).as("kpiInnrMaMrMaxNPY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrMaxX).as("statInnrFdMaMrMaxX"),
                JOB_OVL_EST_RECIPE_SETTINGS.modelParamReduct,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMaMrValueOfNX).as("kpiInnrMaMrValueOfNX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statInnrFdMaMrMaxY).as("statInnrFdMaMrMaxY"),
                JOB_OVL_EST_RECIPE_SETTINGS.modelUncertaintyParamReduct,
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.innfXMaxModelUncertainty).as("innfXMaxModelUncertainty"),
                JOB_OVL_EST_RECIPE_SETTINGS.sfldfpupEdgeTargetCategory,
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.innfYMaxModelUncertainty).as("innfYMaxModelUncertainty"),
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.sfldfpupEdgeThrModelUncert).as("sfldfpupEdgeThrModelUncert"),
                JOB_OVL_EST_RECIPE_SETTINGS.kpiGlEdgeFieldThreshold,
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.sfldfpupEdgeThrRtioValidpt).as("sfldfpupEdgeThrRtioValidpt"),
                JOB_OVL_EST_RECIPE_SETTINGS.kpiGlInnerFieldThreshold,
                JOB_OVL_EST_RECIPE_SETTINGS.sfldfpupInnfTargetCategory,
                JOB_OVL_EST_RECIPE_SETTINGS.kpiXEdgeFieldThreshold,
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.sfldfpupInnfThrModelUncert).as("sfldfpupInnfThrModelUncert"),
                JOB_OVL_EST_RECIPE_SETTINGS.kpiXInnerFieldThreshold,
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.sfldfpupInnfThrOvlImpact).as("sfldfpupInnfThrOvlImpact"),
                JOB_OVL_EST_RECIPE_SETTINGS.kpiYEdgeFieldThreshold,
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.sfldfpupInnfThrRtioValidpt).as("sfldfpupInnfThrRtioValidpt"),
                JOB_OVL_EST_RECIPE_SETTINGS.kpiYInnerFieldThreshold,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeSfldfpuHpfpNrBdf,
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.modelXMaxModelUncertainty).as("modelXMaxModelUncertainty"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeSfldfpuHpfpNrBdfL,
                roundKpi.apply(JOB_OVL_EST_RECIPE_SETTINGS.modelYMaxModelUncertainty).as("modelYMaxModelUncertainty"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeSfldfpuHpfpNrUaf,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobOimMaxX).as("kpiInnrMprOobOimMaxX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeSfldfpuHpfpNrUafL,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobOimMaxY).as("kpiInnrMprOobOimMaxY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeSfldfpuNpfpNrBdf,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobOimMax).as("kpiMprOobOimMax"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeSfldfpuNpfpNrBdfL,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobOimMaxLmtX).as("kpiMprOobOimMaxLmtX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeSfldfpuNpfpNrUaf,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobOimMaxLmtY).as("kpiMprOobOimMaxLmtY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiEdgeSfldfpuNpfpNrUafL,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobOimMaxLmt).as("kpiMprOobOimMaxLmt"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrSfldfpuHpfpNrBdf,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobOimClcstidX,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrSfldfpuHpfpNrBdfL,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobOimClcstidY,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrSfldfpuHpfpNrUaf,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiMprOobOimClcstid,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrSfldfpuHpfpNrUafL,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statMprOvimpAvgX).as("statMprOvimpAvgX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrSfldfpuNpfpNrBdf,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statMprOvimpAvgY).as("statMprOvimpAvgY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrSfldfpuNpfpNrBdfL,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobOimMax).as("kpiInnrMprOobOimMax"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrSfldfpuNpfpNrUaf,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobOimMaxLmtX).as("kpiInnrMprOobOimMaxLmtX"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrSfldfpuNpfpNrUafL,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobOimMaxLmtY).as("kpiInnrMprOobOimMaxLmtY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobOimMaxLmt).as("kpiInnrMprOobOimMaxLmt"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobOimClcstidX,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobOimClcstidY,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrMprOobOimClcstid,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiFcSigmaLimitX).as("kpiFcSigmaLimitX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.kpiFcSigmaLimitY).as("kpiFcSigmaLimitY"),
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrNrUnhlInnrFld,
                JOB_LOT_OVL_ESTIMATION_KPIS.kpiInnrNrUnhlInnrFldL,
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statMprOvimpMaxyFldPosX).as("statMprOvimpMaxyFldPosX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statMprOvimpMaxyFldPosY).as("statMprOvimpMaxyFldPosY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statMprOvimpVdfPctX).as("statMprOvimpVdfPctX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statMprOvimpVdfPctY).as("statMprOvimpVdfPctY"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statMprOvimpMaxxFldPosX).as("statMprOvimpMaxxFldPosX"),
                roundKpi.apply(JOB_LOT_OVL_ESTIMATION_KPIS.statMprOvimpMaxxFldPosY).as("statMprOvimpMaxxFldPosY"),
                JOB_OVL_EST_RECIPE_SETTINGS.sepHealthFilterSettings);
    }

    private static NumberExpression<BigDecimal> calculateModeledResidualM3s(NumberPath<BigDecimal> numberPath1, NumberPath<BigDecimal> numberPath2) {
        return Expressions.cases()
                .when(numberPath1.isNotNull()
                        .and(JOB.estimationJobType.eq(RECIPE_TYPE_GLOBAL)))
                .then(numberPath1)
                .otherwise(numberPath2);
    }

    private static BooleanExpression getLotAndWaferLevelChuckConstraint(String lotChuckId, String waferChuckId) {
        return LOT_KPI_MAPPING.aggregationLevel.eq(lotChuckId)
                .and(WAFER_EXPOSURE_STEP.chuckId.eq(waferChuckId));
    }
}
