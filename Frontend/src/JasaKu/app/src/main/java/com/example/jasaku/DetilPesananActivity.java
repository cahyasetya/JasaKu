package com.example.jasaku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.jasaku.adapter.DetilPesananAdapter;
import com.example.jasaku.model.Belanjaan;
import com.example.jasaku.model.KeranjangBelanja;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetilPesananActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.detil_pesanan_recyclerview)
    RecyclerView detilPesananRecyclerView;
    @BindView(R.id.lanjutkan)
    Button lanjutkanButton;

    private LinearLayoutManager llm;
    private DetilPesananAdapter adapter;
    private List<Belanjaan> belanjaanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_pesanan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        init();
    }

    private void init(){
        llm=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        detilPesananRecyclerView.setLayoutManager(llm);
        adapter=new DetilPesananAdapter(this);
        detilPesananRecyclerView.setAdapter(adapter);

        lanjutkanButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, DataPemesanActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeranjangBelanja.getBelanjaanList().clear();
    }
}
