package com.example.helper.Pages;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helper.Entity.LogIn;
import com.example.helper.Entity.Shop;
import com.example.helper.Entity.ShopEd;
import com.example.helper.Entity.User;
import com.example.helper.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class partner extends AppCompatActivity {

    String logmail = "ana@mail.ru";
    String password = "123";
    Boolean b = false;
    TextView done;
    TextView notdone;
    TextView mail;
    TextView pass;
    TextView allShops;
    EditText email;
    EditText epass;
    Button login;
    Button logout;
    Button addShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner);
        done  = (TextView) findViewById(R.id.done);
        notdone  = (TextView) findViewById(R.id.notdone);
        mail  = (TextView) findViewById(R.id.mail);
        email  = (EditText) findViewById(R.id.email);
        pass  = (TextView) findViewById(R.id.pass);
        epass  = (EditText) findViewById(R.id.epass);
        login = (Button) findViewById(R.id.login);
        logout = (Button) findViewById(R.id.logout);
        addShop = (Button) findViewById(R.id.addShop);
        allShops =  (TextView) findViewById(R.id.allshops);
        DBHelper dbHelper = new DBHelper (this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("MetaList", null, null, null, null, null, null);
        allShops.setText("");

        if (!c.moveToFirst()) {
            done.setVisibility(View.INVISIBLE);
            notdone.setVisibility(View.INVISIBLE);
            login.setVisibility(View.VISIBLE);
            logout.setVisibility(View.INVISIBLE);
            pass.setVisibility(View.VISIBLE);
            epass.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            mail.setVisibility(View.VISIBLE);
            addShop.setVisibility(View.INVISIBLE);
            allShops.setVisibility(View.INVISIBLE);
        }
        else {
            done.setVisibility(View.VISIBLE);
            login.setVisibility(View.INVISIBLE);
            logout.setVisibility(View.VISIBLE);
            notdone.setVisibility(View.INVISIBLE);
            pass.setVisibility(View.INVISIBLE);
            epass.setVisibility(View.INVISIBLE);
            email.setVisibility(View.INVISIBLE);
            mail.setVisibility(View.INVISIBLE);
            addShop.setVisibility(View.VISIBLE);
            allShops.setVisibility(View.VISIBLE);
            ShopEd se = new ShopEd();
            int id = c.getColumnIndex("id");
            try {
                String res ="";
                List<Shop> list = se.execute().get();
                for (Shop s: list){
                    if (s.getUserId() == c.getInt(id)){
                        res += s.getName();
                        res += "\n";
                        res += s.getAdress();
                        res += "\n";
                        res += "\n";
                    }
                    allShops.setText(res);
                }
            } catch (InterruptedException e) {

            } catch (ExecutionException e) {

            }
        }
        c.close();
    }
    public void login (View v) throws ExecutionException, InterruptedException {
        LogIn li = new LogIn();
        User user = new User();
        user.setName(email.getText().toString());
        user.setPass(epass.getText().toString());
        user = li.execute(user).get();

        if (user != null) {
            ContentValues cv = new ContentValues();

            cv.put("id", user.getId());
            cv.put("name", user.getName());
            cv.put("pass", user.getPass());

            DBHelper dbHelper = new DBHelper (this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            long rowID = db.insert("MetaList", null, cv);
            Cursor c = db.query("MetaList", null, null, null, null, null, null);
            ShopEd se = new ShopEd();
            int id = c.getColumnIndex("id");
            c.moveToFirst();
                 String res = "";
                List<Shop> list = se.execute().get();
                for (Shop s : list) {
                    if (s.getUserId() == c.getInt(id)) {
                        res += s.getName();
                        res += "\n";
                        res += s.getAdress();
                        res += "\n";
                        res += "\n";

                    }}
            c.close();
                    allShops.setText(res);
                    done.setVisibility(View.VISIBLE);
                    login.setVisibility(View.INVISIBLE);
                    logout.setVisibility(View.VISIBLE);
                    notdone.setVisibility(View.INVISIBLE);
                    pass.setVisibility(View.INVISIBLE);
                    epass.setVisibility(View.INVISIBLE);
                    email.setVisibility(View.INVISIBLE);
                    mail.setVisibility(View.INVISIBLE);
                    addShop.setVisibility(View.VISIBLE);
                    allShops.setVisibility(View.VISIBLE);
                }
            else
        {
            done.setVisibility(View.INVISIBLE);
            login.setVisibility(View.VISIBLE);
            logout.setVisibility(View.INVISIBLE);
            notdone.setVisibility(View.VISIBLE);
            pass.setVisibility(View.VISIBLE);
            epass.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            mail.setVisibility(View.VISIBLE);
            addShop.setVisibility(View.INVISIBLE);
            allShops.setVisibility(View.INVISIBLE);
                    }
    }

    public void logout (View v){
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("MetaList", null, null);
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

    public void addShop (View v){
        Intent intent = new Intent(this, shop.class);
        startActivity(intent);
    }
}
