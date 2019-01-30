package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.Discvhed;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class DiscvhedDS {
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG="swadeshi";
	
	
	public DiscvhedDS (Context context){
		
		this.context = context;
		dbHelper = new DatabaseHelper(context);
		
	}
	
	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}
	
	@SuppressWarnings("static-access")
	public int createOrUpdateDiscvhed(ArrayList<Discvhed>  list) {
		int count =0;		
		if(dB == null){
			open();
		}else if(!dB.isOpen()){
			open();
		}
		Cursor cursor = null;
		
	try{
		for (Discvhed discvhed : list) {
			
		ContentValues values = new ContentValues();
	
		values.put(dbHelper.FDISCVHED_REF_NO, discvhed.getFDISCVHED_REF_NO());
		values.put(dbHelper.FDISCVHED_TXN_DATE, discvhed.getFDISCVHED_TXN_DATE());
		values.put(dbHelper.FDISCVHED_DISC_DESC, discvhed.getFDISCVHED_DISC_DESC());
		values.put(dbHelper.FDISCVHED_PRIORITY, discvhed.getFDISCVHED_PRIORITY());
		values.put(dbHelper.FDISCVHED_DIS_TYPE, discvhed.getFDISCVHED_DIS_TYPE());
		values.put(dbHelper.FDISCVHED_V_DATE_F, discvhed.getFDISCVHED_V_DATE_F());
		values.put(dbHelper.FDISCVHED_V_DATE_T, discvhed.getFDISCVHED_V_DATE_T());
		values.put(dbHelper.FDISCVHED_REMARKS, discvhed.getFDISCVHED_REMARKS());
		
		count = (int) dB.insert(dbHelper.TABLE_FDISCVHED, null, values);
	
		}
		}finally {  
			if (cursor !=null) {
				cursor.close();
			}
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
			
			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FDISCVHED, null);
			count =cursor.getCount();
			if(count>0){
				int success = dB.delete(dbHelper.TABLE_FDISCVHED, null, null);
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
