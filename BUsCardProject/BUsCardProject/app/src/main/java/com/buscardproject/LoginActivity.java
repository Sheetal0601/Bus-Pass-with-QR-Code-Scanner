package com.buscardproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends Activity {

    private TextInputEditText ed_password,ed_name;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activitylogin);

        ed_name = findViewById(R.id.ed_name);
        ed_password = findViewById(R.id.ed_password);
        btn_login = findViewById(R.id.btn_login);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed_name.getText().toString().trim();
                String password = ed_password.getText().toString().trim();
                if(name != null && !name.isEmpty()){
                    if(password != null && !password.isEmpty()){
                        if(name.equals(password)){
                            datashow(name);
                        }else{
                            ed_name.setText("");
                            ed_password.setText("");
                            Toast.makeText(getApplicationContext(),"Error:- The old password you have entered is incorrect.",Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Please enter your Password.!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Please enter your name.!",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void datashow(String name){
        SQLiteDatabase readableDatabase = new DBhelp(this).getReadableDatabase();
        Cursor c = readableDatabase.rawQuery("select so,name,pass,status from tbl_admin WHERE name = '"+name+"'", null);
        if(c.moveToFirst()){
           String data = c.getString(c.getColumnIndex("status"));
           if(data.equals("1")){

               SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
               SharedPreferences.Editor editor = pref.edit();
               editor.putString("regId", data);
               editor.commit();
               Intent intent = new Intent(getApplicationContext(),HomepageActivity.class);
               startActivity(intent);
               finish();
           }
            Toast.makeText(getApplicationContext(),"Login successfully..",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Login Fail...",Toast.LENGTH_LONG).show();
        }
    }
}

//"so"	INTEGER,
//        "name"	TEXT,
//        "pass"	TEXT,
//        "status"	TEXT