package com.mohitvirmani.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class FullTestInstr extends AppCompatActivity {

    private ImageButton back;
    private Button next;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_test_instr);

        back = (ImageButton) findViewById(R.id.back9);
        next = (Button) findViewById(R.id.next);
        tv = (TextView) findViewById(R.id.chapter8);

        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String chapter = result.getString("chapter", "0");
//        tv.setText(chapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FullTestInstr.this, PhysicsCard.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(300);

                new AlertDialog.Builder(FullTestInstr.this)
                        .setMessage("Start the test now?")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(FullTestInstr.this, FullTestExam.class);
                                intent.putExtra("val", chapter);
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", null)
                        .show();


            }
        });


    }
}