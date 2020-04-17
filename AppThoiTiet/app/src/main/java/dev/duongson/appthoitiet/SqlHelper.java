package dev.duongson.appthoitiet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlHelper extends SQLiteOpenHelper {
    static final String DATABASE = "DBThoiTiet";
    static final String TABLE = "ThoiTiet";
    SQLiteDatabase sqLiteDatabase;

    public SqlHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + "(diadiem text, nhietdo integer, kieuthoitiet text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
    }

    //Thêm thời tiết
    public boolean insertWeather(WeatherCountry wc) {
        String ktt = "";
        if (wc.getTemp() < 20) ktt = "Rainy";
        else if (wc.getTemp() > 30) ktt = "Sunny";
        else ktt = "Cloudy";
        ContentValues values = new ContentValues();
        values.put("diadiem", wc.getNameCountry());
        values.put("nhietdo", wc.getTemp());
        values.put("kieuthoitiet", ktt);
        long result = sqLiteDatabase.insert(TABLE, null, values);
        return result > 0;
    }
}
