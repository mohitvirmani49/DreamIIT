package com.mohitvirmani.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MathsForm extends AppCompatActivity implements Phy_Adapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ImageButton back;
    private List<Phy> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_form);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_me);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = (ImageButton) findViewById(R.id.back);


        list = new ArrayList<>();
        list.add(new Phy("Straight Lines"));
        list.add(new Phy("Circle"));
        list.add(new Phy("Parabola"));
        list.add(new Phy("Ellipse"));
        list.add(new Phy("Hyperbola"));
        list.add(new Phy("Limit of Function"));
        list.add(new Phy("Differentiation"));
        list.add(new Phy("Indefinite Integration"));
        list.add(new Phy("Definite Integration"));
        list.add(new Phy("Fundamental of Mathematics"));
        list.add(new Phy("Quadratic Equations"));
        list.add(new Phy("Sequence and Series"));
        list.add(new Phy("Permutation and Combination"));
        list.add(new Phy("Probability"));
        list.add(new Phy("Complex Numbers"));
        list.add(new Phy("Vectors"));
        list.add(new Phy("3 D Geometry"));
        list.add(new Phy("Solution of a Triangle"));
        list.add(new Phy("Vectors"));
        list.add(new Phy("Inverse Trigonometric Functions"));

        list.add(new Phy("Statistics"));
        list.add(new Phy("Mathematical Reasoning"));

        list.add(new Phy("Sets & Relation"));


        Phy_Adapter mAdapter2 = new Phy_Adapter(MathsForm.this, list);
//        mAdapter = new Phy_Adapter(Main29Activity.this,mUploads);

        mAdapter2.setOnItemClickListener(MathsForm.this);
        mRecyclerView.setAdapter(mAdapter2);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathsForm.this, MathsCard.class);
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


        Intent intent = new Intent(MathsForm.this, MF.class);
        startActivity(intent);

    }
}