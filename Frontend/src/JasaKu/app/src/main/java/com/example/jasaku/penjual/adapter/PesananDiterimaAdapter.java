package com.example.jasaku.penjual.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.PesananMasuk;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by light on 18/04/17.
 */

public class PesananDiterimaAdapter extends RecyclerView.Adapter<PesananDiterimaAdapter.PesanDiterimaViewHolder>{

    private Context context;
    private List<PesananMasuk> pesananMasukList;

    public PesananDiterimaAdapter(Context context, List<PesananMasuk> pesananMasukList){
        this.context=context;
        this.pesananMasukList = pesananMasukList;
    }

    @Override
    public PesanDiterimaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pesanan_diterima_item,parent,false);
        return new PesanDiterimaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PesanDiterimaViewHolder holder, int position) {
        PesananMasuk pesananMasuk= pesananMasukList.get(position);
        holder.namaJasaTextView.setText(pesananMasuk.getNama());
        holder.hargaJasaTextView.setText(String.valueOf(pesananMasuk.getHarga()));
        holder.alamatPembeliTextView.setText(pesananMasuk.getPembeli().getAlamat());
        holder.namaPembeliTextView.setText(pesananMasuk.getPembeli().getNama());
        holder.kontakPembeliTextView.setText(pesananMasuk.getPembeli().getKontak());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.pembeliContainer.getVisibility()==View.VISIBLE)
                    holder.pembeliContainer.setVisibility(View.GONE);
                else
                    holder.pembeliContainer.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pesananMasukList.size();
    }

    public class PesanDiterimaViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.nama_jasa)
        TextView namaJasaTextView;
        @BindView(R.id.harga_jasa)
        TextView hargaJasaTextView;
        @BindView(R.id.alamat_pembeli)
        TextView alamatPembeliTextView;
        @BindView(R.id.nama_pembeli)
        TextView namaPembeliTextView;
        @BindView(R.id.kontak_pembeli)
        TextView kontakPembeliTextView;
        @BindView(R.id.pembeli_container)
        LinearLayout pembeliContainer;

        public PesanDiterimaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

