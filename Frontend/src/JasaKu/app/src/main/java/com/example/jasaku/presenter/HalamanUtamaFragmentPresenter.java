package com.example.jasaku.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.fragment.HalamanUtamaFragment;
import com.example.jasaku.model.Toko;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by light on 04/05/17.
 */

public class HalamanUtamaFragmentPresenter {

    HalamanUtamaFragment callback;

    public HalamanUtamaFragmentPresenter(HalamanUtamaFragment callback) {
        this.callback=callback;
    }

    public void loadData(){
        ServiceInterface serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
        serviceInterface.getToko().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::dataLoaded,this::dataLoadError);
    }

    private void dataLoaded(List<Toko> tokoList){
        callback.onDataLoaded(tokoList);
    }

    private void dataLoadError(Throwable t){
        callback.onDataLoadError(t);
    }
}
