package com.example.jasaku.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jasaku.DetilTokoActivity;
import com.example.jasaku.R;
import com.example.jasaku.model.Toko;
import com.example.jasaku.penjual.EditTokoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by light on 17/04/17.
 */

public class TokoAdapter extends RecyclerView.Adapter<TokoAdapter.TokoViewHolder> {

    private Context context;
    private List<Toko> tokoList;
    private int mode;

    public TokoAdapter(Context context, List<Toko> tokoList,int mode) {
        this.context=context;
        this.tokoList=tokoList;
        this.mode=mode;
    }

    @Override
    public TokoAdapter.TokoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.toko_item,parent,false);
        return new TokoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TokoAdapter.TokoViewHolder holder, int position) {
        Toko toko=tokoList.get(position);
        holder.namaTokoTextView.setText(toko.getNama());
        holder.deskripsiTokoTextView.setText(toko.getDeskripsi());
        holder.alamatToko.setText(toko.getAlamat());
        holder.hargaMinimal.setText(String.valueOf(toko.getHargaMinimal()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mode==1)
                    context.startActivity(new Intent(context, DetilTokoActivity.class));
                else
                    context.startActivity(new Intent(context, EditTokoActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return tokoList.size();
    }

    public class TokoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama_toko)
        TextView namaTokoTextView;
        @BindView(R.id.deskripsi_toko)
        TextView deskripsiTokoTextView;
        @BindView(R.id.alamat_toko)
        TextView alamatToko;
        @BindView(R.id.harga_minimal)
        TextView hargaMinimal;
        public TokoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
