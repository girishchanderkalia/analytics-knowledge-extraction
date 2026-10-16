package com.asml.sara.feature.ui.monitoring.selector.service.app.imo.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.Job;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QExposureMachine;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QJobLotExposureStep;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QLotExposureStep;
import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QWaferScannerFocusMonKpis;
import com.asml.sara.feature.ui.monitoring.selector.service.app.imo.domain.ImoFilterCriterion;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

import io.github.sercasti.tracing.Traceable;

/**
 * The repository for performing Job related queries.
 */
@Repository
public class ImoJobRepository extends QuerydslRepositorySupport {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance.
     */
    public ImoJobRepository() {
        super(Job.class);
    }

    /**
     * Retrieve all filter criteria.
     *
     * @param lotExposureStartFrom
     *            the start of date-time interval for lot exposure start
     * @param lotExposureStartTo
     *            the end of date-time interval for lot exposure start
     * @param excludedExposureProductIds
     *            the excluded exposure product ids
     * @return list of all filter criteria;
     */
    @Traceable(name = "imo-db-filter-criteria")
    @Transactional(readOnly = true)
    public List<ImoFilterCriterion> getFilterCriteria(final Instant lotExposureStartFrom, final Instant lotExposureStartTo, final List<String> excludedExposureProductIds) {
        QJobLotExposureStep jobLotExposureStep = QJobLotExposureStep.jobLotExposureStep;
        QLotExposureStep lotExposureStep = QLotExposureStep.lotExposureStep;
        QExposureMachine exposureMachine = QExposureMachine.exposureMachine;
        QWaferScannerFocusMonKpis waferScannerFocusMonKpis = QWaferScannerFocusMonKpis.waferScannerFocusMonKpis;

        final BooleanExpression lotExposureStartConstraint = lotExposureStep.lotStart.between(lotExposureStartFrom, lotExposureStartTo);
        final BooleanExpression productIdConstraint = lotExposureStep.productId.notIn(excludedExposureProductIds)
                // see https://stackoverflow.com/questions/45846761/java-jpa-query-is-ignoring-records-where-a-searched-field-is-null
                .or(lotExposureStep.productId.isNull());

        return from(waferScannerFocusMonKpis)
                .innerJoin(jobLotExposureStep).on(jobLotExposureStep.jobId.eq(waferScannerFocusMonKpis.jobId))
                .innerJoin(lotExposureStep).on(lotExposureStep.id.eq(jobLotExposureStep.lotExposureStepId))
                .leftJoin(exposureMachine).on(exposureMachine.id.eq(lotExposureStep.machineId)).fetchJoin()
                .where(lotExposureStartConstraint
                        .and(productIdConstraint))
                .groupBy(
                        lotExposureStep.layerId,
                        lotExposureStep.productId,
                        lotExposureStep.machineId,
                        exposureMachine.customerName)
                .select(Projections.bean(ImoFilterCriterion.class,
                        lotExposureStep.layerId,
                        lotExposureStep.productId,
                        lotExposureStep.machineId.as("exposureMachineId"),
                        exposureMachine.customerName.as("exposureMachineCustomerName"),
                        waferScannerFocusMonKpis.id.count().as("kpiCount")))
                .fetch();
    }
}
