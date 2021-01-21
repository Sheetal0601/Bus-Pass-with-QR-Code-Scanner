package com.buscardproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class AllUserActivity extends AppCompatActivity {

    DBhelp help;
    SQLiteDatabase db;
    private List<UserList> actorsLists;
    private RecyclerView _recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityalluser);
        getSupportActionBar().setTitle("User List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        help = new DBhelp(this);
        db = help.getReadableDatabase();
        actorsLists = new ArrayList<>();
        _recyclerView = (RecyclerView)findViewById(R.id._recyclerView);

        cartDataShow();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void cartDataShow(){
        try{
            actorsLists.clear();
            String dataQuery = "select Sno,name,mobileno,email,address,city,state,pincode,qrcode from tbl_user";
            Cursor cursor = db.rawQuery(dataQuery, null);
            if (cursor.getCount() > 0) {
                int i = 0;
                while (cursor.moveToNext()) {
                    UserList actorsList = new UserList(
                            cursor.getString(0)
                            ,cursor.getString(1)
                            ,cursor.getString(2)
                            ,cursor.getString(3)
                            ,cursor.getString(4)
                            ,cursor.getString(5)
                            ,cursor.getString(6)
                            ,cursor.getString(7)
                            ,cursor.getString(8));
                    i++;
                    actorsLists.add(actorsList);
                }

                UserListAdapter whyGPAdapter = new UserListAdapter(AllUserActivity.this, actorsLists);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManagerWrapper(AllUserActivity.this, LinearLayoutManager.VERTICAL, false);
                _recyclerView.setLayoutManager(mLayoutManager);
                _recyclerView.setHasFixedSize(true);
                _recyclerView.setItemAnimator(null);
                _recyclerView.setAdapter(whyGPAdapter);
            }else {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}