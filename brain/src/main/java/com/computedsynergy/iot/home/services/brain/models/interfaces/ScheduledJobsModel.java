package com.computedsynergy.iot.home.services.brain.models.interfaces;

import com.computedsynergy.iot.home.services.brain.models.pojos.ScheduledJob;
import java.util.List;


/**
 *
 * @author Faisal Thaheem
 */

public interface ScheduledJobsModel {

    int addScheduledJob(ScheduledJob job);
    boolean deleteScheduledJob(ScheduledJob job);
    List<ScheduledJob> listJobs();
    ScheduledJob getJob(int jobId);
}
