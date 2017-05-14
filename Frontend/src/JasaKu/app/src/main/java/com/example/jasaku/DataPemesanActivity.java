package com.example.jasaku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jasaku.interfaces.HalamanDataPemesanActivityInterface;
import com.example.jasaku.model.Belanjaan;
import com.example.jasaku.model.KeranjangBelanja;
import com.example.jasaku.model.Paket;
import com.example.jasaku.model.RequestMembeli;
import com.example.jasaku.presenter.HalamanDataPemesanActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataPemesanActivity extends AppCompatActivity implements HalamanDataPemesanActivityInterface{

    @BindView(R.id.buttonGantiAlamat)
    Button buttonGantiAlamat;

    HalamanDataPemesanActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pemesan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        presenter=new HalamanDataPemesanActivityPresenter(this);
    }

    @OnClick(R.id.buttonGantiAlamat)
    public void makeNewAlamat(){
        startActivity(new Intent(DataPemesanActivity.this, TambahAlamatActivity.class));
    }

    @OnClick(R.id.buttonPesan)
    public void pesan(View v){
        prepareRequest();
    }

    private void prepareRequest(){
        SharedPreferences preferences=getSharedPreferences("jasaku", MODE_PRIVATE);
        String id=preferences.getString("id_pengguna",null);

        RequestMembeli requestMembeli=new RequestMembeli();
        requestMembeli.setId_pengguna(id);

        for(Belanjaan belanjaan: KeranjangBelanja.getBelanjaanList()){
            Paket paket=new Paket();
            paket.setId_jasa(belanjaan.getJasa().getId());
            paket.setKuantitas(belanjaan.getKuantitas());
            requestMembeli.addPaket(paket);
        }
        presenter.beli(requestMembeli);
    }

    @Override
    public void onPembelianSuccess() {
        Toast.makeText(this,"Pemesanan berhasil",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPembelianFailed() {
        Toast.makeText(this,"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }
}
