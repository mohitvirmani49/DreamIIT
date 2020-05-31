package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

public class Main17Activity extends AppCompatActivity {
    private ImageButton question_image;
    private TextView question_text;
    private CircularImageView question_user_pic;
    private TextView question_user_name;
    private Button answer_question;
    private RecyclerView mRecyclerView;
    private AnswerAdapter mAdapter;
    //    private Button floatingActionButton;
    private DatabaseReference mDatabaseRef;
    private List<Ans_Upload> mUploads;
//    private AnswerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);

        question_image = (ImageButton) findViewById(R.id.question_image);
        question_text = (TextView) findViewById(R.id.question_text);
        question_user_pic = (CircularImageView) findViewById(R.id.answer_pg_image);
        question_user_name = (TextView) findViewById(R.id.answer_pg_name);
        answer_question = (Button) findViewById(R.id.answer_question);
        mRecyclerView = (RecyclerView) findViewById(R.id.answer_recycler);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploadsAns");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Ans_Upload uploadsAns = postSnapshot.getValue(Ans_Upload.class);
                    mUploads.add(uploadsAns);
                }
//                Ans_Upload upload = dataSnapshot.getValue(Ans_Upload.class);
//                mUploads.add(upload);
                mAdapter = new AnswerAdapter(Main17Activity.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main17Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (result.getString("image_q", "0").isEmpty()) {

            question_image.requestLayout();
            question_image.getLayoutParams().height = 0;
            question_image.getLayoutParams().width = 0;
            question_image.setVisibility(View.INVISIBLE);


        } else {
            question_image.requestLayout();
            question_image.getLayoutParams().height = 380;

            Picasso.get().load(result.getString("image_q", "0")).fit().centerCrop().into(question_image);


        }
        String txt = result.getString("question", "1");
        question_text.setText(txt);
        question_user_name.setText(result.getString("username", "2"));
        Picasso.get().load(result.getString("userpic", "3")).fit().centerCrop().into(question_user_pic);


        answer_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main17Activity.this, Main18Activity.class);
                startActivity(intent);
            }
        });


    }

    public void showPopup1(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_2, popup.getMenu());
        popup.show();
    }
}