package com.example.jasaku.penjual.interfaces;

import com.example.jasaku.model.PesananMasuk;

import java.util.List;

/**
 * Created by light on 15/05/17.
 */

public interface PesananMasukInterface {
    void onPesananMasukLoaded(List<PesananMasuk> pesananMasukList);
    void onPesananMasukLoadFailed();
}
