package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.quiz.notification.APIService;
import com.example.quiz.notification.Client;
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

import static com.example.quiz.Main17Activity.CORRECT;

public class Main21Activity extends AppCompatActivity {
    private ImageButton back_button;
    private RecyclerView recyclerView;
    private EditText comment;
    private ImageButton send;
    private DatabaseReference mDatabaseRef;
    private List<Comm> mUploads;
    private StorageTask mUploadTask;
    private MessageAdapter mAdapter;


    private static final String MOHIT = "yDfOwvhJ7eS6eWvwBN3lqZ8ptgZ2";
    private static final String SMRITI = "uoAzBlr2VddHXkLYS1Hhr8giHQg2";
    private static final String DREAMIIT = "9IPp81AxH9O918RQaQ1SR2XS2v52";
    private static final String JKV = "bLvR6e3LQwPwxzhSVQvKaSKBoXQ2";
    private static final String NEETIKA = "ksKgQxl9YqfLQPHWUokkg5QHt9H3";
    private static final String PIYUSH = "AW2yUDB0RehcD5SimfrJbSfVe7t2";

    APIService apiService;
    boolean notify = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);

        back_button = (ImageButton) findViewById(R.id.comments_back);
        recyclerView = (RecyclerView) findViewById(R.id.comment_recycler);
        comment = (EditText) findViewById(R.id.main_comment);
        send = (ImageButton) findViewById(R.id.comment_send);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mUploads = new ArrayList<>();


//        apiService = Client.getRetrofit("https://fcm.googleapis.com/").create(APIService.class);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(Main21Activity.this, "Hold On !!! Uploading", Toast.LENGTH_LONG).show();
                } else {
                    uploadFile();
                    comment.getText();
                    notify = true;
                }
            }
        });
        DatabaseReference databaseReference;
        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String txt = result.getString("txt", "1");
        final String answer = result.getString("myAns", "0");

        SharedPreferences result4 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String work = result4.getString("myAns", "0");
        System.out.println(":::::::Hello There::::::::::" + work);
        Intent intent = getIntent();
        System.out.println("Hi Hello How" + intent.getStringExtra("correct"));
        String comm = intent.getStringExtra("correct");

        databaseReference = FirebaseDatabase.getInstance().getReference("doubts").child(comm);
//        Query query = FirebaseDatabase.getInstance().getReference().child("doubts");
//        query.equalTo(answer).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()){
//                    String key = foodSnapshot.getKey();
//                    System.out.println("::::::Ulalaleleo" + key);
//                    DatabaseReference fbdatabase = FirebaseDatabase.getInstance().getReference().child("uploads").child(key);
//
//                }

//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

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
        Intent intent = getIntent();
        final String comm1 = intent.getStringExtra("correct");

        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        final Comm comm = new Comm(comment.getText().toString(), user.getDisplayName(), "");
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("doubts");
        dr.child(comm1).push().setValue(comm);
        comment.getText().clear();

    }

    public void showPopup1(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_3, popup.getMenu());
        popup.show();


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.report_comm:
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user.getUid().equals(NEETIKA) || user.getUid().equals(MOHIT)
                                || user.getUid().equals(SMRITI) || user.getUid().equals(DREAMIIT)
                                || user.getUid().equals(PIYUSH) || user.getUid().equals(JKV)) {

                            System.out.println("Check" + user.getUid());
                            SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            String txt = result.getString("question", "1");
                            Intent intent = getIntent();
                            System.out.println("Hi Hello How" + intent.getStringExtra("correct"));
                            final String comm2 = intent.getStringExtra("correct");

                            Query query = FirebaseDatabase.getInstance().getReference("doubts").child(comm2);
                            query.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {

                                        String key = foodSnapshot.getKey();
                                        DatabaseReference fbdatabase = FirebaseDatabase.getInstance().getReference().child("doubts").child(comm2);
                                        fbdatabase.removeValue();
                                        Toast.makeText(Main21Activity.this, "Successfully deleted question", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Main21Activity.this, Main17Activity.class));

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(Main21Activity.this, "Sorry, Something went goofy", Toast.LENGTH_SHORT).show();

                                }
                            });

                        } else {

                            Toast.makeText(Main21Activity.this, "Thanks for reporting the comments, Our Moderators will have a look at it soon", Toast.LENGTH_SHORT).show();
                        }
                        return true;


                    default:
                        return false;

                }

            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Main21Activity.this, Main17Activity.class);
        startActivity(intent);
    }
}