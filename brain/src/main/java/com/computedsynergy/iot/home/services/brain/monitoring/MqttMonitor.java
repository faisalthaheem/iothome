package com.computedsynergy.iot.home.services.brain.monitoring;

import com.computedsynergy.iot.home.services.brain.Brain;
import com.computedsynergy.iot.home.services.brain.models.impl.MqttMonitorModelImpl;
import com.computedsynergy.iot.home.services.brain.models.impl.PowerMonModelImpl;
import com.computedsynergy.iot.home.services.brain.models.pojos.MqttMon;
import com.computedsynergy.iot.home.services.brain.models.pojos.PowerMon;
import com.computedsynergy.iot.home.services.brain.pojo.CommandLineOptions;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author Faisal Thaheem
 */
public class MqttMonitor implements Runnable, MqttCallback {

    private static final Logger logger = Logger.getLogger(MqttMonitor.class.getName());
    final static MemoryPersistence persistence = new MemoryPersistence();

    @Override
    public void run() {

        try {
            CommandLineOptions options = Brain.getCommandLineOptions();

            MqttClient mqttClient = new MqttClient(options.getMqBroker(), options.getClientId(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setKeepAliveInterval(30);
            connOpts.setAutomaticReconnect(true);
            connOpts.setCleanSession(true);
            mqttClient.setCallback(this);

            logger.log(Level.INFO, "Connecting to broker: " + options.getMqBroker());
            mqttClient.connect(connOpts);
            logger.log(Level.INFO, "Connected...");

            try {

                mqttClient.subscribe("#");

                while (true) {
                    Thread.sleep(1000);
                }

            } catch (Exception ex) {
                logger.log(Level.SEVERE, "execute", ex);
            }

            mqttClient.disconnect();
            logger.log(Level.INFO, "Disconnected from broker");

        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    @Override
    public void connectionLost(Throwable thrwbl) {
        logger.log(Level.INFO, "Disconnected from broker");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mm) throws Exception {
        logger.log(Level.INFO, "MessageArrived {0} = {1}", new Object[]{topic, new String(mm.getPayload())});
        
        if(topic.equalsIgnoreCase("fromPowerMon")){

            PowerMon reading = new PowerMon();
            reading.setReadat(new Date());
            reading.setReading(ByteBuffer.wrap(mm.getPayload()).getDouble());
            PowerMonModelImpl powerMonModel = new PowerMonModelImpl();
            powerMonModel.addPowerMonReading(reading);
        }else{
            MqttMon mon = new MqttMon();
            mon.setTopicname(topic);
            mon.setPayload(new String(mm.getPayload()));
            mon.setCreated(new Date());
            MqttMonitorModelImpl monModel = new MqttMonitorModelImpl();
            monModel.addEntry(mon);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
        logger.log(Level.INFO, "Delivery completed");
    }

}
