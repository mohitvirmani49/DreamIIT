package com.mohit.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Arrays;
import java.util.Collections;

import Model.Question;

public class Main9Activity extends AppCompatActivity {


    DatabaseReference reference;
    Button nxt, submit;
    Button optn1, optn2, optn3, optn4;
    TextView questions, timer;
    int correct = 0;
    int wrong = 0;
    int alpha = 0;
    Integer[] array = {1, 2, 3, 4, 5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        nxt = (Button) findViewById(R.id.next);
        submit = (Button) findViewById(R.id.submit);

        optn1 = (Button) findViewById(R.id.radio_btn1);
        optn2 = (Button) findViewById(R.id.radio_btn2);
        optn3 = (Button) findViewById(R.id.radio_btn3);
        optn4 = (Button) findViewById(R.id.radio_btn4);
//        res = (TextView)findViewById(R.id.res);

        questions = (TextView) findViewById(R.id.qstns);
        timer = (TextView) findViewById(R.id.timer);
        random();

        updateQuestions();
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                }
                optn1.setBackgroundColor(getResources().getColor(R.color.Button));
                optn2.setBackgroundColor(getResources().getColor(R.color.Button));
                optn3.setBackgroundColor(getResources().getColor(R.color.Button));
                optn4.setBackgroundColor(getResources().getColor(R.color.Button));

                updateQuestions();


            }
        });


    }

    public int random() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
            System.out.println("Simple array print: " + array[i]);
        }
        Collections.shuffle(Arrays.asList(array));
        return array[alpha];

    }


    public void updateQuestions() {
        if (alpha > 4) {


        } else {

            System.out.println("My value is which should be ques no" + array[0]);
            System.out.println("My value is which should be ques no" + array[1]);
            System.out.println("My value is which should be ques no" + array[2]);
            System.out.println("My value is which should be ques no" + array[3]);
            System.out.println("My value is which should be ques no" + array[4]);


            System.out.println(" alpha = " + alpha);
            System.out.println("Value of question should be " + array[alpha]);
            reference = FirebaseDatabase.getInstance().getReference().child("random").child(String.valueOf(array[alpha]));
            System.out.println("Comfirmation alpha = " + alpha);
            System.out.println("I was able to execute " + array[alpha]);

            alpha++;
            System.out.println("New i" + alpha);


            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);
//                    questions.setText(question.getQuestion());
                    optn1.setText(question.getOption1());
                    optn2.setText(question.getOption2());
                    optn3.setText(question.getOption3());
                    optn4.setText(question.getOption4());

                    optn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optn1.setBackgroundColor(Color.GREEN);

                        }
                    });

                    optn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optn2.setBackgroundColor(Color.GREEN);

                        }
                    });


                    optn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optn3.setBackgroundColor(Color.GREEN);

                        }
                    });


                    optn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optn4.setBackgroundColor(Color.GREEN);

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

