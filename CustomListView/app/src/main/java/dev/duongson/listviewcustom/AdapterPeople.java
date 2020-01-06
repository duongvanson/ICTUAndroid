package dev.duongson.listviewcustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdapterPeople extends BaseAdapter {
    ArrayList<People> lstPeople;
    int layout;
    Context context;

    public AdapterPeople(ArrayList<People> lstPeople, int layout, Context context) {
        this.lstPeople = lstPeople;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lstPeople.size();
    }

    @Override
    public Object getItem(int position) {
        return lstPeople.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(layout, null);
        People people = (People) getItem(position);
        ImageView img = view.findViewById(R.id.img);
        TextView tvName = view.findViewById(R.id.tvName);
        img.setImageResource(people.getImg());
        tvName.setText(people.getName());
        return view;
    }
}
