package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.FreeQohStatus;
import com.bit.sfa.Models.OrdDet;
import com.bit.sfa.Models.OrderDisc;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Rashmi on 6/20/2018.
 */

public class OrdDetDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG = "swadeshi OrdDet";

    public OrdDetDS(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {

        dB = dbHelper.getWritableDatabase();

    }

    @SuppressWarnings("static-access")
    public int createOrUpdateOrdDet(ArrayList<OrdDet> list) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        try {

            for (OrdDet ordDet : list) {

                Cursor cursor = null;
                ContentValues values = new ContentValues();

                String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_ID + " = '" + ordDet.getFORDDET_ID() + "'";

                cursor = dB.rawQuery(selectQuery, null);

                values.put(dbHelper.FORDDET_AMT, ordDet.getFORDDET_AMT());
                values.put(dbHelper.FORDDET_BAL_QTY, ordDet.getFORDDET_BAL_QTY());
                values.put(dbHelper.FORDDET_B_AMT, ordDet.getFORDDET_B_AMT());
                values.put(dbHelper.FORDDET_B_DIS_AMT, ordDet.getFORDDET_B_DIS_AMT());
                values.put(dbHelper.FORDDET_BP_DIS_AMT, ordDet.getFORDDET_BP_DIS_AMT());
                values.put(dbHelper.FORDDET_B_SELL_PRICE, ordDet.getFORDDET_B_SELL_PRICE());
                values.put(dbHelper.FORDDET_BT_TAX_AMT, ordDet.getFORDDET_BT_TAX_AMT());
                values.put(dbHelper.FORDDET_BT_SELL_PRICE, ordDet.getFORDDET_BT_SELL_PRICE());
                values.put(dbHelper.FORDDET_CASE, ordDet.getFORDDET_CASE());
                values.put(dbHelper.FORDDET_CASE_QTY, ordDet.getFORDDET_CASE_QTY());
                values.put(dbHelper.FORDDET_DIS_AMT, ordDet.getFORDDET_DIS_AMT());
                values.put(dbHelper.FORDDET_DIS_PER, ordDet.getFORDDET_DIS_PER());
                values.put(dbHelper.FORDDET_FREE_QTY, ordDet.getFORDDET_FREE_QTY());
                values.put(dbHelper.FORDDET_ITEM_CODE, ordDet.getFORDDET_ITEM_CODE());
                values.put(dbHelper.FORDDET_P_DIS_AMT, ordDet.getFORDDET_P_DIS_AMT());
                values.put(dbHelper.FORDDET_PRIL_CODE, ordDet.getFORDDET_PRIL_CODE());
                values.put(dbHelper.FORDDET_QTY, ordDet.getFORDDET_QTY());
                values.put(dbHelper.FORDDET_DIS_VAL_AMT, ordDet.getFORDDET_DIS_VAL_AMT());
                values.put(dbHelper.FORDDET_PICE_QTY, ordDet.getFORDDET_PICE_QTY());
                values.put(dbHelper.FORDDET_REA_CODE, ordDet.getFORDDET_REA_CODE());
                values.put(dbHelper.FORDDET_TYPE, ordDet.getFORDDET_TYPE());
                values.put(dbHelper.FORDDET_RECORD_ID, ordDet.getFORDDET_RECORD_ID());
                values.put(dbHelper.FORDDET_REFNO, ordDet.getFORDDET_REFNO());
                values.put(dbHelper.FORDDET_SELL_PRICE, ordDet.getFORDDET_SELL_PRICE());
                values.put(dbHelper.FORDDET_SEQNO, ordDet.getFORDDET_SEQNO());
                values.put(dbHelper.FORDDET_TAX_AMT, ordDet.getFORDDET_TAX_AMT());
                values.put(dbHelper.FORDDET_TAX_COM_CODE, ordDet.getFORDDET_TAX_COM_CODE());
                values.put(dbHelper.FORDDET_TIMESTAMP_COLUMN, ordDet.getFORDDET_TIMESTAMP_COLUMN());
                values.put(dbHelper.FORDDET_T_SELL_PRICE, ordDet.getFORDDET_T_SELL_PRICE());
                values.put(dbHelper.FORDDET_TXN_DATE, ordDet.getFORDDET_TXN_DATE());
                values.put(dbHelper.FORDDET_TXN_TYPE, ordDet.getFORDDET_TAX_TYPE());
                values.put(dbHelper.FORDDET_IS_ACTIVE, ordDet.getFORDDET_IS_ACTIVE());

                values.put(dbHelper.FORDDET_ITEMNAME, ordDet.getFORDDET_ITEMNAME());
                values.put(dbHelper.FORDDET_PACKSIZE, ordDet.getFORDDET_PACKSIZE());


                int cn = cursor.getCount();

                if (cn > 0) {
                    count = dB.update(dbHelper.TABLE_FORDDET, values, dbHelper.FORDDET_ID + " =?", new String[] { String.valueOf(ordDet.getFORDDET_ID()) });
                } else {
                    count = (int) dB.insert(dbHelper.TABLE_FORDDET, null, values);
                }

                cursor.close();
            }
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            dB.close();
        }
        return count;

    }

    // *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

    public void updateDiscount(OrdDet ordDet, double discount) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            OrderDisc orderDisc = new OrderDisc();
            orderDisc.setRefNo(ordDet.getFORDDET_REFNO());
            orderDisc.setTxnDate(ordDet.getFORDDET_TXN_DATE());
            orderDisc.setItemCode(ordDet.getFORDDET_ITEM_CODE());
            orderDisc.setDisAmt(ordDet.getFORDDET_DIS_AMT());

           // new OrderDiscDS(context).UpdateOrderDiscount(orderDisc, ordDet.getFORDDET_DISC_REF(), ordDet.getFORDDET_DIS_PER());
            String updateQuery = "UPDATE forddet SET DisPer='0.00', DisAmt='0.00', DisValAmt='" + discount + "' where Itemcode ='" + ordDet.getFORDDET_ITEM_CODE() + "'";
            dB.execSQL(updateQuery);

        } catch (Exception e) {
            Log.v(TAG + " Exception", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

    }
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    public int getItemCountForUpload(String refNo) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        try {
            String selectQuery = "SELECT count(RefNo) as RefNo FROM " + DatabaseHelper.TABLE_FORDDET + " WHERE  " + DatabaseHelper.FORDDET_REFNO + "='" + refNo + "' and "+DatabaseHelper.FORDDET_IS_ACTIVE+ " = '1' ";
            Cursor cursor = dB.rawQuery(selectQuery, null);

            while (cursor.moveToNext()) {
                return Integer.parseInt(cursor.getString(cursor.getColumnIndex("RefNo")));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            dB.close();
        }
        return 0;

    }


    // *-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

    public void UpdateArrayDiscount(ArrayList<OrdDet> orderList) {

        String DiscRef = orderList.get(0).getFORDDET_DISC_REF();
        String DiscPer = orderList.get(0).getFORDDET_DIS_PER();

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {

            for (OrdDet ordDet : orderList) {

                OrderDisc orderDisc = new OrderDisc();
                orderDisc.setRefNo(ordDet.getFORDDET_REFNO());
                orderDisc.setTxnDate(ordDet.getFORDDET_TXN_DATE());
                orderDisc.setRefNo1(ordDet.getFORDDET_DISC_REF());
                orderDisc.setItemCode(ordDet.getFORDDET_ITEM_CODE());
                orderDisc.setDisAmt(ordDet.getFORDDET_DIS_AMT());
                orderDisc.setDisPer(ordDet.getFORDDET_DIS_PER());

              //  new OrderDiscDS(context).UpdateOrderDiscount(orderDisc, DiscRef, DiscPer);
                String updateQuery = "UPDATE forddet SET DisPer='0.00', DisAmt='0.00', DisValAmt='" + ordDet.getFORDDET_DIS_AMT() + "' where Itemcode ='" + ordDet.getFORDDET_ITEM_CODE() + "'";
                dB.execSQL(updateQuery);

            }

        } catch (Exception e) {
            Log.v(TAG + " Exception", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }
    }

    // *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

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

            String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_REFNO + " = '" + refno + "'";
            cursor = dB.rawQuery(selectQuery, null);
            int cn = cursor.getCount();

            if (cn > 0) {
                count = dB.delete(dbHelper.TABLE_FORDDET, dbHelper.FORDDET_REFNO + " ='" + refno + "'", null);
                Log.v("Success Stauts", count + "");
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
    public int restFreeIssueData(String refno) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_REFNO + " = '" + refno + "' AND " + dbHelper.FORDDET_TYPE + " = 'FI'";
            cursor = dB.rawQuery(selectQuery, null);
            int cn = cursor.getCount();

            if (cn > 0) {
                count = dB.delete(dbHelper.TABLE_FORDDET, dbHelper.FORDDET_REFNO + " = '" + refno + "' AND " + dbHelper.FORDDET_TYPE + " = 'FI'", null);
                Log.v("Success Stauts", count + "");
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

	/*-*-**-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public ArrayList<OrdDet> getAllOrderDetails(String refno) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<OrdDet> list = new ArrayList<OrdDet>();

        String selectQuery = "select * from " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_TYPE + " in ('SA','FI') AND " + dbHelper.FORDDET_REFNO + "='" + refno + "' and "+dbHelper.FORDDET_IS_ACTIVE +" = '1'";

        Cursor cursor = dB.rawQuery(selectQuery, null);

        try {
            while (cursor.moveToNext()) {

                OrdDet ordDet = new OrdDet();

                ordDet.setFORDDET_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_ID)));
                ordDet.setFORDDET_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_AMT)));
                ordDet.setFORDDET_BAL_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BAL_QTY)));
                ordDet.setFORDDET_B_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_AMT)));
                ordDet.setFORDDET_B_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_DIS_AMT)));
                ordDet.setFORDDET_BP_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BP_DIS_AMT)));
                ordDet.setFORDDET_B_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_SELL_PRICE)));
                ordDet.setFORDDET_BT_TAX_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BT_TAX_AMT)));
                ordDet.setFORDDET_BT_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BT_SELL_PRICE)));
                ordDet.setFORDDET_CASE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_CASE)));
                ordDet.setFORDDET_CASE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_CASE_QTY)));
                ordDet.setFORDDET_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_AMT)));
                ordDet.setFORDDET_DIS_PER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_PER)));
                ordDet.setFORDDET_FREE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_FREE_QTY)));
                ordDet.setFORDDET_ITEM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_ITEM_CODE)));
                ordDet.setFORDDET_P_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_P_DIS_AMT)));
                ordDet.setFORDDET_PRIL_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_PRIL_CODE)));
                ordDet.setFORDDET_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_QTY)));
                ordDet.setFORDDET_DIS_VAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_VAL_AMT)));
                ordDet.setFORDDET_PICE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_PICE_QTY)));
                ordDet.setFORDDET_REA_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_REA_CODE)));
                ordDet.setFORDDET_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TYPE)));
                ordDet.setFORDDET_RECORD_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_RECORD_ID)));
                ordDet.setFORDDET_REFNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_REFNO)));
                ordDet.setFORDDET_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_SELL_PRICE)));
                ordDet.setFORDDET_SEQNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_SEQNO)));
                ordDet.setFORDDET_TAX_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TAX_AMT)));
                ordDet.setFORDDET_TAX_COM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TAX_COM_CODE)));
                ordDet.setFORDDET_TIMESTAMP_COLUMN(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TIMESTAMP_COLUMN)));
                ordDet.setFORDDET_T_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_T_SELL_PRICE)));
                ordDet.setFORDDET_TXN_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TXN_DATE)));
                ordDet.setFORDDET_TAX_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TXN_TYPE)));
                ordDet.setFORDDET_IS_ACTIVE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_IS_ACTIVE)));

                ordDet.setFORDDET_PACKSIZE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_PACKSIZE)));
                ordDet.setFORDDET_ITEMNAME(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_ITEMNAME)));

                list.add(ordDet);

            }
            cursor.close();

        } catch (Exception e) {
            Log.v(TAG + " Exception", e.toString());
        } finally {
            dB.close();
        }

        return list;
    }

    public ArrayList<OrdDet> getAllOrderDetailOthers(String refno) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<OrdDet> list = new ArrayList<OrdDet>();
        //
        // String selectQuery = "select * from "+dbHelper.TABLE_FORDDET
        // +" WHERE "+dbHelper.FORDDET_TXN_TYPE+"!='22' AND "+dbHelper.FORDDET_REFNO+"='"+refno+"'";

        String selectQuery = "select * from " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_TYPE + " in ('MR','UR','FD') AND " + dbHelper.FORDDET_REFNO + "='" + refno + "'";

        Cursor cursor = dB.rawQuery(selectQuery, null);

        try {
            while (cursor.moveToNext()) {

                OrdDet ordDet = new OrdDet();

                ordDet.setFORDDET_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_ID)));
                ordDet.setFORDDET_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_AMT)));
                ordDet.setFORDDET_BAL_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BAL_QTY)));
                ordDet.setFORDDET_B_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_AMT)));
                ordDet.setFORDDET_B_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_DIS_AMT)));
                ordDet.setFORDDET_BP_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BP_DIS_AMT)));
                ordDet.setFORDDET_B_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_SELL_PRICE)));
                ordDet.setFORDDET_BT_TAX_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BT_TAX_AMT)));
                ordDet.setFORDDET_BT_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BT_SELL_PRICE)));
                ordDet.setFORDDET_CASE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_CASE)));
                ordDet.setFORDDET_CASE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_CASE_QTY)));
                ordDet.setFORDDET_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_AMT)));
                ordDet.setFORDDET_DIS_PER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_PER)));
                ordDet.setFORDDET_FREE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_FREE_QTY)));
                ordDet.setFORDDET_ITEM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_ITEM_CODE)));
                ordDet.setFORDDET_P_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_P_DIS_AMT)));
                ordDet.setFORDDET_PRIL_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_PRIL_CODE)));
                ordDet.setFORDDET_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_QTY)));
                ordDet.setFORDDET_DIS_VAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_VAL_AMT)));
                ordDet.setFORDDET_PICE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_PICE_QTY)));
                ordDet.setFORDDET_REA_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_REA_CODE)));
                ordDet.setFORDDET_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TYPE)));
                ordDet.setFORDDET_RECORD_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_RECORD_ID)));
                ordDet.setFORDDET_REFNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_REFNO)));
                ordDet.setFORDDET_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_SELL_PRICE)));
                ordDet.setFORDDET_SEQNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_SEQNO)));
                ordDet.setFORDDET_TAX_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TAX_AMT)));
                ordDet.setFORDDET_TAX_COM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TAX_COM_CODE)));
                ordDet.setFORDDET_TIMESTAMP_COLUMN(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TIMESTAMP_COLUMN)));
                ordDet.setFORDDET_T_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_T_SELL_PRICE)));
                ordDet.setFORDDET_TXN_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TXN_DATE)));
                ordDet.setFORDDET_TAX_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TXN_TYPE)));
                ordDet.setFORDDET_IS_ACTIVE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_IS_ACTIVE)));

                list.add(ordDet);

            }
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return list;
    }

    public ArrayList<OrdDet> getSAForFreeIssueCalc(String refno) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<OrdDet> list = new ArrayList<OrdDet>();

        String selectQuery = "select * from " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_TXN_TYPE + "='21' AND " + dbHelper.FORDDET_TYPE + "='SA' AND "  + dbHelper.FORDDET_REFNO + "='" + refno + "'";

        Cursor cursor = dB.rawQuery(selectQuery, null);

        try {
            while (cursor.moveToNext()) {

                OrdDet ordDet = new OrdDet();

                ordDet.setFORDDET_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_ID)));
                ordDet.setFORDDET_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_AMT)));
                ordDet.setFORDDET_BAL_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BAL_QTY)));
                ordDet.setFORDDET_B_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_AMT)));
                ordDet.setFORDDET_B_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_DIS_AMT)));
                ordDet.setFORDDET_BP_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BP_DIS_AMT)));
                ordDet.setFORDDET_B_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_SELL_PRICE)));
                ordDet.setFORDDET_BT_TAX_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BT_TAX_AMT)));
                ordDet.setFORDDET_BT_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BT_SELL_PRICE)));
                ordDet.setFORDDET_CASE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_CASE)));
                ordDet.setFORDDET_CASE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_CASE_QTY)));
                ordDet.setFORDDET_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_AMT)));
                ordDet.setFORDDET_DIS_PER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_PER)));
                ordDet.setFORDDET_FREE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_FREE_QTY)));
                ordDet.setFORDDET_ITEM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_ITEM_CODE)));
                ordDet.setFORDDET_P_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_P_DIS_AMT)));
                ordDet.setFORDDET_PRIL_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_PRIL_CODE)));
                ordDet.setFORDDET_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_QTY)));
                ordDet.setFORDDET_DIS_VAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_VAL_AMT)));
                ordDet.setFORDDET_PICE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_PICE_QTY)));
                ordDet.setFORDDET_REA_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_REA_CODE)));
                ordDet.setFORDDET_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TYPE)));
                ordDet.setFORDDET_RECORD_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_RECORD_ID)));
                ordDet.setFORDDET_REFNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_REFNO)));
                ordDet.setFORDDET_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_SELL_PRICE)));
                ordDet.setFORDDET_SEQNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_SEQNO)));
                ordDet.setFORDDET_TAX_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TAX_AMT)));
                ordDet.setFORDDET_TAX_COM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TAX_COM_CODE)));
                ordDet.setFORDDET_TIMESTAMP_COLUMN(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TIMESTAMP_COLUMN)));
                ordDet.setFORDDET_T_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_T_SELL_PRICE)));
                ordDet.setFORDDET_TXN_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TXN_DATE)));
                ordDet.setFORDDET_TAX_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TXN_TYPE)));
                ordDet.setFORDDET_IS_ACTIVE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_IS_ACTIVE)));

                list.add(ordDet);

            }
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return list;
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

            String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_REFNO + " = '" + refno + "'";

            cursor = dB.rawQuery(selectQuery, null);

            ContentValues values = new ContentValues();

            values.put(dbHelper.FORDDET_IS_ACTIVE, "0");

            int cn = cursor.getCount();

            if (cn > 0) {
                count = dB.update(dbHelper.TABLE_FORDDET, values, dbHelper.FORDDET_REFNO + " =?", new String[] { String.valueOf(refno) });
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
    public int deleteOrdDetByID(String id) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {

            cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_ID + "='" + id + "'", null);
            count = cursor.getCount();
            if (count > 0) {
                int success = dB.delete(dbHelper.TABLE_FORDDET, dbHelper.FORDDET_ID + "='" + id + "'", null);
                Log.v("OrdDet Deleted ", success + "");
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
    public int deleteOrdDetByItemCode(String itemcode, String Refno) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {

            cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_REFNO + "='" + Refno + "' AND "+dbHelper.FORDDET_ITEM_CODE + "='"+itemcode+"'", null);
            count = cursor.getCount();
            if (count > 0) {
                int success = dB.delete(dbHelper.TABLE_FORDDET, dbHelper.FORDDET_REFNO + "='" + Refno + "' AND "+dbHelper.FORDDET_ITEM_CODE + "='"+itemcode+"'", null);
                Log.v("OrdDet Deleted ", success + "");
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

    public String getGrossValue(String refno) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        // String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREASON
        // +" WHERE "+dbHelper.FREASON_NAME+"='"+name+"'";
        @SuppressWarnings("static-access")
        String selectQuery = "SELECT SUM(" + dbHelper.FORDDET_BAL_QTY + " * " + dbHelper.FORDDET_B_SELL_PRICE + ") AS 'Gross Value'  FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_TYPE + " ='SA' AND " + dbHelper.FORDDET_REFNO + "='" + refno + "'";
        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        try {
            while (cursor.moveToNext()) {
                if (cursor.getString(cursor.getColumnIndex("Gross Value")) != null)
                    return cursor.getString(cursor.getColumnIndex("Gross Value"));
                else
                    return "0.00";
            }
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return "0.00";
    }

    public String getTotalReturns(String refno) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        // String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREASON
        // +" WHERE "+dbHelper.FREASON_NAME+"='"+name+"'";
        @SuppressWarnings("static-access")
        String selectQuery = "SELECT SUM(" + dbHelper.FORDDET_BAL_QTY + " * " + dbHelper.FORDDET_B_SELL_PRICE + ") AS 'Total Return'  FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_TYPE + " IN('MR','UR') AND " + dbHelper.FORDDET_REFNO + "='" + refno + "'";
        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        try {
            while (cursor.moveToNext()) {
                if (cursor.getString(cursor.getColumnIndex("Total Return")) != null)
                    return cursor.getString(cursor.getColumnIndex("Total Return"));
                else
                    return "0.00";

            }
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return "0.00";
    }

    public String getTotalLineDiscount(String refno) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        String selectQuery = "SELECT SUM((BalQty * BSellPrice)* disper/100  + DisValAmt) AS 'Total line'  FROM forddet WHERE types ='SA' AND " + dbHelper.FORDDET_REFNO + "='" + refno + "'";
        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        try {
            while (cursor.moveToNext()) {

                if (cursor.getString(cursor.getColumnIndex("Total line")) != null)
                    return cursor.getString(cursor.getColumnIndex("Total line"));
                else
                    return "0.00";

            }
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return "0.00";
    }

    public ArrayList<OrdDet> getAllUnSync(String refno) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<OrdDet> list = new ArrayList<OrdDet>();

        String selectQuery = "select * from " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_REFNO + "='" + refno + "'";

        Cursor cursor = dB.rawQuery(selectQuery, null);

        try {
            while (cursor.moveToNext()) {

                OrdDet ordDet = new OrdDet();

                ordDet.setFORDDET_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_ID)));
                ordDet.setFORDDET_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_AMT)));
                ordDet.setFORDDET_BAL_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BAL_QTY)));
                ordDet.setFORDDET_B_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_AMT)));
                ordDet.setFORDDET_B_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_DIS_AMT)));
                ordDet.setFORDDET_BP_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BP_DIS_AMT)));
                ordDet.setFORDDET_B_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_B_SELL_PRICE)));
                ordDet.setFORDDET_BT_TAX_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BT_TAX_AMT)));
                ordDet.setFORDDET_BT_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_BT_SELL_PRICE)));
                ordDet.setFORDDET_CASE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_CASE)));
                ordDet.setFORDDET_CASE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_CASE_QTY)));
                ordDet.setFORDDET_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_AMT)));
                ordDet.setFORDDET_DIS_PER(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_PER)));
                ordDet.setFORDDET_FREE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_FREE_QTY)));
                ordDet.setFORDDET_ITEM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_ITEM_CODE)));
                ordDet.setFORDDET_P_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_P_DIS_AMT)));
                ordDet.setFORDDET_PRIL_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_PRIL_CODE)));
                ordDet.setFORDDET_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_QTY)));
                ordDet.setFORDDET_DIS_VAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_VAL_AMT)));
                ordDet.setFORDDET_PICE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_PICE_QTY)));
                ordDet.setFORDDET_REA_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_REA_CODE)));
                ordDet.setFORDDET_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TYPE)));
                ordDet.setFORDDET_RECORD_ID(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_RECORD_ID)));
                ordDet.setFORDDET_REFNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_REFNO)));
                ordDet.setFORDDET_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_SELL_PRICE)));
                ordDet.setFORDDET_SEQNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_SEQNO)));
                ordDet.setFORDDET_TAX_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TAX_AMT)));
                ordDet.setFORDDET_TAX_COM_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TAX_COM_CODE)));
                ordDet.setFORDDET_TIMESTAMP_COLUMN(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TIMESTAMP_COLUMN)));
                ordDet.setFORDDET_T_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_T_SELL_PRICE)));
                ordDet.setFORDDET_TXN_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TXN_DATE)));
                ordDet.setFORDDET_TAX_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TXN_TYPE)));
                ordDet.setFORDDET_IS_ACTIVE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_IS_ACTIVE)));



                list.add(ordDet);

            }
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return list;
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

            String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_TXN_DATE + " BETWEEN '"+ DateFrom + "' AND '" + DateTo + "'";
            cursor = dB.rawQuery(selectQuery, null);
            int cn = cursor.getCount();

            if (cn > 0) {
                int success = dB.delete(dbHelper.TABLE_FORDDET, dbHelper.FORDDET_TXN_DATE + " BETWEEN '"+ DateFrom + "' AND '" + DateTo + "'", null);
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
    public boolean getCheckQOH(String RefNo, String ItemCode, int FreeQty, int QOH) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }


        Cursor cursor = null;
        String selectQuery = "SELECT QTY,CASES FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_REFNO + "='" + RefNo + "' AND "+dbHelper.FORDDET_ITEM_CODE + "='" + ItemCode +"'";
        cursor = dB.rawQuery(selectQuery, null);

        try {


            //int cn = cursor.getCount();

            while (cursor.moveToNext()) {

                if (cursor.getString(cursor.getColumnIndex("Qty")) != null){
                    int SalQty =  Integer.parseInt(cursor.getString(cursor.getColumnIndex("Qty")));
                    int ITQoh =  Integer.parseInt(cursor.getString(cursor.getColumnIndex("Cases")));
                    if(ITQoh >= (SalQty+FreeQty)){
                        return true;
                    }else{
                        return false;
                    }

                }else{
                    return false;
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
        return false;

    }

    @SuppressWarnings("static-access")
    public  FreeQohStatus setCheckQOH(String RefNo, String ItemCode, int FreeQty, int QOH) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }


        Cursor cursor = null;
        String selectQuery = "SELECT QTY,CASES FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_REFNO + "='" + RefNo + "' AND "+dbHelper.FORDDET_ITEM_CODE + "='" + ItemCode +"'";
        cursor = dB.rawQuery(selectQuery, null);
        ArrayList<FreeQohStatus> flist = new ArrayList<FreeQohStatus>();
        FreeQohStatus frqoh = new FreeQohStatus();

        try {


            //int cn = cursor.getCount();


            while (cursor.moveToNext()) {
                //FreeQohStatus frqoh = new FreeQohStatus();

                if (cursor.getString(cursor.getColumnIndex("Qty")) != null){
                    int SalQty =  Integer.parseInt(cursor.getString(cursor.getColumnIndex("Qty")));
                    int ITQoh =  Integer.parseInt(cursor.getString(cursor.getColumnIndex("Cases")));

                    frqoh.setFREE_ITEM_CODE(ItemCode);
                    frqoh.setFREE_QOH(cursor.getString(cursor.getColumnIndex("Cases")));
                    frqoh.setFREE_REQ_QOH(String.valueOf((SalQty+FreeQty)));
                    //flist.add(frqoh);
                }else{
                    return frqoh;
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
        return frqoh;

    }

    @SuppressWarnings("static-access")
    public void setCheckQOHFree(String refno, String Itemcode, String QOHStat) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_REFNO + " = '" + refno + "'";

            cursor = dB.rawQuery(selectQuery, null);

            ContentValues values = new ContentValues();

            //values.put(dbHelper.FORDDET_REA_CODE, "Y");

            int cn = cursor.getCount();

            if (cn > 0 && !(Itemcode.equals(""))) {
                String updateQuery = "UPDATE forddet SET "+dbHelper.FORDDET_REA_CODE +"='"+QOHStat+"' where " + dbHelper.FORDDET_REFNO + " = '"+ refno +"' AND "+ dbHelper.FORDDET_ITEM_CODE + "='"+Itemcode+"' AND "+dbHelper.FORDDET_TYPE+"='SA'";
                dB.execSQL(updateQuery);
            } else if(cn > 0 && Itemcode.equals("")){
                String updateQuery = "UPDATE forddet SET "+dbHelper.FORDDET_REA_CODE +"='"+QOHStat+"' where " + dbHelper.FORDDET_REFNO + " = '"+ refno +"' AND "+dbHelper.FORDDET_TYPE+"='SA'";
                dB.execSQL(updateQuery);
                //count = (int) dB.insert(dbHelper.TABLE_FORDDET, null, values);
            }

        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }


    }
    public void mDeleteRecords(String RefNo) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        try {
            dB.delete(DatabaseHelper.TABLE_FORDDET, DatabaseHelper.FORDDET_REFNO + " ='" + RefNo + "'", null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dB.close();
        }
    }


    @SuppressWarnings("static-access")
    public int UpdateQOHCases(String RefNo, String ItemCode, int QtyQOH) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            ContentValues values = new ContentValues();

            values.put(dbHelper.FORDDET_CASE, String.valueOf(QtyQOH));

            String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FORDDET + " WHERE " + dbHelper.FORDDET_REFNO + " ='"+ RefNo + "' AND "+dbHelper.FORDDET_ITEM_CODE+" = '"+ItemCode+"' AND "+dbHelper.FORDDET_TYPE + " in ('SA')";
            cursor = dB.rawQuery(selectQuery, null);
            int cn = cursor.getCount();

            if (cn > 0) {
                int success = dB.update(dbHelper.TABLE_FORDDET, values, dbHelper.FORDDET_REFNO + " ='"+ RefNo + "' AND "+dbHelper.FORDDET_ITEM_CODE+" = '"+ItemCode+"' AND "+dbHelper.FORDDET_TYPE + " in ('SA')", null);
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
}
