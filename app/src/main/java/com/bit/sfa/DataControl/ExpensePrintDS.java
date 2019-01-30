package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.bit.sfa.Models.ExpesePrintPre;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class ExpensePrintDS {
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG="swadeshi";
	
	
	public ExpensePrintDS (Context context){
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}
	
	
	
	
	public ArrayList<ExpesePrintPre> getAllExpenseListPreview(String currentRefNo) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		
		ArrayList<ExpesePrintPre> list = new ArrayList<ExpesePrintPre>();
		//String currentRefNo = "EXASA/000010";
		String selectQuery = "select a.refno,a.txndate,a.expcode,a.amt,b.totAmt from fdayExpDet as a,fdayExpHed as b where a.refno=b.refno and a.refno ='"+ currentRefNo  + "'";
		
		Cursor cursor = dB.rawQuery(selectQuery, null);
		while(cursor.moveToNext()){
			
			ExpesePrintPre aExpensePrintPre=new ExpesePrintPre();
			
			aExpensePrintPre.setEXPENSE_PRINT_REF_NO(cursor.getString(cursor.getColumnIndex(dbHelper.FDAYEXPHED_REFNO)));
			aExpensePrintPre.setEXPENSE_PRINT_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FDAYEXPHED_TXNDATE)));
			aExpensePrintPre.setEXPENSE_PRINT_EXPNAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDAYEXPDET_EXPCODE)));
			aExpensePrintPre.setEXPENSE_PRINT_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FDAYEXPDET_AMT)));
			aExpensePrintPre.setEXPENSE_PRINT_TOTAMT(cursor.getString(cursor.getColumnIndex(dbHelper.FDAYEXPHED_TOTAMT)));
			list.add(aExpensePrintPre);
			System.out.println(list.size());
			
		}
		
		return list;
	}
	
	
	public String getTotalCaseQtyReturns(String refno) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		//String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREASON +" WHERE "+dbHelper.FREASON_NAME+"='"+name+"'";
		@SuppressWarnings("static-access")
		String selectQuery = "select sum(FD.CaseQty) as CaseQtySum from ftranDet FD where refno = '"+ refno  +"'";
		Cursor cursor = null;
		cursor = dB.rawQuery(selectQuery, null);
		
		while(cursor.moveToNext()){
			if (cursor.getString(cursor.getColumnIndex("CaseQtySum"))!=null)
				return cursor.getString(cursor.getColumnIndex("CaseQtySum"));
			else
				return "0";
			
		}
		
		return "0";
	}
	
	
	public String getTotalPieceQtyReturns(String refno) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		//String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREASON +" WHERE "+dbHelper.FREASON_NAME+"='"+name+"'";
		@SuppressWarnings("static-access")
		String selectQuery = "select sum(FD.PiceQty) as PieceQtySum from ftranDet FD where refno ='"+ refno  +"'";
		Cursor cursor = null;
		cursor = dB.rawQuery(selectQuery, null);
		
		while(cursor.moveToNext()){
			if (cursor.getString(cursor.getColumnIndex("PieceQtySum"))!=null)
				return cursor.getString(cursor.getColumnIndex("PieceQtySum"));
			else
				return "0";
			
		}
		
		return "0";
	}
	
	public String getTotalAmountReturns(String refno) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}

		//String selectQuery = "SELECT * FROM " + dbHelper.TABLE_FREASON +" WHERE "+dbHelper.FREASON_NAME+"='"+name+"'";
		@SuppressWarnings("static-access")
		String selectQuery = "select sum(FD.Amt) as totalAmtSum from fdayexpdet FD where refno ='"+ refno  +"'";
		Cursor cursor = null;
		cursor = dB.rawQuery(selectQuery, null);
		
		while(cursor.moveToNext()){
			if (cursor.getString(cursor.getColumnIndex("totalAmtSum"))!=null)
				return cursor.getString(cursor.getColumnIndex("totalAmtSum"));
			else
				return "0";
			
		}
		
		return "0";
	}
	
	
	
	
}
