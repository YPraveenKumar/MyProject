package com.example.stuantlogin.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;

public class AdminLogIN extends AppCompatActivity {

    private EditText userId, userPassword;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log_in);


        userId = findViewById(R.id.etAdminID);
        userPassword = findViewById(R.id.etAdminPassword);
        login = findViewById(R.id.btnAdminLogIN);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(userId.getText().toString().trim(), userPassword.getText().toString().trim());
            }
        });

    }

    private void validate(String id, String password) {
        if(id.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
        }
        else if ((id.equals("Admin1234")) && password.equals("Admin1234")){
            Toast.makeText(this, "LogIN Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminLogIN.this, AdminDashBoard.class));
        }
        else{
            Toast.makeText(this, "LogIN Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
