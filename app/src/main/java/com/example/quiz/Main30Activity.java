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

public class Main30Activity extends AppCompatActivity implements Phy_Adapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageButton back;
    private List<Phy> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main30);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_me);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = (ImageButton) findViewById(R.id.back);
//        List<Phy> list = new ArrayList<>();
        list = new ArrayList<>();
        list.add(new Phy("1 Units and Measurement"));
        list.add(new Phy("2 Kinematics"));
        list.add(new Phy("3 Laws Of Motion"));
        list.add(new Phy("4 Work, Energy and Power"));
        list.add(new Phy("5 Rotational Motion"));
        list.add(new Phy("6 Gravitation"));
        list.add(new Phy("7 Properties Of Solids and Liquids"));
        list.add(new Phy("8 Thermodynamics"));
        list.add(new Phy("9 Kinetic Theory Of Gases"));
        list.add(new Phy("10 Oscillations and Waves"));
        list.add(new Phy("11 Electrostatics"));
        list.add(new Phy("12 Current Electricity"));
        list.add(new Phy("13 Magnetic Effects Of Current and Magnetism"));
        list.add(new Phy("14 Electromagnetic Induction and Alternating Currents"));
        list.add(new Phy("15 Electromagnetic Waves"));
        list.add(new Phy("16 Optics"));
        list.add(new Phy("17 Dual Nature Of Matter and radiation"));
        list.add(new Phy("18 Atoms and Nuclei"));
        list.add(new Phy("19 Electronic Devices"));
        list.add(new Phy("20 Communication Systems"));


        Phy_Adapter mAdapter2 = new Phy_Adapter(Main30Activity.this, list);
//        mAdapter = new Phy_Adapter(Main29Activity.this,mUploads);

        mAdapter2.setOnItemClickListener(Main30Activity.this);
        mRecyclerView.setAdapter(mAdapter2);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main30Activity.this, Main26Activity.class);
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



        Intent intent = new Intent(Main30Activity.this, Main35Activity.class);
        startActivity(intent);

    }
}