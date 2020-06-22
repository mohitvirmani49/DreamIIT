package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class MathsAnswerVerify extends AppCompatActivity {

    private ImageButton back;
    private Button submit, nxt;
    private ImageView question_img;
    private TextView chapterName, marks, number;
    private RadioGroup radioGroup;
    private RadioButton optionA, optionB, optionC, optionD;
    private int alpha = 1;
    private int letter = 1;
    private Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    private DatabaseReference reference;
    private int total = 1;
    private int no = 1;
    private int correct = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_answer_verify);

        back = (ImageButton) findViewById(R.id.back);
        submit = (Button) findViewById(R.id.submit);
        chapterName = (TextView) findViewById(R.id.chapterName);
        nxt = (Button) findViewById(R.id.next);
        marks = (TextView) findViewById(R.id.my_marks);
        question_img = (ImageView) findViewById(R.id.main_qs);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        optionA = (RadioButton) findViewById(R.id.a);
        optionB = (RadioButton) findViewById(R.id.b);
        optionC = (RadioButton) findViewById(R.id.c);
        optionD = (RadioButton) findViewById(R.id.d);
        number = (TextView) findViewById(R.id.number);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("test").child(firebaseUser.getUid());
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
                    }
                    catch (Exception e){
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

    }
    private void updateQuestion() {
        if (letter > 10) {
            submitTest();


        } else {
            number.setText("Q" + no);
            no++;
            Query query = FirebaseDatabase.getInstance().getReference().child("test");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    DatabaseReference fbdatabase = reference;
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

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
                            } else if(mark.equals("")){
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


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            System.out.println("sorry error");

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    System.out.println("sorry error2");

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
        startActivity(new Intent(this, ImagesActivity.class));

    }

}
