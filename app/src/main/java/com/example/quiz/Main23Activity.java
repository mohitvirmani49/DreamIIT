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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quiz.notification.Token;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class Main23Activity extends AppCompatActivity {
    private Button physics;
    private Button chemistry;
    private Button maths;
    private ImageButton back;

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
        back = (ImageButton) findViewById(R.id.back);

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagesActivity();
            }
        });

    }

    private void uploadPhyFile() {

        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String doubt = result.getString("d", "0");
        final String img = result.getString("down", "1");
        final String usrname = result.getString("user", "2");
        final String pic7 = result.getString("pic", "3");

        final SharedPreferences.Editor editor = result.edit();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        Query query2 = FirebaseDatabase.getInstance().getReference("answercount").child(user.getUid());
        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int number = (int) dataSnapshot.getChildrenCount();
                System.out.println("My total answers upto now is" + number);


                if (img.matches("1")) {

                    Upload upload = new Upload(doubt, "",
                            usrname, pic7, "", "", "",
                            "", "", String.valueOf(number), "", "", "", "Physics", "", "", "", "");
                    mDatabaseRef.push().setValue(upload);

                    openImagesActivity();
                    editor.clear();
                    editor.apply();
                } else {

                    Upload upload = new Upload(doubt,
                            img, usrname, pic7, "", "", "", "",
                            "", String.valueOf(number), "", "", "", "Physics", "", "", "", "");

                    mDatabaseRef.push().setValue(upload);

                    System.out.println("myImage" + img);

                    openImagesActivity();
                    editor.clear();
                    editor.apply();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main23Activity.this, "Something wrong happened. Try again later", Toast.LENGTH_SHORT).show();

            }
        });
//        query2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//

    }

    private void uploadChemFile() {

        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String doubt = result.getString("d", "0");
        final String img = result.getString("down", "1");
        final String usrname = result.getString("user", "2");
        final String pic7 = result.getString("pic", "3");

        final SharedPreferences.Editor editor = result.edit();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        Query query2 = FirebaseDatabase.getInstance().getReference("answercount").child(user.getUid());
        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int number = (int) dataSnapshot.getChildrenCount();
                System.out.println("My total answers upto now is" + number);


                if (img.matches("1")) {

                    Upload upload = new Upload(doubt, "",
                            usrname, pic7, "", "", "",
                            "", "", String.valueOf(number), "", "", "", "Chemistry", "", "", "", "");
                    mDatabaseRef.push().setValue(upload);

                    openImagesActivity();
                    editor.clear();
                    editor.apply();
                } else {

                    Upload upload = new Upload(doubt,
                            img, usrname, pic7, "", "", "", "",
                            "", String.valueOf(number), "", "", "", "Chemistry", "", "", "", "");

                    mDatabaseRef.push().setValue(upload);

                    System.out.println("myImage" + img);

                    openImagesActivity();
                    editor.clear();
                    editor.apply();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main23Activity.this, "Something wrong happened. Try again later", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void uploadMathsFile() {

        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        final String doubt = result.getString("d", "0");
        final String img = result.getString("down", "1");
        final String usrname = result.getString("user", "2");
        final String pic7 = result.getString("pic", "3");

        final SharedPreferences.Editor editor = result.edit();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        Query query2 = FirebaseDatabase.getInstance().getReference("answercount").child(user.getUid());
        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int number = (int) dataSnapshot.getChildrenCount();
                System.out.println("My total answers upto now is" + number);


                if (img.matches("1")) {

                    Upload upload = new Upload(doubt, "",
                            usrname, pic7, "", "", "",
                            "", "", String.valueOf(number), "", "", "", "Maths", "", "", "", "");
                    mDatabaseRef.push().setValue(upload);

                    openImagesActivity();
                    editor.clear();
                    editor.apply();
                } else {

                    Upload upload = new Upload(doubt,
                            img, usrname, pic7, "", "", "", "",
                            "", String.valueOf(number), "", "", "", "Maths", "", "", "", "");

                    mDatabaseRef.push().setValue(upload);

                    System.out.println("myImage" + img);

                    openImagesActivity();
                    editor.clear();
                    editor.apply();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main23Activity.this, "Something wrong happened. Try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        openImagesActivity();
    }

    private void openImagesActivity() {
        Intent intent = new Intent(this, ImagesActivity.class);
        startActivity(intent);
    }

}