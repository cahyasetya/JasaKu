package com.example.jasaku.interfaces;

import com.example.jasaku.model.Kategori;

import java.util.List;

/**
 * Created by light on 14/05/17.
 */

public interface HalamanRegisterTokoActivityInterface {
    void onKategoriLoadedSuccessfully(List<Kategori> kategoriList);
    void onKategoriLoadFailed();
    void onBuatTokoSuccessful();
    void onBuatTokoFailed();
}
