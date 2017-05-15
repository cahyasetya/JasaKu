package com.example.jasaku.penjual.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.Jasa;
import com.example.jasaku.penjual.KelolaJasa;
import com.example.jasaku.presenter.HalamanJasaFragmentPenjualPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by light on 18/04/17.
 */

public class JasaAdapter extends RecyclerView.Adapter<JasaAdapter.JasaViewHolder>{

    private Context context;
    private List<Jasa> jasaList;
    private HalamanJasaFragmentPenjualPresenter presenter;

    public JasaAdapter(Context context, List<Jasa> jasaList, HalamanJasaFragmentPenjualPresenter presenter){
        this.context=context;
        this.jasaList=jasaList;
        this.presenter=presenter;
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
        holder.hapusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.hapusJasa(jasa.getId());
            }
        });
        holder.ubahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer update = 1;
                Intent intent = new Intent(context, KelolaJasa.class);
                intent.putExtra("update", update);
                intent.putExtra("jasaId", jasa.getId());
                intent.putExtra("jasaNama", jasa.getNama());
                intent.putExtra("jasaHarga", String.valueOf(jasa.getHarga()));
                context.startActivity(intent);
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
        @BindView(R.id.hapusButton)
        ImageButton hapusButton;
        @BindView(R.id.ubahButton)
        ImageButton ubahButton;

        public JasaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

