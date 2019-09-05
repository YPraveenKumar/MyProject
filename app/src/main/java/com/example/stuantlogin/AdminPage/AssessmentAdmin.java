package com.example.stuantlogin.AdminPage;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AssessmentAdmin extends AppCompatActivity {

    private EditText etName, etRollNo, etSub1, etSub2, etSub3, etTotal, etPercentage, etRecommendation, etStuActivity, etAttendance;
    private Spinner spinner;
    private RadioGroup rgGrade;
    private Button btnSave;
    private String sName;
    private String sRollNo;
    private String sSub1;
    private String sSub2;
    private String sSub3;
    private String sTotal;
    private String sPercentage;
    private String sRecommendation;
    private String sStuActivity;
    private String sAttendance;
    private String sGrade;
    private String sTerm;
    private int Grade;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_admin);


        etName = findViewById(R.id.etAssName);
        etRollNo = findViewById(R.id.etAssRollNo);
        etSub1 = findViewById(R.id.etAssSub1);
        etSub2 = findViewById(R.id.etAssSub2);
        etSub3 = findViewById(R.id.etAssSub3);
        etTotal = findViewById(R.id.etAssTotal);
        etPercentage = findViewById(R.id.etAssPercentage);
        etRecommendation = findViewById(R.id.etAssRecommendation);
        etStuActivity = findViewById(R.id.etAssStuActivity);
        etAttendance = findViewById(R.id.etAssAttendance);
        spinner = findViewById(R.id.spnAssTerm);
        rgGrade = findViewById(R.id.rgAssGrade);
        btnSave = findViewById(R.id.btnAssSave);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Assessment Details");

        rgGrade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Grade = group.getCheckedRadioButtonId();
                RadioButton button = findViewById(checkedId);
                sGrade = button.getText().toString();
            }
        });

        List<String> termList = new ArrayList<>();
        termList.add("Term1");
        termList.add("Term2");
        termList.add("Term3");
        termList.add(0, "Select Term");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, termList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sTerm = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AssessmentValidated()){

                    AssessmentModel assessmentModel = new AssessmentModel(sName, sRollNo, sSub1, sSub2, sSub3, sTotal, sPercentage, sRecommendation, sStuActivity, sAttendance, sGrade, sTerm);

                    databaseReference.child(sRollNo).setValue(assessmentModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AssessmentAdmin.this, "Details Saved SuccessFully...", Toast.LENGTH_SHORT).show();
                                etSub1.setText("");
                                etSub2.setText("");
                                etSub3.setText("");
                                etName.setText("");
                                etRecommendation.setText("");
                                etRollNo.setText("");
                                etAttendance.setText("");
                                etTotal.setText("");
                                etPercentage.setText("");
                                etStuActivity.setText("");
                            }
                            else {
                                Toast.makeText(AssessmentAdmin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean AssessmentValidated() {
        boolean result = false;

        sSub1 = etSub1.getText().toString().trim();
        sSub2 = etSub2.getText().toString().trim();
        sSub3 = etSub3.getText().toString().trim();
        sTotal = etTotal.getText().toString().trim();
        sName = etName.getText().toString().trim();
        sRollNo = etRollNo.getText().toString().trim();
        sRecommendation = etRecommendation.getText().toString().trim();
        sPercentage = etPercentage.getText().toString().trim();
        sStuActivity = etStuActivity.getText().toString().trim();
        sAttendance = etAttendance.getText().toString().trim();
        if(sName.isEmpty() || sRollNo.isEmpty() || sSub1.isEmpty() || sSub2.isEmpty() || sSub3.isEmpty() || sTotal.isEmpty() || sPercentage.isEmpty() || sRecommendation.isEmpty() || sStuActivity.isEmpty() || sAttendance.isEmpty()){
            Toast.makeText(this, "Please Enter Detail", Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }
        return result;
    }
}
