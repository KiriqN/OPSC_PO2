package com.example.opsc_poe;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Map extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnPolygonClickListener,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap map;
    public Marker marker;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    SupportMapFragment supportMapFragment;
    private static final int REQUEST_CODE = 101;
    Dialog mDialog;


    int lat;

    int log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mDialog = new Dialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        lat = getIntent().getIntExtra("lat", -34);
        log = getIntent().getIntExtra("log", 18);

        Toast.makeText(this, "it is " + lat, Toast.LENGTH_SHORT).show();

        SupportMapFragment map_frag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        assert map_frag != null;
        map_frag.getMapAsync(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Map.this);

        if (ActivityCompat.checkSelfPermission(Map.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(Map.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


    }

    private void getCurrentLocation() {
        @SuppressLint("MissingPermission") Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    Toast.makeText(Map.this, "the location is here", Toast.LENGTH_SHORT).show();
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {

                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                            MarkerOptions options = new MarkerOptions().position(latLng)
                                    .title("Here you are");

                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                            googleMap.addMarker(options);

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getCurrentLocation();

            }
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {


        // Add a marker in Sydney and move the camera


        //LatLng waterFront = new LatLng(-33.90656762931461, 18.41935132097025);
        //googleMap.addMarker(new MarkerOptions().position(waterFront).title("VA Waterfront"));
        final LatLng VaWaterFront = new LatLng(-33.90656762931461, 18.41935132097025);
        Marker waterFront = googleMap.addMarker(new MarkerOptions()
                .position(VaWaterFront)
                .title("VA Waterfront the go to mall of cape town")
                .snippet("Ratings: 4/5 ⭐⭐⭐⭐"));
                 waterFront.showInfoWindow();
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(waterFront));

        final LatLng Castle = new LatLng(-33.92596585949937, 18.428079555795414);
        Marker CastleOfGoodHope = googleMap.addMarker(new MarkerOptions()
                .position(Castle)
                .title("The castle of cape town")
                .snippet("Ratings: 4/5 ⭐⭐⭐⭐  A Cape Town landmark"));
        waterFront.showInfoWindow();

        final LatLng gardens = new LatLng(-33.987056303006554, 18.432850744600135);
        Marker botanicalGardens = googleMap.addMarker(new MarkerOptions()
                .position(gardens)
                .title("Botanical Gardens")
                .snippet("Ratings: 5/5 ⭐⭐⭐⭐⭐  The Cape Gardens"));
        waterFront.showInfoWindow();




        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-35.016, 143.321),
                        new LatLng(-34.747, 145.592),
                        new LatLng(-34.364, 147.891),
                        new LatLng(-33.501, 150.217),
                        new LatLng(-32.306, 149.248),
                        new LatLng(-32.491, 147.309)));

        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationClickListener(this);
        googleMap.setOnInfoWindowClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);




             }



             public void OnMarkerClick() {

         }

    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
            return;
        }


    }


             @Override
             public boolean onMarkerClick(@NonNull Marker marker) {

                 Toast.makeText(this, "My Position" + marker.getPosition(), Toast.LENGTH_SHORT).show();
                 mDialog.setContentView(R.layout.popup);
                 mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                 return false;
             }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onPolygonClick(@NonNull Polygon polygon) {

    }

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }
}