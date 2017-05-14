package com.example.jasaku.interfaces;

import com.example.jasaku.model.Kategori;

import java.util.List;

/**
 * Created by light on 14/05/17.
 */

public interface HalamanRegisterTokoActivityInterface {
    public void onKategoriLoadedSuccessfully(List<Kategori> kategoriList);
    public void onKategoriLoadFailed();
    public void onBuatTokoSuccessful();
    public void onBuatTokoFailed();
}
