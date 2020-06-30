package dev.duongson.chuadethi;

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
    EditText etUsername, etPassword;
    Button btnAdd, btnLogin;
    SqlHelper sqlHelper;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlHelper = new SqlHelper(this);
        addControls();
        addEvents();
        loadData();
    }

    private void loadData() {
        ArrayList<User> lstUser = sqlHelper.getAll();
        for(int i = 0; i < lstUser.size(); i++){
            data.add(lstUser.get(i).toString());
        }
        adapter.notifyDataSetChanged();
    }

    private void addEvents() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Thiếu thông tin", Toast.LENGTH_LONG).show();
                }else{
                    if (sqlHelper.addUsers(new User(username, password))){
                        Toast.makeText(MainActivity.this, "Thêm OK", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Lỗi thêm", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Thiếu thông tin", Toast.LENGTH_LONG).show();
                }else{
                    if (sqlHelper.checkLogin(username, password)){
                        Toast.makeText(MainActivity.this, "Login OK", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Login FAIL", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void addControls() {
        etPassword = findViewById(R.id.etPassword);
        etUsername = findViewById(R.id.etUsername);
        btnAdd = findViewById(R.id.btnAdd);
        btnLogin = findViewById(R.id.btnLogin);
        data = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView = findViewById(R.id.lvUsers);
        listView.setAdapter(adapter);
    }
}