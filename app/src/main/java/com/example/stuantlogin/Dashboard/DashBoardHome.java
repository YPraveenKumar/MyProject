package com.example.stuantlogin.Dashboard;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.R;
import com.example.stuantlogin.StudentLogIN.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashBoardHome extends AppCompatActivity {


    private Button btnCamera, btnGallery;
    private CircleImageView circleImageView;
    private FirebaseAuth firebaseAuth;
//    private String uid;
    private static final int CAM_REQ_CODE = 100;
    private static final int PERMISSION_REQUEST_CODE = 101;
    private static final int GALLERY_REQ_CODE = 102;
    private static final int PERMISSION_REQUEST_READ_CODE = 103;
    private Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_home);

        firebaseAuth = FirebaseAuth.getInstance();
        RecyclerView dashboard_recyclerView = findViewById(R.id.rvDAshboard);
        btnCamera = findViewById(R.id.btnCamera);
        btnGallery = findViewById(R.id.btnGallery);
        circleImageView = findViewById(R.id.cvStuProfile);

        ArrayList<String> db_NameList = new ArrayList<>();
        db_NameList.add("Program Registration");
        db_NameList.add("Notes");
        db_NameList.add("MY Profile");
        db_NameList.add("Billing");
        db_NameList.add("Time Table");
        db_NameList.add("Assessment");
        db_NameList.add("Announcements");
        db_NameList.add("Student Corner");


        //for putString in Adapter CLass


        ArrayList<Integer> db_ImageList = new ArrayList<>();
        db_ImageList.add(R.drawable.program_registration);
        db_ImageList.add(R.drawable.notes);
        db_ImageList.add(R.drawable.student_details);
        db_ImageList.add(R.drawable.biling);
        db_ImageList.add(R.drawable.time_table);
        db_ImageList.add(R.drawable.assessment);
        db_ImageList.add(R.drawable.announcements);
        db_ImageList.add(R.drawable.student_corner);

        dashboard_recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        Dashboard_Adapter dashboard_adapter = new Dashboard_Adapter(this, db_NameList, db_ImageList);
        dashboard_recyclerView.setAdapter(dashboard_adapter);
        dashboard_adapter.notifyDataSetChanged();


        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkpermission();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionForReadData();
            }
        });
    }

    @TargetApi(23)
    private void checkpermission() {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            cameraIntent();
        } else {
            String[] permission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permission, PERMISSION_REQUEST_CODE);
        }

    }

    @TargetApi(23)
    private void checkPermissionForReadData() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            galleryIntent();
        } else {
            String[] permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, PERMISSION_REQUEST_READ_CODE);
        }

    }

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Select file"), GALLERY_REQ_CODE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                cameraIntent();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(DashBoardHome.this, Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(DashBoardHome.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardHome.this);
                    builder.setTitle("APPLICATION PERMISSION ACCESS");
                    builder.setMessage("Please click GRANT to Access to change Image!");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ActivityCompat.requestPermissions(DashBoardHome.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
                    Intent intentAccess = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                    startActivity(intentAccess);
                }
            }
        } else if (requestCode == PERMISSION_REQUEST_READ_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                galleryIntent();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(DashBoardHome.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardHome.this);
                    builder.setTitle("APPLICATION PERMISSION ACCESS");
                    builder.setMessage("Please click GRANT to Access to change Image!");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ActivityCompat.requestPermissions(DashBoardHome.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_READ_CODE);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
                    Intent intentAccess = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                    startActivity(intentAccess);
                }
            }
        }
    }

    private void cameraIntent() {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentCamera.resolveActivity(getPackageManager()) != null) {
            File file = null;
            try {
                file = openNewFile();
            } catch (Exception ex) {
                Log.d("msg", "IO Exception occured");
            }
            if (file != null) {
                imageUri = FileProvider.getUriForFile(DashBoardHome.this, getPackageName(), file);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intentCamera, CAM_REQ_CODE);
            }
        }
    }

    private File openNewFile() throws IOException {

        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(new Date());
        String imageName = "IMG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFile = File.createTempFile(
                imageName,
                ".jpg",
                storageDir);
        final String imageFilePath = imageFile.getAbsolutePath();
        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAM_REQ_CODE && resultCode == RESULT_OK) {
            circleImageView.setImageURI(imageUri);
        } else if (requestCode == GALLERY_REQ_CODE && resultCode == RESULT_OK) {
            circleImageView.setImageURI(data.getData());
        }
    }
    private void logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(DashBoardHome.this, MainActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
        case R.id.logout:
            logout();
    }
        return super.onOptionsItemSelected(item);
    }
}
