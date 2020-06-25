package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choise extends AppCompatActivity {

    private Button physical, inorganic, organic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise);



        physical = (Button) findViewById(R.id.physical);
        inorganic = (Button) findViewById(R.id.inorganic);
        organic = (Button) findViewById(R.id.organic);

        physical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choise.this, PhysicalChallenge.class));
            }
        });
        inorganic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choise.this, InorganicChallenge.class));
            }
        });
        organic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choise.this, OrganicChallnege.class));
            }
        });

    }
}