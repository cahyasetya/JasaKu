package com.example.jasaku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.interfaces.KategoriInterface;
import com.example.jasaku.model.Kategori;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by light on 25/04/17.
 */

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>{

    private List<Kategori> kategoriList;
    private Context context;
    private KategoriViewHolder currViewHolder=null;

    public KategoriAdapter(Context context, List<Kategori> kategoriList){
        this.context=context;
        this.kategoriList=kategoriList;
    }

    @Override
    public KategoriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.kategori_item,parent,false);
        return new KategoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final KategoriViewHolder holder, int position) {
        Kategori kategori=kategoriList.get(position);
        holder.kategoriNama.setText(kategori.getNama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currViewHolder!=null)
                    currViewHolder.kategoriCheck.setVisibility(View.INVISIBLE);
                holder.kategoriCheck.setVisibility(View.VISIBLE);
                currViewHolder=holder;
                ((KategoriInterface)context).onKategoriSelected(String.valueOf(kategori.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return kategoriList.size();
    }

    public class KategoriViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.kategori_nama)
        TextView kategoriNama;
        @BindView(R.id.kategori_check)
        ImageView kategoriCheck;

        public KategoriViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
