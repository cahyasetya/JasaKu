package com.example.jasaku.penjual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jasaku.R;
import com.example.jasaku.interfaces.HalamanKelolaJasaActivityInterface;
import com.example.jasaku.presenter.HalamanKelolaJasaActivityPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KelolaJasa extends AppCompatActivity implements HalamanKelolaJasaActivityInterface{

    @BindView(R.id.editTextNamaBarang)
    EditText namaJasaEditText;
    @BindView(R.id.editTextHarga)
    EditText hargaEditText;

    HalamanKelolaJasaActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_jasa);

        ButterKnife.bind(this);

        presenter=new HalamanKelolaJasaActivityPresenter(this);
    }

    @OnClick(R.id.buttonSimpan)
    public void simpanJasa(){
        String idToko="1";
        String namaJasa=namaJasaEditText.getText().toString();
        String hargaJasa=hargaEditText.getText().toString();

        Map<String, String> fields=new HashMap<>();
        fields.put("id_toko",idToko);
        fields.put("nama",namaJasa);
        fields.put("harga",hargaJasa);

        presenter.insertJasa(fields);

    }

    @Override
    public void onDataInserted() {
        Toast.makeText(this,"Berhasil disimpan",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataInsertFailed() {
        Toast.makeText(this,"Gangguan server",Toast.LENGTH_SHORT).show();
    }
}
