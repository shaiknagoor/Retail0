package com.example.lenovo.retail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationScreen extends AppCompatActivity implements View.OnClickListener {

    EditText userName_et,Email_et1,Mobile_et1,Password_et2,conform_password;
    Button signup_btn;

    private String userName,email,password,mobileno,ConformPassword;
    private User savedUserData = null;
    private boolean isUpdateData = false;
    private DataAcceshandler mDBHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        userName_et=(EditText)findViewById(R.id.userName_et);
        Email_et1=(EditText)findViewById(R.id.Email_et1);
        Mobile_et1=(EditText)findViewById(R.id.Mobile_et1);
        Password_et2=(EditText)findViewById(R.id.Password_et2);
        conform_password=(EditText)findViewById(R.id.conform_password);
        signup_btn=(Button)findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(this);

        savedUserData = (User) DataManager.getInstance().getDataFromManager(DataManager.USER_DETAILS);


        if (savedUserData != null ) {
            isUpdateData = true;

        } else {

            savedUserData = new User();
        }
    }

    @Override
    public void onClick(View v) {


        if (validate()) {
            saveUserData();

            DataSavingHelper.saveUserDetails(this, new ApplicationThread.OnComplete<String>() {
                @Override
                public void execute(boolean success, String result, String msg) {
                    if (success) {
                        Log.v("@@@@Registration","data success");
                        Toast.makeText(RegistrationScreen.this,"Data Inserted Sucessfully",Toast.LENGTH_LONG).show();
                        finish();
                  /*  Intent newuser = new Intent(RegistrationScreen.this,HomeScreen.class);
                    startActivity(newuser);*/
                    } else {
                        Toast.makeText(getApplicationContext(),"Data not Inserted ",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }


    }

    private void saveUserData() {
        savedUserData.setUName(userName);
        savedUserData.setMobileNo(mobileno);
        savedUserData.setUEmail(email);
        savedUserData.setPassword(password);
        savedUserData.setConformpassword(ConformPassword);
        savedUserData.setIsActive(1);

        savedUserData.setCreatedByUserId(Integer.parseInt(Constants.USER_ID));
        savedUserData.setCreatedDate(CommonUtills.getcurrentDateTime(Constants.DATE_FORMAT_DDMMYYYY_HHMMSS));
        savedUserData.setUpdatedByUserId(Integer.parseInt(Constants.USER_ID));
        savedUserData.setUpdatedDate(CommonUtills.getcurrentDateTime(Constants.DATE_FORMAT_DDMMYYYY_HHMMSS));
        DataManager.getInstance().addData(DataManager.USER_DETAILS, savedUserData);



    }

    private boolean validate() {

         userName=userName_et.getText().toString();
         password=Password_et2.getText().toString();
         mobileno = Mobile_et1.getText().toString().trim();
         email = Email_et1.getText().toString().trim();

        ConformPassword=conform_password.getText().toString();
        if (TextUtils.isEmpty(userName)){
            Toast.makeText(getApplicationContext(),"Please enter User Name",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mobileno)){
            Toast.makeText(getApplicationContext(),"Please enter User Mobile Number",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please enter User Email",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Please enter Password",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(ConformPassword)){
            Toast.makeText(getApplicationContext(),"Please enter  Conform Password",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password.equals(ConformPassword))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
