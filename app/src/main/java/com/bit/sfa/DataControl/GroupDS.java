package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.Group;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class GroupDS {

	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "swadeshi";

	public GroupDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}

	@SuppressWarnings("static-access")
	public int createOrUpdateGroup(ArrayList<Group> list) {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		Cursor cursor_ini = null;

		try {

			cursor_ini = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FGROUP, null);

			for (Group group : list) {
				ContentValues values = new ContentValues();

				values.put(dbHelper.FGROUP_ADD_DATE, group.getFGROUP_ADD_DATE());
				values.put(dbHelper.FGROUP_ADD_MACH, group.getFGROUP_ADD_MACH());
				values.put(dbHelper.FGROUP_ADD_USER, group.getFGROUP_ADD_USER());
				values.put(dbHelper.FGROUP_CODE, group.getFGROUP_CODE());
				values.put(dbHelper.FGROUP_NAME, group.getFGROUP_NAME());
				values.put(dbHelper.FGROUP_RECORDID, group.getFGROUP_RECORDID());

				if (cursor_ini.moveToFirst()) {
					String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FGROUP + " WHERE " + dbHelper.FGROUP_CODE + "='" + group.getFGROUP_CODE() + "'";
					cursor = dB.rawQuery(selectQuery, null);

					if (cursor.moveToFirst()) {
						count = (int) dB.update(dbHelper.TABLE_FGROUP, values, dbHelper.FGROUP_CODE + "='" + group.getFGROUP_CODE() + "'", null);
					} else {
						count = (int) dB.insert(dbHelper.TABLE_FGROUP, null, values);
					}

				} else {
					count = (int) dB.insert(dbHelper.TABLE_FGROUP, null, values);
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
			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FGROUP, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FGROUP, null, null);
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
