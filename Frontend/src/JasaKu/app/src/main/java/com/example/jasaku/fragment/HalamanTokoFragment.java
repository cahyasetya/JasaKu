package com.example.jasaku.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.Toko;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HalamanTokoFragment extends Fragment {

    View view;

    @BindView(R.id.textViewNamaToko)
    TextView namaTokoTextView;
    @BindView(R.id.textViewDescription)
    TextView deskripsiTokoTextView;
    @BindView(R.id.textViewAlamat)
    TextView alamatTextView;
    @BindView(R.id.textViewKontak)
    TextView kontakTextView;
    @BindView(R.id.textViewBuka)
    TextView jamOperasionalTextView;

    public HalamanTokoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_halaman_toko, container, false);
        ButterKnife.bind(this,view);

        init();

        return view;
    }

    private void init(){
        Toko toko=(Toko)getArguments().getSerializable("toko");

        namaTokoTextView.setText(toko.getNama());
        deskripsiTokoTextView.setText(toko.getDeskripsi());
        alamatTextView.setText(toko.getAlamat());
        kontakTextView.setText(toko.getKontak());
        jamOperasionalTextView.setText(toko.getJamOperasional());
    }
}
