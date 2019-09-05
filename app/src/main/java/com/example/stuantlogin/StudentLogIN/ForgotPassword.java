package com.example.stuantlogin.StudentLogIN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText etEmail;
    private Button btnReset;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forgot_password);

        etEmail = findViewById(R.id.etPasswordReset);
        btnReset = findViewById(R.id.btnSendMail);

        firebaseAuth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_Email = etEmail.getText().toString().trim();

                if(user_Email.equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Your Email Address", Toast.LENGTH_LONG).show();
                }else {
                    firebaseAuth.sendPasswordResetEmail(user_Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Mail Sent", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(ForgotPassword.this, MainActivity.class));
                            }else{
                                Toast.makeText(getApplicationContext(), "Error in Sending Mail. Check Your Network Connection", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
