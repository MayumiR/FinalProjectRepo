package com.bit.sfa.helpers;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bit.sfa.Models.CustomNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Rashmi on 1/11/2018.
 * Handles network based functions.
 */
public class NetworkFunctions {

    private final String LOG_TAG = NetworkFunctions.class.getSimpleName();

    private final SharedPref pref;

    /**
     * The base URL to POST/GET the parameters to. The function names will be appended to this
     */
    private String baseURL;

    //private User user;//comment because error

    public NetworkFunctions(Context context) {
         pref = SharedPref.getInstance(context);
        String domain = pref.getBaseURL();
        Log.wtf("baseURL>>>>>>>>>",domain);
        baseURL = domain + "chelcey/android_service/";
      //  user = pref.getLoginUser();//comment because error
    }
//comment because error
//    public void setUser(User user) {
//        this.user = user;
//    }

    /**
     * This function will POST username and password and will return a the response JSON
     * from the server.
     *
     * @param username The string of the entered username
     * @param password The string of the entered password
     * @return The response as a String
     * @throws IOException Throws if unable to reach the server
     */
    public String authenticate(String username, String password) throws IOException {

        List<CustomNameValuePair> params = new ArrayList<>();
        params.add(new CustomNameValuePair("username", username));
        params.add(new CustomNameValuePair("password", password));

        Log.d(LOG_TAG, "Authenticating : " + baseURL + "user_login" + params);

        return postToServer(baseURL + "user_login", params);
    }

//    public String getRoutesAndOutlets() throws IOException {
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("Id_territory", String.valueOf(user.getTerritoryId())));
//
//        Log.d(LOG_TAG, "Getting Routes and Outlets");
//
//        return postToServer(baseURL + "getOutlet", params);
//    }
//
//    public String getItemCategories() throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//
//        Log.d(LOG_TAG, "Getting Item categories");
//
//        return postToServer(baseURL + "getItemDetails", params);
//    }
//
//    public String getStockDetails(int repType) throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("user_location_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("user_type_id", String.valueOf(repType)));
//
//        Log.d(LOG_TAG, "Getting stock details");
//
//        return postToServer(baseURL + "stock", params);
//    }
//
//    public String getBanks() throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("user_location_id", String.valueOf(user.getLocationId())));
//
//        Log.d(LOG_TAG, "Getting banks");
//
//        return getFromServer(baseURL + "getBank", params);
//    }
//
//    public String requestLoading(JSONArray request, long requestTime) throws IOException {
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("distributor_location_id", String.valueOf(user.getDistributorLocationId())));
//        params.add(new CustomNameValuePair("user_id_ref", String.valueOf(user.getWebUserId())));
//        params.add(new CustomNameValuePair("jsonString", request.toString()));
//        params.add(new CustomNameValuePair("time", String.valueOf(requestTime)));
//
//        Log.d(LOG_TAG, "Requesting loading");
//
//        return postToServer(baseURL + "mobileRequested", params);
//    }
//
//    public String requestDistributorLoading(JSONArray request, long requestTime) throws IOException {
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("user_id_ref", String.valueOf(user.getWebUserId())));
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("dis_uid", String.valueOf(user.getDistributorId())));
//        params.add(new CustomNameValuePair("distributor_location_id", String.valueOf(user.getDistributorLocationId())));
//        params.add(new CustomNameValuePair("jsonString", request.toString()));
//        params.add(new CustomNameValuePair("time", String.valueOf(requestTime)));
//
//
//        Log.d(LOG_TAG, "Distributor Requesting loading");
//
//        return postToServer(baseURL + "save_purchase_service", params);
//    }
//
//    public String getProductTypes() throws IOException {
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//
//        Log.d(LOG_TAG, "Getting product types");
//
//        return postToServer(baseURL + "productType", params);
//    }
//
//    public String getFlavours() throws IOException {
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//
//        Log.d(LOG_TAG, "Getting product flavours");
//
//        return postToServer(baseURL + "productFlaver", params);
//    }
//
//    public String syncUnproductive(UnproductiveCall unproductiveCall) throws IOException {
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("jsonString", unproductiveCall.getUnproductiveCallAsJSON().toString()));
//
//        Log.d(LOG_TAG, "POSTing unproductive call");
//
//        return postToServer(baseURL + "unproductive", params);
//    }
//
//    public String getAddressOfLocation(Location location) throws IOException {
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("latlng", String.valueOf(location.getLatitude()) + ", " + String.valueOf(location.getLongitude())));
//        params.add(new CustomNameValuePair("sensor", "true"));
//
//        Log.d(LOG_TAG, "Requesting location details");
//
//        return getFromServer("http://maps.googleapis.com/maps/api/geocode/json", params);
//    }
//
//    public String getExpenses() throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("user_id", String.valueOf(user.getWebUserId())));
//        params.add(new CustomNameValuePair("user_type_id", String.valueOf(user.getId())));
//        return getFromServer(baseURL + "get_expenses_user_wise", params);
//    }
//
//    public String sendExpenseHistory(ExpenseHistory expenseHistory) throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("user_id", String.valueOf(user.getWebUserId())));
//        params.add(new CustomNameValuePair("user_type_id", String.valueOf(user.getId())));
//        params.add(new CustomNameValuePair(DatabaseHandler.keyAllocatedExpenseWebId , String.valueOf(expenseHistory.getAllocatedExpenseWebId())));
//        params.add(new CustomNameValuePair(DatabaseHandler.keyCreatedTime           , String.valueOf(expenseHistory.getCreatedTime())));
//        params.add(new CustomNameValuePair(DatabaseHandler.keyComment               , String.valueOf(expenseHistory.getComment())));
//        params.add(new CustomNameValuePair(DatabaseHandler.keyExpenseValue          , String.valueOf(expenseHistory.getValue())));
//        params.add(new CustomNameValuePair(DatabaseHandler.keyPhotoEncode           , String.valueOf(expenseHistory.getPhotoEncode())));
//        return postToServer(baseURL + "send_expenses", params);
//    }
//
//    public String checkExpenseHistoryState(int expenseHistoryWebId) throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair(DatabaseHandler.keyExpenseHistoryWebId, String.valueOf(expenseHistoryWebId)));
//        return postToServer(baseURL + "check_expense_history_state", params);
//    }
//
//    public String getFreeIssueStructureType2() throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("web_user_id", String.valueOf(user.getWebUserId())));
//        return getFromServer(baseURL + "free_structure", params);
//    }
//
//    public String syncAttendanceDetails(Attendance attendance) throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("web_user_id", String.valueOf(user.getWebUserId())));
//        params.add(new CustomNameValuePair("user_position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("log_status", String.valueOf(attendance.getLogStatus())));
//        params.add(new CustomNameValuePair("time", String.valueOf(attendance.getLogTime())));
//        params.add(new CustomNameValuePair("millage", String.valueOf(attendance.getMileage())));
//        params.add(new CustomNameValuePair("latitude", String.valueOf(attendance.getLatitude())));
//        params.add(new CustomNameValuePair("longitude", String.valueOf(attendance.getLongitude())));
//        params.add(new CustomNameValuePair("location_string", (attendance.getLoc() == null || attendance.getLoc().isEmpty() ? "" : attendance.getLoc())));
//        params.add(new CustomNameValuePair("location_accuracy", String.valueOf(attendance.getLocationAccuracy())));
//        params.add(new CustomNameValuePair("location_type", String.valueOf(attendance.getLocationType())));
//
//        return postToServer(baseURL + "send_attendance", params);
//    }
//
//    public String syncCreatedOutlet(Outlet outlet) throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("jsonString", outlet.getCreatedOutletAsJSON(user.getWebUserId()).toString()));
//        params.add(new CustomNameValuePair("new_outlet", String.valueOf(outlet.getOutletId() <= 0)));
//        //params.add(new CustomNameValuePair("new_outlet", "\"" + String.valueOf(outlet.getOutletId() <= 0) + "\""));
//
//        Log.d(LOG_TAG, "POSTing created outlet");
//
//        return postToServer(baseURL + "save_new_outlet", params);
//    }
//
//    /**
//     * Returns the selected monthly report String
//     *
//     * @param time The required time in the format of yyyy-MM-dd
//     * @return The String of the response JSONObject
//     * @throws IOException Throws when unable to reach the server
//     */
//    public String getMonthlyReport(String time) throws IOException {
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("date", time));
//        params.add(new CustomNameValuePair("user_id", String.valueOf(user.getWebUserId())));
//
//        Log.d(LOG_TAG, "Getting monthly report");
//
//        return postToServer(baseURL + "get_monthly_statement", params);
//    }
//
//    public String syncOrder(Order order) throws IOException, JSONException {
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("user_id", String.valueOf(user.getId())));
//        params.add(new CustomNameValuePair("territory_id", String.valueOf(user.getTerritoryId())));
//        String jsonString = order.getOrderAsJSON(pref.getVersionName()).toString();
//        params.add(new CustomNameValuePair("jsonString", jsonString));
//        params.add(new CustomNameValuePair("session_id", "0"));
//        params.add(new CustomNameValuePair("user_status", "1"));
//        params.add(new CustomNameValuePair("sales_type", String.valueOf(user.getSalesType())));
//
//        Log.d(LOG_TAG, "Syncing order JSON : " + jsonString);
//
//        return postToServer(baseURL + "insert_order", params);
//    }
//
//    public String syncPayments(List<CashPayment> cashPayments, List<Cheque> cheques) throws IOException, JSONException {
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("user_id", String.valueOf(user.getId())));
//        params.add(new CustomNameValuePair("session_id", "0"));
//        params.add(new CustomNameValuePair("user_status", "1"));
//
//        JSONArray cashCheckPaymentJasonArray = new JSONArray();
//
//        if (cashPayments != null) {
//            for (CashPayment cashPayment : cashPayments) {
//                cashCheckPaymentJasonArray.put(cashPayment.getCashPaymentAsJSON());
//            }
//        }
//
//        if (cheques != null) {
//            for (Cheque cheque : cheques) {
//                cashCheckPaymentJasonArray.put(cheque.getChequePaymentAsJSON());
//            }
//        }
//
//        params.add(new CustomNameValuePair("cash_check_payment", cashCheckPaymentJasonArray.toString()));
//
//        Log.d(LOG_TAG, "Syncing payments");
//
//        return postToServer(baseURL + "save_payments", params);
//    }
//
//    public String getOutletTypes() throws IOException {
//
//        Log.d(LOG_TAG, "Getting outlet types");
//
//        return getFromServer(baseURL + "get_outlet_type", null);
//    }
//
//    public String getOutletClasses() throws IOException {
//
//        Log.d(LOG_TAG, "Getting outlet classes");
//
//        return getFromServer(baseURL + "outletClass", null);
//    }
//
//    public String getDiscountsAndFreeIssues() throws IOException {
//
//        Log.d(LOG_TAG, "Getting free issue and discount structures");
//
//        return getFromServer(baseURL + "freeissue", null);
//    }
//
//    public String fetchOrderDetails(long invoiceId) throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("sales_order_code", String.valueOf(invoiceId)));
//
//        Log.d(LOG_TAG, "Fetching order details");
//
//        return postToServer(baseURL + "get_invoice_details", params);
//    }
//
//    public String syncDayRouteAmountPlans(List<DayPlanHolder> planHolders) throws IOException {
//
//        JSONArray planHoldersArray = new JSONArray();
//
//        for (DayPlanHolder planHolder : planHolders) {
//            planHoldersArray.put(planHolder.toJSON());
//        }
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("territory_id", String.valueOf(user.getTerritoryId())));
//        params.add(new CustomNameValuePair("user_id", String.valueOf(user.getWebUserId())));
//        params.add(new CustomNameValuePair("dis_current_location_id", String.valueOf(user.getDistributorLocationId())));
//        params.add(new CustomNameValuePair("dis_uid", String.valueOf(user.getDistributorId())));
//        params.add(new CustomNameValuePair("jsonString", planHoldersArray.toString()));
//
//        Log.d(LOG_TAG, "Syncing Day-Route_Amount plan details");
//
//        return postToServer(baseURL + "save_target_plan_new", params);
//    }
//
//    public String updateDayRouteAmountPlan(DayPlanHolder planHolder) throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("jsonString", planHolder.toJSON().toString()));
//
//        return postToServer(baseURL + "update_day_target_plan_new", params);
//    }
//
//    public String getDayRouteAmountPlan(String date) throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("target_date", date));
//
//        return postToServer(baseURL + "get_day_target_plan_new", params);
//    }
//
//    public String getTargetAndAchievementByItem(String dateString) throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//        params.add(new CustomNameValuePair("month", dateString));
//        return postToServer(baseURL + "target_vs_achiment_item", params);
//    }
//
//    public String syncTrackingData(@NonNull List<TrackingData> trackingDatas) throws IOException {
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//
//        JSONArray dataArray = new JSONArray();
//        for (TrackingData trackingData : trackingDatas) {
//            dataArray.put(trackingData.toJSON());
//        }
//
//        Log.wtf("TRACKING DATA", dataArray.toString());
//
//        params.add(new CustomNameValuePair("jsonString", dataArray.toString()));
//
////        Log.d(LOG_TAG, "Syncing tracking data");
////        Log.d(LOG_TAG, "POSTing Data\n" + params);
//
//        return postToServer(baseURL + "save_tracking", params);
//    }
//
//    public String syncErrorLogs(@NonNull List<BugReport> bugReports, String appHash) throws IOException {
//
//        List<CustomNameValuePair> params = new ArrayList<>();
//        params.add(new CustomNameValuePair("app", appHash));
//        params.add(new CustomNameValuePair("position_id", String.valueOf(user.getLocationId())));
//
//        JSONArray dataArray = new JSONArray();
//        for (BugReport bugReport : bugReports) {
//            dataArray.put(bugReport.toJSON());
//        }
//
//        params.add(new CustomNameValuePair("bug_reports", dataArray.toString()));
//
//        // TODO : Create a function to sync errors
//        return postToServer(baseURL + "functionName", params);
//    }
//
//    public String unloadStock(User user, List<Item> unloadingItems) throws IOException {
//
//        List<CustomNameValuePair> values = new ArrayList<>();
//
//        values.add(new CustomNameValuePair("user_id", String.valueOf(user.getId())));
//        values.add(new CustomNameValuePair("user_web_id", String.valueOf(user.getWebUserId())));
//        values.add(new CustomNameValuePair("user_name", String.valueOf(user.getName())));
//        values.add(new CustomNameValuePair("user_location_id", String.valueOf(user.getLocationId())));
//        values.add(new CustomNameValuePair("distributor_id", String.valueOf(user.getDistributorId())));
//        values.add(new CustomNameValuePair("distributor_name", String.valueOf(user.getDistributorName())));
//        values.add(new CustomNameValuePair("distributor_location_id", String.valueOf(user.getDistributorLocationId())));
//
//        JSONArray unloadingDetailJSON = new JSONArray();
//        for (Item item : unloadingItems) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("unloading_item_no", item.getItemNo());
//            map.put("unloading_item_name", item.getItemName());
//            map.put("unloading_qty", item.getStockQty());
//            unloadingDetailJSON.put(new JSONObject(map));
//        }
//
//        values.add(new CustomNameValuePair("unloadingJSON", unloadingDetailJSON.toString()));
//
//        return postToServer(baseURL + "functionName", values);
//
//
//    }
//    //Rukshan
//    public List<FreeIssueItem> downloadSalesFreeIssueData() throws IOException, JSONException {
//
//        List<CustomNameValuePair> params = new ArrayList<CustomNameValuePair>();
//        // params.add(new CustomNameValuePair("position_id",  "1"));
//
//        String response =  postToServer(baseURL+"free_promotion",params);
//
//        Log.wtf("response:", response);
//
//        JSONArray freeIssueArray = new JSONArray(response);
//        List<FreeIssueItem> freeIssueItemses = new ArrayList<>();
//
//        for(int i=0;i<freeIssueArray.length();i++){
//
//            freeIssueItemses.add(FreeIssueItem.parseValues(freeIssueArray.getJSONObject(i)));
//            Log.wtf("downloading.......", "freeissue");
//        }
//
//        return freeIssueItemses;
//
//    }
//
//    public String downloadSalesFreeIssueData1()  {
//
//        List<CustomNameValuePair> params = new ArrayList<CustomNameValuePair>();
//        // params.add(new CustomNameValuePair("position_id",  "1"));
//
//
//        try {
//            return postToServer(baseURL+"free_promotion",params);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//            return  null;
//    }
//
//
//    public FreeCalculate dounwloadfreeCalculate(User user1)throws  IOException,JSONException{
//        List<CustomNameValuePair> params = new ArrayList<CustomNameValuePair>();
//
//
//      Log.wtf("user id network functions >>>>>>>>>>>",user1.getWebUserId()+"");
//
//        params.add(new CustomNameValuePair("user_id",user1.getWebUserId()+""));
//
//        String response = postToServer(baseURL+"free_calculate",params);
//
//        Log.wtf("response of freeCalculate",response);
//
//        JSONObject jsonObject = new JSONObject(response);
//        int repId = jsonObject.getInt("rep_id");
//        double freeTotal = jsonObject.getDouble("free_tot");
//
//
//        return new FreeCalculate(repId,freeTotal);}
//
    /**
     * This function POSTs params to server and gets the response.
     *
     * @param url    The URL to POST to
     * @param params The list of {@link CustomNameValuePair} of params to POST
     * @return The response from the server as a String
     * @throws IOException Throws if unable to connect to the server
     */
    private String postToServer(String url, List<CustomNameValuePair> params) throws IOException {

        String response = "";

        URL postURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection) postURL.openConnection();
        con.setConnectTimeout(10 * 1000);
        con.setReadTimeout(30 * 1000);
        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(generatePOSTParams(params));
        writer.flush();
        writer.close();
        os.close();

        con.connect();

        int status = con.getResponseCode();
        switch (status) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();

                response = sb.toString();
                Log.d(LOG_TAG, "Server Response : \n" + response);
        }

        return response;
    }

    /**
     * This function GETs params to server and returns the response.
     *
     * @param url    The URL to GET from
     * @param params The List<CustomNameValuePair></> of params to encode as GET parameters
     * @return The response string from the server
     * @throws IOException Throws if unable to connect to the server
     */
    private String getFromServer(String url, List<CustomNameValuePair> params) throws IOException {

        String response = "";

        URL postURL = new URL(url + generateGETParams(params));
//        Log.d(LOG_TAG, postURL.toString());
        HttpURLConnection con = (HttpURLConnection) postURL.openConnection();
        con.setConnectTimeout(10 * 1000);
        con.setReadTimeout(30 * 1000);
        con.setRequestMethod("GET");
        con.setDoInput(true);
//        con.setDoOutput(true);

        con.connect();

        int status = con.getResponseCode();
        switch (status) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();

                response = sb.toString();
                Log.d(LOG_TAG, "Server Response : \n" + response);
        }

        return response;
    }

    /**
     * This function will return the params as a queried String to POST to the server
     *
     * @param params The parameters to be POSTed
     * @return The formatted String
     * @throws UnsupportedEncodingException
     */
    private String generatePOSTParams(List<CustomNameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (CustomNameValuePair pair : params) {
            if (pair != null) {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
            }
        }

        Log.d(LOG_TAG, "Server REQUEST : " + result.toString());
        Log.d(LOG_TAG, "Upload size : " + result.toString().getBytes().length + " bytes");

        return result.toString();
    }

    /**
     * This function will return the params as a queried String to GET from the server
     *
     * @param params The parameters to encode as GET params
     * @return The formatted String
     */
    private String generateGETParams(List<CustomNameValuePair> params) {

        StringBuilder result = new StringBuilder().append("");
        boolean first = true;

        if (params != null) {
            for (CustomNameValuePair pair : params) {
                if (pair != null) {
                    if (first) {
                        first = false;
                        result.append("?");
                    } else
                        result.append("&");

                    result.append(pair.getName());
                    result.append("=");
                    result.append(pair.getValue());
                }
            }
        }

        Log.d(LOG_TAG, "Upload size : " + result.toString().getBytes().length + " bytes");

        return result.toString();
    }




}
