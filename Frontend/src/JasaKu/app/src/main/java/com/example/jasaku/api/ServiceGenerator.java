package com.example.jasaku.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by light on 30/04/17.
 */

public class ServiceGenerator {
    private static final String API_BASE_URL="http://jasaq.esy.es/public/";

    private static OkHttpClient.Builder httpClient=new OkHttpClient.Builder();

    private static RxJava2CallAdapterFactory rxAdapter=RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

    private static Retrofit.Builder builder=new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(rxAdapter);

    private static Retrofit retrofit=builder.build();

    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}
