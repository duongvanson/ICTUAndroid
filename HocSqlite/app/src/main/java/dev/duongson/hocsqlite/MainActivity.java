package dev.duongson.hocsqlite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etCode, etName, etMark;
    Button btnAdd, btnDelete, btnShow, btnDel001, btnView4, btnEdit001;
    ArrayAdapter<String> adapter;
    ArrayList<Student> students;
    ArrayList<String> views;
    ListView lvStudent;
    SqlHelper sqlHelper;
    int index = -1;
    boolean edit001 = false;
    String code_old="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlHelper = new SqlHelper(this);
        students = new ArrayList<Student>();
        views  = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, views);
        addControl();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(etCode.getText().toString(),etName.getText().toString(), Double.parseDouble(etMark.getText().toString()));
                if (sqlHelper.insertStudent(student)){
                    Toast.makeText(MainActivity.this,"Thêm thành công!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Thêm thất bại!",Toast.LENGTH_SHORT).show();
                }
                loadData();
                index= -1;
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvStudent.setAdapter(adapter);
                loadData();
            }
        });
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                Student student = students.get(position);
                etCode.setText(student.getCode());
                etName.setText(student.getName());
                etMark.setText(student.getMark()+"");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sqlHelper.deleteAllStudent()){
                        Toast.makeText(MainActivity.this,"Đã xóa toàn bộ!",Toast.LENGTH_SHORT).show();
                        loadData();
                    }else{
                        Toast.makeText(MainActivity.this,"Xóa thất bại!",Toast.LENGTH_SHORT).show();
                    }
            }
        });
        btnDel001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sqlHelper.deleteStudent("dtc001")==1){
                    Toast.makeText(MainActivity.this,"Đã xóa dtc001!",Toast.LENGTH_SHORT).show();
                    loadData();
                }else{
                    Toast.makeText(MainActivity.this,"Không tồn tại!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                views.clear();
                students = sqlHelper.getAllStudent(4);
                for(int i = 0; i < students.size(); i++){
                    views.add(students.get(i).toString());
                }
                adapter.notifyDataSetChanged();
            }
        });
        btnEdit001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit001){
                    if(sqlHelper.updateStudent(code_old,etCode.getText().toString(),etName.getText().toString(), etMark.getText().toString())){
                        Toast.makeText(MainActivity.this,"Cập nhật thành công!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,"Cập nhật thất bại!",Toast.LENGTH_SHORT).show();
                    }
                    edit001=false;
                    btnEdit001.setText("Sửa thông tin dtc001");
                }else{
                    Student student = sqlHelper.getStudent("dtc001");
                    if(student==null){
                        Toast.makeText(MainActivity.this,"Không tồn tại!",Toast.LENGTH_SHORT).show();
                    }else{
                        etCode.setText(student.getCode());
                        etMark.setText(student.getMark()+"");
                        etName.setText(student.getName());
                        code_old = student.getCode();
                        edit001=true;
                        btnEdit001.setText("Cập nhật dtc001");
                    }
                }
                loadData();
            }
        });
    }
    private  void loadData(){
        views.clear();
        students = sqlHelper.getAllStudent();
        for(int i = 0; i < students.size(); i++){
            views.add(students.get(i).toString());
        }
        adapter.notifyDataSetChanged();
    }
    private void addControl() {
        btnDel001 = findViewById(R.id.btnDelete001);
        btnView4 = findViewById(R.id.btnViewByMark);
        btnEdit001 = findViewById(R.id.btnEdit001);
        etCode = findViewById(R.id.etCode);
        etName = findViewById(R.id.etName);
        etMark = findViewById(R.id.etMark);
        btnAdd = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnShow = findViewById(R.id.btnShow);
        lvStudent = findViewById(R.id.lvStudent);
    }
}
