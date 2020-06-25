package com.example.mr_kajol.barcode;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

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

                break;
               }
            case R.id.cardview_scan:{

                    Intent i = new Intent(HomePage.this, MainActivity.class);
                    startActivity(i);
                    break;
            }
            case R.id.cardview_offer:{

                    Intent i = new Intent(HomePage.this, LoginPage.class);
                    startActivity(i);
                break;
            }
            case R.id.cardview_delar:{

            }
        }
    }
}