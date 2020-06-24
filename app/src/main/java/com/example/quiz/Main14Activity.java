package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codemybrainsout.ratingdialog.RatingDialog;
import com.example.quiz.notification.Token;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

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

        cardView_fulltest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main14Activity.this, FullTest.class);
                startActivity(intent);
            }
        });

//        updateToken(FirebaseInstanceId.getInstance().getToken());


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
                        intent.putExtra(Intent.EXTRA_TEXT, "Check out this app! Best Student JEE Prep companion\n link of playstore");
                        startActivity(Intent.createChooser(intent, "Invite Friends"));
                        return true;


                    case R.id.rate_us:
                        System.out.println("Hello");

                        final RatingDialog ratingDialog = new RatingDialog.Builder(Main14Activity.this)
                                .threshold(3)
                                .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                                    @Override
                                    public void onFormSubmitted(String feedback) {
                                        Toast.makeText(Main14Activity.this, "Thanks for your feedback", Toast.LENGTH_SHORT).show();

                                    }
                                }).build();

                        ratingDialog.show();

                        return true;


                    case R.id.contact_dev:

                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"contact.dreamiit@gmail.com"});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                        Intent chooser = Intent.createChooser(emailIntent, "Contact Developer");
                        if (emailIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(chooser);

                        } else {
                            Toast.makeText(Main14Activity.this, "Something went Goofy", Toast.LENGTH_SHORT).show();

                        }


                    default:
                        return false;

                }

            }
        });
    }

//    @Override
//    protected void onResume() {
//        status();
//        super.onResume();
//    }

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

    private void updateToken(String token) {
        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token mToken = new Token(token);
        reference.child(u.getUid()).setValue(mToken);

    }

    private void status() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String mUid = firebaseUser.getUid();

        SharedPreferences sp = getSharedPreferences("SP_USER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Current_USERID", mUid);
        editor.apply();
    }


}