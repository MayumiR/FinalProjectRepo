package com.bit.sfa.DataControl;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.bit.sfa.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Himas on 5/29/2017.
 */

public class UtilityContainer {

//    public static void mRescheduleUploadDialogbox(final Context context) {
//
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View promptView = layoutInflater.inflate(R.layout.settings_reschedule_uploading_layout, null);
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//        alertDialogBuilder.setTitle("Schedule Uploads");
//        alertDialogBuilder.setView(promptView);
//
//        EditText serverURL = (EditText) promptView.findViewById(R.id.et_mac_address);
//        final DatePicker fromDatePicker = (DatePicker) promptView.findViewById(R.id.datePicker1);
//        final DatePicker ToDatePicker = (DatePicker) promptView.findViewById(R.id.datePicker2);
//
//        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                Log.v("Date", "From " + getDateFromDatePicker(fromDatePicker) + " To " + getDateFromDatePicker(ToDatePicker));
//            }
//        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.cancel();
//            }
//        });
//
//        AlertDialog alertD = alertDialogBuilder.create();
//
//        alertD.show();
//    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public static String getDateFromDatePicker(DatePicker datePicker) {

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = sdf.format(new Date(year, month, day));

        return formatedDate;
    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

//    public static void mPrinterDialogbox(final Context context) {
//
//        View promptView = LayoutInflater.from(context).inflate(R.layout.settings_printer_layout, null);
//        final EditText serverURL = (EditText) promptView.findViewById(R.id.et_mac_address);
//
//        final AlertDialog dialog = new AlertDialog.Builder(context)
//                .setView(promptView)
//                .setTitle("Printer MAC Address")
//                .setPositiveButton(android.R.string.ok, null)
//                .setNegativeButton(android.R.string.cancel, null)
//                .create();
//
//        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//
//            @Override
//            public void onShow(final DialogInterface dialog) {
//                Button bOk = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
//                Button bClose = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
//
//                bOk.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View view) {
//
//                        if (serverURL.length() > 0) {
//
//                            if (validate(serverURL.getText().toString().toUpperCase()))
//                                SharedPreferencesClass.setLocalSharedPreference(context, "printer_mac_address", serverURL.getText().toString().toUpperCase());
//                            else
//                                Toast.makeText(context, "Enter Valid MAC Address", Toast.LENGTH_LONG).show();
//                        } else
//                            Toast.makeText(context, "Type in the MAC Address", Toast.LENGTH_LONG).show();
//                    }
//                });
//
//                bClose.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//            }
//        });
//        dialog.show();
//    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public static boolean validate(String mac) {
        Pattern p = Pattern.compile("^([a-fA-F0-9]{2}[:-]){5}[a-fA-F0-9]{2}$");
        Matcher m = p.matcher(mac);
        return m.find();
    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

//    public static void mSQLiteDatabase(final Context context) {
//
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.settings_sqlite_database_layout);
//        dialog.setTitle("SQLite Backup/Restore");
//
//        final Button b_backups = (Button) dialog.findViewById(R.id.b_backups);
//        final Button b_restore = (Button) dialog.findViewById(R.id.b_restore);
//
//        b_backups.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                SQLiteBackUp backUp = new SQLiteBackUp(context);
//                backUp.exportDB();
//                dialog.dismiss();
//            }
//        });
//
//        b_restore.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.main_container, new SQLiteRestore());
//                ft.addToBackStack(null);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                ft.commit();
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

