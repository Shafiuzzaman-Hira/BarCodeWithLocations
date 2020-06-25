package com.example.mr_kajol.barcode;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String BASE_URL = "https://cylinder-tracker-web.el.r.appspot.com/";
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

        btnScanCode.setOnClickListener(this);

        requestPersmission();
        client = LocationServices.getFusedLocationProviderClient(this);


        getData();
        //postData();
        sendPost("kajol","mostafijurj@gmail.com");
    }

    private void  getData() {

        // create an instance of gson to be used when building our service
        Gson gson = new GsonBuilder().create();

        // use retrofit to create an instance of BookService
        TrackerService service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(TrackerService.class);

        // call the method we defined in our interface, and handle the result
        service.getAllUsers().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.println(response.code());
                List users = (List) response.body();
                tvlocation.append(" " + users.get(0).equals("name"));
                for (int i=0; i<2; i++)
                {
                   User u = (User) users.get(i);
                    tvlocation.append(" " + u.getName());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Something went wrong!");
            }


        });
    }

    public void sendPost(String title, String body) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            SenderService senderService =  retrofit.create(SenderService.class);

            Data data = new Data(title,body);
            Call<Data> call = senderService.sendPost(data);
            call.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                  //  tvlocation.append(response.body().getEmail());
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {

                }
            });

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

                            tvlocation.append("\nLatitude = "+lat +"\nLongitude = " + lang);

                            // Write a message to the database
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("Locations");
                            myRef.push().setValue(location);


                            Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                            intent.putExtra("data", location);
                            startActivity(intent);


                    }
                    }

                    @NonNull
                    private OnSuccessListener<Location> getOnSuccessListener() {
                        return this;
                    }
                });
            }

        }
    }

    private void requestPersmission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
    }
}
