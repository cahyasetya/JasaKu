package com.example.jasaku.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.Kategori;

import java.util.List;

/**
 * Created by light on 14/05/17.
 */

public class KategoriSpinner extends BaseAdapter {

    private Context context;
    private List<Kategori> kategoriList;

    public KategoriSpinner(Context context,List<Kategori> kategoriList){
        this.context=context;
        this.kategoriList=kategoriList;
    }

    @Override
    public int getCount() {
        return kategoriList.size();
    }

    @Override
    public Object getItem(int position) {
        return kategoriList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return kategoriList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Kategori kategori=kategoriList.get(position);

        View view=LayoutInflater.from(context).inflate(R.layout.kategori_item_list,parent,false);
        TextView namaKategori=(TextView)view.findViewById(R.id.nama_kategori);
        namaKategori.setText(kategori.getNama());
        return view;
    }
}
