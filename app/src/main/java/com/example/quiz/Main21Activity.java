package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageTask;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.quiz.App.CHANNEL_1_ID;

public class Main21Activity extends AppCompatActivity {
    private ImageButton back_button;
    private RecyclerView recyclerView;
    private EditText comment;
    private ImageButton send;
    private DatabaseReference mDatabaseRef;
    private List<Comm> mUploads;
    private StorageTask mUploadTask;
    private MessageAdapter mAdapter;
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);

        back_button = (ImageButton) findViewById(R.id.comments_back);
        recyclerView = (RecyclerView) findViewById(R.id.comment_recycler);
        comment = (EditText) findViewById(R.id.main_comment);
        send = (ImageButton) findViewById(R.id.comment_send);

        notificationManager = NotificationManagerCompat.from(this);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(Main21Activity.this, "Hold On !!! Uploading", Toast.LENGTH_LONG).show();
                } else {
                    uploadFile();
                    notify_me();
                    comment.getText();
                }
            }
        });
        DatabaseReference databaseReference;
        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String txt = result.getString("txt", "1");
        final String answer = result.getString("myAns", "0");

        databaseReference = FirebaseDatabase.getInstance().getReference("doubts").child(answer);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Comm upload = postSnapshot.getValue(Comm.class);
                    mUploads.add(upload);

                }
                mAdapter = new MessageAdapter(Main21Activity.this, mUploads);
                recyclerView.setAdapter(mAdapter);

//                SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                Notification notification = new NotificationCompat.Builder(Main21Activity.this, CHANNEL_1_ID)
//                        .setSmallIcon(R.drawable.mohitpic)
//                        .setContentTitle("New Comment")
//                        .setContentText(result.getString("comm", "0"))
//                        .setPriority(NotificationCompat.PRIORITY_HIGH)
//                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                        .build();
//
//                notificationManager.notify(1, notification);
//


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void uploadFile() {
        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String txt = result.getString("txt", "1");
        final String answer = result.getString("myAns", "0");
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        final Comm comm = new Comm(comment.getText().toString(), user.getDisplayName(), "");
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("doubts");
        dr.child(answer).push().setValue(comm);
        comment.getText().clear();

    }

    private void notify_me() {

        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (!result.getString("name789", "0").equals(user.getDisplayName())) {
            System.out.println("hi789");
            Notification notification = new NotificationCompat.Builder(Main21Activity.this, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.mohitpic)
                    .setContentTitle("New Comment")
                    .setContentText(result.getString("comm", "0"))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setOnlyAlertOnce(true)
                    .setAutoCancel(true)
                    .build();
            System.out.println("Dekh Bhai " + result.getString("comm","0"));

            notificationManager.notify(1, notification);
        }
        else {
            System.out.println("Logic OK");
        }


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Main21Activity.this, Main17Activity.class);
        startActivity(intent);
    }
}