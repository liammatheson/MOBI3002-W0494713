package com.example.liamsgooglemapsproject2025;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Circle;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Center camera on Nova Scotia
        LatLng novaScotia = new LatLng(45.0, -63.0);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(novaScotia, 7f));

        // Add three markers
        LatLng campus1 = new LatLng(44.6488, -63.5752);
        LatLng campus2 = new LatLng(45.3717, -63.2492);
        LatLng campus3 = new LatLng(46.1293, -60.1934);

        mMap.addMarker(new MarkerOptions().position(campus1).title("NSCC Campus 1"));
        mMap.addMarker(new MarkerOptions().position(campus2).title("NSCC Campus 2"));
        mMap.addMarker(new MarkerOptions().position(campus3).title("NSCC Campus 3"));

        // Circle around COGS
        LatLng cogs = new LatLng(44.8667607, -65.3162485);
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(cogs)
                .radius(500)
                .strokeWidth(4f));
    }
}