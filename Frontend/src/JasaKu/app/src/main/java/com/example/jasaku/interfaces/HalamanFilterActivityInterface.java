package com.example.jasaku.interfaces;

import com.example.jasaku.model.Kabupaten;
import com.example.jasaku.model.Kategori;
import com.example.jasaku.model.Kecamatan;
import com.example.jasaku.model.Provinsi;

import java.util.List;

/**
 * Created by cahyasetya on 5/17/2017.
 */

public interface HalamanFilterActivityInterface {
    public void onKategoriLoaded(List<Kategori> kategoriList);
    public void onLoadFailed(Throwable t);
    public void onProvinsiLoaded(List<Provinsi> provinsiList);
    public void onKabupatenLoaded(List<Kabupaten> kabupatenList);
    public void onKecamatanLoaded(List<Kecamatan> kecamatanList);
}
