package com.example.jasaku.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cahyasetya on 5/17/2017.
 */

public class Kabupaten {
    private String id;
    private String nama;
    @SerializedName("id_provinsi")
    private String idProvinsi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdProvinsi() {
        return idProvinsi;
    }

    public void setIdProvinsi(String idProvinsi) {
        this.idProvinsi = idProvinsi;
    }
}
