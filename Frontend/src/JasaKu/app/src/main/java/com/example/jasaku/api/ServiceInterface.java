package com.example.jasaku.api;

import com.example.jasaku.model.Jasa;
import com.example.jasaku.model.Kabupaten;
import com.example.jasaku.model.Kategori;
import com.example.jasaku.model.Kecamatan;
import com.example.jasaku.model.Pengguna;
import com.example.jasaku.model.PesananMasuk;
import com.example.jasaku.model.Provinsi;
import com.example.jasaku.model.RequestMembeli;
import com.example.jasaku.model.Toko;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @FormUrlEncoded
    @POST("register")
    Observable<ResponseBody> register(@FieldMap Map<String,String> field);

    @FormUrlEncoded
    @POST("login")
    Observable<Pengguna> login(@FieldMap Map<String,String> field);

    @POST("membeli")
    Observable<ResponseBody> beli(@Body RequestMembeli body);

    @GET("kategori")
    Observable<List<Kategori>> getKategori();

    @FormUrlEncoded
    @POST("toko")
    Observable<ResponseBody> buatToko(@FieldMap Map<String, String> field);

    //@FormUrlEncoded
    @POST("/jasa")
    Observable<ResponseBody> insertJasa(@Body Map<String, String> fields);

    @GET("toko/id_pengguna/{id_pengguna}")
    Observable<List<Toko>> getTokoByIdPengguna(@Path("id_pengguna") String id_pengguna);

    @DELETE("jasa/{id}")
    Observable<ResponseBody> hapusJasa(@Path("id") String id);

    //@FormUrlEncoded
    @PUT("/jasa")
    Observable<ResponseBody> ubahJasa(@Body Map<String, String> fields);

    @GET("toko/{id}/pemesanan/{status}")
    Observable<List<PesananMasuk>> getPesananMasuk(@Path("id") String idToko,@Path("status") String status);

    @GET("toko/{idpengguna}/pemesanan/{status}")
    Observable<List<PesananMasuk>> getPesananDiterima(@Path("idpengguna") String idPengguna,@Path("status") String status);

    @FormUrlEncoded
    @POST("transaksi/disetujui")
    Observable<ResponseBody> terimaPesanan(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("transaksi/ditolak")
    Observable<ResponseBody> tolakPesanan(@FieldMap Map<String, String> fields);

    @GET("provinsi/")
    Observable<List<Provinsi>> getProvinsi();

    @GET("kabupaten/id_provinsi/{idprovinsi}")
    Observable<List<Kabupaten>> getKabupatenByProvinsi(@Path("idprovinsi") String idProvinsi);

    @GET("kecamatan/id_kabupaten/{idkabupaten}")
    Observable<List<Kecamatan>> getKecamatanByKabupaten(@Path("idkabupaten") String idKabupaten);
}
