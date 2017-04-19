package com.example.jasaku.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.Belanjaan;
import com.example.jasaku.model.KeranjangBelanja;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by light on 19/04/17.
 */

public class DetilPesananAdapter extends RecyclerView.Adapter<DetilPesananAdapter.DetilPesananViewHolder> {

    private Context context;
    private List<Belanjaan> belanjaanList;

    public DetilPesananAdapter(Context context, List<Belanjaan> belanjaanList){
        this.context=context;
        this.belanjaanList=belanjaanList;
    }

    @Override
    public DetilPesananAdapter.DetilPesananViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.detil_pesanan_item,parent,false);
        return new DetilPesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetilPesananAdapter.DetilPesananViewHolder holder, int position) {
        Belanjaan belanjaan=belanjaanList.get(position);
        holder.namaJasaTextView.setText(belanjaan.getJasa().getNama());
        holder.hargaJasaTextView.setText(String.valueOf(belanjaan.getJasa().getHarga()));
        holder.jumlahJasaEdittext.setText(String.valueOf(belanjaan.getKuantitas()));
    }

    @Override
    public int getItemCount() {
        return belanjaanList.size();
    }

    public class DetilPesananViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.nama_jasa)
        TextView namaJasaTextView;
        @BindView(R.id.harga_jasa)
        TextView hargaJasaTextView;
        @BindView(R.id.jumlah_jasa)
        EditText jumlahJasaEdittext;

        public DetilPesananViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
