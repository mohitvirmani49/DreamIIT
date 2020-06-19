package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

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
import com.squareup.picasso.Picasso;

public class Main13Activity extends AppCompatActivity {
    private TextView name1, name2, ranks;
    private ImageView imageView;
    private ImageButton back;
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
                Toast.makeText(Main13Activity.this,"Sign out successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Main13Activity.this, Main12Activity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Main13Activity.this, Main14Activity.class));
    }
}
