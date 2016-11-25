package com.computedsynergy.iot.home.services.brain.models.impl;

import com.computedsynergy.iot.home.services.brain.models.DbUtil;
import com.computedsynergy.iot.home.services.brain.models.interfaces.PowerMonModel;
import com.computedsynergy.iot.home.services.brain.models.pojos.PowerMon;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sql2o.Connection;

/**
 *
 * @author Faisal Thaheem
 */
public class PowerMonModelImpl implements PowerMonModel {

    private static final Logger logger = Logger.getLogger(PowerMonModelImpl.class.getName());
    
    @Override
    public int addPowerMonReading(PowerMon bean) {
        
        String sql = "INSERT INTO JOBSSCHEMA.powermon(reading, readat) " +
                "VALUES(:reading, :readat)";
        
        int insertedRecordId = -1;
        
        try(Connection con = DbUtil.getDBConnection().open()){
            
            try{
                insertedRecordId = con.createQuery(sql)
                        .addParameter("reading", bean.getReading())
                        .addParameter("readat", bean.getReadat())
                        .executeUpdate().getKey(Integer.class);

                System.out.println("Record created successfully. " + insertedRecordId);
                
            }catch(Exception ex){
                logger.log(Level.SEVERE, "addScheduledJob", ex);
            }
        }
        
        return insertedRecordId;
    }
    
}
