package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MathsChallenge extends AppCompatActivity implements Phy_Adapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ImageButton back;
    private List<Phy> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_challenge);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_me);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = (ImageButton) findViewById(R.id.back);

        list = new ArrayList<>();
        list.add(new Phy("Complex Numbers & Quadratic equations"));
        list.add(new Phy("Sequence & series"));
        list.add(new Phy("Permutation & Combination"));
        list.add(new Phy("Binomial Theorem"));
        list.add(new Phy("Matrices & Determinants"));
        list.add(new Phy("Probability"));
        list.add(new Phy("Logarithmic & their properties"));
        list.add(new Phy("Functions"));
        list.add(new Phy("Limits Continuity & Differentiability"));
        list.add(new Phy("Differentiation"));
        list.add(new Phy("Application of differentiation"));
        list.add(new Phy("Indefinite Integration"));
        list.add(new Phy("Definite Integration"));
        list.add(new Phy("Application of Integration"));
        list.add(new Phy("Differential Equation"));
        list.add(new Phy("Straight Lines"));
        list.add(new Phy("Circles"));
        list.add(new Phy("Conic Sections"));
        list.add(new Phy("Trigonometry"));
        list.add(new Phy("Vectors"));
        list.add(new Phy("3 D Geometry"));

        Phy_Adapter mAdapter2 = new Phy_Adapter(MathsChallenge.this, list);
//        mAdapter = new Phy_Adapter(Main29Activity.this,mUploads);

        mAdapter2.setOnItemClickListener(MathsChallenge.this);
        mRecyclerView.setAdapter(mAdapter2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathsChallenge.this, Main28Activity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void itemClicked(int position) {
        Phy myList = list.get(position);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("chapter", myList.getChapters());
        editor.apply();



        Intent intent = new Intent(MathsChallenge.this, MathsChallengeInstr.class);
        startActivity(intent);


    }
}