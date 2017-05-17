package com.example.jasaku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jasaku.adapter.KabupatenAdapter;
import com.example.jasaku.adapter.KategoriAdapter;
import com.example.jasaku.adapter.KecamatanAdapter;
import com.example.jasaku.adapter.ProvinsiAdapter;
import com.example.jasaku.interfaces.HalamanFilterActivityInterface;
import com.example.jasaku.interfaces.KategoriInterface;
import com.example.jasaku.model.Kabupaten;
import com.example.jasaku.model.Kategori;
import com.example.jasaku.model.Kecamatan;
import com.example.jasaku.model.Provinsi;
import com.example.jasaku.presenter.HalamanFilterActivityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity implements KategoriInterface, HalamanFilterActivityInterface,AdapterView.OnItemSelectedListener{

    @BindView(R.id.kategori_recyclerview)
    RecyclerView kategoriRecyclerView;
    @BindView(R.id.provinsi_spinner)
    Spinner provinsiSpinner;
    @BindView(R.id.kabupaten_spinner)
    Spinner kabupatenSpinner;
    @BindView(R.id.kecamatan_spinner)
    Spinner kecamatanSpinner;

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

        presenter.loadProvinsi();
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
        provinsiSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onKabupatenLoaded(List<Kabupaten> kabupatenList) {
        KabupatenAdapter adapter=new KabupatenAdapter(this,kabupatenList);
        kabupatenSpinner.setAdapter(adapter);
        kabupatenSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onKecamatanLoaded(List<Kecamatan> kecamatanList) {
        KecamatanAdapter adapter=new KecamatanAdapter(this,kecamatanList);
        kecamatanSpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.provinsi_container:
                presenter.loadKabupaten(String.valueOf(id));
                break;
            case R.id.kabupaten_container:
                presenter.loadKecamatan(String.valueOf(id));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
