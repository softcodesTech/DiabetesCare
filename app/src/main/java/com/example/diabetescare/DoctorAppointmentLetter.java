package com.example.diabetescare;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DoctorAppointmentLetter extends AppCompatActivity {
    TimePickerDialog picker;
    DatePickerDialog datepicker;
    //EditText eText, eTextt,appo;
    Button btnGet, btnGett, btnAddAppointment;
    TextView tvw;
    private EditText appo;
    private EditText eText;
    private EditText eTextt;
    // String appointment_message,appointment_time,appointment_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment_letter);
        //tvw=(TextView)findViewById(R.id.textView1);
        eText = findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eTextt = findViewById(R.id.editText2);
        appo = findViewById(R.id.textArea_information);
        eTextt.setInputType(InputType.TYPE_NULL);
        btnAddAppointment = findViewById(R.id.submit_appointment);
        checkInternetConenction();

//        btnAddAppointment.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(DoctorAppointmentLetter.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eText.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        btnGet = findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                System.out.println("I love coding");
            }
        });
        // implementing date picker

        eTextt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(DoctorAppointmentLetter.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eTextt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.show();
            }
        });

    }

    public void submit_myappointment(View view) {
        final String appointment_message = appo.getText().toString();
        final String appointment_time = eText.getText().toString();
        final String appointment_date = eTextt.getText().toString();
        //validations
        if (TextUtils.isEmpty(appointment_message)) {
            appo.setError("Please enter the reason for visiting the doctor");
            appo.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(appointment_time)) {
            eText.setError("Select Time for the appointment");
            eText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(appointment_date)) {
            eText.setError("Select Date for the appointment");
            eText.requestFocus();
            return;
        }
        new DoctorAppointmentLetter.Insert().execute();
    }

    public boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;
        } else if (
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED) {
            new AlertDialog.Builder(this)
                    .setTitle("Connect Network?")
                    .setMessage("Please Connect your phone to Wifi or enable data")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            // DCALogin.super.onBackPressed();
                            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                            startActivity(intent);
                        }
                    }).create().show();
            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();

            return false;
        }
        return true;
    }

    public class Insert extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(DoctorAppointmentLetter.this);
            dialog.setMessage("saving Appointment, please wait");
            dialog.setTitle("Connecting... ");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            final String appointment_message = appo.getText().toString();
            final String appointment_time = eText.getText().toString();
            final String appointment_date = eTextt.getText().toString();

            try {

                List<NameValuePair> insert = new ArrayList<NameValuePair>();
                insert.add(new BasicNameValuePair("appointment_message", appointment_message));
                insert.add(new BasicNameValuePair("appointment_time", appointment_time));
                insert.add(new BasicNameValuePair("appointment_date", appointment_date));

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(
                        "http://softcodes.tech/diabetes/appointments.php"); // link to connect to database
                httpPost.setEntity(new UrlEncodedFormEntity(insert));

                HttpResponse response = httpClient.execute(httpPost);

                HttpEntity entity = response.getEntity();

                return true;


            } catch (IOException e) {
                e.printStackTrace();

            }


            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();

            AlertDialog.Builder ac = new AlertDialog.Builder(DoctorAppointmentLetter.this);
            ac.setTitle("Appointment");
            ac.setMessage("Your Appointment has been saved Successfully");
            ac.setCancelable(true);

            ac.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            appo.setText("");
                            eText.setText("");
                            eTextt.setText("");

                        }
                    });

            AlertDialog alert = ac.create();
            alert.show();
        }

    }
}
