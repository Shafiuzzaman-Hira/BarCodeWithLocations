package com.example.mr_kajol.barcode;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registration extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword, editText_confirm_Password, editTextPhone, editTextAddress;
    CheckBox checkBox;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //get ID and data from the xml file

        editTextName = findViewById(R.id.edit_text_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editText_confirm_Password = findViewById(R.id.edit_text_password_cnf);
        editTextAddress = findViewById(R.id.edit_text_address);
        editTextPhone = findViewById(R.id.edit_text_phone);

        checkBox = findViewById(R.id.chkBox1);

        mAuth = FirebaseAuth.getInstance();

        registerUser();

    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String ConfirmPassword = editText_confirm_Password.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();
        final String address = editTextAddress.getText().toString().trim();




        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }
        if (address.isEmpty()) {
                    editTextAddress.setError(getString(R.string.input_error_address));
                    editTextAddress.requestFocus();
                    return;
                }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError(getString(R.string.input_error_phone));
            editTextPhone.requestFocus();
            return;
        }
        if (phone.length() != 11) {
            editTextPhone.setError(getString(R.string.input_error_phone_invalid));
            editTextPhone.requestFocus();
            return;
        }

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        final String date = formattedDate.toString();



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {


                            CreateAccountClass user = new CreateAccountClass(
                                    name,
                                    address,
                                    email,
                                    phone,
                                    password,
                                    date
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Registration.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();

                                        Intent i = new Intent(Registration.this, HomePage.class);
                                        startActivity(i);
                                    } else {
                                        //display a failure message
                                        Toast.makeText(Registration.this, "Registration Failed", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}