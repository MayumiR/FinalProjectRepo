package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class FreeMslabDS {

	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "swadeshi";

	public FreeMslabDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}

	@SuppressWarnings("static-access")
	public int createOrUpdateFreeMslab(ArrayList<FreeMslab> list) {

		int count = 0;

		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		try {
			dB.beginTransaction();

			String sql = "Insert or Replace into " + dbHelper.TABLE_FFREEMSLAB + " (" + dbHelper.FFREEMSLAB_REFNO + ", " + dbHelper.FFREEMSLAB_QTY_F + ", " + dbHelper.FFREEMSLAB_QTY_T + ", " + dbHelper.FFREEMSLAB_ITEM_QTY + ", " + dbHelper.FFREEMSLAB_FREE_IT_QTY + ", " + dbHelper.FFREEMSLAB_ADD_USER + ", " + dbHelper.FFREEMSLAB_ADD_DATE + ", " + dbHelper.FFREEMSLAB_ADD_MACH + ", " + dbHelper.FFREEMSLAB_RECORD_ID + ", " + dbHelper.FFREEMSLAB_TIMESTAMP_COLUMN + ", " + dbHelper.FFREEMSLAB_SEQ_NO + ") values(?,?,?,?,?,?,?,?,?,?,?)";

			SQLiteStatement insert = dB.compileStatement(sql);

			for (int i = 0; i < list.size(); i++) {

				FreeMslab item = list.get(i);

				insert.bindString(1, item.getFFREEMSLAB_REFNO());
				insert.bindString(2, item.getFFREEMSLAB_QTY_F());
				insert.bindString(3, item.getFFREEMSLAB_QTY_T());
				insert.bindString(4, item.getFFREEMSLAB_ITEM_QTY());
				insert.bindString(5, item.getFFREEMSLAB_FREE_IT_QTY());
				insert.bindString(6, item.getFFREEMSLAB_ADD_USER());
				insert.bindString(7, item.getFFREEMSLAB_ADD_DATE());
				insert.bindString(8, item.getFFREEMSLAB_ADD_MACH());
				insert.bindString(9, item.getFFREEMSLAB_RECORD_ID());
				insert.bindString(10, item.getFFREEMSLAB_TIMESTAMP_COLUMN());
				insert.bindString(11, item.getFFREEMSLAB_SEQ_NO());
				insert.execute();

				count = count + 1;
			}

			dB.setTransactionSuccessful();
			Log.w(TAG, "Done");
		} catch (Exception e) {

			Log.v(TAG + " Exception", e.toString());

		} finally {
			dB.endTransaction();

			dB.close();
		}
		return count;
	}

	public ArrayList<FreeMslab> getMixDetails(String refno, int tQty) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		ArrayList<FreeMslab> list = new ArrayList<FreeMslab>();

		String selectQuery = "select * from ffreeMslab where refno='" + refno + "' and " + tQty + " between CAST(Qtyf as double) and CAST(Qtyt as double)";

		Cursor cursor = dB.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {

			FreeMslab freeMslab = new FreeMslab();

			freeMslab.setFFREEMSLAB_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_ID)));
			freeMslab.setFFREEMSLAB_REFNO(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_REFNO)));
			freeMslab.setFFREEMSLAB_QTY_F(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_QTY_F)));
			freeMslab.setFFREEMSLAB_QTY_T(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_QTY_T)));
			freeMslab.setFFREEMSLAB_ITEM_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_ITEM_QTY)));
			freeMslab.setFFREEMSLAB_FREE_IT_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_FREE_IT_QTY)));
			freeMslab.setFFREEMSLAB_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_ADD_USER)));
			freeMslab.setFFREEMSLAB_ADD_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_ADD_DATE)));
			freeMslab.setFFREEMSLAB_ADD_MACH(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_ADD_MACH)));
			freeMslab.setFFREEMSLAB_RECORD_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_RECORD_ID)));
			freeMslab.setFFREEMSLAB_TIMESTAMP_COLUMN(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_TIMESTAMP_COLUMN)));
			freeMslab.setFFREEMSLAB_SEQ_NO(cursor.getString(cursor.getColumnIndex(dbHelper.FFREEMSLAB_SEQ_NO)));

			list.add(freeMslab);

		}

		return list;
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
			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FFREEMSLAB, null);
			count = cursor.getCount();
			if (count > 0) {
				int success = dB.delete(dbHelper.TABLE_FFREEMSLAB, null, null);
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

	public String getFreeDetails(String itemcode, String DebCode) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String FreeDetails = "";
		String Seperate = "";
		String selectQuerydeb = "";
		String freeRefNo = "";
		boolean hasdebfree = false;
		Cursor cursor = null;
//		Cursor cursordeb = null;
		Cursor cursordebcnt = null;
		Cursor cursordebfree = null;
		
		try {

			// String selectQuery = "SELECT refno,ItemQty, FreeItQty FROM
			// ffreemslab WHERE refno IN (SELECT refno FROM ffreedet where
			// itemcode='" + itemcode
			// + "') and refno NOT IN (SELECT refno FROM ffreedeb WHERE
			// DebCode='"+DebCode+"') order by seqno";
			//

			String selectQuery = " SELECT distinct refno as Refno FROM ffreemslab WHERE refno IN (SELECT refno FROM ffreedet where itemcode='" + itemcode + "')";
			cursor = dB.rawQuery(selectQuery, null);

			if (cursor.getCount() > 0) {
				// cursor.moveToFirst();
				if (cursor.moveToFirst()) {
					while (!cursor.isAfterLast()) {

						// for (cursor.moveToFirst(); !cursor.isAfterLast();
						// cursor.moveToNext()) {
						// do what you need with the cursor here
						// String freeRefNo ="";

						freeRefNo = cursor.getString(cursor.getColumnIndex(dbHelper.FFREEDEB_REFNO));

						String selectQuerydebcnt = "SELECT refno FROM " + dbHelper.TABLE_FFREEDEB + " WHERE DebCode='" + DebCode + "' AND " + dbHelper.FFREEDEB_REFNO + "='" + freeRefNo + "'";
						cursordebcnt = dB.rawQuery(selectQuerydebcnt, null);

						if (cursordebcnt.getCount() > 0) {

//							String selectQueryDeb = "SELECT refno FROM ffreedeb WHERE DebCode='" + DebCode + "' AND RefNo = '" + freeRefNo + "'";
//							cursordeb = dB.rawQuery(selectQueryDeb, null);

							if (cursordebcnt.getCount() > 0) {
								hasdebfree = true;
								String selectQueryDebfree = "SELECT refno,ItemQty, FreeItQty FROM ffreemslab WHERE refno = '" + freeRefNo + "'";
								// freeRefNo =
								// cursor.getString(cursor.getColumnIndex(dbHelper.FFREEDEB_REFNO));
								cursordebfree = dB.rawQuery(selectQueryDebfree, null);

								while (cursordebfree.moveToNext()) {

									if (cursordebfree.isLast()) {
										Seperate = "";
									} else {
										Seperate = " || ";
									}

									FreeDetails = FreeDetails + cursordebfree.getString(cursordebfree.getColumnIndex(dbHelper.FFREEMSLAB_ITEM_QTY)) + " x " + cursordebfree.getString(cursordebfree.getColumnIndex(dbHelper.FFREEMSLAB_FREE_IT_QTY)) + Seperate;

									Seperate = "";
								}
							}
						} else {

							String selectQueryDebfree = "SELECT refno,ItemQty, FreeItQty FROM ffreemslab WHERE refno = '" + freeRefNo + "'";

							cursordebfree = dB.rawQuery(selectQueryDebfree, null);

							while (cursordebfree.moveToNext()) {

								if (cursordebfree.isLast()) {
									Seperate = "";
								} else {
									Seperate = " || ";
								}

								FreeDetails = FreeDetails + cursordebfree.getString(cursordebfree.getColumnIndex(dbHelper.FFREEMSLAB_ITEM_QTY)) + " x " + cursordebfree.getString(cursordebfree.getColumnIndex(dbHelper.FFREEMSLAB_FREE_IT_QTY)) + Seperate;

								Seperate = "";
							}
						}

						cursor.moveToNext();
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return FreeDetails;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			
//			if (cursordeb != null) {
//				cursordeb.close();
//			}
			
			if (cursordebcnt != null) {
				cursordebcnt.close();
			}
			
			if (cursordebfree != null) {
				cursordebfree.close();
			}
			dB.close();
		}

		return FreeDetails;
	}
	
	public String getFreeDetailsnew(String freeref, String DebCode) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		String FreeDetails = "";
		String Seperate = "";
		String selectQuerydeb = "";
		//String freeRefNo = "";
		boolean hasdebfree = false;
		Cursor cursor = null;
//		Cursor cursordeb = null;
		Cursor cursordebcnt = null;
		Cursor cursordebfree = null;
		
		try {

			



						String selectQuerydebcnt = "SELECT refno FROM " + dbHelper.TABLE_FFREEDEB + " WHERE DebCode='" + DebCode + "' AND " + dbHelper.FFREEDEB_REFNO + "='" + freeref + "'";
						cursordebcnt = dB.rawQuery(selectQuerydebcnt, null);

						if (cursordebcnt.getCount() > 0) {

//							String selectQueryDeb = "SELECT refno FROM ffreedeb WHERE DebCode='" + DebCode + "' AND RefNo = '" + freeRefNo + "'";
//							cursordeb = dB.rawQuery(selectQueryDeb, null);

							if (cursordebcnt.getCount() > 0) {
								hasdebfree = true;
								String selectQueryDebfree = "SELECT refno,ItemQty, FreeItQty FROM ffreemslab WHERE refno = '" + freeref + "'";
								// freeRefNo =
								// cursor.getString(cursor.getColumnIndex(dbHelper.FFREEDEB_REFNO));
								cursordebfree = dB.rawQuery(selectQueryDebfree, null);

								while (cursordebfree.moveToNext()) {

									if (cursordebfree.isLast()) {
										Seperate = "";
									} else {
										Seperate = " || ";
									}

									FreeDetails = FreeDetails + cursordebfree.getString(cursordebfree.getColumnIndex(dbHelper.FFREEMSLAB_ITEM_QTY)) + " x " + cursordebfree.getString(cursordebfree.getColumnIndex(dbHelper.FFREEMSLAB_FREE_IT_QTY)) + Seperate;

									Seperate = "";
								}
							}
						} else {

							String selectQueryDebfree = "SELECT refno,ItemQty, FreeItQty FROM ffreemslab WHERE refno = '" + freeref + "'";

							cursordebfree = dB.rawQuery(selectQueryDebfree, null);

							while (cursordebfree.moveToNext()) {

								if (cursordebfree.isLast()) {
									Seperate = "";
								} else {
									Seperate = " || ";
								}

								FreeDetails = FreeDetails + cursordebfree.getString(cursordebfree.getColumnIndex(dbHelper.FFREEMSLAB_ITEM_QTY)) + " x " + cursordebfree.getString(cursordebfree.getColumnIndex(dbHelper.FFREEMSLAB_FREE_IT_QTY)) + Seperate;

								Seperate = "";
							}
						}

						

		} catch (Exception ex) {
			ex.printStackTrace();
			return FreeDetails;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			
//			if (cursordeb != null) {
//				cursordeb.close();
//			}
			
			if (cursordebcnt != null) {
				cursordebcnt.close();
			}
			
			if (cursordebfree != null) {
				cursordebfree.close();
			}
			dB.close();
		}

		return FreeDetails;
	}
}