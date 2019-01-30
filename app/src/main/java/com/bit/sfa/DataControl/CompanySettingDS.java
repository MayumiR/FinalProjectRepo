package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.CompanySetting;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class CompanySettingDS {
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "KFD";
	
	public CompanySettingDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}

	@SuppressWarnings("static-access")
	public int createOrUpdateFCompanySetting(ArrayList<CompanySetting> list) {
		int count = 0;
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
	
		
		try {

			dB.beginTransaction();
			
			String sql = "Insert or Replace into " + dbHelper.TABLE_FCOMPANYSETTING + " (" + dbHelper.FCOMPANYSETTING_SETTINGS_CODE + ", " + dbHelper.FCOMPANYSETTING_GRP + ", " 
			+ dbHelper.FCOMPANYSETTING_LOCATION_CHAR + ", " + dbHelper.FCOMPANYSETTING_CHAR_VAL + ", " + dbHelper.FCOMPANYSETTING_NUM_VAL + ", " 
					+ dbHelper.FCOMPANYSETTING_REMARKS + ", " + dbHelper.FCOMPANYSETTING_TYPE + ", " + dbHelper.FCOMPANYSETTING_COMPANY_CODE 
					+ ") values(?,?,?,?,?,?,?,?)";
			
			SQLiteStatement insert = dB.compileStatement(sql);
			
			for (int i = 0; i < list.size(); i++) {

				CompanySetting item = list.get(i);
				
				insert.bindString(1, item.getFCOMPANYSETTING_SETTINGS_CODE());
				insert.bindString(2, item.getFCOMPANYSETTING_GRP());
				insert.bindString(3, item.getFCOMPANYSETTING_LOCATION_CHAR());
				insert.bindString(4, item.getFCOMPANYSETTING_CHAR_VAL());
				insert.bindString(5, item.getFCOMPANYSETTING_NUM_VAL());
				insert.bindString(6, item.getFCOMPANYSETTING_REMARKS());
				insert.bindString(7, item.getFCOMPANYSETTING_TYPE());
				insert.bindString(8, item.getFCOMPANYSETTING_COMPANY_CODE());
				insert.execute();
				
				
			}
			dB.setTransactionSuccessful();
			Log.w(TAG, "Done");
			
		} catch (Exception e) {
			Log.w("XML:", e);
		}

		finally {
			dB.endTransaction();

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

			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FCOMPANYSETTING, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FCOMPANYSETTING, null, null);
				Log.v("Success", success + "");
			}
		} catch (Exception e) {

			Log.v(" Exception", e.toString());

		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}

		return count;

	}
}
