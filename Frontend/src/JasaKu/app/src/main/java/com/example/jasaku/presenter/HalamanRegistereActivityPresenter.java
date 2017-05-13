package com.example.jasaku.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanRegisterActivityInterface;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by light on 14/05/17.
 */

public class HalamanRegistereActivityPresenter {

    private HalamanRegisterActivityInterface callback;

    public HalamanRegistereActivityPresenter(HalamanRegisterActivityInterface callback) {
        this.callback = callback;
    }

    public void register(Map<String, String> field){
        ServiceInterface serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
        serviceInterface.register(field).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::registerSuccessful,this::registerFailed);
    }

    public void registerSuccessful(ResponseBody response){
        callback.onRegisterSuccessful();
    }

    public void registerFailed(Throwable t){
        callback.onRegisterFailed(t);
    }
}
