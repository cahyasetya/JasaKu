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
import com.example.jasaku.penjual.adapter.PesananMasukAdapter;
import com.example.jasaku.penjual.interfaces.PesananMasukInterface;
import com.example.jasaku.penjual.presenter.PesananMasukPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PesananMasukFragment extends Fragment implements PesananMasukInterface{

    @BindView(R.id.jasa_recyclerview)
    RecyclerView jasaRecyclerView;

    private LinearLayoutManager llm;
    private PesananMasukAdapter adapter;
    private List<PesananMasuk> pesananMasukList;
    private String idToko;
    private PesananMasukPresenter presenter;

    public PesananMasukFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pesanan_masuk_penjual, container, false);

        ButterKnife.bind(this,view);

        idToko=getArguments().getString("id_toko");

        presenter=new PesananMasukPresenter(this);

        return view;
    }

    @Override
    public void onPesananMasukLoaded(List<PesananMasuk> pesananMasukList) {
        llm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        jasaRecyclerView.setLayoutManager(llm);
        this.pesananMasukList=pesananMasukList;
        adapter=new PesananMasukAdapter(getContext(),this.pesananMasukList, presenter);
        jasaRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onPesananMasukLoadFailed() {
        Toast.makeText(getContext(),"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPesananDiterima() {
        onResume();
        Toast.makeText(getContext(),"Pesanan diterima",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPesananDiterimaError() {
        Toast.makeText(getContext(),"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPesananDitolak() {
        onResume();
        Toast.makeText(getContext(),"Pesanan ditolak",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPesananDitolakError() {
        Toast.makeText(getContext(),"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getPesananMasuk(idToko);
    }
}
