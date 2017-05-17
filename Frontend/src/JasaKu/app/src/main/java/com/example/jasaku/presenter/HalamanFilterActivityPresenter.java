package com.example.jasaku.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanFilterActivityInterface;
import com.example.jasaku.model.Kabupaten;
import com.example.jasaku.model.Kategori;
import com.example.jasaku.model.Kecamatan;
import com.example.jasaku.model.Provinsi;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cahyasetya on 5/17/2017.
 */

public class HalamanFilterActivityPresenter {

    HalamanFilterActivityInterface callback;
    ServiceInterface serviceInterface;

    public HalamanFilterActivityPresenter(HalamanFilterActivityInterface callback) {
        this.callback = callback;
        serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
    }

    public void loadKategori() {
        serviceInterface.getKategori().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::kategoriLoaded,this::loadFailed);
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

    private void loadFailed(Throwable throwable) {
        callback.onLoadFailed(throwable);
    }

    private void kategoriLoaded(List<Kategori> kategoriList) {
        callback.onKategoriLoaded(kategoriList);
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
