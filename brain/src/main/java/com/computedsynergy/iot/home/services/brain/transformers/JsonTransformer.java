package com.computedsynergy.iot.home.services.brain.transformers;

import com.google.gson.Gson;
import spark.ResponseTransformer;


/**
 *
 * @author Faisal Thaheem
 */

public class JsonTransformer implements ResponseTransformer {

    private final Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
