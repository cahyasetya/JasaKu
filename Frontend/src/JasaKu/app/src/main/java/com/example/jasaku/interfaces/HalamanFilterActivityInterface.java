package com.example.jasaku.interfaces;

import com.example.jasaku.model.Kategori;

import java.util.List;

/**
 * Created by cahyasetya on 5/17/2017.
 */

public interface HalamanFilterActivityInterface {
    public void onKategoriLoaded(List<Kategori> kategoriList);
    public void onKategoriLoadFailed();
}
