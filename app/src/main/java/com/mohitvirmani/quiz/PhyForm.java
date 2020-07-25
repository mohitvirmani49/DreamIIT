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

public class PhyForm extends AppCompatActivity implements Phy_Adapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageButton back;
    private List<Phy> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phy_form);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_me);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = (ImageButton) findViewById(R.id.back);

        list = new ArrayList<>();
        list.add(new Phy("Units and Dimensions"));
        list.add(new Phy("Rectilinear Motion"));
        list.add(new Phy("Projectile Motion and Vectors"));
        list.add(new Phy("Friction"));
        list.add(new Phy("Circular Motion"));
        list.add(new Phy("Centre of Mass"));
        list.add(new Phy("Simple Harmonic Motion"));
        list.add(new Phy("String Waves"));
        list.add(new Phy("Heat and Thermodynamics"));
        list.add(new Phy("Electrostatics"));
        list.add(new Phy("Current Electricity"));
        list.add(new Phy("Capacitance"));
        list.add(new Phy("Electromagnetic Induction"));
        list.add(new Phy("Geometrical Optics"));
        list.add(new Phy("Modern Physics"));
        list.add(new Phy("Wave Optics"));
        list.add(new Phy("Fluid Mechanics and Properties of Matter"));

        Phy_Adapter mAdapter2 = new Phy_Adapter(PhyForm.this, list);
//        mAdapter = new Phy_Adapter(Main29Activity.this,mUploads);

        mAdapter2.setOnItemClickListener(PhyForm.this);
        mRecyclerView.setAdapter(mAdapter2);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhyForm.this, PhysicsCard.class);
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


        Intent intent = new Intent(PhyForm.this, PForm.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, PhysicsCard.class));
    }
}