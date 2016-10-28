package com.computedsynergy.iot.home.services.brain.pojo;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Faisal Thaheem
 */

public class ResponsePojo {

    private Map<String, Object> responseData;
    private String error;
    private String message;

    
    public ResponsePojo() {
        this.responseData = new HashMap<>();
        this.error = "false"; 
    }
    
    public ResponsePojo(String responseKey, Object responseValue) {
        this.responseData = new HashMap<>();
        this.error = "false"; 
        
        responseData.put(responseKey, responseValue);
    };

    /**
     * @return the responseData
     */
    public Map<String, Object> getResponseData() {
        return responseData;
    }

    /**
     * @param responseData the responseData to set
     */
    public void setResponseData(Map<String, Object> responseData) {
        this.responseData = responseData;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

   
}
