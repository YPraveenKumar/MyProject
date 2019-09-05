package com.example.stuantlogin.Dashboard;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.AdminPage.BillingModel;
import com.example.stuantlogin.R;
import com.example.stuantlogin.StudentLogIN.SignUP_Getter_Setter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Billing extends AppCompatActivity {

    private TextView tvName, tvRollno, tvClassName, tvTotalFee, tvPaidFee, tvBalanceFee, tvDueDate;
    private DatabaseReference databaseReference, uid;
    private BillingModel billingModel;
    private SignUP_Getter_Setter getter_setter;
    private String Uuid, RollNO;
    private String sName, sRollno, sClassName, sTotalFee, sPaidFee, sBalanceFee, sDueDate;
    private String firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        tvName = findViewById(R.id.tvSBName);
        tvRollno = findViewById(R.id.tvSBRoll);
        tvClassName = findViewById(R.id.tvSBClass);
        tvTotalFee = findViewById(R.id.tvSBTotalFee);
        tvPaidFee = findViewById(R.id.tvSBPaid);
        tvBalanceFee = findViewById(R.id.tvSBBal);
        tvDueDate = findViewById(R.id.tvSBDate);
        billingModel = new BillingModel();
        getter_setter = new SignUP_Getter_Setter();

        firebaseAuth = FirebaseAuth.getInstance().getUid();

      /*  if(getIntent() != null){
            uid = getIntent().getStringExtra("UID");
        }*/
//        ArrayList<BillingModel> models = new ArrayList<>();
//        models.get(1);
//        Uuid = billingModel.getsRollno();
//        uid = getter_setter.getRollNo();



    }

    @Override
    protected void onStart() {
        super.onStart();
        uid = FirebaseDatabase.getInstance().getReference(firebaseAuth).child("StudentProfile");
        uid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Uuid = dataSnapshot.child("rollNo").getValue().toString();

                databaseReference = FirebaseDatabase.getInstance().getReference("Billing Details").child(Uuid);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        sName = dataSnapshot.child("sName").getValue().toString();
                        sRollno = dataSnapshot.child("sRollno").getValue().toString();
                        sClassName = dataSnapshot.child("sClassName").getValue().toString();
                        sTotalFee = dataSnapshot.child("sTotalFee").getValue().toString();
                        sPaidFee = dataSnapshot.child("sPaidFee").getValue().toString();
                        sBalanceFee = dataSnapshot.child("sBalanceFee").getValue().toString();
                        sDueDate = dataSnapshot.child("sDueDate").getValue().toString();

                        tvName.setText(sName);
                        tvRollno.setText(sRollno);
                        tvClassName.setText(sClassName);
                        tvTotalFee.setText(sTotalFee);
                        tvPaidFee.setText(sPaidFee);
                        tvBalanceFee.setText(sBalanceFee);
                        tvDueDate.setText(sDueDate);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        RollNO = Uuid;
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
