package com.example.jasaku;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jasaku.adapter.KategoriSpinner;
import com.example.jasaku.interfaces.HalamanRegisterActivityInterface;
import com.example.jasaku.interfaces.HalamanRegisterTokoActivityInterface;
import com.example.jasaku.model.Kategori;
import com.example.jasaku.presenter.HalamanRegisterTokoActivityPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterTokoActivity extends AppCompatActivity implements HalamanRegisterTokoActivityInterface{

    @BindView(R.id.kategori_spinner)
    Spinner kategoriSpinner;

    HalamanRegisterTokoActivityPresenter presenter;
    List<Kategori> kategoriList;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_toko);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        pd.setMessage("Harap menunggu...");
        pd.setCancelable(false);

        presenter=new HalamanRegisterTokoActivityPresenter(this);
        pd.show();
        presenter.loadKategori();
    }

    @OnClick(R.id.buttonBuatToko)
    public void buatToko(){
        Map<String, String> fields=new HashMap<>();
        /*fields.put("nama",);
        fields.put("alamat",);
        fields.put("kontak",);
        fields.put("deskripsi",);
        fields.put("jamOperasional",);
        fields.put("id_pengguna",);
        fields.put("id_kategori",);
        fields.put("")*/
    }

    @Override
    public void onKategoriLoadedSuccessfully(List<Kategori> kategoriList) {
        this.kategoriList=kategoriList;
        KategoriSpinner spinner=new KategoriSpinner(this,this.kategoriList);
        kategoriSpinner.setAdapter(spinner);
        pd.dismiss();
    }

    @Override
    public void onKategoriLoadFailed() {
        Toast.makeText(this,"Gangguan Jaringan",Toast.LENGTH_SHORT).show();
        pd.dismiss();
    }

    @Override
    public void onBuatTokoSuccessful() {
        Toast.makeText(this,"Berhasil",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBuatTokoFailed() {
        Toast.makeText(this,"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }
}
