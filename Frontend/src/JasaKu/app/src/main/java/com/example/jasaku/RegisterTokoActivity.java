package com.example.jasaku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterTokoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_toko);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}