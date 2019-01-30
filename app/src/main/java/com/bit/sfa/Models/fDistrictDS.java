package com.bit.sfa.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Dhanushika on 4/4/2018.
 */

public class fDistrictDS {
    Context context;
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    private String TAG = "fDistrictDS";

    public fDistrictDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public int createOrUpdatefDistrict(ArrayList<fDistrict> list) {
        int count = 0;
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            for (fDistrict fDistrict : list) {

                cursor = dB.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FDISTRICT + " WHERE " + DatabaseHelper.FDISTRICT_CODE + "='" + fDistrict.getDISTRICT_CODE() + "' AND " + DatabaseHelper.FDISTRICT_NAME + "='" + fDistrict.getDISTRICT_NAME() +  "'", null);

                ContentValues values = new ContentValues();

                values.put(dbHelper.FDISTRICT_CODE, fDistrict.getDISTRICT_CODE());
                values.put(dbHelper.FDISTRICT_NAME, fDistrict.getDISTRICT_NAME());
                values.put(dbHelper.FDISTRICT_PROVECODE, fDistrict.getDISTRICT_PROVECODE());


                if (cursor.getCount() > 0) {
                    dB.update(DatabaseHelper.TABLE_FDISTRICT, values, DatabaseHelper.FDISTRICT_CODE + "=? AND " + DatabaseHelper.FDISTRICT_NAME , new String[]{fDistrict.getDISTRICT_CODE().toString(), fDistrict.getDISTRICT_NAME().toString()});

                } else {
                    count = (int) dB.insert(dbHelper.TABLE_FDISTRICT, null, values);
                    Log.v("TABLE_FDISTRICT : ***", "Inserted " + count);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }
        return count;
    }
    public ArrayList<fDistrict> getDistrict(String newText)  {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<fDistrict> list = new ArrayList<fDistrict>();

        String selectQuery = "select * from fDistrict where DistrCode Like '%" + newText + "%' or DistrName Like '%" + newText + "%'";
        Cursor cursor=null;
        try {
            cursor = dB.rawQuery(selectQuery, null);
            while (cursor.moveToNext()) {

                fDistrict fDistrict = new fDistrict();

                fDistrict.setDISTRICT_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FDISTRICT_CODE)));
                fDistrict.setDISTRICT_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDISTRICT_NAME)));


                list.add(fDistrict);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public String getCode(String Name) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        String Code = null;
        Cursor cursor = null;
        try {


            String selectQuery = "select * from fDistrict where DistrName = '" + Name.trim() + "'";

            cursor = dB.rawQuery(selectQuery, null);

            while (cursor.moveToNext()) {

                Code = cursor.getString(cursor.getColumnIndex(dbHelper.FDISTRICT_CODE));

            }
        } catch (Exception e) {
          e.printStackTrace();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }
        return Code;
    }
    public String getName(String code) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        String Code = null;
        Cursor cursor = null;
        try {


            String selectQuery = "select * from fDistrict where DistrCode = '" + code.trim() + "'";

            cursor = dB.rawQuery(selectQuery, null);

            while (cursor.moveToNext()) {

                Code = cursor.getString(cursor.getColumnIndex(dbHelper.FDISTRICT_NAME));

            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }
        return Code;
    }

    	/*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*/

    public void deleteAll() {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        dB.delete(DatabaseHelper.TABLE_FDISTRICT, null, null);
        dB.close();
    }
    //------------------------------------------------------------------------------------------------------------------------



}
