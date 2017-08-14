package com.example.lenovo.retail;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {
    EditText et_username, et_password;
    TextView sign;
    Button login;
    private NestedScrollView nestedScrollView;

    private DataAcceshandler mDBHelper ;
       DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        et_username = (EditText) findViewById(R.id.luName);
        et_password = (EditText) findViewById(R.id.luPass);
//        databaseHelper = new DatabaseHelper(this);
        sign = (TextView) findViewById(R.id.lsignup);
        login = (Button) findViewById(R.id.loginb);
        login.setOnClickListener(this);
        sign.setOnClickListener(this);

        // create a instance of SQLite Database
        databaseHelper=new DatabaseHelper(this);
        try {
            databaseHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.loginb:
                 login();
                break;

            case R.id.lsignup:
                Intent signIntent = new Intent(getApplicationContext(), RegistrationScreen.class);
                startActivity(signIntent);
                break;

        }


    }

    private void login() {

// get The User name and Password
        String userName=et_username.getText().toString();
        String password=et_password.getText().toString();

// fetch the Password form database for respective user name
        String storedPassword=databaseHelper.getSinlgeEntry(userName);

// check if the Stored password matches with Password entered by user
        if(password.equals(storedPassword))
        {
            Toast.makeText(LoginScreen.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
            Intent login = new Intent(this, HomeScreen.class);
            startActivity(login);

        }
        else
        {
            Toast.makeText(LoginScreen.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
// Close The Database

        databaseHelper.close();
    }

}



