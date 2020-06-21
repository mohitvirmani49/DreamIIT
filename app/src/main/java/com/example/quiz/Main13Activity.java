package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Main13Activity extends AppCompatActivity {
    private TextView name1, name2, ranks;
    private ImageView imageView;
    private ImageButton back;
    private TextView userRank, answerRank, rankLeft;
    private Button notification, message, invite, settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        name1 = (TextView) findViewById(R.id.profile_user_name);
        name2 = (TextView) findViewById(R.id.p_user_nm);
        imageView = (ImageView) findViewById(R.id.profile_other_user_pic);
        back = (ImageButton) findViewById(R.id.back_button);
        notification = (Button) findViewById(R.id.notify);
        message = (Button) findViewById(R.id.msg);
        invite = (Button) findViewById(R.id.invite_frnds);
        settings = (Button) findViewById(R.id.settings);
        ranks = (TextView) findViewById(R.id.rank);
        userRank = (TextView) findViewById(R.id.user_rank);
        answerRank = (TextView) findViewById(R.id.answerrank);
        rankLeft = (TextView) findViewById(R.id.rank_ans);

        final FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        String name = user.getDisplayName();
        name1.setText(name);
        name2.setText(name);
        Picasso.get().load(user.getPhotoUrl()).into(imageView);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main13Activity.this, Main14Activity.class);
                startActivity(intent);
            }
        });

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "DreamIIT");
                intent.putExtra(Intent.EXTRA_TEXT, "Check out this app! Best Student JEE Prep companion\n link of playstore");
                startActivity(Intent.createChooser(intent, "Invite Friends"));

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Main13Activity.this, "Sign out successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Main13Activity.this, Main12Activity.class));
            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user1 = mAuth.getCurrentUser();

        Query query = FirebaseDatabase.getInstance().getReference(user1.getUid()).child("level1");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = (int) dataSnapshot.getChildrenCount();
                if (value > 0 && value <= 2) {
                    userRank.setText("  Level 1");

                } else if (value > 2 && value <= 4) {
                    userRank.setText("  Level 2");
                } else if (value > 4 && value <= 6) {
                    userRank.setText("   Level 3");
                } else if (value > 6 && value <= 8) {
                    userRank.setText("  Level 4");
                } else if (value > 8) {
                    userRank.setText("  Level 5");

                } else {
                    userRank.setText("  Level 0");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if (user1.getUid().equals(R.string.mohit) || user1.getUid().equals(R.string.smriti)) {
            answerRank.setText("Moderator");
            ranks.setText("Congratulations you are an esteemed member of DreamIIT");
            rankLeft.setText("");

        }
        Query query2 = FirebaseDatabase.getInstance().getReference("rankans").child(user.getUid());
        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int number = (int) dataSnapshot.getChildrenCount();

                System.out.println("My total answers upto now is" + number);
                if (number >= 0 && number <= 5) {
                    answerRank.setText("Toddler");

                } else if (number > 5 && number <= 15) {
                    answerRank.setText("Rookie");
                } else if (number > 15 && number < 30) {
                    answerRank.setText("Rising Star");

                } else if (number >= 30 && number < 50) {
                    answerRank.setText("Achiever");
                } else if (number >= 50 && number < 80) {
                    answerRank.setText("Expert");
                } else if (number >= 80 && number < 120) {
                    answerRank.setText("Genius");
                } else if (number >= 120) {
                    answerRank.setText("Legend");
                }

                if (number >= 0 && number <= 5) {
                    ranks.setText("Rookie");
                    rankLeft.setText("Just " + (6 - number) + " quality answers away");

                } else if (number > 5 && number <= 15) {
                    ranks.setText("Rising Star");
                    rankLeft.setText("Just " + (16 - number) + " quality answers away");
                } else if (number > 15 && number < 30) {
                    ranks.setText("Achiever");
                    rankLeft.setText("Just " + (30 - number) + " quality answers away");

                } else if (number >= 30 && number < 50) {
                    ranks.setText("Expert");
                    rankLeft.setText("Just " + (50 - number) + " quality answers away");
                } else if (number >= 50 && number < 80) {
                    ranks.setText("Genius");
                    rankLeft.setText("Just " + (80 - number) + " quality answers away");
                } else if (number >= 80 && number < 120) {
                    ranks.setText("Legend");
                    rankLeft.setText("Just " + (120 - number) + " quality answers away");
                } else if (number >= 120) {
                    ranks.setText("Congrats, you have become DreamIIT Master!");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Main13Activity.this, Main14Activity.class));
    }
}
