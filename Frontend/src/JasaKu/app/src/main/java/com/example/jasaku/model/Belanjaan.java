package com.example.jasaku.model;

/**
 * Created by light on 17/04/17.
 */

public class Belanjaan {
    private Jasa jasa;
    private int kuantitas;
    private long total;

    public Jasa getJasa() {
        return jasa;
    }

    public void setJasa(Jasa jasa) {
        this.jasa = jasa;
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(int kuantitas) {
        this.kuantitas = kuantitas;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
