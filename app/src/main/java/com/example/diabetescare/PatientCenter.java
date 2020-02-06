package com.example.diabetescare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

public class PatientCenter extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiredcard);

        View card1 = findViewById(R.id.card1);

        card1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.card1:
                Intent intent = new Intent(PatientCenter.this, FindDoctor.class);
                startActivity(intent);

                break;

        }
    }
}
