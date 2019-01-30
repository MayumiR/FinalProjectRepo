package com.bit.sfa.DataControl;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.FmDebDet;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 7/23/2018.
 */

public class FmDebDetDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "FmDebDetDS";

    public static SharedPreferences localSP;

    public FmDebDetDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public long InsertFmDebDet(ArrayList<FmDebDet> fmDebDets){

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {
            dB.beginTransaction();
            String sql = "Insert or Replace into " + dbHelper.TABLE_FMDEBDET + " (" + dbHelper.DEBCODEM + ", "
                    + dbHelper.DEBCODE+","
                    + dbHelper.RECORD_ID  +") values(?,?,?)";

            SQLiteStatement insert = dB.compileStatement(sql);

            for (FmDebDet debDet : fmDebDets) {

                insert.bindString(1, debDet.getDEBCODEM());
                insert.bindString(2, debDet.getDEBCODE());
                insert.bindString(3, debDet.getRECORD_ID());

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
