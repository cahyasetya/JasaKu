package com.example.jasaku.interfaces;

import com.example.jasaku.model.Toko;

/**
 * Created by light on 13/05/17.
 */

public interface HalamanTokoFragmentInterface {
    void onDataLoaded(Toko toko);
    void onDataLoadError(Throwable t);
}
