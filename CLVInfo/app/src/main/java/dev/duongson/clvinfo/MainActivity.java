package dev.duongson.clvinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etName, etAge;
    Button btnAdd, btnEdit;
    ListView lvStudent;
    StudentAdapter studentAdapter;
    List<Student> lstStudent;
    int edit = -1;
    boolean insert = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addDataDemo();
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Đã xóa " + lstStudent.get(position).getName(), Toast.LENGTH_LONG).show();
                lstStudent.remove(position);
                studentAdapter.notifyDataSetChanged();
                return false;
            }
        });
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnAdd.setText("Thêm mới");
                Student student = lstStudent.get(position);
                etName.setText(student.getName());
                etAge.setText(""+student.getAge());
                btnEdit.setEnabled(true);
                edit = position;
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit != -1){
                    Student student = new Student(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()));
                    lstStudent.set(edit, student);
                    studentAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Đã sửa!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Chưa chọn", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = -1;
                if (insert){
                    if (!etAge.getText().equals("") && !etName.getText().equals("")){
                        Student student = new Student(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()));
                        lstStudent.add(student);
                        studentAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Đã thêm "+student.getName(), Toast.LENGTH_LONG).show();
                        btnAdd.setText("Thêm mới");
                        insert = false;
                        etName.setText("");
                        etAge.setText("");
                    }else{
                        Toast.makeText(getApplicationContext(), "Chưa nhập", Toast.LENGTH_LONG).show();
                    }
                }else{
                    etName.setText("");
                    etAge.setText("");
                    btnAdd.setText("Thêm");
                    insert = true;
                }
            }
        });
    }

    private void addDataDemo() {
        lstStudent = new ArrayList<>();
        lstStudent.add(new Student("Dương", 20));
        lstStudent.add(new Student("Văn", 21));
        lstStudent.add(new Student("Sơn", 22));
    }

    private void addControls() {
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        lvStudent = findViewById(R.id.lvStudent);
        studentAdapter = new StudentAdapter(lstStudent, R.layout.item_student, this);
        lvStudent.setAdapter(studentAdapter);
        btnEdit.setEnabled(false);
    }
}
