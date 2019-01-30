package com.bit.sfa.Settings;

/**
 * Created by Sathiyaraja on 8/2/2018.
 */

public class StaticData {

    public static String OrdNo = null;
    public static String ManuNo = null;
    public static String Route = null;
    public static String CustomerName = null;
    //rashmi -2018-08-30
    public static final String CONNECT_TO_WIFI = "WIFI";
    public static final String NOT_CONNECT = "NOT_CONNECT";
    public static int CONN_FLG = 0;


    public static String getOrdNo() {
        return OrdNo;
    }

    public static void setOrdNo(String ordNo) {
        OrdNo = ordNo;
    }

    public static String getManuNo() {
        return ManuNo;
    }

    public static void setManuNo(String manuNo) {
        ManuNo = manuNo;
    }

    public static String getRoute() {
        return Route;
    }

    public static void setRoute(String route) {
        Route = route;
    }

    public static String getCustomerName() {
        return CustomerName;
    }

    public static void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
}
