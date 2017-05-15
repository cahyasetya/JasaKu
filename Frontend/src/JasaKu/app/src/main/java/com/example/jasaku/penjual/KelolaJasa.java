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
import com.example.jasaku.penjual.interfaces.UpdateJasaInterface;
import com.example.jasaku.penjual.presenter.UpdateJasaPresenter;
import com.example.jasaku.presenter.HalamanJasaFragmentPenjualPresenter;
import com.example.jasaku.presenter.HalamanKelolaJasaActivityPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KelolaJasa extends AppCompatActivity implements HalamanKelolaJasaActivityInterface, UpdateJasaInterface {

    @BindView(R.id.textViewJudul)
    TextView textViewJudul;
    @BindView(R.id.editTextNamaBarang)
    EditText namaJasaEditText;
    @BindView(R.id.editTextHarga)
    EditText hargaEditText;

    Integer update;
    String IdJasa;
    String namaJasa;
    String hargaJasa;

    HalamanKelolaJasaActivityPresenter presenter;
    UpdateJasaPresenter presenterUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_jasa);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        update = bundle.getInt("update");

        if (update == 1){
            ifUpdateJasa(bundle);
        }

        presenterUpdate = new UpdateJasaPresenter(this);
        presenter=new HalamanKelolaJasaActivityPresenter(this);

    }

    public void ifUpdateJasa(Bundle bundle) {
        namaJasa = bundle.getString("jasaNama");
        hargaJasa = bundle.getString("jasaHarga");

        textViewJudul.setText("Perbarui Jasa");
        namaJasaEditText.setText(namaJasa);
        hargaEditText.setText(String.valueOf(hargaJasa));
    }

    @OnClick(R.id.buttonSimpan)
    public void simpanJasa(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        update = bundle.getInt("update");

        if(update == 1){
            IdJasa = bundle.getString("jasaId");
            String fixNamaJasa = namaJasaEditText.getText().toString();
            String fixHargaJasa = hargaEditText.getText().toString();

            Map<String, String> fields = new HashMap<>();
            fields.put("id", IdJasa);
            fields.put("nama", fixNamaJasa);
            fields.put("harga", fixHargaJasa);

            presenterUpdate.ubahJasa(fields);
        }
        else {
            String idToko = bundle.getString("jasaIdToko");
            namaJasa = namaJasaEditText.getText().toString();
            hargaJasa = hargaEditText.getText().toString();

            Map<String, String> fields = new HashMap<>();
            fields.put("id_toko", idToko);
            fields.put("nama", namaJasa);
            fields.put("harga", hargaJasa);

            presenter.insertJasa(fields);
        }
    }

    @Override
    public void onDataInserted() {
        Toast.makeText(this,"Berhasil disimpan",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataInsertFailed() {
        Toast.makeText(this,"Gangguan server",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onJasaEdited() {
        Toast.makeText(this,"Berhasil diupdate",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onJasaEditFailed() {
        Toast.makeText(this,"Gagal memperbarui jasa",Toast.LENGTH_SHORT).show();
    }
}
