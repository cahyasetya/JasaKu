package com.example.jasaku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlmLoginActivity extends AppCompatActivity {

    @BindView(R.id.buttonLogin) Button buttonLogin;
    @BindView(R.id.buttonRegister) Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blm_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.buttonLogin)
    public void toLoginPage() {
        startActivity(new Intent(BlmLoginActivity.this, LoginActivity.class));
    }

    @OnClick(R.id.buttonRegister)
    public void toRegisterPage() {
        startActivity(new Intent(BlmLoginActivity.this, RegisterActivity.class));
    }
}
