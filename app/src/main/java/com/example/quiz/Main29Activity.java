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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main29Activity extends AppCompatActivity implements Phy_Adapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageButton back;
    private List<Phy> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main29);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_me);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = (ImageButton) findViewById(R.id.back);
//        List<Phy> list = new ArrayList<>();
        list = new ArrayList<>();
        list.add(new Phy("Units and Dimensions"));
        list.add(new Phy("Vectors"));
        list.add(new Phy("Kinematics"));
        list.add(new Phy("Laws of Motion"));
        list.add(new Phy("Work, Energy and Power"));
        list.add(new Phy("Centre of Mass"));
        list.add(new Phy("Rotational Motion"));
        list.add(new Phy("Gravitation"));
        list.add(new Phy("Simple Harmonic Motion"));
        list.add(new Phy("Fluid Mechanics"));
        list.add(new Phy("Mechanical Properties of Matter"));
        list.add(new Phy("Sound Waves"));
        list.add(new Phy("Heat and Thermodynamics"));
        list.add(new Phy("Physics for Gaseous States"));
        list.add(new Phy("Ray Optics"));
        list.add(new Phy("Wave Optics"));
        list.add(new Phy("Electrostatics"));
        list.add(new Phy("Current Electricity"));
        list.add(new Phy("Magnetism"));
        list.add(new Phy("Electromagnetic Induction"));
        list.add(new Phy("Modern Physics"));
        list.add(new Phy("Atomic Structure, Nucleus and SemiConductors"));



        Phy_Adapter mAdapter2 = new Phy_Adapter(Main29Activity.this, list);
//        mAdapter = new Phy_Adapter(Main29Activity.this,mUploads);

        mAdapter2.setOnItemClickListener(Main29Activity.this);
        mRecyclerView.setAdapter(mAdapter2);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main29Activity.this, Main26Activity.class);
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



        Intent intent = new Intent(Main29Activity.this, Main33Activity.class);
        startActivity(intent);

    }
}