package com.bit.sfa.DataControl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.SalOrdPrintPre;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

public class SalOrdPrintDS {
	private SQLiteDatabase dB;
	private DatabaseHelper dbHelper;
	Context context;
	private String TAG="swadeshi";
	
	
	public SalOrdPrintDS (Context context){
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		dB = dbHelper.getWritableDatabase();
	}
	
	
	
	
	public ArrayList<SalOrdPrintPre> getAllListPreview(String currentRefNo) {
		if (dB == null) {
			open();
		} else if (!dB.isOpen()) {
			open();
		}
		
		ArrayList<SalOrdPrintPre> list = new ArrayList<SalOrdPrintPre>();
		//String currentRefNo = "EXASA/000010";
		String selectQuery = "SELECT FD.RefNo,FD.Itemcode,FD.Types,FD.Amt,FD.Qty,FD.PiceQty,FD.Types,FH.DebCode,FDE.DebName,FH.DisPer as a,FD.SeqNo,FH.TxnDate,FD.DisAmt,FH.TotalDis,FH.TotalAmt,FH.BTotalDis,FD.Disper as b,FD.disamt,FH.TotalDis,IM.Itemcode,IM.ItemName,FH.Remarks,FH.delvDate,FDE.DebAdd1,FDE.DebAdd2,FDE.DebAdd3,FDE.DebTele,FD.sellprice,FD.piceQty,FH.TotalitemDis,FH.TotMkrAmt,IM.Pack,FD.DisValAmt FROM FOrdHed FH INNER JOIN FOrddet FD ON FD.RefNo = FH.RefNo INNER JOIN fDebtor FDE ON FDE.DebCode = FH.DebCode INNER JOIN fItem IM ON IM.Itemcode = FD.Itemcode WHERE  FD.RefNo = '"
					+ currentRefNo  + "' ORDER BY FD.TxnType" ;
		
		Cursor cursor = dB.rawQuery(selectQuery, null);
		while(cursor.moveToNext()){
			
			SalOrdPrintPre aSalOrdPrintPre=new SalOrdPrintPre();
			
			aSalOrdPrintPre.setSAL_PRINT_REF_NO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REFNO)));
			aSalOrdPrintPre.setSAL_PRINT_ITEMCODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_CODE)));
			aSalOrdPrintPre.setSAL_PRINT_TRAN_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TYPE)));
			aSalOrdPrintPre.setSAL_PRINT_DETAIL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_AMT)));
			aSalOrdPrintPre.setSAL_PRINT_CASE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_QTY)));
			aSalOrdPrintPre.setSAL_PRINT_PIECE_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_QTY)));
			aSalOrdPrintPre.setSAL_PRINT_TRAN_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_TYPE)));
			aSalOrdPrintPre.setSAL_PRINT_DEBCODE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DEB_CODE)));
			aSalOrdPrintPre.setSAL_PRINT_DEBNAME(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_NAME)));
			aSalOrdPrintPre.setSAL_HEAD_DIS_CALL(cursor.getString(cursor.getColumnIndex("a")));
			aSalOrdPrintPre.setSAL_PRINT_SEQNO(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_SEQNO)));
			aSalOrdPrintPre.setSAL_PRINT_TXN_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TXN_DATE)));
			aSalOrdPrintPre.setSAL_PRINT_DISAMT_DETAIL(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_AMT)));	
			aSalOrdPrintPre.setSAL_PRINT_TOTAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_AMT)));	
			aSalOrdPrintPre.setSAL_PRINT_TOTALDIS_HEAD_B(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_AMT)));	//error
			aSalOrdPrintPre.setSAL_PRINT_TOTAL_DIS_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_B_TOTAL_DIS)));	
			aSalOrdPrintPre.setSAL_PRINT_DETAIL_DISPER(cursor.getString(cursor.getColumnIndex("b")));	
			aSalOrdPrintPre.setSAL_PRINT_TOTAL_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_AMT)));
			aSalOrdPrintPre.setSAL_PRINT_TOTALDIS_HEAD(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTALDIS)));
			aSalOrdPrintPre.setSAL_PRINT_ITEMCODE(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_CODE)));
			aSalOrdPrintPre.setSAL_PRINT_ITEMNAME(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_ITEM_NAME)));
			aSalOrdPrintPre.setSAL_PRINT_REMARKS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_REMARKS)));
			aSalOrdPrintPre.setSAL_PRINT_DELVIDATE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_DELV_DATE)));
			aSalOrdPrintPre.setSAL_PRINT_DEBADD1(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD1)));
			aSalOrdPrintPre.setSAL_PRINT_DEBADD2(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD2)));
			aSalOrdPrintPre.setSAL_PRINT_DEBADD3(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_ADD3)));
			aSalOrdPrintPre.setSAL_PRINT_DEBTELE(cursor.getString(cursor.getColumnIndex(dbHelper.FDEBTOR_TELE)));
			aSalOrdPrintPre.setSAL_PRINT_SELL_PRICE(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_SELL_PRICE)));
			aSalOrdPrintPre.setSAL_PRINT_TOTAL_UNIT_QTY(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_QTY)));
			aSalOrdPrintPre.setSAL_PRINT_ITEM_DIS(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOTAL_ITM_DIS)));
			aSalOrdPrintPre.setPRINT_TOTALMKT_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOT_MKR_AMT)));
			aSalOrdPrintPre.setPRINT_TOTALMKT_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDHED_TOT_MKR_AMT)));
			aSalOrdPrintPre.setSAL_PRINT_UNITS(cursor.getString(cursor.getColumnIndex(dbHelper.FITEM_PACK)));
			aSalOrdPrintPre.setSAL_DIS_VALUE_AMT(cursor.getString(cursor.getColumnIndex(dbHelper.FORDDET_DIS_VAL_AMT)));
			

			aSalOrdPrintPre.setSAL_HEAD_DIS(cursor.getString(cursor.getColumnIndex("a")));
			
		
			Log.v("Testi", aSalOrdPrintPre.getSAL_PRINT_REF_NO());
			
			list.add(aSalOrdPrintPre);
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
		String selectQuery = "select sum(FD.CaseQty) as CaseQtySum from forddet FD where refno = '"+ refno  +"'";
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
		String selectQuery = "select sum(FD.PiceQty) as PieceQtySum from forddet FD where refno ='"+ refno  +"'";
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
	
	
	
	
}
