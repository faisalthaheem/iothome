package com.computedsynergy.iot.home.services.brain.models;

import java.io.InputStreamReader;
import java.io.Reader;
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

    
     public static Sql2o getDBConnection() {
        Sql2o dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = new Sql2o(DB_CONNECTION, DB_USER, DB_PASSWORD);

            
            RunScript.execute(dbConnection.getDataSource().getConnection(), getDDLcontent());
            return dbConnection;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
     
     private static Reader getDDLcontent(){
         
         ClassLoader classLoader = DbUtil.class.getClassLoader();
         
         return new InputStreamReader(classLoader.getResourceAsStream("create-db.sql"));
     }
}
