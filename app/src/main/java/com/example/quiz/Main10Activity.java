package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Question;

public class Main10Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main10);

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


    }

    public void updateQuestions() {
        if (total > 10) {

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

                    SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                    String optn1ans = result.getString("Value","0");
                    String optn2ans = result.getString("Value2","0");
                    String optn3ans = result.getString("Value1","0");
                    String optn4ans = result.getString("Value3","0");

                    System.out.println("Dil neh yeh kaha" + optn1ans);
//                    String optn1_ans = result.getString("Value", "Data");
//                    String optn2_ans = result.getString("Value2", "Data");
//                    String optn3_ans = result.getString("Value1", "Data");
//                    String optn4_ans = result.getString("Value3", "Data");


//                    optn1.setBackgroundColor(Color.GREEN);
//                    if (optn1.getText().toString().equals(question.getAnswer())) {
//                        if (optn1_ans.equals(optn1.getText().toString().equals(question.answer))) {
//                            timer.setText("Correct");
//                        } else {
//                            timer.setText("Wrong");
//
//
//                        }
//
//                    } else if (optn2.getText().toString().equals(question.answer)) {
//                        if (optn2_ans.equals(optn2.getText().toString().equals(question.answer))) {
//                            timer.setText("Correct");
//                        } else {
//                            timer.setText("Wrong");
//
//
//                        }
//
//
//                    } else if (optn3.getText().toString().equals(question.answer)) {
//                        if (optn3_ans.equals(optn3.getText().toString().equals(question.answer))) {
//                            timer.setText("Correct");
//                        } else {
//                            timer.setText("Wrong");
//
//
//                        }
//
//
//                    } else if (optn4.getText().toString().equals(question.answer)) {
//                        if (optn4_ans.equals(optn4.getText().toString().equals(question.answer))) {
//                            timer.setText("Correct");
//                        } else {
//                            timer.setText("Wrong");
//
//
//                        }
//
//
//                    }
//

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }
}
