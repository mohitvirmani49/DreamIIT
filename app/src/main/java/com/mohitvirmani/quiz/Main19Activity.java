package com.mohitvirmani.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

public class Main19Activity extends AppCompatActivity {
    ImageButton backButton;
    TextView userName0;
    CircularImageView circularImageView;
    TextView posts, followers, following;
    TextView userName1;
    TextView userRank;
    Button followUser, messageUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main19);

        backButton = (ImageButton) findViewById(R.id.back_button);
        userName0 = (TextView) findViewById(R.id.profile_user_name);
        circularImageView = (CircularImageView) findViewById(R.id.profile_other_user_pic);
        posts = (TextView) findViewById(R.id.posts);
        followers = (TextView) findViewById(R.id.followers);
        following = (TextView) findViewById(R.id.following);
        userName1 = (TextView) findViewById(R.id.p_user_nm);
        userRank = (TextView) findViewById(R.id.user_rank);
        followUser = (Button) findViewById(R.id.btn_follow_user);
        messageUser = (Button) findViewById(R.id.message_user);


    }
}
