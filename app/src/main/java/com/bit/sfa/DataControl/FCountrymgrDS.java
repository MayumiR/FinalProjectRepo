package com.bit.sfa.DataControl;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.FCountrymgr;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 7/23/2018.
 */

public class FCountrymgrDS {

    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "FCountrymgrDS";

    public static SharedPreferences localSP;

    public FCountrymgrDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public long InsertFCountrymgr(ArrayList<FCountrymgr> fCountrymgrs){

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {
            dB.beginTransaction();
            String sql = "Insert or Replace into " + dbHelper.TABLE_FCOUNTRY_MGR + " (" + dbHelper.COUNTRY_CODE + ", "
                    + dbHelper.COUNTRY_NAME +") values(?,?)";

            SQLiteStatement insert = dB.compileStatement(sql);

            for (FCountrymgr countrymgr : fCountrymgrs) {

                insert.bindString(1, countrymgr.getCOUNTRY_CODE());
                insert.bindString(2, countrymgr.getCOUNTRY_NAME());
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