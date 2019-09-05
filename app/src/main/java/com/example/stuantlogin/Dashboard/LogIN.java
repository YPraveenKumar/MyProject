package com.example.stuantlogin.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.AdminPage.AdminLogIN;
import com.example.stuantlogin.StudentLogIN.MainActivity;
import com.example.stuantlogin.R;

public class LogIN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void toAdminActivity(View view) {
        startActivity(new Intent(LogIN.this, AdminLogIN.class));
    }

    public void toMainActivity(View view) {
        startActivity(new Intent(LogIN.this, MainActivity.class));
    }
}
