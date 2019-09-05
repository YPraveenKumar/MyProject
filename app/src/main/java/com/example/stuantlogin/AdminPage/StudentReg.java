package com.example.stuantlogin.AdminPage;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class StudentReg extends AppCompatActivity {

    private EditText name, rollno, gender, date, email, address, phone, className;
    private Button save;
    private DatabaseReference databaseReference;
    private String sName, sRollno, sGender, sDate, sEmail, sAddress, sPhone, sClassName;
    private Calendar calendar;
    private int mDay, mMonth, mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reg);

        name = findViewById(R.id.etSRName);
        rollno = findViewById(R.id.etSRRollNO);
        gender = findViewById(R.id.etSRGender);
        date = findViewById(R.id.etSRDate);
        email = findViewById(R.id.etSREmail);
        address = findViewById(R.id.etSRAddress);
        phone = findViewById(R.id.etSRPhnNo);
        className = findViewById(R.id.etSRClass);
        save = findViewById(R.id.btnSRSave);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("StudentRegistration");

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(StudentReg.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, mDay, mMonth, mYear);
                datePickerDialog.show();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    StudentListModel studentListModel = new StudentListModel(sName, sRollno, sGender, sDate, sEmail, sAddress, sPhone, sClassName);

                    databaseReference.child(sRollno).setValue(studentListModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(StudentReg.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                                name.setText("");
                                rollno.setText("");
                                gender.setText("");
                                date.setText("");
                                email.setText("");
                                address.setText("");
                                phone.setText("");
                                className.setText("");
                            }
                            else {
                                Toast.makeText(StudentReg.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });
    }

    private boolean validate() {
        boolean result = false;

        sName = name.getText().toString().trim();
        sRollno = rollno.getText().toString().trim();
        sGender = gender.getText().toString().trim();
        sDate = date.getText().toString().trim();
        sEmail = email.getText().toString().trim();
        sPhone = phone.getText().toString().trim();
        sClassName = className.getText().toString().trim();
        sAddress = address.getText().toString().trim();

        if(sName.isEmpty() || sRollno.isEmpty() || sGender.isEmpty() || sDate.isEmpty() || sEmail.isEmpty() || sAddress.isEmpty() || sPhone.isEmpty() || sClassName.isEmpty()){
            Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }
        return result;
    }

}
