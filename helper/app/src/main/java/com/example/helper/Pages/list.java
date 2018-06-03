package com.example.helper.Pages;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helper.R;

import java.util.ArrayList;

public class list extends AppCompatActivity {
    ListView items;
    EditText item;
    ArrayList <String> allItems;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        // подключаемся к БД
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        allItems = new ArrayList<String>();
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query("ProdList", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int emailColIndex = c.getColumnIndex("flag");

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                allItems.add(c.getString(nameColIndex));
            } while (c.moveToNext());
        } else
        c.close();
        items = (ListView) findViewById(R.id.items);
        makeList();
        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String s = ((TextView) view).getText().toString();
                searchItem(s);
                                 }});

    }

    public void searchItem (String s){
        Intent intent = new Intent(this, search.class);
        intent.putExtra("request", s);
        startActivity(intent);
    }

    public void makeList(){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                //android.R.layout.simple_list_item_multiple_choice, allItems);
                android.R.layout.simple_list_item_1, allItems);
        items.setAdapter(adapter);
    }

    public void addNewItem (View view) {
        item = (EditText) findViewById(R.id.item);
        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // подключаемся к БД
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        allItems = new ArrayList<String>();
        cv.put("name", item.getText().toString());
        cv.put("flag", false);
        // вставляем запись и получаем ее ID
        long rowID = db.insert("ProdList", null, cv);
        allItems = new ArrayList<String>();
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query("ProdList", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int emailColIndex = c.getColumnIndex("flag");

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                allItems.add(c.getString(nameColIndex));
            } while (c.moveToNext());
        } else
            c.close();
        makeList();
        item.setText("");
        makeList();
    }

    public void clearAll(View v){
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("ProdList", null, null);
        allItems = new ArrayList<String>();
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query("ProdList", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int emailColIndex = c.getColumnIndex("flag");

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                allItems.add(c.getString(nameColIndex));
            } while (c.moveToNext());
        } else
            c.close();
        makeList();
    }
}
