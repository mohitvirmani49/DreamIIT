package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main28Activity extends AppCompatActivity {
    private ImageButton back;
    private CardView practice, challenge, formulae, doubts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main28);

        back = (ImageButton) findViewById(R.id.back1);
        practice = (CardView) findViewById(R.id.phy_phy);
        challenge = (CardView) findViewById(R.id.challenge_phy);
        formulae = (CardView) findViewById(R.id.formulae_phy);
        doubts = (CardView) findViewById(R.id.doubt_phy);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main28Activity.this, Main14Activity.class);
                startActivity(intent);
            }
        });
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main28Activity.this, MathsLayoutPractice.class);
                startActivity(intent);
            }
        });
        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main28Activity.this, MathsChallenge.class);
                startActivity(intent);
            }
        });
        doubts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main28Activity.this, ImagesActivity.class);
                startActivity(intent);
            }
        });
//        formulae.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Main28Activity.this, SliderPage.class));
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Main14Activity.class));
    }
}