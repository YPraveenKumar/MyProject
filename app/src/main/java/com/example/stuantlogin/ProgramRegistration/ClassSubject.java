package com.example.stuantlogin.ProgramRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;
import com.example.stuantlogin.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ClassSubject extends AppCompatActivity {

    private EditText etClassID, etSubID, etsubCode;
    private Button btnSave;
    private PreviewConstructor previewConstructor;
//    private long maxid = 0;
    private String sClassID, sSubID, sSubCode;
    Utils utils;

    private String stringpreProgID, stringpreProgCode, stringpreProgType, stringpreProgName, stringpreProgDetails, stringpreProgOrganiser, stringpreProgLMD, stringpreClassID, stringpreClassName, stringpreClassFee, stringpreCLassDuration, stringpreClassDate,
            stringpreSubID, stringpreSubCode, stringpreSubName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_subject);

        etClassID = findViewById(R.id.etCSClassID);
        etsubCode = findViewById(R.id.etCSSubCode);
        etSubID = findViewById(R.id.etCSId);
        btnSave = findViewById(R.id.btnCSSave);
        previewConstructor = new PreviewConstructor();

        sClassID = etClassID.getText().toString().trim();
        sSubID = etSubID.getText().toString().trim();
        sSubCode = etsubCode.getText().toString().trim();
        stringpreProgID = getIntent().getStringExtra("a");
        stringpreProgCode = getIntent().getStringExtra("b");
        stringpreProgType = getIntent().getStringExtra("c");
        stringpreProgName = getIntent().getStringExtra("d");
        stringpreProgDetails = getIntent().getStringExtra("e");
        stringpreProgOrganiser = getIntent().getStringExtra("f");
        stringpreProgLMD = getIntent().getStringExtra("g");
        stringpreClassID = getIntent().getStringExtra("h");
        stringpreClassName = getIntent().getStringExtra("i");
        stringpreClassFee = getIntent().getStringExtra("j");
        stringpreClassDate = getIntent().getStringExtra("k");
        stringpreCLassDuration = getIntent().getStringExtra("l");
        stringpreSubID = getIntent().getStringExtra("m");
        stringpreSubCode = getIntent().getStringExtra("n");
        stringpreSubName = getIntent().getStringExtra("o");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateClassSub()) {
                    saveProgDetails();
                }
            }
        });


    }

    private void saveProgDetails() {
        HashMap<String, Object> prgList = new HashMap<>();
        prgList.put("PrgID", stringpreProgID);
        prgList.put("PrgCode", stringpreProgCode);
        prgList.put("PrgType", stringpreProgType);
        prgList.put("PrgName", stringpreProgName);
        prgList.put("PrgDetails", stringpreProgDetails);
        prgList.put("PrgOrg", stringpreProgOrganiser);
        prgList.put("PrgDate", stringpreProgLMD);
        prgList.put("ClsID", stringpreClassID);
        prgList.put("ClsName", stringpreClassName);
        prgList.put("ClsFee", stringpreClassFee);
        prgList.put("ClsTime", stringpreCLassDuration);
        prgList.put("ClsDate", stringpreClassDate);
        prgList.put("SubID", stringpreSubID);
        prgList.put("SubCode", stringpreSubCode);
        prgList.put("SubName", stringpreSubName);
        prgList.put("ActName", sClassID);
        prgList.put("ActNOS", sSubID);
        prgList.put("ActTeam", sSubCode);

        Task<Void> dataPref = FirebaseDatabase.getInstance().getReference().child("ProgramDetails").child(stringpreProgID).updateChildren(prgList);
        dataPref.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ClassSubject.this, "Program Registration is Successful.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ClassSubject.this, Preview.class));
                    finish();
                }
            }
        });

    }

    private Boolean validateClassSub() {

        sClassID = etClassID.getText().toString().trim();
        sSubID = etSubID.getText().toString().trim();
        sSubCode = etsubCode.getText().toString().trim();

        boolean result = false;

        if (sClassID.isEmpty() || sSubCode.isEmpty() || sSubID.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();}

        else{
            result = true;}


        return result;
    }

    public void preClassSub(View view) {
        startActivity(new Intent(ClassSubject.this, SubjectMaster.class));
    }
}
