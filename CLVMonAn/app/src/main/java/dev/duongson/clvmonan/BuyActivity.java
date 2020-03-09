package dev.duongson.clvmonan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        TextView tvSum = findViewById(R.id.tvSum);
        Intent intent = this.getIntent();
        tvSum.setText("Tổng tiền cần trả: "+intent.getStringExtra("sum"));
    }
}
