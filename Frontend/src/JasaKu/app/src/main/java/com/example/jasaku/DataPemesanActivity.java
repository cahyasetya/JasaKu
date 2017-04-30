package com.example.jasaku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataPemesanActivity extends AppCompatActivity {

    @BindView(R.id.buttonGantiAlamat)
    Button buttonGantiAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pemesan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonGantiAlamat)
    public void makeNewAlamat(){
        startActivity(new Intent(DataPemesanActivity.this, TambahAlamatActivity.class));
    }
}
