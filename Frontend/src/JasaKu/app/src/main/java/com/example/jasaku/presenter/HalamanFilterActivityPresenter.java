package com.example.jasaku.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanFilterActivityInterface;
import com.example.jasaku.model.Kategori;

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

    }

    private void loadFailed(Throwable throwable) {
        callback.onLoadFailed();
    }

    private void kategoriLoaded(List<Kategori> kategoriList) {
        callback.onKategoriLoaded(kategoriList);
    }
}
