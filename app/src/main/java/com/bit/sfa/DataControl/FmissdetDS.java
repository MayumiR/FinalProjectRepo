package com.bit.sfa.DataControl;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.Fmissdet;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 7/26/2018.
 */

public class FmissdetDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "FmisshedDS";

    public static SharedPreferences localSP;

    public FmissdetDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public long InsertFmissdet(ArrayList<Fmissdet> fmissdets) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {
            dB.beginTransaction();
            String sql = "Insert or Replace into " + dbHelper.TABLE_FMISS_DET + " (" + dbHelper.FMISS_DET_REFNO + ", "
                    + dbHelper.FMISS_DET_TXNDATE + ","
                    + dbHelper.FMISS_DET_TXNTYPE + ","
                    + dbHelper.FMISS_DET_SQNO + ","
                    + dbHelper.FMISS_DET_GITEMCODE + ","
                    + dbHelper.FMISS_DET_QTY + ","
                    + dbHelper.FMISS_DET_COST_PRICE + ","
                    + dbHelper.FMISS_DET_AMT + ") values(?,?,?,?,?,?,?,?)";

            SQLiteStatement insert = dB.compileStatement(sql);

            for (Fmissdet fmissdet : fmissdets) {

                insert.bindString(1, fmissdet.getRefNo());
                insert.bindString(2, fmissdet.getTxnDate());
                insert.bindString(3, fmissdet.getTxnType());
                insert.bindString(4, fmissdet.getSeqNo());
                insert.bindString(5, fmissdet.getGItemCode());
                insert.bindString(6, fmissdet.getQty());
                insert.bindString(7, fmissdet.getCostPrice());
                insert.bindString(8, fmissdet.getAmt());

                insert.execute();

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
}