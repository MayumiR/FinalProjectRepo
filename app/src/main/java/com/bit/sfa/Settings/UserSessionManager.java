package com.bit.sfa.Settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.bit.sfa.view.ActivityLogin;

/**
 * Created by Sathiyaraja on 7/6/2018.
 */

public class UserSessionManager {

    private Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public UserSessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("login", 0);
        editor = preferences.edit();
    }

    public void createLogin(String uname, String passw) {


        editor.putString("uname", uname);
        editor.putString("passw", passw);
        editor.commit();
    }

    public boolean isLogged() {

        boolean result = false;
        if (!preferences.getString("passw", "na").equalsIgnoreCase("na")) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public void Logout() {
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, ActivityLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}
