package com.mohitvirmani.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codemybrainsout.ratingdialog.RatingDialog;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {
    CardView cardView_phy, cardView_chem, cardView_maths, cardView_fulltest;
    RelativeLayout doubt;
    BottomNavigationView menu;
    private int menuId;
    private NotificationManagerCompat notificationManager;
    private TextView intro, intro1;

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
        intro = (TextView) findViewById(R.id.intro);
        intro1 = (TextView) findViewById(R.id.intro1);
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
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainPage.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
        try {



            SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String name = result.getString("name", "0");

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.apply();
            intro.setText("Hello, " + name + " !");

            Shader textShader = new LinearGradient(0, 0, 0, intro.getTextSize(),
                    new int[]{
                            Color.parseColor("#1FD1F9"),
                            Color.parseColor("#B621FE"),

                    }, null, Shader.TileMode.CLAMP);


            Shader textShader1 = new LinearGradient(0, 0, 0, intro1.getTextSize(),
                    new int[]{
                            Color.parseColor("#FF0000"),
                            Color.parseColor("#FF7F00"),
                            Color.parseColor("#00FF00"),
                            Color.parseColor("#0000FF"),
                            Color.parseColor("#2E2B5F"),
                            Color.parseColor("#8B00FF"),


                    }, null, Shader.TileMode.CLAMP);
            intro.getPaint().setShader(textShader);
            intro1.getPaint().setShader(textShader1);
        } catch (Exception e) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                }
            }, 1500);
        }


        cardView_phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, PhysicsCard.class);
                startActivity(intent);

            }
        });


        cardView_chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, ChemistryCard.class);
                startActivity(intent);


            }
        });

        cardView_maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, MathsCard.class);
                startActivity(intent);


            }
        });
        doubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, ImagesActivity.class);
                startActivity(intent);
            }
        });

        cardView_fulltest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, FullTest.class);
                startActivity(intent);
            }
        });

        notificationManager = NotificationManagerCompat.from(this);
        System.out.println("Fatal Error: Web API and Console link are different! Get the console link from Signing in reports!");


    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_1, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.share:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "DreamIIT");
                        intent.putExtra(Intent.EXTRA_TEXT, "Check out this app! Best Student JEE Prep companion\n https://lnkd.in/eesXWpk");
                        startActivity(Intent.createChooser(intent, "Invite Friends"));
                        return true;


                    case R.id.rate_us:

                        final RatingDialog ratingDialog = new RatingDialog.Builder(MainPage.this)
                                .threshold(3)
                                .playstoreUrl("https://play.google.com/store/apps/details?id=com.mohitvirmani.quiz&hl=en&gl=US&fbclid=IwAR3bg3aD86fyGr1yzyHhQ6VD3B_gZsxNqg5L7NKobbqj_G0p4PYTJ1ZNwo0")
                                .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                                    @Override
                                    public void onFormSubmitted(String feedback) {
                                        Toast.makeText(MainPage.this, "Thanks for your feedback", Toast.LENGTH_SHORT).show();

                                    }
                                }).build();

                        ratingDialog.show();

                        return true;


                    case R.id.contact_dev:

                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"contact.mohitvirmani@gmail.com"});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Dream IIT !");
                        Intent chooser = Intent.createChooser(emailIntent, "Contact Developer");
                        if (emailIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(chooser);

                        } else {
                            Toast.makeText(MainPage.this, "Something went Goofy", Toast.LENGTH_SHORT).show();

                        }


                    default:
                        return false;

                }

            }
        });
    }

    private Boolean exit = false;

    @Override
    public void onBackPressed() {
        if (exit) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();

            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
}