package com.example.jasaku.penjual.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jasaku.R;
import com.example.jasaku.interfaces.HalamanJasaFragmentPenjualInterface;
import com.example.jasaku.model.Jasa;
import com.example.jasaku.penjual.KelolaJasa;
import com.example.jasaku.penjual.adapter.JasaAdapter;
import com.example.jasaku.presenter.HalamanJasaFragmentPenjualPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HalamanJasaFragment extends Fragment implements View.OnClickListener,HalamanJasaFragmentPenjualInterface {

    @BindView(R.id.jasa_recyclerview)
    RecyclerView jasaRecyclerView;
    @BindView(R.id.tambah_jasa)
    Button tambahJasaButton;

    private LinearLayoutManager llm;
    private JasaAdapter adapter;
    private List<Jasa> jasaList;
    private Integer condition;
    private HalamanJasaFragmentPenjualPresenter presenter;

    public HalamanJasaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_halaman_jasa_penjual, container, false);
        ButterKnife.bind(this,view);

        String idToko=getArguments().getString("id_toko");

        presenter = new HalamanJasaFragmentPenjualPresenter(this);
        presenter.loadData(idToko);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent tmbhIntent = new Intent(getContext(), KelolaJasa.class);
        String idToko = getArguments().getString("id_toko");
        tmbhIntent.putExtra("jasaIdToko", idToko);
        startActivity(tmbhIntent);
    }

    @Override
    public void onDataLoaded(List<Jasa> jasaList) {
        llm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        jasaRecyclerView.setLayoutManager(llm);
        this.jasaList=jasaList;
        adapter=new JasaAdapter(getContext(),this.jasaList,presenter);
        jasaRecyclerView.setAdapter(adapter);
        tambahJasaButton.setOnClickListener(this);
    }

    @Override
    public void onDataLoadError(Throwable t) {
        t.printStackTrace();
        Toast.makeText(getContext(),"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onJasaDeleted() {
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(),"Jasa berhasil dihapus",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onJasaDeleteFailed() {
        Toast.makeText(getContext(),"Gangguan jaringan", Toast.LENGTH_SHORT).show();
    }
}
