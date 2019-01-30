package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.RouteDet;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class RouteDetDS {

	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG="swadeshi";
	
	
	public RouteDetDS (Context context){
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}
	
	@SuppressWarnings("static-access")
	public int createOrUpdateFRouteDet(ArrayList<RouteDet>  list) {
		int count =0;		
		if(dB == null){
			open();
		}else if(!dB.isOpen()){
			open();
		}
		
	try{
		
		dB.beginTransaction();

		String sql = "Insert or Replace into " + dbHelper.TABLE_FROUTEDET + " (" + dbHelper.FROUTEDET_DEB_CODE + ", " + dbHelper.FROUTEDET_ROUTE_CODE + ") values(?,?)";
		
		SQLiteStatement insert = dB.compileStatement(sql);
		
		for (RouteDet routeDet : list) {
			
			insert.bindString(1, routeDet.getFROUTEDET_DEB_CODE());
			insert.bindString(2, routeDet.getFROUTEDET_ROUTE_CODE());
			insert.execute();
			
			count = 1;
	
		}
		
		dB.setTransactionSuccessful();
		
		}finally {  
			dB.endTransaction();
			dB.close();
		}
		return count;
	}

	@SuppressWarnings("static-access")
	public int deleteAll(){
		
		int count =0;
		
		if(dB == null){
			open();
		}else if(!dB.isOpen()){
			open();
		}
		Cursor cursor = null;
		try{
			
			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FROUTEDET, null);
			count =cursor.getCount();
			if(count>0){
				int success = dB.delete(dbHelper.TABLE_FROUTEDET, null, null);
				Log.v("Success", success+"");
			}
		}catch (Exception e){

			Log.v(TAG+" Exception", e.toString());
			
		}finally{  
			if (cursor !=null) {
				cursor.close();
			}
			dB.close();
		}
		
		return count;
		
	}
}
