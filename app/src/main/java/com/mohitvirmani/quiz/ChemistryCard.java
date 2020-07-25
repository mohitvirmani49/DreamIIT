package com.mohitvirmani.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChemistryCard extends AppCompatActivity {
    private ImageButton back;
    private CardView practice, challenge, formulae, doubts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main27);

        back = (ImageButton) findViewById(R.id.back2);
        practice = (CardView) findViewById(R.id.chem_chem);
        challenge = (CardView) findViewById(R.id.pp);
        formulae = (CardView) findViewById(R.id.formulae_chem);
        doubts = (CardView) findViewById(R.id.doubt_chem);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChemistryCard.this, MainPage.class);
                startActivity(intent);
            }
        });
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChemistryCard.this, ChemChoise.class);
                startActivity(intent);
            }
        });
        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChemistryCard.this, Choise.class);
                startActivity(intent);
            }
        });
        doubts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChemistryCard.this, ImagesActivity.class);
                startActivity(intent);
            }
        });
        formulae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChemistryCard.this, ChemF.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainPage.class));
    }
}