package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import Model.Upload;

public class Main16Activity extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 1;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    private StorageTask mUploadTask;

    EditText ask_doubt;
    ImageButton attach;
    Button submit_doubt;
    ImageView imageView;
    ProgressBar progressBar;
    Spinner spinner;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);

        ask_doubt = (EditText) findViewById(R.id.ask_doubt);
        attach = (ImageButton) findViewById(R.id.attach);
        submit_doubt = (Button) findViewById(R.id.doub);
        imageView = (ImageView) findViewById(R.id.my_ques);
        progressBar = (ProgressBar) findViewById(R.id.upload_prog);
        spinner = (Spinner) findViewById(R.id.choose_topic);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        submit_doubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(Main16Activity.this, "Uploading Your Question"
                            , Toast.LENGTH_SHORT).show();

                } else {
                    uploadImage();
                }
            }
        });


        attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChoosen();
            }
        });


    }

    private void showFileChoosen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, CHOOSE_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Picasso.get().load(imageUri).into(imageView);

        }

    }

    private void uploadImage() {


        if (imageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            mUploadTask = fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    }, 500);
                    Toast.makeText(Main16Activity.this, "Question has been added Successfully :)", Toast.LENGTH_SHORT).show();
                    Upload upload = new Upload(ask_doubt.getText().toString().trim(), taskSnapshot.getUploadSessionUri().toString());
                    String uploadID = mDatabaseRef.push().getKey();

                    String my_name = user.getDisplayName();
                    Upload upload1 = new Upload(my_name, taskSnapshot.getUploadSessionUri().toString());
                    String uploadID1 = mDatabaseRef.push().getKey();

                    mDatabaseRef.child(uploadID).setValue(upload);
                    mDatabaseRef.child(uploadID1).setValue(upload1);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Main16Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int) progress);
                }
            });

        } else {

        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}
