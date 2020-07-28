package com.example.mr_kajol.barcode;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    CardView cardView_buy,cardView_scan, cardView_offer,cardView_delar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        cardView_buy = findViewById(R.id.cardview_buy);
        cardView_scan = findViewById(R.id.cardview_scan);
        cardView_offer = findViewById(R.id.cardview_offer);
        cardView_delar = findViewById(R.id.cardview_delar);

        cardView_buy.setOnClickListener(this);
        cardView_scan.setOnClickListener(this);
        cardView_offer.setOnClickListener(this);
        cardView_delar.setOnClickListener(this);

         mAuth = FirebaseAuth.getInstance();




    /*    if (mAuth.getCurrentUser() != null) {
            //handle the already login user
            Intent i = new Intent(HomePage.this, LoginPage.class);
            startActivity(i);
        }*/


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.cardview_buy:{
                Intent i = new Intent(HomePage.this, Buy_Activity.class);
                startActivity(i);
                break;
               }
            case R.id.cardview_scan:{
                            Intent intent = new Intent(HomePage.this, ScanPage.class);
                            startActivity(intent);
                    break;
            }
            case R.id.cardview_offer:{

                    Intent i = new Intent(HomePage.this, LoginPage.class);
                    startActivity(i);
                break;
            }
            case R.id.cardview_delar:{
                Intent i = new Intent(HomePage.this, NearestDelar.class);
                startActivity(i);
                break;
            }
        }
    }

}