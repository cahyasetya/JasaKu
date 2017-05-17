package com.example.jasaku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jasaku.interfaces.HalamanRegisterActivityInterface;
import com.example.jasaku.presenter.HalamanRegistereActivityPresenter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements HalamanRegisterActivityInterface{

    @BindView(R.id.textViewLogin)
    TextView textViewLogin;
    @BindView(R.id.editTextUsername)
    EditText editTextUsername;
    @BindView(R.id.editTextNama)
    EditText editTextNama;
    @BindView(R.id.editTextGender)
    EditText editTextJenisKelamin;
    @BindView(R.id.editTextKontak)
    EditText editTextKontak;
    @BindView(R.id.editTextAlamat)
    EditText editTextAlamat;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    HalamanRegistereActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        presenter=new HalamanRegistereActivityPresenter(this);
    }

    @OnClick(R.id.textViewLogin)
    public void backToLogin(){
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    @OnClick(R.id.buttonRegister)
    public void register(View v){
        Map<String, String> field=new HashMap<>();
        field.put("nama",editTextNama.getText().toString());
        field.put("alamat",editTextAlamat.getText().toString());
        field.put("jenisKelamin",editTextJenisKelamin.getText().toString());
        field.put("kontak",editTextKontak.getText().toString());
        field.put("username",editTextUsername.getText().toString());
        field.put("password",editTextPassword.getText().toString());
        field.put("token", FirebaseInstanceId.getInstance().getToken());
        presenter.register(field);
    }

    @Override
    public void onRegisterSuccessful() {
        Toast.makeText(this, "Anda berhasil terdaftar",Toast.LENGTH_SHORT).show();
        backToLogin();
    }

    @Override
    public void onRegisterFailed(Throwable t) {
        Toast.makeText(this,"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }
}
