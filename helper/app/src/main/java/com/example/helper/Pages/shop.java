package com.example.helper.Pages;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.helper.Entity.Shop;
import com.example.helper.Entity.ShopAdd;
import com.example.helper.R;

import java.util.ArrayList;

public class shop extends AppCompatActivity {
    EditText name;
    EditText street;
    EditText home;
    EditText city;
    ArrayList <String> allItems;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        name = (EditText) findViewById(R.id.name);
        street = (EditText) findViewById(R.id.street);
        home = (EditText) findViewById(R.id.home);
        city = (EditText) findViewById(R.id.city);
          }

          public void addNewShop(View v){
              Shop shop = new Shop();
              shop.setName(name.getText().toString());
              DBHelper dbHelper = new DBHelper (this);
              SQLiteDatabase db = dbHelper.getWritableDatabase();
              Cursor c = db.query("MetaList", null, null, null, null, null, null);
              c.moveToFirst();
              int id = c.getColumnIndex("id");
              shop.setUserId(c.getInt(id));
              c.close();
              shop.setAdress(city.getText().toString() + ", " + street.getText().toString() + ", " + home.getText().toString());
              ShopAdd sa = new ShopAdd();
              sa.execute(shop);
              Intent intent = new Intent(this, partner.class);
              startActivity(intent);
          }


}
