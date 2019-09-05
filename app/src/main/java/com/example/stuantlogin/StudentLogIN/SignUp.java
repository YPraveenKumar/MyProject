package com.example.stuantlogin.StudentLogIN;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;
import com.example.stuantlogin.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class SignUp extends AppCompatActivity {

    private EditText etDOB, etSignUpName, etSignUPRollno, etSignupemail, etSignupPassword, etSignUpFather, etSignUpMother, etSignupBlood, etSignupAddress, etSignUpExtra;
    private FirebaseAuth signUPFirebaseAuth;
    private CheckBox checkBox;
    private String name, rollNo, dob, standard, email, father, mother, blood, address, extra, password, gender;
    Utils utils;
    private Button btnSIgnupRegister;
    private Spinner spnSignUPStd, spnSignUPGender;
    private Calendar calendar;
    private ArrayList<String> stdList, genList;
    private String stringSTD;
    private int dayDOB, monthDOB, yearDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        utils = Utils.getInstance(this);

        etDOB = findViewById(R.id.etSignupDOB);
        etSignUpName = findViewById(R.id.etSignupName);
        etSignUPRollno = findViewById(R.id.etSignupRollNUm);
        spnSignUPStd = findViewById(R.id.spnSignUPStd);
        etSignupemail = findViewById(R.id.etSignupEmail);
        etSignupPassword = findViewById(R.id.etSignuopPassword);
        etSignUpFather = findViewById(R.id.etSignupFather);
        etSignUpMother = findViewById(R.id.etSignupMonther);
        etSignupBlood = findViewById(R.id.etSignupBlood);
        etSignupAddress = findViewById(R.id.etSignupAddress);
        etSignUpExtra = findViewById(R.id.etSignupExtra);
        checkBox = findViewById(R.id.cbSignup);
        spnSignUPGender = findViewById(R.id.spnSignUPGender);
        btnSIgnupRegister = findViewById(R.id.btnRegister);

        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDOB.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                }, dayDOB, monthDOB, yearDOB);
                datePickerDialog.show();
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked()){
                    Toast.makeText(SignUp.this, "PLease Verify the Entered Details", Toast.LENGTH_SHORT).show();
                    btnSIgnupRegister.setEnabled(true);
                }
            }
        });


        stdList = new ArrayList<>();
        stdList.add("Select Standard");
        stdList.add("FIRST Std");
        stdList.add("SECOND Std");
        stdList.add("THIRD Std");
        stdList.add("FOURTH Std");
        stdList.add("FIFTH Std");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_spinner_item, stdList);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnSignUPStd.setAdapter(adapter);


        spnSignUPStd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                standard =  spnSignUPStd.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    genList = new ArrayList<>();
        genList.add("Select Gender");
        genList.add("MALE");
        genList.add("FEMALE");
        genList.add("TRANSGENDER");


        ArrayAdapter<String> genAdapter1 = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_spinner_item, genList);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnSignUPGender.setAdapter(genAdapter1);


        spnSignUPGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender =  spnSignUPGender.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        signUPFirebaseAuth = FirebaseAuth.getInstance();
        btnSIgnupRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    final String user_name = etSignupemail.getText().toString().trim();
                    String user_password = etSignupPassword.getText().toString().trim();
                    utils.showProgress();
                    signUPFirebaseAuth.createUserWithEmailAndPassword(user_name, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendStudentDetails(task.getResult().getUser().getUid());
                            } else {
                                utils.hideProgress();
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnSIgnupRegister.setEnabled(true);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void sendStudentDetails(String uid) {

//        HashMap<String, Object> data = new HashMap<>();
//        data.put("Name", name);
//        data.put("Roll No", rollNo);
//        data.put("DOB", dob);
//        data.put("Standard", standard);
//        data.put("Email", email);
//        data.put("Father", father);
//        data.put("Mother", mother);
//        data.put("Blood", blood);
//        data.put("Address", address);
//        data.put("Extra", extra);
//        data.put("Gender", gender);
        SignUP_Getter_Setter signUP_getter_setter = new SignUP_Getter_Setter(name, rollNo, dob, standard, email, father, mother, blood, address, extra, gender);

        Task<Void> databaseReference = FirebaseDatabase.getInstance().getReference().child(uid).child("StudentProfile").setValue(signUP_getter_setter);
        databaseReference.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                utils.hideProgress();
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration Done", Toast.LENGTH_LONG).show();
                    sendVerification();
                    signUPFirebaseAuth.signOut();
                    finish();
                }
            }
        });
    }

    private Boolean validate() {
        boolean result = false;

        name = etSignUpName.getText().toString().trim();
        rollNo = etSignUPRollno.getText().toString().trim();
        dob = etDOB.getText().toString().trim();
        email = etSignupemail.getText().toString().trim();
        password = etSignupPassword.getText().toString().trim();
        father = etSignUpFather.getText().toString().trim();
        mother = etSignUpMother.getText().toString().trim();
        blood = etSignupBlood.getText().toString().trim();
        address = etSignupAddress.getText().toString().trim();
        extra = etSignUpExtra.getText().toString().trim();

        if (name.isEmpty() || rollNo.isEmpty() || standard.isEmpty() || email.isEmpty() || password.isEmpty() || father.isEmpty() || mother.isEmpty() || address.isEmpty() || blood.isEmpty() || extra.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill details", Toast.LENGTH_LONG).show();
        } else {
            return result = true;
        }

        return result;
    }

    private void sendVerification() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Verifying mail is sent", Toast.LENGTH_LONG).show();
                        finish();
                        Intent signUPIntent = new Intent(SignUp.this, MainActivity.class).putExtra("FIRSTTIME", true);
//                        signUPIntent.putExtra("UID", rollNo);
                        startActivity(signUPIntent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Verification mail hasn't been sent", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void toLoginActivity(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}