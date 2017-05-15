package com.example.jasaku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.Belanjaan;
import com.example.jasaku.model.KeranjangBelanja;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by light on 19/04/17.
 */

public class DetilPesananAdapter extends RecyclerView.Adapter<DetilPesananAdapter.DetilPesananViewHolder> {

    private Context context;

    public DetilPesananAdapter(Context context){
        this.context=context;
    }

    @Override
    public DetilPesananAdapter.DetilPesananViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.detil_pesanan_item,parent,false);
        return new DetilPesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetilPesananAdapter.DetilPesananViewHolder holder, int position) {
        final Belanjaan belanjaan=KeranjangBelanja.getBelanjaanList().get(position);
        holder.namaJasaTextView.setText(belanjaan.getJasa().getNama());
        holder.hargaJasaTextView.setText("Rp "+String.valueOf(belanjaan.getJasa().getHarga()));
        holder.jumlahJasaEdittext.setText(String.valueOf(belanjaan.getKuantitas()));
        if(belanjaan.getKuantitas()<1)
            holder.kurangiJasaButton.setEnabled(false);
        holder.kurangiJasaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                belanjaan.kurangiKuantitas();
                holder.jumlahJasaEdittext.setText(String.valueOf(belanjaan.getKuantitas()));
            }
        });
        holder.tambahJasaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                belanjaan.tambahKuantitas();
                holder.jumlahJasaEdittext.setText(String.valueOf(belanjaan.getKuantitas()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return KeranjangBelanja.getBelanjaanList().size();
    }

    public class DetilPesananViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.nama_jasa)
        TextView namaJasaTextView;
        @BindView(R.id.harga_jasa)
        TextView hargaJasaTextView;
        @BindView(R.id.jumlah_jasa)
        TextView jumlahJasaEdittext;
        @BindView(R.id.kurangi_jasa)
        ImageButton kurangiJasaButton;
        @BindView(R.id.tambah_jasa)
        ImageButton tambahJasaButton;


        public DetilPesananViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
