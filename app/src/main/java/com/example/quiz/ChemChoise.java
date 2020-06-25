package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class ChemChoise extends AppCompatActivity {

    private Button physical, inorganic, organic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chem_choise);

        physical = (Button) findViewById(R.id.physical);
        inorganic = (Button) findViewById(R.id.inorganic);
        organic = (Button) findViewById(R.id.organic);

        physical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChemChoise.this, Physical.class));
            }
        });
        inorganic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChemChoise.this, Inorganic.class));
            }
        });
        organic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChemChoise.this, Main31Activity.class));
            }
        });



    }

}