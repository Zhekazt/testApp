package com.stuzo.zheka.weathertest.Api;

import com.stuzo.zheka.weathertest.Const;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zheka on 27.04.16.
 */
public class ApiModule {

    private static final boolean ENABLE_LOG = false;

    public static ApiInterface getApiInterface(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(3000, TimeUnit.SECONDS);

        if (ENABLE_LOG) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.interceptors().add(logging);
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build();


        ApiInterface apiInterface =  retrofit.create(ApiInterface.class);
        return apiInterface;
    }
}
