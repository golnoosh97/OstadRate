package com.example.ostadrate.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.ostadrate.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView image = findViewById(R.id.image);
        ImageView image1 = findViewById(R.id.image1);
        ImageView image2= findViewById(R.id.image2);
        ImageView image3= findViewById(R.id.image3);
        ImageView image4= findViewById(R.id.image4);


        ImageView image5= findViewById(R.id.image5);
        ImageView image6= findViewById(R.id.image6);
        ImageView image7= findViewById(R.id.image7);
        ImageView image8= findViewById(R.id.image8);


        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(image1);
        Glide.with(this).load(R.raw.giphy).into(imageViewTarget);

        GlideDrawableImageViewTarget imageViewTarget1 = new GlideDrawableImageViewTarget(image2);
        Glide.with(this).load(R.raw.gophy).into(imageViewTarget1);

        GlideDrawableImageViewTarget imageViewTarget2 = new GlideDrawableImageViewTarget(image3);
        Glide.with(this).load(R.raw.giphy2).into(imageViewTarget2);

        GlideDrawableImageViewTarget imageViewTarget3 = new GlideDrawableImageViewTarget(image4);
        Glide.with(this).load(R.raw.gophy2).into(imageViewTarget3);



        GlideDrawableImageViewTarget imageViewTarget4 = new GlideDrawableImageViewTarget(image5);
        Glide.with(this).load(R.raw.gophy2).into(imageViewTarget4);

        GlideDrawableImageViewTarget imageViewTarget5 = new GlideDrawableImageViewTarget(image6);
        Glide.with(this).load(R.raw.giphy2).into(imageViewTarget5);

        GlideDrawableImageViewTarget imageViewTarget6 = new GlideDrawableImageViewTarget(image7);
        Glide.with(this).load(R.raw.gophy2).into(imageViewTarget6);

        GlideDrawableImageViewTarget imageViewTarget7 = new GlideDrawableImageViewTarget(image8);
        Glide.with(this).load(R.raw.giphy2).into(imageViewTarget7);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}
