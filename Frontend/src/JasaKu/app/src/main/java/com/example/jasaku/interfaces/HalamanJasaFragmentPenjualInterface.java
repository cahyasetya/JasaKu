package com.example.jasaku.interfaces;

import com.example.jasaku.model.Jasa;

import java.util.List;

/**
 * Created by light on 15/05/17.
 */

public interface HalamanJasaFragmentPenjualInterface {
    void onDataLoaded(List<Jasa> jasaList);
    void onDataLoadError(Throwable t);
    void onJasaDeleted();
    void onJasaDeleteFailed();
    void onJasaEdited();
    void onJasaEditFailed();
}
