package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.bit.sfa.Models.FSupplier;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class FSupplierDS {
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG = "KFD";
	
	public FSupplierDS(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}
	
	public int createOrUpdateFSup(ArrayList<FSupplier> fSuppliers) {
		int serverdbID = 0;
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		try {
			dB.beginTransaction();
			String sql = "Insert or Replace into " + dbHelper.TABLE_FSUPPLIER + " (" + dbHelper.FSUPPLIER_CODE + ", "
									+ dbHelper.FSUPPLIER_SUPNAME + ") values(?,?)";
			SQLiteStatement insert = dB.compileStatement(sql);

			for (FSupplier db : fSuppliers) {
				insert.bindString(1, db.getFSupplier_CODE());
				insert.bindString(2, db.getFSupplier_NAME());
				
				insert.execute();

				serverdbID = 1;
				
			}
		
			
			dB.setTransactionSuccessful();
		
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			dB.endTransaction();
			dB.close();
		}
		return serverdbID;
		
	}
	
public int deleteAll(){
		
		int count =0;
		
		if(dB == null){
			open();
		}else if(!dB.isOpen()){
			open();
		}
		Cursor cursor = null;
		try{
			
			cursor = dB.rawQuery("SELECT * FROM " + dbHelper.TABLE_FSUPPLIER, null);
			count =cursor.getCount();
			if(count>0){
				int success = dB.delete(dbHelper.TABLE_FSUPPLIER, null, null);
				Log.v("Success", success+"");
			}
		}catch (Exception e){

			e.printStackTrace();
			
		}finally{  
			if (cursor !=null) {
				cursor.close();
			}
			dB.close();
		}
		
		return count;
		
	}

public ArrayList<FSupplier> getAllSupplier() {
	if (dB == null) {
		open();
	} else if (!dB.isOpen()) {
		open();
	}

	ArrayList<FSupplier> list = new ArrayList<FSupplier>();
	Cursor cursor = null;
	try {
		String selectQuery = "select * from " + dbHelper.TABLE_FSUPPLIER + " ORDER BY SupCode ASC " ;

		cursor = dB.rawQuery(selectQuery, null);
		while (cursor.moveToNext()) {

			FSupplier supplier = new FSupplier();

			supplier.setFSupplier_CODE(cursor.getString(cursor.getColumnIndex(dbHelper.FSUPPLIER_CODE)));
			supplier.setFSupplier_NAME(cursor.getString(cursor.getColumnIndex(dbHelper.FSUPPLIER_SUPNAME)));
			
			
			list.add(supplier);

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
}
