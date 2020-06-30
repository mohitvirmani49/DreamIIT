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

public class InorganicChallenge extends AppCompatActivity implements Phy_Adapter.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private ImageButton back;
    private List<Phy> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inorganic_challenge);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_me);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = (ImageButton) findViewById(R.id.back);
//        List<Phy> list = new ArrayList<>();
        list = new ArrayList<>();
        list.add(new Phy("Periodic Properties"));
        list.add(new Phy("Chemical Bonding"));
        list.add(new Phy("Coordinate Compounds"));
        list.add(new Phy("s block Elements"));
        list.add(new Phy("p block Elements"));
        list.add(new Phy("d block Elements"));
        list.add(new Phy("Types of Reactions"));
        list.add(new Phy("Qualitative Analysis"));

        Phy_Adapter mAdapter2 = new Phy_Adapter(InorganicChallenge.this, list);
//        mAdapter = new Phy_Adapter(Main29Activity.this,mUploads);

        mAdapter2.setOnItemClickListener(InorganicChallenge.this);
        mRecyclerView.setAdapter(mAdapter2);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InorganicChallenge.this, Main27Activity.class);
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



        Intent intent = new Intent(InorganicChallenge.this, InorganicCInstr.class);
        startActivity(intent);


    }
}