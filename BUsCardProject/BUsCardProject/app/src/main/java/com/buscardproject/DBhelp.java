package com.buscardproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBhelp extends SQLiteOpenHelper {

	Context context;
	public static final String databasename= "Buspass.db";

	public DBhelp(Context context) {
		super(context, databasename, null, 12);
		// TODO Auto-generated constructor stub
		
		this.context=context;
		if(!checkDataBase()){
		createDatabase();
		}
	}

	private void createDatabase() {
		// TODO Auto-generated method stub
		try {
			boolean exists = (new File("data/data/"+context.getPackageName()+"/databases")).exists();
            if (!exists) {
                new File("data/data/"+context.getPackageName()+"/databases").mkdirs();
            }
			InputStream in= context.getAssets().open(databasename);
			byte b[]=new byte[in.available()];
			in.read(b);
			OutputStream out=new FileOutputStream("data/data/"+context.getPackageName()+"/databases/"+databasename);
					
			out.write(b);
			in.close();
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			Toast.makeText(context, "There is an Error During Database Creation", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public boolean checkDataBase(){

	    SQLiteDatabase checkDB = null;

	    try{
	        String myPath = "data/data/"+context.getPackageName()+"/databases/"+databasename;
	        checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);

	    }catch(SQLiteException e){

	    }
	    if(checkDB != null){
	        checkDB.close();
	    }
	    return checkDB != null ? true : false;
	}
}
