package com.computedsynergy.iot.home.services.brain.models.pojos;


/**
 *
 * @author Faisal Thaheem
 */

public class ScheduledJob{
    
    private int id;
    private String jobName;
    private String cronSchedule;
    private String topic;
    private String payload;
    private String jobidentifier;


    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @return the payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     * @return the jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * @param jobName the jobName to set
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * @return the cronSchedule
     */
    public String getCronSchedule() {
        return cronSchedule;
    }

    /**
     * @param cronSchedule the cronSchedule to set
     */
    public void setCronSchedule(String cronSchedule) {
        this.cronSchedule = cronSchedule;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the jobidentifier
     */
    public String getJobidentifier() {
        return jobidentifier;
    }

    /**
     * @param jobidentifier the jobidentifier to set
     */
    public void setJobidentifier(String jobidentifier) {
        this.jobidentifier = jobidentifier;
    }
    
    
}
