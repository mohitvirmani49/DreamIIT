package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Model.Question;
import Model.Test;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

public class OrganicCTest extends AppCompatActivity {



    private TextView timer;
    private int time = 1800;

    private ImageButton back;
    private Button submit, nxt;
    private ImageViewZoom question_img;
    private TextView chapterName, number;
    private RadioGroup radioGroup;
    private RadioButton optionA, optionB, optionC, optionD;
    private int alpha = 0;
    private Integer[] array = new Integer[51];

    private DatabaseReference reference;
    private DatabaseReference mDatabaseRef;
    private int total = 1;
    private int no = 1;
    private int correct = 0;
    int truth = 0;
    int bluff = 0;
    int notattempt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organic_c_test);



        back = (ImageButton) findViewById(R.id.back);
        submit = (Button) findViewById(R.id.submit);
        chapterName = (TextView) findViewById(R.id.chapterName);
        nxt = (Button) findViewById(R.id.next);
        question_img = (ImageViewZoom) findViewById(R.id.main_qs);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        optionA = (RadioButton) findViewById(R.id.a);
        optionB = (RadioButton) findViewById(R.id.b);
        optionC = (RadioButton) findViewById(R.id.c);
        optionD = (RadioButton) findViewById(R.id.d);
        number = (TextView) findViewById(R.id.number);

        timer = (TextView) findViewById(R.id.my_marks);
        startTimer();
