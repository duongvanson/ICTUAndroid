package dev.duongson.sqliteonthi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvSinhVien;
    Button btnAdd, btnShow, btnReset, btnDeleteAll;
    EditText etTen, etMa, etDiem;
    SqlHelper sqlHelper;
    ArrayAdapter<String> adapter;
    ArrayList<String> lstSinhVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = etMa.getText().toString().trim();
                String ten = etTen.getText().toString().trim();
                Double diemTB = Double.parseDouble(etDiem.getText().toString().trim().isEmpty() ? "-1" : etDiem.getText().toString().trim());
                if (ma.isEmpty() || ten.isEmpty() || diemTB == -1){
                    Toast.makeText(MainActivity.this, "Thông tin thiếu", Toast.LENGTH_LONG).show();
                }else{
                    if (sqlHelper.themSinhVien(new SinhVien(ma, ten, diemTB))){
                        Toast.makeText(MainActivity.this, "Thêm thành công!", Toast.LENGTH_LONG).show();
                        btnReset.performClick();
                        loadData();
                    }else{
                        Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMa.setText("");
                etTen.setText("");
                etDiem.setText("");
            }
        });
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sqlHelper.deleteAll()){
                    Toast.makeText(MainActivity.this, "Đã xóa hết!", Toast.LENGTH_LONG).show();
                    loadData();
                }else{
                    Toast.makeText(MainActivity.this, "Không xóa nổi :( ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loadData(){
        lstSinhVien.clear();
        ArrayList<SinhVien> data = sqlHelper.getAllSinhVien();
        for(int i = 0; i < data.size(); i++){
            lstSinhVien.add(data.get(i).toString());
        }
        adapter.notifyDataSetChanged();
    }
    private void addControls() {
        lvSinhVien = findViewById(R.id.lvSinhVien);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        btnReset = findViewById(R.id.btnReset);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        etMa = findViewById(R.id.etMa);
        etTen = findViewById(R.id.etTen);
        etDiem = findViewById(R.id.etDTB);
        sqlHelper = new SqlHelper(this);
        lstSinhVien = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lstSinhVien);
        lvSinhVien.setAdapter(adapter);
    }
}