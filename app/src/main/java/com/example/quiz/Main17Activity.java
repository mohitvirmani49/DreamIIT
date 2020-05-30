package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.prefs.PreferenceChangeEvent;

public class Main17Activity extends AppCompatActivity {
    private ImageButton question_image;
    private TextView question_text;
    private CircularImageView question_user_pic;
    private TextView question_user_name;
    private Button answer_question;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);

        question_image = (ImageButton) findViewById(R.id.question_image);
        question_text = (TextView) findViewById(R.id.question_text);
        question_user_pic = (CircularImageView) findViewById(R.id.answer_pg_image);
        question_user_name = (TextView) findViewById(R.id.answer_pg_name);
        answer_question = (Button) findViewById(R.id.answer_question);
        mRecyclerView = (RecyclerView) findViewById(R.id.answer_recycler);

        SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (result.getString("image_q", "0").isEmpty()) {

            question_image.requestLayout();
            question_image.getLayoutParams().height = 0;
            question_image.getLayoutParams().width = 0;
            question_image.setVisibility(View.INVISIBLE);


        } else {
            question_image.requestLayout();
            question_image.getLayoutParams().height = 380;

            Picasso.get().load(result.getString("image_q", "0")).fit().centerCrop().into(question_image);


        }
        String txt = result.getString("question", "1");
        question_text.setText(txt);
        question_user_name.setText(result.getString("username", "2"));
        Picasso.get().load(result.getString("userpic", "3")).fit().centerCrop().into(question_user_pic);


        answer_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main17Activity.this,Main18Activity.class);
                startActivity(intent);
            }
        });


    }

    public void showPopup1(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_2, popup.getMenu());
        popup.show();
    }
}