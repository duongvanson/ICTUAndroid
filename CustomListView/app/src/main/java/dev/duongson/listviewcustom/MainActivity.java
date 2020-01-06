package dev.duongson.listviewcustom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    Button btnInsert, btnUpdate, btnDelete, btnCancle;
    ListView lvPeople;
    AdapterPeople adapterPeople;
    ArrayList<People> lstPeople;
    LinearLayout mnuHide;
    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        final Random rd = new Random();
        lstPeople = new ArrayList<People>();
        lstPeople.add(new People(R.drawable.female,"Hải Yến"));
        adapterPeople = new AdapterPeople(lstPeople, R.layout.people_item, MainActivity.this);
        lvPeople.setAdapter(adapterPeople);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etName.getText().toString().equals("")){
                    lstPeople.add(new People(
                            rd.nextInt(2)==0?R.drawable.male:R.drawable.female,
                            etName.getText().toString()
                    ));
                    adapterPeople.notifyDataSetChanged();
                    etName.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Không được để trống", Toast.LENGTH_LONG).show();
                }
            }
        });
        lvPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                People people = lstPeople.get(position);
                etName.setText(people.getName());
                btnInsert.setVisibility(View.GONE);
                mnuHide.setVisibility(View.VISIBLE);
                index = position;
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstPeople.set(index, new People(
                        lstPeople.get(index).getImg(),
                        etName.getText().toString()
                ));
                btnInsert.setVisibility(View.VISIBLE);
                mnuHide.setVisibility(View.GONE);
                etName.setText("");
                index = -1;
                adapterPeople.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Đã cập nhật!", Toast.LENGTH_LONG).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstPeople.remove(index);
                btnInsert.setVisibility(View.VISIBLE);
                mnuHide.setVisibility(View.GONE);
                etName.setText("");
                Toast.makeText(MainActivity.this, "Đã xóa!", Toast.LENGTH_LONG).show();
                adapterPeople.notifyDataSetChanged();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInsert.setVisibility(View.VISIBLE);
                mnuHide.setVisibility(View.GONE);
                etName.setText("");
            }
        });
    }

    private void addControl() {
        etName = findViewById(R.id.etName);
        btnInsert = findViewById(R.id.btnInsert);
        lvPeople = findViewById(R.id.lvPeople);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancle = findViewById(R.id.btnCancle);
        mnuHide = findViewById(R.id.mnuHide);
    }
}
