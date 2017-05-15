package com.example.jasaku.penjual.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.model.PesananMasuk;
import com.example.jasaku.penjual.interfaces.PesananMasukInterface;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

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

    public void terimaPesanan(Map<String, String> fields){
        serviceInterface.terimaPesanan(fields).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::pesananDiterima,this::pesananDiterimaError);
    }

    public void tolakPesanan(Map<String, String> fields){
        serviceInterface.tolakPesanan(fields).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::pesananDitolak,this::pesananDitolakError);
    }

    private void pesananDitolakError(Throwable throwable) {
        callback.onPesananDitolakError();
    }

    private void pesananDitolak(ResponseBody responseBody) {
        callback.onPesananDitolak();
    }

    private void pesananDiterimaError(Throwable throwable) {
        callback.onPesananDiterimaError();
    }

    private void pesananDiterima(ResponseBody responseBody) {
        callback.onPesananDiterima();
    }

    private void pesananMasukLoaded(List<PesananMasuk> pesananMasukList){
        callback.onPesananMasukLoaded(pesananMasukList);
    }

    private void pesananMasukLoadFailed(Throwable t){
        callback.onPesananMasukLoadFailed();
    }
}
