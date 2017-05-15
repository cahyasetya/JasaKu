package com.example.jasaku.penjual.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jasaku.R;
import com.example.jasaku.model.Jasa;
import com.example.jasaku.model.PesananMasuk;
import com.example.jasaku.penjual.adapter.PesananDiterimaAdapter;
import com.example.jasaku.penjual.adapter.PesananMasukAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PesananDiterimaFragment extends Fragment {

    @BindView(R.id.jasa_recyclerview)
    RecyclerView jasaRecyclerView;

    private LinearLayoutManager llm;
    private PesananDiterimaAdapter adapter;
    private List<PesananMasuk> jasaList;

    public PesananDiterimaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pesanan_diterima_penjual, container, false);

        ButterKnife.bind(this,view);

        //init();

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
            //jasaList.add(jasa);
        }
        adapter=new PesananDiterimaAdapter(getContext(),jasaList);
        jasaRecyclerView.setAdapter(adapter);
    }

}
