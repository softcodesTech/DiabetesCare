package com.example.diabetescare;

import androidx.appcompat.app.AlertDialog;
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
        View card2 = findViewById(R.id.bloodlevels);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.card1:
                Intent intent = new Intent(PatientCenter.this, FindDoctor.class);
                startActivity(intent);

                break;
            case R.id.bloodlevels:
                Intent sugar = new Intent(PatientCenter.this, EnterSugarLevels.class);
                startActivity(sugar);

        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
