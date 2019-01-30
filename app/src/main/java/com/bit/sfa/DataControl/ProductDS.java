package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.FmItem;
import com.bit.sfa.Models.Product;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class ProductDS {
	 Context context;
	    private SQLiteDatabase dB;
	    private DatabaseHelper dbHelper;

	    public ProductDS(Context context) {
	        this.context = context;
	        dbHelper = new DatabaseHelper(context);
	    }

	    public void open() throws SQLException {
	        dB = dbHelper.getWritableDatabase();
	    }


	    public void insertOrUpdateProducts(ArrayList<Product> list) {
	        if (dB == null) {
	            open();
	        } else if (!dB.isOpen()) {
	            open();
	        }

	        try {
	            dB.beginTransactionNonExclusive();
	            String sql = "INSERT OR REPLACE INTO " + DatabaseHelper.TABLE_FPRODUCT + " (itemcode,itemname,price,qoh,NOUCase,Pack,qty,fSchema,SupCode) VALUES (?,?,?,?,?,?,?,?,?)";

	            SQLiteStatement stmt = dB.compileStatement(sql);

	            for (Product items : list) {

	                stmt.bindString(1, items.getFPRODUCT_ITEMCODE());
	                stmt.bindString(2, items.getFPRODUCT_ITEMNAME());
	                stmt.bindString(3, items.getFPRODUCT_PRICE());
	                stmt.bindString(4, items.getFPRODUCT_QOH());
	                stmt.bindString(5, items.getFPRODUCT_NOUCASE());
	                stmt.bindString(6, items.getFPRODUCT_PACK());
	                stmt.bindString(7, items.getFPRODUCT_QTY());
	                stmt.bindString(8, items.getFPRODUCT_FREESCREMA());
	                stmt.bindString(9, items.getSupCode());

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


	    public boolean tableHasRecords() {

	        if (dB == null) {
	            open();
	        } else if (!dB.isOpen()) {
	            open();
	        }
	        boolean result = false;
	        Cursor cursor = null;

	        try {
	            cursor = dB.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT, null);

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

	    public ArrayList<Product> getAllItems(String newText,String supcode) {

	        if (dB == null) {
	            open();
	        } else if (!dB.isOpen()) {
	            open();
	        }
	        Cursor cursor = null;
	        ArrayList<Product> list = new ArrayList<Product>();
	        try {
	        	
	        	if (supcode.length()>0){
	        		String supsearchsql = "";
	        		supsearchsql = "SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT + " WHERE itemname LIKE '" + newText + "%' AND SupCode = '" + supcode + "'"; 
	        		cursor = dB.rawQuery(supsearchsql , null);	
	        	}else{
	        		String searchsql = "";
	        		searchsql = "SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT + " WHERE itemname LIKE '" + newText + "%'";
	        		cursor = dB.rawQuery( searchsql, null);
	        		// old 09-04-2018 cursor = dB.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT + " WHERE itemcode || itemname LIKE '%" + newText + "%' AND SupCode = '" + supcode + "'" , null);
	        	}
	            

	            while (cursor.moveToNext()) {
	                Product product = new Product();
	                product.setFPRODUCT_ID(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ID)));
	                product.setFPRODUCT_ITEMCODE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ITEMCODE)));
	                product.setFPRODUCT_ITEMNAME(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ITEMNAME)));
	                product.setFPRODUCT_PRICE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_PRICE)));
	                product.setFPRODUCT_QOH(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_QOH)));
	                product.setFPRODUCT_NOUCASE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_NOUCASE)));
	                product.setFPRODUCT_PACK(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_PACK)));
	                product.setFPRODUCT_QTY(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_QTY)));
	                product.setFPRODUCT_FREESCREMA(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_FREESCHEMA)));
	                product.setSupCode(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_supCode)));
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
	    
	    public ArrayList<FmItem> getAllItemsbySupCode(String supcode) {

	        if (dB == null) {
	            open();
	        } else if (!dB.isOpen()) {
	            open();
	        }
	        Cursor cursor = null;
	        ArrayList<FmItem> list = new ArrayList<FmItem>();
	        try {
	        	String sql="SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT + " WHERE SupCode = '" + supcode +"'";
	        	cursor = dB.rawQuery(sql , null);

	            while (cursor.moveToNext()) {

					FmItem product = new FmItem();

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


	    public void updateProductQty(String itemCode, String qty) {

	        if (dB == null) {
	            open();
	        } else if (!dB.isOpen()) {
	            open();
	        }

	        try {

	            ContentValues values = new ContentValues();
	            values.put(DatabaseHelper.FPRODUCT_QTY, qty);
	            dB.update(DatabaseHelper.TABLE_FPRODUCT, values, DatabaseHelper.FPRODUCT_ITEMCODE + " =?", new String[]{String.valueOf(itemCode)});

	        } catch (Exception e) {
	            Log.v(" Exception", e.toString());
	        } finally {
	            dB.close();
	        }
	    }
	//---------------------------------added by dhanushika----------------------------------------------------------------------------


	    public int updateProductQtyfor(String itemCode, String qty) {
	        int count = 0;
	        if (dB == null) {
	            open();
	        } else if (!dB.isOpen()) {
	            open();
	        }

	        try {

	            ContentValues values = new ContentValues();
	            values.put(DatabaseHelper.FPRODUCT_QTY, qty);
	            count=(int)dB.update(DatabaseHelper.TABLE_FPRODUCT, values, DatabaseHelper.FPRODUCT_ITEMCODE + " =?", new String[]{String.valueOf(itemCode)});

	        } catch (Exception e) {
	        e.printStackTrace();
	        } finally {
	            dB.close();
	        }
	        return  count;
	    }


	    public ArrayList<Product> getSelectedItems() {

	        if (dB == null) {
	            open();
	        } else if (!dB.isOpen()) {
	            open();
	        }
	        Cursor cursor = null;
	        ArrayList<Product> list = new ArrayList<Product>();
	        try {
	            cursor = dB.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FPRODUCT + " WHERE  qty<>'0'", null);

	            while (cursor.moveToNext()) {
	                Product product = new Product();
	                product.setFPRODUCT_ID(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ID)));
	                product.setFPRODUCT_ITEMCODE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ITEMCODE)));
	                product.setFPRODUCT_ITEMNAME(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_ITEMNAME)));
	                product.setFPRODUCT_PRICE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_PRICE)));
	                product.setFPRODUCT_QOH(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_QOH)));
	                product.setFPRODUCT_NOUCASE(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_NOUCASE)));
	                product.setFPRODUCT_PACK(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_PACK)));
	                product.setFPRODUCT_QTY(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_QTY)));
	                product.setFPRODUCT_FREESCREMA(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_FREESCHEMA)));
	                product.setSupCode(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FPRODUCT_supCode)));
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
	    }
*/
	    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*/

	    public void mClearTables() {

	        if (dB == null) {
	            open();
	        } else if (!dB.isOpen()) {
	            open();
	        }
	        try {
	            dB.delete(DatabaseHelper.TABLE_FPRODUCT, null, null);

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            dB.close();
	        }
	    }
}
