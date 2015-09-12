package com.katana.quikread.rest.models;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */
public class QuikrResponseDeserializer implements JsonDeserializer<BooksByLocationResponse>{

    @Override
    public BooksByLocationResponse deserialize(JsonElement json, Type typeOfT,
                                               JsonDeserializationContext context) throws JsonParseException {

        JsonElement response = json.getAsJsonObject().get("AdsByCategoryResponse").getAsJsonObject().get("AdsByCategoryData");

        return new Gson().fromJson(response, BooksByLocationResponse.class);
    }
}
