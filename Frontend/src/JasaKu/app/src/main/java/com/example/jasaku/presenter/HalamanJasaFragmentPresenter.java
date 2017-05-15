package com.example.jasaku.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanJasaFragmentInterfaces;
import com.example.jasaku.model.Jasa;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by light on 04/05/17.
 */

public class HalamanJasaFragmentPresenter {

    private HalamanJasaFragmentInterfaces callback;
    private ServiceInterface serviceInterface;

    public HalamanJasaFragmentPresenter(HalamanJasaFragmentInterfaces callback){
        this.callback=callback;
        serviceInterface = ServiceGenerator.createService(ServiceInterface.class);
    }

    public void loadData(String idToko){
        serviceInterface.getJasa(idToko).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::dataLoaded,this::dataError);
    }

    private void dataLoaded(List<Jasa> jasaList){
        callback.onDataLoaded(jasaList);
    }

    private void dataError(Throwable t){
        callback.onDataLoadError(t);
    }
}
