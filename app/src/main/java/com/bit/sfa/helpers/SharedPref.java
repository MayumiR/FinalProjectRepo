package com.bit.sfa.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;

import com.ceylonlinux.chelcey.salespad.model.FreeCalculate;
import com.ceylonlinux.chelcey.salespad.model.FreeIssueItem;
import com.ceylonlinux.chelcey.salespad.model.FreeIssueItems;
import com.ceylonlinux.chelcey.salespad.model.FreeIssueType2;
import com.ceylonlinux.chelcey.salespad.model.Route;
import com.ceylonlinux.chelcey.salespad.model.User;
import com.ceylonlinux.chelcey.salespad.utils.TimeUtils;

import java.util.List;

/**
 * Created by TaZ on 12/11/14.
 * Functions to access shared preferences.
 */
public class SharedPref {

    private static final String LOG_TAG = SharedPref.class.getSimpleName();

    public static List<FreeIssueItem> freeIssueItemses1=null;


//    private Context context;
    private SharedPreferences sharedPref;

    private static SharedPref pref;

    public SharedPref() {
    }

    public static SharedPref getInstance(Context context) {
        if(pref == null) {
            pref = new SharedPref(context);
        }

        return pref;
    }

    private SharedPref(Context context) {
//        this.context = context;
        sharedPref = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
    }

    public void setLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("login_status", status).apply();
    }

    public boolean isLoggedIn() {
        return sharedPref.getBoolean("login_status", false);
    }

    public void storeLoginUser(User user) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_id", user.getId());
        editor.putString("user_name", user.getName());
        editor.putString("user_username", user.getUsername());
        editor.putString("user_position", user.getPosition());
        editor.putString("user_territory", user.getTerritory());
        editor.putString("user_contact", user.getContact());
        editor.putString("user_image_uri", user.getImageURI());
        editor.putInt("user_type", user.getType());
        editor.putInt("user_location_id", user.getLocationId());
        editor.putInt("sales_type", user.getSalesType());
        editor.putInt("web_user_id", user.getWebUserId());
        editor.putInt("distributor_id", user.getDistributorId());
        editor.putInt("distributor_location_id", user.getDistributorLocationId());
        editor.apply();
    }

    public void storefreeAmount(FreeCalculate freeCalculate){
         Log.d("free in the sherd perferances",freeCalculate.getFreeTotal()+"");
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("reId", freeCalculate.getRepId());
        editor.putString("freeTotal", freeCalculate.getFreeTotal() + "");
        editor.apply();

    }

    public FreeCalculate getFreeCalculate(){
        FreeCalculate freeCalculate = new FreeCalculate();
        freeCalculate.setRepId(sharedPref.getInt("repId", 0));

        freeCalculate.setFreeTotal(Double.parseDouble(sharedPref.getString("freeTotal", null)));

        return freeCalculate;
    }

//    public void  storeFreeIssue(FreeIssueItem freeIssueItems){
//        SharedPreferences.Editor editor = sharedPref.edit();
//      //  for(int i=0;i<freeIssueItems.size();i++){}
//        editor.putInt("freeIssueID",freeIssueItems.getFree_issue_id());
//        editor.putString("maxEligibleValue",freeIssueItems.getMax_eligible_value()+"");
//        editor.putStringSet("d4frehvrtn",freeIssueItems);




