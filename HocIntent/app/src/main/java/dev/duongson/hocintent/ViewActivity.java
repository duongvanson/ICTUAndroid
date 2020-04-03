package dev.duongson.hocintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    ListView lvItem;
    PostAdapter postAdapter;
    List<Post> lstPost;
    Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        lvItem = findViewById(R.id.lvItem);
        lstPost = new ArrayList<>();
        addData();
        postAdapter = new PostAdapter(this, lstPost, R.layout.post_line);
        lvItem.setAdapter(postAdapter);
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("post", lstPost.get(position));
                setResult(0, intent);
                finish();
            }
        });
    }

    private void addData() {
        for(int i = 0; i < 10; i++){
            lstPost.add(new Post("Post Title "+i, "Content something...", i*31));
        }
    }
}
