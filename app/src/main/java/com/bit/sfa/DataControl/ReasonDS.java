package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.Reason;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class ReasonDS {

	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "swadeshi";

	public ReasonDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}

	/*
	 * insert code
	 */
	@SuppressWarnings("static-access")
	public int createOrUpdateFReason(ArrayList<Reason> list) {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		Cursor cursor_ini = null;
		try {

			cursor_ini = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FREASON, null);

			for (Reason reason : list) {
				ContentValues values = new ContentValues();

				values.put(dbHelper.FREASON_ADD_DATE, reason.getFREASON_ADD_DATE());
				values.put(dbHelper.FREASON_ADD_MACH, reason.getFREASON_ADD_MACH());
				values.put(dbHelper.FREASON_ADD_USER, reason.getFREASON_ADD_USER());
				values.put(dbHelper.FREASON_CODE, reason.getFREASON_CODE());
				values.put(dbHelper.FREASON_NAME, reason.getFREASON_NAME());
				values.put(dbHelper.FREASON_REATCODE, reason.getFREASON_REATCODE());
				values.put(dbHelper.FREASON_RECORD_ID, reason.getFREASON_RECORD_ID());
				values.put(dbHelper.FREASON_TYPE, reason.getFREASON_TYPE());

				if (cursor_ini.moveToFirst()) {
					String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREASON + " WHERE " + dbHelper.FREASON_CODE + "='" + reason.getFREASON_CODE() + "'";
					cursor = dB.rawQuery(selectQuery, null);

					if (cursor.moveToFirst()) {
						count = (int) dB.update(dbHelper.TABLE_FREASON, values, dbHelper.FREASON_CODE + "='" + reason.getFREASON_CODE() + "'", null);
					} else {
						count = (int) dB.insert(dbHelper.TABLE_FREASON, null, values);
					}

				} else {
					count = (int) dB.insert(dbHelper.TABLE_FREASON, null, values);
				}

			}
		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());

		} finally {
			if (cursor != null) {
				cursor.close();
			}
			
			if (cursor_ini != null) {
				cursor_ini.close();
			}
			dB.close();
		}
		return count;

	}

	/*
	 * delete code
	 */
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

			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FREASON, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FREASON, null, null);
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

	public ArrayList<String> getReasonName() {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<String> reason = new ArrayList<String>();

		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREASON + " WHERE " + dbHelper.FREASON_REATCODE + "='RT01' OR reatcode='RT02'";

		Cursor cursor = null;
		cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			reason.add(cursor.getString(cursor.getColumnIndex(dbHelper.FREASON_NAME)));

		}

		return reason;
	}

	public String getReaCodeByName(String name) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREASON + " WHERE " + dbHelper.FREASON_NAME + "='" + name + "'";

		Cursor cursor = null;
		cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {
			return cursor.getString(cursor.getColumnIndex(dbHelper.FREASON_CODE));
		}

		return "";
	}

	public String getReasonByReaCode(String reaCode) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREASON + " WHERE " + dbHelper.FREASON_CODE + "='" + reaCode + "'";

		Cursor cursor = null;
		cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {
			return cursor.getString(cursor.getColumnIndex(dbHelper.FREASON_NAME));
		}

		return "";
	}

	public ArrayList<Reason> getAllReasons(String RTcode) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Reason> list = new ArrayList<Reason>();

		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREASON + " WHERE " + dbHelper.FREASON_REATCODE + "='" + RTcode + "'";

		Cursor cursor = dB.rawQuery(selectQuery, null);
		while (cursor.moveToNext()) {

			Reason reason = new Reason();

			reason.setFREASON_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FREASON_CODE)));
			reason.setFREASON_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FREASON_NAME)));

			list.add(reason);

		}

		return list;
	}

	public ArrayList<Reason> getDebDetails(String searchword) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Reason> Itemname = new ArrayList<Reason>();

		String selectQuery = "select * from fReason where ReaTcode='RT02' AND ReaCode LIKE '%" + searchword + "%' OR ReaName LIKE '%" + searchword + "%' ";

		Cursor cursor = null;
		cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {
			Reason items = new Reason();

			items.setFREASON_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FREASON_NAME)));
			items.setFREASON_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FREASON_CODE)));
			Itemname.add(items);
		}

		return Itemname;
	}

	@SuppressWarnings("static-access")
	public String getReaNameByCode(String code) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREASON + " WHERE " + dbHelper.FREASON_CODE + "='" + code + "'";

		Cursor cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			return cursor.getString(cursor.getColumnIndex(dbHelper.FREASON_NAME));

		}

		return "";
	}

}
