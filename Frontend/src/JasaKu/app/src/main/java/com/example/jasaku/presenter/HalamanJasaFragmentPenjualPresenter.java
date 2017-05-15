package com.example.jasaku.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanJasaFragmentInterfaces;
import com.example.jasaku.interfaces.HalamanJasaFragmentPenjualInterface;
import com.example.jasaku.model.Jasa;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by light on 15/05/17.
 */

public class HalamanJasaFragmentPenjualPresenter {

    private HalamanJasaFragmentPenjualInterface callback;
    private ServiceInterface serviceInterface;

    public HalamanJasaFragmentPenjualPresenter(HalamanJasaFragmentPenjualInterface callback){
        this.callback=callback;
        serviceInterface = ServiceGenerator.createService(ServiceInterface.class);
    }

    public void loadData(String idToko){
        serviceInterface.getJasa(idToko).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::dataLoaded,this::dataError);
    }

    public void hapusJasa(String id){
        serviceInterface.hapusJasa(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::jasaDeleted,this::jasaDeleteFailed);
    }

    public void ubahJasa(Map<String, String> fields){
        serviceInterface.ubahJasa(fields).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::jasaEdited,this::jasaEditFailed);
    }

    private void jasaEdited(ResponseBody responseBody){

    }

    private void jasaEditFailed(Throwable t){

    }

    private void jasaDeleted(ResponseBody responseBody){
        callback.onJasaDeleted();
    }

    private void jasaDeleteFailed(Throwable t){
        callback.onJasaDeleteFailed();
    }

    private void dataLoaded(List<Jasa> jasaList){
        callback.onDataLoaded(jasaList);
    }

    private void dataError(Throwable t){
        callback.onDataLoadError(t);
    }
}