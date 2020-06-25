package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PhysicalChallengeInstr extends AppCompatActivity {

    private ImageButton back;
    private Button next;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_challenge_instr);


        back = (ImageButton) findViewById(R.id.back9);
        next = (Button) findViewById(R.id.next);
        tv = (TextView) findViewById(R.id.chapter8);

        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String chapter = result.getString("chapter", "0");
        tv.setText(chapter);

        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("test").child(u.getUid()).child(chapter);
        databaseReference.removeValue();

        DatabaseReference d = FirebaseDatabase.getInstance().getReference("marks").child(u.getUid()).child(chapter);
        d.removeValue();

//        DatabaseReference mydatabase = FirebaseDatabase.getInstance().getReference("test");
//        mydatabase.removeValue();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhysicalChallengeInstr.this, Main27Activity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhysicalChallengeInstr.this, PhysicalCTest.class);
                intent.putExtra("val", chapter);
                startActivity(intent);

            }
        });

    }
}