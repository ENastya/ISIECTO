package com.example.helper.Pages;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("create table ProdList ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "flag boolean" + ");");

        db.execSQL("create table MetaList ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "pass text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}