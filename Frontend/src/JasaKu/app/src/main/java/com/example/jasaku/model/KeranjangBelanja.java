package com.example.jasaku.model;

import java.util.List;

/**
 * Created by light on 17/04/17.
 */

public class KeranjangBelanja {
    private List<Belanjaan> belanjaanList;
    private Long total;

    public List<Belanjaan> getBelanjaanList() {
        return belanjaanList;
    }

    public void setBelanjaanList(List<Belanjaan> belanjaanList) {
        this.belanjaanList = belanjaanList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
