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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HalamanUtamaFragment extends Fragment implements HalamanUtamaFragmentInterfaces{

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.filter:
                startActivity(new Intent(getContext(), FilterActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadData();
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
}
