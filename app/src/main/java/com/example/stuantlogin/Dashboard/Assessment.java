package com.example.stuantlogin.Dashboard;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Assessment extends AppCompatActivity {

    private TextView tvName, tvRollNo, tvTerm, tvSub1, tvSub2, tvSub3, tvTotal, tvPercentage, tvGrade, tvRecommendation, tvActivity, tvAttendance;
    private String sName, sRollNo, sTerm, sSub1, sSub2, sSub3, sTotal, sPercentage, sGrade, sRecommendation, sStuActivity, sAttendance;
    private DatabaseReference databaseReference, uid;
    private String firebaseAuth, Uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        tvName = findViewById(R.id.tvSAssName);
        tvRollNo = findViewById(R.id.tvSAssRollNo);
        tvTerm = findViewById(R.id.tvSAssTerm);
        tvSub1 = findViewById(R.id.tvSAssSub1);
        tvSub2 = findViewById(R.id.tvSAssSub2);
        tvSub3 = findViewById(R.id.tvSAssSub3);
        tvTotal = findViewById(R.id.tvSAssTotal);
        tvPercentage = findViewById(R.id.tvSAssPercent);
        tvGrade = findViewById(R.id.tvSAssGrade);
        tvRecommendation = findViewById(R.id.tvSAssRecom);
        tvAttendance = findViewById(R.id.tvSAssAttendance);
        tvActivity = findViewById(R.id.tvSAssActivity);

        firebaseAuth = FirebaseAuth.getInstance().getUid();

    }


    @Override
    protected void onStart() {
        super.onStart();

        uid = FirebaseDatabase.getInstance().getReference(firebaseAuth).child("StudentProfile");
        uid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Uuid = dataSnapshot.child("rollNo").getValue().toString();

                databaseReference = FirebaseDatabase.getInstance().getReference("Assessment Details").child(Uuid);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        sAttendance = dataSnapshot.child("sAttendance").getValue().toString();
                        sGrade = dataSnapshot.child("sGrade").getValue().toString();
                        sName = dataSnapshot.child("sName").getValue().toString();
                        sPercentage = dataSnapshot.child("sPercentage").getValue().toString();
                        sRecommendation = dataSnapshot.child("sRecommendation").getValue().toString();
                        sRollNo = dataSnapshot.child("sRollNo").getValue().toString();
                        sStuActivity = dataSnapshot.child("sStuActivity").getValue().toString();
                        sSub1 = dataSnapshot.child("sSub1").getValue().toString();
                        sSub2 = dataSnapshot.child("sSub2").getValue().toString();
                        sSub3 = dataSnapshot.child("sSub3").getValue().toString();
                        sTerm = dataSnapshot.child("sTerm").getValue().toString();
                        sTotal = dataSnapshot.child("sTotal").getValue().toString();

                        tvName.setText(sName);
                        tvRollNo.setText(sRollNo);
                        tvTerm.setText(sTerm);
                        tvSub1.setText(sSub1);
                        tvSub2.setText(sSub2);
                        tvSub3.setText(sSub3);
                        tvTotal.setText(sTotal);
                        tvPercentage.setText(sPercentage);
                        tvGrade.setText(sGrade);
                        tvRecommendation.setText(sRecommendation);
                        tvAttendance.setText(sAttendance);
                        tvActivity.setText(sStuActivity);
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
    }

}
