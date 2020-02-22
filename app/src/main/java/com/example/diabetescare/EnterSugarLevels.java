package com.example.diabetescare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

public class EnterSugarLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_sugar_levels);
        EditText et = (EditText) findViewById(R.id.readings);
        et.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "30")});
    }

    public void savesugarlevel(View view) {
    }
}
