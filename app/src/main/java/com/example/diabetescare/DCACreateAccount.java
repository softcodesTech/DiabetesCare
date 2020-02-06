package com.example.diabetescare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DCACreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcacreate_account);
    }

    public void createdacc(View view) {
        Intent dsp = new Intent(DCACreateAccount.this,PatientCenter.class);
        startActivity(dsp);
    }
}
