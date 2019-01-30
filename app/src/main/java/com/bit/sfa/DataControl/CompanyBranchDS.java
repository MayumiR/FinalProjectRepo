package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.CompanyBranch;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Sathiyaraja on 6/20/2018.
 */

public class CompanyBranchDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG="kandanafd";


    public CompanyBranchDS (Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    @SuppressWarnings("static-access")
    public int createOrUpdateFCompanyBranch(ArrayList<CompanyBranch> list) {
        int count =0;
        if(dB == null){
            open();
        }else if(!dB.isOpen()){
            open();
        }
        Cursor cursor = null;
        Cursor cursor_ini = null;
        try{

            cursor_ini = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FCOMPANYBRANCH, null);

            for (CompanyBranch branch : list) {

                ContentValues values = new ContentValues();
                values.put(dbHelper.FCOMPANYBRANCH_BRANCH_CODE,  branch.getFCOMPANYBRANCH_BRANCH_CODE());
                values.put(dbHelper.FCOMPANYBRANCH_RECORD_ID,  branch.getFCOMPANYBRANCH_RECORD_ID());
                values.put(dbHelper.FCOMPANYBRANCH_CSETTINGS_CODE,  branch.getFCOMPANYBRANCH_CSETTINGS_CODE());
                values.put(dbHelper.FCOMPANYBRANCH_NNUM_VAL,  branch.getFCOMPANYBRANCH_NNUM_VAL());
                values.put(dbHelper.FCOMPANYBRANCH_NYEAR_VAL,  branch.getFCOMPANYBRANCH_NYEAR_VAL());
                values.put(dbHelper.FCOMPANYBRANCH_NMONTH_VAL,  branch.getFCOMPANYBRANCH_NMONTH_VAL());

                if (cursor_ini.moveToFirst()) {
                    String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FCOMPANYBRANCH + " WHERE " + dbHelper.FCOMPANYBRANCH_CSETTINGS_CODE + "='" + branch.getFCOMPANYBRANCH_CSETTINGS_CODE() + "' AND " + dbHelper.FCOMPANYBRANCH_BRANCH_CODE + "='" + branch.getFCOMPANYBRANCH_BRANCH_CODE() + "' AND " + dbHelper.FCOMPANYBRANCH_NYEAR_VAL + "='" + branch.getFCOMPANYBRANCH_NYEAR_VAL() + "' AND " + dbHelper.FCOMPANYBRANCH_NMONTH_VAL + "='" + branch.getFCOMPANYBRANCH_NMONTH_VAL() + "'";
                    cursor = dB.rawQuery(selectQuery, null);

                    if (cursor.moveToFirst()) {
                        count = (int) dB.update(dbHelper.TABLE_FCOMPANYBRANCH, values, dbHelper.FCOMPANYBRANCH_CSETTINGS_CODE + "='" + branch.getFCOMPANYBRANCH_CSETTINGS_CODE() + "' AND " + dbHelper.FCOMPANYBRANCH_BRANCH_CODE + "='" + branch.getFCOMPANYBRANCH_BRANCH_CODE() + "' AND " + dbHelper.FCOMPANYBRANCH_NYEAR_VAL + "='" + branch.getFCOMPANYBRANCH_NYEAR_VAL() + "' AND " + dbHelper.FCOMPANYBRANCH_NMONTH_VAL + "='" + branch.getFCOMPANYBRANCH_NMONTH_VAL() + "'", null);
                    } else {
                        count = (int) dB.insert(dbHelper.TABLE_FCOMPANYBRANCH, null, values);
                    }

                } else {
                    count = (int) dB.insert(dbHelper.TABLE_FCOMPANYBRANCH, null, values);
                }

            }
        }finally {
            if (cursor !=null) {
                cursor.close();
            }

            if (cursor_ini !=null) {
                cursor_ini.close();
            }
            dB.close();
        }
        return count;

    }

    @SuppressWarnings("static-access")
    public String getCurrentNextNumVal(String cSettingsCode ){

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        Calendar c = Calendar.getInstance();

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FCOMPANYBRANCH +" WHERE "+dbHelper.FCOMPANYBRANCH_CSETTINGS_CODE+" ='"+cSettingsCode + "' AND nYear='" + String.valueOf(c.get(Calendar.YEAR)) + "' AND nMonth='" + String.valueOf(c.get(Calendar.MONTH) + 1) + "'";

        Cursor cursor = dB.rawQuery(selectQuery, null);

        while(cursor.moveToNext()){

            return cursor.getString(cursor.getColumnIndex(dbHelper.FCOMPANYBRANCH_NNUM_VAL));

        }

        return "0";
    }

    @SuppressWarnings("static-access")
    public int deleteAll(){

        int count =0;

        if(dB == null){
            open();
        }else if(!dB.isOpen()){
            open();
        }
        Cursor cursor = null;
        try{

            cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FCOMPANYBRANCH, null);
            count =cursor.getCount();
            if(count>0){
                int success = dB.delete(dbHelper.TABLE_FCOMPANYBRANCH, null, null);
                Log.v("Success", success+"");
            }
        }catch (Exception e){

            Log.v(TAG+" Exception", e.toString());

        }finally{
            if (cursor !=null) {
                cursor.close();
            }
            dB.close();
        }

        return count;

    }




}
