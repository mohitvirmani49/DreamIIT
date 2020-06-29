package com.mohit.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ChemChoise extends AppCompatActivity {

    private Button physical, inorganic, organic;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chem_choise);

        physical = (Button) findViewById(R.id.physical);
        inorganic = (Button) findViewById(R.id.inorganic);
        organic = (Button) findViewById(R.id.organic);

        back = (ImageButton) findViewById(R.id.back);

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChemChoise.this, Main27Activity.class));
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ChemChoise.this, Main27Activity.class));
    }
}