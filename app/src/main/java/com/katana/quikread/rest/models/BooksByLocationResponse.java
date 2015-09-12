package com.katana.quikread.rest.models;

import com.google.gson.annotations.SerializedName;
import com.katana.quikread.models.QuikrItem;

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
    List<QuikrItem> quikritems;

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

}
