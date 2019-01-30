package com.bit.sfa.DataControl;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.FmitemsDet;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 7/20/2018.
 */

public class FmitemsDetDS {

    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "TourDS";

    public static SharedPreferences localSP;

    public FmitemsDetDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public int insertFmitemsDet(ArrayList<FmitemsDet> fmitemsDets){
        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {
            dB.beginTransaction();

            String sql = "Insert or Replace into " + dbHelper.TABLE_FMITEMS_DET + " (" + dbHelper.GITEMCODE + ", "
                    + dbHelper.ITEM_CODE + ", "
                    + dbHelper.RECORDID + ") values(?,?,?)";

            SQLiteStatement insert = dB.compileStatement(sql);

            for (FmitemsDet fitem : fmitemsDets) {

                insert.bindString(1, fitem.getGITEMCODE());
                insert.bindString(2, fitem.getITEM_CODE());
                insert.bindString(3, fitem.getRECORDID());
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
