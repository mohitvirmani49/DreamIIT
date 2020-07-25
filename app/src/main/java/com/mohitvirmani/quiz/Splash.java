package com.mohitvirmani.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        GifImageView gifImageView = (GifImageView) findViewById(R.id.gif);


        try {
            InputStream inputStream = getAssets().open("aa2.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        } catch (IOException ex) {

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Splash.this.startActivity(new Intent(Splash.this, Login.class));
                Splash.this.finish();
            }
        }, 1800);

    }

}

