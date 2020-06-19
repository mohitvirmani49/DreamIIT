package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class Main23Activity extends AppCompatActivity {
    private Button physics;
    private Button chemistry;
    private Button maths;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);

        physics = (Button) findViewById(R.id.phy_filter);
        chemistry = (Button) findViewById(R.id.chem_filter);
        maths = (Button) findViewById(R.id.maths_filter);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        physics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(Main23Activity.this, "Upload in progress", Toast.LENGTH_LONG).show();
                } else {
                    uploadPhyFile();
                }
            }
        });
        chemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(Main23Activity.this, "Upload in progress", Toast.LENGTH_LONG).show();
                } else {
                    uploadChemFile();
                }

            }
        });
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(Main23Activity.this, "Upload in progress", Toast.LENGTH_LONG).show();
                } else {
                    uploadMathsFile();
                }
            }
        });

    }

    private void uploadPhyFile() {

        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String doubt = result.getString("d", "0");
        String img = result.getString("down", "1");
        String usrname = result.getString("user", "2");
        String pic7 = result.getString("pic", "3");

        SharedPreferences.Editor editor = result.edit();


        if (img.matches("1")) {

            Upload upload = new Upload(doubt, "",
                    usrname, pic7, "", "", "",
                    "", "", "", "", "", "", "Physics", "", "", "", "");
            mDatabaseRef.push().setValue(upload);

            openImagesActivity();
            editor.clear();
            editor.apply();
        } else {

            Upload upload = new Upload(doubt,
                    img, usrname, pic7, "", "", "", "",
                    "", "", "", "", "", "Physics", "", "", "", "");

            mDatabaseRef.push().setValue(upload);

            System.out.println("myImage" + img);

            openImagesActivity();
            editor.clear();
            editor.apply();

        }

    }

    private void uploadChemFile() {

        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String doubt = result.getString("d", "0");
        String img = result.getString("down", "1");
        String usrname = result.getString("user", "2");
        String pic7 = result.getString("pic", "3");

        SharedPreferences.Editor editor = result.edit();


        if (img.matches("1")) {
            System.out.println("Celebrations, I reached here yayayyyyyyyyyy");


            Upload upload = new Upload(doubt, "",
                    usrname, pic7, "", "", "",
                    "", "", "", "", "", "", "Chemistry", "", "", "", "");
            mDatabaseRef.push().setValue(upload);

            openImagesActivity();
            editor.clear();
            editor.apply();
        } else {
            System.out.println("I cant reach there, oops");

            Upload upload = new Upload(doubt,
                    img, usrname, pic7, "", "", "", "",
                    "", "", "", "", "", "Chemistry", "", "", "", "");

            mDatabaseRef.push().setValue(upload);

            System.out.println("myImage" + img);

            openImagesActivity();
            editor.clear();
            editor.apply();

        }

    }

    private void uploadMathsFile() {
        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String doubt = result.getString("d", "0");
        System.out.println("My doubt" + doubt);
        String img = result.getString("down", "1");
        System.out.println("My Image" + img);
        String usrname = result.getString("user", "2");
        System.out.println("My name" + usrname);
        String pic7 = result.getString("pic", "3");
        System.out.println("My pic" + pic7);

        SharedPreferences.Editor editor = result.edit();


        if (img.matches("1")) {
            System.out.println("Celebrations, I reached here yayayyyyyyyyyy");


            Upload upload = new Upload(doubt, "",
                    usrname, pic7, "", "", "",
                    "", "", "", "", "", "", "Maths", "", "", "", "");
            mDatabaseRef.push().setValue(upload);

            openImagesActivity();
            editor.clear();
            editor.apply();
        } else {
            System.out.println("I cant reach there, oops");

            Upload upload = new Upload(doubt,
                    img, usrname, pic7, "", "", "", "",
                    "", "", "", "", "", "Maths", "", "", "", "");

            mDatabaseRef.push().setValue(upload);

            openImagesActivity();
            editor.clear();
            editor.apply();

        }
    }

    private void openImagesActivity() {
        Intent intent = new Intent(this, ImagesActivity.class);
        startActivity(intent);
    }

}