package com.mohitvirmani.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class MathsResultPractice extends AppCompatActivity {

    private Button tv1;
    private LottieAnimationView pass;
    private RelativeLayout my;
    private ImageButton back;
    private Button check, score1, high;
    private Button detail;
    private TextView name;
    private CircularImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_result_practice);


        tv1 = (Button) findViewById(R.id.score);
        pass = (LottieAnimationView) findViewById(R.id.pass);
        name = (TextView) findViewById(R.id.name);
        pic = (CircularImageView) findViewById(R.id.photo);
        my = (RelativeLayout) findViewById(R.id.my);
        score1 = (Button) findViewById(R.id.score1);
        high = (Button) findViewById(R.id.high);
        back = (ImageButton) findViewById(R.id.back);
        check = (Button) findViewById(R.id.check);
        detail = (Button) findViewById(R.id.detail);

        Intent myIntent = getIntent();
        int intValue = myIntent.getIntExtra("intVariableName", 0);
        int percent = intValue / 40;
        if (intValue < 16) {
            pass.setVisibility(View.INVISIBLE);
            my.setBackgroundResource(R.color.red);

        } else {

            my.setBackgroundResource(R.color.main_page);
            pic.setVisibility(View.INVISIBLE);

        }
        int val = percent * 100;
        tv1.setText("Score:\n" + intValue + " / 40"
        );

        score1.setText("Percent:\n" + String.valueOf((100 * intValue) / 40) + "%");
        if (intValue >= 32) {
            high.setText("Grade:\n" + "A+");
        } else if (intValue >= 26 && intValue < 32) {
            high.setText("Grade:\n" + "A");
        } else if (intValue >= 20 && intValue < 26) {
            high.setText("Grade:\n" + "B+");
        } else if (intValue >= 14 && intValue < 20) {
            high.setText("Grade:\n" + "B");
        } else if (intValue >= 10 && intValue < 14) {
            high.setText("Grade:\n" + "C");
        } else if (intValue < 10) {
            high.setText("Grade:\n" + "D");
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathsResultPractice.this, MainPage.class);
                startActivity(intent);
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name.setText(user.getDisplayName());
        Picasso.get().load(user.getPhotoUrl()).into(pic);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainPage.class));
    }
}