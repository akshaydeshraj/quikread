package com.katana.quikread.rest;

import com.google.gson.GsonBuilder;
import com.katana.quikread.rest.api.GoodreadsService;
import com.katana.quikread.rest.api.QuikrService;
import com.katana.quikread.rest.models.BooksByLocationResponse;
import com.katana.quikread.rest.models.QuikrResponseDeserializer;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit.Callback;
import retrofit.Endpoint;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.converter.SimpleXMLConverter;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */
public class RestDataSource {

    private static final String TAG = RestDataSource.class.getSimpleName();

    private QuikrService quikrService;
    private GoodreadsService goodreadsService;

    private OnRequestFinishedListener listener;

    @Inject
    public RestDataSource(@Named("Quikr")Endpoint quikrEndpoint, @Named("Goodreads")Endpoint goodreadsEndpoint,
                          Client client){

        RequestInterceptor quikrInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("X-Quikr-App-Id", Keys.QUIKR_APP_ID);
                request.addHeader("X-Quikr-Token-Id", Keys.QUIKR_TOKEN_ID);
                request.addHeader("Content-Type", "application/json");
            }
        };

        RestAdapter quikrAdapter = new RestAdapter.Builder()
                .setEndpoint(quikrEndpoint)
                .setClient(client)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(new GsonBuilder()
                                .registerTypeAdapter(BooksByLocationResponse.class, new QuikrResponseDeserializer())
                                .create()))
                .setRequestInterceptor(quikrInterceptor)
                .build();

        quikrService = quikrAdapter.create(QuikrService.class);

        RequestInterceptor goodreadsInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam("key", Keys.GOODREADS_API_KEY);
            }
        };

        RestAdapter goodreadsAdapter = new RestAdapter.Builder()
                .setEndpoint(goodreadsEndpoint)
                .setClient(client)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new SimpleXMLConverter())
                .setRequestInterceptor(goodreadsInterceptor)
                .build();

        goodreadsService = goodreadsAdapter.create(GoodreadsService.class);
    }

    public void setOnRequestFinishedListener(OnRequestFinishedListener listener){
        this.listener = listener;
    }

    Callback retrofitCallback = new Callback() {
        @Override
        public void success(Object o, Response response) {
            listener.onSuccess(o, response);
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onError(error);
        }
    };

    public void fetchBooksByLocation(String cityName){

        quikrService.getBooksByLocation("52", "2", "Delhi", retrofitCallback);
    }

    public void searchBookByTitle(String title){

        goodreadsService.searchBookByTitle(title, retrofitCallback);
    }
}