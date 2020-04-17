package dev.duongson.appthoitiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewWeatherActivity extends AppCompatActivity {
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_weather);
        Intent intent = getIntent();
        WeatherCountry wc = (WeatherCountry) intent.getSerializableExtra("country");
        tvResult = findViewById(R.id.tvResult);
        tvResult.setText(wc.getNameCountry()+"\n"+wc.getTemp()+"ºC");
    }
}
