package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.Route;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class RouteDS {

    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;

    private String TAG = "KFD";

    public RouteDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }


    public String getTodayRoute(String month, int year, String repcode, String date) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String Rcode = null;

        String selectQuery = "SELECT RouteCode from " + dbHelper.TABLE_FITENRDET + " where " + dbHelper.FITENRDET_REFNO + " in (select distinct " + dbHelper.FITENRHED_REF_NO + " from " + dbHelper.TABLE_FITENRHED + " where RepCode='" + repcode + "' AND Year='" + year + "' AND Month='" + month + "') "; //AND TxnDate='" + date + "'
        System.out.println(TAG + selectQuery);
        Cursor cursor = null;

        cursor = dB.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0)
            while (cursor.moveToNext()) {
                Rcode = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FITENRDET_ROUTE));
            }
        return Rcode;


    }
    public String getRouteFromItenary(String date) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String Rcode = null;

        String selectQuery = "SELECT RouteCode from " + dbHelper.TABLE_FITENRDET + " where " + dbHelper.FITENRDET_TXNDATE +" ='" + date + "'";
        System.out.println(TAG + selectQuery);
        Cursor cursor = null;

        cursor = dB.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0)
            while (cursor.moveToNext()) {
                Rcode = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FITENRDET_ROUTE));
            }
        return Rcode;


    }
    //----------------------------getAllRoute---------------------------------------
    public ArrayList<Route> getRoute() {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<Route> list = new ArrayList<Route>();

        String selectQuery = "select * from fRoute";
        Cursor cursor = null;
        try {
            cursor = dB.rawQuery(selectQuery, null);
            while (cursor.moveToNext()) {
                Route route = new Route();
                route.setFROUTE_ROUTECODE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FROUTE_ROUTECODE)));
                route.setFROUTE_ROUTE_NAME(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FROUTE_ROUTE_NAME)));

                list.add(route);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /*
     * insert code
     */
    @SuppressWarnings("static-access")
    public int createOrUpdateFRoute(ArrayList<Route> list) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        Cursor cursor = null;
        Cursor cursor_ini = null;

        try {

            cursor_ini = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FROUTE, null);

            for (Route route : list) {
                ContentValues values = new ContentValues();

                values.put(dbHelper.FROUTE_REPCODE, route.getFROUTE_REPCODE());
                values.put(dbHelper.FROUTE_ROUTECODE, route.getFROUTE_ROUTECODE());
                values.put(dbHelper.FROUTE_ROUTE_NAME, route.getFROUTE_ROUTE_NAME());
                values.put(dbHelper.FROUTE_RECORDID, route.getFROUTE_RECORDID());
                values.put(dbHelper.FROUTE_ADDDATE, route.getFROUTE_ADDDATE());
                values.put(dbHelper.FROUTE_ADD_MACH, route.getFROUTE_ADD_MACH());
                values.put(dbHelper.FROUTE_ADD_USER, route.getFROUTE_ADD_USER());
                values.put(dbHelper.FROUTE_AREACODE, route.getFROUTE_AREACODE());
                values.put(dbHelper.FROUTE_DEALCODE, route.getFROUTE_DEALCODE());
                values.put(dbHelper.FROUTE_FREQNO, route.getFROUTE_FREQNO());
                values.put(dbHelper.FROUTE_KM, route.getFROUTE_KM());
                values.put(dbHelper.FROUTE_MINPROCALL, route.getFROUTE_MINPROCALL());
                values.put(dbHelper.FROUTE_RDALORATE, route.getFROUTE_RDALORATE());
                values.put(dbHelper.FROUTE_RDTARGET, route.getFROUTE_RDTARGET());
                values.put(dbHelper.FROUTE_REMARKS, route.getFROUTE_REMARKS());
                values.put(dbHelper.FROUTE_STATUS, route.getFROUTE_STATUS());
                values.put(dbHelper.FROUTE_TONNAGE, route.getFROUTE_TONNAGE());

                if (cursor_ini.moveToFirst()) {
                    String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FROUTE + " WHERE " + dbHelper.FROUTE_ROUTECODE + "='" + route.getFROUTE_ROUTECODE() + "'";
                    cursor = dB.rawQuery(selectQuery, null);

                    if (cursor.moveToFirst()) {
                        count = (int) dB.update(dbHelper.TABLE_FROUTE, values, dbHelper.FROUTE_ROUTECODE + "='" + route.getFROUTE_ROUTECODE() + "'", null);
                    } else {
                        count = (int) dB.insert(dbHelper.TABLE_FROUTE, null, values);
                    }

                } else {
                    count = (int) dB.insert(dbHelper.TABLE_FROUTE, null, values);
                }

            }
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }

            if (cursor_ini != null) {
                cursor_ini.close();
            }
            dB.close();
        }
        return count;

    }

    /*
     * delete code
     */
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

            cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FROUTE, null);
            count = cursor.getCount();
            if (count > 0) {
                int success = dB.delete(dbHelper.TABLE_FROUTE, null, null);
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
    public ArrayList<String> getAllRouteByRepCode(String repCode) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<String> list = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FROUTE; // Menaka 12-02-2016+ " WHERE " + dbHelper.FROUTE_REPCODE + "='" + repCode + "'";

        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            list.add(cursor.getString(cursor.getColumnIndex(dbHelper.FROUTE_ROUTE_NAME)));

        }

        return list;
    }

    @SuppressWarnings("static-access")
    public String getRouteCodeByRoute(String route) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FROUTE + " WHERE " + dbHelper.FROUTE_ROUTE_NAME + "='" + route + "'";

        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            return cursor.getString(cursor.getColumnIndex(dbHelper.FROUTE_ROUTECODE));

        }

        return "";
    }
    @SuppressWarnings("static-access")
    public String getRouteNameByRCode(String route) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FROUTE + " WHERE " + dbHelper.FROUTEDET_ROUTE_CODE + "='" + route + "'";

        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            return cursor.getString(cursor.getColumnIndex(dbHelper.FROUTE_ROUTE_NAME));

        }

        return "";
    }
    @SuppressWarnings("static-access")
    public String getRouteNameByCode(String code) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FROUTE + " R, " + dbHelper.TABLE_FROUTEDET + " RD " + "WHERE R." + dbHelper.FROUTE_ROUTECODE + "=RD." + dbHelper.FROUTEDET_ROUTE_CODE + " AND RD." + dbHelper.FROUTEDET_DEB_CODE + "='" + code + "'";

        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            return cursor.getString(cursor.getColumnIndex(dbHelper.FROUTE_ROUTE_NAME));

        }

        return "";
    }

    @SuppressWarnings("static-access")
    public String getRouteNameByRouteCode(String rCode) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FROUTE + " R, " + dbHelper.TABLE_FROUTEDET + " RD " + "WHERE R." + dbHelper.FROUTE_ROUTECODE + "=RD." + dbHelper.FROUTEDET_ROUTE_CODE + " AND RD." + dbHelper.FROUTE_ROUTECODE + "='" + rCode + "'";

        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            return cursor.getString(cursor.getColumnIndex(dbHelper.FROUTE_ROUTE_NAME));

        }

        return "";
    }
}
