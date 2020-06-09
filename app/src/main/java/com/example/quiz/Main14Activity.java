package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class Main14Activity extends AppCompatActivity {
    CardView cardView_phy, cardView_chem, cardView_maths, cardView_fulltest;
    Button doubt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);
        cardView_phy = (CardView) findViewById(R.id.phy);
        cardView_chem = (CardView) findViewById(R.id.chem);
        cardView_maths = (CardView) findViewById(R.id.mathematics);
        cardView_fulltest = (CardView) findViewById(R.id.full_test_papers);
        doubt = (Button) findViewById(R.id.doubts);

        cardView_phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main14Activity.this, Main22Activity.class);
                startActivity(intent);

            }
        });
        doubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main14Activity.this, ImagesActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_1, popup.getMenu());
        popup.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
