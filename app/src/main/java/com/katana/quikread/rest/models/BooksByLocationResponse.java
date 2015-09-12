package com.katana.quikread.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */
public class BooksByLocationResponse {

    boolean success;

    int total;

    int timeTaken;

    int responseCode;

    @SerializedName("docs")
    List<Object> quikritems;

    public boolean isSuccess() {
        return success;
    }

    public int getTotal() {
        return total;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public List<Object> getQuikritems() {
        return quikritems;
    }
}
