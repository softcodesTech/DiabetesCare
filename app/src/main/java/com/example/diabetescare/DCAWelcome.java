package com.example.diabetescare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class DCAWelcome extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcawelcome);

        img = findViewById(R.id.img);
        img.animate().alpha(4000).setDuration(0);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(DCAWelcome.this,DCALogin.class);
                startActivity(dsp);
                finish();
            }
        },4000);
    }
}