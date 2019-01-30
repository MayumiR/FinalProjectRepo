package com.bit.sfa.DataControl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bit.sfa.Models.ServerDatabase;
import com.bit.sfa.Settings.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 6/19/2018.
 */

public class ServerDatabaseDS {
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    Context context;
    private String TAG="swadeshi";

    public ServerDatabaseDS (Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    @SuppressWarnings("static-access")
    public int createOrUpdateServerDB(ArrayList<ServerDatabase>  serverdb) {
        int serverdbID =0;
        if(dB == null){
            open();
        }else if(!dB.isOpen()){
            open();
        }
        Cursor cursor = null;

        try{

            for (ServerDatabase serverDatabase : serverdb) {


                String selectQuery = "SELECT * FROM " + dbHelper.TABLE_SERVER_DB
                        + " WHERE " + dbHelper.SERVER_DB_NAME + " = '" +serverDatabase.getDatabaseName() + "'";

                cursor = dB.rawQuery(selectQuery, null);

                ContentValues values = new ContentValues();
                //values.put(dbHelper.SERVER_DB_ID, serverdb.getId());
                values.put(dbHelper.SERVER_DB_NAME, serverDatabase.getDatabaseName());


                int count = cursor.getCount();
                Log.i(TAG , "Cursor ServerDatabase count:" + count);

                if(count>0){
                    serverdbID = dB.update(dbHelper.TABLE_SERVER_DB, values,dbHelper.SERVER_DB_NAME + " =?",
                            new String[] { String.valueOf(serverDatabase.getDatabaseName())});
                }else{
                    serverdbID = (int) dB.insert(dbHelper.TABLE_SERVER_DB, null, values);
                }
            }
        }finally {
            if (cursor !=null) {
                cursor.close();
            }
            dB.close();
        }
        return serverdbID;

    }

    //select all data from server DB
    public ArrayList<String> getAllDatabaseName() {
        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        ArrayList<String> db_name = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_SERVER_DB ;

        Cursor cursor = null;
        cursor = dB.rawQuery(selectQuery, null);

        while(cursor.moveToNext()){

            db_name.add(cursor.getString(cursor.getColumnIndex(dbHelper.SERVER_DB_NAME)));

        }

        return db_name;
    }

}
