package com.example.jasaku.api;

import com.example.jasaku.model.Toko;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by light on 04/05/17.
 */

public interface ServiceInterface {
    @GET("toko")
    Observable<List<Toko>> getToko();

    @GET("toko")
    Observable<List<Toko>> getToko(@QueryMap Map<String, String> query);
}
