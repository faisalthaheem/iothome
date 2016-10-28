package com.computedsynergy.iot.home.services.brain.models.impl;

import com.computedsynergy.iot.home.services.brain.models.DbUtil;
import com.computedsynergy.iot.home.services.brain.models.interfaces.ScheduledJobsModel;
import com.computedsynergy.iot.home.services.brain.models.pojos.ScheduledJob;
import java.util.List;
import org.sql2o.Connection;


/**
 *
 * @author Faisal Thaheem
 */
public class ScheduledJobsModelImpl implements ScheduledJobsModel {

    @Override
    public int addScheduledJob(ScheduledJob job) {
        
        String sql = "INSERT INTO JOBSSCHEMA.scheduledjobs(jobName, jobidentifier, cronSchedule, topic, payload) " +
                "VALUES(:jobName,:jobidentifier,:cronSchedule,:topic,:payload)";
        
        int insertedRecordId = -1;
        
        try(Connection con = DbUtil.getDBConnection().open()){
            
            try{
                insertedRecordId = con.createQuery(sql)
                        .addParameter("jobName", job.getJobName())
                        .addParameter("jobidentifier", job.getJobidentifier())
                        .addParameter("cronSchedule", job.getCronSchedule())
                        .addParameter("topic", job.getTopic())
                        .addParameter("payload", job.getPayload())
                        .executeUpdate().getKey(Integer.class);

                System.out.println("Record created successfully. " + insertedRecordId);
                
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        
        return insertedRecordId;
    }

    @Override
    public boolean deleteScheduledJob(ScheduledJob job) {
        
        boolean ret;
        
        String sql = "DELETE FROM JOBSSCHEMA.scheduledjobs where id = " + job.getId();
        try(Connection con = DbUtil.getDBConnection().open()){
            ret = (con.createQuery(sql).executeUpdate().getResult() > 0);
        }
        return ret;
    }

    @Override
    public List<ScheduledJob> listJobs() {
        
        List<ScheduledJob> retJobs;
        
        String sql = "select id, jobName, jobidentifier, cronSchedule, topic, payload from JOBSSCHEMA.scheduledjobs";
        
        try(Connection con = DbUtil.getDBConnection().open()){
            retJobs = con.createQuery(sql).executeAndFetch(ScheduledJob.class);
        }
        
        return retJobs;
    }

    @Override
    public ScheduledJob getJob(int jobId) {
        
        ScheduledJob job;
        
        String sql = "select id, jobName, jobidentifier, cronSchedule, topic, payload from JOBSSCHEMA.scheduledjobs WHERE id = :jobId";
        
        try(Connection con = DbUtil.getDBConnection().open()){
            job = con.createQuery(sql).addParameter("jobId", "" + jobId).executeAndFetchFirst(ScheduledJob.class);
        }
        
        return job;
    }
    
    
}
