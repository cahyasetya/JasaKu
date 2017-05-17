package com.example.jasaku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.Kecamatan;

import java.util.List;

/**
 * Created by cahyasetya on 5/17/2017.
 */

public class KecamatanAdapter extends BaseAdapter {

    private Context context;
    private List<Kecamatan> kecamatanList;

    public KecamatanAdapter(Context context, List<Kecamatan> kecamatanList) {
        this.context = context;
        this.kecamatanList = kecamatanList;
    }

    @Override
    public int getCount() {
        return kecamatanList.size();
    }

    @Override
    public Object getItem(int position) {
        return kecamatanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(kecamatanList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Kecamatan kecamatan=kecamatanList.get(position);

        View view= LayoutInflater.from(context).inflate(R.layout.kecamatan_item,parent,false);

        TextView namaKecamatan=(TextView)view.findViewById(R.id.nama_kecamatan);

        namaKecamatan.setText(kecamatan.getNama());

        return view;
    }
}
