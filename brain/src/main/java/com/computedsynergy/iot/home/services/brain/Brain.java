package com.computedsynergy.iot.home.services.brain;

import com.beust.jcommander.JCommander;
import com.computedsynergy.iot.home.services.brain.models.impl.ScheduledJobsModelImpl;
import com.computedsynergy.iot.home.services.brain.pojo.ResponsePojo;
import com.computedsynergy.iot.home.services.brain.models.pojos.ScheduledJob;
import com.computedsynergy.iot.home.services.brain.pojo.CommandLineOptions;
import com.computedsynergy.iot.home.services.brain.quartz.JobRunner;
import com.computedsynergy.iot.home.services.brain.transformers.JsonTransformer;
import com.google.gson.Gson;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import static spark.Spark.*;


/**
 *
 * @author Faisal Thaheem
 */
public class Brain {
    
    // Grab the Scheduler instance from the Factory
    Scheduler scheduler;
    
    private static Brain myInstance = null;
    private static CommandLineOptions commandLineOptions = new CommandLineOptions();
    private static Logger logger = Logger.getLogger(Brain.class.getName());
    
    private static Brain getInstance(){
        
        if(null == myInstance){
            myInstance = new Brain();
        }
        
        return myInstance;
    }
    
    public Brain(){
        try {
            scheduler  = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException ex) {
            logger.log(Level.SEVERE, "Brain()", ex);
        }
    }
    
    private static void startSavedJobs(){
        
        logger.log(Level.INFO, "Begin starting saved jobs.");
        
        ScheduledJobsModelImpl dbJobs = new ScheduledJobsModelImpl();
        List<ScheduledJob> jobs = dbJobs.listJobs();
        
        for(ScheduledJob job : jobs){
            
            JobDetail jobDetail = JobBuilder.newJob(JobRunner.class)
                    .withIdentity(job.getJobidentifier(), "group1").build();

            jobDetail.getJobDataMap().put("id", job.getId());

            Trigger trigger = TriggerBuilder
                        .newTrigger()
                        .withIdentity(job.getJobidentifier()+ "Trigger", "group1")
                        .withSchedule(
                                CronScheduleBuilder.cronSchedule(job.getCronSchedule())
                        )
                        .build();

            try {            
                getInstance().scheduler.scheduleJob(jobDetail, trigger);
                
                logger.log(Level.INFO, "Started job: " + job.getJobName());
                
            } catch (SchedulerException ex) {
                logger.log(Level.SEVERE, "startSavedJobs", ex);
            }
        }
        
        logger.log(Level.INFO, "End starting saved jobs.");
    }
    
            
    public static void main(String[] args){
        
        //parse command line options to be used across app
        new JCommander(getCommandLineOptions(), args);
        
        startSavedJobs();
        
        get("/listJobs", (req, res) -> {
            
            ScheduledJobsModelImpl dbJobs = new ScheduledJobsModelImpl();
            List<ScheduledJob> jobs = dbJobs.listJobs();
            
            return new ResponsePojo("jobs", jobs);
            
        }, new JsonTransformer());
        
        post("/deleteJob", (req, res) -> {
            
            ResponsePojo response = new ResponsePojo();
            
            ScheduledJob job = new Gson().fromJson(new String(req.body().getBytes(), "UTF-8"), ScheduledJob.class);
            
            ScheduledJobsModelImpl dbJobs = new ScheduledJobsModelImpl();
            boolean successful = dbJobs.deleteScheduledJob(job);
            
            if(successful){
                JobKey key = new JobKey(job.getJobidentifier(),"group1");
                getInstance().scheduler.deleteJob(key);
                
            }else{
                response.setError("true");
            }
            
            return response;
            
        }, new JsonTransformer());
        
        post("/addJob", (req, res) -> {
            
            ResponsePojo response = new ResponsePojo();
            
            ScheduledJob job = new Gson().fromJson(new String(req.body().getBytes(), "UTF-8"), ScheduledJob.class);
            
            ScheduledJobsModelImpl dbJobs = new ScheduledJobsModelImpl();
               
            int createdJobId = dbJobs.addScheduledJob(job);
            if(createdJobId > 0){
                try{
                    JobDetail jobDetail = JobBuilder.newJob(JobRunner.class)
                            .withIdentity(job.getJobidentifier(), "group1").build();

                    jobDetail.getJobDataMap().put("id", createdJobId);

                    Trigger trigger = TriggerBuilder
                                .newTrigger()
                                .withIdentity(job.getJobidentifier()+ "Trigger", "group1")
                                .withSchedule(
                                        CronScheduleBuilder.cronSchedule(job.getCronSchedule())
                                )
                                .build();
                    
                    getInstance().scheduler.scheduleJob(jobDetail, trigger);
                }catch(Exception ex){
                    logger.log(Level.SEVERE, "main.addJob", ex);
                    
                    response.setError("true");
                    response.setMessage(ex.getMessage());
                }
            }else{
                response.setError("true");
            }
        
            return response;
            
        }, new JsonTransformer());
    }

    /**
     * @return the commandLineOptions
     */
    public static CommandLineOptions getCommandLineOptions() {
        return commandLineOptions;
    }
    
    
}
