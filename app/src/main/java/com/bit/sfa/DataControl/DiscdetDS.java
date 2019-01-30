package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.Discdet;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DiscdetDS {
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "swadeshi";

	public DiscdetDS(Context context) {

		this.context = context;
		dbHelper = new DatabaseHelper(context);

	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}

	@SuppressWarnings("static-access")
	public int createOrUpdateDiscdet(ArrayList<Discdet> list) {
		int count = 0;
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;

		try {
			for (Discdet discdet : list) {

				ContentValues values = new ContentValues();

				values.put(dbHelper.FDISCDET_REF_NO, discdet.getFDISCDET_REF_NO());
				values.put(dbHelper.FDISCDET_ITEM_CODE, discdet.getFDISCDET_ITEM_CODE());
				values.put(dbHelper.FDISCDET_RECORD_ID, discdet.getFDISCDET_RECORD_ID());
				values.put(dbHelper.FDISCHED_TIEMSTAMP_COLUMN, discdet.getFDISCHED_TIEMSTAMP_COLUMN());

				count = (int) dB.insert(dbHelper.TABLE_FDISCDET, null, values);

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

	/*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

	/* Get assort item list for each item code */
	public List<String> getAssortByItemCode(String itemCode) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<String> list = new ArrayList<String>();

		String selectQuery = "select * from fdiscdet where refno in (select RefNo from fdiscdet where itemcode='" + itemCode + "')";

		String s = null;
		Cursor cursor = dB.rawQuery(selectQuery, null);

		try {
			while (cursor.moveToNext()) {

				list.add(cursor.getString(cursor.getColumnIndex(dbHelper.FDISCDET_ITEM_CODE)));

			}
		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());

		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}

		return list;

	}

	/*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

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

			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FDISCDET, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FDISCDET, null, null);
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
