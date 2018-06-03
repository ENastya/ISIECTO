package com.example.helper.Pages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.helper.Entity.Shop;
import com.example.helper.Entity.ShopEd;
import com.example.helper.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class shops extends AppCompatActivity {
    ListView items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shops);
        List allItems = new ArrayList<String>();
        ShopEd se = new ShopEd();
        List<Shop> list = new ArrayList();
        try {
            list = se.execute().get();
            for (Shop s: list){
                allItems.add(s.getName());
                allItems.add(s.getAdress());
                allItems.add("");
            }
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }

        items = (ListView) findViewById(R.id.items);
        makeList(allItems);

    }

    public void makeList(List allItems){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                //android.R.layout.simple_list_item_multiple_choice, allItems);
                android.R.layout.simple_list_item_1, allItems);
        items.setAdapter(adapter);
    }
}
