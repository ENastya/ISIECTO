package com.example.helper.Pages;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.helper.Entity.Product;
import com.example.helper.Entity.ProductEd;
import com.example.helper.Entity.Shop;
import com.example.helper.Entity.ShopEd;
import com.example.helper.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class search extends AppCompatActivity {

    GridView items;
    EditText req;
    TextView notFound;
    ArrayList<String> allItems;
    List<Product> listPr;
    List<Shop> listSh;
    Button search;
    List<Address> addresses;
    private FusedLocationProviderClient mFusedLocationClient;
    LocationManager locM;
    Location loc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        items = (GridView) findViewById(R.id.gitems);
        search = (Button) findViewById(R.id.button3);
        items.setVisibility(View.INVISIBLE);
        notFound = (TextView) findViewById(R.id.notfound);
        notFound.setVisibility(View.INVISIBLE);
        req = (EditText) findViewById(R.id.req);
        //mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locM =  (LocationManager) getSystemService(LOCATION_SERVICE);
            loc = null;
            loc = locM.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (loc == null) loc = locM.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (getIntent().getStringExtra("request") != null) {
            req.setText(getIntent().getStringExtra("request"));
            search.callOnClick();
        }
        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int i = position % 3;
                if (i == 1) position -= 1;
                if (i == 2) position -= 2;
                Object o = parent.getItemAtPosition(position);
                String s = o.toString();
                searchPlace(s);
            }
        });

    }


    public void searchPlace(String s) {
        Intent intent = new Intent(this, map.class);
        intent.putExtra("place", s);
        startActivity(intent);
    }


    public void makeList(ArrayList<String> allItems) {
        items = (GridView) findViewById(R.id.gitems);
        items.setNumColumns(3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allItems);
        items.setAdapter(adapter);

    }

    class lengthComparator implements Comparator<Product> {

        @Override
        public int compare(Product o1, Product o2) {
            // так короче
            // return o1.getAge() - o2.getAge();
            if (o1.getHowFar() > o2.getHowFar()) {
                return 1;
            } else if (o1.getHowFar() < o2.getHowFar()) {
                return -1;
            }
            return 0;
        }
    }

    public void search(View v) throws IOException {
        ProductEd productEd = new ProductEd();
        ShopEd shopEd = new ShopEd();
        try {
            listPr = productEd.execute().get();
            listSh = shopEd.execute().get();
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }
        allItems = new ArrayList<String>();
        for (Product p : listPr) {
            Geocoder geocoder = new Geocoder(this);
            for (Shop s : listSh) {
                if (p.getShop().equals(s.getName())) {
                    addresses = geocoder.getFromLocationName(s.getAdress(), 7);
                }
            }
            LatLng from = new LatLng(loc.getLatitude(),loc.getLongitude());
            LatLng to = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
            p.setHowFar((int) com.google.maps.android.SphericalUtil.computeDistanceBetween(from, to));
        }
        Collections.sort(listPr, new lengthComparator());
                for (Product p : listPr) {
            String Name = p.getName();
                if (p.getName().equals(req.getText().toString())) {
                                    allItems.add(p.getShop());
                allItems.add(String.valueOf(p.getPrice()) + " руб.");
                allItems.add(String.valueOf(p.getHowFar()+" м"));

            }
        }

        if (allItems.size()!=0){
            items.setVisibility(View.VISIBLE);
            notFound.setVisibility(View.INVISIBLE);
            makeList(allItems);
        }
        else {
            notFound.setVisibility(View.VISIBLE);
            items.setVisibility(View.INVISIBLE);
        }

    }
}
