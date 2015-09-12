package com.katana.quikread.rest.api;

import com.katana.quikread.rest.Keys;
import com.katana.quikread.rest.models.BooksByLocationResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */
public interface QuikrService {

    @Headers({
            "X-Quikr-Signature: "+ Keys.QUIKR_BOOKS_LOCATION_SIGNATURE})
    @GET("/public/adsByCategory")
    void getBooksByLocation(
        @Query("categoryId") String categoryId,
        @Query("size") String size,
        @Query("city") String city,
        @Query("from") String from,
        Callback<BooksByLocationResponse> callback
    );
}
