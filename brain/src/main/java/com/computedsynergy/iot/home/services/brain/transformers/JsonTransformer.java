package com.computedsynergy.iot.home.services.brain.transformers;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;


/**
 *
 * @author Faisal Thaheem
 */

public class JsonTransformer implements ResponseTransformer {

    private final Gson gson;
    
    public JsonTransformer(){
        gson = Converters.registerDateTime(new GsonBuilder()).create();
    }

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
