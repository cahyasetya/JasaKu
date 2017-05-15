package com.example.jasaku.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jasaku.DetilPesananActivity;
import com.example.jasaku.R;
import com.example.jasaku.interfaces.HalamanJasaFragmentInterfaces;
import com.example.jasaku.model.Belanjaan;
import com.example.jasaku.model.Jasa;
import com.example.jasaku.model.KeranjangBelanja;
import com.example.jasaku.presenter.HalamanJasaFragmentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HalamanJasaFragment extends Fragment implements View.OnClickListener,HalamanJasaFragmentInterfaces {

    @BindView(R.id.jasa_recyclerview)
    RecyclerView jasaRecyclerView;
    @BindView(R.id.pesan)
    Button pesanButton;

    private List<Jasa> jasaList;
    private LinearLayoutManager llm;
    private JasaAdapter adapter;

    public HalamanJasaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_halaman_jasa, container, false);
        ButterKnife.bind(this,view);

        String idToko=getArguments().getString("id_toko",null);

        if(idToko!=null){
            HalamanJasaFragmentPresenter presenter=new HalamanJasaFragmentPresenter(this);
            presenter.loadData(idToko);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        for(Jasa jasa:this.jasaList){
            if(jasa.isSelected()==true){
                Belanjaan belanjaan=new Belanjaan();
                belanjaan.setJasa(jasa);
                belanjaan.setKuantitas(1);
                KeranjangBelanja.tambahBelanjaan(belanjaan);
            }
        }
        getContext().startActivity(new Intent(getActivity(), DetilPesananActivity.class));
    }

    @Override
    public void onDataLoaded(List<Jasa> jasaList) {
        SharedPreferences preferences = getContext().getSharedPreferences("jasaku", MODE_PRIVATE);
        this.jasaList=jasaList;
        llm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        jasaRecyclerView.setLayoutManager(llm);
        adapter=new JasaAdapter(getContext(),this.jasaList);
        jasaRecyclerView.setAdapter(adapter);
        boolean isLoggedIn=preferences.getBoolean("isLoggedIn",false);
        if(isLoggedIn){
            pesanButton.setVisibility(View.VISIBLE);
            pesanButton.setOnClickListener(this);
        }
        else{
            pesanButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDataLoadError(Throwable t) {
        Toast.makeText(getContext(),"Gangguan jaringan",Toast.LENGTH_SHORT).show();
    }
}

class JasaAdapter extends RecyclerView.Adapter<JasaAdapter.JasaViewHolder>{

    private Context context;
    private List<Jasa> jasaList;

    public JasaAdapter(Context context, List<Jasa> jasaList){
        this.context=context;
        this.jasaList=jasaList;
    }

    @Override
    public JasaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.jasa_item,parent,false);
        return new JasaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JasaViewHolder holder, int position) {
        Jasa jasa=jasaList.get(position);
        holder.namaJasaTextView.setText(jasa.getNama());
        holder.hargaJasaTextView.setText(String.valueOf(jasa.getHarga()));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked())
                    jasa.setSelected(true);
                else
                    jasa.setSelected(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jasaList.size();
    }

    public class JasaViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.nama_jasa)
        TextView namaJasaTextView;
        @BindView(R.id.harga_jasa)
        TextView hargaJasaTextView;
        @BindView(R.id.checkBox)
        CheckBox checkBox;

        public JasaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
