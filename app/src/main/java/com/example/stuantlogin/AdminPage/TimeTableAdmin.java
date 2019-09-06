package com.example.stuantlogin.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.Dashboard.TimeTableSTD;
import com.example.stuantlogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TimeTableAdmin extends AppCompatActivity {

    private Spinner spnStandard, spnDay;
    private EditText etHr1, etHr2, etHr3, etHr4, etHr5, etHr6, etHr7, etHr8;
    private DatabaseReference databaseReference;
    private Button btnSave;
    private String sStd, sDay, sHr1, sHr2, sHr3, sHr4, sHr5, sHr6, sHr7, sHr8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_admin);

        spnStandard = findViewById(R.id.spnStandard);
        spnDay = findViewById(R.id.spnDay);
        etHr1 = findViewById(R.id.etMDFirst);
        etHr2 = findViewById(R.id.etMDSecond);
        etHr3 = findViewById(R.id.etMDThird);
        etHr4 = findViewById(R.id.etMDFourth);
        etHr5 = findViewById(R.id.etMDFifth);
        etHr6 = findViewById(R.id.etMDSixth);
        etHr7 = findViewById(R.id.etMDSeventh);
        etHr8 = findViewById(R.id.etMDEighth);
        btnSave = findViewById(R.id.btnAdminTimeTable);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("TimeTable");

        if(getIntent() != null){
            etHr1.setText(getIntent().getStringExtra("Hr1"));
            etHr2.setText(getIntent().getStringExtra("Hr2"));
            etHr3.setText(getIntent().getStringExtra("Hr3"));
            etHr4.setText(getIntent().getStringExtra("Hr4"));
            etHr5.setText(getIntent().getStringExtra("Hr5"));
            etHr6.setText(getIntent().getStringExtra("Hr6"));
            etHr7.setText(getIntent().getStringExtra("Hr7"));
            etHr8.setText(getIntent().getStringExtra("Hr8"));
        }


        ArrayList<String> stdList = new ArrayList<>();
        stdList.add("Select Standard");
        stdList.add("FIRST Std");
        stdList.add("SECOND Std");
        stdList.add("THIRD Std");
        stdList.add("FOURTH Std");
        stdList.add("FIFTH Std");

        ArrayAdapter<String> stdAdapter = new ArrayAdapter<>(TimeTableAdmin.this, android.R.layout.simple_list_item_1, stdList);
        spnStandard.setAdapter(stdAdapter);

        ArrayList<String> dayList = new ArrayList<>();
        dayList.add("Select Day");
        dayList.add("MonDay");
        dayList.add("TuesDay");
        dayList.add("WednesDay");
        dayList.add("ThursDay");
        dayList.add("FriDay");


        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(TimeTableAdmin.this, android.R.layout.simple_list_item_1, dayList);
        spnDay.setAdapter(dayAdapter);


        spnStandard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    sStd = spnStandard.getSelectedItem().toString();
                    Toast.makeText(TimeTableAdmin.this, stdList.get(position), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    spnDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position != 0) {
                sDay = spnDay.getSelectedItem().toString();
                Toast.makeText(TimeTableAdmin.this, dayList.get(position), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spnStandard.getSelectedItem().toString().trim().equals("Select Standard") || spnDay.getSelectedItem().toString().trim().equals("Select Day")) {
                    Toast.makeText(TimeTableAdmin.this, "Please select the Day & Standard", Toast.LENGTH_SHORT).show();
                }
                    else{
                        if (isTTAbleValidated()) {
                            TimeTableModule module = new TimeTableModule(sHr1, sHr2, sHr3, sHr4, sHr5, sHr6, sHr7, sHr8);

                            databaseReference.child(sStd).child(sDay).setValue(module).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(TimeTableAdmin.this, "Time Table Saved Successfully...", Toast.LENGTH_SHORT).show();

                                        etHr1.setText("");
                                        etHr2.setText("");
                                        etHr3.setText("");
                                        etHr4.setText("");
                                        etHr5.setText("");
                                        etHr6.setText("");
                                        etHr7.setText("");
                                        etHr8.setText("");
                                    } else {
                                        Toast.makeText(TimeTableAdmin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                }

        });
    }
    private boolean isTTAbleValidated() {
        boolean result = false;

        sHr1 = etHr1.getText().toString().trim();
        sHr2 = etHr2.getText().toString().trim();
        sHr3 = etHr3.getText().toString().trim();
        sHr4 = etHr4.getText().toString().trim();
        sHr5 = etHr5.getText().toString().trim();
        sHr6 = etHr6.getText().toString().trim();
        sHr7 = etHr7.getText().toString().trim();
        sHr8 = etHr8.getText().toString().trim();

        if(sHr1.isEmpty() || sHr2.isEmpty() || sHr3.isEmpty() || sHr4.isEmpty() || sHr5.isEmpty() || sHr6.isEmpty() || sHr7.isEmpty() || sHr8.isEmpty()){
            Toast.makeText(this, "Please Enter Details...", Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }

    public void viewTimeTable(View view) {
        startActivity(new Intent(TimeTableAdmin.this, TimeTableSTD.class));
    }
}
