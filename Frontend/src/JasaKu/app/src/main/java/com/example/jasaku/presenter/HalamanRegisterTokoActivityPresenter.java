package com.example.jasaku.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanRegisterActivityInterface;
import com.example.jasaku.interfaces.HalamanRegisterTokoActivityInterface;
import com.example.jasaku.model.Kategori;

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
                .subscribe();
    }

    public void loadKategori(){
        serviceInterface.getKategori().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::kategoriLoded,this::kategoriLoadFailed);
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

    private void buatTokoFailed(Throwable t){
        callback.onBuatTokoFailed();
    }
}
