package com.example.jasaku.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by light on 17/04/17.
 */

public class KeranjangBelanja implements Serializable{

    private static KeranjangBelanja instance=null;

    private static List<Belanjaan> belanjaanList=new ArrayList<>();
    private static Long total;

    public static KeranjangBelanja getInstance(){
        if(instance==null){
            instance=new KeranjangBelanja();
        }
        return instance;
    }

    public static List<Belanjaan> getBelanjaanList() {
        return belanjaanList;
    }

    public static void setBelanjaanList(List<Belanjaan> belanjaanList) {
        KeranjangBelanja.belanjaanList = belanjaanList;
    }

    public static Long getTotal() {
        return total;
    }

    public static void setTotal(Long total) {
        KeranjangBelanja.total = total;
    }

    public static void tambahBelanjaan(Belanjaan belanjaan){
        KeranjangBelanja.belanjaanList.add(belanjaan);
    }
}
