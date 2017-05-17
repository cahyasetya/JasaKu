package com.example.jasaku.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanKelolaJasaActivityInterface;
import com.example.jasaku.model.Jasa;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.google.gson.Gson;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by light on 14/05/17.
 */

public class HalamanKelolaJasaActivityPresenter {

    private HalamanKelolaJasaActivityInterface callback;

    public HalamanKelolaJasaActivityPresenter(HalamanKelolaJasaActivityInterface callback) {
        this.callback = callback;
    }

    public void insertJasa(Map<String, String> fields){
        ServiceInterface serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
        serviceInterface.insertJasa(fields).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::insertSuccessful,this::insertDataFailed);
    }

    private void insertSuccessful(ResponseBody responseBody){
        callback.onDataInserted();
    }

    private void insertDataFailed(Throwable t){
        callback.onDataInsertFailed();
    }
}
