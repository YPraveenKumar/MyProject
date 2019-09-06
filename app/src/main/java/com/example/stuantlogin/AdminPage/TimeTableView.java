package com.example.stuantlogin.AdminPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TimeTableView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<TimeTableModule> timeTableModules;
    private DatabaseReference databaseReference;
    private String sStd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_view);

        recyclerView = findViewById(R.id.rvViewTTable);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        timeTableModules = new ArrayList<>();

        if(getIntent() != null ){
            sStd = getIntent().getStringExtra("STD");
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("TimeTable").child(sStd);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        timeTableModules.add(ds.getValue(TimeTableModule.class));
                    }
                    TimeTableViewAdapter viewAdapter = new TimeTableViewAdapter(TimeTableView.this, timeTableModules);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
