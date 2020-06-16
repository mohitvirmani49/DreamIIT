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

public class Main33Activity extends AppCompatActivity {
    private ImageButton back;
    private Button next;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main33);

        back = (ImageButton) findViewById(R.id.back9);
        next = (Button) findViewById(R.id.next);
        tv = (TextView)findViewById(R.id.chapter8);
//
        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String chapter = result.getString("chapter","0");
        tv.setText(result.getString("chapter","0"));

//
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("chapter", chapter);
//        editor.apply();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main33Activity.this, Main26Activity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main33Activity.this, Main34Activity.class);
                intent.putExtra("chapter",chapter);
                startActivity(intent);

            }
        });
    }
}