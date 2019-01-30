package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.Discvdet;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class DiscvdetDS {
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG="swadeshi";
	
	
	public DiscvdetDS (Context context){
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}
	
	@SuppressWarnings("static-access")
	public int createOrUpdateDiscvdet(ArrayList<Discvdet>  list) {
		
		int count =0;
		
		if(dB == null){
			open();
		}else if(!dB.isOpen()){
			open();
		}
		Cursor cursor = null;
		
	try{
		
		for (Discvdet discvdet : list) {
		
			ContentValues values = new ContentValues();
				
			values.put(dbHelper.FDISCVDET_REF_NO, discvdet.getFDISCVDET_REF_NO());
			values.put(dbHelper.FDISCVDET_VALUE_F, discvdet.getFDISCVDET_VALUE_F());
			values.put(dbHelper.FDISCVDET_VALUE_T, discvdet.getFDISCVDET_VALUE_T());
			values.put(dbHelper.FDISCVDET_DISPER, discvdet.getFDISCVDET_DISPER());
			values.put(dbHelper.FDISCVDET_DIS_AMT, discvdet.getFDISCVDET_DIS_AMT());
			
			count = (int) dB.insert(dbHelper.TABLE_FDISCVDET, null, values);

			}
		}catch (Exception e) {
		
			Log.v("Exception", e.toString());
	
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
			
			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FDISCVDET, null);
			count =cursor.getCount();
			if(count>0){
				int success = dB.delete(dbHelper.TABLE_FDISCVDET, null, null);
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
