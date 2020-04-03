package dev.duongson.hocintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etAcc, etPass;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etAcc = findViewById(R.id.etAccount);
        etPass = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        final Intent intent = new Intent(this, HomeActivity.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = etAcc.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                if(acc.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this,"Rỗng quá anh ei", Toast.LENGTH_LONG).show();
                }else if(!acc.equals("admin") || !pass.equals("admin")){
                    Toast.makeText(MainActivity.this,"Sai rồi anh ei", Toast.LENGTH_LONG).show();
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putString("user",acc);
                    intent.putExtra("data", bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
