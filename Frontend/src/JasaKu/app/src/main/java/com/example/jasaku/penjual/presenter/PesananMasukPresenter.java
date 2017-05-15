package com.example.jasaku.penjual.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.model.PesananMasuk;
import com.example.jasaku.penjual.interfaces.PesananMasukInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by light on 15/05/17.
 */

public class PesananMasukPresenter {

    private PesananMasukInterface callback;
    private ServiceInterface serviceInterface;

    public PesananMasukPresenter(PesananMasukInterface callback) {
        this.callback = callback;
        this.serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
    }

    public void getPesananMasuk(String idToko){
        serviceInterface.getPesananMasuk(idToko).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::pesananMasukLoaded,this::pesananMasukLoadFailed);
    }

    private void pesananMasukLoaded(List<PesananMasuk> pesananMasukList){
        callback.onPesananMasukLoaded(pesananMasukList);
    }

    private void pesananMasukLoadFailed(Throwable t){
        callback.onPesananMasukLoadFailed();
    }
}
