package com.example.jasaku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jasaku.adapter.KabupatenAdapter;
import com.example.jasaku.adapter.KategoriSpinner;
import com.example.jasaku.adapter.KecamatanAdapter;
import com.example.jasaku.adapter.ProvinsiAdapter;
import com.example.jasaku.api.ServiceGenerator;
import com.example.jasaku.api.ServiceInterface;
import com.example.jasaku.interfaces.HalamanRegisterTokoActivityInterface;
import com.example.jasaku.model.Kabupaten;
import com.example.jasaku.model.Kategori;
import com.example.jasaku.model.Kecamatan;
import com.example.jasaku.model.Pengguna;
import com.example.jasaku.model.Provinsi;
import com.example.jasaku.model.Toko;
import com.example.jasaku.penjual.EditTokoActivity;
import com.example.jasaku.presenter.HalamanRegisterTokoActivityPresenter;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterTokoActivity extends AppCompatActivity implements HalamanRegisterTokoActivityInterface{

    @BindView(R.id.editTextNamaToko)
    EditText etNamaToko;
    @BindView(R.id.editTextDeskripsi)
    EditText etDeskripsi;
    @BindView(R.id.editTextAlamat)
    EditText etAlamat;
    @BindView(R.id.editTextKontak)
    EditText etKontak;
    @BindView(R.id.editTextBuka)
    EditText etBuka;
    @BindView(R.id.textViewProvinsi)
    TextView tvProv;
    @BindView(R.id.provinsi_spinner)
    Spinner provinsiSpinner;
    @BindView(R.id.textViewKabupaten)
    TextView tvKab;
    @BindView(R.id.kabupaten_spinner)
    Spinner kabupatenSpinner;
    @BindView(R.id.textViewKecamatan)
    TextView tvKec;
    @BindView(R.id.kecamatan_spinner)
    Spinner kecamatanSpinner;
    @BindView(R.id.kategori_spinner)
    Spinner kategoriSpinner;

    public String id_kategori;
    public String id_user;
    public String id_kecamatan;

    HalamanRegisterTokoActivityPresenter presenter;
    List<Kategori> kategoriList;
    //private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_toko);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        //pd.setMessage("Harap menunggu...");
        //pd.setCancelable(false);

        presenter=new HalamanRegisterTokoActivityPresenter(this);
        //pd.show();
        presenter.loadKategori();
    }

    @OnClick(R.id.buttonBuatToko)
    public void buatToko(){
        SharedPreferences preferences=getSharedPreferences("jasaku",MODE_PRIVATE);
        Gson gson=new Gson();
        Pengguna pengguna=gson.fromJson(preferences.getString("pengguna",null),Pengguna.class);
        id_user = String.valueOf(pengguna.getId());

        Map<String, String> fields=new HashMap<>();
        fields.put("nama", etNamaToko.getText().toString());
        fields.put("alamat", etAlamat.getText().toString());
        fields.put("kontak", etKontak.getText().toString());
        fields.put("deskripsi", etDeskripsi.getText().toString());
        fields.put("jamOperasional", etBuka.getText().toString());
        fields.put("id_pengguna", id_user);
        fields.put("id_kategori", id_kategori);
        fields.put("id_kecamatan", id_kecamatan);

        presenter.buatToko(fields);
    }

    @Override
    public void onKategoriLoadedSuccessfully(List<Kategori> kategoriList) {
        this.kategoriList=kategoriList;
        Kategori kategori = new Kategori();
        kategori.setId(-1);
        kategori.setNama("-");
        kategoriList.add(0,kategori);
        KategoriSpinner spinner=new KategoriSpinner(this,this.kategoriList);
        kategoriSpinner.setAdapter(spinner);
        kategoriSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_kategori = String.valueOf(id);
                presenter.loadProvinsi();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //pd.dismiss();
    }

    @Override
    public void onKategoriLoadFailed() {
        Toast.makeText(this,"Gangguan Jaringan",Toast.LENGTH_SHORT).show();
        //pd.dismiss();
    }

    @Override
    public void onBuatTokoSuccessful() {
        Toast.makeText(this,"Berhasil",Toast.LENGTH_SHORT).show();
        getToko();
    }

    @Override
    public void onBuatTokoFailed() {
        Toast.makeText(this,"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadFailed(Throwable t) {
        if(((HttpException)t).response().code()!=400) {
            Toast.makeText(this, "Gangguan jaringan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProvinsiLoaded(List<Provinsi> provinsiList) {
        if (!id_kategori.equals("-1")){
            tvProv.setVisibility(View.VISIBLE);
            provinsiSpinner.setVisibility(View.VISIBLE);
            ProvinsiAdapter adapter=new ProvinsiAdapter(this, provinsiList);
            provinsiSpinner.setAdapter(adapter);
            provinsiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    presenter.loadKabupaten(String.valueOf(id));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    @Override
    public void onKabupatenLoaded(List<Kabupaten> kabupatenList) {
        if(kabupatenList.size()>0){
            KabupatenAdapter adapter=new KabupatenAdapter(this,kabupatenList);
            kabupatenSpinner.setAdapter(adapter);
            tvKab.setVisibility(View.VISIBLE);
            kabupatenSpinner.setVisibility(View.VISIBLE);
            kabupatenSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    presenter.loadKecamatan(String.valueOf(id));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }else{
            Toast.makeText(this,"Tidak ada penyedia jasa terkait di daerah yang dipilih",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onKecamatanLoaded(List<Kecamatan> kecamatanList) {
        if(kecamatanList.size()>0) {
            KecamatanAdapter adapter = new KecamatanAdapter(this, kecamatanList);
            kecamatanSpinner.setAdapter(adapter);
            tvKec.setVisibility(View.VISIBLE);
            kecamatanSpinner.setVisibility(View.VISIBLE);
            kecamatanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    id_kecamatan = String.valueOf(id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }else{
            Toast.makeText(this,"Tidak ada penyedia jasa terkait di daerah yang dipilih",Toast.LENGTH_SHORT).show();
        }
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
