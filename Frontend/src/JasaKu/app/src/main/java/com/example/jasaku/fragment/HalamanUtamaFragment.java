package com.example.jasaku.fragment;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jasaku.FilterActivity;
import com.example.jasaku.R;
import com.example.jasaku.adapter.TokoAdapter;
import com.example.jasaku.interfaces.HalamanUtamaFragmentInterfaces;
import com.example.jasaku.model.Toko;
import com.example.jasaku.presenter.HalamanUtamaFragmentPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HalamanUtamaFragment extends Fragment implements HalamanUtamaFragmentInterfaces, SearchView.OnQueryTextListener{

    @BindView(R.id.toko_recyclerview)
    RecyclerView tokoRecyclerView;
    @BindView(R.id.toko_searchview)
    SearchView tokoSearchview;

    private List<Toko> tokoList;
    TokoAdapter adapter;
    LinearLayoutManager llm;
    HalamanUtamaFragmentPresenter presenter;

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

        presenter=new HalamanUtamaFragmentPresenter(this);

        return view;
    }

    private void init(){
        tokoSearchview.onActionViewExpanded();
        tokoSearchview.clearFocus();
        tokoSearchview.setOnQueryTextListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.halaman_utama_menu,menu);

        //custom sort icon
        /*MenuItem sort=menu.findItem(R.id.sort);
        Drawable sortIcon=sort.getIcon();
        sortIcon.mutate().setColorFilter(ContextCompat.getColor(getContext(),R.color.white), PorterDuff.Mode.SRC_IN);
        sort.setIcon(sortIcon);

        //custom filterToko icon
        MenuItem filter=menu.findItem(R.id.filter);
        Drawable filterIcon=filter.getIcon();
        filterIcon.mutate().setColorFilter(ContextCompat.getColor(getContext(),R.color.white),PorterDuff.Mode.SRC_IN);
        filter.setIcon(filterIcon);*/
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.filter:
                startActivity(new Intent(getContext(), FilterActivity.class));
                break;
            case R.id.harga_ascending:
                Map<String, String> q=new HashMap<>();
                q.put("sort","harga_asc");
                presenter.filterToko(q);
                break;
            case R.id.harga_descending:
                q=new HashMap<>();
                q.put("sort","harga_dsc");
                presenter.filterToko(q);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getArguments()!=null) {
            String idKategori = getArguments().getString("id_kategori", null);
            String idKecamatan = getArguments().getString("id_kecamatan", null);
            String sort = getArguments().getString("sort", null);
            if (idKategori == null && idKecamatan == null)
                presenter.loadData();
            else {
                Map<String, String> fields = new HashMap<>();
                if (idKategori != null)
                    fields.put("id_kategori", idKategori);
                if (idKecamatan != null)
                    fields.put("id_kecamatan", idKecamatan);
                if (sort != null)
                    fields.put("sort", sort);
                presenter.filterToko(fields);
            }
        }else {
            presenter.loadData();
        }
    }

    @Override
    public void onDataLoaded(List<Toko> tokoList) {
        this.tokoList=tokoList;
        llm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        adapter=new TokoAdapter(getContext(), this.tokoList,1);
        tokoRecyclerView.setLayoutManager(llm);
        tokoRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDataLoadError(Throwable t) {
        Toast.makeText(getContext(),"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.searchToko(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
