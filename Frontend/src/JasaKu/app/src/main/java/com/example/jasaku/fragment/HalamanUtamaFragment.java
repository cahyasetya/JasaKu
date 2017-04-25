package com.example.jasaku.fragment;


import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    @BindView(R.id.toko_searchview)
    SearchView tokoSearchview;

    private List<Toko> tokoList;
    TokoAdapter adapter;
    LinearLayoutManager llm;

    public HalamanUtamaFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
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

        tokoSearchview.onActionViewExpanded();
        tokoSearchview.clearFocus();

        llm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        tokoList=new ArrayList<>();
        Toko toko=new Toko();
        toko.setNama("Toko Ibu Bejo");
        toko.setDeskripsi("Menjual serabi khas Solo");
        toko.setAlamat("Keputih Gg. 3f, Sukolilo, Surabaya");
        toko.setHargaMinimal(5000);
        for(int i=0; i<10; i++){
            tokoList.add(toko);
        }
        adapter=new TokoAdapter(getContext(), tokoList,1);
        tokoRecyclerView.setLayoutManager(llm);
        tokoRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.halaman_utama_menu,menu);

        //custom sort icon
        MenuItem sort=menu.findItem(R.id.sort);
        Drawable sortIcon=sort.getIcon();
        sortIcon.mutate().setColorFilter(ContextCompat.getColor(getContext(),R.color.white), PorterDuff.Mode.SRC_IN);
        sort.setIcon(sortIcon);

        //custom filter icon
        MenuItem filter=menu.findItem(R.id.filter);
        Drawable filterIcon=filter.getIcon();
        filterIcon.mutate().setColorFilter(ContextCompat.getColor(getContext(),R.color.white),PorterDuff.Mode.SRC_IN);
        filter.setIcon(filterIcon);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
