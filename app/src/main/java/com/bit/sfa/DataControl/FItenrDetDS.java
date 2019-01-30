package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.FItenrDet;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 6/20/2018.
 */

public class FItenrDetDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "FItenrDetDS";

    public FItenrDetDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    @SuppressWarnings("static-access")
    public int createOrUpdateFItenrDet(ArrayList<FItenrDet> list) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {
            dB.beginTransaction();
            String sql = "Insert or Replace into " + dbHelper.TABLE_FITENRDET + " (" + dbHelper.FITENRDET_REFNO + ", "
                    + dbHelper.FITENRDET_TXNDATE + ","
                    + dbHelper.FITENRDET_ROUTE + ","
                    + dbHelper.FITENRDET_NO_OUT_LET + ","
                    + dbHelper.FITENRDET_NO_SHUCAL + ","
                    + dbHelper.FITENRDET_REMARKS + ","
                    + dbHelper.FITENRDET_RECORDID + ") values(?,?,?,?,?,?,?)";


            SQLiteStatement insert = dB.compileStatement(sql);

            for (FItenrDet fItenrDet : list) {

                insert.bindString(1, fItenrDet.getFITENRDET_REF_NO());
                insert.bindString(2, fItenrDet.getFITENRDET_TXN_DATE());
                insert.bindString(3, fItenrDet.getFITENRDET_ROUTE_CODE());
                insert.bindString(4, fItenrDet.getFITENRDET_NO_OUTLET());
                insert.bindString(5, fItenrDet.getFITENRDET_NO_SHCUCAL());
                insert.bindString(6, fItenrDet.getFITENRDET_REMARKS());
                insert.bindString(7, fItenrDet.getFITENRDET_TXN_DATE());
                insert.execute();
                System.out.println("sathya"+fItenrDet.getFITENRDET_ID());
                count = 1;
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
            cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FITENRDET, null);
            count = cursor.getCount();
            if (count > 0) {
                int success = dB.delete(dbHelper.TABLE_FITENRDET, null, null);
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
