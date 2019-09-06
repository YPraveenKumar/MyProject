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

public class BillingDeatils extends AppCompatActivity {
    private EditText name, rollno, className, totalFee, paidFee, balanceFee, dueDate;
    private Calendar calendar;
    private int dDay, dMonth, dYear;
    private String sName, sRollno, sClassName, sTotalFee, sPaidFee, sBalanceFee, sDueDate;
    private DatabaseReference databaseReference;
    private Button btnsave;
    private BillingModel billingModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_deatils);

        //SetUP Views
        name = findViewById(R.id.etBDName);
        rollno = findViewById(R.id.etBDRollNo);
        className = findViewById(R.id.etBDClass);
        totalFee = findViewById(R.id.etBDTotalFee);
        paidFee = findViewById(R.id.etBDPaid);
        balanceFee = findViewById(R.id.etBDBalance);
        dueDate = findViewById(R.id.etBDDueDate);
        btnsave = findViewById(R.id.btnBDSave);
        calendar = Calendar.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Billing Details");

        if(getIntent() != null){
            name.setText(getIntent().getStringExtra("Name"));
            rollno.setText(getIntent().getStringExtra("RollNo"));
            className.setText(getIntent().getStringExtra("Class"));
            totalFee.setText(getIntent().getStringExtra("Total"));
            paidFee.setText(getIntent().getStringExtra("Paid"));
            balanceFee.setText(getIntent().getStringExtra("Balance"));
            dueDate.setText(getIntent().getStringExtra("Date"));
        }

        dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BillingDeatils.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dueDate.setText(dayOfMonth +"/"+(month+1)+"/"+year);
                    }
                }, dDay, dMonth, dYear);
                datePickerDialog.show();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(billingValidated()){

                    BillingModel model = new BillingModel(sName, sRollno, sClassName, sTotalFee, sPaidFee, sBalanceFee, sDueDate);

                    databaseReference.child(sRollno).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(BillingDeatils.this, "Details Saved Successfully", Toast.LENGTH_SHORT).show();
                                name.setText("");
                                rollno.setText("");
                                className.setText("");
                                totalFee.setText("");
                                paidFee.setText("");
                                balanceFee.setText("");
                                dueDate.setText("");
                            }
                            else {
                                Toast.makeText(BillingDeatils.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }



    private boolean billingValidated() {
        boolean result = false;
        sName = name.getText().toString().trim();
        sRollno = rollno.getText().toString().trim();
        sClassName = className.getText().toString().trim();
        sTotalFee = totalFee.getText().toString().trim();
        sPaidFee = paidFee.getText().toString().trim();
        sBalanceFee = balanceFee.getText().toString().trim();
        sDueDate = dueDate.getText().toString().trim();

        if(sName.isEmpty() || sRollno.isEmpty() || sClassName.isEmpty() || sTotalFee.isEmpty() || sPaidFee.isEmpty() || sBalanceFee.isEmpty() || sDueDate.isEmpty()){
            Toast.makeText(this, "Please enter the Details", Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }
}
