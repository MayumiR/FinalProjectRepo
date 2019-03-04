package com.bit.sfa.DataControl;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.FmSalRep;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Rashmi on 7/23/2018.
 */

public class FmSalRepDS {

    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "FmSalRepDS";

    public static SharedPreferences localSP;

    public FmSalRepDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public long InsertFmSalRep(ArrayList<FmSalRep> fmSalReps){

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {
            dB.beginTransaction();
            String sql = "Insert or Replace into " + dbHelper.TABLE_FMSALREP + " ("
                    + dbHelper.FMSALREP_ID + ", "
                    + dbHelper.FMSALREP_IDNO +","
                    + dbHelper.FMSALREP_NAME +","
                    + dbHelper.FMSALREP_ADD1 +","
                    + dbHelper.FMSALREP_ADD2 +","
                    + dbHelper.FMSALREP_ADD3 +","
                    + dbHelper.FMSALREP_TELE +","
                    + dbHelper.FMSALREP_MOBILE +","
                    + dbHelper.FMSALREP_EMAIL +","
                    + dbHelper.FMSALREP_ROUTE_CODE +","
                    + dbHelper.FMSALREP_LOCCODE +","
                    + dbHelper.FMSALREP_REP_PREFIX +","
                    + dbHelper.FMSALREP_REP_MACKID+","
                    + dbHelper.FMSALREP_AREASCODE +","
                    + dbHelper.FMSALREP_ADD_USER +","
                    + dbHelper.FMSALREP_ADD_MACH +","
                    + dbHelper.FMSALREP_ADD_DATE +","
                    + dbHelper.FMSALREP_REP_PASSWORD+","
                    + dbHelper.FMSALREP_REP_STATUS+","
                    + dbHelper.FSALREP_RECORD_ID
                    +") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            SQLiteStatement insert = dB.compileStatement(sql);

            for (FmSalRep salRep : fmSalReps) {

                insert.bindString(1, salRep.getRepCodem());
                insert.bindString(2, salRep.getRepIdNo());
                insert.bindString(3, salRep.getRepNamem());
                insert.bindString(4, salRep.getRepAdd1());
                insert.bindString(5, salRep.getRepAdd2());
                insert.bindString(6, salRep.getRepAdd3());
                insert.bindString(7, salRep.getRepTele());
                insert.bindString(8, salRep.getRepMobil());
                insert.bindString(9, salRep.getRepEMail());
                insert.bindString(10, salRep.getRouteCode());
                insert.bindString(11, salRep.getLocCode());
                insert.bindString(12, salRep.getPrefix());
                insert.bindString(13, salRep.getMackid());
                insert.bindString(14, salRep.getAreascode());
                insert.bindString(16, salRep.getAddUser());
                insert.bindString(17, salRep.getAddMach());
                insert.bindString(18, salRep.getAddDate());
                insert.bindString(19, salRep.getPassword());
                insert.bindString(20, salRep.getStatus());
                insert.bindString(15, salRep.getRecordId());

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
    public String getCurrentRepCode() {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FMSALREP;

        Cursor cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            return cursor.getString(cursor.getColumnIndex(dbHelper.FMSALREP_ID));

        }

        return "";
    }

}
