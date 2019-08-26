package com.example.ostadrate.pages;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ostadrate.R;
import com.squareup.picasso.Picasso;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView image = findViewById(R.id.image);
        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);
        ImageView image3 = findViewById(R.id.image3);
        ImageView image4 = findViewById(R.id.image4);
        ImageView image5 = findViewById(R.id.image5);
        ImageView image6 = findViewById(R.id.image6);
        ImageView image7 = findViewById(R.id.image7);
        ImageView image8 = findViewById(R.id.image8);

        Picasso.get().load(R.drawable.giphy2).into(image1);
        Picasso.get().load(R.drawable.gophy2).into(image2);
        Picasso.get().load(R.drawable.giphy2).into(image3);
        Picasso.get().load(R.drawable.gophy2).into(image4);
        Picasso.get().load(R.drawable.giphy2).into(image5);
        Picasso.get().load(R.drawable.gophy2).into(image6);
        Picasso.get().load(R.drawable.giphy2).into(image6);
        Picasso.get().load(R.drawable.gophy2).into(image7);
        Picasso.get().load(R.drawable.giphy2).into(image8);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, RateTeacherActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}
