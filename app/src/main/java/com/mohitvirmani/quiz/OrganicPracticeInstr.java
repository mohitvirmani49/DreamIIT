package com.mohitvirmani.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class OrganicPracticeInstr extends AppCompatActivity {
    private ImageButton back;
    private Button next;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organic_practice_instr);

        back = (ImageButton) findViewById(R.id.back9);
        next = (Button) findViewById(R.id.next);
        tv = (TextView) findViewById(R.id.chapter8);
//
        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String chapter = result.getString("chapter", "0");
//        tv.setText(chapter);

        System.out.println("Hiii Hello8" + chapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganicPracticeInstr.this, Main27Activity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganicPracticeInstr.this, OrganicPTest.class);
                intent.putExtra("val",chapter);
                startActivity(intent);

            }
        });
    }
}