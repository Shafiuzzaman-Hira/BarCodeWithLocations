package com.example.mr_kajol.barcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
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


public class Buy_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, DatePickerDialog.OnDateSetListener  {

    String[] SizeUnits = { "12 KG", "30 KG", "45 KG"};
    String[] QtyUnits = { "1", "2", "3","4","5"};


    private CardView CVDateView;
    private TextView TvDateshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Spinner SizeSpin = (Spinner) findViewById(R.id.sizeSpinner);
        Spinner QtySpin = (Spinner) findViewById(R.id.qtySpinner);

        CVDateView = findViewById(R.id.card_Date);
        TvDateshow = findViewById(R.id.datetv);

        CVDateView.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SizeUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SizeSpin.setAdapter(adapter);
        SizeSpin.setOnItemSelectedListener(this);

        ArrayAdapter<String> qtyadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, QtyUnits);
        qtyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        QtySpin.setAdapter(qtyadapter);
        QtySpin.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        //Toast.makeText(getApplicationContext(), "Selected User: "+SizeUnits[position] , Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_Date:{
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                break;
            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        //TextView textView = (TextView) findViewById(R.id.datetv);
        //textView.setText(currentDateString);
    }
}