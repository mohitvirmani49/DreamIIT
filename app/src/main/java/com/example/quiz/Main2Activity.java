package com.example.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Delayed;

import Model.Question;

public class Main2Activity extends AppCompatActivity {

    DatabaseReference reference;
    Button nxt, submit;
    RadioButton optn1, optn2, optn3, optn4;
    RadioGroup radioGroup;
    TextView questions, timer;
    int total = 1;
    int correct = 0;
    int wrong = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nxt = (Button) findViewById(R.id.next);
        submit = (Button) findViewById(R.id.submit);

        optn1 = (RadioButton) findViewById(R.id.radio_btn1);
        optn2 = (RadioButton) findViewById(R.id.radio_btn2);
        optn3 = (RadioButton) findViewById(R.id.radio_btn3);
        optn4 = (RadioButton) findViewById(R.id.radio_btn4);
        radioGroup = (RadioGroup) findViewById(R.id.rg1);
//        res = (TextView)findViewById(R.id.res);

        questions = (TextView) findViewById(R.id.qstns);
        timer = (TextView) findViewById(R.id.timer);

        updateQuestions();

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                System.out.println("1"+optn1.getId());
                System.out.println("2"+optn2.getId());
                System.out.println("3"+optn3.getId());
                System.out.println("4"+optn4.getId());

                if (optn1.isChecked()) {
                    editor.putString("Value", optn1.getText().toString());
                    editor.apply();
                }
                if (optn3.isChecked()) {
                    editor.putString("Value1", optn3.getText().toString());
                    editor.apply();

                }
                if (optn2.isChecked()) {
                    editor.apply();
                    System.out.println("Mai tera hero" + optn2.getId());


                }
                if (optn4.isChecked()) {
                    editor.putInt("Value3", optn4.getId());
//                    optn1.
                    editor.apply();

                }
//                editor.putString("Value", String.valueOf(radioGroup.getCheckedRadioButtonId()));
//                editor.apply();
                optn1.setBackgroundColor(getResources().getColor(R.color.Button));
                optn2.setBackgroundColor(getResources().getColor(R.color.Button));
                optn3.setBackgroundColor(getResources().getColor(R.color.Button));
                optn4.setBackgroundColor(getResources().getColor(R.color.Button));

                optn1.setChecked(false);
                optn2.setChecked(false);
                optn3.setChecked(false);
                optn4.setChecked(false);

                updateQuestions();


            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main2Activity.this, Main10Activity.class);
                myIntent.putExtra("intVariableName", correct);
                startActivity(myIntent);

//                res.setText(String.valueOf(correct));

            }
        });

    }

    public void updateQuestions() {
        String a = "Questions";
        if (total > 10) {

        } else {


            System.out.println("Total value is " + total);
            reference = FirebaseDatabase.getInstance().getReference().child(a).child(String.valueOf(total));
            System.out.println("I executed" + total);
            total++;


            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);
                    questions.setText(question.getQuestion());
                    optn1.setText(question.getOption1());
                    optn2.setText(question.getOption2());
                    optn3.setText(question.getOption3());
                    optn4.setText(question.getOption4());


                    optn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optn2.setChecked(false);
                            optn2.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn3.setChecked(false);
                            optn3.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn4.setChecked(false);
                            optn4.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn1.setBackgroundColor(Color.GREEN);

//                            editor.putString("Value", String.valueOf(optn1.getText()));
//                            editor.apply();

                        }
                    });

                    optn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optn1.setChecked(false);
                            optn1.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn4.setChecked(false);
                            optn4.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn3.setChecked(false);
                            optn3.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn2.setBackgroundColor(Color.GREEN);

//                            editor.putString("Value", String.valueOf(optn1.getText()));
//                            editor.apply();

                        }
                    });


                    optn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optn1.setChecked(false);
                            optn1.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn2.setChecked(false);
                            optn2.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn4.setChecked(false);
                            optn4.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn3.setBackgroundColor(Color.GREEN);

//                            editor.putString("Value", String.valueOf(optn1.getText()));
//                            editor.apply();

                        }
                    });


                    optn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optn1.setChecked(false);
                            optn1.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn2.setChecked(false);
                            optn2.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn3.setChecked(false);
                            optn3.setBackgroundColor(getResources().getColor(R.color.Button));
                            optn4.setBackgroundColor(Color.GREEN);
//
//                            editor.putString("Value", String.valueOf(optn1.getText()));
//                            editor.apply();

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }


}

