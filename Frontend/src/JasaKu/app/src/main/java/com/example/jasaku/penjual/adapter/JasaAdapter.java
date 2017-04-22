package com.example.jasaku.penjual.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.Jasa;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by light on 18/04/17.
 */

public class JasaAdapter extends RecyclerView.Adapter<JasaAdapter.JasaViewHolder>{

    private Context context;
    private List<Jasa> jasaList;

    public JasaAdapter(Context context, List<Jasa> jasaList){
        this.context=context;
        this.jasaList=jasaList;
    }

    @Override
    public JasaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.jasa_item_penjual,parent,false);
        return new JasaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JasaViewHolder holder, int position) {
        Jasa jasa=jasaList.get(position);
        holder.namaJasaTextView.setText(jasa.getNama());
        holder.hargaJasaTextView.setText(String.valueOf(jasa.getHarga()));
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

        public JasaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

