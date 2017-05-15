package com.example.jasaku.penjual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jasaku.R;
import com.example.jasaku.interfaces.HalamanJasaFragmentPenjualInterface;
import com.example.jasaku.interfaces.HalamanKelolaJasaActivityInterface;
import com.example.jasaku.presenter.HalamanJasaFragmentPenjualPresenter;
import com.example.jasaku.presenter.HalamanKelolaJasaActivityPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KelolaJasa extends AppCompatActivity implements HalamanKelolaJasaActivityInterface{

    @BindView(R.id.textViewJudul)
    TextView textViewJudul;
    @BindView(R.id.editTextNamaBarang)
    EditText namaJasaEditText;
    @BindView(R.id.editTextHarga)
    EditText hargaEditText;

    String IdJasa;
    String namaJasa;
    String hargaJasa;

    HalamanKelolaJasaActivityPresenter presenter;
    private HalamanJasaFragmentPenjualPresenter presenterUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_jasa);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            ifUpdateJasa(bundle);
            //presenterUpdate = new HalamanJasaFragmentPenjualPresenter(this);
        }
        else{
            namaJasa = namaJasaEditText.getText().toString();
            hargaJasa = hargaEditText.getText().toString();
            presenter=new HalamanKelolaJasaActivityPresenter(this);
        }
    }

    public void ifUpdateJasa(Bundle bundle) {
        String idToko="1";
        String idJasa = bundle.getString("jasaId");
        namaJasa = bundle.getString("jasaNama");
        hargaJasa = bundle.getString("jasaHarga");

        textViewJudul.setText("Perbarui Jasa");
        namaJasaEditText.setText(namaJasa);
        hargaEditText.setText(hargaJasa);

        Button buttonSimpan = (Button) findViewById(R.id.buttonSimpan);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String fixNamaJasa = namaJasaEditText.getText().toString();
                String fixHargaJasa = hargaEditText.getText().toString();

                Map<String, String> fields = new HashMap<>();
                fields.put("id_toko", idToko);
                fields.put("nama", fixNamaJasa);
                fields.put("harga", fixHargaJasa);

                //presenter.ubahJasa(fields);
            }
        });
    }

    @OnClick(R.id.buttonSimpan)
    public void simpanJasa(){
        String idToko="1";

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
