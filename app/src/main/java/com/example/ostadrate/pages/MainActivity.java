package com.example.ostadrate.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ostadrate.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Toast.makeText(this, "Must go to the list after 3 sec (and show animation and logo, etc)", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, RateTeacherActivity.class));
    }
}