//    }



    public User getLoginUser() {

        User user = new User();
        user.setId(sharedPref.getInt("user_id", 0));
        user.setName(sharedPref.getString("user_name", null));
        user.setUsername(sharedPref.getString("user_username", null));
        user.setPosition(sharedPref.getString("user_position", null));
        user.setTerritory(sharedPref.getString("user_territory", null));
        user.setContact(sharedPref.getString("user_contact", null));
        user.setImageURI(sharedPref.getString("user_image_uri", null));
        user.setType(sharedPref.getInt("user_type", 0));
        user.setLocationId(sharedPref.getInt("user_location_id", 0));
        user.setSalesType(sharedPref.getInt("sales_type", 0));
        user.setWebUserId(sharedPref.getInt("web_user_id", 0));
        user.setDistributorLocationId(sharedPref.getInt("distributor_location_id", 0));
        user.setDistributorId(sharedPref.getInt("distributor_id", 0));

        if (user.getId() == 0) {
            return null;
        } else {
            return user;
        }
    }


    public long generateOrderId() {
        long time = System.currentTimeMillis();
        Log.wtf("ID", String.valueOf(sharedPref.getInt("user_location_id", 0)) + String.valueOf(time));
        long order_id = Long.parseLong(String.valueOf(sharedPref.getInt("user_location_id", 0)) + String.valueOf(time));
        return (order_id < 0 ? -order_id : order_id);
    }

    public void storeSelectedRoute(Route route) {
        if (route != null) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("selected_route_id", route.getRouteId());
            editor.putString("selected_route_code", route.getRouteCode());
            editor.putString("selected_route_name", route.getRouteName());
            editor.putFloat("selected_route_fixed_target", (float) route.getFixedTarget());
            editor.putFloat("selected_route_selected_target", (float) route.getSelectedTarget());
            editor.apply();
        }
    }

    public Route getSelectedRoute() {

        Route route = new Route(sharedPref.getInt("selected_route_id", 0),
                sharedPref.getString("selected_route_code", null),
                sharedPref.getString("selected_route_name", null),
                sharedPref.getFloat("selected_route_fixed_target", 0),
                sharedPref.getFloat("selected_route_selected_target", 0));
        if (route.getRouteId() != 0) {
            return route;
        }

        return null;
    }

    public void storePreviousRoute(Route route) {
        if (route != null) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("previous_route_id", route.getRouteId());
            editor.putString("previous_route_name", route.getRouteName());
            editor.putFloat("previous_route_fixed_target", (float) route.getFixedTarget());
            editor.putFloat("previous_route_selected_target", (float) route.getSelectedTarget());
            editor.apply();
        }
    }

//    public Route getPreviousRoute() {
//
//        Route route = new Route(sharedPref.getInt("previous_route_id", 0), sharedPref.getString("previous_route_name", null),
//                sharedPref.getFloat("previous_route_fixed_target", 0), sharedPref.getFloat("previous_route_selected_target", 0));
//        if (route.getRouteId() != 0) {
//            return route;
//        }
//
//        return null;
//    }

    public void clearPref() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("login_status", false);
        storePreviousRoute(getSelectedRoute());
        editor.putInt("selected_route_id", 0);
        editor.putString("selected_route_name", "");
        editor.putFloat("selected_route_fixed_target", 0);
        editor.putFloat("selected_route_selected_target", 0);
        editor.apply();
    }

    public int getSelectedOutletId() {
        return sharedPref.getInt("selected_out_id", 0);
    }

    public void setSelectedOutletId(int outletId) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("selected_out_id", outletId);
        editor.apply();
    }

    public int startDay() {

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("day_status", true);

        int session = sharedPref.getInt("local_session", 0) + 1;
        editor.putInt("local_session", session);

        long timeOut = TimeUtils.getDayEndTime(System.currentTimeMillis());

        Log.d(LOG_TAG, "Setting timeout time at " + TimeUtils.formatDateTime(timeOut));

        editor.putLong("login_timeout", timeOut);

        editor.apply();

        return session;
    }

    public long getLoginTimeout() {
        return sharedPref.getLong("login_timeout", 0);
    }

    public void endDay() {
        Log.d(LOG_TAG, "Ending Day");
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("day_status", false);
        storePreviousRoute(getSelectedRoute());
        editor.putInt("selected_route_id", 0);
        editor.putString("selected_route_name", null);
        editor.putFloat("selected_route_fixed_target", 0);
        editor.putFloat("selected_route_selected_target", 0);
        editor.apply();
    }

    public int getLocalSessionId() {
        return sharedPref.getInt("local_session", 0);
    }

