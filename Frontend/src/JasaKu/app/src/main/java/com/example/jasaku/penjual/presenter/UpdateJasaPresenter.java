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
    private ServiceInterface serviceInterface;

    public UpdateJasaPresenter(UpdateJasaInterface callback) {
        this.callback = callback;
        serviceInterface = ServiceGenerator.createService(ServiceInterface.class);
    }

    public void ubahJasa(Map<String, String> fields){
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
