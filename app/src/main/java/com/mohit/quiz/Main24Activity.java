package com.mohit.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main24Activity extends AppCompatActivity {
    private Button phy;
    private Button chem;
    private Button maths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main24);

        phy = (Button) findViewById(R.id.phy_dialog);
        chem = (Button) findViewById(R.id.chem_dialog);
        maths = (Button) findViewById(R.id.maths_dialog);

        phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main24Activity.this, ImagesActivity.class);

                startActivity(intent);

            }
        });
        chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main24Activity.this, ImagesActivity.class);

                startActivity(intent);


            }
        });
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main24Activity.this, ImagesActivity.class);

                startActivity(intent);

            }
        });
    }
}