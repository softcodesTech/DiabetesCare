package com.example.diabetescare;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diabetescare.Resturant.FindResturant;

public class PatientCenter extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiredcard);

        View card1 = findViewById(R.id.card1);
        View card2 = findViewById(R.id.bloodlevels);
        View card3 = findViewById(R.id.dietplan);
        View card4 = findViewById(R.id.exercisplan);
        View card5 = findViewById(R.id.restuarant);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);


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
                break;
            case R.id.dietplan:
                Intent diet = new Intent(PatientCenter.this, DietPlan.class);
                startActivity(diet);
                break;
            case R.id.exercisplan:
                Intent ex = new Intent(PatientCenter.this, ExercisePlan.class);
                startActivity(ex);
                break;
            case R.id.restuarant:
                Intent resturants = new Intent(PatientCenter.this, FindResturant.class);
                startActivity(resturants);
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Press Again to Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        // DCALogin.super.onBackPressed();
                        Intent dsp = new Intent(PatientCenter.this, DCALogin.class);
                        startActivity(dsp);
                        PatientCenter.super.onBackPressed();
                        finish();
                    }
                }).create().show();
    }
}
