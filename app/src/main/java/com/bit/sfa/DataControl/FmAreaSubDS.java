package com.bit.sfa.DataControl;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.FmAreaSub;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 7/23/2018.
 */

public class FmAreaSubDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "FmAreaSubDS";

    public static SharedPreferences localSP;

    public FmAreaSubDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public long InsertFmAreaSub(ArrayList<FmAreaSub> fmAreaSubs) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {
            dB.beginTransaction();
            String sql = "Insert or Replace into " + dbHelper.TABLE_FAREA_SUB + " (" + dbHelper.AREA_SCODE + ", "
                    + dbHelper.AREA_SNAME + ","
                    + dbHelper.ROUTE_CODE + ","
                    + dbHelper.ADD_USER + ","
                    + dbHelper.ADD_DATE + ","
                    + dbHelper.ADD_MACH + ","
                    + dbHelper.RECORD_ID + ") values(?,?,?,?,?,?,?)";

            SQLiteStatement insert = dB.compileStatement(sql);

            for (FmAreaSub area : fmAreaSubs) {

                insert.bindString(1, area.getAREA_SCODE());
                insert.bindString(2, area.getAREA_SNAME());
                insert.bindString(3, area.getROUTE_CODE());
                insert.bindString(4, area.getADD_USER());
                insert.bindString(5, area.getADD_DATE());
                insert.bindString(6, area.getADD_MACH());
                insert.bindString(7, area.getRECORD_ID());
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
