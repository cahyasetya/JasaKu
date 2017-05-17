package com.example.jasaku.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.jasaku.LoginActivity;
import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.model.Pengguna;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by light on 14/05/17.
 */

public class HalamanLoginActivityPresenter {

    private LoginActivity callback;

    public HalamanLoginActivityPresenter(LoginActivity callback) {
        this.callback=callback;
    }

    public void login(Map<String, String> field){
        ServiceInterface serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
        serviceInterface.login(field).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loginSuccess,this::loginFailed);
    }

    public void loginSuccess(Pengguna pengguna){
        Gson gson=new Gson();
        String penggunaString=gson.toJson(pengguna);

        SharedPreferences preferences=callback.getSharedPreferences("jasaku", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("isLoggedIn",true);
        editor.putString("id_user",String.valueOf(pengguna.getId()));
        editor.putString("pengguna",penggunaString);
        editor.putInt("has_toko",pengguna.getToko());
        editor.putString("nama",pengguna.getNama());
        editor.putString("kontak", pengguna.getKontak());
        editor.commit();

        //Toast.makeText(callback,"Token anda: "+FirebaseInstanceId.getInstance().getToken(),Toast.LENGTH_SHORT).show();
        Log.d(this.getClass().getSimpleName(),"token: "+FirebaseInstanceId.getInstance().getToken());
        callback.onLoginSuccess();
    }

    public void loginFailed(Throwable t){
        t.printStackTrace();
        callback.onLoginError();
    }
}