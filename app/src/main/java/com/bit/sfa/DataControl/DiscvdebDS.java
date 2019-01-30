package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.Discvdeb;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class DiscvdebDS {
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;

	
	
	public DiscvdebDS (Context context){
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}
	
	@SuppressWarnings("static-access")
	public int createOrUpdateDiscvdeb(ArrayList<Discvdeb>  list) {
		
		int count =0;
		
		if(dB == null){
			open();
		}else if(!dB.isOpen()){
			open();
		}
		Cursor cursor = null;
		
	try{
		
		for (Discvdeb discvdeb : list) {
		
			ContentValues values = new ContentValues();
				
			values.put(dbHelper.FDISCVDEB_REF_NO, discvdeb.getFDISCVDEB_REF_NO());
			values.put(dbHelper.FDISCVDED_DEB_CODE, discvdeb.getFDISCVDED_DEB_CODE());
						
			count = (int) dB.insert(dbHelper.TABLE_FDISCVDEB, null, values);

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
			
			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FDISCVDEB, null);
			count =cursor.getCount();
			if(count>0){
				int success = dB.delete(dbHelper.TABLE_FDISCVDEB, null, null);
				Log.v("Success", success+"");
			}
		}catch (Exception e){

			Log.v("Exception", e.toString());
			
		}finally{  
			if (cursor !=null) {
				cursor.close();
			}
			dB.close();
		}
		
		return count;
		
	}
}
