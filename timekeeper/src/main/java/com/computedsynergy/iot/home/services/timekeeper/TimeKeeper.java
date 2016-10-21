/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computedsynergy.iot.home.services.timekeeper;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author muhammad.faisal6
 * 
 * The sole purpose of this class is to publish updates to the 
 * time queue every second
 */
public class TimeKeeper {
    
    final static Logger logger = Logger.getLogger(TimeKeeper.class);
    
    
    public static void Main(String[] args){
        do
        {
            
            try{
                
            }catch(Exception ex){
                logger.error(ex);
            }
        
        }while(true);
    }
}
