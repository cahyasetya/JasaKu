package com.example.jasaku.interfaces;

import com.example.jasaku.model.Toko;

import java.util.List;

/**
 * Created by light on 13/05/17.
 */

public interface HalamanTokoFragmentInterface {
    public void onDataLoaded(Toko toko);
    public void onDataLoadError(Throwable t);
}
