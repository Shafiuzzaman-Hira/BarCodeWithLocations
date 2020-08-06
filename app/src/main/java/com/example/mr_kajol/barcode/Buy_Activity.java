package com.example.mr_kajol.barcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;


public class Buy_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    String[] SizeUnits = { "12 KG", "30 KG", "45 KG"};
    String[] QtyUnits = { "1", "2", "3","4","5"};


    Calendar calendar = Calendar.getInstance();
    final  int year =  calendar.get(Calendar.YEAR);
    final  int month =  calendar.get(Calendar.MONTH);
    final  int day =  calendar.get(Calendar.DAY_OF_MONTH);

    DatePickerDialog.OnDateSetListener setListner;


    private CardView CVDateView;
    private TextView TvDateshow, tvdateclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Spinner SizeSpin = (Spinner) findViewById(R.id.sizeSpinner);
        Spinner QtySpin = (Spinner) findViewById(R.id.qtySpinner);

        CVDateView = findViewById(R.id.card_Date);
        tvdateclick = findViewById(R.id.tvDateclick);
        TvDateshow = findViewById(R.id.tvdateView);

      //  CVDateView.setOnClickListener(this);
        //tvdateclick.setOnClickListener(this);
        TvDateshow.setText(" "+day+"/"+month+"/"+year);
        TvDateshow.setOnClickListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SizeUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SizeSpin.setAdapter(adapter);
        SizeSpin.setOnItemSelectedListener(this);

        ArrayAdapter<String> qtyadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, QtyUnits);
        qtyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        QtySpin.setAdapter(qtyadapter);
        QtySpin.setOnItemSelectedListener(this);



        setListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = " "+day+"/"+month+"/"+year;
                TvDateshow.setText(date);
            }
        };
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        //Toast.makeText(getApplicationContext(), "Selected User: "+SizeUnits[position] , Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Cu
        //  2stom Code
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvDateclick:{
                /// this is just for testing
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Buy_Activity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListner, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                break;
            }
            case R.id.tvdateView: {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Buy_Activity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = " "+day+"/"+month+"/"+year;
                        TvDateshow.setText(date);
                    }
                },year,month,day
                );

                datePickerDialog.show();
                break;
            }
        }
    }
}