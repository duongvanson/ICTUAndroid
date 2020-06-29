package dev.duongson.monanonthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvMonAn;
    EditText etTen, etGia;
    Button btnUpdate, btnOrder, btnCancle;
    ArrayList<MonAn> lstMonAn;
    static AdapterMonAn adapterMonAn;
    LinearLayout layoutEdit;
    Intent intent;
    int select = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this, OrderActivity.class);
        addControls();
        addDataDemo();
        addEvents();
    }

    private void addEvents() {
        lvMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select = position; //Giu vi tri mon an dang duoc chon
                MonAn monAn = lstMonAn.get(position);
                etTen.setText(monAn.getTen());
                etGia.setText(monAn.getGia()+"");
                layoutEdit.setVisibility(View.VISIBLE);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonAn monAn = lstMonAn.get(select);
                monAn.setGia(Integer.parseInt(etGia.getText().toString().trim()));
                monAn.setTen(etTen.getText().toString().trim());
                lstMonAn.set(select, monAn);
                adapterMonAn.notifyDataSetChanged();
                btnCancle.performClick();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTen.setText("");
                etGia.setText("");
                select = -1;
                layoutEdit.setVisibility(View.INVISIBLE);
            }
        });
        lvMonAn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lstMonAn.remove(position);
                adapterMonAn.notifyDataSetChanged();
                btnCancle.performClick();
                return false;
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = 0;
                ArrayList<MonAn> lstOrder = new ArrayList<MonAn>();
                for (int i = 0; i < lstMonAn.size(); i++){
                    if(lstMonAn.get(i).isCheck()){
                        lstOrder.add(lstMonAn.get(i));
                        sum += lstMonAn.get(i).getGia();
                    }
                }
                intent.putExtra("money", sum);
                intent.putExtra("lst", lstOrder);
                startActivity(intent);
            }
        });
    }

    private void addDataDemo() {
        lstMonAn.add(new MonAn(R.drawable.monan, 1000, "Món ăn 1", false));
        lstMonAn.add(new MonAn(R.drawable.monan, 5000, "Món ăn 2", false));
        lstMonAn.add(new MonAn(R.drawable.monan, 2000, "Món ăn 3", false));
        lstMonAn.add(new MonAn(R.drawable.monan, 7000, "Món ăn 4", false));
        adapterMonAn = new AdapterMonAn(R.layout.line_monan, this, lstMonAn);
        lvMonAn.setAdapter(adapterMonAn);
    }

    private void addControls() {
        lvMonAn = findViewById(R.id.lvMonAn);
        etTen = findViewById(R.id.etTen);
        etGia = findViewById(R.id.etGia);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnOrder = findViewById(R.id.btnOrder);
        lstMonAn = new ArrayList<MonAn>();
        layoutEdit = findViewById(R.id.layoutEdit);
        btnCancle = findViewById(R.id.btnCancle);
    }
}