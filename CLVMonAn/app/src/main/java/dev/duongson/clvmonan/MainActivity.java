package dev.duongson.clvmonan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<MonAn> lstMonAn;
    AdapterMonAn adapterMonAn;
    ListView lvDish;
    Button btnBuy, btnUpdate, btnDelete;
    EditText etName, etPrice;
    int isCheck = -1;
    LinearLayout layoutEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createdDataDemo();
        addControls();
        setEvents();
    }

    private void createdDataDemo() {
        lstMonAn = new ArrayList<MonAn>();
        lstMonAn.add(new MonAn(R.drawable.mon1, "Tôm chiên", 30000, false));
        lstMonAn.add(new MonAn(R.drawable.mon2, "Thịt xào", 25000, false));
        lstMonAn.add(new MonAn(R.drawable.mon3, "Canh đậu", 15000, false));
        lstMonAn.add(new MonAn(R.drawable.mon4, "Tôm chiên cuốn", 25000, false));
        adapterMonAn = new AdapterMonAn(R.layout.line_dish,lstMonAn, this);
    }

    private void setEvents() {
        lvDish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isCheck = position;
                MonAn monAn = lstMonAn.get(position);
                etName.setText(monAn.getName());
                etPrice.setText(""+monAn.getPrice());
                layoutEdit.setVisibility(View.VISIBLE);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonAn monAn = lstMonAn.get(isCheck);
                lstMonAn.set(isCheck, new MonAn(
                        monAn.getImg(),
                        etName.getText().toString(),
                        Double.parseDouble(etPrice.getText().toString()),
                        monAn.isBuy
                ));
                Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                adapterMonAn.notifyDataSetChanged();
                layoutEdit.setVisibility(View.GONE);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstMonAn.remove(isCheck);
                Toast.makeText(getApplicationContext(), "Đã xóa", Toast.LENGTH_LONG).show();
                adapterMonAn.notifyDataSetChanged();
                layoutEdit.setVisibility(View.GONE);
            }
        });
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = 0;
                for (int i = 0; i < lvDish.getChildCount(); i++){
                    LinearLayout layout = (LinearLayout) lvDish.getChildAt(i);
                    CheckBox checkBox = (CheckBox) layout.getChildAt(2);
                    if (checkBox.isChecked()){
                        sum += lstMonAn.get(i).getPrice();
                    }
                }
                Intent intent = new Intent(MainActivity.this, BuyActivity.class);
                intent.putExtra("sum", sum+"");
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void addControls() {
        lvDish = findViewById(R.id.lvDish);
        btnBuy = findViewById(R.id.btnBuy);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        etName = findViewById(R.id.etName);
        etPrice =  findViewById(R.id.etPrice);
        layoutEdit = findViewById(R.id.layoutEdit);
        lvDish.setAdapter(adapterMonAn);
    }
}
