package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Main17Activity extends AppCompatActivity {
    ImageButton imageButton;
    Button answer;
    TextView question, name_person;
    CircularImageView circularImageView_photo;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);

        imageButton = (ImageButton) findViewById(R.id.question_image);
        answer = (Button) findViewById(R.id.answer_question);
        question = (TextView) findViewById(R.id.question_text);
        name_person = (TextView) findViewById(R.id.answer_pg_name);
        circularImageView_photo = (CircularImageView) findViewById(R.id.answer_pg_image);


        Intent intent = getIntent();
        final String ques = intent.getStringExtra("question");
        String userN = intent.getStringExtra("username");
        final String img_qs = intent.getStringExtra("image_q");
        String user_pic = intent.getStringExtra("userpic");
//        Uri uri = intent.get
        if (img_qs.isEmpty()) {
            imageButton.requestLayout();
            imageButton.getLayoutParams().height = 0;
            imageButton.getLayoutParams().width = 0;
//            holder.imageView.setImageBitmap(null);
            imageButton.setVisibility(View.INVISIBLE);
            question.setText(ques);
            name_person.setText(userN);
            Picasso.get().load(user_pic).into(circularImageView_photo);

        } else {
            imageButton.setVisibility(View.VISIBLE);
            imageButton.requestLayout();
            imageButton.getLayoutParams().height = 380;

            question.setText(ques);
            name_person.setText(userN);
            Picasso.get().load(img_qs).fit().centerCrop().into(imageButton);
            Picasso.get().load(user_pic).into(circularImageView_photo);
        }
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main17Activity.this,Main18Activity.class);
                intent.putExtra("qs",ques);
                intent.putExtra("ig",img_qs);
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
