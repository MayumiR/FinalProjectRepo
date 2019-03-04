package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.Fmisshed;
import com.bit.sfa.Models.PreSalesMapper;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Rashmi on 7/26/2018.
 */

public class FmisshedDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "FmisshedDS";

    public static SharedPreferences localSP;

    public FmisshedDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public long InsertFmisshed(ArrayList<Fmisshed> fmissheds) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {
            dB.beginTransaction();
            String sql = "Insert or Replace into " + dbHelper.TABLE_FMISS_HED + " (" + dbHelper.FMISS_REFNO + ", "
                    + dbHelper.FMISS_TXNDATE + ","
                    + dbHelper.FMISS_MANUREF + ","
                    + dbHelper.FMISS_COSTCODE + ","
                    + dbHelper.FMISS_LOCCODE + ","
                    + dbHelper.FMISS_DEBCODEM + ","
                    + dbHelper.FMISS_REPCODEM + ","
                    + dbHelper.FMISS_REMARKS + ","
                    + dbHelper.FMISS_TXNTYPE + ","
                    + dbHelper.FMISS_TOTALAMT + ","
                    + dbHelper.FMISS_GITYPE + ","
                    + dbHelper.FMISS_GITYPES + ","
                    + dbHelper.FMISS_PRTCOPY + ","
                    + dbHelper.FMISS_GIPOST + ","
                    + dbHelper.FMISS_GIBATCH + ","
                    + dbHelper.FMISS_ADDUSER + ","
                    + dbHelper.FMISS_ADD_DATE + ","
                    + dbHelper.FMISS_ADD_MACH + ","
                    + dbHelper.FMISS_RECORDID +") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            SQLiteStatement insert = dB.compileStatement(sql);

            for (Fmisshed fmisshed : fmissheds) {

                insert.bindString(1, fmisshed.getRefNo());
                insert.bindString(2, fmisshed.getTxnDate());
                insert.bindString(3, fmisshed.getManuRef());
                insert.bindString(4, fmisshed.getCostCode());
                insert.bindString(5, fmisshed.getLocCode());
                insert.bindString(6, fmisshed.getDebCodeM());
                insert.bindString(7, fmisshed.getRepCodeM());
                insert.bindString(8, fmisshed.getRemarks());
                insert.bindString(9, fmisshed.getTxnType());
                insert.bindString(10, fmisshed.getTotalAmt());
                insert.bindString(11, fmisshed.getGIType());
                insert.bindString(12, fmisshed.getGITypeS());
                insert.bindString(13, fmisshed.getPrtcopy());
                insert.bindString(14, fmisshed.getGlPost());
                insert.bindString(15, fmisshed.getGlBatch());
                insert.bindString(16, fmisshed.getAddUser());
                insert.bindString(17, fmisshed.getTxnDate());
                insert.bindString(18, fmisshed.getAddMach());
                insert.bindString(19, fmisshed.getRecordId());

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

// load when image icon click, it can be first time or second time or...etc...
    public ArrayList<Fmisshed> getIssuesByDebCode(String DebCode) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<Fmisshed> list = new ArrayList<Fmisshed>();

        String selectQuery = "SELECT det."+DatabaseHelper.FMISS_DET_GITEMCODE+" as giftItem, hed."+DatabaseHelper.FMISS_IS_ISSUE
                +" as status, hed."+DatabaseHelper.FMISS_REFNO+" as giftRef FROM "
                + DatabaseHelper.TABLE_FMISS_HED+" as hed, "+DatabaseHelper.TABLE_FMISS_DET+" as det where hed."
                +DatabaseHelper.FMISS_DEBCODEM+" = '"+DebCode+"' and hed."
                +DatabaseHelper.FMISS_REFNO+" = det."+DatabaseHelper.FMISS_DET_REFNO
                +" and hed."+DatabaseHelper.FMISS_IS_SYNC+" = '0' ";;//<> '2' or is sync = 0
        Cursor cursor = dB.rawQuery(selectQuery, null);
        try {
            while (cursor.moveToNext()) {
                Fmisshed issue = new Fmisshed();
                issue.setIsIssue(cursor.getString(cursor.getColumnIndex("status")));
                issue.setRemarks(cursor.getString(cursor.getColumnIndex("giftItem")));
                issue.setRefNo(cursor.getString(cursor.getColumnIndex("giftRef")));
                list.add(issue);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return list;
    }
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    // for sync data
    public ArrayList<Fmisshed> getActiveIssues(String DebCode) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<Fmisshed> list = new ArrayList<Fmisshed>();

        String selectQuery = "SELECT det."+DatabaseHelper.FMISS_DET_GITEMCODE+" as giftItem, hed."
                +DatabaseHelper.FMISS_IS_ISSUE+" as status, hed."+DatabaseHelper.FMISS_REFNO
                +" as giftRef FROM " + DatabaseHelper.TABLE_FMISS_HED+" as hed, "
                +DatabaseHelper.TABLE_FMISS_DET+" as det where hed."+DatabaseHelper.FMISS_DEBCODEM+" = '"+DebCode+"' and hed."

                +DatabaseHelper.FMISS_REFNO+" = det."+DatabaseHelper.FMISS_DET_REFNO
                +" and hed."+DatabaseHelper.FMISS_IS_ISSUE+" = '2' "
                +" and hed."+DatabaseHelper.FMISS_IS_SYNC+" = '0' ";
        Cursor cursor = dB.rawQuery(selectQuery, null);
        try {
            while (cursor.moveToNext()) {
                Fmisshed issue = new Fmisshed();
                issue.setIsIssue(cursor.getString(cursor.getColumnIndex("status")));
                issue.setRemarks(cursor.getString(cursor.getColumnIndex("giftItem")));
                issue.setRefNo(cursor.getString(cursor.getColumnIndex("giftRef")));
                list.add(issue);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return list;
    }
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    // for when click upload if not apply giftitems, check gift has or not
    public String checkIssuedByDebCode(String DebCode) {

        int count = 0, issueCount = 0, giftCount = 0;
        Cursor cursor = null;
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT count(hed.IsIssue) as IssueCount, count(det.RefNo) as giftCount FROM " + DatabaseHelper.TABLE_FMISS_HED+" as hed, "+DatabaseHelper.TABLE_FMISS_DET+" as det where hed."
                +DatabaseHelper.FMISS_DEBCODEM+" = '"+DebCode+"' and hed."+DatabaseHelper.FMISS_REFNO+" = det."+DatabaseHelper.FMISS_DET_REFNO;
        cursor = dB.rawQuery(selectQuery, null);
        try {

            count = cursor.getCount();

            while (cursor.moveToNext()){
                issueCount = cursor.getInt(cursor.getColumnIndex("IssueCount"));
                giftCount = cursor.getInt(cursor.getColumnIndex("giftCount"));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return issueCount+"-"+giftCount;
    }
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    // save occation
    public int InactiveStatusUpdate(String refno) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_FMISS_HED + " WHERE " + DatabaseHelper.FMISS_ORD_REFNO + " = '" + refno + "'";

            cursor = dB.rawQuery(selectQuery, null);

            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.FMISS_IS_ISSUE, "2");

            int cn = cursor.getCount();

            if (cn > 0) {
                count = dB.update(DatabaseHelper.TABLE_FMISS_HED, values, DatabaseHelper.FMISS_ORD_REFNO + " =?", new String[] { String.valueOf(refno) });
            }


        } catch (Exception e) {

            Log.v(TAG + "Excption", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }
        return count;

    }
    // for showGiftItems when swapping on view pager
    public ArrayList<Fmisshed> getIssuesByRefNo(String RefNo) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<Fmisshed> list = new ArrayList<Fmisshed>();
        String selectQuery = "SELECT det."+DatabaseHelper.FMISS_DET_GITEMCODE+" as giftItem, hed."+DatabaseHelper.FMISS_IS_ISSUE
                +" as status, hed."+DatabaseHelper.FMISS_REFNO+" as giftRef FROM  " + DatabaseHelper.TABLE_FMISS_HED+" as hed, "
         //       +DatabaseHelper.TABLE_FMISS_DET+" as det where hed."+DatabaseHelper.FMISS_ORD_REFNO+" = '"+RefNo+"' and hed."
                +DatabaseHelper.TABLE_FMISS_DET+" as det where hed."+DatabaseHelper.FMISS_ORD_REFNO+" = '"+RefNo+"' and hed."
                +DatabaseHelper.FMISS_IS_ISSUE+" = '1' and hed."
                +DatabaseHelper.FMISS_IS_SYNC+" = '0' and hed."
                +DatabaseHelper.FMISS_REFNO+" = det."+DatabaseHelper.FMISS_DET_REFNO;
        // String selectQuery = "SELECT * from "+DatabaseHelper.TABLE_FMISS_HED+" where "+DatabaseHelper.FMISS_ORD_REFNO+" = '" + RefNo + "' and "+DatabaseHelper.FMISS_IS_ISSUE+" = '1'";
        // String selectQuery = "SELECT * from "+DatabaseHelper.TABLE_FMISS_HED+" where "+DatabaseHelper.FMISS_IS_ISSUE+" = '1' ";
        Cursor cursor = dB.rawQuery(selectQuery, null);
        try {
            while (cursor.moveToNext()) {
                Fmisshed issue = new Fmisshed();
                issue.setIsIssue(cursor.getString(cursor.getColumnIndex("status")));
                issue.setRemarks(cursor.getString(cursor.getColumnIndex("giftItem")));
                issue.setRefNo(cursor.getString(cursor.getColumnIndex("giftRef")));
                list.add(issue);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return list;
    }
    // update in checkboxclick whether it checked or not
    public void updateIssueFlag(String refno, String status) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        try {

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.FMISS_IS_ISSUE, status);
            dB.update(DatabaseHelper.TABLE_FMISS_HED, values, DatabaseHelper.FMISS_REFNO + " =?", new String[]{String.valueOf(refno)});

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dB.close();
        }
    }
    // update when sync complete
    public void updateIssueSync(PreSalesMapper mapper) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        try {

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.FMISS_IS_SYNC, "1");
            dB.update(DatabaseHelper.TABLE_FMISS_HED, values, DatabaseHelper.FMISS_ORD_REFNO + " =?", new String[]{String.valueOf(mapper.getFORDHED_REFNO())});

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dB.close();
        }
    }
    // update fmisshed using order ref no when issue gift items
    public void updateOrdRefNo(String refno, String orderRef) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        try {

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.FMISS_ORD_REFNO, orderRef);
            dB.update(DatabaseHelper.TABLE_FMISS_HED, values, DatabaseHelper.FMISS_REFNO + " =?", new String[]{String.valueOf(refno)});

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dB.close();
        }
    }
}
