package com.example.jasaku.interfaces;

import com.example.jasaku.model.Toko;

import java.util.List;

/**
 * Created by light on 04/05/17.
 */

public interface HalamanUtamaFragmentInterfaces {
    public void onDataLoaded(List<Toko> tokoList);
    public void onDataLoadError(Throwable t);
}
