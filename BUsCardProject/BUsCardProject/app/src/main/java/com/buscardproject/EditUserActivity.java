package com.buscardproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

public class EditUserActivity extends AppCompatActivity {

    private TextInputEditText ed_name,ed_mobile,ed_email,ed_address,ed_city,ed_state,ed_pincode;
    private Button btn_reg;
    DBhelp help;
    SQLiteDatabase db;
    String sno,qrcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);
        setTitle("Update Profile");
        help = new DBhelp(this);
        db = help.getReadableDatabase();
        ed_name = findViewById(R.id.ed_name);
        ed_mobile = findViewById(R.id.ed_mobile);
        ed_email = findViewById(R.id.ed_email);
        ed_address = findViewById(R.id.ed_address);
        ed_city = findViewById(R.id.ed_city);
        ed_state = findViewById(R.id.ed_state);
        ed_pincode = findViewById(R.id.ed_pincode);
        btn_reg = findViewById(R.id.btn_reg);


        sno = getIntent().getStringExtra("sno");
        if(sno != null && !sno.isEmpty()){
            cartDataShow(sno);
        }else{
            onBackPressed();
        }

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed_name.getText().toString().trim();
                String mobileno = ed_mobile.getText().toString().trim();
                String email = ed_email.getText().toString().trim();
                String address = ed_address.getText().toString().trim();
                String city = ed_city.getText().toString().trim();
                String state = ed_state.getText().toString().trim();
                String pincode = ed_pincode.getText().toString().trim();

                if(name != null && !name.isEmpty()){
                    if(mobileno != null && !mobileno.isEmpty()){
                        if(email != null && !email.isEmpty()){
                            if(address != null && !address.isEmpty()){
                                if(city != null && !city.isEmpty()){
                                    if(state != null && !state.isEmpty()){
                                        if(pincode != null && !pincode.isEmpty()){
                                            dataInserat(name,mobileno,email,address
                                                    ,city,state,pincode,qrcode);
                                        }else{
                                            Toast.makeText(getApplicationContext(),"This Field Can't be Empty !",Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        Toast.makeText(getApplicationContext(),"This Field Can't be Empty !",Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(),"This Field Can't be Empty !",Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),"This Field Can't be Empty !",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"This Field Can't be Empty !",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"This Field Can't be Empty !",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"This Field Can't be Empty !",Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    private void cartDataShow(String sno){
        try{
            String dataQuery = "select Sno,name,mobileno,email,address,city,state,pincode,qrcode from tbl_user WHERE Sno = '"+sno+"'";
            Cursor cursor = db.rawQuery(dataQuery, null);
            if (cursor.getCount() > 0) {
                String Sno = cursor.getString(0);
                qrcode = cursor.getString(8);
                ed_name.setText(cursor.getString(1));
                ed_mobile.setText(cursor.getString(2));
                ed_email.setText(cursor.getString(3));
                ed_address.setText(cursor.getString(4));
                ed_city.setText(cursor.getString(5));
                ed_state.setText(cursor.getString(6));
                ed_pincode.setText(cursor.getString(7));
            }else {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void dataInserat(String name,String mobileno,String email,String address
            ,String city,String state,String pincode,String qrcode){
            SQLiteDatabase readableDatabase = new DBhelp(EditUserActivity.this).getReadableDatabase();
            Cursor rawQuery = readableDatabase.rawQuery("select * from tbl_user", null);
            ContentValues contentValues;
            if (rawQuery.getCount() > 0) {
                contentValues = new ContentValues();
                contentValues.put("name"	,name);
                contentValues.put("mobileno",mobileno);
                contentValues.put("email",email);
                contentValues.put("address",address);
                contentValues.put("city",city);
                contentValues.put("state",state);
                contentValues.put("pincode",pincode);
                contentValues.put("qrcode",qrcode);
                readableDatabase.insert("tbl_user", null, contentValues);
                Toast.makeText(getApplicationContext(),"Submit successfully.",Toast.LENGTH_LONG).show();
            } else {
                contentValues = new ContentValues();
                contentValues.put("name"	,name);
                contentValues.put("mobileno",mobileno);
                contentValues.put("email",email);
                contentValues.put("address",address);
                contentValues.put("city",city);
                contentValues.put("state",state);
                contentValues.put("pincode",pincode);
                contentValues.put("qrcode",qrcode);

                readableDatabase.insert("tbl_user", null, contentValues);
                Toast.makeText(getApplicationContext(),"Submit successfully.",Toast.LENGTH_LONG).show();
            }
            rawQuery.close();

    }
}