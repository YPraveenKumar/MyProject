package com.example.stuantlogin.AdminPage;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAnnouncement extends AppCompatActivity {

    private EditText etMessage;
    private Button btnSend;
    private DatabaseReference databaseReference;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_announcement);

        etMessage = findViewById(R.id.etAdmAnnouncements);
        btnSend = findViewById(R.id.btnSend);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Announcements");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 msg = etMessage.getText().toString();

                 AnnnounceModel model = new AnnnounceModel(msg);

                if(msg.isEmpty()){
                    Toast.makeText(AdminAnnouncement.this, "Enter the Message", Toast.LENGTH_SHORT).show();
                }else{
                    databaseReference.child("msg").setValue(msg).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Message Sent Successfully", Toast.LENGTH_SHORT).show();
                                etMessage.setText("");
                            }else{
                                Toast.makeText(AdminAnnouncement.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}
