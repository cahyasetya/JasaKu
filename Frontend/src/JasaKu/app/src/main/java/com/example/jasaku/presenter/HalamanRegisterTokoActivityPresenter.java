package com.example.jasaku.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanRegisterTokoActivityInterface;
import com.example.jasaku.model.Kabupaten;
import com.example.jasaku.model.Kategori;
import com.example.jasaku.model.Kecamatan;
import com.example.jasaku.model.Provinsi;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by light on 14/05/17.
 */

public class HalamanRegisterTokoActivityPresenter {

    private HalamanRegisterTokoActivityInterface callback;
    ServiceInterface serviceInterface;

    public HalamanRegisterTokoActivityPresenter(HalamanRegisterTokoActivityInterface callback) {
        this.callback = callback;
        serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
    }

    public void buatToko(Map<String, String> field){
        serviceInterface.buatToko(field).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::buatTokoSuccess, this::buatTokoFailed);
    }

    public void loadKategori(){
        serviceInterface.getKategori().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::kategoriLoded,this::kategoriLoadFailed);
    }

    public void loadProvinsi(){
        serviceInterface.getProvinsi().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::provinsiLoaded,this::loadFailed);
    }

    public void loadKabupaten(String idProvinsi){
        serviceInterface.getKabupatenByProvinsi(idProvinsi).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::kabupatenLoaded,this::loadFailed);
    }

    public void loadKecamatan(String idKabupaten){
        serviceInterface.getKecamatanByKabupaten(idKabupaten).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::kecamatanLoaded,this::loadFailed);
    }

    private void kategoriLoded(List<Kategori> kategoriList){
        callback.onKategoriLoadedSuccessfully(kategoriList);
    }

    private void kategoriLoadFailed(Throwable t){
        callback.onKategoriLoadFailed();
    }

    private void buatTokoSuccess(ResponseBody responseBody){
        callback.onBuatTokoSuccessful();
    }

    private void loadFailed(Throwable throwable) {
        callback.onLoadFailed(throwable);
    }

    private void buatTokoFailed(Throwable t){
        callback.onBuatTokoFailed();
    }

    private void provinsiLoaded(List<Provinsi> provinsiList){
        callback.onProvinsiLoaded(provinsiList);
    }

    private void kabupatenLoaded(List<Kabupaten> kabupatenList){
        callback.onKabupatenLoaded(kabupatenList);
    }

    private void kecamatanLoaded(List<Kecamatan> kecamatanList){
        callback.onKecamatanLoaded(kecamatanList);
    }
}
