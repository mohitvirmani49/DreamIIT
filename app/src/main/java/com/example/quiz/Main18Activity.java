package com.example.quiz;

import androidx.annotation.NonNull;
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
import android.widget.TextView;
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
import com.squareup.picasso.Picasso;


public class Main18Activity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    EditText main_ans;
    ImageButton prev_img_qs;
    Button submit_ans;
    TextView prev_ans_txt;
    Button attach_ans_img;
    ImageView imageView;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);

        main_ans = (EditText) findViewById(R.id.main_answers);
        prev_img_qs = (ImageButton) findViewById(R.id.answer_are_question_image);
        submit_ans = (Button) findViewById(R.id.submit_ans);
        prev_ans_txt = (TextView) findViewById(R.id.answer_are_text);
        attach_ans_img = (Button) findViewById(R.id.attach_answer);
        imageView = (ImageView) findViewById(R.id.verify_image);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploadsAns");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploadsAns");

        attach_ans_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        submit_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(Main18Activity.this, "Upload in progress", Toast.LENGTH_LONG).show();
                } else {
                    uploadFile();
                }
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

            Picasso.get().load(mImageUri).into(imageView);
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


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 500);


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
                                    Ans_Upload upload = new Ans_Upload(main_ans.getText().toString().trim(), downloadUri.toString(), username.trim(), pic.toString(), "");

                                    mDatabaseRef.push().setValue(upload);

                                    Toast.makeText(Main18Activity.this, "Upload successful", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(Main18Activity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Main18Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        } else {
            FirebaseAuth firebaseAuth;
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser user2 = firebaseAuth.getCurrentUser();
            String username = user2.getDisplayName();

            Uri pic = user2.getPhotoUrl();
            Ans_Upload upload = new Ans_Upload(main_ans.getText().toString().trim(), "", username.trim(), pic.toString(), "");
            mDatabaseRef.push().setValue(upload);
            Toast.makeText(Main18Activity.this, "Upload successful", Toast.LENGTH_LONG).show();
            openImagesActivity();

        }
    }

    private void openImagesActivity() {
        Intent intent = new Intent(this, Main17Activity.class);
        startActivity(intent);
    }


}