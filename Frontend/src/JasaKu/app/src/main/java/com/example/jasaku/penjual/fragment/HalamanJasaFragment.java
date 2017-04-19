package com.example.jasaku.penjual.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jasaku.R;
import com.example.jasaku.model.Jasa;
import com.example.jasaku.penjual.KelolaJasa;
import com.example.jasaku.penjual.adapter.JasaAdapter;
import com.example.jasaku.penjual.adapter.PesananMasukAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HalamanJasaFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.jasa_recyclerview)
    RecyclerView jasaRecyclerView;
    @BindView(R.id.tambah_jasa)
    Button tambahJasaButton;

    private LinearLayoutManager llm;
    private JasaAdapter adapter;
    private List<Jasa> jasaList;

    public HalamanJasaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_halaman_jasa_penjual, container, false);
        ButterKnife.bind(this,view);

        init();

        return view;
    }

    private void init(){
        llm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        jasaRecyclerView.setLayoutManager(llm);
        jasaList=new ArrayList<>();
        Jasa jasa=new Jasa();
        jasa.setNama("Gayung");
        jasa.setHarga(5000);
        for(int i=0; i<20; i++){
            jasaList.add(jasa);
        }
        adapter=new JasaAdapter(getContext(),jasaList);
        jasaRecyclerView.setAdapter(adapter);

        tambahJasaButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), KelolaJasa.class));
    }
}
