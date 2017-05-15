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

public class PesananMasukAdapter extends RecyclerView.Adapter<PesananMasukAdapter.PesananMasukViewHolder>{

    private Context context;
    private List<PesananMasuk> pesananMasukList;

    public PesananMasukAdapter(Context context, List<PesananMasuk> pesananMasukList){
        this.context=context;
        this.pesananMasukList = pesananMasukList;
    }

    @Override
    public PesananMasukViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pesanan_masuk_item,parent,false);
        return new PesananMasukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PesananMasukViewHolder holder, int position) {
        PesananMasuk pesananMasuk= pesananMasukList.get(position);
        holder.namaJasaTextView.setText(pesananMasuk.getNama());
        holder.hargaJasaTextView.setText(String.valueOf(pesananMasuk.getHarga()));
        holder.namaPembeliTextView.setText(pesananMasuk.getPembeli().getNama());
        holder.kontakPembeliTextView.setText(pesananMasuk.getPembeli().getKontak());
        holder.alamatTextView.setText(pesananMasuk.getPembeli().getAlamat());
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

    public class PesananMasukViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.nama_jasa)
        TextView namaJasaTextView;
        @BindView(R.id.harga_jasa)
        TextView hargaJasaTextView;
        @BindView(R.id.nama_pembeli)
        TextView namaPembeliTextView;
        @BindView(R.id.kontak_pembeli)
        TextView kontakPembeliTextView;
        @BindView(R.id.pembeli_container)
        LinearLayout pembeliContainer;
        @BindView(R.id.alamat)
        TextView alamatTextView;

        public PesananMasukViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

