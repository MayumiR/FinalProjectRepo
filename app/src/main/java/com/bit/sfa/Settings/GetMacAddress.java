package com.bit.sfa.Settings;

import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Created by Sathiyaraja on 6/19/2018.
 */

public class GetMacAddress {
    public String getMacAddress(Context context) {
        WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String macAddress = wimanager.getConnectionInfo().getMacAddress();
        if (macAddress == null) {
            macAddress = "No MAC Address";
        }
        return macAddress;
    }
}
