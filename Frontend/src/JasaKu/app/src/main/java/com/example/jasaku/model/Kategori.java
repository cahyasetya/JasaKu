package com.example.jasaku.model;

/**
 * Created by light on 25/04/17.
 */

public class Kategori {
    private int id;
    private String nama;

    public Kategori(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
