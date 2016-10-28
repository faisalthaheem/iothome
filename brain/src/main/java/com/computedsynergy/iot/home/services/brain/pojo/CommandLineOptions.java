package com.computedsynergy.iot.home.services.brain.pojo;

import com.beust.jcommander.Parameter;
import java.util.UUID;



/**
 *
 * @author Faisal Thaheem
 */
public class CommandLineOptions {
    
    @Parameter(names = "--mqtt-broker", description = "MQTT Broker URL")
    private String mqBroker = "tcp://iot.eclipse.org:1883";
    
    @Parameter(names = "--mqtt-qos", description = "MQTT QoS")
    private int qos = 2;

    @Parameter(names = "--mqtt-client", description = "Client Id")
    private String clientId = UUID.randomUUID().toString();

    /**
     * @return the mqBroker
     */
    public String getMqBroker() {
        return mqBroker;
    }

    /**
     * @param mqBroker the mqBroker to set
     */
    public void setMqBroker(String mqBroker) {
        this.mqBroker = mqBroker;
    }

    /**
     * @return the qos
     */
    public int getQos() {
        return qos;
    }

    /**
     * @param qos the qos to set
     */
    public void setQos(int qos) {
        this.qos = qos;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    
}
