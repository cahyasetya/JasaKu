package com.example.jasaku.interfaces;

import com.example.jasaku.model.Jasa;

import java.util.List;

/**
 * Created by light on 04/05/17.
 */

public interface HalamanJasaFragmentInterfaces {
    public void onDataLoaded(List<Jasa> jasaList);
    public void onDataLoadError(Throwable t);
}
