package com.example.jasaku.interfaces;

import com.example.jasaku.model.Toko;

import java.util.List;

/**
 * Created by light on 04/05/17.
 */

public interface HalamanUtamaFragmentInterfaces {
    void onDataLoaded(List<Toko> tokoList);
    void onDataLoadError(Throwable t);
}
