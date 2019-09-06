package com.example.stuantlogin.AdminPage;

import android.os.Bundle;
import android.widget.SearchView;

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

public class AssessmentList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchView svAssList;
    private DatabaseReference databaseReference;
    private ArrayList<AssessmentModel> assessmentModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);


        recyclerView = findViewById(R.id.rvAssessmentList);
        svAssList = findViewById(R.id.svAssessmentList);
        databaseReference = FirebaseDatabase.getInstance().getReference("Assessment Details");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }


    protected void onStart() {
        super.onStart();
        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){

                        assessmentModels = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            assessmentModels.add(ds.getValue(AssessmentModel.class));
                        }
                        AssessmentAdapter student_list_adapter = new AssessmentAdapter(assessmentModels, AssessmentList.this);
                        recyclerView.setAdapter(student_list_adapter);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(svAssList != null){
            svAssList.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        ArrayList<AssessmentModel> myList = new ArrayList<>();
        for (AssessmentModel model : assessmentModels){
            if(model.getsName().toLowerCase().contains(newText.toLowerCase())){
                myList.add(model);
            }
            AssessmentAdapter list_adapter = new AssessmentAdapter(myList, AssessmentList.this);
            recyclerView.setAdapter(list_adapter);
        }

    }
}
