package com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.repository;

import static com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.repository.util.R2rFilterConstraint.getFilterConstraint;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.asml.sara.feature.ui.monitoring.selector.service.app.common.domain.dataentitlement.ExcludedProducts;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.JobLotOvlOptActKpis;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QExposureMachine;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QJob;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QJobLotOvlOptActKpis;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QLotExposureStep;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QLotKpiMapping;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QLotMetrologyStep;
import com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.domain.R2rActuatorIntrafield;
import com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.domain.R2rActuatorMetric;
import com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.domain.R2rActuatorValues;
import com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.domain.R2rActuatorValuesParams;
import com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.dto.R2rFilterCriteriaRequestDTO;
import com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.repository.util.R2rCommonConstraints;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringExpression;

import io.github.sercasti.tracing.Traceable;
import lombok.extern.slf4j.Slf4j;

/**
 * The repository for performing 'actuator values' related queries.
 */
@Slf4j
@Repository
public class R2rActuatorValuesRepository extends QuerydslRepositorySupport {

    private static final QJob JOB = QJob.job;
    private static final QJobLotOvlOptActKpis JOB_LOT_OVL_OPT_ACT_KPIS = QJobLotOvlOptActKpis.jobLotOvlOptActKpis;
    private static final QLotKpiMapping LOT_KPI_MAPPING = QLotKpiMapping.lotKpiMapping;
    private static final QLotExposureStep LOT_EXPOSURE_STEP = QLotExposureStep.lotExposureStep;
    private static final QLotMetrologyStep LOT_METROLOGY_STEP = QLotMetrologyStep.lotMetrologyStep;
    private static final QExposureMachine EXPOSURE_MACHINE = QExposureMachine.exposureMachine;
    private static final String EXTRACT_EPOCH_MS_TEMPLATE = "extract(epoch from {0}) * 1000";

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance.
     */
    public R2rActuatorValuesRepository() {
        super(JobLotOvlOptActKpis.class);
    }

