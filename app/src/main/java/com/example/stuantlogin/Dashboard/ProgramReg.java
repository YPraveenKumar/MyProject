package com.example.stuantlogin.Dashboard;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.ProgramRegistration.Preview;
import com.example.stuantlogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class ProgramReg extends AppCompatActivity implements View.OnClickListener {
    private EditText progID, progCode, progType, progName, progDetails, progOrganiser, progLMD, classID, className, classFee, cLassDuration, classDate, subID, subCode, subName, actName,
            actNoOfStud, actTeamName;
    private Button btnSaveSubmit;
    private Button btnPMCal, btnDay, btnTime;
    private FirebaseDatabase firebaseDatabase;
    private String sProgID, sProgCode, sProgType, sProgName, sProgDetails, sProgOrganiser, sProgLMD, sClassID, sClassName, sClassFee, sCLassDuration, sClassDate, sSubID, sSubCode, sSubName, sActName,
            sActNoOfStud, sActTeamName;
    private Calendar calendar;
    private int pDay, pMOnth, pYear, cDay, cMonth, cYear, cHr, cMin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_reg);

        progID = findViewById(R.id.etProgramID);
        progCode = findViewById(R.id.etProgramCode);
        progType = findViewById(R.id.etProgramType);
        progName = findViewById(R.id.etProgramName);
        progDetails = findViewById(R.id.etProgramDetails);
        progOrganiser = findViewById(R.id.etCreatedBy);
        progLMD = findViewById(R.id.etLMD);
        classID = findViewById(R.id.etCMClassID);
        className = findViewById(R.id.etCMClassName);
        classFee = findViewById(R.id.etCMFees);
        cLassDuration = findViewById(R.id.etCMDuration);
        classDate = findViewById(R.id.etCMDay);
        subID = findViewById(R.id.etSMSubjectID);
        subCode = findViewById(R.id.etSMSubCode);
        subName = findViewById(R.id.etSMSubjectName);
        actName = findViewById(R.id.etCSId);
        actNoOfStud = findViewById(R.id.etCSClassID);
        actTeamName = findViewById(R.id.etCSSubCode);

        btnPMCal = findViewById(R.id.btnPMCalender);
        btnDay = findViewById(R.id.btnDay);
        btnTime = findViewById(R.id.btnTime);

        btnPMCal.setOnClickListener( this);
        btnDay.setOnClickListener( this);
        btnTime.setOnClickListener( this);

        btnSaveSubmit = findViewById(R.id.btnProgRegSave);



        btnSaveSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateRegistration()){

                    HashMap<String, Object> prgList = new HashMap<>();

                    prgList.put("pID", sProgID);
                    prgList.put("pCode", sProgCode);
                    prgList.put("pType", sProgType);
                    prgList.put("pDet", sProgDetails);
                    prgList.put("pName", sProgName);
                    prgList.put("pOrg", sProgOrganiser);
                    prgList.put("pDate", sProgLMD);
                    prgList.put("cID", sClassID);
                    prgList.put("cName", sClassName);
                    prgList.put("cFee", sClassFee);
                    prgList.put("cDate", sClassDate);
                    prgList.put("cTime", sCLassDuration);
                    prgList.put("sID", sSubID);
                    prgList.put("sCode", sSubCode);
                    prgList.put("sName", sSubName);
                    prgList.put("aName", sActName);
                    prgList.put("aNo", sActNoOfStud);
                    prgList.put("aTeamName", sActTeamName);

                    Task<Void> databaseReference = FirebaseDatabase.getInstance().getReference().child("ProgReg").child(sProgID).updateChildren(prgList);
                    databaseReference.addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Registration is Successfull", Toast.LENGTH_SHORT).show();

                                Intent progRegIntent = new Intent(ProgramReg.this, Preview.class);
                                progRegIntent.putExtra("uid", sProgID);
                                startActivity(progRegIntent);
                            }
                            else
                            {
                                Toast.makeText(ProgramReg.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }

    private Boolean validateRegistration() {

        boolean result = false;
        sProgID = progID.getText().toString().trim();
        sProgCode = progCode.getText().toString().trim();
        sProgType = progType.getText().toString().trim();
        sProgName = progName.getText().toString().trim();
        sProgDetails = progDetails.getText().toString().trim();
        sProgOrganiser = progOrganiser.getText().toString().trim();
        sProgLMD = progLMD.getText().toString().trim();
        sClassID = classID.getText().toString().trim();
        sClassName = className.getText().toString().trim();
        sClassFee = classFee.getText().toString().trim();
        sCLassDuration = cLassDuration.getText().toString().trim();
        sClassDate = classDate.getText().toString().trim();
        sSubID = subID.getText().toString().trim();
        sSubCode = subCode.getText().toString().trim();
        sSubName = subName.getText().toString().trim();
        sActName = actName.getText().toString().trim();
        sActNoOfStud = actNoOfStud.getText().toString().trim();
        sActTeamName = actTeamName.getText().toString().trim();

        if(sProgID.isEmpty() || sProgCode.isEmpty() || sProgType.isEmpty() || sProgName.isEmpty() || sProgDetails.isEmpty() || sProgOrganiser.isEmpty() || sProgLMD.isEmpty() || sClassID.isEmpty() || sClassName.isEmpty() || sClassFee.isEmpty() || sCLassDuration.isEmpty() || sClassDate.isEmpty() || sSubID.isEmpty() || sSubCode.isEmpty() || sSubName.isEmpty() || sActName.isEmpty() ||
                sActNoOfStud.isEmpty() || sActTeamName.isEmpty()){

            Toast.makeText(this, "PLease Enter Your Details", Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }


    @Override
    public void onClick(View v) {

        calendar = Calendar.getInstance();


        if(v == btnPMCal){

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    progLMD.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }, pDay, pMOnth, pYear);
            datePickerDialog.show();
        }
        if(v == btnDay){
            DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    classDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                }
            }, cDay, cMonth, cYear);
            pickerDialog.show();
        }
        if(v == btnTime){
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    cLassDuration.setText(hourOfDay+":"+minute);
                }
            }, cHr, cMin, true);
            timePickerDialog.show();
        }

    }

}
