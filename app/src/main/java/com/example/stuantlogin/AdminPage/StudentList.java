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

//import androidx.appcompat.widget.SearchView;

public class StudentList extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private ArrayList<StudentListModel> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);


        searchView = findViewById(R.id.svStuList);
        recyclerView = findViewById(R.id.rvStuList);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("StudentRegistration");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){

                        studentList = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            studentList.add(ds.getValue(StudentListModel.class));
                        }
                        Student_List_Adapter student_list_adapter = new Student_List_Adapter(StudentList.this, studentList);
                        recyclerView.setAdapter(student_list_adapter);
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
        ArrayList<StudentListModel> myList = new ArrayList<>();
        for (StudentListModel studentListModel : studentList){
            if(studentListModel.getsName().toLowerCase().contains(newText.toLowerCase())){
                myList.add(studentListModel);
            }
            Student_List_Adapter list_adapter = new Student_List_Adapter(StudentList.this, myList);
            recyclerView.setAdapter(list_adapter);
        }

    }
}
