package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.OrderDisc;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 6/20/2018.
 */

public class OrderDiscDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "swadeshi orderDisc";

    public OrderDiscDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    @SuppressWarnings("static-access")
    public int createOrUpdateOrdDisc(ArrayList<OrderDisc> list) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            for (OrderDisc orderDisc : list) {

                ContentValues values = new ContentValues();

                String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDDISC + " WHERE " + dbHelper.FORDDISC_REFNO + " = '" + orderDisc.getRefNo() + "'";

                cursor = dB.rawQuery(selectQuery, null);

                values.put(dbHelper.FORDDISC_REFNO, orderDisc.getRefNo());
                values.put(dbHelper.FORDDISC_TXNDATE, orderDisc.getTxnDate());
                values.put(dbHelper.FORDDISC_REFNO1, orderDisc.getRefNo1());
                values.put(dbHelper.FORDDISC_ITEMCODE, orderDisc.getItemCode());
                values.put(dbHelper.FORDDISC_DISAMT, orderDisc.getDisAmt());
                values.put(dbHelper.FORDDISC_DISPER, orderDisc.getDisPer());

                int cn = cursor.getCount();
                if (cn > 0) {

                    count = dB.update(dbHelper.TABLE_FORDDISC, values, dbHelper.FORDDISC_REFNO + " =?", new String[] { String.valueOf(orderDisc.getRefNo()) });

                } else {
                    count = (int) dB.insert(dbHelper.TABLE_FORDDISC, null, values);
                }

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

	/*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*--*-Update order discount table-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public void UpdateOrderDiscount(OrderDisc orderDisc, String DiscRef, String DiscPer) {

		/* Remove record using order discount ref no & item code */
        RemoveOrderDiscRecord(orderDisc.getRefNo(), orderDisc.getItemCode());

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        try {

            ContentValues values = new ContentValues();

            values.put(dbHelper.FORDDISC_REFNO, orderDisc.getRefNo());
            values.put(dbHelper.FORDDISC_TXNDATE, orderDisc.getTxnDate());
            values.put(dbHelper.FORDDISC_REFNO1, DiscRef);
            values.put(dbHelper.FORDDISC_ITEMCODE, orderDisc.getItemCode());
            values.put(dbHelper.FORDDISC_DISAMT, orderDisc.getDisAmt());
            values.put(dbHelper.FORDDISC_DISPER, DiscPer);
            dB.insert(dbHelper.TABLE_FORDDISC, null, values);
        } catch (Exception e) {
            Log.v(TAG + " Exception", e.toString());
        } finally {

            dB.close();
        }

    }

	/*-*-*-*-*-*-*-*-*Check record availability using RefNo and Itemcode in FORDDISC*-*-*-*-*-*-*-*-*-*-*-*/

    public boolean isRecordAvailable(String RefNo, String ItemCode) {

        int count = 0;
        boolean Result = false;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {

            String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDDISC + " WHERE " + dbHelper.FORDDISC_REFNO + " = '" + RefNo + "' AND " + dbHelper.FORDDISC_ITEMCODE + " = '" + ItemCode + "'";
            cursor = dB.rawQuery(selectQuery, null);
            count = cursor.getCount();

            if (count > 0) {
                Result = true;
            } else {
                Result = false;
            }

        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }
        return Result;
    }

	/*-*-*-*-*-*-*-*- Remove particular record from Order Discount table -*-*-* *-*-*-*-*-*/

    public void RemoveOrderDiscRecord(String RefNo, String ItemCode) {

        int count;
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FORDDISC + " WHERE " + dbHelper.FORDDISC_REFNO + " = '" + RefNo + "' AND " + dbHelper.FORDDISC_ITEMCODE + " = '" + ItemCode + "'", null);
            count = cursor.getCount();
            if (count > 0) {
                dB.delete(dbHelper.TABLE_FORDDISC, dbHelper.FORDDISC_REFNO + " = '" + RefNo + "' AND " + dbHelper.FORDDISC_ITEMCODE + " = '" + ItemCode + "'", null);
            }

        } catch (Exception e) {
            Log.v(TAG + " Exception", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

    }

	/* Delete all records*/

    public int deleteAll() {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {

            cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FORDDISC, null);
            count = cursor.getCount();
            if (count > 0) {
                int success = dB.delete(dbHelper.TABLE_FORDDISC, null, null);
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

	/*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**--*--*-*-*-*-*-*-*-*-*-*-*-*-**/

}
