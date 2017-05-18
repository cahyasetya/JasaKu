package com.example.jasaku.interfaces;

import com.example.jasaku.model.Jasa;

import java.util.List;

/**
 * Created by light on 04/05/17.
 */

public interface HalamanJasaFragmentInterfaces {
    void onDataLoaded(List<Jasa> jasaList);
    void onDataLoadError(Throwable t);
    void onJasaSelected();
    void onJasaNotSelected();
}
