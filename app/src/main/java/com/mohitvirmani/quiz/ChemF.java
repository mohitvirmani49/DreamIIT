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

public class ChemF extends AppCompatActivity implements Phy_Adapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ImageButton back;
    private List<Phy> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chem_f);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_me);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = (ImageButton) findViewById(R.id.back);

        list = new ArrayList<>();
        list.add(new Phy("Atomic Structure"));
        list.add(new Phy("Stoichiometry"));
        list.add(new Phy("Gaseous State"));
        list.add(new Phy("Thermodynamics"));
        list.add(new Phy("Chemical Equilibrium"));
        list.add(new Phy("Ionic Equilibrium"));
        list.add(new Phy("Electrochemistry"));
        list.add(new Phy("Solution & Colligative Properties"));
        list.add(new Phy("Solid State"));
        list.add(new Phy("Periodic Table and Periodicity"));
        list.add(new Phy("Chemical Bonding"));
        list.add(new Phy("Coordination Compounds"));
        list.add(new Phy("Metallurgy"));

        Phy_Adapter mAdapter2 = new Phy_Adapter(ChemF.this, list);
//        mAdapter = new Phy_Adapter(Main29Activity.this,mUploads);

        mAdapter2.setOnItemClickListener(ChemF.this);
        mRecyclerView.setAdapter(mAdapter2);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChemF.this, Main27Activity.class);
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



        Intent intent = new Intent(ChemF.this, CehmFormulae.class);
        startActivity(intent);


    }
}