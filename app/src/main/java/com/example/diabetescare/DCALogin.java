package com.example.diabetescare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DCALogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcalogin);
    }

    public void createaccount(View view) {
        Intent dsp = new Intent(DCALogin.this,DCACreateAccount.class);
        startActivity(dsp);
    }

    public void loginn(View view) {
        Intent dsp = new Intent(DCALogin.this,Dr.class);
        startActivity(dsp);
    }
}
