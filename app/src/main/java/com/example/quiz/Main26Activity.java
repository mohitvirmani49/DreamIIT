package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Main26Activity extends AppCompatActivity {
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
                Intent intent = new Intent(Main26Activity.this, Main14Activity.class);
                startActivity(intent);
            }
        });
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main26Activity.this, Main29Activity.class);
                startActivity(intent);
            }
        });
        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main26Activity.this, Main30Activity.class);
                startActivity(intent);
            }
        });
        doubts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main26Activity.this, ImagesActivity.class);
                startActivity(intent);
            }
        });
    }
}