package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


import com.bit.sfa.Models.PreProduct;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by rashmi 2018-08-20
 */

public class PreProductDS {
    Context context;
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;

    public PreProductDS(Context context) {
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public void insertOrUpdateProducts(ArrayList<PreProduct> list) {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        try {
            dB.beginTransactionNonExclusive();
            String sql = "INSERT OR REPLACE INTO " + DatabaseHelper.TABLE_FPRODUCT_PRE + " (itemcode_pre,itemname_pre,qty_pre) VALUES (?,?,?)";

            SQLiteStatement stmt = dB.compileStatement(sql);

            for (PreProduct items : list) {

                stmt.bindString(1, items.getPREPRODUCT_ITEMCODE());
                stmt.bindString(2, items.getPREPRODUCT_ITEMNAME());
                stmt.bindString(3, items.getPREPRODUCT_QTY());

                stmt.execute();
                stmt.clearBindings();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dB.setTransactionSuccessful();
            dB.endTransaction();
            dB.close();
        }

    }

//-------------------------------------------------------------------------------------------------------------------------------------------------



    public int createOrUpdateSODett(ArrayList<PreProduct> list) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;

        try {

            for (PreProduct preProduct : list) {

                ContentValues values = new ContentValues();

                String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT_PRE + " WHERE " + DatabaseHelper.FPRODUCT_ITEMCODE_PRE + " = '" + preProduct.getPREPRODUCT_ITEMCODE() + "'";

                cursor = dB.rawQuery(selectQuery, null);
                values.put(DatabaseHelper.FPRODUCT_ITEMCODE_PRE, preProduct.getPREPRODUCT_ITEMCODE());
                values.put(DatabaseHelper.FPRODUCT_ITEMNAME_PRE, preProduct.getPREPRODUCT_ITEMNAME());
                values.put(DatabaseHelper.FPRODUCT_QTY_PRE, preProduct.getPREPRODUCT_QTY());



                int cn = cursor.getCount();
                if (cn > 0) {

                    count = dB.update(DatabaseHelper.TABLE_FPRODUCT_PRE, values, DatabaseHelper.FPRODUCT_ITEMCODE_PRE + " =?", new String[]{String.valueOf(preProduct.getPREPRODUCT_ITEMCODE())});

                } else {
                    count = (int) dB.insert(DatabaseHelper.TABLE_FPRODUCT_PRE, null, values);
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



    public boolean tableHasRecords() {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        boolean result = false;
        Cursor cursor = null;

        try {
            cursor = dB.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT_PRE, null);

            if (cursor.getCount() > 0)
                result = true;
            else
                result = false;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            dB.close();

        }

        return result;

    }

    public ArrayList<PreProduct> getAllItems(String newText) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        ArrayList<PreProduct> list = new ArrayList<>();
        try {
            cursor = dB.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT_PRE + " WHERE itemcode_pre || itemname_pre LIKE '%" + newText + "%' group by itemcode_pre", null);

            while (cursor.moveToNext()) {
                PreProduct product = new PreProduct();
                product.setPREPRODUCT_ID(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ID_PRE)));
                product.setPREPRODUCT_ITEMCODE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ITEMCODE_PRE)));
                product.setPREPRODUCT_ITEMNAME(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ITEMNAME_PRE)));
                product.setPREPRODUCT_QTY(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_QTY_PRE)));

                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            dB.close();
        }

        return list;
    }
//--------------getItems ItemCodevise--------------------------------------------------------------------------------------------------------

    public ArrayList<PreProduct> getItemsCodeVise(String newText, String ItemCode) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        ArrayList<PreProduct> list = new ArrayList<>();
        try {
            cursor = dB.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT_PRE + " WHERE itemcode_pre || itemname_pre LIKE '%" + newText + "%'and itemcode_pre = '" + ItemCode + "'", null);

            while (cursor.moveToNext()) {
                PreProduct product = new PreProduct();
                product.setPREPRODUCT_ID(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ID_PRE)));
                product.setPREPRODUCT_ITEMCODE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ITEMCODE_PRE)));
                product.setPREPRODUCT_ITEMNAME(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ITEMNAME_PRE)));
                product.setPREPRODUCT_QTY(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_QTY_PRE)));

                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            dB.close();
        }

        return list;
    }


    //-----------------------------------------------------------------------------------------------------------------------------------------

    public void updateProductQty(String itemCode, String qty) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        try {

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.FPRODUCT_QTY_PRE, qty);
            dB.update(DatabaseHelper.TABLE_FPRODUCT_PRE, values, DatabaseHelper.FPRODUCT_ITEMCODE_PRE + " =?", new String[]{String.valueOf(itemCode)});

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dB.close();
        }
    }


    //------------------rashmi --------------------------------------------------------------------------------------------------

    public int updateProductQtyFor(String itemCode, String qty) {
        int count = 0;
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        try {

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.FPRODUCT_QTY_PRE, qty);
          count=(int)  dB.update(DatabaseHelper.TABLE_FPRODUCT_PRE, values, DatabaseHelper.FPRODUCT_ITEMCODE_PRE + " =?", new String[]{String.valueOf(itemCode)});

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dB.close();
        }
        return count;
    }
//----------------------------------------------------------------------------------------------------------------

    public ArrayList<PreProduct> getSelectedItems() {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        ArrayList<PreProduct> list = new ArrayList<>();
        try {
            cursor = dB.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT_PRE + " WHERE  qty_pre<>'0'", null);

            while (cursor.moveToNext()) {
                PreProduct product = new PreProduct();
                product.setPREPRODUCT_ID(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ID_PRE)));
                product.setPREPRODUCT_ITEMCODE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ITEMCODE_PRE)));
                product.setPREPRODUCT_ITEMNAME(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ITEMNAME_PRE)));
                product.setPREPRODUCT_QTY(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_QTY_PRE)));




                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            dB.close();
        }

        return list;
    }


    /*public ArrayList<InvDet> getSelectedItemsForInvoice(String Refno) {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        ArrayList<InvDet> list = new ArrayList<>();
        try {
            cursor = dB.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT + " WHERE  qty<>'0'", null);

            while (cursor.moveToNext()) {
                InvDet invDet = new InvDet();
                invDet.setFINVDET_ID(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ID)));
                invDet.setFINVDET_ITEM_CODE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ITEMCODE)));
                invDet.setFINVDET_REFNO(Refno);
                invDet.setFINVDET_SELL_PRICE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_PRICE)));
                invDet.setFINVDET_QOH(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_QOH)));
                invDet.setFINVDET_QTY(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_QTY)));
                list.add(invDet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            dB.close();
        }

        return list;
    }*/

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*/

    public void mClearTables() {

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        try {
            dB.delete(DatabaseHelper.TABLE_FPRODUCT_PRE, null, null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dB.close();
        }
    }

}
