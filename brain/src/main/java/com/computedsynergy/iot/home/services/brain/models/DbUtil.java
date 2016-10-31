package com.computedsynergy.iot.home.services.brain.models;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.h2.tools.RunScript;
import org.sql2o.Sql2o;


/**
 *
 * @author Faisal Thaheem
 */

public class DbUtil {
    
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/iot.home.jobsdb.h2;AUTO_SERVER=TRUE;";
    
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private static Logger logger = Logger.getLogger(DbUtil.class.getName());
    
     public static Sql2o getDBConnection() {
        Sql2o dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            
        } catch (ClassNotFoundException ex) {
            
            logger.log(Level.SEVERE, "getDBConnection", ex);
        }
        try {
            dbConnection = new Sql2o(DB_CONNECTION, DB_USER, DB_PASSWORD);

            
            RunScript.execute(dbConnection.getDataSource().getConnection(), getDDLcontent());
            return dbConnection;
            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "getDBConnection", ex);
        }
        return dbConnection;
    }
     
     private static Reader getDDLcontent(){
         
         ClassLoader classLoader = DbUtil.class.getClassLoader();
         
         return new InputStreamReader(classLoader.getResourceAsStream("create-db.sql"));
     }
}
