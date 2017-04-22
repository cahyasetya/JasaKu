package com.example.jasaku.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
public class HalamanUtamaFragment extends Fragment {

    @BindView(R.id.toko_recyclerview)
    RecyclerView tokoRecyclerView;

    private List<Toko> tokoList;
    TokoAdapter adapter;
    LinearLayoutManager llm;

    public HalamanUtamaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_halaman_utama, container, false);

        ButterKnife.bind(this,view);

        init();

        return view;
    }

    private void init(){
        llm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        tokoList=new ArrayList<>();
        Toko toko=new Toko();
        toko.setNama("Toko Ibu Bejo");
        toko.setDeskripsi("Menjual serabi khas Solo");
        for(int i=0; i<10; i++){
            tokoList.add(toko);
        }
        adapter=new TokoAdapter(getContext(), tokoList,1);
        tokoRecyclerView.setLayoutManager(llm);
        tokoRecyclerView.setAdapter(adapter);
    }

}
