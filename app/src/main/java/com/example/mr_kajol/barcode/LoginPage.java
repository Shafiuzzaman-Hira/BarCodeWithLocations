package com.example.mr_kajol.barcode;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    private Button EnterButton, SignupButton;
    private EditText UserName,UserPassword;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        UserName =  findViewById(R.id.LoginUserName);
        UserPassword =  findViewById(R.id.LoginUserPass);
        EnterButton =  findViewById(R.id.loginbtn);
        SignupButton =  findViewById(R.id.signupbtn);


        EnterButton.setOnClickListener(this);
        SignupButton.setOnClickListener(this);



        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
            Intent i = new Intent(LoginPage.this, HomePage.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.loginbtn: {

                String username = UserName.getText().toString();
                String UserPass = UserPassword.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LoginPage.this, "Enter Valid UserName", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(UserPass)) {
                    Toast.makeText(LoginPage.this, "Enter the correct password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(username,UserPass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                //if the task is successfull
                                if(task.isSuccessful()){
                                    //start the profile activity
                                    Intent i = new Intent(LoginPage.this, HomePage.class);
                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    Toast.makeText(LoginPage.this, "Email Pass Invalid", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                break;
            }
            case R.id.signupbtn:{

                Intent i = new Intent(LoginPage.this, Registration.class);
                startActivity(i);
                break;
            }

        }

    }
}