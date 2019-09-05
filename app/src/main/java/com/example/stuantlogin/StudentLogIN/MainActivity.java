package com.example.stuantlogin.StudentLogIN;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.stuantlogin.Dashboard.DashBoardHome;
import com.example.stuantlogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText et_LIEmail, et_LIPassword;
    private FirebaseAuth firebaseAuth;
    private TextView tvAttempts;
    private int count = 5;
    private Button btnLoginhome;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    //    private String uid;
    private final static int CAMERA_REQ_CODE = 100;
    private final static int INTERNAL_STORAGE_REQ_CODE = 101;
    private final static int EXTERNAL_STORAGE_REQ_CODE = 102;
    private final static int INTERNET_REQ_CODE = 103;
    private final static int USER_PERMISSIONS_REQ_CODE = 103;
    private FirebaseUser signInFirebaseUser;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        et_LIEmail = findViewById(R.id.etLogin_EmailID);
        et_LIPassword = findViewById(R.id.etLogin_Password);
        tvAttempts = findViewById(R.id.tvAttempts);
        btnLoginhome = findViewById(R.id.btnLogIN);
        //to save user ID in shared preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        tvAttempts.setText("Number of Attempts Left:5");
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        signInFirebaseUser = firebaseAuth.getCurrentUser();

        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            if (!isFirstTime() && signInFirebaseUser != null) {
                checkEmailVerification();
                finish();
                startActivity(new Intent(MainActivity.this, DashBoardHome.class)/*.putExtra("UID", uid)*/);
            }

            btnLoginhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateLognin(et_LIEmail.getText().toString(), et_LIPassword.getText().toString());
                }
            });
        }
        else {
            String[] permission = new String[]{Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permission, USER_PERMISSIONS_REQ_CODE);
        }

    }

    private boolean isFirstTime() {
        return getIntent() != null && getIntent().getExtras() != null &&
                getIntent().hasExtra("FIRSTTIME") &&
                getIntent().getExtras().getBoolean("FIRSTTIME");
    }

    private void validateLognin(String user_name, String user_password) {

        if (user_name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Email Address", Toast.LENGTH_LONG).show();
        } else if (user_password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Your Password", Toast.LENGTH_LONG).show();
        } else {
            progressDialog.setMessage("Wait for a Moment");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(user_name, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        checkEmailVerification();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                        count--;
                        tvAttempts.setText("Number of Attempts Left:" + count);
                        progressDialog.dismiss();
                        if (count == 0) {
                            btnLoginhome.setEnabled(false);
                        }
                    }
                }
            });
        }
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        boolean emailFlag = firebaseUser.isEmailVerified();

        if (emailFlag) {
            //Saving userid In sharedPreferences
            FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser fbUser = firebaseAuth.getCurrentUser();
                    if (fbUser != null) {
                        String userId = fbUser.getUid();
                        String userEmail = fbUser.getEmail();
                        sharedPreferences = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Uuid", userId);
//                        editor.commit();
                        editor.apply();
                    }
                }
            };
            finish();
            startActivity(new Intent(MainActivity.this, DashBoardHome.class)/*.putExtra("UID", uid)*/);

        } else {
            Toast.makeText(getApplicationContext(), "Verify Your Mail", Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
        }
    }

    public void signUpActivity(View view) {
        Intent signUpIntent = new Intent(this, SignUp.class);
        startActivity(signUpIntent);
    }

    public void forgotPasswordActivity(View view) {
        Intent forgotPaswrdIntent = new Intent(this, ForgotPassword.class);
        startActivity(forgotPaswrdIntent);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 103) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[3] == PackageManager.PERMISSION_GRANTED)
            {
                permissionChecked();
            }else {
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.INTERNET) ||
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("ACCESS PERMISSION REQUEST");
                    builder.setMessage("Please Allow Permission to CONTINUE further...");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, USER_PERMISSIONS_REQ_CODE);
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
                else {
                    Intent intentAccess = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                    startActivity(intentAccess);
                }
            }
        }
    }

    private void permissionChecked()
    {
        if (!isFirstTime() && signInFirebaseUser != null) {
            checkEmailVerification();
            finish();
            startActivity(new Intent(MainActivity.this, DashBoardHome.class)/*.putExtra("UID", uid)*/);
        }

        btnLoginhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLognin(et_LIEmail.getText().toString(), et_LIPassword.getText().toString());
            }
        });
    }
}
