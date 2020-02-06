package com.example.diabetescare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MeetDoctor extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_doctor);

        View card1 = findViewById(R.id.makeappointment);

        card1.setOnClickListener(this);
        View card2 = findViewById(R.id.calldoctor);
        card2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.makeappointment:
                Intent intent = new Intent(MeetDoctor.this, DoctorAppointmentLetter.class);
                startActivity(intent);
                break;
            case R.id.calldoctor:
                Intent i = new Intent(MeetDoctor.this, CallDoctor.class);
                startActivity(i);

                break;

        }
    }
}
