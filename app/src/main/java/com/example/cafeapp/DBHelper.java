package com.example.cafeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "DB_sales.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("DBHelper", "onCreate");
        db.execSQL("DROP TABLE IF EXISTS sales;");
        db.execSQL("CREATE TABLE sales (" +
                "sales_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sales_title TEXT," +
                "sales_cost TEXT," +
                "sales_w_date TEXT" +
                ");");
    }

    public void insert(String title, String cost) {
        Log.e("helper", "insert");
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO sales (sales_id, sales_title, sales_cost, sales_w_date) VALUES " +
                " (NULL, ?, ?, datetime('now','localtime'))", new Object[] {title, cost});
        db.close();
    }

    public List<Map<String, Object>> select() {
        Log.e("helper", "select");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT sales_id, sales_title, sales_cost, sales_w_date FROM sales", null);
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", cursor.getInt(0));
            map.put("title", cursor.getString(1));
            map.put("cost", cursor.getString(2));
            map.put("w_date", cursor.getString(3));
            list.add(map);
        }
        db.close();
        return list;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}