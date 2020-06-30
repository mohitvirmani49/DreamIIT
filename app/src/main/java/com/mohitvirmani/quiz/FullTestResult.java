package com.mohitvirmani.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

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

import java.util.Random;

public class FullTestResult extends AppCompatActivity {
    private Button score, highScore, rank;
    private TextView name;
    private CircularImageView image;
    int random;
    int max;
    int min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_test_result);

        highScore = (Button) findViewById(R.id.high);
        score = (Button) findViewById(R.id.score);
        name = (TextView) findViewById(R.id.name);
        image = (CircularImageView) findViewById(R.id.photo);
        rank = (Button) findViewById(R.id.rankpred);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name.setText(user.getDisplayName());
        Picasso.get().load(user.getPhotoUrl()).into(image);


        final Intent myIntent = getIntent();
        final int truth = myIntent.getIntExtra("truth", 0);
        final int bluff = myIntent.getIntExtra("bluff", 0);
        final int not = myIntent.getIntExtra("not", 0);
        final int intValue = myIntent.getIntExtra("intVariableName", 0);
        int percent = intValue / 40;

        score.setText("Score \n" + intValue);

        if (intValue < 0) {
            max = 1500000;
            min = 1400000;
            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue >= 0 && intValue <= 25) {
            max = 1300000;
            min = 1100000;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);


        } else if (intValue > 25 && intValue <= 50) {
            max = 1100000;
            min = 900000;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 50 && intValue <= 75) {
            max = 950000;
            min = 550000;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 75 && intValue <= 100) {
            max = 500000;
            min = 100000;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 100 && intValue <= 125) {
            max = 80000;
            min = 41000;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 125 && intValue <= 150) {
            max = 46000;
            min = 27000;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 150 && intValue <= 175) {
            max = 32000;
            min = 18000;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 175 && intValue <= 200) {
            max = 22000;
            min = 10000;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 200 && intValue <= 225) {

            max = 15000;
            min = 7000;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 225 && intValue <= 250) {
            max = 6500;
            min = 3500;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 250 && intValue <= 275) {
            max = 3700;
            min = 2500;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 275 && intValue <= 300) {
            max = 2200;
            min = 1000;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 300 && intValue <= 325) {
            max = 1000;
            min = 250;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        } else if (intValue > 325 && intValue <= 360) {
            min = 1;
            max = 100;

            Random r = new Random();
            random = r.nextInt(max - min + 1) + min;
            rank.setText("Pred Rank\n" + random);

        }

        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String chapter = result.getString("chapter", "0");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user1 = mAuth.getCurrentUser();

        final DatabaseReference dr = FirebaseDatabase.getInstance().getReference(user1.getUid()).child("Mock Test");

        Query query = FirebaseDatabase.getInstance().getReference(user1.getUid()).child("Mock Test");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = (int) dataSnapshot.child(chapter).getChildrenCount();
                System.out.println("Hi" + count);
                if (count != 0) {
                    String value4 = dataSnapshot.child(chapter).child("name").getValue().toString();
                    System.out.println(":::::Hi:::");
                    int val = Integer.parseInt(value4);
                    System.out.println(val + ":::::::::::");
                    System.out.println("Hiiiiiii Hiiiiiii" + value4);

                    if (intValue > val) {
                        highScore.setText("High\nScore\n" + intValue);
                        User user5 = new User(intValue);
                        dr.child(chapter).setValue(user5);


                    } else {
                        highScore.setText("High\nScore\n" + val);

                    }


                } else {
                    highScore.setText("High\nScore\n" + intValue);
                    User user5 = new User(intValue);
                    dr.child(chapter).setValue(user5);


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, FullTest.class));
    }


}
