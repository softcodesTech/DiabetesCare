package com.example.diabetescare;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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
import java.util.List;


public class EnterSugarLevels extends AppCompatActivity {
    String enteredsugars;
    EditText et;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_sugar_levels);
        et = findViewById(R.id.readings);
        et.setFilters(new InputFilter[]{new InputFilterMinMax("0", "30")});
        btnAddData = findViewById(R.id.savereadings);
        checkInternetConenction();

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                enteredsugars = et.getText().toString();
                //validations
                if (TextUtils.isEmpty(enteredsugars)) {
                    et.setError("Please Read the Measurements on the Device and enter them");
                    et.requestFocus();
                    return;
                }

                new Insert().execute();

            }
        });

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Back to Control Center")
                .setMessage("Are you sure you want to leave?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        // DCALogin.super.onBackPressed();
                        Intent dsp = new Intent(EnterSugarLevels.this, PatientCenter.class);
                        startActivity(dsp);
                        EnterSugarLevels.super.onBackPressed();
                        finish();
                    }
                }).create().show();
    }

    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

//        myDb.close();
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

    @SuppressLint("StaticFieldLeak")
    public class Insert extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(EnterSugarLevels.this);
            dialog.setMessage("saving sugar levels, please wait");
            dialog.setTitle("Connecting... ");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {


            try {

                List<NameValuePair> insert = new ArrayList<NameValuePair>();
                insert.add(new BasicNameValuePair("level", enteredsugars));


                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(
                        "http://softcodes.tech/diabetes/Insert_sugar_levels.php"); // link to connect to database
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

            AlertDialog.Builder ac = new AlertDialog.Builder(EnterSugarLevels.this);
            ac.setTitle("Sugar Levels");
            ac.setMessage("Your Blood sugar levels have been saved Successfully");
            ac.setCancelable(true);

            ac.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            et.setText("");

                        }
                    });

            AlertDialog alert = ac.create();
            alert.show();
        }

    }
}
