package com.example.jasaku.api;

import com.android.volley.Response;
import com.example.jasaku.model.Jasa;
import com.example.jasaku.model.Toko;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by light on 04/05/17.
 */

public interface ServiceInterface {
    @GET("toko")
    Observable<List<Toko>> getToko();

    @GET("toko")
    Observable<List<Toko>> getToko(@QueryMap Map<String, String> query);

    @GET("jasa/id_toko/{idtoko}")
    Observable<List<Jasa>> getJasa(@Path("idtoko") String idtoko);

    @GET("transaksi/pengguna/{idpengguna}/pesananmasuk")
    Observable<List<Jasa>> getPesananMasuk(@Path("idpengguna") String idpengguna);
}
