package com.example.blood_bank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.provider.Settings;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Create_account2 extends AppCompatActivity implements LocationListener{
    private Button next_id2;
    private Button button_back5;
    private TextView district_id, division_id, address_id;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationManager locationManager;
    public String district,division,full_address;

    public String name,phone,yes_no,blood,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account2);

        button_back5=(Button)findViewById(R.id.button_back5);
        button_back5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreate_account();
            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            phone = extras.getString("phone");
            yes_no = extras.getString("yes_no");
            blood=extras.getString("blood");
            password=extras.getString("password");


        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }


//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        next_id2 = (Button) findViewById(R.id.next_id2);
        next_id2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_Verify_and_save_data();
            }
        });

        district_id = (TextView) findViewById(R.id.district_id);
        division_id = (TextView) findViewById(R.id.division_id);

        address_id = (TextView) findViewById(R.id.address_id);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationEnabled();
        getLocation();



    }






    public void open_Verify_and_save_data() {

        if(district==null&&division==null&&full_address==null){
            Toast.makeText(Create_account2.this,"Wait",Toast.LENGTH_LONG).show();


        }else {

            Intent intent = new Intent(Create_account2.this, Verify_and_save_data.class);

            intent.putExtra("jela", district);
            intent.putExtra("bivag", division);
            intent.putExtra("thikana", full_address);

            intent.putExtra("name", name);
            intent.putExtra("phone", phone);
            intent.putExtra("blood", blood);
            intent.putExtra("yes_no", yes_no);
            intent.putExtra("password", password);


            startActivity(intent);
        }

    }
    public void openCreate_account(){
        Intent intent =new Intent(this,Create_account.class);
        startActivity(intent);
    }






    private void locationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(Create_account2.this)
                    .setTitle("Enable GPS Service")
                    .setMessage("We need your GPS location to show Near Places around you.")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }





    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            district=addresses.get(0).getSubAdminArea();
            division=addresses.get(0).getAdminArea();
            full_address=addresses.get(0).getAddressLine(0);

            district_id.setText(district);

            division_id.setText(division);

            address_id.setText(full_address);





        } catch (Exception e) {
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }



}