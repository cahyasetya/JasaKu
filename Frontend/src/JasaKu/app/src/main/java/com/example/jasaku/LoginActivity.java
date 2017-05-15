package com.example.jasaku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jasaku.interfaces.HalamanLoginActivityInterface;
import com.example.jasaku.presenter.HalamanLoginActivityPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements HalamanLoginActivityInterface {

    @BindView(R.id.buttonLogin) Button buttonLogin;
    @BindView(R.id.textViewRegister) TextView textViewRegister;
    @BindView(R.id.editTextUsername) EditText editTextUsername;
    @BindView(R.id.editTextPassword) EditText editTextPassword;

    HalamanLoginActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        presenter=new HalamanLoginActivityPresenter(this);
    }

    @OnClick(R.id.buttonLogin)
    public void makeLogin(){
        Map<String,String> fields=new HashMap<>();
        fields.put("username",editTextUsername.getText().toString());
        fields.put("password",editTextPassword.getText().toString());
        presenter.login(fields);
    }

    @OnClick(R.id.textViewRegister)
    public void makeRegister(){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void onLoginError() {
        Toast.makeText(this,"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }
}
