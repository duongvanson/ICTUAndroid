package dev.duongson.appthoitiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button btnAdd, btnView;
    WeatherAdapter adapter;
    List<WeatherCountry> lstData;
    EditText etName, etTemp;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addData();
        addControl();
        addEvent();
        intent = new Intent(this, ViewWeatherActivity.class);
    }

    private void addEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().trim().isEmpty() || etTemp.getText().toString().trim().isEmpty()) return;
                lstData.add(new WeatherCountry(etName.getText().toString(), Integer.parseInt(etTemp.getText().toString())));
                adapter.notifyDataSetChanged();
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherCountry wc = lstData.get(0);
                for(int i = 0; i < lstData.size(); i++){
                    if(wc.getTemp() > lstData.get(i).getTemp()){
                        wc = lstData.get(i);
                    }
                }
                intent.putExtra("country", wc);
                startActivity(intent);
            }
        });
    }

    private void addData() {
        lstData = new ArrayList<WeatherCountry>();
        lstData.add(new WeatherCountry("Viet Nam", 29));
        lstData.add(new WeatherCountry("Lon Don", 5));
        lstData.add(new WeatherCountry("New York", 32));
    }

    private void addControl() {
        listView = findViewById(R.id.lvView);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        etName = findViewById(R.id.etNameCountry);
        etTemp = findViewById(R.id.etTemp);
        adapter = new WeatherAdapter(R.layout.item_country, lstData, MainActivity.this);
        listView.setAdapter(adapter);
    }
}
