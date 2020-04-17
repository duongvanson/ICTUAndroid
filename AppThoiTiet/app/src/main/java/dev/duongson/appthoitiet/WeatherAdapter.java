package dev.duongson.appthoitiet;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class WeatherAdapter extends BaseAdapter {
    int layout;
    List<WeatherCountry> lstWeather;
    Context context;

    public WeatherAdapter(int layout, List<WeatherCountry> lstWeather, Context context) {
        this.layout = layout;
        this.lstWeather = lstWeather;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lstWeather.size();
    }

    @Override
    public Object getItem(int position) {
        return lstWeather.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout,  null);
        WeatherCountry wc = (WeatherCountry) getItem(position);
        ImageView img = convertView.findViewById(R.id.img);
        TextView country = convertView.findViewById(R.id.tvNameCountry);
        TextView weather = convertView.findViewById(R.id.tvWeather);
        TextView temp = convertView.findViewById(R.id.tvTemp);
        country.setText(wc.getNameCountry());
        temp.setText(wc.getTemp()+"ºC");
        int t = wc.getTemp();
        if (t < 20){
            weather.setText("Rainy");
            img.setImageResource(R.drawable.stormy);
        }else if(t > 30){
            weather.setText("Sunny");
            img.setImageResource(R.drawable.sunny);
        }else {
            weather.setText("Cloudy");
            img.setImageResource(R.drawable.windy);
        }
        return convertView;
    }
}
