package com.example.mr_kajol.barcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static Button btnScanCode;
    public static TextView tvShowScanned, tvlocation;
    private FusedLocationProviderClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScanCode = findViewById(R.id.buttoncameraclicked);
        tvShowScanned = findViewById(R.id.tvScanText);
        tvlocation = findViewById(R.id.tv_Location);

        btnScanCode.setOnClickListener(this);

        requestPersmission();
        client = LocationServices.getFusedLocationProviderClient(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttoncameraclicked: {

                String lat, lang;
                if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if(location != null){
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            String lat = Double.toString(latitude);
                            String lang = Double.toString(longitude);

                            tvlocation.setText("\nLatitude = "+lat +"\nLongitude = " + lang);

                            // Write a message to the database
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("Locations");
                            myRef.push().setValue(location);


                            Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                            intent.putExtra("data", location);
                            startActivity(intent);


                    }
                    }
                });
            }

        }
    }

    private void requestPersmission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
    }
}
