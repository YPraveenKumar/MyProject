package com.example.stuantlogin.AdminPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import android.widget.SearchView;

//import androidx.appcompat.widget.SearchView;

public class BillHistory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchView svBillList;
    private DatabaseReference databaseReference;
    private ArrayList<BillingModel> billingModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_history);

        recyclerView = findViewById(R.id.rvBillList);
        svBillList = (SearchView) findViewById(R.id.svBillList);
        databaseReference = FirebaseDatabase.getInstance().getReference("Billing Details");


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    protected void onStart() {
        super.onStart();
        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){

                        billingModels = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            billingModels.add(ds.getValue(BillingModel.class));
                        }
                        BillingAdapter billingAdapter = new BillingAdapter(billingModels, BillHistory.this);
                        recyclerView.setAdapter(billingAdapter);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(svBillList != null){
            svBillList.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        ArrayList<BillingModel> myList = new ArrayList<>();
        for (BillingModel model : billingModels){
            if(model.getsName().toLowerCase().contains(newText.toLowerCase())){
                myList.add(model);
            }
            BillingAdapter billingAdapter = new BillingAdapter(myList, BillHistory.this);
            recyclerView.setAdapter(billingAdapter);
        }

    }
}
