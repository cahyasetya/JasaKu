package com.example.jasaku.interfaces;

import com.example.jasaku.model.Jasa;

import java.util.List;

/**
 * Created by light on 14/05/17.
 */

public interface HalamanPesananMasukFragmentInterface {
    void onDataLoaded(List<Jasa> jasaList);
    void onDataLoadFailed();
}
