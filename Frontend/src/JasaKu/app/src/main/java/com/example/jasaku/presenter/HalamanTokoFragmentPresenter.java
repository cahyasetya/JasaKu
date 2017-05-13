package com.example.jasaku.presenter;

import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanTokoFragmentInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by light on 13/05/17.
 */

public class HalamanTokoFragmentPresenter {

    private HalamanTokoFragmentInterface callback;

    public HalamanTokoFragmentPresenter(HalamanTokoFragmentInterface callback){
        this.callback=callback;
    }

    public void loadData(){

    }
}
