package com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.repository;

import java.time.ZoneId;

import com.asml.sara.feature.ui.monitoring.selector.service.app.common.util.DateTimeHelper;
import com.asml.sara.feature.ui.monitoring.selector.service.app.r2r.domain.R2rEstimationKpiRecord;

import lombok.extern.slf4j.Slf4j;

/**
 * Implements some of the logic present in Spotfire, wherein some of the columns
 * are produced by running complex (SQL) expressions.
 * This class implements some of those expressions - the ones that benefit most
 * (in terms of complexity) from being ported to Java.
 * <p>
 * Used to keep all data transformations in one place (in the repository layer),
 * and to keep {@link R2rEstimationKpiRecord} purely as value objects.
 */
@Slf4j
public class R2rEstimationKpiRecordPostProcessor {

    private final R2rEstimationKpiRecord r2rEstimationKpiRecord;
    private final ZoneId timeZone;

    private R2rEstimationKpiRecordPostProcessor(
            final R2rEstimationKpiRecord r2rEstimationKpiRecord,
            final ZoneId timeZone) {
        this.r2rEstimationKpiRecord = r2rEstimationKpiRecord;
        this.timeZone = timeZone;
    }

    void setCustomContextFields() {
        if (r2rEstimationKpiRecord.getFpCtxCustom() == null) {
            return;
        }

        var customContext = r2rEstimationKpiRecord.getFpCtxCustom();
        var parser = new CustomContextParser(customContext);
        r2rEstimationKpiRecord.setCustomContextGlobal1(parser.getItem("FP1"));
        r2rEstimationKpiRecord.setGroupIdGlobal1(parser.getItem("FP1", "WLGC"));
        r2rEstimationKpiRecord.setCustomContextRefinement1(parser.getItem("FP2"));
        r2rEstimationKpiRecord.setGroupIdRefinement1(parser.getItem("FP2", "WLGC"));
    }

    void setFinishedTime() {
        Long epoch = this.r2rEstimationKpiRecord.getFinishedTimeEpoch();
        String jobFinish = DateTimeHelper.formatTimestamp(epoch, this.timeZone);
        this.r2rEstimationKpiRecord.setFinishedTime(jobFinish);
    }

    /**
     * This method is used to post process bean members of {@link R2rEstimationKpiRecord}
     *
     * @param dbRecord
     *            Run to run estimation kpi record bean
     * @param timeZone
     *            time zone for which the timestamps will be formatted
     * @return the post-processed run to run estimation kpi record bean
     */
    public static R2rEstimationKpiRecord updateCalculatedFields(
            R2rEstimationKpiRecord dbRecord,
            ZoneId timeZone) {
        var wrapper = new R2rEstimationKpiRecordPostProcessor(dbRecord, timeZone);
        wrapper.setCustomContextFields();
        wrapper.setFinishedTime();

        return dbRecord;
    }
}
