package com.example.jasaku.model;

import android.support.v7.widget.LinearSmoothScroller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by light on 14/05/17.
 */

public class RequestMembeli {
    private String id_pengguna;
    private List<Paket> paket;

    public RequestMembeli(){
        paket=new ArrayList<>();
    }

    public String getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(String id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public List<Paket> getPaket() {
        return paket;
    }

    public void setPaket(List<Paket> paket) {
        this.paket = paket;
    }

    public void addPaket(Paket paket){
        this.paket.add(paket);
    }
}
