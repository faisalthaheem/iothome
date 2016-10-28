package com.computedsynergy.iot.home.services.brain.quartz;

import com.computedsynergy.iot.home.services.brain.Brain;
import com.computedsynergy.iot.home.services.brain.models.impl.ScheduledJobsModelImpl;
import com.computedsynergy.iot.home.services.brain.models.pojos.ScheduledJob;
import com.computedsynergy.iot.home.services.brain.pojo.CommandLineOptions;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author Faisal Thaheem
 */
public class JobRunner  implements Job{

    final static Logger logger = Logger.getLogger(JobRunner.class);
    final static MemoryPersistence persistence = new MemoryPersistence();
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        int myJobId = (int) jec.getJobDetail().getJobDataMap().get("id");
        
        ScheduledJobsModelImpl dbJobs = new ScheduledJobsModelImpl();
        
        ScheduledJob job = dbJobs.getJob(myJobId);
        System.out.println("job run [" + myJobId + "] name: " + job.getJobName());
        
        try{
            CommandLineOptions options = Brain.getCommandLineOptions();
            
            MqttClient mqttClient = new MqttClient(options.getMqBroker(), options.getClientId(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            
            System.out.println("Connecting to broker: " + options.getMqBroker());
            mqttClient.connect(connOpts);
            System.out.println("Connected");
            
            try{

                String payload = job.getPayload();
                MqttMessage message = new MqttMessage(payload.getBytes());
                message.setQos(options.getQos());
                mqttClient.publish(job.getTopic(), message);
                System.out.println("Message published");

            }catch(Exception ex){
                logger.error(ex);
            }
            
            mqttClient.disconnect();
            System.out.println("Disconnected");
            
        }catch(Exception ex){
            logger.error(ex);
        }
    }
    
}
