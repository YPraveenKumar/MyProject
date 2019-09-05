package com.example.stuantlogin.ProgramRegistration;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;

import java.util.Calendar;

public class ClassMaster extends AppCompatActivity implements View.OnClickListener {

    private EditText etclassID, etClassName, etFees, etTime, etDate;
    private Button save1;
//    private DatabaseReference databaseReference;
    private Calendar calendar;
    private int mDay, mMonth, mYear, mHour, mMinute;
//    private ClassMasterConstructor classMasterConstructor;
//    private long maxID = 0;
    private String sclassID, sclassName, sFee, sDate, sTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_master);

        etclassID = findViewById(R.id.etCMClassID);
        etClassName = findViewById(R.id.etCMClassName);
        etFees = findViewById(R.id.etCMFees);
        etTime = findViewById(R.id.etCMDuration);
        etDate = findViewById(R.id.etCMDay);
        save1 = findViewById(R.id.btnCMSave);
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
        mHour = calendar.get(Calendar.HOUR);
        mMinute = calendar.get(Calendar.MINUTE);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    maxID = dataSnapshot.getChildrenCount();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        classMasterConstructor = new ClassMasterConstructor();

        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateClassMAster()) {

                    sclassID = etclassID.getText().toString().trim();
                    sclassName = etClassName.getText().toString().trim();
                    sFee = etFees.getText().toString().trim();
                    sDate = etFees.getText().toString().trim();
                    sTime = etTime.getText().toString().trim();

                    Intent cmIntent = new Intent(ClassMaster.this, SubjectMaster.class);

                    cmIntent.putExtra("h", sclassID);
                    cmIntent.putExtra("i", sclassName);
                    cmIntent.putExtra("j", sFee);
                    cmIntent.putExtra("k", sDate);
                    cmIntent.putExtra("l", sTime);

                    if(getIntent().getExtras() != null){
                        cmIntent.putExtras(getIntent().getExtras());
                    }

//
//                    classMasterConstructor.setCalssID(sclassID);
//                    classMasterConstructor.setClasNAme(sclassName);
//                    classMasterConstructor.setDate(sDate);
//                    classMasterConstructor.setFee(sFee);
//                    classMasterConstructor.setTime(sTime);

//                    databaseReference.setValue(classMasterConstructor);
//                    databaseReference.child(String.valueOf(maxID + 1)).setValue(classMasterConstructor);

                    Toast.makeText(getApplicationContext(), "Details Saved Successfully", Toast.LENGTH_LONG).show();

                    startActivity(cmIntent);
                }
            }
        });
        etDate.setOnClickListener(this);
        etTime.setOnClickListener(this);
    }

    private Boolean validateClassMAster() {

        String sclassID = etclassID.getText().toString().trim();
        String sclassName = etClassName.getText().toString().trim();
        String sFee = etFees.getText().toString().trim();
        String sDate = etFees.getText().toString().trim();
        String sTime = etTime.getText().toString().trim();

        boolean result = false;
        if(sclassID.isEmpty() || sclassName.isEmpty() || sDate.isEmpty() || sFee.isEmpty() || sTime.isEmpty())
            Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();

        else
            result = true;

        return result;
    }

    @Override
    public void onClick(View v) {
        if (v == etDate) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ClassMaster.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            etDate.setText(dayOfMonth + "/" + (month +1)+ "/" + year);
                        }
                    }, mDay, mMonth, mYear);
            datePickerDialog.show();
        }
        if (v == etTime){
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    ClassMaster.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    etTime.setText(hourOfDay+":"+minute);
                }
            }, mHour, mMinute, true);
            timePickerDialog.show();
        }

    }

    public void preClassMAster(View view) {
        startActivity(new Intent(getApplicationContext(), ProgramMaster.class));
    }
}
