package com.example.mr_kajol.barcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView zXingScannerView;
    int MY_PERMISSIONS_REQUEST_CAMERA = 0;
    private FusedLocationProviderClient client;
    String latitude, longitude;
    private IScannerService scannerService;
    private static final String TAG = "Log";
    private TextView mResponseTv;
    private static final String BASE_URL = "https://cylinder-tracker-web.el.r.appspot.com/";

  //  private static final String BASE_URL = "https://192.168.43.11/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(ScanActivity.this);
        setContentView(zXingScannerView);


        scannerService = ApiUtils.getScannerService();
        Intent intent = getIntent();
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");

       /*  FirebaseDatabase data = FirebaseDatabase.getInstance();
         DatabaseReference MyRefff = data.getReference("Locations");

         MyRefff.push().setValue(str);*/

    }

    private void  sendScannedData(String sku, String latitude, String longitude) {

        Gson gson = new GsonBuilder().create();

        TrackerService service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(TrackerService.class);

        Map<String, String> mapdata = new HashMap<>();
        mapdata.put("longitude", longitude);
        mapdata.put("latitude", latitude);
        mapdata.put("sku", sku);
        // call the method we defined in our interface, and handle the result
        service.getAllUsers(mapdata).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.println(response.code());
                List users = (List) response.body();
                //tvlocation.append(" " + users.get(0).equals("name"));
                for (int i=0; i<2; i++)
                {
                    User u = (User) users.get(i);
                   // tvlocation.append(" " + u.getName());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Something went wrong!");
            }
        });
    }

    @Override
    public void handleResult(Result result) {
        MainActivity.tvShowScanned.setText(result.getText());
        if(!TextUtils.isEmpty(result.getText()) && !TextUtils.isEmpty(latitude) && !TextUtils.isEmpty(longitude)) {
            sendScannedData(result.getText(), latitude, longitude);
        }
                onBackPressed();
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
    }

}
