package com.example.jasaku.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jasaku.R;
import com.example.jasaku.adapter.TokoAdapter;
import com.example.jasaku.model.Toko;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class KelolaTokoFragment extends Fragment {

    @BindView(R.id.toko_recyclerview)
    RecyclerView tokoRecyclerView;

    private LinearLayoutManager llm;
    private GridLayoutManager glm;
    private TokoAdapter adapter;
    private List<Toko> tokoList;

    public KelolaTokoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_kelola_toko, container, false);
        ButterKnife.bind(this,view);

        init();

        return view;
    }

    private void init(){
        glm=new GridLayoutManager(getContext(),2);
        llm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        tokoRecyclerView.setLayoutManager(glm);
        tokoList=new ArrayList<>();
        Toko toko=new Toko();
        toko.setNama("Toko Untung Jaya");
        toko.setDeskripsi("Menjual berbagai kebutuhan sehari-hari");
        for(int i=0; i<20; i++){
            tokoList.add(toko);
        }
        adapter=new TokoAdapter(getContext(),tokoList,2);
        tokoRecyclerView.setAdapter(adapter);
    }

}
