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

public class Main31Activity extends AppCompatActivity implements Phy_Adapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ImageButton back;
    private List<Phy> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main31);



        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_me);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = (ImageButton) findViewById(R.id.back);
//        List<Phy> list = new ArrayList<>();
        list = new ArrayList<>();
        list.add(new Phy("General Organic Chemistry"));
        list.add(new Phy("Isomerism"));
        list.add(new Phy("Hydrocarbons"));
        list.add(new Phy("Alcohol Ether and Epoxides"));
        list.add(new Phy("Aldehydes and Ketones"));
        list.add(new Phy("Aldol and Cannizaro reactions"));
        list.add(new Phy("Carboxylic acid & Amines"));
        list.add(new Phy("Qualitative Analysis"));
        list.add(new Phy("Aromatic Compounds"));
        list.add(new Phy("Nomenclature"));
        Phy_Adapter mAdapter2 = new Phy_Adapter(Main31Activity.this, list);
//        mAdapter = new Phy_Adapter(Main29Activity.this,mUploads);

        mAdapter2.setOnItemClickListener(Main31Activity.this);
        mRecyclerView.setAdapter(mAdapter2);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main31Activity.this, Main27Activity.class);
                startActivity(intent);
            }
        });


//        list.add(new Phy("General Organic Chemistry"));
//        list.add(new Phy("Isomerism"));
//        list.add(new Phy("Practical Organic Chemistry"));
//        list.add(new Phy("Hydrocarbons"));
//        list.add(new Phy("Alcohol Ether and Epoxides"));
//        list.add(new Phy("Aldehydes and Ketones"));
//        list.add(new Phy("Aldol and Cannizaro reactions"));
//        list.add(new Phy("Carboxylic acid & Amines"));
//        list.add(new Phy("Aromatic Compounds"));
//        list.add(new Phy("Nomenclature"));
//


    }

    @Override
    public void itemClicked(int position) {

        Phy myList = list.get(position);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("chapter", myList.getChapters());
        editor.apply();


        Intent intent = new Intent(Main31Activity.this, OrganicPracticeInstr.class);
        startActivity(intent);

    }
}