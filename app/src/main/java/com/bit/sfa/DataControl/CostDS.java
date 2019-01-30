package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.Cost;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class CostDS {
	
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG="kandanafd";

	public CostDS (Context context){
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}
	
	@SuppressWarnings("static-access")
	public int createOrUpdateFCost(ArrayList<Cost>  list) {
		int count =0;		
		if(dB == null){
			open();
		}else if(!dB.isOpen()){
			open();
		}
		Cursor cursor = null;
		Cursor cursor_ini = null;
	try{

		cursor_ini = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FCOST, null);
		
		for (Cost cost : list) {
			
		ContentValues values = new ContentValues();
		values.put(dbHelper.FCOST_CODE,  cost.getFCOST_COST_CODE());
		values.put(dbHelper.FCOST_NAME,  cost.getFCOST_COST_NAME());
								
		if (cursor_ini.moveToFirst()) {
			String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FCOST + " WHERE " + dbHelper.FCOST_CODE + "='" + cost.getFCOST_COST_CODE() + "'";
			cursor = dB.rawQuery(selectQuery, null);

			if (cursor.moveToFirst()) {
				count = (int) dB.update(dbHelper.TABLE_FCOST, values, dbHelper.FCOST_CODE + "='" + cost.getFCOST_COST_CODE() +  "'", null);
			} else {
				count = (int) dB.insert(dbHelper.TABLE_FCOST, null, values);
			}

		} else {
			count = (int) dB.insert(dbHelper.TABLE_FCOST, null, values);
		}
	
		}
		}finally {  
			if (cursor !=null) {
				cursor.close();
			}
			
			if (cursor_ini !=null) {
				cursor_ini.close();
			}
			dB.close();
		}
		return count;
				
	}
	
	@SuppressWarnings("static-access")
	public int deleteAll() {
		int count = 0;
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {
			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FCOST, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FCOST, null, null);
				Log.v("Success", success + "");
			}
		} catch (Exception e) {
			Log.v(TAG + " Exception", e.toString());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}
		return count;
	}
	
	public ArrayList<Cost> getAllCostCenters() {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Cost> list = new ArrayList<Cost>();

		String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_FCOST;
		Cursor cursor = dB.rawQuery(selectQuery, null);
		try {
			while (cursor.moveToNext()) {
				Cost cost = new Cost();
				cost.setFCOST_COST_CODE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FCOST_CODE)));
				cost.setFCOST_COST_NAME(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FCOST_NAME)));
				list.add(cost);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}

		return list;
	}
	
	public String getCostCenterName(String Costcode) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String costcenter = "";

		String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_FCOST + " WHERE "+DatabaseHelper.FCOST_CODE + "='" + Costcode + "'";
		Cursor cursor = dB.rawQuery(selectQuery, null);
		try {
			while (cursor.moveToNext()) {
				
				costcenter = (cursor.getString(cursor.getColumnIndex(DatabaseHelper.FCOST_NAME)));
							
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}

		return costcenter;
	}
}
