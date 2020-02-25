package com.example.diabetescare;

import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class EnterSugarLevels extends AppCompatActivity {
    SaveSugarLevels myDb;
    EditText et;

    Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_sugar_levels);
        et = findViewById(R.id.readings);
        et.setFilters(new InputFilter[]{new InputFilterMinMax("0", "30")});
        btnAddData = findViewById(R.id.savereadings);


        btnAddData.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                int EnterReadings = Integer.parseInt(et.getText().toString());
                //String EnterReadings = et.getText().toString();
                if (EnterReadings == 0) {
                    Toast.makeText(getApplicationContext(), "Please Read the Measurements on the Device and enter them", Toast.LENGTH_LONG).show();

                } else {
                    // myDb.insertreadings(EnterReadings);
                    Toast.makeText(getApplicationContext(), "Saved Successfully ", Toast.LENGTH_LONG).show();
                    et.setText("");
                    finish();
                }


            }
        });

    }

    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

//        myDb.close();
    }

    public void savesugarlevel(View view) {
    }
}
