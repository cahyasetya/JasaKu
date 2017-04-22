package com.example.jasaku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.buttonLogin) Button buttonLogin;
    @BindView(R.id.textViewRegister) TextView textViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.buttonLogin)
    public void makeLogin(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @OnClick(R.id.textViewRegister)
    public void makeRegister(){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}
