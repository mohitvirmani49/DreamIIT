package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv1 = (TextView)findViewById(R.id.result);

        Intent myIntent = getIntent();
        int intValue = myIntent.getIntExtra("intVariableName",0);
        tv1.setText("You Scored " + String.valueOf(intValue));

    }
}
