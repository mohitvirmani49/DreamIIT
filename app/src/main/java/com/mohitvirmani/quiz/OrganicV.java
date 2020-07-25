package com.mohitvirmani.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

public class OrganicV extends AppCompatActivity {


    private ImageButton back;
    private Button submit, nxt;
    private ImageViewZoom question_img;
    private TextView chapterName, marks, number;
    private RadioGroup radioGroup;
    private RadioButton optionA, optionB, optionC, optionD;
    private int alpha = 1;
    private int letter = 1;
    private Integer[] array = new Integer[51];

    private DatabaseReference reference;
    private int total = 1;
    private int no = 1;
    private int correct = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organic_v);


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

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String v = result.getString("value", "0");
        reference = FirebaseDatabase.getInstance().getReference("test").child(firebaseUser.getUid()).child(v);
        random();
        updateQuestion();

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (letter <= 10) {
                    try {


                        radioGroup.clearCheck();

                        optionA.setBackgroundColor(getResources().getColor(R.color.white));
                        optionB.setBackgroundColor(getResources().getColor(R.color.white));
                        optionC.setBackgroundColor(getResources().getColor(R.color.white));
                        optionD.setBackgroundColor(getResources().getColor(R.color.white));
                        for (int i = 0; i < radioGroup.getChildCount(); i++) {
                            radioGroup.getChildAt(i).setEnabled(false);

                        }
                        updateQuestion();
                    } catch (Exception e) {
                        submitTest();
                    }
                } else {
                    radioGroup.clearCheck();

                    optionA.setBackgroundColor(getResources().getColor(R.color.white));
                    optionB.setBackgroundColor(getResources().getColor(R.color.white));
                    optionC.setBackgroundColor(getResources().getColor(R.color.white));
                    optionD.setBackgroundColor(getResources().getColor(R.color.white));
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        radioGroup.getChildAt(i).setEnabled(false);
                        submitTest();

                    }
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChemistryCard.class));
            }
        });

    }


    private void updateQuestion() {
        if (letter > 10) {
            submitTest();


        } else {
            number.setText("Q" + no);
            no++;
            SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String v = result.getString("value", "0");
            System.out.println("::::::::::::::::::" + v);
            FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
            Query query = FirebaseDatabase.getInstance().getReference().child("test").child(u.getUid()).child(v);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try {


                        String mark = dataSnapshot.child(String.valueOf(letter)).child("optionMarked").getValue().toString();
                        String correctResponse = dataSnapshot.child(String.valueOf(letter)).child("correctAns").getValue().toString();
                        System.out.println("I am alha" + alpha);
                        System.out.println(correctResponse);

//                            String correctResponse = dataSnapshot.child(String.valueOf(letter)).child("CorrectAns").getValue().toString();

                        System.out.println("Mark Mate" + mark);
//                            System.out.println("correct" + correctResponse);

                        if (mark.equals(optionA.getText().toString())) {
                            if (mark.equals(correctResponse)) {
                                System.out.println("Mark val" + mark);
                                System.out.println("hi" + correctResponse);
                                optionA.setBackgroundColor(Color.GREEN);
                            } else if (correctResponse.equals(optionB.getText().toString())) {
                                System.out.println("Mark val" + mark);
                                System.out.println("hiB" + correctResponse);
                                optionA.setBackgroundColor(Color.RED);
                                optionB.setBackgroundColor(Color.GREEN);
                            } else if (correctResponse.equals(optionC.getText().toString())) {
                                System.out.println("Mark val" + mark);
                                System.out.println("hiC" + correctResponse);
                                optionA.setBackgroundColor(Color.RED);
                                optionC.setBackgroundColor(Color.GREEN);

                            } else {
                                System.out.println("Mark val" + mark);
                                System.out.println("hiD" + correctResponse);
                                optionA.setBackgroundColor(Color.RED);
                                optionD.setBackgroundColor(Color.GREEN);
                            }
                        } else if (mark.equals(optionB.getText().toString())) {
                            if (mark.equals(correctResponse)) {
                                optionB.setBackgroundColor(Color.GREEN);
                            } else if (correctResponse.equals(optionA.getText().toString())) {
                                optionB.setBackgroundColor(Color.RED);
                                optionA.setBackgroundColor(Color.GREEN);
                            } else if (correctResponse.equals(optionC.getText().toString())) {
                                optionB.setBackgroundColor(Color.RED);
                                optionC.setBackgroundColor(Color.GREEN);

                            } else {
                                optionB.setBackgroundColor(Color.RED);
                                optionD.setBackgroundColor(Color.GREEN);
                            }
                        } else if (mark.equals(optionC.getText().toString())) {
                            if (mark.equals(correctResponse)) {
                                optionC.setBackgroundColor(Color.GREEN);
                            } else if (correctResponse.equals(optionB.getText().toString())) {
                                optionC.setBackgroundColor(Color.RED);
                                optionB.setBackgroundColor(Color.GREEN);
                            } else if (correctResponse.equals(optionA.getText().toString())) {
                                optionC.setBackgroundColor(Color.RED);
                                optionC.setBackgroundColor(Color.GREEN);

                            } else {
                                optionC.setBackgroundColor(Color.RED);
                                optionD.setBackgroundColor(Color.GREEN);
                            }
                        } else if (mark.equals(optionD.getText().toString())) {
                            if (mark.equals(correctResponse)) {
                                optionD.setBackgroundColor(Color.GREEN);
                            } else if (correctResponse.equals(optionB.getText().toString())) {
                                optionD.setBackgroundColor(Color.RED);
                                optionB.setBackgroundColor(Color.GREEN);
                            } else if (correctResponse.equals(optionC.getText().toString())) {
                                optionD.setBackgroundColor(Color.RED);
                                optionC.setBackgroundColor(Color.GREEN);

                            } else {
                                optionD.setBackgroundColor(Color.RED);
                                optionA.setBackgroundColor(Color.GREEN);
                            }
                        } else if (mark.equals("")) {
//                                if (correctResponse.equals(optionA.getText().toString())) {
//                                    optionA.setBackgroundColor(Color.GREEN);
//                                } else if (correctResponse.equals(optionB.getText().toString())) {
//                                    optionB.setBackgroundColor(Color.GREEN);
//                                } else if (correctResponse.equals(optionC.getText().toString())) {
//                                    optionC.setBackgroundColor(Color.GREEN);
//                                } else {
//                                    optionD.setBackgroundColor(Color.GREEN);
//                                }
                        }
                        letter++;
                    } catch (Exception e) {

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    System.out.println("sorry error");

                }
            });


        }

    }

    public int random() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        return array[alpha];

    }

    private void submitTest() {


        startActivity(new Intent(this, ChemistryCard.class));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, ChemistryCard.class));
    }
}