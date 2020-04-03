package dev.duongson.hocintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView tvHello, tvPost;
    Button btnView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intent = new Intent(this, ViewActivity.class);
        tvHello = findViewById(R.id.tvHello);
        btnView = findViewById(R.id.btnView);
        tvPost = findViewById(R.id.tvItemSelect);
        Bundle bundle = getIntent().getBundleExtra("data");
        tvHello.setText("Hello, " + bundle.getString("user"));
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Post post = (Post) data.getSerializableExtra("post");
        tvPost.setText("CHÚC MỪNG ANH EM ĐÃ LẤY ĐƯƠC! \n---\n"+post.getTitle()+"\n"+post.getContent()+"\nView: "+post.getView());
    }
}
