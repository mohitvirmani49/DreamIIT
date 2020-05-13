package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1;
    Button phy,chem,maths,full;
    Button own_test;
    Button random_ques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.btn);
        phy = (Button)findViewById(R.id.physics);
        chem = (Button)findViewById(R.id.chemistry);
        maths = (Button)findViewById(R.id.maths);
        full = (Button) findViewById(R.id.full_paper);
        own_test = (Button)findViewById(R.id.own_test);
        random_ques = (Button) findViewById(R.id.random_questions);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();

            }
        });

        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_maths();
            }
        });

        own_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main8Activity.class);
                startActivity(intent);

            }
        });

        random_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main9Activity.class);
                startActivity(intent);

            }
        });


    }
    private void startQuiz(){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    private void start_maths(){
        Intent intent = new Intent(this,Main4Activity.class);
        startActivity(intent);

    }

}
