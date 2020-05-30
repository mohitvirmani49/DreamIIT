package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Main18Activity extends AppCompatActivity {
    EditText main_ans;
    ImageButton prev_img_qs;
    Button submit_ans;
    TextView prev_ans_txt;
    Button attach_ans_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);

        main_ans = (EditText)findViewById(R.id.main_answers);
        prev_img_qs = (ImageButton)findViewById(R.id.answer_are_question_image);
        submit_ans = (Button)findViewById(R.id.submit_ans);
        prev_ans_txt = (TextView)findViewById(R.id.answer_are_text);
        attach_ans_img = (Button)findViewById(R.id.attach_answer);

        Intent intent = getIntent();
        String qs = intent.getStringExtra("qs");
        String ig = intent.getStringExtra("ig");



        prev_ans_txt.setText(qs.trim());
        prev_img_qs.setVisibility(View.VISIBLE);
        Picasso.get().load(ig).fit().centerCrop().into(prev_img_qs);
    }
}