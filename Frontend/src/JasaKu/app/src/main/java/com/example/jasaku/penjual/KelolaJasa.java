package com.example.jasaku.penjual;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jasaku.MainActivity;
import com.example.jasaku.R;
import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanKelolaJasaActivityInterface;
import com.example.jasaku.model.Toko;
import com.example.jasaku.penjual.fragment.HalamanJasaFragment;
import com.example.jasaku.penjual.interfaces.UpdateJasaInterface;
import com.example.jasaku.penjual.presenter.UpdateJasaPresenter;
import com.example.jasaku.presenter.HalamanKelolaJasaActivityPresenter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        getToko();
        Toast.makeText(this,"Berhasil disimpan",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onDataInsertFailed() {
        Toast.makeText(this,"Gagal menambah jasa",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onJasaEdited() {
        getToko();
        Toast.makeText(this,"Berhasil disimpan",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onJasaEditFailed() {
        Toast.makeText(this,"Gagal memperbarui jasa",Toast.LENGTH_SHORT).show();
    }

    private void getToko(){
        SharedPreferences preferences=getSharedPreferences("jasaku",MODE_PRIVATE);
        String userId=preferences.getString("id_user",null);

        ServiceInterface serviceInterface= ServiceGenerator.createService(ServiceInterface.class);
        serviceInterface.getTokoByIdPengguna(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getTokoSuccess,this::getTokoFailed);
    }

    private void getTokoSuccess(List<Toko> tokoList){
        Toko toko=tokoList.get(0);

        Gson gson=new Gson();
        String tokoString=gson.toJson(toko);

        Intent intent=new Intent(this, EditTokoActivity.class);
        intent.putExtra("id_toko",toko.getId());
        intent.putExtra("nama_toko",toko.getNama());
        intent.putExtra("toko",tokoString);
        intent.putExtra("pos", "ada");

        startActivity(intent);
    }

    private void getTokoFailed(Throwable t){
        Toast.makeText(this,"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }
}
