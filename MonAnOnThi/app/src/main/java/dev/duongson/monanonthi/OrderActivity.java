package dev.duongson.monanonthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        TextView tvTong = findViewById(R.id.tvTongTien);
        int money = intent.getIntExtra("money", 0);
        ArrayList<MonAn> lstOrder = (ArrayList<MonAn>) intent.getSerializableExtra("lst");
        String result = "Danh sách món: ";
        for(int i = 0; i < lstOrder.size(); i++){
            result += "\n + "+lstOrder.get(i).getTen();
        }
        result += "\nTổng tiền: " + money+ " VNĐ";
        tvTong.setText(result);
    }
}