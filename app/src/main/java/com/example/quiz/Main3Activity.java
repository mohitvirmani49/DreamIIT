package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Main3Activity extends AppCompatActivity {
    TextView tv1;
    LottieAnimationView pass, fail, succ, loose;
    RelativeLayout my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv1 = (TextView) findViewById(R.id.result);
        pass = (LottieAnimationView) findViewById(R.id.my_progress);
        fail = (LottieAnimationView) findViewById(R.id.my_progress2);
        my = (RelativeLayout) findViewById(R.id.my);
        succ = (LottieAnimationView) findViewById(R.id.pass);
        loose = (LottieAnimationView) findViewById(R.id.fail);

        Intent myIntent = getIntent();
        int intValue = myIntent.getIntExtra("intVariableName", 0);
        int percent = intValue / 40;
        if (intValue < 4) {
            pass.setVisibility(View.INVISIBLE);
            my.setBackgroundResource(R.color.red);
            succ.setVisibility(View.INVISIBLE);

        } else {
            fail.setVisibility(View.INVISIBLE);
            my.setBackgroundResource(R.color.main_page);
            loose.setVisibility(View.INVISIBLE);
        }
        int val = percent * 100;
        tv1.setText(String.valueOf((100 * intValue) / 40) + "%");

    }
}
