package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.like.LikeButton;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Main17Activity extends AppCompatActivity {
    private ImageButton question_image;
    private TextView question_text;
    private CircularImageView question_user_pic;
    private TextView question_user_name;
    private Button answer_question;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private ImageButton answer_image;
    private TextView answer_text;

    private TextView userNameA;

    private TextView comments;
    private TextView like;
    private LikeButton likeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);

        question_image = (ImageButton) findViewById(R.id.question_image);
        question_text = (TextView) findViewById(R.id.question_text);
        question_user_pic = (CircularImageView) findViewById(R.id.answer_pg_image);
        question_user_name = (TextView) findViewById(R.id.answer_pg_name);
        answer_question = (Button) findViewById(R.id.answer_question);

        answer_image = (ImageButton) findViewById(R.id.ans_image5);
        answer_text = (TextView) findViewById(R.id.answer_text5);
        userNameA = (TextView) findViewById(R.id.answer_user_name);

        comments = (TextView) findViewById(R.id.comments);
        like = (TextView) findViewById(R.id.no_of_likes);
        likeButton = (LikeButton) findViewById(R.id.like);


        final View vw = (View) findViewById(R.id.l123);
        final View ans_btn5 = (View) findViewById(R.id.answer_question);

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

        Query query = FirebaseDatabase.getInstance().getReference().child("uploads");
        query.orderByChild("mName").equalTo(txt).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {
                    String key = foodSnapshot.getKey();
                    DatabaseReference fbdatabase = FirebaseDatabase.getInstance().getReference().child("uploads").child(key);
                    fbdatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String myAnswer = dataSnapshot.child("mAnswer").getValue().toString();
                            String myName = dataSnapshot.child("mAnsDisName").getValue().toString();
                            String myAnsImage = dataSnapshot.child("mAnsImage").getValue().toString();
//                            String myImage = dataSnapshot.child("mAnsDisImg").getValue().toString();
                            System.out.println("My Answer::::::::" + myAnswer);
                            if (myAnswer.matches("") && myAnsImage.matches("")) {
                                vw.setVisibility(View.INVISIBLE);
                            } else {
                                vw.setVisibility(View.VISIBLE);
                                answer_text.setText(myAnswer);
                                userNameA.setText(myName);
                                ans_btn5.setVisibility(View.INVISIBLE);

                                userNameA.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(Main17Activity.this, Main19Activity.class);
                                        startActivity(intent);
                                    }
                                });

                                if (myAnsImage.matches("")) {
                                    answer_image.requestLayout();
                                    answer_image.getLayoutParams().width = 0;
                                    answer_image.getLayoutParams().height = 0;
                                    System.out.println("empty");

                                } else {
                                    answer_image.requestLayout();
                                    answer_image.getLayoutParams().width = 200;
                                    answer_image.getLayoutParams().height = 200;
                                    Picasso.get().load(myAnsImage).into(answer_image);


                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        question_text.setText(txt);
        question_user_name.setText(result.getString("username", "2"));
        Picasso.get().load(result.getString("userpic", "3")).fit().centerCrop().into(question_user_pic);

        question_user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main17Activity.this, Main19Activity.class);
                startActivity(intent);
            }
        });
        question_user_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main17Activity.this, Main19Activity.class);
                startActivity(intent);
            }
        });


        answer_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main17Activity.this, Main18Activity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("txt", txt);
        editor.apply();


        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main17Activity.this, Main21Activity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Main17Activity.this, ImagesActivity.class);
        startActivity(intent);
    }

    public void showPopup1(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_2, popup.getMenu());
        popup.show();
    }
}