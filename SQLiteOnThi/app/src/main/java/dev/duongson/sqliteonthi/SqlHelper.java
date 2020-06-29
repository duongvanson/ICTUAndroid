package dev.duongson.sqliteonthi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SqlHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "QLSinhVien";
    static final String TABLE_SV = "SinhVien";
    static final String COL_MA = "masinhvien";
    static final String COL_HOTEN = "hoten";
    static final String COL_DIEMTB = "diemtrungbinh";
    static final int VERSION = 1;
    SQLiteDatabase database;
    public SqlHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        database  = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY, %s TEXT, %s REAL)", TABLE_SV, COL_MA, COL_HOTEN, COL_DIEMTB);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public boolean themSinhVien(SinhVien sv){
//        String sql = String.format("INSERT INTO %s VALUES('%s', '%s', %f)", TABLE_SV, sv.getMa(), sv.getHoTen(), sv.getDiemTrungBinh());
//        try{
//            database.execSQL(sql);
//            return true;
//        }catch (SQLException ex){
//            Log.d("sqlerr", ex.getMessage());
//            return false;
//        }
        ContentValues values = new ContentValues();
        values.put(COL_MA, sv.getMa());
        values.put(COL_HOTEN, sv.getHoTen());
        values.put(COL_DIEMTB, sv.getDiemTrungBinh());
        int res = (int) database.insert(TABLE_SV, null, values);
        return res >= -1;
    }
    public ArrayList<SinhVien> getAllSinhVien(){
        String sql = "SELECT * FROM " + TABLE_SV;
        Cursor cs = database.rawQuery(sql, null);
        ArrayList<SinhVien> res = new ArrayList<SinhVien>();
        while (cs.moveToNext()){
            res.add(new SinhVien(cs.getString(0), cs.getString(1), cs.getDouble(2)));
        }
        return res;
    }
    public boolean deleteAll(){
        int res = database.delete(TABLE_SV, null, null);
        return res > 0;
    }
}
