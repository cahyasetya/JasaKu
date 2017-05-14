package com.example.jasaku.penjual.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.Toko;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HalamanTokoFragment extends Fragment {

    @BindView(R.id.textViewNamaToko)
    TextView namaTokoTextView;
    @BindView(R.id.editTextDescription)
    EditText deskripsiEditText;
    @BindView(R.id.editTextAlamat)
    EditText alamatEditText;
    @BindView(R.id.editTextKontak)
    EditText kontakEditText;
    @BindView(R.id.editTextBuka)
    EditText jamOperasionalEditText;

    public HalamanTokoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_halaman_toko_penjual, container, false);

        ButterKnife.bind(this,view);

        init();

        return view;
    }

    private void init(){
        Toko toko=(Toko)getArguments().getSerializable("toko");
        namaTokoTextView.setText(toko.getNama());
        deskripsiEditText.setText(toko.getDeskripsi());
        alamatEditText.setText(toko.getAlamat());
        kontakEditText.setText(toko.getKontak());
        jamOperasionalEditText.setText(toko.getJamOperasional());
    }

}
