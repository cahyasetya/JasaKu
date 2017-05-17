package com.example.jasaku.penjual.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.penjual.interfaces.UpdateJasaInterface;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by buthorokuntor on 5/16/17.
 */

public class UpdateJasaPresenter {

    private UpdateJasaInterface callback;

    public UpdateJasaPresenter(UpdateJasaInterface callback) {
        this.callback = callback;
    }

    public void ubahJasa(Map<String, String> fields){
        ServiceInterface serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
        serviceInterface.ubahJasa(fields).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::jasaEdited,this::jasaEditFailed);
    }

    private void jasaEdited(ResponseBody responseBody){
        callback.onJasaEdited();
    }

    private void jasaEditFailed(Throwable t){
        callback.onJasaEditFailed();
    }
}
