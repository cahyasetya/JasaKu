package com.example.jasaku.penjual.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jasaku.R;
import com.example.jasaku.model.PesananMasuk;
import com.example.jasaku.penjual.adapter.PesananDiterimaAdapter;
import com.example.jasaku.penjual.interfaces.PesananDiterimaInterfaces;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PesananDiterimaFragment extends Fragment implements PesananDiterimaInterfaces{

    @BindView(R.id.jasa_recyclerview)
    RecyclerView jasaRecyclerView;

    private LinearLayoutManager llm;
    private PesananDiterimaAdapter adapter;
    private List<PesananMasuk> pesananMasukList;

    public PesananDiterimaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pesanan_diterima_penjual, container, false);

        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onDataLoaded(List<PesananMasuk> pesananMasukList) {
        llm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        jasaRecyclerView.setLayoutManager(llm);
        this.pesananMasukList=pesananMasukList;
        adapter=new PesananDiterimaAdapter(getContext(), this.pesananMasukList);
        jasaRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDataLoadFailed() {
        Toast.makeText(getContext(),"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }
}
