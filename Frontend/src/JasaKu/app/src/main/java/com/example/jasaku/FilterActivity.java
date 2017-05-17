package com.example.jasaku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jasaku.adapter.KategoriAdapter;
import com.example.jasaku.adapter.ProvinsiAdapter;
import com.example.jasaku.interfaces.HalamanFilterActivityInterface;
import com.example.jasaku.interfaces.KategoriInterface;
import com.example.jasaku.model.Kabupaten;
import com.example.jasaku.model.Kategori;
import com.example.jasaku.model.Kecamatan;
import com.example.jasaku.model.Provinsi;
import com.example.jasaku.presenter.HalamanFilterActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity implements KategoriInterface, HalamanFilterActivityInterface{

    @BindView(R.id.kategori_recyclerview)
    RecyclerView kategoriRecyclerView;
    @BindView(R.id.nama_provinsi)
    Spinner provinsiSpinner;

    HalamanFilterActivityPresenter presenter;

    LinearLayoutManager lm;
    KategoriAdapter kategoriAdapter;
    List<Kategori> kategoriList;

    String idKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter=new HalamanFilterActivityPresenter(this);
    }

    @Override
    public void onKategoriSelected(String idKategori) {
        this.idKategori=idKategori;
    }

    @Override
    public void onKategoriLoaded(List<Kategori> kategoriList) {
        lm=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        kategoriRecyclerView.setLayoutManager(lm);
        this.kategoriList = kategoriList;
        kategoriAdapter=new KategoriAdapter(this,this.kategoriList);
        kategoriRecyclerView.setAdapter(kategoriAdapter);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,lm.getOrientation());
        kategoriRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadKategori();
    }

    @Override
    public void onLoadFailed() {
        Toast.makeText(this,"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProvinsiLoaded(List<Provinsi> provinsiList) {
        ProvinsiAdapter adapter=new ProvinsiAdapter(this, provinsiList);
        provinsiSpinner.setAdapter(adapter);
    }

    @Override
    public void onKabupatenLoaded(List<Kabupaten> kabupatenList) {

    }

    @Override
    public void onKecamatanLoaded(List<Kecamatan> kecamatanList) {

    }
}
