package com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.repository;

import static com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.QJobLotOvlOptActKpis.jobLotOvlOptActKpis;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.asml.sara.feature.ui.monitoring.selector.service.app.common.lana.entity.JobLotOvlOptActKpis;
import com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.domain.R2rActuatorKpi;
import com.querydsl.core.types.Projections;

import io.github.sercasti.tracing.Traceable;
import lombok.extern.slf4j.Slf4j;

/**
 * The repository for performing 'actuator kpi' related queries.
 */
@Slf4j
@Repository
public class R2rActuatorKpisRepository extends QuerydslRepositorySupport {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance.
     */
    public R2rActuatorKpisRepository() {
        super(JobLotOvlOptActKpis.class);
    }

    /**
     * Retrieve available actuators.
     *
     * @return stream of the requested actuators
     */
    @Traceable(name = "r2r-db-actuator-kpis")
    public List<R2rActuatorKpi> getActuatorKpis() {
        var projection = Projections.constructor(R2rActuatorKpi.class, jobLotOvlOptActKpis.usedActName.stringValue());

        return from(jobLotOvlOptActKpis)
                .select(projection)
                .distinct()
                .orderBy(jobLotOvlOptActKpis.usedActName.asc())
                .fetch();
    }
}
