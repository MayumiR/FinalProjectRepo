package com.bit.sfa.DataControl;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.FmissSubDet;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 7/26/2018.
 */

public class FmissSubDetDS {

    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "FmissSubDetDS";

    public static SharedPreferences localSP;

    public FmissSubDetDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public long InsertFmissSubDet(ArrayList<FmissSubDet> fmissSubDets) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {
            dB.beginTransaction();
            String sql = "Insert or Replace into " + dbHelper.TABLE_FMISS_SUBDET + " (" + dbHelper.FMISS_SUBDET_REFNO + ", "
                    + dbHelper.FMISS_SUBDET_ITEMCODE + ","
                    + dbHelper.FMISS_SUBDET_GITEMCODE + ","
                    + dbHelper.FMISS_SUBDET_COSTPRICE + ","
                    + dbHelper.FMISS_SUBDET_QTY + ","
                    + dbHelper.FMISS_SUBDET_AMT + ","
                    + dbHelper.FMISS_SUBDET_SQNO + ","
                    + dbHelper.FMISS_SUBDET_RECORDID + ") values(?,?,?,?,?,?,?,?)";

            SQLiteStatement insert = dB.compileStatement(sql);

            for (FmissSubDet subDet : fmissSubDets) {

                insert.bindString(1, subDet.getRefn());
                insert.bindString(2, subDet.getItemCode());
                insert.bindString(3, subDet.getGItemCode());
                insert.bindString(4, subDet.getCostPrice());
                insert.bindString(5, subDet.getQty());
                insert.bindString(6, subDet.getAmt());
                insert.bindString(7, subDet.getSeqNo());
                insert.bindString(8, subDet.getRecordId());

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
