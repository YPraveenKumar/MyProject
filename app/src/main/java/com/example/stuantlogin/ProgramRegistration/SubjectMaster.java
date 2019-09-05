package com.example.stuantlogin.ProgramRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;

public class SubjectMaster extends AppCompatActivity {

    private EditText etSubID, etSubCode, etSubName;
    private Button btnSave;
    private String sSubID, sSunCode, sSubName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_master);

        etSubCode = findViewById(R.id.etSMSubCode);
        etSubID = findViewById(R.id.etSMSubjectID);
        etSubName = findViewById(R.id.etSMSubjectName);
        btnSave = findViewById(R.id.btnSMSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateSubMaster()) {

                    Intent smIntent = new Intent(SubjectMaster.this, ClassSubject.class);

                    smIntent.putExtra("m", sSubID);
                    smIntent.putExtra("n", sSunCode);
                    smIntent.putExtra("o", sSubName);

                    if(getIntent().getExtras() != null){
                        smIntent.putExtras(getIntent().getExtras());
                    }

                    Toast.makeText(getApplicationContext(), "Details Saved Successfully", Toast.LENGTH_LONG).show();

                    startActivity(smIntent);
                }
            }
        });
    }

    private Boolean validateSubMaster() {

        sSubID = etSubID.getText().toString().trim();
        sSunCode = etSubCode.getText().toString().trim();
        sSubName = etSubName.getText().toString().trim();

        boolean result = false;

        if(sSubID.isEmpty() || sSubName.isEmpty() || sSunCode.isEmpty()){
            Toast.makeText(SubjectMaster.this, "Please Enter Your Details", Toast.LENGTH_LONG).show();}

        else{
            result= true;}

        return result;
    }

    public void preSubMaster(View view) {
        startActivity(new Intent(SubjectMaster.this, ClassMaster.class));
    }
}
