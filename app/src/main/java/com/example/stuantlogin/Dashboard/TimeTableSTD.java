package com.example.stuantlogin.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.AdminPage.TimeTableView;
import com.example.stuantlogin.R;

import java.util.ArrayList;

public class TimeTableSTD extends AppCompatActivity {

    private Spinner spnStuStd;
    private String stringSTD;
    private ArrayList<String> stdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_std);

        spnStuStd = findViewById(R.id.spnStuStandard);

        stdList = new ArrayList<>();
        stdList.add("Select Standard");
        stdList.add("FIRST Std");
        stdList.add("SECOND Std");
        stdList.add("THIRD Std");
        stdList.add("FOURTH Std");
        stdList.add("FIFTH Std");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(TimeTableSTD.this, android.R.layout.simple_spinner_item, stdList);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnStuStd.setAdapter(adapter);

        spnStuStd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringSTD = spnStuStd.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void ViewTimeTable(View view) {
        Intent vIntent = new Intent(this, TimeTableView.class);
        vIntent.putExtra("STD", stringSTD);
        startActivity(vIntent);
    }
}
