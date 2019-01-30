package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.SKU;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class SkuDS {

	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG="swadeshi";
	
	
	public SkuDS (Context context){
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}
	
	@SuppressWarnings("static-access")
	public int createOrUpdateSku(ArrayList<SKU>  list) {
		
		int count =0;
		
		if(dB == null){
			open();
		}else if(!dB.isOpen()){
			open();
		}
		Cursor cursor = null;
		
	try{
		
			
    for (SKU sku : list) {
    		ContentValues values = new ContentValues();
			
			values.put(dbHelper.FSKU_ADD_DATE, sku.getFSKU_ADD_DATE());
			values.put(dbHelper.FSKU_ADD_MACH, sku.getFSKU_ADD_MACH());
			values.put(dbHelper.FSKU_ADD_USER, sku.getFSKU_ADD_USER());
			values.put(dbHelper.FSKU_BRAND_CODE, sku.getFSKU_BRAND_CODE());
			values.put(dbHelper.FSKU_GROUP_CODE, sku.getFSKU_GROUP_CODE());
			values.put(dbHelper.FSKU_ITEM_STATUS, sku.getFSKU_ITEM_STATUS());
			values.put(dbHelper.FSKU_MUST_SALE, sku.getFSKU_MUST_SALE());
			values.put(dbHelper.FSKU_NOUCASE, sku.getFSKU_NOUCASE());
			values.put(dbHelper.FSKU_ORDSEQ, sku.getFSKU_ORDSEQ());
			values.put(dbHelper.FSKU_RECORDID, sku.getFSKU_RECORDID());
			values.put(dbHelper.FSKU_SUB_BRAND_CODE, sku.getFSKU_SUB_BRAND_CODE());
			values.put(dbHelper.FSKU_CODE, sku.getFSKU_CODE());
			values.put(dbHelper.FSKU_NAME, sku.getFSKU_NAME());
			values.put(dbHelper.FSKU_SIZE_CODE, sku.getFSKU_SIZE_CODE());
			values.put(dbHelper.FSKU_TONNAGE, sku.getFSKU_TONNAGE());
			values.put(dbHelper.FSKU_TYPE_CODE, sku.getFSKU_TYPE_CODE());
			values.put(dbHelper.FSKU_UNIT, sku.getFSKU_UNIT());
			
			count = (int) dB.insert(dbHelper.TABLE_FSKU, null, values);

			}
		}catch (Exception e) {
		
			Log.v(TAG+" Exception", e.toString());
	
		}finally {  
			if (cursor !=null) {
				cursor.close();
			}
			dB.close();
		}
		return count;
				
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
			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FSKU, null);
			count =cursor.getCount();
			if(count>0){
				int success = dB.delete(dbHelper.TABLE_FSKU, null, null);
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
