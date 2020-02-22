package com.example.diabetescare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DCACreateAccount extends AppCompatActivity {
    EditText editTextfName,editTextPassword,editTextSname, editTextEmail,editTextPhone,editTextUsername,editTextconfirmpassword;
    Button btnCreateAccount;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcacreate_account);

// get Instance of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

// Get Refferences of Views
        editTextfName=(EditText)findViewById(R.id.fname);
        editTextSname=(EditText)findViewById(R.id.sname);
        editTextEmail=(EditText)findViewById(R.id.email);
        editTextPhone=(EditText)findViewById(R.id.phone);
        editTextPassword=(EditText)findViewById(R.id.password);
        editTextconfirmpassword=(EditText)findViewById(R.id.confirmpassword);
        editTextUsername =(EditText)findViewById(R.id.username);
        btnCreateAccount=(Button)findViewById(R.id.account_creation);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// TODO Auto-generated method stub
                String firstName = editTextfName.getText().toString();
                String SName= editTextSname.getText().toString();
                String Email= editTextEmail.getText().toString();
                String Phone=editTextPhone.getText().toString();
                String userName=editTextUsername.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword =editTextconfirmpassword.getText().toString();

// check if any of the fields are vaccant
                if(userName.equals("")||password.equals("")||firstName.equals("")||SName.equals("")||Email.equals("")||Phone.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
                    return;
                }
// check if both password matches
                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
// Save the Data in Database
                    loginDataBaseAdapter.insertEntry(firstName,SName,Email,Phone,userName, password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    editTextfName.setText("");
                    editTextSname.setText("");
                    editTextEmail.setText("");
                    editTextPhone.setText("");
                    editTextPassword.setText("");
                    editTextconfirmpassword.setText("");
                    editTextUsername.setText("");
                    Intent intent=new Intent(DCACreateAccount.this,DCALogin.class);
                    startActivity(intent);
                }
            }
        });
    }

//    public void createdacc(View view) {
//        Intent dsp = new Intent(DCACreateAccount.this,PatientCenter.class);
//        startActivity(dsp);
//    }
    protected void onDestroy() {
    // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
