package com.example.fooddelivery.activities.avtivities;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.SharedPrefs;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    boolean isPermissionGranted;
    GoogleMap mGoogleMap;
    FloatingActionButton fab;
    FloatingActionButton next;
    private FusedLocationProviderClient mloctionClient;
    private int GPS_REQUEST_CODE=9001;
    EditText locsearch;
    ImageView searchIcon;
   static String Location;
   public static String getLocation(){
       return Location;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        fab=findViewById(R.id.fab);
        next=findViewById(R.id.Next);
        locsearch=findViewById(R.id.search);
        searchIcon=findViewById(R.id.search_icon);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(LastOrderActivity.geteditkey()!=null){
                    Intent i=new Intent(LocationActivity.this,LastOrderActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                else {
                Intent i=new Intent(LocationActivity.this,photoActivity.class);
                startActivity(i);}
            }
        });
        checkMyPermission();
        initMap();

        mloctionClient = new FusedLocationProviderClient(this);
        getCurrLoc();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getCurrLoc();
            }
        });
        searchIcon.setOnClickListener(this::geoLocate);

    }

    private void geoLocate(View view) {
        String locationName=locsearch.getText().toString();
        Geocoder geocoder=new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addressList=geocoder.getFromLocationName(locationName,1);
            if(addressList.size()>0){
                Address address=addressList.get(0);
                gotoLocation(address.getLatitude(),address.getLongitude());
                mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude(),address.getLongitude())));
                Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initMap() {
        if (isPermissionGranted) {
            if(isGPSenable()){
                SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                supportMapFragment.getMapAsync(this);
            }
        }
    }

    private boolean isGPSenable(){
        LocationManager locationManager=(LocationManager) getSystemService(LOCATION_SERVICE);
        boolean providerEnable=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (providerEnable){
            return true;
        }
        else {
            AlertDialog alertDialog=new AlertDialog.Builder(this)
                    .setTitle("GPS Permission")
                    .setMessage("GPS is required for this app to work .please enable GPS")
                    .setPositiveButton("yes",((dialogInterface,i) ->{
                        Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent,GPS_REQUEST_CODE);

                    })).setCancelable(false).show();
        }
        return false;
    }
    @SuppressLint("MissingPermission")
    private void getCurrLoc() {
        mloctionClient.getLastLocation().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Location location = task.getResult();
                gotoLocation(location.getLatitude(), location.getLongitude());
            }
        });
    }

    private void gotoLocation(double latitude, double longitude) {
        LatLng LatLng=new LatLng(latitude,longitude);
        MarkerOptions options = new MarkerOptions();
        options.position(LatLng);
        options.title("Current Position");
        mGoogleMap.addMarker(options);
        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(LatLng,20);
        mGoogleMap.moveCamera(cameraUpdate);
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        Geocoder geocoder=new Geocoder(this, Locale.getDefault());

        try {
            List<Address> myaddresses=geocoder.getFromLocation(LatLng.latitude,LatLng.longitude,5);
            String address= myaddresses.get(0).getAddressLine(0);
            String city=myaddresses.get(0).getLocality();

            locsearch.setText(address+" "+city);
            Location= String.valueOf(locsearch.getText());
            SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
            sharedPrefs.setLocation(Location);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void checkMyPermission() {

        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener(){
            public void onPermissionGranted (PermissionGrantedResponse permissionGrantedPesponse) {
                Toast.makeText(LocationActivity.this,"permission Granted",Toast.LENGTH_SHORT).show();
                isPermissionGranted=true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",getPackageName(),"");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }

        }).check();

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap=googleMap;
        // mGoogleMap.setMyLocationEnabled(true);



//        String locationName=locsearch.getText().toString();
//Geocoder geocoder=new Geocoder(this, Locale.getDefault());
//
//        try {
//            List<Address> addressList=geocoder.getFromLocationName(locationName,1);
//            if(addressList.size()>0){
//                Address address=addressList.get(0);
//                gotoLocation(address.getLatitude(),address.getLongitude());
//                mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude(),address.getLongitude())));
//                Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GPS_REQUEST_CODE){
            LocationManager locationManager=(LocationManager) getSystemService(LOCATION_SERVICE);
            boolean providerEnable =locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (providerEnable){
                Toast.makeText(this,"GPS is enable",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this,"GPS is not enable",Toast.LENGTH_SHORT).show();
            }
        }
    }}
