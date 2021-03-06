package com.mohitvirmani.quiz;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
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
import com.squareup.picasso.Picasso;


public class QuestionsUpload extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private Button mTextViewShowUploads;
    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private LottieAnimationView prog;
    private Uri mImageUri;
    private ImageButton back;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);

        mButtonChooseImage = findViewById(R.id.chooseimg);
        mButtonUpload = findViewById(R.id.upload);
        prog = (LottieAnimationView) findViewById(R.id.my_progress);
        mEditTextFileName = findViewById(R.id.main_doubt);
        mImageView = findViewById(R.id.imv1);
        mProgressBar = findViewById(R.id.progress_bar);
        back = (ImageButton) findViewById(R.id.back);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
                prog.setVisibility(View.VISIBLE);
                mButtonUpload.setVisibility(View.INVISIBLE);


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionsUpload.this, ImagesActivity.class));
            }
        });

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(mImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            FirebaseAuth firebaseAuth;
            firebaseAuth = FirebaseAuth.getInstance();
            final FirebaseUser user = firebaseAuth.getCurrentUser();


            if (user.getDisplayName() != null) {


                final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                        + "." + getFileExtension(mImageUri));

                fileReference.putFile(mImageUri).continueWithTask(
                        new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }
                                return fileReference.getDownloadUrl();
                            }
                        })
                        .addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();
                                    String username = user.getDisplayName();
                                    Uri pic = user.getPhotoUrl();
//                                    Upload upload = new Upload(mEditTextFileName.getText().toString().trim(),
//                                            downloadUri.toString(), username.trim(),
//                                            pic.toString(), "", "", "", "",
//                                            "", "", "", "", "","");


                                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    String doubt = mEditTextFileName.getText().toString().trim();
                                    System.out.println("::::::::::::::::::::::::" + doubt);
                                    System.out.println("::::::::789" + "na" + username);

                                    editor.putString("d", doubt);
                                    editor.putString("down", downloadUri.toString());
                                    editor.putString("user", username.trim());
                                    editor.putString("pic", pic.toString());
                                    editor.apply();


//                                    mDatabaseRef.push().setValue(upload);
//                                    mDatabaseRef.push().setValue(upload1);
                                    Toast.makeText(QuestionsUpload.this, "Upload successful", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(QuestionsUpload.this, Main23Activity.class);
                                    startActivity(intent);
//                                    openImagesActivity();

                                } else {
                                    Toast.makeText(QuestionsUpload.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(QuestionsUpload.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        } else {
            FirebaseAuth firebaseAuth;
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser user2 = firebaseAuth.getCurrentUser();
            String username = user2.getDisplayName();

            Uri pic = user2.getPhotoUrl();

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();

            String doubt = mEditTextFileName.getText().toString().trim();
            System.out.println("::::::::::::::::::::::::" + doubt);
            System.out.println("::::::::789" + "na" + username);

            editor.putString("d", doubt);
            editor.putString("user", username.trim());
            editor.putString("pic", pic.toString());
            editor.apply();

//            Upload upload = new Upload(mEditTextFileName.getText().toString().trim(), "",
//                    username.trim(), pic.toString(), "", "", "",
//                    "", "", "", "", "", "","");
//            mDatabaseRef.push().setValue(upload);
            Toast.makeText(QuestionsUpload.this, "Upload successful", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(QuestionsUpload.this, Main23Activity.class);
            startActivity(intent);
//            openImagesActivity();


        }
    }

    private void openImagesActivity() {
        Intent intent = new Intent(this, ImagesActivity.class);
        startActivity(intent);
    }


}