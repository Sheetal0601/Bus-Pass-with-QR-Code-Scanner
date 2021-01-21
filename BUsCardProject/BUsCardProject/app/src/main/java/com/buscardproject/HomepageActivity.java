package com.buscardproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class HomepageActivity extends AppCompatActivity {

    Button btn_userregstion,btn_alluser;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhomepage);
        setTitle("Home Page");
        if (checkPermission()) {

        } else {
            requestPermission();
        }

        btn_userregstion = (Button)findViewById(R.id.btn_userregstion);
        btn_alluser = (Button)findViewById(R.id.btn_alluser);



        btn_userregstion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserRegActivity.class);
                startActivity(intent);
            }
        });

        btn_alluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AllUserActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean checkPermission() {
        int result0 = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_NETWORK_STATE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), INTERNET);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result5 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int result6 = ContextCompat.checkSelfPermission(getApplicationContext(), FOREGROUND_SERVICE);

        return result0 == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
                && result2 == PackageManager.PERMISSION_GRANTED && result3 == PackageManager.PERMISSION_GRANTED
                && result5 == PackageManager.PERMISSION_GRANTED //&& result4 == PackageManager.PERMISSION_GRANTED
                && result6 == PackageManager.PERMISSION_GRANTED ;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_NETWORK_STATE,INTERNET,READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE,
                CAMERA,FOREGROUND_SERVICE}, PERMISSION_REQUEST_CODE); //,READ_CONTACTS,WRITE_CONTACTS,READ_PHONE_STATE

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean p0 = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean p1 = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean p2 = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean p3 = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean p4 = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    boolean p5 = grantResults[5] == PackageManager.PERMISSION_GRANTED;

                    if (p0 && p1 && p2 && p3 && p4 && p5 )
                        Toast.makeText(this,"Permission Granted.", Toast.LENGTH_LONG).show();
                    else {
                        // Toast.makeText(this,"Permission Denied.", Toast.LENGTH_LONG).show();
//                        checkPermission();
//                        requestPermission();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_NETWORK_STATE,INTERNET,READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE,
                                                                    CAMERA,FOREGROUND_SERVICE},
                                                            PERMISSION_REQUEST_CODE); //,READ_CONTACTS,WRITE_CONTACTS,READ_PHONE_STATE
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(HomepageActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}