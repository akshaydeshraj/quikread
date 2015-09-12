package com.katana.quikread.rest;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */
public interface OnRequestFinishedListener {

    void onSuccess(Object response, Response retrofitResponse);

    void onError(RetrofitError error);
}