//    public static void mRepsDetailsDialogbox(final Context context) {
//
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View promptView = layoutInflater.inflate(R.layout.settings_reps_details_layout, null);
//
//        final EditText etUserName = (EditText) promptView.findViewById(R.id.et_rep_username);
//        final EditText etRepCode = (EditText) promptView.findViewById(R.id.et_rep_code);
//        final EditText etPreFix = (EditText) promptView.findViewById(R.id.et_rep_prefix);
//        final EditText etLocCode = (EditText) promptView.findViewById(R.id.et_rep_loc_code);
//
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//        alertDialogBuilder.setTitle("Sales Executive");
//        alertDialogBuilder.setView(promptView);
//        ArrayList<SalRep> Vre = new SalRepDS(context).getSaleRepDetails();
//
//        for (SalRep salRep : Vre) {
//            etUserName.setText(salRep.getREPCODE());
//            etRepCode.setText(salRep.getREPCODE());
//            etPreFix.setText(salRep.getPREFIX());
//        }
//
//        alertDialogBuilder.setCancelable(false).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.cancel();
//            }
//        });
//
//        AlertDialog alertD = alertDialogBuilder.create();
//        alertD.show();
//    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public static void mLoadFragment(Fragment fragment, Context context) {

        FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.in, R.anim.exit);
        ft.replace(R.id.fragmentContainer, fragment, fragment.getClass().getSimpleName());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public static boolean mHttpManager(String sURL, String sJsonObject) throws Exception {
        Log.v("## Json ##", sJsonObject);
        HttpPost requestfDam = new HttpPost(sURL);
        StringEntity entityfDam = new StringEntity(sJsonObject, "UTF-8");
        entityfDam.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        entityfDam.setContentType("application/json");
        requestfDam.setEntity(entityfDam);
        DefaultHttpClient httpClientfDamRec = new DefaultHttpClient();

        HttpResponse responsefDamRec = httpClientfDamRec.execute(requestfDam);
        HttpEntity entityfDamEntity = responsefDamRec.getEntity();
        InputStream is = entityfDamEntity.getContent();

        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            is.close();
            String result = sb.toString();
            String result_fDamRec = result.replace("\"", "");
            Log.e("response", "connect:" + result_fDamRec);
            if (result_fDamRec.trim().equals("200"))
                return true;
        }
        return false;
    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public static void ClearVanSharedPref(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("KeyRoute");
        editor.remove("KeyCostCode");
        editor.remove("KeyLocCode");
        editor.remove("KeyTouRef");
        editor.remove("KeyAreaCode");
        editor.remove("KeyRouteCode");
        editor.remove("KeyTourPos");
        editor.remove("keyCustomer");
        editor.remove("keyCusCode");
        editor.commit();
    }


    public static void PreClearSharedPref(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("PrekeyCustomer");
        editor.remove("preKeyRoute");
        editor.remove("preKeyCostCode");
        editor.remove("preKeyLocCode");
        editor.remove("preKeyTouRef");
        editor.remove("preKeyAreaCode");
        editor.remove("preKeyRouteCode");
        editor.remove("preKeyTourPos");
        editor.remove("PrekeyCusCode");
        editor.remove("PrekeyCreditDis");
        editor.remove("PrekeyPayType");
        editor.commit();
    }
  /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public static void ClearNonSharedPref(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("NonkeyCustomer");
        editor.remove("NonkeyCusCode");

        editor.commit();
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    public static void showKeyboard(Activity activity) {
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    public static void ClearReturnSharedPref(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("returnKeyRoute");
        editor.remove("returnKeyCostCode");
        editor.remove("returnKeyLocCode");
        editor.remove("returnKeyTouRef");
        editor.remove("returnKeyAreaCode");
        editor.remove("returnKeyRouteCode");
        editor.remove("returnKeyTourPos");
        editor.remove("returnkeyCustomer");
        editor.commit();
    }

    public static void ClearReceiptSharedPref(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("ReckeyPayModePos");
        editor.remove("ReckeyPayMode");
        editor.remove("ReckeyRemnant");
        editor.remove("ReckeyRecAmt");
        editor.remove("ReckeyCHQNo");
        editor.remove("ReckeyCustomer");
        editor.remove("ReckeyHeader");
        editor.remove("ReckeyCusCode");
        editor.commit();
    }


}
