package dev.duongson.chuadethi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SqlHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "quanlytaikhoan";
    static final String TAB_USER = "users";
    static final String COL_USER = "username";
    static final String COL_PASS = "password";
    static final int VERSION = 1;
    SQLiteDatabase database;
    public SqlHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY, %s TEXT)", TAB_USER, COL_USER, COL_PASS);
        db.execSQL(sql);
    }
    public boolean addUsers(User user){
        ContentValues values = new ContentValues();
        values.put(COL_USER, user.getUsername());
        values.put(COL_PASS, user.getPassword());
        return database.insert(TAB_USER, null, values) != -1;
    }
    public boolean checkLogin(String username, String password){
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s' and %s = '%s'", TAB_USER, COL_USER, username, COL_PASS, password);
        Cursor cs = database.rawQuery(sql, null);
        return cs.getCount() > 0;
    }
    public ArrayList<User> getAll(){
        String sql = "SELECT * FROM "+TAB_USER;
        ArrayList<User> res = new ArrayList<User>();
        Cursor cs = database.rawQuery(sql, null);
        while (cs.moveToNext()){
            res.add(new User(cs.getString(0), cs.getString(1)));
        }
        return res;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
