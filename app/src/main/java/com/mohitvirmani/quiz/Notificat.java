package com.mohitvirmani.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Notificat extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageButton back;
    private List<Phy> list;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificat);


        back = (ImageButton) findViewById(R.id.back);
        tv = (TextView) findViewById(R.id.rank);
//        list.add(new Phy("Complex Numzbers1 & Quadratic equations"));
//        list.add(new Phy("Sequence & series"));
//        list.add(new Phy("Permutation & Combination"));
//        list.add(new Phy("Binomial Theorem"));
//        list.add(new Phy("Matrices & Determinants"));
//        list.add(new Phy("Probability"));
//        list.add(new Phy("Logarithmic & their properties"));
//        list.add(new Phy("Functions"));
//        list.add(new Phy("Limits Continuity & Differentiability"));
//        list.add(new Phy("Differentiation"));
//        list.add(new Phy("Application of differentiation"));
//        list.add(new Phy("Indefinite Integration"));
//        list.add(new Phy("Definite Integration"));
//        list.add(new Phy("Application of Integration"));
//        list.add(new Phy("Differential Equation"));
//        list.add(new Phy("Straight Lines"));
//        list.add(new Phy("Circles"));
//        list.add(new Phy("Conic Sections"));
//        list.add(new Phy("Trigonometry"));
//        list.add(new Phy("Vectors"));
//        list.add(new Phy("31 D Geometry"));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Query query2 = FirebaseDatabase.getInstance().getReference("rankans").child(user.getUid());
        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int number = (int) dataSnapshot.getChildrenCount();

                System.out.println("My total answers upto now is" + number);
                if (number >= 0 && number <= 5) {
//                    list = new ArrayList<>();

                    tv.setText("Congrats!! You earned a new Rank, Toddler!");
//                    list.add(new Phy("Congrats!! You earned a new Rank, Toddler!"));


//                    answerRank.setText("Toddler");

                } else if (number > 5 && number <= 15) {
                    tv.setText("Congrats!! You earned a new Rank, Rookie!");
//                    list.add(new Phy("Congrats!! You earned a new Rank, Rookie!"));
//                    answerRank.setText("Rookie");
                } else if (number > 15 && number < 30) {
                    tv.setText("Congrats!! You earned a new Rank, Rising Star!");
//                    answerRank.setText("Rising Star");
//                    list.add(new Phy("Congrats!! You earned a new Rank, Rising Star!"));

                } else if (number >= 30 && number < 50) {
                    tv.setText("Congrats!! You earned a new Rank, Achiever!");
//                    answerRank.setText("Achiever");
//                    list.add(new Phy("Congrats!! You earned a new Rank, Achiever!"));
                } else if (number >= 50 && number < 80) {
                    tv.setText("Congrats!! You earned a new Rank, Expert!");
//                    answerRank.setText("Expert");
//                    list.add(new Phy("Congrats!! You earned a new Rank, Expert!"));
                } else if (number >= 80 && number < 120) {
                    tv.setText("Congrats!! You earned a new Rank, Genius!");
//                    answerRank.setText("Genius");
//                    list.add(new Phy("Congrats!! You earned a new Rank, Genius!"));
                } else if (number >= 120) {
                    tv.setText("Congrats!! You earned a new Rank, Legend!");
//                    list.add(new Phy("Congrats!! You earned a new Rank, Legend!"));
//                    answerRank.setText("Legend");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainPage.class);
        startActivity(intent);

    }
}