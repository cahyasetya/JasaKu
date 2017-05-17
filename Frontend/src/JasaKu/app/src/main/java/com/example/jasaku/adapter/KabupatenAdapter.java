package com.example.jasaku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jasaku.R;
import com.example.jasaku.model.Kabupaten;

import java.util.List;

/**
 * Created by cahyasetya on 5/17/2017.
 */

public class KabupatenAdapter extends BaseAdapter {

    private Context context;
    private List<Kabupaten> kabupatenList;

    public KabupatenAdapter(Context context, List<Kabupaten> kabupatenList) {
        this.context = context;
        this.kabupatenList = kabupatenList;
    }

    @Override
    public int getCount() {
        return kabupatenList.size();
    }

    @Override
    public Object getItem(int position) {
        return kabupatenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(kabupatenList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Kabupaten kabupaten=kabupatenList.get(position);

        View view= LayoutInflater.from(context).inflate(R.layout.kabupaten_item,parent,false);

        TextView namaKabupten=(TextView)view.findViewById(R.id.nama_kabupaten);

        namaKabupten.setText(kabupaten.getNama());

        return view;
    }
}
