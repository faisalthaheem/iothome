package com.computedsynergy.iot.home.services.brain.transformers;


import com.google.gson.Gson;
import java.util.Map;



/**
 *
 * @author Faisal Thaheem
 */
public class jsonUtil {
    public static Map<String, String> parse(String object) {
            return new Gson().fromJson(object, Map.class);
    }
}
