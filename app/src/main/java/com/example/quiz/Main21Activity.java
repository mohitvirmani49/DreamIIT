package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class Main21Activity extends AppCompatActivity {
    private ImageButton back_button;
    private RecyclerView recyclerView;
    private EditText comment;
    private ImageButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);

        back_button = (ImageButton) findViewById(R.id.comments_back);
        recyclerView = (RecyclerView) findViewById(R.id.comment_recycler);
        comment = (EditText) findViewById(R.id.main_comment);
        send = (ImageButton) findViewById(R.id.comment_send);
    }
}