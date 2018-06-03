package com.example.helper.Pages;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.helper.Entity.Shop;
import com.example.helper.Entity.ShopEd;
import com.example.helper.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List <Shop> listPr;
    String shopAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses;
            ShopEd productEd = new ShopEd();
            try {
                listPr = productEd.execute().get();
            } catch (InterruptedException e) {

            } catch (ExecutionException e) {

            }

            for (Shop p: listPr){
                if (p.getName().equals(getIntent().getStringExtra("place"))){
                    shopAdress = p.getAdress();
                }
            }
            addresses = geocoder.getFromLocationName(shopAdress, 7);

            if(addresses.size() > 0) {
                double latitude= addresses.get(0).getLatitude();
                double longitude= addresses.get(0).getLongitude();
                LatLngBounds Coord = new LatLngBounds(new LatLng(latitude, longitude), new LatLng(latitude, longitude));
                mMap.addMarker(new MarkerOptions().position(Coord.getCenter()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Coord.getCenter(), 17));
            }

        }catch (IOException e) {

        }
    }


}
