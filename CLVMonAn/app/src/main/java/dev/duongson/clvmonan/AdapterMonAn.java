package dev.duongson.clvmonan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterMonAn extends BaseAdapter {
    int layout;
    List<MonAn> lstMonAn;
    Context context;

    public AdapterMonAn(int layout, List<MonAn> lstMonAn, Context context) {
        this.layout = layout;
        this.lstMonAn = lstMonAn;
        this.context = context;
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
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(layout,parent, false);
        MonAn monAn = lstMonAn.get(position);
        ImageView img = view.findViewById(R.id.imgDish);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvPrice = view.findViewById(R.id.tvPridce);
        CheckBox cbBuy = view.findViewById(R.id.cbBuy);
        img.setImageResource(monAn.img);
        tvName.setText(monAn.getName());
        tvPrice.setText(""+monAn.getPrice());
        cbBuy.setChecked(monAn.isBuy());
        return view;
    }
}
