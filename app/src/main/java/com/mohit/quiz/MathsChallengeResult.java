package com.mohit.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class MathsChallengeResult extends AppCompatActivity {

    private Button tv1;
    private LottieAnimationView pass;
    private RelativeLayout my;
    private ImageButton back;
    private Button check, score1, high;
    private Button detail;
    private TextView name;
    private CircularImageView pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_challenge_result);

        tv1 = (Button) findViewById(R.id.score);
        pass = (LottieAnimationView) findViewById(R.id.pass);
        name = (TextView) findViewById(R.id.name);
        pic = (CircularImageView) findViewById(R.id.photo);
        my = (RelativeLayout) findViewById(R.id.my);
        score1 = (Button) findViewById(R.id.score1);
        high = (Button) findViewById(R.id.high);
        back = (ImageButton) findViewById(R.id.back);
        check = (Button) findViewById(R.id.check);
        detail = (Button) findViewById(R.id.detail);

        final Intent myIntent = getIntent();
        final int truth = myIntent.getIntExtra("truth", 0);
        final int bluff = myIntent.getIntExtra("bluff", 0);
        final int not = myIntent.getIntExtra("not", 0);
        final String value = myIntent.getStringExtra("value");
        final int intValue = myIntent.getIntExtra("intVariableName", 0);


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Query query = FirebaseDatabase.getInstance().getReference("marks").child(user.getUid()).child(value);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {


                    int truth = (int) dataSnapshot.getChildrenCount();
                    System.out.println("try" + not);
                    int bluff2 = bluff-not;
                    System.out.println("bluff" + bluff2);
                    System.out.println("truth" + truth);
                    int bluff = 10 - (truth + not);
                    int intValu = (truth * 4) - bluff2;
                    int percent = intValu / 40;
//                    System.out.println("bluff2" + bluff);
                    if (intValu < 10) {
                        pass.setVisibility(View.INVISIBLE);
                        my.setBackgroundResource(R.color.red);


                    } else {

                        my.setBackgroundResource(R.color.main_page);
                        pic.setVisibility(View.INVISIBLE);

                    }
                    int val = percent * 100;
                    tv1.setText("Score:\n" + intValu);
                    score1.setText("Percent:\n" + String.valueOf((100 * intValu) / 40) + "%");
                    if (intValu >= 32) {
                        high.setText("Grade:\n" + "A+");
                    } else if (intValu >= 26 && intValu < 32) {
                        high.setText("Grade:\n" + "A");
                    } else if (intValu >= 20 && intValu < 26) {
                        high.setText("Grade:\n" + "B+");
                    } else if (intValu >= 14 && intValu < 20) {
                        high.setText("Grade:\n" + "B");
                    } else if (intValu >= 10 && intValu < 14) {
                        high.setText("Grade:\n" + "C");
                    } else if (intValu < 10) {
                        high.setText("Grade:\n" + "D");
                    }

                    SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    final String chapter = result.getString("chapter", "0");
                    DatabaseReference dr = FirebaseDatabase.getInstance().getReference(user.getUid()).child("level1");
                    if (intValu >= 16) {
                        User user = new User(intValu);
                        dr.child(chapter).setValue(user);
                    }


                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathsChallengeResult.this, Main28Activity.class);
                startActivity(intent);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathsChallengeResult.this, MathsAnswerVerify.class);
                startActivity(intent);

            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user1 = mAuth.getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(user1.getUid());
//        databaseReference.child("Maths").push().setValue(user);


        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathsChallengeResult.this, MathsDetail.class);
                intent.putExtra("truth", truth);
                intent.putExtra("bluff", bluff);
                intent.putExtra("not", not);
                startActivity(intent);
            }
        });

        name.setText(user1.getDisplayName());
        Picasso.get().load(user1.getPhotoUrl()).into(pic);


        // in page13 just query child count if >5 in level1 assign rank =1; else not


//
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference resultRef = rootRef.child("Sumofcal").child("result");
//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int total = 0;
//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                    int number = Integer.valueOf(ds.getValue(String.class).replaceAll(" ", ""));
//                    total = total + number;
//                }
//                Log.d(TAG, "total= " + total);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
//            }
//        };
//        resultRef.addListenerForSingleValueEvent(valueEventListener);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Main28Activity.class));
    }
}