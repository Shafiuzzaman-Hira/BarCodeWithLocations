package com.example.mr_kajol.barcode;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class ScanPage extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Error";
    public static Button btnScanCode, backtohome;
    public static TextView tvShowScanned, tvlocation;
    private FusedLocationProviderClient client;
    private ISenderService mAPIService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScanCode = findViewById(R.id.buttoncameraclicked);
        tvShowScanned = findViewById(R.id.tvScanText);
        tvlocation = findViewById(R.id.tv_Location);
        backtohome = findViewById(R.id.backtohome);

        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);

        backtohome.setOnClickListener(this);

       btnScanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(ScanPage.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                client.getLastLocation().addOnSuccessListener(ScanPage.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        tvShowScanned = findViewById(R.id.tvScanText);
                        if(location != null){
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            String lat = Double.toString(latitude);
                            String lang = Double.toString(longitude);

                           // tvlocation.append("\nLatitude = "+lat +"\nLongitude = " + lang);

                            Intent intent = new Intent(ScanPage.this, ScanActivity.class);
                            intent.putExtra("latitude", lat);
                            intent.putExtra("longitude", lang);
                            startActivity(intent);
                        }
                    }

                    @NonNull
                    private OnSuccessListener<Location> getOnSuccessListener() {
                        return this;
                    }
                });
            }
        });
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.backtohome:
                {
                    startActivity(new Intent(ScanPage.this, HomePage.class));
                    break;
                }
            }
    }
}
