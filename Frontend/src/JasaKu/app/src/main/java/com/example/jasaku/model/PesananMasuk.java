package com.example.jasaku.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by light on 15/05/17.
 */

public class PesananMasuk {
    @SerializedName("id_jasa")
    private String idJasa;
    @SerializedName("nama")
    private String nama;
    @SerializedName("harga")
    private String harga;
    @SerializedName("kuantitas")
    private String kuantitas;
    @SerializedName("data_pembeli")
    private Pengguna pembeli;

    public String getIdJasa() {
        return idJasa;
    }

    public void setIdJasa(String idJasa) {
        this.idJasa = idJasa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(String kuantitas) {
        this.kuantitas = kuantitas;
    }

    public Pengguna getPembeli() {
        return pembeli;
    }

    public void setPembeli(Pengguna pembeli) {
        this.pembeli = pembeli;
    }
}
