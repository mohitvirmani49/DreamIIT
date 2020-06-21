package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;


public class SliderPage extends AppCompatActivity {

    private int[] layouts = {R.layout.try_3, R.layout.try_l2, R.layout.try_liquid1};
    private MPagerAdapter mPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = new MPagerAdapter(layouts, this);
        viewPager.setAdapter(mPagerAdapter);

    }
}