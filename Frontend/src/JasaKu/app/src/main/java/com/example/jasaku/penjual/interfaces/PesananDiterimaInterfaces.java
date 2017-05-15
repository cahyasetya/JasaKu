package com.example.jasaku.penjual.interfaces;

import com.example.jasaku.model.PesananMasuk;

import java.util.List;

/**
 * Created by cahyasetya on 5/15/2017.
 */

public interface PesananDiterimaInterfaces {
    public void onDataLoaded(List<PesananMasuk> pesananMasukList);
    public void onDataLoadFailed();
}
