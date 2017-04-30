package com.example.jasaku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jasaku.adapter.KategoriAdapter;
import com.example.jasaku.model.Kategori;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity {

    @BindView(R.id.kategori_recyclerview)
    RecyclerView kategoriRecyclerView;

    LinearLayoutManager lm;
    KategoriAdapter kategoriAdapter;
    List<Kategori> kategoriList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void init(){

        //prepare kategori
        lm=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        kategoriRecyclerView.setLayoutManager(lm);
        kategoriList = new ArrayList<>();
        Kategori laundryKategori=new Kategori("Laundry");
        Kategori makananRinganKategori=new Kategori("Makanan Ringan");
        Kategori makanan=new Kategori("Makanan Berat");
        Kategori minuman=new Kategori("Minuman");
        Kategori fashion=new Kategori("Fashion");
        kategoriList.add(laundryKategori);
        kategoriList.add(makananRinganKategori);
        kategoriList.add(makanan);
        kategoriList.add(minuman);
        kategoriList.add(fashion);
        kategoriAdapter=new KategoriAdapter(this,kategoriList);
        kategoriRecyclerView.setAdapter(kategoriAdapter);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,lm.getOrientation());
        kategoriRecyclerView.addItemDecoration(dividerItemDecoration);
    }
}
