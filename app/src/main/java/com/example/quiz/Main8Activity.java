package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import Model.Question;

public class Main8Activity extends AppCompatActivity {

    int total = 1;
    DatabaseReference reference;
    ImageView imageView;
    Button optn1, optn2, optn3, optn4, nxt, sbmt;
    TextView questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        imageView = (ImageView) findViewById(R.id.img);

        optn1 = (Button) findViewById(R.id.radio_btn1);
        optn2 = (Button) findViewById(R.id.radio_btn2);
        optn3 = (Button) findViewById(R.id.radio_btn3);
        optn4 = (Button) findViewById(R.id.radio_btn4);

        nxt = (Button) findViewById(R.id.next);
        sbmt = (Button) findViewById(R.id.submit);

        questions = (TextView) findViewById(R.id.qstns);

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


    }

    public void updateQuestions() {
        String a = "create_own";
        if (total > 3) {

        } else {

            reference = FirebaseDatabase.getInstance().getReference().child(a).child(String.valueOf(total));
            total++;
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);

                    questions.setText(question.getQuestion());
                    Picasso.get().load(question.getImage()).into(imageView);

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
//                                        correct++;
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));

//
//
                                    }
                                }, 1500);
                            } else {
//                                wrong++;
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
//                                        correct++;
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));

//
//
                                    }
                                }, 1500);
                            } else {
//                                wrong++;
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
//                                        correct++;
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));

//
//
                                    }
                                }, 1500);
                            } else {
//                                wrong++;
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
//                                        correct++;
//                                        optn1.setBackgroundColor(Color.parseColor("#03A9F4"));

//
//
                                    }
                                }, 1500);
                            } else {
//                                wrong++;
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
