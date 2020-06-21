package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


//import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
//import com.github.chrisbanes.photoview.PhotoView;
//import com.bogdwellers.pinchtozoom.MultiTouchListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

public class Main17Activity extends AppCompatActivity {
    private ImageViewZoom question_image;
    private TextView question_text;
    private CircularImageView question_user_pic;
    private TextView question_user_name;
    private Button answer_question;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private ImageViewZoom answer_image;
    private TextView answer_text;
    private TextView userNameA;

    private TextView comments;
    private TextView like1;
    private LikeButton likeButton;
    private int love = 0;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);

        question_image = (ImageViewZoom) findViewById(R.id.question_image);
        question_text = (TextView) findViewById(R.id.question_text);
        question_user_pic = (CircularImageView) findViewById(R.id.answer_pg_image);
        question_user_name = (TextView) findViewById(R.id.answer_pg_name);
        answer_question = (Button) findViewById(R.id.answer_question);

        answer_image = (ImageViewZoom) findViewById(R.id.ans_image5);
        answer_text = (TextView) findViewById(R.id.answer_text5);
        userNameA = (TextView) findViewById(R.id.answer_user_name);
        comments = (TextView) findViewById(R.id.comments);
        like1 = (TextView) findViewById(R.id.no_of_likes);
        likeButton = (LikeButton) findViewById(R.id.like);
//        photoView = (PhotoView) findViewById(R.id.photo_view);

//        final View vw = (View) findViewById(R.id.l123);
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
                        public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {


                            Query query1 = FirebaseDatabase.getInstance().getReference("likes").child((dataSnapshot.child("mAnswer").getValue().toString()));
                            query1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    int total = (int) dataSnapshot.getChildrenCount();
                                    System.out.println("My val, plz tell" + total);
                                    like1.setText(String.valueOf(total));

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                            likeButton.setOnLikeListener(new OnLikeListener() {
                                @Override
                                public void liked(LikeButton likeButton) {
                                    FirebaseAuth firebaseAuth;
                                    firebaseAuth = FirebaseAuth.getInstance();
                                    final FirebaseUser user = firebaseAuth.getCurrentUser();
                                    love = love + 1;
                                    final Like like = new Like(String.valueOf(love));
                                    DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("likes");
                                    dbr.child((dataSnapshot.child("mAnswer").getValue().toString())).child(user.getUid()).setValue(like);


                                    Query query1 = FirebaseDatabase.getInstance().getReference("likes").child((dataSnapshot.child("mAnswer").getValue().toString()));
                                    query1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            int total = (int) dataSnapshot.getChildrenCount();
                                            System.out.println("My val, plz tell" + total);
                                            like1.setText(String.valueOf(total));

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });


                                }

                                @Override
                                public void unLiked(LikeButton likeButton) {
                                    FirebaseAuth firebaseAuth;
                                    firebaseAuth = FirebaseAuth.getInstance();
                                    final FirebaseUser user = firebaseAuth.getCurrentUser();
//                                    love = love + 1;
//                                    final Like like = new Like(String.valueOf(love));
                                    DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("likes").child((dataSnapshot.child("mAnswer").getValue().toString())).child(user.getUid());
//                                    dbr.child((dataSnapshot.child("mAnswer").getValue().toString())).child(user.getUid());
                                    dbr.removeValue();

                                    Query query1 = FirebaseDatabase.getInstance().getReference("likes").child((dataSnapshot.child("mAnswer").getValue().toString()));
                                    query1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            int total = (int) dataSnapshot.getChildrenCount();
                                            System.out.println("My val, plz tell" + total);
                                            like1.setText(String.valueOf(total));

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });


                                }
                            });

//
//                            if(likeButton.isLiked()) {
//
//
//                                love = love + 1;
//                            }if(!likeButton.isLiked()){
//                                love= love+0;
//                            }
//
//
//

                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("doubts").child(dataSnapshot.child("mAnswer").getValue().toString());
                            Query query = FirebaseDatabase.getInstance().getReference("doubts").child(dataSnapshot.child("mAnswer").getValue().toString());
                            query.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    int total = (int) dataSnapshot.getChildrenCount();
                                    System.out.println("My val, plz tell" + total);
                                    comments.setText(String.valueOf(total));


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            String myAnswer = dataSnapshot.child("mAnswer").getValue().toString();

                            editor.putString("myAns", myAnswer);
                            editor.apply();

                            String myName = dataSnapshot.child("mAnsDisName").getValue().toString();
                            String myAnsImage = dataSnapshot.child("mAnsImage").getValue().toString();
//                            String myImage = dataSnapshot.child("mAnsDisImg").getValue().toString();
                            System.out.println("My Answer::::::::" + myAnswer);
                            if (myAnswer.matches("") && myAnsImage.matches("")) {
//                                vw.setVisibility(View.INVISIBLE);
                            } else {
//                                vw.setVisibility(View.VISIBLE);
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
//        question_image.setOnTouchListener(new ImageMatrixTouchHandler(onCreateView().getContext());

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
