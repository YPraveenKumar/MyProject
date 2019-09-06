package com.example.stuantlogin.AdminPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.Dashboard.ProgRegModel;
import com.example.stuantlogin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProgramDetails extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private DatabaseReference databaseReference;
    private ArrayList<ProgRegModel> progRegModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_details);

        recyclerView = findViewById(R.id.rvPgmDetails);
        searchView = findViewById(R.id.svPgmDet);
        databaseReference = FirebaseDatabase.getInstance().getReference("ProgReg");
        progRegModels = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        progRegModels.add(ds.getValue(ProgRegModel.class));
                    }
                    SignleTextViewAdapter adapter =  new SignleTextViewAdapter(ProgramDetails.this, progRegModels);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    searchText(newText);
                    return true;
                }
            });
        }
    }

    private void searchText(String newText) {
        ArrayList<ProgRegModel> nameList = new ArrayList<>();
        for(ProgRegModel model:nameList){
            if(model.getpID().toLowerCase().contains(newText.toLowerCase())){
                nameList.add(model);

            }
            SignleTextViewAdapter textViewAdapter = new SignleTextViewAdapter(ProgramDetails.this, nameList);
            recyclerView.setAdapter(textViewAdapter);
        }
    }
}
