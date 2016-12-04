package com.computedsynergy.iot.home.services.brain.models.impl;

import com.computedsynergy.iot.home.services.brain.models.DbUtil;
import com.computedsynergy.iot.home.services.brain.models.interfaces.MqttMonitorModel;
import com.computedsynergy.iot.home.services.brain.models.pojos.MqttMon;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sql2o.Connection;

/**
 *
 * @author Faisal Thaheem
 */
public class MqttMonitorModelImpl implements MqttMonitorModel {

    private static final Logger logger = Logger.getLogger(MqttMonitorModelImpl.class.getName());
    
    @Override
    public int addEntry(MqttMon mon) {
        String sql = "INSERT INTO JOBSSCHEMA.mqttmon(topicname, payload, created) " +
                "VALUES(:topicname, :payload, :created)";
        
        int insertedRecordId = -1;
        
        try(Connection con = DbUtil.getDBConnection().open()){
            
            try{
                insertedRecordId = con.createQuery(sql)
                        .addParameter("topicname", mon.getTopicname())
                        .addParameter("payload", mon.getPayload())
                        .addParameter("created", mon.getCreated())
                        .executeUpdate().getKey(Integer.class);

                System.out.println("Record created successfully. " + insertedRecordId);
                
            }catch(Exception ex){
                logger.log(Level.SEVERE, "addEntry", ex);
            }
        }
        
        return insertedRecordId;
    }

    @Override
    public List<MqttMon> listEntriesForDate(Date dated) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
