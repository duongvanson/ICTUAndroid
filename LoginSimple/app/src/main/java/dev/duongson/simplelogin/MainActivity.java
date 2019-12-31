package dev.duongson.simplelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText txtUsername, txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNname = txtUsername.getText().toString();
                String passWord = txtPassword.getText().toString();
                if (userNname.equals("") || passWord.equals("")){
                    Toast.makeText(MainActivity.this, "Username or password not empty.", Toast.LENGTH_LONG).show();
                }else if(userNname.equals("admin") && passWord.equals("123456")){
                    Toast.makeText(MainActivity.this, "Login success!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Login fail!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
