package com.example.jasaku;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.jasaku.model.Pengguna;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.editTextKontak)
    TextView editTextKontak;
    @BindView(R.id.editTextAlamat)
    TextView getEditTextAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        init();

    }

    private void init(){
        SharedPreferences preferences=getSharedPreferences("jasaku",MODE_PRIVATE);

        Gson gson=new Gson();

        Pengguna pengguna=gson.fromJson(preferences.getString("pengguna",null),Pengguna.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout.setTitle(pengguna.getNama());

        Context context = this;
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(context, R.color.colorPrimary));

        toolbarTextAppearance();

        editTextKontak.setText(pengguna.getKontak());
        getEditTextAlamat.setText(pengguna.getAlamat());
    }

    private void toolbarTextAppearance(){
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }
}
