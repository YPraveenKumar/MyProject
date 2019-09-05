package com.example.stuantlogin.Dashboard;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.R;
import com.example.stuantlogin.StudentLogIN.SignUp;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class StudentDetails extends AppCompatActivity {

    private TextView tvname, tvrollNo, tvdob, tvstandard, tvemail, tvfather, tvmother, tvblood, tvaddress, tvextra, tvgeder;
    Button choose, upload;
    public Uri imageUri;
    StorageTask uploadTask;
    ImageView profilePic;
    StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        tvname = findViewById(R.id.tvName);
        tvrollNo = findViewById(R.id.tvRollNUm);
        tvdob = findViewById(R.id.tvDOB);
        tvstandard = findViewById(R.id.tvStandard);
        tvemail = findViewById(R.id.tvEmail);
        tvfather = findViewById(R.id.tvFather);
        tvmother = findViewById(R.id.tvMonther);
        tvblood = findViewById(R.id.tvBlood);
        tvgeder = findViewById(R.id.tvGen);
        tvaddress = findViewById(R.id.tvAddress);
        tvextra = findViewById(R.id.tvExtra);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference(firebaseAuth.getUid()).child("StudentProfile");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String address = dataSnapshot.child("address").getValue().toString();
                String blood = dataSnapshot.child("blood").getValue().toString();
                String dob = dataSnapshot.child("dob").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String extra = dataSnapshot.child("extra").getValue().toString();
                String father = dataSnapshot.child("father").getValue().toString();
                String mother = dataSnapshot.child("mother").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                String rollno = dataSnapshot.child("rollNo").getValue().toString();
                String standard = dataSnapshot.child("standard").getValue().toString();
                String gender = dataSnapshot.child("gender").getValue().toString();

                tvname.setText(name);
                tvrollNo.setText(rollno);
                tvdob.setText(dob);
                tvstandard.setText(standard);
                tvemail.setText(email);
                tvextra.setText(extra);
                tvfather.setText(father);
                tvmother.setText(mother);
                tvblood.setText(blood);
                tvaddress.setText(address);
                tvgeder.setText(gender);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        choose = findViewById(R.id.btnChoose);
        upload = findViewById(R.id.btnUpload);
        storageReference = FirebaseStorage.getInstance().getReference("images ");
        profilePic = findViewById(R.id.cvProfilePic);


        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(StudentDetails.this, "Please wait... Image is uploading", Toast.LENGTH_SHORT).show();
                } else {
                    fileUpload();
                }
            }
        });


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + location); //log
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Test", "onCancelled");
            }
        });

    }

    private void fileUpload() {
        StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + getExtenstion(imageUri));

        uploadTask = ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(StudentDetails.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    private String getExtenstion(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void fileChooser () {


        Intent chooseIntent = new Intent();
        chooseIntent.setType("image/*");
        chooseIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(chooseIntent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            profilePic.setImageURI(imageUri);
        }
    }

    public void editDetails(View view) {
        startActivity(new Intent(getApplicationContext(), SignUp.class));
    }
}
