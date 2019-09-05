package com.example.stuantlogin.Dashboard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Transport extends AppCompatActivity {
    private EditText etSub, etMsg;
    private Button btnSend;
    private DatabaseReference databaseReference, uid;
    private String sSub, sMsg, firebaseAuth, name, rollno, std, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        etSub = findViewById(R.id.etSCSubject);
        etMsg = findViewById(R.id.etSCMessage);
        btnSend = findViewById(R.id.btnSCSend);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("StudentCorner");
        firebaseAuth = FirebaseAuth.getInstance().getUid();
        uid = FirebaseDatabase.getInstance().getReference(firebaseAuth).child("StudentProfile");
        uid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("name").getValue().toString();
                rollno = dataSnapshot.child("rollNo").getValue().toString();
                std = dataSnapshot.child("standard").getValue().toString();
                email = dataSnapshot.child("email").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


if(uid!=null){
    btnSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(msgValidated()){
                TransportModel model = new TransportModel(sSub, sMsg, name, rollno, std, email);
                databaseReference.child("Quaries").child(name).child(rollno).child(sSub).child(sMsg).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Transport.this, "Message Sent Successfully", Toast.LENGTH_SHORT).show();
                            etSub.setText("");
                            etMsg.setText("");
                        }else {
                            Toast.makeText(Transport.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    });
}
}


    private boolean msgValidated() {
        boolean result = false;


        sSub = etSub.getText().toString().trim();
        sMsg = etMsg.getText().toString().trim();

        if(sSub.isEmpty() || sMsg.isEmpty()){
            Toast.makeText(this, "Enter Details...", Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }
        return result;
    }
}