    /**
     * Retrieve actuator values for the request parameters.
     *
     * @param requestParams
     *            request
     * @param kpi
     *            kpi
     * @param metric
     *            metric
     * @param intrafield
     *            intrafield
     * @param filters
     *            filter criteria
     * @param excludedProducts
     *            excluded products for data entitlement
     * @return stream of the requested actuators
     */
    @Traceable(name = "r2r-db-actuator-values")
    public Stream<R2rActuatorValues> getActuatorValues(R2rActuatorValuesParams requestParams, String kpi, R2rActuatorMetric metric,
            R2rActuatorIntrafield intrafield, R2rFilterCriteriaRequestDTO filters, ExcludedProducts excludedProducts) {
        final ConstructorExpression<R2rActuatorValues> descriptors = Projections.constructor(R2rActuatorValues.class,
                JOB.id.stringValue(),
                JOB.keyJobId,
                lotIdColumn(),
                LOT_EXPOSURE_STEP.productId,
                LOT_EXPOSURE_STEP.keyLayerId,
                Expressions.dateTemplate(Long.class, EXTRACT_EPOCH_MS_TEMPLATE, JOB.finishedTime),
                R2rCommonConstraints.exposureMachineIdWithCustomerName(),
                getKpiColumn(metric, intrafield),
                JOB.id.as("lanaJobId"));

        return from(JOB_LOT_OVL_OPT_ACT_KPIS)
                .innerJoin(JOB).on(JOB_LOT_OVL_OPT_ACT_KPIS.jobId.eq(JOB.id))
                .innerJoin(LOT_KPI_MAPPING).on(LOT_KPI_MAPPING.id.eq(JOB_LOT_OVL_OPT_ACT_KPIS.lotKpiMappingId))
                .innerJoin(LOT_EXPOSURE_STEP).on(LOT_EXPOSURE_STEP.id.eq(LOT_KPI_MAPPING.lotExposureStepId))
                .leftJoin(LOT_METROLOGY_STEP).on(LOT_METROLOGY_STEP.id.eq(LOT_KPI_MAPPING.lotMetrologyStepId))
                .leftJoin(EXPOSURE_MACHINE).on(EXPOSURE_MACHINE.id.eq(LOT_EXPOSURE_STEP.machineId))
                .where(JOB.finishedTime.between(requestParams.getJobFinishedFrom(), requestParams.getJobFinishedTo())
                        .and(JOB_LOT_OVL_OPT_ACT_KPIS.usedActName.eq(kpi))
                        .and(LOT_KPI_MAPPING.aggregationLevel.eq("LOT"))
                        .and(R2rCommonConstraints.getDataEntitlementConstraint(excludedProducts))
                        .and(getFilterConstraint(JOB.status, filters.getJobStatuses()))
                        .and(getFilterConstraint(LOT_EXPOSURE_STEP.productId, filters.getProductIds()))
                        .and(getFilterConstraint(LOT_EXPOSURE_STEP.keyLayerId, filters.getLayerIds()))
                        .and(getFilterConstraint(LOT_EXPOSURE_STEP.reticleId, filters.getReticleIds()))
                        .and(getFilterConstraint(LOT_EXPOSURE_STEP.machineId, filters.getExposureEquipmentIds()))
                        .and(getFilterConstraint(LOT_METROLOGY_STEP.machineId, filters.getMetrologyEquipmentIds()))
                        .and(getFilterConstraint(LOT_METROLOGY_STEP.recipeName, filters.getMetrologyRecipeIds()))
                        .and(getFilterConstraint(JOB.globalModelId, filters.getEstimationGlobalModelIds()))
                        .and(getFilterConstraint(JOB.refinementModelId, filters.getEstimationRefinementModelIds()))
                        .and(getFilterConstraint(JOB.errorCodeId, filters.getErrorCodeIds()))
                        .and(getFilterConstraint(JOB.fpCtxCustom, filters.getCustomContexts()))
                        .and(getFilterConstraint(lotIdColumn(), filters.getLotIds()))
                        .and(getFilterConstraint(JOB.keyJobId, filters.getJobIds()))
                        .and(getFilterConstraint(JOB.instanceId, filters.getJobInstanceIds())))
                .select(descriptors)
                .limit(requestParams.getLimit())
                .stream();
    }

    /**
     * The idea is that the lot id comes from part of the JOB.SUB_RECIPE_NAME (that's why a substring function is used),
     * and it falls back to the exposure lot id in case it is null/empty - which explains the nullif and coalesce functions.
     *
     * ps: although not explicitly documented, this is a customer specific need.
     */
    private static StringExpression lotIdColumn() {
        return R2rCommonConstraints.exposureKeyLotIdForOptimizationJob()
                .nullif("")
                .coalesce(LOT_EXPOSURE_STEP.keyLotId);
    }

    private static NumberPath<BigDecimal> getKpiColumn(R2rActuatorMetric metric, R2rActuatorIntrafield intrafield) {
        if (metric == R2rActuatorMetric.MAX && intrafield == R2rActuatorIntrafield.ABSOLUTE) {
            return JOB_LOT_OVL_OPT_ACT_KPIS.usedActIntraMax;
        } else if (metric == R2rActuatorMetric.RANGE_USED && intrafield == R2rActuatorIntrafield.ABSOLUTE) {
            return JOB_LOT_OVL_OPT_ACT_KPIS.usedActIntraFracPer;
        } else if (metric == R2rActuatorMetric.MAX && intrafield == R2rActuatorIntrafield.RELATIVE) {
            return JOB_LOT_OVL_OPT_ACT_KPIS.usedActRelIntraMax;
        } else if (metric == R2rActuatorMetric.RANGE_USED && intrafield == R2rActuatorIntrafield.RELATIVE) {
            return JOB_LOT_OVL_OPT_ACT_KPIS.usedActRelIntraFracPer;
        } else {
            throw new IllegalArgumentException("Unsupported combination of metric and intrafield: " + metric + ", " + intrafield);
        }
    }
}
