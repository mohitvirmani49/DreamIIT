package com.mohitvirmani.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity {
    Button seqnseries, trigo, pnc, bt, diff, integ, probab, easy_seqnseries, med_seqnseries, hard_seqnseries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        seqnseries = (Button)findViewById(R.id.sequence_series);
        trigo = (Button)findViewById(R.id.trigo);
        pnc = (Button)findViewById(R.id.pnc);
        bt = (Button) findViewById(R.id.binomial);
        diff = (Button) findViewById(R.id.differentiation);
        integ = (Button)findViewById(R.id.integration);
        probab = (Button) findViewById(R.id.probability);
        easy_seqnseries = (Button)findViewById(R.id.seqnseries_easy);
        med_seqnseries = (Button)findViewById(R.id.seqnseries_med);
        hard_seqnseries = (Button)findViewById(R.id.seqnseries_hard);

        seqnseries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easy_seqnseries.setVisibility(View.VISIBLE);
                med_seqnseries.setVisibility(View.VISIBLE);
                hard_seqnseries.setVisibility(View.VISIBLE);
            }
        });
        easy_seqnseries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this,Main5Activity.class);
                startActivity(intent);
            }
        });
        med_seqnseries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this,Main6Activity.class);
                startActivity(intent);
            }
        });
        hard_seqnseries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this,Main7Activity.class);
                startActivity(intent);
            }
        });


    }
}
