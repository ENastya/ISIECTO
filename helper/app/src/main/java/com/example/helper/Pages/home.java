package com.example.helper.Pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.helper.R;

public class home extends AppCompatActivity {
ImageButton btnList;
ImageButton btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void goToList (View v) {
        Intent intent = new Intent(this, list.class);
        startActivity(intent);
    }

    public void goToSearch (View v) {
        Intent intent = new Intent(this, search.class);
        startActivity(intent);
    }

    public void goToShops (View v) {
        Intent intent = new Intent(this, shops.class);
        startActivity(intent);
    }

    public void goToPartner (View v) {
        Intent intent = new Intent(this, partner.class);
        startActivity(intent);
    }
}
