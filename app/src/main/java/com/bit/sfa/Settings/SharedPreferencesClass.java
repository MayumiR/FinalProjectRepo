package com.bit.sfa.Settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sathiyaraja on 6/19/2018.
 */

public class SharedPreferencesClass extends Activity {


    public static String SETTINGS = "SETTINGS";
    public static SharedPreferences localSharedPreferences;

    public static void setLocalSharedPreference(final Context con, String localSPKey, String localSPValue) {

        SharedPreferences localSP = con.getSharedPreferences(SETTINGS, 0);
        SharedPreferences.Editor localBackupEditor = localSP.edit();
        localBackupEditor.putString(localSPKey, localSPValue);
        localBackupEditor.apply();
    }

    public static  String getLocalSharedPreference(final Context con, String localSPKey, String localSPValue) {
        SharedPreferences localSP = con.getSharedPreferences(SETTINGS, 0);
        String value = localSP.getString(localSPKey, localSPValue);

        return value;
    }

    public boolean checkSyncState(final Context con, String localSPKey) {

        SharedPreferences localSP = con.getSharedPreferences(SETTINGS, 0);

        if (!localSP.getString(localSPKey, "na").equalsIgnoreCase("na")) {
            return true;
        } else {
            return false;
        }

    }

}
