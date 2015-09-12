package com.katana.quikread.modules;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import com.katana.quikread.App;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */

@Module
public class ApiModule {

    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50 MB
    public static long HTTP_TIMEOUT = 30 * 1000; // 30 seconds


    @Provides
    @Named("Quikr")
    public Endpoint provideQuikrEndpoint(){
        return Endpoints.newFixedEndpoint("https://api.quikr.com");
    }

    @Provides
    @Named("Goodreads")
    public Endpoint provideGoodreadsEndpoint(){
        return Endpoints.newFixedEndpoint("");
    }

    @Provides
    public OkHttpClient provideOkHttpClient(App application){
        return createOkHttpClient(application);
    }

    @Provides
    public Client provideClient(OkHttpClient okHttpClient){
        return new OkClient(okHttpClient);
    }

    @Provides
    public Picasso providePicasso(App app, OkHttpClient client){

        return new Picasso.Builder(app)
                .downloader(new OkHttpDownloader(client))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.e("Picasso", "Failed to load image " + uri.toString());
                    }
                })
                .build();
    }
    static OkHttpClient createOkHttpClient(Application app){

        OkHttpClient client = new OkHttpClient();

        client.setConnectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);

        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        client.setCache(cache);

        return client;
    }
}
