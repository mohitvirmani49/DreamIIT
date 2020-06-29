package com.mohit.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import Model.Question;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

public class PForm extends AppCompatActivity {
    private ImageViewZoom formulaes;
    int total = 1;
    private Button nxt;
    private TextView tv;
    private ImageButton back9;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_form);

        formulaes = (ImageViewZoom) findViewById(R.id.formulaes);
        nxt = (Button) findViewById(R.id.next);
        tv = (TextView) findViewById(R.id.ch);
        back9 = (ImageButton) findViewById(R.id.back9);
        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String chapter = result.getString("chapter", "0");
        tv.setText(chapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Phy").child(chapter);

        updateQuestion();
        back9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PhyForm.class));
            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    updateQuestion();
                } catch (Exception e) {
                    startActivity(new Intent(getApplicationContext(), PhyForm.class));
                }
            }
        });

    }

    private void updateQuestion() {
        DatabaseReference dr = databaseReference.child(String.valueOf(total));
        total++;
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {


                    final Question question = dataSnapshot.getValue(Question.class);
                    Picasso.get().load(question.getQuestion()).fit().into(formulaes);
                } catch (Exception e) {
                    startActivity(new Intent(getApplicationContext(), PhyForm.class));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, PhyForm.class));
    }
}