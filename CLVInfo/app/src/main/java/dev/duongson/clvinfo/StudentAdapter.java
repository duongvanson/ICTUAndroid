package dev.duongson.clvinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    List<Student> students;
    int layoutItem;
    Context context;

    public StudentAdapter(List<Student> students, int layoutItem, Context context) {
        this.students = students;
        this.layoutItem = layoutItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(layoutItem, parent, false);
        Student student = (Student) getItem(position);
        TextView tvName = view.findViewById(R.id.tvName);
        tvName.setText(student.getName());
        TextView tvAge = view.findViewById(R.id.tvAge);
        tvAge.setText(""+student.getAge());
        return view;
    }
}
