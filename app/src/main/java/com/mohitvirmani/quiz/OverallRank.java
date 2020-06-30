package com.mohitvirmani.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

public class OverallRank extends AppCompatActivity {
    private int[] layouts = {R.layout.level1, R.layout.level2, R.layout.level3, R.layout.level4, R.layout.level5};
    private MPagerAdapter mPagerAdapter;
    private ViewPager viewPager;
    private ImageButton back;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_rank);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        back = (ImageButton) findViewById(R.id.back);

        mPagerAdapter = new MPagerAdapter(layouts, this);
        viewPager.setAdapter(mPagerAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == 5) {
                    currentPage = 0;

                }
                viewPager.setCurrentItem(currentPage++, true);

            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OverallRank.this, Main13Activity.class));
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(OverallRank.this, Main13Activity.class));
    }
}