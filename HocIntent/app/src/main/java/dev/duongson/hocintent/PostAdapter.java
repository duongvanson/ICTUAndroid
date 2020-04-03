package dev.duongson.hocintent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    Context context;
    List<Post> lstPost;
    int layout;

    public PostAdapter(Context context, List<Post> lstPost, int layout) {
        this.context = context;
        this.lstPost = lstPost;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return lstPost.size();
    }

    @Override
    public Object getItem(int position) {
        return lstPost.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(layout, null);
        Post post = lstPost.get(position);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvContent = convertView.findViewById(R.id.tvContent);
        TextView tvView = convertView.findViewById(R.id.tvView);
        tvName.setText(post.getTitle());
        tvContent.setText(post.getContent());
        tvView.setText("View: " + post.getView());
        return convertView;
    }
}
