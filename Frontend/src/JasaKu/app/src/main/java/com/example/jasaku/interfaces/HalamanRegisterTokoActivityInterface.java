package com.example.jasaku.interfaces;

import com.example.jasaku.model.Kabupaten;
import com.example.jasaku.model.Kategori;
import com.example.jasaku.model.Kecamatan;
import com.example.jasaku.model.Provinsi;

import java.util.List;

/**
 * Created by light on 14/05/17.
 */

public interface HalamanRegisterTokoActivityInterface {
    void onKategoriLoadedSuccessfully(List<Kategori> kategoriList);
    void onKategoriLoadFailed();
    void onBuatTokoSuccessful();
    void onBuatTokoFailed();
    public void onLoadFailed(Throwable t);
    public void onProvinsiLoaded(List<Provinsi> provinsiList);
    public void onKabupatenLoaded(List<Kabupaten> kabupatenList);
    public void onKecamatanLoaded(List<Kecamatan> kecamatanList);
}
