package com.mohitvirmani.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MathsDetail extends AppCompatActivity {
    private PieChart pie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_detail);

        pie = (PieChart) findViewById(R.id.pie);


        final Intent myIntent = getIntent();
        final int truth = myIntent.getIntExtra("truth", 0);
        final int bluff = myIntent.getIntExtra("bluff", 0);
        final int not = myIntent.getIntExtra("not",0);

        pie.setUsePercentValues(true);
        pie.getDescription().setEnabled(false);
        pie.setExtraOffsets(5, 10, 5, 5);

        pie.setDragDecelerationFrictionCoef(0.95f);
        pie.setDrawHoleEnabled(true);
        pie.setHoleColor(Color.WHITE);
        pie.setTransparentCircleRadius(61);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(truth, "Correct Answers"));
        yValues.add(new PieEntry(bluff, "Wrong Answers"));
        yValues.add(new PieEntry(not, "Not Attempted"));

        PieDataSet dataSet = new PieDataSet(yValues, "Marks");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);


        PieData data = new PieData(dataSet);
        data.setValueTextSize(10);
        data.setValueTextColor(Color.BLACK);
        pie.setData(data);


    }
}