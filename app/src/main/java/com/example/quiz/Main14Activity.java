package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main14Activity extends AppCompatActivity {
    CardView cardView_phy, cardView_chem, cardView_maths, cardView_fulltest;
    RelativeLayout doubt;
    BottomNavigationView menu;
    private int menuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);
        cardView_phy = (CardView) findViewById(R.id.phy);
        cardView_chem = (CardView) findViewById(R.id.chem);
        cardView_maths = (CardView) findViewById(R.id.mathematics);
        cardView_fulltest = (CardView) findViewById(R.id.full_test_papers);
        doubt = (RelativeLayout) findViewById(R.id.doubts);
        menu = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        menu.setSelectedItemId(R.id.home);
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.notification9:
                        startActivity(new Intent(getApplicationContext(), ImagesActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile9:
                        startActivity(new Intent(getApplicationContext(), Main13Activity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Main14Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });


        cardView_phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main14Activity.this, Main26Activity.class);
                startActivity(intent);

            }
        });


        cardView_chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main14Activity.this, Main27Activity.class);
                startActivity(intent);


            }
        });

        cardView_maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main14Activity.this, Main28Activity.class);
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