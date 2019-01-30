package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.FItenrHed;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class FItenrHedDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "FItenrHedDS";

    public FItenrHedDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    @SuppressWarnings("static-access")
    public int createOrUpdateFItenrHed(ArrayList<FItenrHed> list) {
        int count = 0;
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {
            dB.beginTransaction();
            String sql = "Insert or Replace into " + dbHelper.TABLE_FITENRHED + " (" + dbHelper.FITENRHED_ID + ", "
                    + dbHelper.FITENRHED_COST_CODE + ","
                    + dbHelper.FITENRHED_DEAL_CODE + ","
                    + dbHelper.FITENRHED_MONTH + ","
                    + dbHelper.FITENRHED_REF_NO + ","
                    + dbHelper.FITENRHED_REMARKS1 + ","
                    + dbHelper.FITENRHED_REP_CODE + ","
                    + dbHelper.FITENRHED_YEAR + ") values(?,?,?,?,?,?,?,?)";


            SQLiteStatement insert = dB.compileStatement(sql);

            for (FItenrHed itenrHed : list) {

                insert.bindString(1, itenrHed.getFITENRHED_ID());
                insert.bindString(2, itenrHed.getFITENRHED_COST_CODE());
                insert.bindString(3, itenrHed.getFITENRHED_DEAL_CODE());
                insert.bindString(4, itenrHed.getFITENRHED_MONTH());
                insert.bindString(5, itenrHed.getFITENRHED_REF_NO());
                insert.bindString(6, itenrHed.getFITENRHED_REMARKS1());
                insert.bindString(7, itenrHed.getFITENRHED_REP_CODE());
                insert.bindString(8, itenrHed.getFITENRHED_YEAR());
                insert.execute();
                count = 1;
            }

            dB.setTransactionSuccessful();
            Log.w(TAG, "Done");
        } catch (Exception e) {

            Log.v(TAG + " FItenrHedDS", e.toString());

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
            cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FITENRHED, null);
            count = cursor.getCount();
            if (count > 0) {
                int success = dB.delete(dbHelper.TABLE_FITENRHED, null, null);
                Log.v("Success", success + "");
            }
        } catch (Exception e) {

            Log.v(TAG + " FItenrHedDS", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return count;

    }
}