//        Query query = FirebaseDatabase.getInstance().getReference()

        random();
        updateQuestion();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        Intent intent = getIntent();
        final String value3 = intent.getStringExtra("val");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("test").child(firebaseUser.getUid()).child(value3);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(OrganicCTest.this)
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


        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alpha <= 9) {
                    if (optionA.isChecked() || optionB.isChecked() || optionC.isChecked() || optionD.isChecked()) {

                    } else {

//                        Test test = new Test(optionA.getText().toString(), question.getAnswer());
                        notattempt++;
                        Map<String, Object> updates = new HashMap<String, Object>();
                        updates.clear();
                        updates.put("optionMarked", "");
                        updates.put("correctAns", "");
                        mDatabaseRef.child(String.valueOf(alpha)).updateChildren(updates);


                    }
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backpresss();

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

            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


            Intent intent = getIntent();
            final String value = intent.getStringExtra("val");

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("value", value);
            editor.apply();
//                            String myAnswer = dataSnapshot.child("mAnswer").getValue().toString();
//
//                            editor.putString("myAns", myAnswer);
//                            editor.apply();
//
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

                                Model.Test test = new Model.Test(optionA.getText().toString(), question.getAnswer());

                                Map<String, Object> updates = new HashMap<String, Object>();
                                updates.clear();
                                updates.put("optionMarked", optionA.getText().toString());
                                updates.put("correctAns", question.getAnswer());


                                mDatabaseRef.child(String.valueOf(alpha)).updateChildren(updates);

                                DatabaseReference dab = FirebaseDatabase.getInstance().getReference("marks").child(firebaseUser.getUid()).child(value);


                                Map<String, Object> updates1 = new HashMap<String, Object>();
                                updates.clear();
                                if(optionA.getText().toString().equals(question.getAnswer())){
                                    updates1.put("marks",String.valueOf(1));

                                }
//                                updates.put("optionMarked", optionA.getText().toString());
//                                updates.put("correctAns", question.getAnswer());


                                dab.child(String.valueOf(alpha)).updateChildren(updates1);


                                System.out.println("Hurray uploaded");

                                if (optionA.getText().toString().equals(question.getAnswer())) {
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct = correct + 4;
                                            truth++;


                                        }
                                    }, 1000);


                                } else {
                                    correct = correct - 1;
                                    bluff++;
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {


                                        }
                                    }, 1000);

                                }
                            } else if (optionB.isChecked()) {


                                Model.Test test = new Model.Test(optionA.getText().toString(), question.getAnswer());

                                Map<String, Object> updates = new HashMap<String, Object>();
                                updates.put("optionMarked", optionB.getText().toString());
                                updates.put("correctAns", question.getAnswer());


                                mDatabaseRef.child(String.valueOf(alpha)).updateChildren(updates);


                                DatabaseReference dab = FirebaseDatabase.getInstance().getReference("marks").child(firebaseUser.getUid()).child(value);


                                Map<String, Object> updates1 = new HashMap<String, Object>();
                                updates.clear();
                                if(optionB.getText().toString().equals(question.getAnswer())){
                                    updates1.put("marks",String.valueOf(1));
                                }
//                                updates.put("optionMarked", optionA.getText().toString());
//                                updates.put("correctAns", question.getAnswer());


                                dab.child(String.valueOf(alpha)).updateChildren(updates1);



                                if (optionB.getText().toString().equals(question.getAnswer())) {

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct = correct + 4;
                                            truth++;

                                        }
                                    }, 1000);


                                } else {
                                    correct = correct - 1;
                                    bluff++;
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {


                                        }
                                    }, 1000);

                                }
                            } else if (optionC.isChecked()) {


                                Model.Test test = new Model.Test(optionA.getText().toString(), question.getAnswer());

                                Map<String, Object> updates = new HashMap<String, Object>();
                                updates.put("optionMarked", optionC.getText().toString());
                                updates.put("correctAns", question.getAnswer());


                                mDatabaseRef.child(String.valueOf(alpha)).updateChildren(updates);


                                DatabaseReference dab = FirebaseDatabase.getInstance().getReference("marks").child(firebaseUser.getUid()).child(value);


                                Map<String, Object> updates1 = new HashMap<String, Object>();
                                updates.clear();
                                if(optionC.getText().toString().equals(question.getAnswer())){
                                    updates1.put("marks",String.valueOf(1));
                                }
//                                updates.put("optionMarked", optionA.getText().toString());
//                                updates.put("correctAns", question.getAnswer());


                                dab.child(String.valueOf(alpha)).updateChildren(updates1);



                                if (optionC.getText().toString().equals(question.getAnswer())) {

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct = correct + 4;
                                            truth++;

                                        }
                                    }, 1000);

                                } else {
                                    correct = correct - 1;
                                    bluff++;

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                        }
                                    }, 1000);

                                }
                            } else if (optionD.isChecked()) {


                                Model.Test test = new Test(optionA.getText().toString(), question.getAnswer());

                                Map<String, Object> updates = new HashMap<String, Object>();
                                updates.put("optionMarked", optionD.getText().toString());
                                updates.put("correctAns", question.getAnswer());


                                mDatabaseRef.child(String.valueOf(alpha)).updateChildren(updates);


                                DatabaseReference dab = FirebaseDatabase.getInstance().getReference("marks").child(firebaseUser.getUid()).child(value);


                                Map<String, Object> updates1 = new HashMap<String, Object>();
                                updates.clear();
                                if(optionD.getText().toString().equals(question.getAnswer())){
                                    updates1.put("marks",String.valueOf(1));
                                }
//                                updates.put("optionMarked", optionA.getText().toString());
//                                updates.put("correctAns", question.getAnswer());


                                dab.child(String.valueOf(alpha)).updateChildren(updates1);



                                if (optionD.getText().toString().equals(question.getAnswer())) {

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            correct = correct + 4;
                                            truth++;

                                        }
                                    }, 1000);


                                } else {
                                    correct = correct - 1;
                                    bluff++;
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

    private void startTimer() {

        new CountDownTimer(1800000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int min = time / 60;
                int sec = time % 60;
                timer.setText(checkDigit(min) + ":" + checkDigit(sec));
                time--;

            }

            @Override
            public void onFinish() {
                timer.setText("Times Up!");
                Toast.makeText(OrganicCTest.this, "Time is up! Your test has been submitted automatically", Toast.LENGTH_LONG).show();
                submitTest();

            }
        }.start();
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    private void submitTest() {
        Intent intent = getIntent();

        Intent myIntent = new Intent(OrganicCTest.this, OrganicResult.class);
        myIntent.putExtra("intVariableName", correct);
        myIntent.putExtra("truth", truth);
        myIntent.putExtra("bluff", bluff);
        myIntent.putExtra("not", notattempt);

        String value = intent.getStringExtra("val");
        myIntent.putExtra("value", value);

        startActivity(myIntent);
    }


    private void backpresss() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(700);

        new AlertDialog.Builder(OrganicCTest.this)
                .setMessage("Are you sure you want to exit the Test")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(OrganicCTest.this, Main27Activity.class));
                    }
                }).setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onBackPressed() {

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(700);

        new AlertDialog.Builder(OrganicCTest.this)
                .setMessage("Are you sure you want to exit the Test")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(OrganicCTest.this, Main27Activity.class));
                    }
                }).setNegativeButton("No", null)
                .show();

    }
}