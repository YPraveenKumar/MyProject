package com.example.stuantlogin.AdminPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.Dashboard.TransportModel;
import com.example.stuantlogin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StuCornerAdmin extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private ArrayList<TransportModel> stuCornerModel;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_corner_admin);

        searchView = findViewById(R.id.svStuCorAdmin);
        recyclerView = findViewById(R.id.rvStuCorner);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference("StudentCorner");

    }
    protected void onStart() {
        super.onStart();
        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){

                        stuCornerModel = new ArrayList<>();
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren() ){
                            stuCornerModel.add(dataSnapshot1.getValue(TransportModel.class));
                        }
                        StudentCornerAdapter cornerAdapter = new StudentCornerAdapter(StuCornerAdmin.this, stuCornerModel);
                        recyclerView.setAdapter(cornerAdapter);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    private void search(String newText) {
        ArrayList<TransportModel> myList = new ArrayList<>();
        for (TransportModel model : stuCornerModel) {
            if (model.getName().toLowerCase().contains(newText.toLowerCase())) {
                myList.add(model);
            }
            StudentCornerAdapter adapter = new StudentCornerAdapter(StuCornerAdmin.this, myList);
            recyclerView.setAdapter(adapter);
        }
    }
}
