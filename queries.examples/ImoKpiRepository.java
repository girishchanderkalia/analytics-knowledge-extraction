package com.asml.sara.feature.ui.monitoring.selector.service.app.imo.repository;

import static com.asml.sara.feature.ui.monitoring.selector.service.app.common.repository.QueryExpressions.productIdsInWithDataEntitlement;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.Job;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QJob;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QLotExposureStep;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QWaferExposureStep;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QWaferKpiMapping;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QWaferScannerFocusMonKpis;
import com.asml.sara.feature.ui.monitoring.selector.service.app.imo.domain.ImoKpiDataRecord;
import com.asml.sara.feature.ui.monitoring.selector.service.app.imo.domain.ImoKpiFilters;
import com.asml.sara.feature.ui.monitoring.selector.service.app.imo.domain.ImoKpiType;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;

import io.github.sercasti.tracing.Traceable;

/**
 * The IMO repository to access the KPI data.
 */
@Repository
public class ImoKpiRepository extends QuerydslRepositorySupport {

    private static final String EXTRACT_MS_EPOCH_TEMPLATE = "extract(epoch from {0}) * 1000";

    private static final QWaferKpiMapping WAFER_KPI_MAP = QWaferKpiMapping.waferKpiMapping;
    private static final QWaferExposureStep WAFER_EXP_STEP = QWaferExposureStep.waferExposureStep;
    private static final QLotExposureStep LOT_EXP_STEP = QLotExposureStep.lotExposureStep;
    private static final QWaferScannerFocusMonKpis WAFER_KPIS = QWaferScannerFocusMonKpis.waferScannerFocusMonKpis;

    /**
     * Constructor to construct ImoKpiRepository.
     */
    public ImoKpiRepository() {
        super(Job.class);
    }

    /**
     * Get streaming access to the data in the IMO KPI tables.
     *
     * @param filters
     *            the context filters to apply
     * @param excludedProductIds
     *            the ids of products to exclude
     * @return the stream of all the KPI data.
     */
    @Traceable(name = "imo-db-filter-criteria")
    public Stream<ImoKpiDataRecord> getFilteredKpiData(ImoKpiFilters filters, List<String> excludedProductIds) {
        final var kpiTypeConstraint = (filters.getKpiType() != null)
                ? WAFER_KPIS.type.eq(filters.getKpiType().name())
                : Expressions.TRUE;

        final var chuckIdConstraint = (filters.getChuckId() != null)
                ? WAFER_EXP_STEP.chuckId.eq(String.valueOf(filters.getChuckId()))
                : Expressions.TRUE;

        var whereClause = LOT_EXP_STEP.lotStart.between(filters.getLotExposureStartFrom(), filters.getLotExposureStartTo())
                .and(QJob.job.type.eq("leveling/monitor"))
                .and(LOT_EXP_STEP.machineId.in(filters.getExposureMachineIds()))
                .and(LOT_EXP_STEP.layerId.in(filters.getLayerIds()))
                .and(productIdsInWithDataEntitlement(filters.getProductIds(), excludedProductIds))
                .and(kpiTypeConstraint)
                .and(chuckIdConstraint);

        if (filters.getCursorId() != null) {
            whereClause = whereClause.and(WAFER_KPIS.id.gt(Long.valueOf(filters.getCursorId())));
        }

        return from(QJob.job)
                .innerJoin(WAFER_KPIS).on(WAFER_KPIS.jobId.eq(QJob.job.id))
                .innerJoin(WAFER_KPI_MAP).on(WAFER_KPI_MAP.id.eq(WAFER_KPIS.id))
                .innerJoin(WAFER_EXP_STEP).on(WAFER_EXP_STEP.id.eq(WAFER_KPI_MAP.waferExposureStepId))
                .innerJoin(LOT_EXP_STEP).on(LOT_EXP_STEP.id.eq(WAFER_EXP_STEP.lotExposureStepId))
                .where(whereClause)
                .select(createProjections())
                .limit(filters.getLimit())
                .orderBy(WAFER_KPIS.id.asc())
                .stream();
    }

    private ConstructorExpression<ImoKpiDataRecord> createProjections() {
        return Projections.constructor(ImoKpiDataRecord.class,
                WAFER_EXP_STEP.chuckId,
                LOT_EXP_STEP.lotId,
                WAFER_EXP_STEP.waferId,
                LOT_EXP_STEP.machineId,
                LOT_EXP_STEP.layerId,
                LOT_EXP_STEP.productId,
                WAFER_KPIS.imageId,
                Expressions.enumPath(ImoKpiType.class, WAFER_KPIS.type.getMetadata()),
                Expressions.dateTemplate(Long.class, EXTRACT_MS_EPOCH_TEMPLATE, WAFER_EXP_STEP.waferStart).as("waferStart"),
                Expressions.dateTemplate(Long.class, EXTRACT_MS_EPOCH_TEMPLATE, LOT_EXP_STEP.lotStart).as("lotStart"),
                WAFER_KPIS.fullDies,
                WAFER_KPIS.partialDies,
                WAFER_KPIS.id.stringValue().as("cursorId"));
    }
}
