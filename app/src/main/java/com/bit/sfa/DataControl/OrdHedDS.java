package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.OrdHed;
import com.bit.sfa.Models.PreSalesMapper;
import com.bit.sfa.R;
import com.bit.sfa.Settings.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Sathiyaraja on 6/20/2018.
 */

public class OrdHedDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "swadeshi";

    // Shared Preferences variables
    public static final String SETTINGS = "SETTINGS";
    public static SharedPreferences localSP;

    public OrdHedDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    @SuppressWarnings("static-access")
    public int createOrUpdateOrdHed(ArrayList<OrdHed> list) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            for (OrdHed ordHed : list) {

                String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDHED + " WHERE " + dbHelper.FORDHED_REFNO
                        + " = '" + ordHed.getFORDHED_REFNO() + "'";

                cursor = dB.rawQuery(selectQuery, null);

                ContentValues values = new ContentValues();

                values.put(dbHelper.FORDHED_REFNO, ordHed.getFORDHED_REFNO());
                values.put(dbHelper.FORDHED_ADD_DATE, ordHed.getFORDHED_ADD_DATE());
                values.put(dbHelper.FORDHED_ADD_MACH, ordHed.getFORDHED_ADD_MACH());
                values.put(dbHelper.FORDHED_ADD_USER, ordHed.getFORDHED_ADD_USER());
                values.put(dbHelper.FORDHED_APP_DATE, ordHed.getFORDHED_APP_DATE());
                values.put(dbHelper.FORDHED_APPSTS, ordHed.getFORDHED_APPSTS());
                values.put(dbHelper.FORDHED_APP_USER, ordHed.getFORDHED_APP_USER());
                values.put(dbHelper.FORDHED_BP_TOTAL_DIS, ordHed.getFORDHED_BP_TOTAL_DIS());
                values.put(dbHelper.FORDHED_B_TOTAL_AMT, ordHed.getFORDHED_B_TOTAL_AMT());
                values.put(dbHelper.FORDHED_B_TOTAL_DIS, ordHed.getFORDHED_B_TOTAL_DIS());
                values.put(dbHelper.FORDHED_B_TOTAL_TAX, ordHed.getFORDHED_B_TOTAL_TAX());
                values.put(dbHelper.FORDHED_COST_CODE, ordHed.getFORDHED_COST_CODE());
                values.put(dbHelper.FORDHED_CUR_CODE, ordHed.getFORDHED_CUR_CODE());
                values.put(dbHelper.FORDHED_CUR_RATE, ordHed.getFORDHED_CUR_RATE());
                values.put(dbHelper.FORDHED_DEB_CODE, ordHed.getFORDHED_DEB_CODE());
                values.put(dbHelper.FORDHED_DIS_PER, ordHed.getFORDHED_DIS_PER());
                values.put(dbHelper.FORDHED_START_TIME_SO, ordHed.getFORDHED_START_TIME_SO());
                values.put(dbHelper.FORDHED_END_TIME_SO, ordHed.getFORDHED_END_TIME_SO());
                values.put(dbHelper.FORDHED_LONGITUDE, ordHed.getFORDHED_LONGITUDE());
                values.put(dbHelper.FORDHED_LATITUDE, ordHed.getFORDHED_LATITUDE());
                values.put(dbHelper.FORDHED_LOC_CODE, ordHed.getFORDHED_LOC_CODE());
                values.put(dbHelper.FORDHED_MANU_REF, ordHed.getFORDHED_MANU_REF());
                values.put(dbHelper.FORDHED_RECORD_ID, ordHed.getFORDHED_RECORD_ID());
                values.put(dbHelper.FORDHED_REMARKS, ordHed.getFORDHED_REMARKS());
                values.put(dbHelper.FORDHED_REPCODE, ordHed.getFORDHED_REPCODE());
                values.put(dbHelper.FORDHED_TAX_REG, ordHed.getFORDHED_TAX_REG());
                values.put(dbHelper.FORDHED_TIMESTAMP_COLUMN, ordHed.getFORDHED_TIMESTAMP_COLUMN());
                values.put(dbHelper.FORDHED_TOTAL_AMT, ordHed.getFORDHED_TOTAL_AMT());
                values.put(dbHelper.FORDHED_TOTALDIS, ordHed.getFORDHED_TOTALDIS());
                values.put(dbHelper.FORDHED_TOTAL_TAX, ordHed.getFORDHED_TOTAL_TAX());
                values.put(dbHelper.FORDHED_TXN_TYPE, ordHed.getFORDHED_TXN_TYPE());
                values.put(dbHelper.FORDHED_TXN_DATE, ordHed.getFORDHED_TXN_DATE());
                values.put(dbHelper.FORDHED_ADDRESS, ordHed.getFORDHED_ADDRESS());
                values.put(dbHelper.FORDHED_TOTAL_ITM_DIS, ordHed.getFORDHED_TOTAL_ITM_DIS());
                values.put(dbHelper.FORDHED_TOT_MKR_AMT, ordHed.getFORDHED_TOT_MKR_AMT());
                values.put(dbHelper.FORDHED_DELV_DATE, ordHed.getFORDHED_DELV_DATE());
                values.put(dbHelper.FORDHED_ROUTE_CODE, ordHed.getFORDHED_ROUTE_CODE());
                values.put(dbHelper.FORDHED_HED_DIS_VAL, ordHed.getFORDHED_HED_DIS_VAL());
                values.put(dbHelper.FORDHED_HED_DIS_PER_VAL, ordHed.getFORDHED_HED_DIS_PER_VAL());
                values.put(dbHelper.FORDHED_IS_SYNCED, "0");
                values.put(dbHelper.FORDHED_IS_ACTIVE, ordHed.getFORDHED_IS_ACTIVE());
                values.put(dbHelper.FORDHED_PAYMENT_TYPE, ordHed.getFORDHED_PAYMENT_TYPE());

                int cn = cursor.getCount();
                if (cn > 0) {
                    count = dB.update(dbHelper.TABLE_FORDHED, values, dbHelper.FORDHED_REFNO + " =?",
                            new String[] { String.valueOf(ordHed.getFORDHED_REFNO()) });
                } else {
                    count = (int) dB.insert(dbHelper.TABLE_FORDHED, null, values);
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
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/


    @SuppressWarnings("static-access")
    public int restData(String refno) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDHED + " WHERE " + dbHelper.FORDHED_REFNO + " = '"
                    + refno + "'";
            cursor = dB.rawQuery(selectQuery, null);
            int cn = cursor.getCount();

            if (cn > 0) {
                int success = dB.delete(dbHelper.TABLE_FORDHED, dbHelper.FORDHED_REFNO + " ='" + refno + "'", null);
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

    @SuppressWarnings("static-access")
    public int InactiveStatusUpdate(String refno) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDHED + " WHERE " + dbHelper.FORDHED_REFNO + " = '"
                    + refno + "'";

            cursor = dB.rawQuery(selectQuery, null);

            ContentValues values = new ContentValues();

            values.put(dbHelper.FORDHED_IS_ACTIVE, "0");

            int cn = cursor.getCount();

            if (cn > 0) {
                count = dB.update(dbHelper.TABLE_FORDHED, values, dbHelper.FORDHED_REFNO + " =?",
                        new String[] { String.valueOf(refno) });
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

    @SuppressWarnings("static-access")
    public int updateIsSynced(PreSalesMapper mapper) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {
            ContentValues values = new ContentValues();
            String UploadDate = "";
            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            UploadDate = sdf.format(cal.getTime());

            values.put(dbHelper.FORDHED_IS_SYNCED, "1");
            values.put(dbHelper.FORDHED_UPLOAD_TIME, UploadDate);

            if (mapper.isSynced()) {
                count = dB.update(dbHelper.TABLE_FORDHED, values, dbHelper.FORDHED_REFNO + " =?",
                        new String[] { String.valueOf(mapper.getFORDHED_REFNO()) });
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
    public int updateIssueSynced(PreSalesMapper mapper) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {
            ContentValues values = new ContentValues();
            String UploadDate = "";
            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            UploadDate = sdf.format(cal.getTime());

            values.put(dbHelper.FMISS_IS_SYNC, "1");


            if (mapper.isSynced()) {
                count = dB.update(dbHelper.TABLE_FMISS_HED, values, dbHelper.FMISS_ORD_REFNO + " =?",
                        new String[] { String.valueOf(mapper.getFORDHED_REFNO()) });
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
    public ArrayList<OrdHed> getAllActiveOrdHed(String refno) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<OrdHed> list = new ArrayList<OrdHed>();

        @SuppressWarnings("static-access")
        String selectQuery = "select * from " + dbHelper.TABLE_FORDHED + " Where " + dbHelper.FORDHED_IS_ACTIVE
                + "='1' and " + dbHelper.FORDHED_REFNO + "='" + refno + "' and " + dbHelper.FORDHED_IS_SYNCED + "='0'";

        Cursor cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            OrdHed ordHed = new OrdHed();

            ordHed.setFORDHED_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ID)));
            ordHed.setFORDHED_REFNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REFNO)));
            ordHed.setFORDHED_ADD_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADD_DATE)));
            ordHed.setFORDHED_ADD_MACH(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADD_MACH)));
            ordHed.setFORDHED_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADD_USER)));
            ordHed.setFORDHED_APP_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_APP_DATE)));
            ordHed.setFORDHED_APPSTS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_APPSTS)));
            ordHed.setFORDHED_APP_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_APP_USER)));
            ordHed.setFORDHED_BP_TOTAL_DIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_BP_TOTAL_DIS)));
            ordHed.setFORDHED_B_TOTAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_B_TOTAL_AMT)));
            ordHed.setFORDHED_B_TOTAL_DIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_B_TOTAL_DIS)));
            ordHed.setFORDHED_B_TOTAL_TAX(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_B_TOTAL_TAX)));
            ordHed.setFORDHED_COST_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_COST_CODE)));
            ordHed.setFORDHED_CUR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_CUR_CODE)));
            ordHed.setFORDHED_CUR_RATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_CUR_RATE)));
            ordHed.setFORDHED_DEB_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DEB_CODE)));
            ordHed.setFORDHED_DIS_PER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DIS_PER)));
            ordHed.setFORDHED_START_TIME_SO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_START_TIME_SO)));
            ordHed.setFORDHED_END_TIME_SO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_END_TIME_SO)));
            ordHed.setFORDHED_LONGITUDE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_LONGITUDE)));
            ordHed.setFORDHED_LATITUDE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_LATITUDE)));
            ordHed.setFORDHED_LOC_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_LOC_CODE)));
            ordHed.setFORDHED_MANU_REF(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_MANU_REF)));
            ordHed.setFORDHED_RECORD_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_RECORD_ID)));
            ordHed.setFORDHED_REMARKS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REMARKS)));
            ordHed.setFORDHED_REPCODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REPCODE)));
            ordHed.setFORDHED_TAX_REG(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TAX_REG)));
            ordHed.setFORDHED_TIMESTAMP_COLUMN(
                    cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TIMESTAMP_COLUMN)));
            ordHed.setFORDHED_TOTAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_AMT)));
            ordHed.setFORDHED_TOTALDIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTALDIS)));
            ordHed.setFORDHED_TOTAL_TAX(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_TAX)));
            ordHed.setFORDHED_TXN_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TXN_TYPE)));
            ordHed.setFORDHED_TXN_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TXN_DATE)));
            ordHed.setFORDHED_ADDRESS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADDRESS)));
            ordHed.setFORDHED_TOTAL_ITM_DIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_ITM_DIS)));
            ordHed.setFORDHED_TOT_MKR_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOT_MKR_AMT)));
            ordHed.setFORDHED_IS_SYNCED(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_IS_SYNCED)));
            ordHed.setFORDHED_IS_ACTIVE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_IS_ACTIVE)));
            ordHed.setFORDHED_DELV_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DELV_DATE)));
            ordHed.setFORDHED_ROUTE_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ROUTE_CODE)));

            list.add(ordHed);

        }

        return list;
    }

    public ArrayList<OrdHed> getAllUnsyncedOrdHed(String newText) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        ArrayList<OrdHed> list = new ArrayList<OrdHed>();
        Cursor cursor = null;
        try {

            @SuppressWarnings("static-access")
            String selectQuery = "select * from fordHed sa, fdebtor cu where sa.isActive='0' and sa.DebCode=cu.DebCode and cu.DebName  like '"+ newText + "%' Order By sa.isSynced,sa.RefNo desc";

            cursor = dB.rawQuery(selectQuery, null);

            while (cursor.moveToNext()) {

                OrdHed ordHed = new OrdHed();

                ordHed.setFORDHED_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ID)));
                ordHed.setFORDHED_REFNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REFNO)));
                ordHed.setFORDHED_ADD_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADD_DATE)));
                ordHed.setFORDHED_ADD_MACH(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADD_MACH)));
                ordHed.setFORDHED_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADD_USER)));
                ordHed.setFORDHED_APP_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_APP_DATE)));
                ordHed.setFORDHED_APPSTS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_APPSTS)));
                ordHed.setFORDHED_APP_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_APP_USER)));
                ordHed.setFORDHED_BP_TOTAL_DIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_BP_TOTAL_DIS)));
                ordHed.setFORDHED_B_TOTAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_B_TOTAL_AMT)));
                ordHed.setFORDHED_B_TOTAL_DIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_B_TOTAL_DIS)));
                ordHed.setFORDHED_B_TOTAL_TAX(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_B_TOTAL_TAX)));
                ordHed.setFORDHED_COST_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_COST_CODE)));
                ordHed.setFORDHED_CUR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_CUR_CODE)));
                ordHed.setFORDHED_CUR_RATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_CUR_RATE)));
                ordHed.setFORDHED_DEB_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DEB_CODE)));
                ordHed.setFORDHED_DIS_PER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DIS_PER)));
                ordHed.setFORDHED_START_TIME_SO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_START_TIME_SO)));
                ordHed.setFORDHED_END_TIME_SO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_END_TIME_SO)));
                ordHed.setFORDHED_LONGITUDE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_LONGITUDE)));
                ordHed.setFORDHED_LATITUDE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_LATITUDE)));
                ordHed.setFORDHED_LOC_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_LOC_CODE)));
                ordHed.setFORDHED_MANU_REF(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_MANU_REF)));
                ordHed.setFORDHED_RECORD_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_RECORD_ID)));
                ordHed.setFORDHED_REMARKS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REMARKS)));
                ordHed.setFORDHED_REPCODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REPCODE)));
                ordHed.setFORDHED_TAX_REG(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TAX_REG)));
                ordHed.setFORDHED_TIMESTAMP_COLUMN(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TIMESTAMP_COLUMN)));
                ordHed.setFORDHED_TOTAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_AMT)));
                ordHed.setFORDHED_TOTALDIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTALDIS)));
                ordHed.setFORDHED_TOTAL_TAX(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_TAX)));
                ordHed.setFORDHED_TXN_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TXN_TYPE)));
                ordHed.setFORDHED_TXN_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TXN_DATE)));
                ordHed.setFORDHED_ADDRESS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADDRESS)));
                ordHed.setFORDHED_TOTAL_ITM_DIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_ITM_DIS)));
                ordHed.setFORDHED_TOT_MKR_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOT_MKR_AMT)));
                ordHed.setFORDHED_IS_SYNCED(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_IS_SYNCED)));
                ordHed.setFORDHED_IS_ACTIVE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_IS_ACTIVE)));
                ordHed.setFORDHED_DELV_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DELV_DATE)));
                ordHed.setFORDHED_ROUTE_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ROUTE_CODE)));
                ordHed.setFORDHED_UPLOAD_TIME(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_UPLOAD_TIME)));

                Log.v("llooolllooollloolllooll", cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ROUTE_CODE)));

                list.add(ordHed);

            }

        } catch (Exception e) {
            // TODO: handle exception

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }
        return list;
    }

    @SuppressWarnings("static-access")
    public ArrayList<PreSalesMapper> getAllUnSyncOrdHed() {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<PreSalesMapper> list = new ArrayList<PreSalesMapper>();

        @SuppressWarnings("static-access")
        String selectQuery = "select * from " + dbHelper.TABLE_FORDHED + " Where " + dbHelper.FORDHED_IS_ACTIVE
                + "='0' and " + dbHelper.FORDHED_IS_SYNCED + "='0'";

        Cursor cursor = dB.rawQuery(selectQuery, null);

        localSP = context.getSharedPreferences(SETTINGS, Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);

        while (cursor.moveToNext()) {

            PreSalesMapper preSalesMapper = new PreSalesMapper();
            OrdDetDS detDS = new OrdDetDS(context);
            FmisshedDS issueDS = new FmisshedDS(context);
            SalRepDS repDS = new SalRepDS(context);

            CompanyBranchDS branchDS = new CompanyBranchDS(context);
            preSalesMapper
                    .setNextNumVal(branchDS.getCurrentNextNumVal(context.getResources().getString(R.string.NumVal)));

            preSalesMapper.setDistDB(localSP.getString("Dist_DB", "").toString());
            preSalesMapper.setConsoleDB(localSP.getString("Console_DB", "").toString());
            preSalesMapper.setSALEREP_DEALCODE(repDS.getDealCode());
            preSalesMapper.setSALEREP_AREACODE(repDS.getAreaCode());
            preSalesMapper.setFORDHED_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ID)));
            preSalesMapper.setFORDHED_REFNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REFNO)));
            preSalesMapper.setFORDHED_ADD_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADD_DATE)));
            preSalesMapper.setFORDHED_ADD_MACH(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADD_MACH)));
            preSalesMapper.setFORDHED_ADD_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADD_USER)));
            preSalesMapper.setFORDHED_APP_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_APP_DATE)));
            preSalesMapper.setFORDHED_APPSTS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_APPSTS)));
            preSalesMapper.setFORDHED_APP_USER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_APP_USER)));
            preSalesMapper
                    .setFORDHED_BP_TOTAL_DIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_BP_TOTAL_DIS)));
            preSalesMapper
                    .setFORDHED_B_TOTAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_B_TOTAL_AMT)));
            preSalesMapper
                    .setFORDHED_B_TOTAL_DIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_B_TOTAL_DIS)));
            preSalesMapper
                    .setFORDHED_B_TOTAL_TAX(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_B_TOTAL_TAX)));
            preSalesMapper.setFORDHED_COST_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_COST_CODE)));
            preSalesMapper.setFORDHED_CUR_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_CUR_CODE)));
            preSalesMapper.setFORDHED_CUR_RATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_CUR_RATE)));
            preSalesMapper.setFORDHED_DEB_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DEB_CODE)));
            preSalesMapper.setFORDHED_DIS_PER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DIS_PER)));
            preSalesMapper
                    .setFORDHED_START_TIME_SO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_START_TIME_SO)));
            preSalesMapper
                    .setFORDHED_END_TIME_SO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_END_TIME_SO)));
            preSalesMapper.setFORDHED_LONGITUDE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_LONGITUDE)));
            preSalesMapper.setFORDHED_LATITUDE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_LATITUDE)));
            preSalesMapper.setFORDHED_LOC_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_LOC_CODE)));
            preSalesMapper.setFORDHED_MANU_REF(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_MANU_REF)));
            preSalesMapper.setFORDHED_RECORD_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_RECORD_ID)));
            preSalesMapper.setFORDHED_REMARKS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REMARKS)));
            preSalesMapper.setFORDHED_REPCODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REPCODE)));
            preSalesMapper.setFORDHED_TAX_REG(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TAX_REG)));
            preSalesMapper.setFORDHED_TIMESTAMP_COLUMN(
                    cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TIMESTAMP_COLUMN)));
            preSalesMapper.setFORDHED_TOTAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_AMT)));
            preSalesMapper.setFORDHED_TOTALDIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTALDIS)));
            preSalesMapper.setFORDHED_TOTAL_TAX(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_TAX)));
            preSalesMapper.setFORDHED_TXN_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TXN_TYPE)));
            preSalesMapper.setFORDHED_TXN_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TXN_DATE)));
            preSalesMapper.setFORDHED_ADDRESS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ADDRESS)));
            preSalesMapper
                    .setFORDHED_TOTAL_ITM_DIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_ITM_DIS)));
            preSalesMapper
                    .setFORDHED_TOT_MKR_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOT_MKR_AMT)));
            preSalesMapper.setFORDHED_IS_SYNCED(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_IS_SYNCED)));
            preSalesMapper.setFORDHED_IS_ACTIVE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_IS_ACTIVE)));
            preSalesMapper.setFORDHED_DELV_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DELV_DATE)));
            preSalesMapper.setFORDHED_ROUTE_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_ROUTE_CODE)));
            preSalesMapper
                    .setFORDHED_HED_DIS_VAL(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_HED_DIS_VAL)));
            preSalesMapper.setFORDHED_HED_DIS_PER_VAL(
                    cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_HED_DIS_PER_VAL)));
            preSalesMapper
                    .setFORDHED_PAYMENT_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_PAYMENT_TYPE)));
            preSalesMapper
                    .setOrdDet(detDS.getAllUnSync(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REFNO))));
            preSalesMapper.setIssuList(
                    issueDS.getActiveIssues(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DEB_CODE))));

            list.add(preSalesMapper);

        }

        return list;
    }

    public String getRefnoByDebcode(String refno) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDHED + " WHERE " + dbHelper.FORDHED_REFNO + "='"
                + refno + "'";

        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            return cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DEB_CODE));

        }
        return "";

    }

    @SuppressWarnings("static-access")
    public int DeleteOldOrders(String DateFrom, String DateTo) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDHED + " WHERE " +dbHelper.FORDHED_TXN_DATE + " BETWEEN '"+ DateFrom + "' AND '" + DateTo + "' AND " + dbHelper.FORDHED_IS_ACTIVE + "= '0' AND " + dbHelper.FORDHED_IS_SYNCED + " ='1' " ;
            cursor = dB.rawQuery(selectQuery, null);
            int cn = cursor.getCount();

            if (cn > 0) {
                int success = dB.delete(dbHelper.TABLE_FORDHED, dbHelper.FORDHED_TXN_DATE + " BETWEEN '"+ DateFrom + "' AND '" + DateTo + "' AND " + dbHelper.FORDHED_IS_ACTIVE + "= '0' AND " + dbHelper.FORDHED_IS_SYNCED + " ='1' ", null);
                count = success;
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

    public String getRefnoToDelete(String refno) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDHED + " WHERE " + dbHelper.FORDHED_REFNO + "='"
                + refno + "'";

        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            return cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_IS_SYNCED));

        }

        return "";

    }
}
