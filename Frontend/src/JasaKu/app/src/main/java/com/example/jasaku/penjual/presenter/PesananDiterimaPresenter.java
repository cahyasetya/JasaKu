package com.example.jasaku.penjual.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.model.PesananMasuk;
import com.example.jasaku.penjual.interfaces.PesananDiterimaInterfaces;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cahyasetya on 5/15/2017.
 */

public class PesananDiterimaPresenter {

    PesananDiterimaInterfaces callback;
    ServiceInterface serviceInterface;

    public PesananDiterimaPresenter(PesananDiterimaInterfaces callback) {
        this.callback = callback;
        serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
    }

    public void loadPesananDiterima(String idPengguna){
        serviceInterface.getPesananDiterima(idPengguna,"2").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::dataLoaded,this::dataLoadError);
    }

    public void dataLoaded(List<PesananMasuk> pesananMasukList){
        callback.onDataLoaded(pesananMasukList);
    }

    public void dataLoadError(Throwable t){
        callback.onDataLoadFailed();
    }
}
