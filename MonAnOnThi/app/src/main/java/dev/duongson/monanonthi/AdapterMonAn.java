package dev.duongson.monanonthi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterMonAn extends BaseAdapter {
    int layout;
    Context context;
    ArrayList<MonAn> lstMonAn;

    public AdapterMonAn(int layout, Context context, ArrayList<MonAn> lstMonAn) {
        this.layout = layout;
        this.context = context;
        this.lstMonAn = lstMonAn;
    }

    @Override
    public int getCount() {
        return lstMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return lstMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout, null);
        final MonAn monAn = (MonAn) getItem(position);
        ImageView img = convertView.findViewById(R.id.imgMonAn);
        TextView ten = convertView.findViewById(R.id.tvTen);
        TextView gia = convertView.findViewById(R.id.tvGia);
        CheckBox cb = convertView.findViewById(R.id.chk);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                monAn.setCheck(isChecked);
                MainActivity.adapterMonAn.notifyDataSetChanged();
            }
        });
        img.setImageResource(monAn.getAnh());
        ten.setText(monAn.getTen());
        gia.setText(monAn.getGia()+"");
        cb.setChecked(monAn.isCheck());
        return convertView;
    }
}
