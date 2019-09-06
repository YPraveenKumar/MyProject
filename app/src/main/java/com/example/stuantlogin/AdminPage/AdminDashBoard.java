package com.example.stuantlogin.AdminPage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.R;

import java.util.ArrayList;

public class AdminDashBoard extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);

        recyclerView = findViewById(R.id.rvAdmin);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("Student Registration");
        nameList.add("Assessment Entray");
        nameList.add("Assessment Details");
        nameList.add("StudentList");
        nameList.add("Billing Details");
        nameList.add("Billing History");
        nameList.add("Announcement");
        nameList.add("Sudent TimeTable");
        nameList.add("Sudent Corner");
        nameList.add("Program Details");

        ArrayList<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.student_register);
        imageList.add(R.drawable.assessment);
        imageList.add(R.drawable.assement_list);
        imageList.add(R.drawable.stu_list);
        imageList.add(R.drawable.biling);
        imageList.add(R.drawable.billing_list);
        imageList.add(R.drawable.announcements);
        imageList.add(R.drawable.time_table);
        imageList.add(R.drawable.student_corner);
        imageList.add(R.drawable.program_registration);

        Admin_Adapter admin_adapter = new Admin_Adapter(this, nameList, imageList);
        recyclerView.setAdapter(admin_adapter);
        admin_adapter.notifyDataSetChanged();

    }
}
