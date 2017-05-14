package com.example.jasaku.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanDataPemesanActivityInterface;
import com.example.jasaku.model.RequestMembeli;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by light on 14/05/17.
 */

public class HalamanDataPemesanActivityPresenter {

    private HalamanDataPemesanActivityInterface callback;

    public HalamanDataPemesanActivityPresenter(HalamanDataPemesanActivityInterface callback) {
        this.callback = callback;
    }

    public void beli(RequestMembeli requestMembeli){
        ServiceInterface serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
        serviceInterface.beli(requestMembeli).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::pembelianSuccess,this::pembelianFailed);
    }

    public void pembelianSuccess(ResponseBody responseBody){
        callback.onPembelianSuccess();
    }

    public void pembelianFailed(Throwable t){
        callback.onPembelianFailed();
    }
}
