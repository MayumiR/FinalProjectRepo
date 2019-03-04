package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.Debtor;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class CustomerController {

	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "swadeshi";

	public CustomerController(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}

	@SuppressWarnings("static-access")
	public int createOrUpdateDebtor(ArrayList<Debtor> debtors) {
		int serverdbID = 0;
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		try {

			dB.beginTransaction();

			String sql = "Insert or Replace into " + dbHelper.TABLE_FDEBTOR + " (" + dbHelper.FDEBTOR_CODE + ", "
					+ dbHelper.FDEBTOR_NAME + ", " + dbHelper.FDEBTOR_ADD1 + ", " + dbHelper.FDEBTOR_ADD2 + ", "
					+ dbHelper.FDEBTOR_ADD3 + ", " + dbHelper.FDEBTOR_TELE + ", " + dbHelper.FDEBTOR_MOB + ", "
					+ dbHelper.FDEBTOR_EMAIL + ", " + dbHelper.FDEBTOR_TOWN_CODE + ", " + dbHelper.FDEBTOR_AREA_CODE
					+ ", " + dbHelper.FDEBTOR_DBGR_CODE + ", " + dbHelper.FDEBTOR_STATUS + ", "
					+ dbHelper.FDEBTOR_ADD_USER + ", " + dbHelper.FDEBTOR_ADD_MACH + ", " + dbHelper.FDEBTOR_CRD_PERIOD
					+ ", " + dbHelper.FDEBTOR_CHK_CRD_PRD + ", " + dbHelper.FDEBTOR_CRD_LIMIT + ", "
					+ dbHelper.FDEBTOR_CHK_CRD_LIMIT + ", " + dbHelper.FDEBTOR_REP_CODE + ", "
					+ dbHelper.FDEBTOR_RANK_CODE + ", " + dbHelper.FDEBTOR_TAXREG + ", " + dbHelper.FDEBTOR_PRIL_CODE
					+ ", " + dbHelper.FDEBTOR_ROUTE_CODE + ", " + dbHelper.FDEBTOR_CHK_MUSTFREE + ", "
					+ dbHelper.FDEBTOR_CHK_FREE + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			SQLiteStatement insert = dB.compileStatement(sql);

			for (Debtor debtor : debtors) {

				insert.bindString(1, debtor.getFDEBTOR_CODE());
				insert.bindString(2, debtor.getFDEBTOR_NAME());
				insert.bindString(3, debtor.getFDEBTOR_ADD1());
				insert.bindString(4, debtor.getFDEBTOR_ADD2());
				insert.bindString(5, debtor.getFDEBTOR_ADD3());
				insert.bindString(6, debtor.getFDEBTOR_TELE());
				insert.bindString(7, debtor.getFDEBTOR_MOB());
				insert.bindString(8, debtor.getFDEBTOR_EMAIL());
				insert.bindString(9, debtor.getFDEBTOR_TOWN_CODE());
				insert.bindString(10, debtor.getFDEBTOR_AREA_CODE());
				insert.bindString(11, debtor.getFDEBTOR_DBGR_CODE());
				insert.bindString(12, debtor.getFDEBTOR_STATUS());
				insert.bindString(13, debtor.getFDEBTOR_ADD_USER());
				insert.bindString(14, debtor.getFDEBTOR_ADD_MACH());
				insert.bindString(15, debtor.getFDEBTOR_CRD_PERIOD());
				insert.bindString(16, debtor.getFDEBTOR_CHK_CRD_PRD());
				insert.bindString(17, debtor.getFDEBTOR_CRD_LIMIT());
				insert.bindString(18, debtor.getFDEBTOR_CHK_CRD_LIMIT());
				insert.bindString(19, debtor.getFDEBTOR_REP_CODE());
				insert.bindString(20, debtor.getFDEBTOR_RANK_CODE());
				insert.bindString(21, debtor.getFDEBTOR_TAXREG());
				insert.bindString(22, debtor.getFDEBTOR_PRIL_CODE());
				insert.bindString(23, debtor.getFDEBTOR_ROUTE_CODE());
				insert.bindString(24, debtor.getFDEBTOR_CHK_MUSTFREE());
				insert.bindString(25, debtor.getFDEBTOR_CHK_FREE());
				insert.execute();

				serverdbID = 1;
			}

			dB.setTransactionSuccessful();
			Log.w(TAG, "Done");
		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());

		} finally {
			dB.endTransaction();
			dB.close();
		}
		return serverdbID;

	}

	public int createOrUpdateTempDebtor(ArrayList<Debtor> debtors) {
		int serverdbID = 0;
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		try {

			dB.beginTransaction();

			String sql = "Insert or Replace into " + dbHelper.TABLE_TEMP_FDEBTOR + " (" + dbHelper.FDEBTOR_CODE + ", "
					+ dbHelper.FDEBTOR_NAME + ") values(?,?)";

			SQLiteStatement insert = dB.compileStatement(sql);

			for (Debtor debtor : debtors) {

				insert.bindString(1, debtor.getFDEBTOR_CODE());
				insert.bindString(2, debtor.getFDEBTOR_NAME());

				insert.execute();

				serverdbID = 1;
			}

			dB.setTransactionSuccessful();
			Log.w(TAG, "Done");
		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());

		} finally {
			dB.endTransaction();
			dB.close();
		}
		return serverdbID;

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

			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FDEBTOR, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FDEBTOR, null, null);
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

	public int deleteAllTemp() {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {

			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_TEMP_FDEBTOR, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_TEMP_FDEBTOR, null, null);
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

	public ArrayList<Debtor> getAllCustomers() {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Debtor> list = new ArrayList<Debtor>();
		Cursor cursor = null;
		try {
			String selectQuery = "select * from " + dbHelper.TABLE_FDEBTOR;

			// String selectQuery = "select "+dbHelper.TABLE_FDEBTOR+".*
			// ,"+dbHelper.TABLE_FTOWN+"."+dbHelper.FTOWN_NAME+" from " +
			// dbHelper.TABLE_FDEBTOR +", "+dbHelper.TABLE_FTOWN+" where
			// "+dbHelper.TABLE_FDEBTOR+
			// "."+dbHelper.FDEBTOR_TOWN_CODE+" = "+
			// dbHelper.TABLE_FTOWN+"."+dbHelper.FTOWN_CODE;

			cursor = dB.rawQuery(selectQuery, null);
			while (cursor.moveToNext()) {

				Debtor aDebtor = new Debtor();

				aDebtor.setFDEBTOR_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ID)));
				aDebtor.setFDEBTOR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODE)));
				aDebtor.setFDEBTOR_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME)));
				aDebtor.setFDEBTOR_ADD1(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD1)));
				aDebtor.setFDEBTOR_ADD2(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD2)));
				aDebtor.setFDEBTOR_ADD3(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD3)));
				aDebtor.setFDEBTOR_TELE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TELE)));
				aDebtor.setFDEBTOR_MOB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_MOB)));
				aDebtor.setFDEBTOR_EMAIL(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_EMAIL)));
				aDebtor.setFDEBTOR_CREATEDATE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CREATEDATE)));
				aDebtor.setFDEBTOR_TOWN_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TOWN_CODE)));
				aDebtor.setFDEBTOR_AREA_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_AREA_CODE)));
				aDebtor.setFDEBTOR_DBGR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_DBGR_CODE)));
				aDebtor.setFDEBTOR_STATUS(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_STATUS)));
				aDebtor.setFDEBTOR_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_USER)));
				aDebtor.setFDEBTOR_ADD_DATE_DEB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_DATE_DEB)));
				aDebtor.setFDEBTOR_ADD_MACH(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_MACH)));
				aDebtor.setFDEBTOR_CRD_PERIOD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_PERIOD)));
				aDebtor.setFDEBTOR_CHK_CRD_PRD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_PRD)));
				aDebtor.setFDEBTOR_CRD_LIMIT(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_LIMIT)));
				aDebtor.setFDEBTOR_CHK_CRD_LIMIT(
						cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_LIMIT)));
				aDebtor.setFDEBTOR_REP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_REP_CODE)));
				aDebtor.setFDEBTOR_RANK_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_RANK_CODE)));
				aDebtor.setFDEBTOR_SUMMARY(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_SUMMARY)));

				list.add(aDebtor);

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

	public ArrayList<Debtor> getOutstandingCustomersForReceipt() {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Debtor> list = new ArrayList<Debtor>();
		Cursor cursor = null;
		try {
			String selectQuery = "select * from " + dbHelper.TABLE_FDEBTOR + " where " + dbHelper.FDEBTOR_CODE
					+ " in ( select distinct " + dbHelper.FDEBTOR_CODE + " from " + dbHelper.TABLE_FDDBNOTE + " where "
					+ dbHelper.FDDBNOTE_TOT_BAL + " > 0) ";

			// String selectQuery = "select "+dbHelper.TABLE_FDEBTOR+".*
			// ,"+dbHelper.TABLE_FTOWN+"."+dbHelper.FTOWN_NAME+" from " +
			// dbHelper.TABLE_FDEBTOR +", "+dbHelper.TABLE_FTOWN+" where
			// "+dbHelper.TABLE_FDEBTOR+
			// "."+dbHelper.FDEBTOR_TOWN_CODE+" = "+
			// dbHelper.TABLE_FTOWN+"."+dbHelper.FTOWN_CODE;

			cursor = dB.rawQuery(selectQuery, null);
			while (cursor.moveToNext()) {

				Debtor aDebtor = new Debtor();

				aDebtor.setFDEBTOR_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ID)));
				aDebtor.setFDEBTOR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODE)));
				aDebtor.setFDEBTOR_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME)));
				aDebtor.setFDEBTOR_ADD1(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD1)));
				aDebtor.setFDEBTOR_ADD2(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD2)));
				aDebtor.setFDEBTOR_ADD3(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD3)));
				aDebtor.setFDEBTOR_TELE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TELE)));
				aDebtor.setFDEBTOR_MOB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_MOB)));
				aDebtor.setFDEBTOR_EMAIL(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_EMAIL)));
				aDebtor.setFDEBTOR_CREATEDATE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CREATEDATE)));
				aDebtor.setFDEBTOR_TOWN_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TOWN_CODE)));
				aDebtor.setFDEBTOR_AREA_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_AREA_CODE)));
				aDebtor.setFDEBTOR_DBGR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_DBGR_CODE)));
				aDebtor.setFDEBTOR_STATUS(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_STATUS)));
				aDebtor.setFDEBTOR_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_USER)));
				aDebtor.setFDEBTOR_ADD_DATE_DEB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_DATE_DEB)));
				aDebtor.setFDEBTOR_ADD_MACH(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_MACH)));
				aDebtor.setFDEBTOR_CRD_PERIOD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_PERIOD)));
				aDebtor.setFDEBTOR_CHK_CRD_PRD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_PRD)));
				aDebtor.setFDEBTOR_CRD_LIMIT(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_LIMIT)));
				aDebtor.setFDEBTOR_CHK_CRD_LIMIT(
						cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_LIMIT)));
				aDebtor.setFDEBTOR_REP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_REP_CODE)));
				aDebtor.setFDEBTOR_RANK_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_RANK_CODE)));
				aDebtor.setFDEBTOR_SUMMARY(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_SUMMARY)));

				list.add(aDebtor);

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

	public ArrayList<Debtor> getAllSelectedCustomers() {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Debtor> list = new ArrayList<Debtor>();
		Cursor cursor = null;
		try {
			String selectQuery = "select * from " + dbHelper.TABLE_TEMP_FDEBTOR + " GROUP BY " + dbHelper.FDEBTOR_CODE;

			cursor = dB.rawQuery(selectQuery, null);
			while (cursor.moveToNext()) {

				Debtor aDebtor = new Debtor();

				aDebtor.setFDEBTOR_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ID)));
				aDebtor.setFDEBTOR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODE)));
				aDebtor.setFDEBTOR_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME)));

				list.add(aDebtor);

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

	public ArrayList<Debtor> getCustomerByCodeAndName(String newText) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Debtor> list = new ArrayList<Debtor>();
		Cursor cursor = null;
		try {
			String selectQuery = "select * from " + dbHelper.TABLE_FDEBTOR + " where " + dbHelper.FDEBTOR_NAME
					+ " like '" + newText + "%'";
			// Original Menaka 25-05-2016 String selectQuery = "select * from "
			// +
			// dbHelper.TABLE_FDEBTOR + " where " + dbHelper.FDEBTOR_CODE + " ||
			// " +
			// dbHelper.FDEBTOR_NAME + " like '%" + newText + "%'";
			cursor = dB.rawQuery(selectQuery, null);
			while (cursor.moveToNext()) {

				Debtor aDebtor = new Debtor();

				aDebtor.setFDEBTOR_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ID)));
				aDebtor.setFDEBTOR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODE)));
				aDebtor.setFDEBTOR_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME)));
				aDebtor.setFDEBTOR_ADD1(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD1)));
				aDebtor.setFDEBTOR_ADD2(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD2)));
				aDebtor.setFDEBTOR_ADD3(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD3)));
				aDebtor.setFDEBTOR_TELE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TELE)));
				aDebtor.setFDEBTOR_MOB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_MOB)));
				aDebtor.setFDEBTOR_EMAIL(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_EMAIL)));
				aDebtor.setFDEBTOR_CREATEDATE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CREATEDATE)));
				aDebtor.setFDEBTOR_TOWN_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TOWN_CODE)));
				aDebtor.setFDEBTOR_AREA_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_AREA_CODE)));
				// aDebtor.setFDEBTOR_DBGR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_DBGR_CODE)));
				aDebtor.setFDEBTOR_STATUS(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_STATUS)));
				// aDebtor.setFDEBTOR_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_USER)));
				// aDebtor.setFDEBTOR_ADD_DATE_DEB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_DATE_DEB)));
				// aDebtor.setFDEBTOR_ADD_MACH(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_MACH)));
				aDebtor.setFDEBTOR_CRD_PERIOD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_PERIOD)));
				aDebtor.setFDEBTOR_CHK_CRD_PRD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_PRD)));
				aDebtor.setFDEBTOR_CRD_LIMIT(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_LIMIT)));
				aDebtor.setFDEBTOR_CHK_CRD_LIMIT(
						cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_LIMIT)));
				aDebtor.setFDEBTOR_REP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_REP_CODE)));
				// aDebtor.setFDEBTOR_RANK_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_RANK_CODE)));
				aDebtor.setFDEBTOR_SUMMARY(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_SUMMARY)));

				list.add(aDebtor);

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

	public ArrayList<Debtor> getCustomerByCodeAndNameForReceipt(String newText) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Debtor> list = new ArrayList<Debtor>();
		Cursor cursor = null;
		try {
			String selectQuery = "select * from " + dbHelper.TABLE_FDEBTOR + " where " + dbHelper.FDEBTOR_NAME
					+ " like '" + newText + "%' " + "and " + dbHelper.FDEBTOR_CODE + " in ( select distinct "
					+ dbHelper.FDEBTOR_CODE + " from " + dbHelper.TABLE_FDDBNOTE + " where " + dbHelper.FDDBNOTE_TOT_BAL
					+ " > 0) ";
			// Original Menaka 25-05-2016 String selectQuery = "select * from "
			// +
			// dbHelper.TABLE_FDEBTOR + " where " + dbHelper.FDEBTOR_CODE + " ||
			// " +
			// dbHelper.FDEBTOR_NAME + " like '%" + newText + "%'";

			cursor = dB.rawQuery(selectQuery, null);
			while (cursor.moveToNext()) {

				Debtor aDebtor = new Debtor();

				aDebtor.setFDEBTOR_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ID)));
				aDebtor.setFDEBTOR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODE)));
				aDebtor.setFDEBTOR_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME)));
				aDebtor.setFDEBTOR_ADD1(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD1)));
				aDebtor.setFDEBTOR_ADD2(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD2)));
				aDebtor.setFDEBTOR_ADD3(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD3)));
				aDebtor.setFDEBTOR_TELE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TELE)));
				aDebtor.setFDEBTOR_MOB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_MOB)));
				aDebtor.setFDEBTOR_EMAIL(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_EMAIL)));
				aDebtor.setFDEBTOR_CREATEDATE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CREATEDATE)));
				aDebtor.setFDEBTOR_TOWN_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TOWN_CODE)));
				aDebtor.setFDEBTOR_AREA_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_AREA_CODE)));
				// aDebtor.setFDEBTOR_DBGR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_DBGR_CODE)));
				aDebtor.setFDEBTOR_STATUS(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_STATUS)));
				// aDebtor.setFDEBTOR_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_USER)));
				// aDebtor.setFDEBTOR_ADD_DATE_DEB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_DATE_DEB)));
				// aDebtor.setFDEBTOR_ADD_MACH(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_MACH)));
				aDebtor.setFDEBTOR_CRD_PERIOD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_PERIOD)));
				aDebtor.setFDEBTOR_CHK_CRD_PRD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_PRD)));
				aDebtor.setFDEBTOR_CRD_LIMIT(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_LIMIT)));
				aDebtor.setFDEBTOR_CHK_CRD_LIMIT(
						cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_LIMIT)));
				aDebtor.setFDEBTOR_REP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_REP_CODE)));
				// aDebtor.setFDEBTOR_RANK_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_RANK_CODE)));
				aDebtor.setFDEBTOR_SUMMARY(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_SUMMARY)));

				list.add(aDebtor);

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

	public ArrayList<Debtor> getRouteCustomers(String RouteCode) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Debtor> list = new ArrayList<Debtor>();

		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FROUTEDET + " RD, " + dbHelper.TABLE_FMDEBTOR
				+ " D WHERE RD." + dbHelper.FROUTEDET_DEB_CODE + "=D." + dbHelper.FDEBTOR_CODEM + " AND RD."
				+ dbHelper.FROUTEDET_ROUTE_CODE + "='" + RouteCode + "'";

		Cursor cursor = dB.rawQuery(selectQuery, null);
		while (cursor.moveToNext()) {

			Debtor aDebtor = new Debtor();

			aDebtor.setFDEBTOR_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ID)));
			aDebtor.setFDEBTOR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODE)));
			aDebtor.setFDEBTOR_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME)));
			aDebtor.setFDEBTOR_ADD1(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD1)));
			aDebtor.setFDEBTOR_ADD2(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD2)));
			aDebtor.setFDEBTOR_ADD3(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD3)));
			aDebtor.setFDEBTOR_TELE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TELE)));
			aDebtor.setFDEBTOR_MOB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_MOB)));
			aDebtor.setFDEBTOR_EMAIL(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_EMAIL)));
			aDebtor.setFDEBTOR_CREATEDATE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CREATEDATE)));
			aDebtor.setFDEBTOR_TOWN_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TOWN_CODE)));
			aDebtor.setFDEBTOR_AREA_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_AREA_CODE)));
			aDebtor.setFDEBTOR_DBGR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_DBGR_CODE)));
			aDebtor.setFDEBTOR_STATUS(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_STATUS)));
			aDebtor.setFDEBTOR_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_USER)));
			aDebtor.setFDEBTOR_ADD_DATE_DEB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_DATE_DEB)));
			aDebtor.setFDEBTOR_ADD_MACH(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_MACH)));
			aDebtor.setFDEBTOR_CRD_PERIOD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_PERIOD)));
			aDebtor.setFDEBTOR_CHK_CRD_PRD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_PRD)));
			aDebtor.setFDEBTOR_CRD_LIMIT(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_LIMIT)));
			aDebtor.setFDEBTOR_CHK_CRD_LIMIT(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_LIMIT)));
			aDebtor.setFDEBTOR_REP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_REP_CODE)));
			aDebtor.setFDEBTOR_RANK_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_RANK_CODE)));
			aDebtor.setFDEBTOR_SUMMARY(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_SUMMARY)));

			list.add(aDebtor);

		}

		return list;
	}

	public ArrayList<Debtor> getRouteCustomersByCodeAndName(String RouteCode, String newText) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Debtor> list = new ArrayList<Debtor>();
		Cursor cursor = null;
		try {
			String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FROUTEDET + " RD, " + dbHelper.TABLE_FDEBTOR
					+ " D WHERE RD." + dbHelper.FROUTEDET_DEB_CODE + "=D." + dbHelper.FDEBTOR_CODE + " AND RD."
					+ dbHelper.FROUTEDET_ROUTE_CODE + "='" + RouteCode + "' AND D." + dbHelper.FDEBTOR_CODE + " || D."
					+ dbHelper.FDEBTOR_NAME + " like '%" + newText + "%'";
			;

			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {

				Debtor aDebtor = new Debtor();

				aDebtor.setFDEBTOR_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ID)));
				aDebtor.setFDEBTOR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODE)));
				aDebtor.setFDEBTOR_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME)));
				aDebtor.setFDEBTOR_ADD1(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD1)));
				aDebtor.setFDEBTOR_ADD2(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD2)));
				aDebtor.setFDEBTOR_ADD3(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD3)));
				aDebtor.setFDEBTOR_TELE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TELE)));
				aDebtor.setFDEBTOR_MOB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_MOB)));
				aDebtor.setFDEBTOR_EMAIL(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_EMAIL)));
				aDebtor.setFDEBTOR_CREATEDATE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CREATEDATE)));
				aDebtor.setFDEBTOR_TOWN_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TOWN_CODE)));
				aDebtor.setFDEBTOR_AREA_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_AREA_CODE)));
				aDebtor.setFDEBTOR_DBGR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_DBGR_CODE)));
				aDebtor.setFDEBTOR_STATUS(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_STATUS)));
				aDebtor.setFDEBTOR_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_USER)));
				aDebtor.setFDEBTOR_ADD_DATE_DEB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_DATE_DEB)));
				aDebtor.setFDEBTOR_ADD_MACH(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_MACH)));
				aDebtor.setFDEBTOR_CRD_PERIOD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_PERIOD)));
				aDebtor.setFDEBTOR_CHK_CRD_PRD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_PRD)));
				aDebtor.setFDEBTOR_CRD_LIMIT(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_LIMIT)));
				aDebtor.setFDEBTOR_CHK_CRD_LIMIT(
						cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_LIMIT)));
				aDebtor.setFDEBTOR_REP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_REP_CODE)));
				aDebtor.setFDEBTOR_RANK_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_RANK_CODE)));
				aDebtor.setFDEBTOR_SUMMARY(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_SUMMARY)));

				list.add(aDebtor);

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

	public Debtor getSelectedCustomerByCode(String code) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		// ArrayList<Debtor> list = new ArrayList<Debtor>();
		Cursor cursor = null;
		try {
			String selectQuery = "select * from " + dbHelper.TABLE_FDEBTOR + " Where " + dbHelper.FDEBTOR_CODE + "='"
					+ code + "'";

			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {

				Debtor aDebtor = new Debtor();

				aDebtor.setFDEBTOR_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ID)));
				aDebtor.setFDEBTOR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODE)));
				aDebtor.setFDEBTOR_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME)));
				aDebtor.setFDEBTOR_ADD1(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD1)));
				aDebtor.setFDEBTOR_ADD2(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD2)));
				aDebtor.setFDEBTOR_ADD3(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD3)));
				aDebtor.setFDEBTOR_TELE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TELE)));
				aDebtor.setFDEBTOR_MOB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_MOB)));
				aDebtor.setFDEBTOR_EMAIL(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_EMAIL)));
				aDebtor.setFDEBTOR_CREATEDATE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CREATEDATE)));
				aDebtor.setFDEBTOR_TOWN_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TOWN_CODE)));
				aDebtor.setFDEBTOR_AREA_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_AREA_CODE)));
				aDebtor.setFDEBTOR_DBGR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_DBGR_CODE)));
				aDebtor.setFDEBTOR_STATUS(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_STATUS)));
				aDebtor.setFDEBTOR_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_USER)));
				aDebtor.setFDEBTOR_ADD_DATE_DEB(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_DATE_DEB)));
				aDebtor.setFDEBTOR_ADD_MACH(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD_MACH)));
				aDebtor.setFDEBTOR_CRD_PERIOD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_PERIOD)));
				aDebtor.setFDEBTOR_CHK_CRD_PRD(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_PRD)));
				aDebtor.setFDEBTOR_CRD_LIMIT(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CRD_LIMIT)));
				aDebtor.setFDEBTOR_CHK_CRD_LIMIT(
						cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_CRD_LIMIT)));
				aDebtor.setFDEBTOR_REP_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_REP_CODE)));
				aDebtor.setFDEBTOR_RANK_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_RANK_CODE)));
				aDebtor.setFDEBTOR_SUMMARY(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_SUMMARY)));

				return aDebtor;

			}
		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());

		} finally {
			if (cursor != null) {
				cursor.close();
			}
			dB.close();
		}

		return null;

	}

	@SuppressWarnings("static-access")
	public String getCustNameByCode(String code) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {
			String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FDEBTOR + " WHERE " + dbHelper.FDEBTOR_CODE + "='"
					+ code + "'";

			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {

				return cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME));

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (cursor != null) {
				cursor.close();
				dB.close();
			}
		}
		return "";
	}

	@SuppressWarnings("static-access")
	public String getTownNameByCode(String code) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {
			String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FTOWN + " WHERE " + dbHelper.FTOWN_CODE
					+ " IN (SELECT " + dbHelper.FTOWN_CODE + " FROM " + dbHelper.TABLE_FDEBTOR + " WHERE "
					+ dbHelper.FDEBTOR_CODE + " = '" + code + "')";

			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {

				return cursor.getString(cursor.getColumnIndex(dbHelper.FTOWN_NAME));

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (cursor != null) {
				cursor.close();
				dB.close();
			}
		}
		return "";
	}

	@SuppressWarnings("static-access")
	public String getTaxRegStatus(String code) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {
			String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FDEBTOR + " WHERE " + dbHelper.FDEBTOR_CODE + "='"
					+ code + "'";

			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {

				return cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TAXREG));

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (cursor != null) {
				cursor.close();
				dB.close();
			}
		}
		return "";
	}

	public ArrayList<Debtor> getDebDetails(String searchword) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<Debtor> Itemname = new ArrayList<Debtor>();

		String selectQuery = "select DebName,DebCode from fDebtor where DebCode LIKE '%" + searchword
				+ "%' OR DebName LIKE '%" + searchword + "%'";

		Cursor cursor = null;
		try {
			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {
				Debtor items = new Debtor();

				items.setFDEBTOR_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME)));
				items.setFDEBTOR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CODE)));
				Itemname.add(items);
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (cursor != null) {
				cursor.close();
				dB.close();
			}
		}
		return Itemname;
	}

	public String getDebNameByCode(String code) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FDEBTOR + " WHERE " + dbHelper.FDEBTOR_CODE + "='" + code
				+ "'";

		Cursor cursor = null;
		try {
			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {

				return cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME));

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (cursor != null) {
				cursor.close();
				dB.close();
			}
		}
		return "";

	}

	public boolean getCheckAllowSelect(String code) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FDEBTOR + " WHERE " + dbHelper.FDEBTOR_CODE + "='" + code
				+ "'";

		Cursor cursor = null;
		try {
			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {
				int AllowChange = Integer
						.parseInt(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_CHK_MUSTFREE)));

				if (AllowChange == 1) {
					return true;
				} else {
					return false;
				}

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (cursor != null) {
				cursor.close();
				dB.close();
			}
		}
		return false;

	}

	@SuppressWarnings("static-access")
	public String getRouteNameByCode(String code) {

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		Cursor cursor = null;
		try {

			String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FROUTE + " WHERE " + dbHelper.FROUTE_ROUTECODE
					+ " IN (SELECT " + dbHelper.FROUTEDET_ROUTE_CODE + " FROM " + dbHelper.TABLE_FROUTEDET + " WHERE "
					+ dbHelper.FROUTEDET_DEB_CODE + " = '" + code + "')";

			cursor = dB.rawQuery(selectQuery, null);

			while (cursor.moveToNext()) {

				return cursor.getString(cursor.getColumnIndex(dbHelper.FROUTE_ROUTE_NAME));

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (cursor != null) {
				cursor.close();
				dB.close();
			}
		}
		return "";
	}

}
