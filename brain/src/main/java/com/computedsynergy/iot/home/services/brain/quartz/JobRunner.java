package com.computedsynergy.iot.home.services.brain.quartz;

import com.computedsynergy.iot.home.services.brain.Brain;
import com.computedsynergy.iot.home.services.brain.models.impl.ScheduledJobsModelImpl;
import com.computedsynergy.iot.home.services.brain.models.pojos.ScheduledJob;
import com.computedsynergy.iot.home.services.brain.pojo.CommandLineOptions;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author Faisal Thaheem
 */
public class JobRunner  implements Job{

    final static Logger logger = Logger.getLogger(JobRunner.class.getName());
    final static MemoryPersistence persistence = new MemoryPersistence();
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        logger.log(Level.INFO, "Start executing job.");
        
        int myJobId = (int) jec.getJobDetail().getJobDataMap().get("id");
        
        ScheduledJobsModelImpl dbJobs = new ScheduledJobsModelImpl();
        
        ScheduledJob job = dbJobs.getJob(myJobId);
        logger.log(Level.INFO, "job identifier : [" + myJobId + "]");

        try{
            CommandLineOptions options = Brain.getCommandLineOptions();
            
            MqttClient mqttClient = new MqttClient(options.getMqBroker(), options.getClientId(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            
            logger.log(Level.INFO, "Connecting to broker: " + options.getMqBroker());
            mqttClient.connect(connOpts);
            logger.log(Level.INFO, "Connected...");
            
            try{

                String payload = job.getPayload();
                MqttMessage message = new MqttMessage(payload.getBytes());
                message.setQos(options.getQos());
                mqttClient.publish(job.getTopic(), message);
                logger.log(Level.INFO, "Message ["+ message +"] published to topic ["+ job.getTopic() +"]");

            }catch(Exception ex){
                logger.log(Level.SEVERE, "execute", ex);
            }
            
            mqttClient.disconnect();
            logger.log(Level.INFO, "Disconnected from broker");
            
            logger.log(Level.INFO, "End executing job.");
            
        }catch(Exception ex){
            logger.log(Level.SEVERE, "execute", ex);
        }
    }
    
}
