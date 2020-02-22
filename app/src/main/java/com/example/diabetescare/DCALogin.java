package com.example.diabetescare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DCALogin extends AppCompatActivity {
    Button btnSignIn;
    LoginDataBaseAdapter loginDataBaseAdapter;
    EditText editTextUserName,editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcalogin);
        // get the Refferences of views
        editTextUserName=(EditText)findViewById(R.id.username);
         editTextPassword=(EditText)findViewById(R.id.password);
        btnSignIn=(Button)findViewById(R.id.login);
        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
// Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// get The User name and Password
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                // checking for validated fields
                if(userName.equals("")||password.equals("")){
                    Toast.makeText(DCALogin.this,"Enter Username and Password",Toast.LENGTH_LONG).show();
                    return;
                }
// fetch the Password from database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

// check if the Stored password matches with Password entered by user
                if(password.equals(storedPassword))
                {
                    Toast.makeText(DCALogin.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    editTextUserName.setText("");
                    editTextPassword.setText("");
                    Intent intent = new Intent(DCALogin.this, PatientCenter.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(DCALogin.this, "Incorrect Username & Password", Toast.LENGTH_LONG).show();
                    editTextPassword.setText("");
                    editTextUserName.setText("");
                    editTextUserName.requestFocus();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
// Close The Database
        loginDataBaseAdapter.close();
    }

    public void createaccount(View view) {
        Intent dsp = new Intent(DCALogin.this,DCACreateAccount.class);
        startActivity(dsp);
    }

//    public void loginn(View view) {
//        Intent dsp = new Intent(DCALogin.this,PatientCenter.class);
//        startActivity(dsp);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
