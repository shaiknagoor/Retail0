package com.example.lenovo.retail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static com.example.lenovo.retail.HomeScreen.LOG_TAG;

public class SplashScreen extends AppCompatActivity {
    public static final String LOG_TAG = SplashScreen.class.getName();


    private DataAcceshandler mDBHelper = null ;
    private DatabaseHelper databaseHelper;

    private String[] PERMISSIONS_REQUIRED = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        mDBHelper = new DataAcceshandler(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !CommonUtills.areAllPermissionsAllowed(this, PERMISSIONS_REQUIRED)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_REQUIRED, CommonUtills.PERMISSION_CODE);
        } else {
            try {
                databaseHelper = DatabaseHelper.getRetailDatabase(this);
                databaseHelper.createDataBase();
            } catch (Exception e) {
                e.getMessage();
            }
            startactivity();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CommonUtills.PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v(LOG_TAG, "permission granted");
                    try {
                        databaseHelper = DatabaseHelper.getRetailDatabase(this);
                        databaseHelper.createDataBase();
                    } catch (Exception e) {
                        Log.e(LOG_TAG, "@@@ Error while getting master data "+e.getMessage());
                    }
//                    startMasterSync();
                    Toast.makeText(getApplicationContext(),"Data is not copied",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    private void startactivity() {

        startActivity(new Intent(SplashScreen.this, LoginScreen.class));
        finish();
          /*  if (success) {
                PrefUtil.putBool(SplashScreen.this, CommonConstants.IS_MASTER_SYNC_SUCCESS, true);

            }*//* else {
                Log.v(LOG_TAG, "@@@ Master sync failed " );
                ApplicationThread.uiPost(LOG_TAG, "data sync message", new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"data saving failed",Toast.LENGTH_LONG).show();
//                        UiUtils.showCustomToastMessage("Data syncing failed", SplashScreen.this, 1);
                        startActivity(new Intent(SplashScreen.this, LoginScreen.class));
                        finish();
                    }
                });
            }*/
        }



}
