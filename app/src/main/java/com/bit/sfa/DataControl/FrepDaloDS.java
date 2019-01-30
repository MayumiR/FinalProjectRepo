package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.FrepDalo;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class FrepDaloDS {
	
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "KFD";
	
	public FrepDaloDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}
	public int createOrUpdateFrepDalo(ArrayList<FrepDalo> frepDalos) {
		int serverdbID = 0;
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		try {
			dB.beginTransaction();
			String sql = "Insert or Replace into " + dbHelper.TABLE_FREPDALO + " (" + dbHelper.FREPDALO_CODE + ", "
									+ dbHelper.FREPDALO_REPcODE + ") values(?,?)";
			SQLiteStatement insert = dB.compileStatement(sql);

			for (FrepDalo db : frepDalos) {
				insert.bindString(1, db.getFDEBTOR_CODE());
				insert.bindString(2, db.getFDEBTOR_REP_CODE());
				
				insert.execute();

				serverdbID = 1;
				
			}
		
			
			dB.setTransactionSuccessful();
		
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			dB.endTransaction();
			dB.close();
		}
		return serverdbID;
		
	}
	
public int deleteAll(){
		
		int count =0;
		
		if(dB == null){
			open();
		}else if(!dB.isOpen()){
			open();
		}
		Cursor cursor = null;
		try{
			
			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FREPDALO, null);
			count =cursor.getCount();
			if(count>0){
				int success = dB.delete(dbHelper.TABLE_FREPDALO, null, null);
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


public boolean getCheckAllowDebtor(String debcode,String repcode) {

	if (dB == null) {
		open();
	} else if (!dB.isOpen()) {
		open();
	}
	
	Cursor cursor = null;
	try {
	String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREPDALO + " WHERE " + dbHelper.FREPDALO_CODE + "='" + debcode + "' and "+ dbHelper.FREPDALO_REPcODE + "='" + repcode + "'GROUP BY DebCode ";

	
	cursor = dB.rawQuery(selectQuery, null);

	while (cursor.moveToNext()) {
		String AllowChange = cursor.getString(cursor.getColumnIndex(dbHelper.FREPDALO_CODE));
		
		if(AllowChange.length()>0){
			return true;
		}else{
			return false;
		}
		

	}
	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		if (cursor != null) {
			cursor.close();
		}
		dB.close();
	}
	return false;

}
	
	

}
