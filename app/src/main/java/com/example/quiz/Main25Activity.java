package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jem.liquidswipe.LiquidSwipeViewPager;

public class Main25Activity extends AppCompatActivity {
    private LiquidSwipeViewPager mPager;
    private int[] layouts ={R.layout.try_3,R.layout.try_l2,R.layout.try_liquid1};
    private MPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main25);

        mPager = (LiquidSwipeViewPager)findViewById(R.id.viewpager);
        mPagerAdapter = new MPagerAdapter(layouts,this);
        mPager.setAdapter(mPagerAdapter);

    }
}