package com.example.jasaku.penjual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jasaku.R;
import com.example.jasaku.interfaces.HalamanKelolaJasaActivityInterface;
import com.example.jasaku.penjual.fragment.HalamanJasaFragment;
import com.example.jasaku.penjual.interfaces.UpdateJasaInterface;
import com.example.jasaku.penjual.presenter.UpdateJasaPresenter;
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
    String idToko;

    HalamanKelolaJasaActivityPresenter presenter;
    UpdateJasaPresenter presenterUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_jasa);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            idToko = bundle.getString("jasaIdToko");

            Map<String, String> fields = new HashMap<>();
            fields.put("id_toko", bundle.getString("jasaIdToko"));
            fields.put("nama", namaJasaEditText.getText().toString());
            fields.put("harga", hargaEditText.getText().toString());

            presenter.insertJasa(fields);
        }
    }

    @Override
    public void onDataInserted() {
        Toast.makeText(this,idToko+" "+namaJasaEditText.getText()+" "+hargaEditText.getText(),Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Berhasil disimpan",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HalamanJasaFragment.class));
        finish();
    }

    @Override
    public void onDataInsertFailed() {
        Toast.makeText(this,idToko+namaJasaEditText.getText()+hargaEditText.getText(),Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Gangguan server",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onJasaEdited() {
        Toast.makeText(this,IdJasa+" "+namaJasaEditText.getText()+" "+hargaEditText.getText(),Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HalamanJasaFragment.class));
        Toast.makeText(this,"Berhasil diupdate",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onJasaEditFailed() {
        Toast.makeText(this,IdJasa+namaJasaEditText.getText()+hargaEditText.getText(),Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Gagal memperbarui jasa",Toast.LENGTH_SHORT).show();
    }
}
