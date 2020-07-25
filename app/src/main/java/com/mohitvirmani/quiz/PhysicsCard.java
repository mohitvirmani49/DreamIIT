package com.mohitvirmani.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PhysicsCard extends AppCompatActivity {
    private ImageButton back;
    private CardView practice, challenge, formulae, doubts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main26);

        back = (ImageButton) findViewById(R.id.back1);
        practice = (CardView) findViewById(R.id.phy_phy);
        challenge = (CardView) findViewById(R.id.challenge_phy);
        formulae = (CardView) findViewById(R.id.formulae_phy);
        doubts = (CardView) findViewById(R.id.doubt_phy);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhysicsCard.this, MainPage.class);
                startActivity(intent);
            }
        });
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhysicsCard.this, PhysicsRecyclerV.class);
                startActivity(intent);
            }
        });
        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhysicsCard.this, PhyPracticeCh.class);
                startActivity(intent);
            }
        });
        doubts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhysicsCard.this, ImagesActivity.class);
                startActivity(intent);
            }
        });
        formulae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhysicsCard.this, PhyForm.class);
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