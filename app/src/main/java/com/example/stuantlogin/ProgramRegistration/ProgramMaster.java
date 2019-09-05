package com.example.stuantlogin.ProgramRegistration;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;

import java.util.Calendar;

public class ProgramMaster extends AppCompatActivity {

    private EditText programID, progCode, progType, progName, progDetails, createdBy, modifiedDate;
    private Button save;
//    private DatabaseReference databaseReference;
//    private ProgramMasterConstructor programMasterConstructor;
    private Calendar calendar;
    private int mdate, mMonth, mYear;
//    private long maxId=0;
    private String spCode, sProgramId, spType, spName, spDetails, sCreatedBy, sDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_master);

        programID = findViewById(R.id.etProgramID);
        progCode = findViewById(R.id.etProgramCode);
        progType = findViewById(R.id.etProgramType);
        progName = findViewById(R.id.etProgramName);
        progDetails = findViewById(R.id.etProgramDetails);
        createdBy = findViewById(R.id.etCreatedBy);
        modifiedDate = findViewById(R.id.etLMD);
        save = findViewById(R.id.btnPMSave);
        calendar = Calendar.getInstance();
        mdate = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);

//        programMasterConstructor = new ProgramMasterConstructor();
//
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    maxId = dataSnapshot.getChildrenCount();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validatePM()) {

                    sProgramId = programID.getText().toString().trim();
                    spCode = progCode.getText().toString().trim();
                    spType = progType.getText().toString().trim();
                    spName = progName.getText().toString().trim();
                    spDetails = progDetails.getText().toString().trim();
                    sCreatedBy = createdBy.getText().toString().trim();
                    sDate = modifiedDate.getText().toString().trim();

                    Intent pmIntent = new Intent(getApplicationContext(), ClassMaster.class);

                    pmIntent.putExtra("a", sProgramId);
                    pmIntent.putExtra("b", spCode);
                    pmIntent.putExtra("c", spType);
                    pmIntent.putExtra("d", spName);
                    pmIntent.putExtra("e", spDetails);
                    pmIntent.putExtra("f", sCreatedBy);
                    pmIntent.putExtra("g", sDate);

                    Toast.makeText(getApplicationContext(), "Details Saved Successfully", Toast.LENGTH_LONG).show();

                    startActivity(pmIntent);
                }
            }
        });

        modifiedDate.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ProgramMaster.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        modifiedDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, mdate, mMonth, mYear);
                datePickerDialog.show();
            }
        });
        }

    private Boolean validatePM() {
        String sProgramId = programID.getText().toString().trim();
        String spCode = progCode.getText().toString().trim();
        String spType = progType.getText().toString().trim();
        String spName = progName.getText().toString().trim();
        String spDetails = progDetails.getText().toString().trim();
        String sCreatedBy = createdBy.getText().toString().trim();
        String sDate = modifiedDate.getText().toString().trim();

        boolean result = false;
        if(sCreatedBy.isEmpty() || sDate.isEmpty() || spCode.isEmpty() || spDetails.isEmpty() || spName.isEmpty() || sProgramId.isEmpty() || spType.isEmpty())
            Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();

        else {
            result = true;
        }
        return result;
    }

}
