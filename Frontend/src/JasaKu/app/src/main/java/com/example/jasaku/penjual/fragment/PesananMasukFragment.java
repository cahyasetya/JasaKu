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

    public PesananMasukFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pesanan_masuk_penjual, container, false);

        ButterKnife.bind(this,view);

        String idToko=getArguments().getString("id_toko");

        PesananMasukPresenter presenter=new PesananMasukPresenter(this);
        presenter.getPesananMasuk(idToko);

        return view;
    }

    @Override
    public void onPesananMasukLoaded(List<PesananMasuk> pesananMasukList) {
        llm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        jasaRecyclerView.setLayoutManager(llm);
        this.pesananMasukList=pesananMasukList;
        adapter=new PesananMasukAdapter(getContext(),this.pesananMasukList);
        jasaRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onPesananMasukLoadFailed() {
        Toast.makeText(getContext(),"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }
}
