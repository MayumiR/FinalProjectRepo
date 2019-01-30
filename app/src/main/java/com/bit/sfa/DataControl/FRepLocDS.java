package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.FRepLoc;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class FRepLocDS {
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "kandanafd";

	public FRepLocDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}
	
	@SuppressWarnings("static-access")
	public int createOrUpdateRepLoc(ArrayList<FRepLoc> list) {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		
		Cursor cursor = null;
		
		try {

			
			for (FRepLoc reploc : list) {
				ContentValues values = new ContentValues();

				values.put(dbHelper.FREPLOC_REPCODE, reploc.getFREPLOC_REPCODE());
				values.put(dbHelper.FREPLOC_LOCCODE, reploc.getFREPLOC_LOCODE());
				values.put(dbHelper.FREPLOC_COSTCODE, reploc.getFREPLOC_COSTODE());
				

				count = (int) dB.insert(dbHelper.TABLE_FREPLOC, null, values);
				
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
	
	public int deleteAll() {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {

			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FREPLOC, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FREPLOC, null, null);
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
}