package com.mohitvirmani.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

public class Main17Activity extends AppCompatActivity implements AnsAdapter.OnItemClickListener {
    private ImageViewZoom question_image;
    private TextView question_text;
    private CircularImageView question_user_pic;
    private TextView question_user_name;
    private Button answer_question;
    private DatabaseReference mDatabaseRef;
    //    private List<Upload> mUploads;
    private ImageViewZoom answer_image;
    private TextView answer_text;
    private TextView userNameA;
    private RecyclerView mRecyclerView;
    private TextView comments;
    private TextView like1;
    private LikeButton likeButton;
    private int love = 0;
    private List<Ans_Upload> uploadsm;
    private AnsAdapter mAdapter;
    public static final String CORRECT = "correct";
    private ImageButton back;

    private static final String MOHIT = "yDfOwvhJ7eS6eWvwBN3lqZ8ptgZ2";
    private static final String SMRITI = "uoAzBlr2VddHXkLYS1Hhr8giHQg2";
    private static final String DREAMIIT = "9IPp81AxH9O918RQaQ1SR2XS2v52";
    private static final String JKV = "bLvR6e3LQwPwxzhSVQvKaSKBoXQ2";
    private static final String NEETIKA = "ksKgQxl9YqfLQPHWUokkg5QHt9H3";
    private static final String PIYUSH = "AW2yUDB0RehcD5SimfrJbSfVe7t2";


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        question_image = (ImageViewZoom) findViewById(R.id.question_image);
        question_text = (TextView) findViewById(R.id.question_text);
        question_user_pic = (CircularImageView) findViewById(R.id.answer_pg_image);
        question_user_name = (TextView) findViewById(R.id.answer_pg_name);
        answer_question = (Button) findViewById(R.id.answer_question);
        back = (ImageButton) findViewById(R.id.back);
        answer_image = (ImageViewZoom) findViewById(R.id.ans_image5);
        answer_text = (TextView) findViewById(R.id.answer_text5);
        userNameA = (TextView) findViewById(R.id.answer_user_name);
        comments = (TextView) findViewById(R.id.comments);
        like1 = (TextView) findViewById(R.id.no_of_likes);
        likeButton = (LikeButton) findViewById(R.id.like);
//        photoView = (PhotoView) findViewById(R.id.photo_view);


        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        uploadsm = new ArrayList<>();


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

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("solv").child(txt);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uploadsm.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Ans_Upload upload = postSnapshot.getValue(Ans_Upload.class);
                    uploadsm.add(upload);
                }
                mAdapter = new AnsAdapter(Main17Activity.this, uploadsm);
                mAdapter.setOnItemClickListener(Main17Activity.this);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main17Activity.this, ImagesActivity.class));
            }
        });
//
//        Query query = FirebaseDatabase.getInstance().getReference().child("uploads");
//        query.orderByChild("mName").equalTo(txt).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {
//                    String key = foodSnapshot.getKey();
//                    DatabaseReference fbdatabase = FirebaseDatabase.getInstance().getReference().child("uploads").child(key);
//                    fbdatabase.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
//
//
//                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                            String myAnswer = dataSnapshot.child("mAnswer").getValue().toString();
//
//                            editor.putString("myAns", myAnswer);
//                            editor.apply();
//
//                            String myName = dataSnapshot.child("mAnsDisName").getValue().toString();
//                            String myAnsImage = dataSnapshot.child("mAnsImage").getValue().toString();
////                            String myImage = dataSnapshot.child("mAnsDisImg").getValue().toString();
//                            System.out.println("My Answer::::::::" + myAnswer);
//                            if (myAnswer.matches("") && myAnsImage.matches("")) {
//                                vw.setVisibility(View.INVISIBLE);
//                            } else {
////                                vw.setVisibility(View.VISIBLE);
//                                answer_text.setText(myAnswer);
//                                userNameA.setText(myName);
////                                ans_btn5.setVisibility(View.INVISIBLE);
//
//                                userNameA.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        Intent intent = new Intent(Main17Activity.this, Main19Activity.class);
//                                        startActivity(intent);
//                                    }
//                                });
//
//                                if (myAnsImage.matches("")) {
//                                    answer_image.requestLayout();
//                                    answer_image.getLayoutParams().width = 0;
//                                    answer_image.getLayoutParams().height = 0;
//                                    System.out.println("empty");
//
//                                } else {
//                                    answer_image.requestLayout();
//                                    answer_image.getLayoutParams().width = 200;
//                                    answer_image.getLayoutParams().height = 200;
//                                    Picasso.get().load(myAnsImage).into(answer_image);
//
//
//                                }
//
//                            }
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

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


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.report_ques:
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user.getUid().equals(NEETIKA) || user.getUid().equals(MOHIT)
                                || user.getUid().equals(SMRITI) || user.getUid().equals(DREAMIIT)
                                || user.getUid().equals(PIYUSH) || user.getUid().equals(JKV)) {

                            System.out.println("Check" + user.getUid());
                            SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            String txt = result.getString("question", "1");
                            Query query = FirebaseDatabase.getInstance().getReference("uploads");
                            query.orderByChild("mName").equalTo(txt).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {

                                        String key = foodSnapshot.getKey();
                                        DatabaseReference fbdatabase = FirebaseDatabase.getInstance().getReference().child("uploads").child(key);
                                        fbdatabase.removeValue();
                                        Toast.makeText(Main17Activity.this, "Successfully deleted question", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Main17Activity.this, ImagesActivity.class));

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(Main17Activity.this, "Sorry, Something went goofy", Toast.LENGTH_SHORT).show();

                                }
                            });

                        } else {

                            Toast.makeText(Main17Activity.this, "Thanks for reporting the question, Our Moderators will have a look at it soon", Toast.LENGTH_SHORT).show();
                        }
                        return true;


                    default:
                        return false;

                }

            }
        });
    }

    @Override
    public void itemClicked(int position) {

    }


}
