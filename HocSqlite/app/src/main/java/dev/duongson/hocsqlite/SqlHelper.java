package dev.duongson.hocsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SqlHelper extends SQLiteOpenHelper {
    static final String DATABASE = "QLSV";
    static final String TABLE = "SinhVien";
    SQLiteDatabase sqLiteDatabase;
    public SqlHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE+"(code text primary key, name text, mark real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
    }
    //Thêm sinh viên
    public boolean insertStudent(Student student){
        ContentValues values = new ContentValues();
        values.put("code",student.getCode());
        values.put("name", student.getName());
        values.put("mark", student.getMark());
        long result = sqLiteDatabase.insert(TABLE, null,values);
        return result > 0;
    }
    //Xóa sinh viên
    public boolean deleteStudent(String code){
        Log.d("code ", code);
        int result = sqLiteDatabase.delete(TABLE, "code = '"+code+"'",null);
        return result > 0;
    }
    public boolean deleteAllStudent(){
        int result = sqLiteDatabase.delete(TABLE, null,null);
        return result > 0;
    }
    //Get all
    public ArrayList<Student> getAllStudent(){
        ArrayList<Student> result = new ArrayList<Student>();
        Cursor cs = sqLiteDatabase.rawQuery("select * from "+TABLE,null);
        while(cs.moveToNext()){
            result.add(new Student(cs.getString(0),cs.getString(1), cs.getDouble(2)));
        }
        return result;
    }
}
