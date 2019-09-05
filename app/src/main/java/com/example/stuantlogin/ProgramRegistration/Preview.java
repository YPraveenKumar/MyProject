package com.example.stuantlogin.ProgramRegistration;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.Dashboard.ProgramReg;
import com.example.stuantlogin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Preview extends AppCompatActivity {

    private CheckBox checkBox;
    private Button submit, previous, viewPre;
    private TextView preProgID, preProgCode, preProgType, preProgName, preProgDetails, preProgOrganiser, preProgLMD, preClassID, preClassName, preClassFee, preCLassDuration, preClassDate, preSubID, preSubCode, preSubName, preActName, preActNoOfStud, preActTeamName;
    private DatabaseReference databaseReference;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);


        setUPViews();

        String Uuid = getIntent().getStringExtra("uid");

        databaseReference = FirebaseDatabase.getInstance().getReference("ProgReg").child(Uuid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String a = dataSnapshot.child("pID").getValue().toString();
                    String b = dataSnapshot.child("pCode").getValue().toString();
                    String c = dataSnapshot.child("pName").getValue().toString();
                    String d = dataSnapshot.child("pType").getValue().toString();
                    String e = dataSnapshot.child("pDet").getValue().toString();
                    String f = dataSnapshot.child("pOrg").getValue().toString();
                    String g = dataSnapshot.child("pDate").getValue().toString();
                    String h = dataSnapshot.child("cID").getValue().toString();
                    String i = dataSnapshot.child("cName").getValue().toString();
                    String j = dataSnapshot.child("cFee").getValue().toString();
                    String k = dataSnapshot.child("cTime").getValue().toString();
                    String l = dataSnapshot.child("cDate").getValue().toString();
                    String m = dataSnapshot.child("sID").getValue().toString();
                    String n = dataSnapshot.child("sCode").getValue().toString();
                    String o = dataSnapshot.child("sName").getValue().toString();
                    String p = dataSnapshot.child("aName").getValue().toString();
                    String q = dataSnapshot.child("aNo").getValue().toString();
                    String r = dataSnapshot.child("aTeamName").getValue().toString();


                    preProgID.setText(a);
                    preProgCode.setText(b);
                    preProgType.setText(d);
                    preProgName.setText(c);
                    preProgDetails.setText(e);
                    preProgOrganiser.setText(f);
                    preProgLMD.setText(g);
                    preClassID.setText(h);
                    preClassName.setText(i);
                    preClassFee.setText(j);
                    preCLassDuration.setText(k);
                    preClassDate.setText(l);
                    preSubID.setText(m);
                    preSubCode.setText(n);
                    preSubName.setText(o);
                    preActName.setText(p);
                    preActNoOfStud.setText(q);
                    preActTeamName.setText(r);
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Preview.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Preview.this, ProgramReg.class));
            }
        });
    }

    private void setUPViews() {
        checkBox = findViewById(R.id.cbAgree);
        submit = findViewById(R.id.btnGoHome);
        previous = findViewById(R.id.btnGoPre);
        preProgID = findViewById(R.id.tvPMProgIDPre);
        preProgCode = findViewById(R.id.tvPMProgCodePre);
        preProgType = findViewById(R.id.tvPMProgTypePre);
        preProgName = findViewById(R.id.tvPMProgNamePre);
        preProgDetails = findViewById(R.id.tvPMProgDetailsPre);
        preProgOrganiser = findViewById(R.id.tvPMProgCreatedByPre);
        preProgLMD = findViewById(R.id.tvPMProgLastDatePre);
        preClassID = findViewById(R.id.tvCMClassIDPre);
        preClassName = findViewById(R.id.tvCMClassNamePRe);
        preClassFee = findViewById(R.id.tvCMFeesPre);
        preCLassDuration = findViewById(R.id.tvCMDurationPre);
        preClassDate = findViewById(R.id.tvCMDatePre);
        preSubID = findViewById(R.id.tvSMsubIDPre);
        preSubCode = findViewById(R.id.tvSMCodePre);
        preSubName = findViewById(R.id.tvSMNamePre);
        preActName = findViewById(R.id.tvCSAcitivityPre);
        preActNoOfStud = findViewById(R.id.tvCSNoOfStudentsPre);
        preActTeamName = findViewById(R.id.tvCSTeamNamePre);
    }
}
