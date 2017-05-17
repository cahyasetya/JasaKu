package com.example.jasaku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.Provinsi;

import java.util.List;

/**
 * Created by cahyasetya on 5/17/2017.
 */

public class ProvinsiAdapter extends BaseAdapter {

    private Context context;
    private List<Provinsi> provinsiList;

    public ProvinsiAdapter(Context context, List<Provinsi> provinsiList) {
        this.context = context;
        this.provinsiList = provinsiList;
    }

    @Override
    public int getCount() {
        return provinsiList.size();
    }

    @Override
    public Object getItem(int position) {
        return provinsiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(provinsiList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Provinsi provinsi=provinsiList.get(position);

        View view= LayoutInflater.from(context).inflate(R.layout.provinsi_item,parent,false);

        TextView namaProvinsi=(TextView)view.findViewById(R.id.nama_provinsi);

        namaProvinsi.setText(provinsi.getNama());

        return view;
    }
}
