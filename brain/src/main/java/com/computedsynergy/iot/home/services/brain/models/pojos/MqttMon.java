/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computedsynergy.iot.home.services.brain.models.pojos;

import org.joda.time.DateTime;

/**
 *
 * @author Faisal Thaheem
 */

public class MqttMon {
    
    private int id;
    private String topicname;
    private String payload;
    private DateTime created;

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
     * @return the topicname
     */
    public String getTopicname() {
        return topicname;
    }

    /**
     * @param topicname the topicname to set
     */
    public void setTopicname(String topicname) {
        this.topicname = topicname;
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
     * @return the created
     */
    public DateTime getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(DateTime created) {
        this.created = created;
    }
    
    
}
