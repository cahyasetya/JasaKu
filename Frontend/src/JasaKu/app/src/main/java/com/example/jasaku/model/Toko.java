package com.example.jasaku.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by light on 17/04/17.
 */

public class Toko {
    private String id;
    private String nama;
    private String alamat;
    private String deskripsi;
    private String jamOperasional;
    private String kontak;
    private long hargaMinimal;
    @SerializedName("id_pengguna")
    private String idPengguna;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getJamOperasional() {
        return jamOperasional;
    }

    public void setJamOperasional(String jamOperasional) {
        this.jamOperasional = jamOperasional;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public long getHargaMinimal() {
        return hargaMinimal;
    }

    public void setHargaMinimal(long hargaMinimal) {
        this.hargaMinimal = hargaMinimal;
    }

    public String getId() {
        return id;
    }

    public String getIdPengguna() {
        return idPengguna;
    }
}