//    public void setDayStatus(boolean isDayStarted) {
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putBoolean("day_status", isDayStarted);
//        editor.apply();
//    }

    public boolean isDayStarted() {
        return sharedPref.getBoolean("day_status", false);
    }

    public void setTransferToDealerList(boolean flag) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("transfer_to_dlist",flag);
        editor.apply();
    }

    public boolean getTransferToDealerList(boolean inverse) {

        boolean result = sharedPref.getBoolean("transfer_to_dlist", false);

        if(inverse){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("transfer_to_dlist", false);
            editor.apply();
        }

        return result;
    }

    public boolean validForLogin(int outletId) {
        String key = "outlet_changed_".concat(String.valueOf(outletId));
        int updatedCount = sharedPref.getInt(key, 0);

        return updatedCount <= 2;
    }

    public void notifyOutletHasChanged(int outletId) {
        String key = "outlet_changed_".concat(String.valueOf(outletId));
        int updatedCount = sharedPref.getInt(key, 0);
        updatedCount++;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, updatedCount);
        editor.apply();
    }

    //<editor-fold desc="Time Management">
    public void createInitialTimeVariables(long correctTime) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("app_start_time", correctTime);
        editor.putLong("app_start_elapsed_time", SystemClock.elapsedRealtime());
        editor.putLong("time_differential", 0);
        editor.apply();
    }

    public void calculateTimeDifferential(long changedTime, long nowElapsedTime) {
        long initialTime = sharedPref.getLong("app_start_time", 0);
        long initialElapsedTime = sharedPref.getLong("app_start_elapsed_time", 0);
//        long initialDifferential = sharedPref.getLong("time_differential", 0);

        long currentCorrectTime = initialTime + (nowElapsedTime - initialElapsedTime);

        // The difference between the correct time and the changed time
        long differential = changedTime - currentCorrectTime;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("time_differential", differential);

        // Don't apply, commit. We need immediate change in the file.
        editor.commit();

    }

    public void compensateForDeviceReboot() {

        long newCorrectTime = System.currentTimeMillis() + sharedPref.getLong("time_differential", 0);
        long newElapsedTime = SystemClock.elapsedRealtime();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("app_start_time", newCorrectTime);
        editor.putLong("app_start_elapsed_time", newElapsedTime);

        // Don't apply, commit. We need immediate change in the file.
        editor.commit();
    }

    public long getRealTimeInMillis() {
        return System.currentTimeMillis() + sharedPref.getLong("time_differential", 0);
    }
    //</editor-fold>

    public void setPointingLocationIndex(int index) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("pointing_location", index);
        editor.apply();
    }

    public int getPointingLocationIndex() {
        return sharedPref.getInt("pointing_location", 0);
    }

    public void setBaseURL(String baseURL) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("baseURL", baseURL);
        editor.apply();
    }

    public String getBaseURL() {
        return sharedPref.getString("baseURL", "http://124.43.26.21");

    }

    public void setCurrentMillage(double millage){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("millage", (float) millage);
        editor.apply();
    }

    public double getPrevoiusMillage(){
        return sharedPref.getFloat("millage", 0);
    }

    public void addFreeIssueType2(FreeIssueType2 freeIssueType2){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("free_eligible_minimum_order_value", (float) freeIssueType2.free_eligible_minimum_order_value).commit();
        editor.putFloat("allowed_free_item_qty_per_month", (float) freeIssueType2.allowed_free_item_qty_per_month).commit();
        editor.putInt("free_item_id", freeIssueType2.free_item_id).commit();
        editor.putFloat("maximum_free_percentage_per_order", (float) freeIssueType2.maximum_free_percentage_per_order).commit();
        editor.putLong("valid_from", freeIssueType2.valid_from).commit();
        editor.putLong("valid_to", freeIssueType2.valid_to).commit();
        editor.putFloat("available_free_quantity", (float) freeIssueType2.available_free_quantity).commit();
    }

    public FreeIssueType2 getFreeIssueType2(){
        FreeIssueType2 fit2 = new FreeIssueType2();
        fit2.free_eligible_minimum_order_value = sharedPref.getFloat("free_eligible_minimum_order_value",-1);
        fit2.allowed_free_item_qty_per_month = sharedPref.getFloat("allowed_free_item_qty_per_month",-1);
        fit2.free_item_id = sharedPref.getInt("free_item_id", -1);
        fit2.maximum_free_percentage_per_order = sharedPref.getFloat("maximum_free_percentage_per_order", -1);
        fit2.valid_from = sharedPref.getLong("valid_from", -1);
        fit2.valid_to = sharedPref.getLong("valid_to", -1);
        fit2.available_free_quantity = sharedPref.getFloat("available_free_quantity", -1);
        return fit2;
    }

    public void setVersionName(String versionName) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("app_version_name", versionName).commit();
    }

    public String getVersionName(){
        return sharedPref.getString("app_version_name", "0.0.0");
    }
}
