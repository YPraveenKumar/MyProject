package com.example.stuantlogin.TimeTableFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.stuantlogin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MonFragment extends Fragment {

    private TextView tvHr1, tvHr2, tvHr3, tvHr4, tvHr5, tvHr6, tvHr7, tvHr8;
    private DatabaseReference databaseReference, uid;
    private String sHr1, sHr2, sHr3, sHr4, sHr5, sHr6, sHr7, sHr8;
    private String firebaseAuth, std;

    public MonFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.mon_fragment, container, false);

         tvHr1 = view.findViewById(R.id.tvMDFirstPer);
         tvHr2 = view.findViewById(R.id.tvMDSecondPer);
         tvHr3 = view.findViewById(R.id.tvMDThirdPer);
         tvHr4 = view.findViewById(R.id.tvMDfourthPer);
         tvHr5 = view.findViewById(R.id.tvMDfifthPer);
         tvHr6 = view.findViewById(R.id.tvMDsixthPer);
         tvHr7 = view.findViewById(R.id.tvMDSeventhPer);
         tvHr8 = view.findViewById(R.id.tvMDEighPer);
/*
        dayList.add("MonDay");
        dayList.add("TuesDay");
        dayList.add("WednesDay");
        dayList.add("ThursDay");
        dayList.add("FriDay");*/

        firebaseAuth = FirebaseAuth.getInstance().getUid();
        uid = FirebaseDatabase.getInstance().getReference(firebaseAuth).child("StudentProfile");
        uid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                std = dataSnapshot.child("standard").getValue().toString();

                databaseReference = FirebaseDatabase.getInstance().getReference("TimeTable").child(std).child("MonDay");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        sHr1 = dataSnapshot.child("sHr1").getValue().toString();
                        sHr2 = dataSnapshot.child("sHr2").getValue().toString();
                        sHr3 = dataSnapshot.child("sHr3").getValue().toString();
                        sHr4 = dataSnapshot.child("sHr4").getValue().toString();
                        sHr5 = dataSnapshot.child("sHr5").getValue().toString();
                        sHr6 = dataSnapshot.child("sHr6").getValue().toString();
                        sHr7 = dataSnapshot.child("sHr7").getValue().toString();
                        sHr8 = dataSnapshot.child("sHr8").getValue().toString();

                        tvHr1.setText(sHr1);
                        tvHr2.setText(sHr2);
                        tvHr3.setText(sHr3);
                        tvHr4.setText(sHr4);
                        tvHr5.setText(sHr5);
                        tvHr6.setText(sHr6);
                        tvHr7.setText(sHr7);
                        tvHr8.setText(sHr8);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
