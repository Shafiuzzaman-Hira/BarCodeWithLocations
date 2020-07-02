package com.example.mr_kajol.barcode;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = "Error";
    public static Button btnScanCode;
    public static TextView tvShowScanned, tvlocation;
    private FusedLocationProviderClient client;
    private SenderService mAPIService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScanCode = findViewById(R.id.buttoncameraclicked);
        tvShowScanned = findViewById(R.id.tvScanText);
        tvlocation = findViewById(R.id.tv_Location);

        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);

       btnScanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        tvShowScanned = findViewById(R.id.tvScanText);
                        if(location != null){
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            String lat = Double.toString(latitude);
                            String lang = Double.toString(longitude);

                            tvlocation.append("\nLatitude = "+lat +"\nLongitude = " + lang);

                            Intent intent = new Intent(MainActivity.this, ScanActivity.class);
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
}
