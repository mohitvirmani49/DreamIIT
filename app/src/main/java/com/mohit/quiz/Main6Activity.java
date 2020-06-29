package com.mohit.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Question;

public class Main6Activity extends AppCompatActivity {


    DatabaseReference reference;
    Button nxt, submit;
    Button optn1, optn2, optn3, optn4;
    TextView questions, timer;
    int total = 1;
    int correct = 0;
    int wrong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        nxt = (Button) findViewById(R.id.next);
        submit = (Button) findViewById(R.id.submit);

        optn1 = (Button) findViewById(R.id.radio_btn1);
        optn2 = (Button) findViewById(R.id.radio_btn2);
        optn3 = (Button) findViewById(R.id.radio_btn3);
        optn4 = (Button) findViewById(R.id.radio_btn4);
//        res = (TextView)findViewById(R.id.res);

        questions = (TextView) findViewById(R.id.qstns);
        timer = (TextView) findViewById(R.id.timer);

        updateQuestions();
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(optn1.isSelected()||optn2.isSelected()||optn3.isSelected()||optn4.isSelected()){
//                    optn1.setSelected(false);
//                    optn2.setSelected(false);
//                    optn3.setSelected(false);
//                    optn4.setSelected(false);
//                }
                optn1.setBackgroundColor(getResources().getColor(R.color.Button));
                optn2.setBackgroundColor(getResources().getColor(R.color.Button));
                optn3.setBackgroundColor(getResources().getColor(R.color.Button));
                optn4.setBackgroundColor(getResources().getColor(R.color.Button));

                updateQuestions();


            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main6Activity.this, Main3Activity.class);
                myIntent.putExtra("intVariableName", correct);
                startActivity(myIntent);

//                res.setText(String.valueOf(correct));

            }
        });

    }

    public void updateQuestions() {
        if (total > 2) {

        } else {
            reference = FirebaseDatabase.getInstance().getReference().child("Sequence_medium").child(String.valueOf(total));
            total++;
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
                            if (optn1.getText().toString().equals(question.getAnswer())) {
//                                optn1.setBackgroundColor(Color.RED);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));

//
//
                                    }
                                }, 1500);
                            } else {
                                wrong++;
//                                optn1.setBackgroundColor(Color.BLUE);
//                                if(optn2.getText().toString().equals(question.getAnswer())){
//                                    optn2.setBackgroundColor(Color.CYAN);
//                                }else if(optn3.getText().toString().equals(question.getAnswer())){
//                                    optn3.setBackgroundColor(Color.CYAN);
//                                }else if(optn4.getText().toString().equals(question.getAnswer())){
//                                    optn4.setBackgroundColor(Color.CYAN);
//                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn2.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn3.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();
//
                                    }
                                }, 1500);
////                                updateQuestions();
                            }
                        }
                    });

                    optn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optn2.setBackgroundColor(Color.GREEN);
                            if (optn2.getText().toString().equals(question.getAnswer())) {
//                                optn1.setBackgroundColor(Color.RED);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));

//
//
                                    }
                                }, 1500);
                            } else {
                                wrong++;
//                                optn1.setBackgroundColor(Color.BLUE);
//                                if(optn2.getText().toString().equals(question.getAnswer())){
//                                    optn2.setBackgroundColor(Color.CYAN);
//                                }else if(optn3.getText().toString().equals(question.getAnswer())){
//                                    optn3.setBackgroundColor(Color.CYAN);
//                                }else if(optn4.getText().toString().equals(question.getAnswer())){
//                                    optn4.setBackgroundColor(Color.CYAN);
//                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn2.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn3.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();
//
                                    }
                                }, 1500);
////                                updateQuestions();
                            }
                        }
                    });


                    optn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optn3.setBackgroundColor(Color.GREEN);
                            if (optn3.getText().toString().equals(question.getAnswer())) {
//                                optn1.setBackgroundColor(Color.RED);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));

//
//
                                    }
                                }, 1500);
                            } else {
                                wrong++;
//                                optn1.setBackgroundColor(Color.BLUE);
//                                if(optn2.getText().toString().equals(question.getAnswer())){
//                                    optn2.setBackgroundColor(Color.CYAN);
//                                }else if(optn3.getText().toString().equals(question.getAnswer())){
//                                    optn3.setBackgroundColor(Color.CYAN);
//                                }else if(optn4.getText().toString().equals(question.getAnswer())){
//                                    optn4.setBackgroundColor(Color.CYAN);
//                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn2.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn3.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();
//
                                    }
                                }, 1500);
////                                updateQuestions();
                            }
                        }
                    });


                    optn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optn4.setBackgroundColor(Color.GREEN);
                            if (optn4.getText().toString().equals(question.getAnswer())) {
//                                optn1.setBackgroundColor(Color.RED);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));

//
//
                                    }
                                }, 1500);
                            } else {
                                wrong++;
//                                optn1.setBackgroundColor(Color.BLUE);
//                                if(optn2.getText().toString().equals(question.getAnswer())){
//                                    optn2.setBackgroundColor(Color.CYAN);
//                                }else if(optn3.getText().toString().equals(question.getAnswer())){
//                                    optn3.setBackgroundColor(Color.CYAN);
//                                }else if(optn4.getText().toString().equals(question.getAnswer())){
//                                    optn4.setBackgroundColor(Color.CYAN);
//                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn2.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn3.setBackgroundColor(Color.parseColor("#03A9F4"));
//                                        optn4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();
//
                                    }
                                }, 1500);
////                                updateQuestions();
                            }
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
