package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.Collections;

import Model.Question;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

public class OrganicPTest extends AppCompatActivity {


    private ImageButton back;
    private Button submit, nxt;
    private ImageViewZoom question_img;
    private TextView chapterName, marks, number;
    private RadioGroup radioGroup;
    private RadioButton optionA, optionB, optionC, optionD;
    private int alpha = 0;
    private Integer[] array = new Integer[51];

    private DatabaseReference reference;
    private int total = 1;
    private int no = 1;
    private int correct = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organic_p_test);



        back = (ImageButton) findViewById(R.id.back);
        submit = (Button) findViewById(R.id.submit);
        chapterName = (TextView) findViewById(R.id.chapterName);
        nxt = (Button) findViewById(R.id.next);
        marks = (TextView) findViewById(R.id.my_marks);
        question_img = (ImageViewZoom) findViewById(R.id.main_qs);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        optionA = (RadioButton) findViewById(R.id.a);
        optionB = (RadioButton) findViewById(R.id.b);
        optionC = (RadioButton) findViewById(R.id.c);
        optionD = (RadioButton) findViewById(R.id.d);
        number = (TextView) findViewById(R.id.number);

        random();
        updateQuestion();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(OrganicPTest.this)
                        .setMessage("Are you sure you want to submit the Test")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                submitTest();
                            }
                        }).setNegativeButton("No", null)
                        .show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backpresss();

            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alpha <= 9) {
                    radioGroup.clearCheck();

                    optionA.setBackgroundColor(getResources().getColor(R.color.white));
                    optionB.setBackgroundColor(getResources().getColor(R.color.white));
                    optionC.setBackgroundColor(getResources().getColor(R.color.white));
                    optionD.setBackgroundColor(getResources().getColor(R.color.white));
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        radioGroup.getChildAt(i).setEnabled(true);

                    }

                    updateQuestion();
                } else {
                    radioGroup.clearCheck();

                    optionA.setBackgroundColor(getResources().getColor(R.color.white));
                    optionB.setBackgroundColor(getResources().getColor(R.color.white));
                    optionC.setBackgroundColor(getResources().getColor(R.color.white));
                    optionD.setBackgroundColor(getResources().getColor(R.color.white));
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        radioGroup.getChildAt(i).setEnabled(true);
                        submitTest();

                    }

                }

            }
        });

    }
    public int random() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(array));
        return array[alpha];

    }


    private void updateQuestion() {
        if (alpha > 10) {
            submitTest();

        } else {

            Intent intent = getIntent();
            final String value = intent.getStringExtra("val");
            chapterName.setText(value);

            reference = FirebaseDatabase.getInstance().getReference("Organic Chemistry").child(intent.getStringExtra("val")).child(String.valueOf(array[alpha]));
            alpha++;
            number.setText("Q" + no + " :");
            no++;
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);
                    Picasso.get().load(question.getQuestion()).fit().into(question_img);

                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            System.out.println(question.getAnswer() + "Name:::::::::");
                            if (optionA.isChecked()) {
                                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                                    radioGroup.getChildAt(i).setEnabled(false);
                                }
                                if (optionA.getText().toString().equals(question.getAnswer())) {
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct = correct + 4;
                                            marks.setText("My Marks: " + correct);

                                        }
                                    }, 1000);

                                    optionA.setBackgroundColor(Color.GREEN);

                                } else {
                                    optionA.setBackgroundColor(Color.RED);
                                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    v.vibrate(400);

                                    if (optionB.getText().toString().equals(question.getAnswer())) {

                                        optionB.setBackgroundColor(Color.GREEN);
                                    } else if (optionC.getText().toString().equals(question.getAnswer())) {
                                        optionC.setBackgroundColor(Color.GREEN);
                                    } else {
                                        optionD.setBackgroundColor(Color.GREEN);
                                    }
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {


                                        }
                                    }, 1000);

                                }
                            } else if (optionB.isChecked()) {
                                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                                    radioGroup.getChildAt(i).setEnabled(false);
                                }
                                if (optionB.getText().toString().equals(question.getAnswer())) {

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct = correct + 4;
                                            marks.setText("My Marks: " + correct);

                                        }
                                    }, 1000);

                                    optionB.setBackgroundColor(Color.GREEN);

                                } else {
                                    optionB.setBackgroundColor(Color.RED);
                                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    v.vibrate(400);

                                    if (optionA.getText().toString().equals(question.getAnswer())) {

                                        optionA.setBackgroundColor(Color.GREEN);
                                    } else if (optionC.getText().toString().equals(question.getAnswer())) {
                                        optionC.setBackgroundColor(Color.GREEN);
                                    } else {
                                        optionD.setBackgroundColor(Color.GREEN);
                                    }
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {


                                        }
                                    }, 1000);

                                }
                            } else if (optionC.isChecked()) {
                                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                                    radioGroup.getChildAt(i).setEnabled(false);
                                }
                                if (optionC.getText().toString().equals(question.getAnswer())) {

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct = correct + 4;
                                            marks.setText("My Marks: " + correct);

                                        }
                                    }, 1000);

                                    optionC.setBackgroundColor(Color.GREEN);

                                } else {
                                    optionC.setBackgroundColor(Color.RED);
                                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    v.vibrate(400);

                                    if (optionB.getText().toString().equals(question.getAnswer())) {

                                        optionB.setBackgroundColor(Color.GREEN);
                                    } else if (optionA.getText().toString().equals(question.getAnswer())) {
                                        optionA.setBackgroundColor(Color.GREEN);
                                    } else {
                                        optionD.setBackgroundColor(Color.GREEN);
                                    }
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                        }
                                    }, 1000);

                                }
                            } else if (optionD.isChecked()) {
                                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                                    radioGroup.getChildAt(i).setEnabled(false);
                                }
                                if (optionD.getText().toString().equals(question.getAnswer())) {

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct = correct + 4;
                                            marks.setText("My Marks: " + correct);

                                        }
                                    }, 1000);

                                    optionD.setBackgroundColor(Color.GREEN);

                                } else {
                                    optionD.setBackgroundColor(Color.RED);
                                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    v.vibrate(400);

                                    if (optionB.getText().toString().equals(question.getAnswer())) {

                                        optionB.setBackgroundColor(Color.GREEN);
                                    } else if (optionC.getText().toString().equals(question.getAnswer())) {
                                        optionC.setBackgroundColor(Color.GREEN);
                                    } else {
                                        optionA.setBackgroundColor(Color.GREEN);
                                    }
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                        }
                                    }, 1000);

                                }
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

    private void submitTest() {
        Intent myIntent = new Intent(OrganicPTest.this, MathsResultPractice.class);
        myIntent.putExtra("intVariableName", correct);
        startActivity(myIntent);

    }


    private void backpresss() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(700);

        new AlertDialog.Builder(OrganicPTest.this)
                .setMessage("Are you sure you want to exit the Test")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(OrganicPTest.this, Main27Activity.class));
                    }
                }).setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(700);

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit the Test")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(OrganicPTest.this, Main27Activity.class));
                    }
                }).setNegativeButton("No", null)
                .show();
    }
}