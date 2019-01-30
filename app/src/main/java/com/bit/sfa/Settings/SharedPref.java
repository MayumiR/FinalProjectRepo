package com.bit.sfa.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPref {
	private  static SharedPreferences sharedPref;

	private static SharedPref pref;

	public static SharedPref getInstance(Context context) {
		if(pref == null) {
			pref = new SharedPref(context);
		}

		return pref;
	}

	public SharedPref(Context context) {
		sharedPref = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);

	}

	public void setGlobalVal(String mKey, String mValue) {
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(mKey, mValue);
		editor.commit();
	}

	public String getGlobalVal(String mKey) {
		return sharedPref.getString(mKey, "***");
	}

	public void clear_shared_pref()

	{
		sharedPref.edit().remove("preKeyRoute").commit();
		sharedPref.edit().remove("PrekeyCusCode").commit();
		sharedPref.edit().remove("PrekeyCusName").commit();
		sharedPref.edit().remove("PrekeyCustomer").commit();
//		sharedPref.edit().remove("keyRefNo").commit();
//		sharedPref.edit().remove("keySubRefNo").commit();
//		sharedPref.edit().remove("keyGroup").commit();
//		sharedPref.edit().remove("keyDiscount").commit();
//		sharedPref.edit().remove("Order_Start_Time").commit();
//		sharedPref.edit().remove("keyDiscPer").commit();
//		sharedPref.edit().remove("keyDiscUsed").commit();
//        sharedPref.edit().remove("keySubRefNo").commit();
//        sharedPref.edit().remove("keyRefNo").commit();
		//sharedPref.edit().clear().commit();
	}
}
