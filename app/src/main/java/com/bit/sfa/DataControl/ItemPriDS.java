package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.ItemPri;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class ItemPriDS {

	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "swadeshi";

	public ItemPriDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}

	@SuppressWarnings("static-access")
	public int createOrUpdateItemPri(ArrayList<ItemPri> list) {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		try {

			dB.beginTransaction();

			String sql = "Insert or Replace into " + dbHelper.TABLE_FITEMPRI + " (" + dbHelper.FITEMPRI_ITEM_CODE + ", " + dbHelper.FITEMPRI_PRIL_CODE + ", " + dbHelper.FITEMPRI_PRICE + ", " + dbHelper.FITEMPRI_TXN_MACH + ", " + dbHelper.FITEMPRI_TXN_USER + ", " + dbHelper.FITEMPRI_ADD_MACH + ", " + dbHelper.FITEMPRI_ADD_USER + ", " + dbHelper.FITEMPRI_COST_CODE + ") values(?,?,?,?,?,?,?,?)";

			SQLiteStatement insert = dB.compileStatement(sql);

			for (ItemPri pri : list) {

				insert.bindString(1, pri.getFITEMPRI_ITEM_CODE());
				insert.bindString(2, pri.getFITEMPRI_PRIL_CODE());
				insert.bindString(3, pri.getFITEMPRI_PRICE());
				insert.bindString(4, pri.getFITEMPRI_TXN_MACH());
				insert.bindString(5, pri.getFITEMPRI_TXN_USER());
				insert.bindString(6, pri.getFITEMPRI_ADD_MACH());
				insert.bindString(7, pri.getFITEMPRI_ADD_USER());
				insert.bindString(8, pri.getFITEMPRI_COST_CODE());
				insert.execute();

				count = 1;
			}

			dB.setTransactionSuccessful();
		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());

		} finally {
			dB.endTransaction();
			dB.close();
		}
		return count;

	}

	public int deleteAllItemPri() {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {

			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FITEMPRI, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FITEMPRI, null, null);
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
 
	public String getProductPriceByCode(String code, String CostCode) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String itempri = "0.00";
		
		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FITEMPRI + " WHERE " + dbHelper.FITEMPRI_ITEM_CODE + "='" + code + "' AND "
		+dbHelper.FITEMPRI_COST_CODE + "='"+CostCode+"'";

		Cursor cursor = null;
		try {
		cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			itempri = cursor.getString(cursor.getColumnIndex(dbHelper.FITEMPRI_PRICE));
			return itempri;

		}
		} catch (Exception e) {
			itempri = "0.00";
			e.printStackTrace();
			return itempri;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}
		return itempri;

	}

	public String getPrilCodeByItemCode(String code) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {
			String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FITEMPRI + " WHERE " + dbHelper.FITEMPRI_ITEM_CODE + "='" + code + "'";

			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {

				return cursor.getString(cursor.getColumnIndex(dbHelper.FITEMPRI_PRIL_CODE));

			}

		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}

		return "";

	}
}
