package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class Main13Activity extends AppCompatActivity {
    TextView name1, name2, ranks;
    ImageView imageView;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        name1 = (TextView) findViewById(R.id.profile_other_user_pic);
        name2 = (TextView) findViewById(R.id.p_user_nm);
        imageView = (ImageView) findViewById(R.id.profile_other_user_pic);
        back = (ImageButton) findViewById(R.id.back_button);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        String name = account.getDisplayName();
        name1.setText(name);
        name2.setText(name);
        Picasso.get().load(account.getPhotoUrl()).into(imageView);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main13Activity.this, Main14Activity.class);
                startActivity(intent);
            }
        });

    }

}
