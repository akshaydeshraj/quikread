package com.katana.quikread.rest.api;

import com.katana.quikread.rest.models.BookSearchResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */
public interface GoodreadsService {

    @GET("/book/title.xml")
    void searchBookByTitle(
            @Query("title") String title,
            Callback<BookSearchResponse> callback
    );
}
