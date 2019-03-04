package com.bit.sfa.Settings;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bit.sfa.DataControl.ControlDS;
import com.bit.sfa.DataControl.DownloadTaskListener;
import com.bit.sfa.DataControl.Downloader;
import com.bit.sfa.DataControl.FmSalRepDS;
import com.bit.sfa.DataControl.OrdDetDS;
import com.bit.sfa.DataControl.OrdHedDS;
import com.bit.sfa.DataControl.RouteDS;
import com.bit.sfa.DataControl.SQLiteBackUp;
import com.bit.sfa.DataControl.SalRepDS;
import com.bit.sfa.DataControl.ServerDatabaseDS;
import com.bit.sfa.DefView.Home;
import com.bit.sfa.Models.FmSalRep;
import com.bit.sfa.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsActivity extends AppCompatActivity implements DownloadTaskListener {

    ArrayList<ContentItem> objects;

    // Shared Preferences variables
    public static final String SETTINGS = "SETTINGS";
    public static SharedPreferences localSP;

    // web service connection URL (SVC)
    public static String connURLsvc = "/KFDWebServices/KFDWebServicesRest.svc";
    private Button btncontinue;
    private Context context = this;

    @SuppressLint({"Recycle", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // getActionBar().setIcon(new
        // ColorDrawable(getResources().getColor(android.R.color.transparent)));
        // set title

        btncontinue = (Button) findViewById(R.id.btncont);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.in, R.anim.exit);
            }
        });
        setTitleBarColor();

        localSP = getSharedPreferences(SETTINGS, Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
        // getActionBar().setDisplayHomeAsUpEnabled(true);

        TypedArray icons = getResources().obtainTypedArray(R.array.listView_icons_for_settingd);

        objects = new ArrayList<ContentItem>();

        objects.add(new ContentItem("Sync Configuration", "config url, server database and header database configuration", icons.getResourceId(0, -1)));
        objects.add(new ContentItem("Printer Configuration", "Enter your MAC address to connect", icons.getResourceId(1, -1)));
        objects.add(new ContentItem("Reset Order Details", "Please enter From date and To date", icons.getResourceId(2, -1)));
        objects.add(new ContentItem("SQLite Database", "DB backups and restore", icons.getResourceId(3, -1)));
        objects.add(new ContentItem("Sales Representative Details", "Reps informations", icons.getResourceId(4, -1)));
        objects.add(new ContentItem("Sales Rep Route", "Route area and code", icons.getResourceId(5, -1)));

        ListViewDataAdapter adapter = new ListViewDataAdapter(getApplicationContext(), objects);

        ListView lv = (ListView) findViewById(R.id.settings_list_view);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view2, int position, long id) {

                String title = objects.get(position).getName().toString();
                Context context = SettingsActivity.this;

                switch (position) {
                    case 0: // Sync Configuration

//					syncDialogbox(context, title);
//					Toast.makeText(getApplicationContext(), objects.get(position).getName() + " selected",
//							Toast.LENGTH_SHORT).show();

                        if (localSP.getString("Sync_Status", "").toString().equals("Success")) {
                            LayoutInflater layoutInflater = LayoutInflater.from(context);

                            View promptView = layoutInflater.inflate(R.layout.settings_sqlite_password_layout, null);

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                            alertDialogBuilder.setView(promptView);

                            final EditText password = (EditText) promptView.findViewById(R.id.et_password);

                            alertDialogBuilder
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            if (password.getText().toString().toString().equals("admin@dspl")) {
                                                //Intent myIntent = new Intent(context,ImportActivity.class);
                                                //startActivity(myIntent);
                                                syncDialogbox(SettingsActivity.this, "Sync Configuration");

                                            } else {
                                                Toast.makeText(getApplicationContext(), "Invalid Password.", Toast.LENGTH_LONG).show();

                                                //dialog.cancel();
                                            }

                                        }
                                    })
                                    .setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();


                                                }
                                            });

                            AlertDialog alertD = alertDialogBuilder.create();

                            alertD.show();
                        } else {
                            syncDialogbox(context, title);
                            Toast.makeText(getApplicationContext(), objects.get(position).getName() + " selected",
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 1: // Printer Configuration
                    {
                        if (localSP.getString("Sync_Status", "").toString().equals("Success")) {
                            printerDialogbox(context, title);
                            Toast.makeText(getApplicationContext(), objects.get(position).getName() + " selected",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please do the Initial sync to activate this app",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;

                    case 2: // Reschedule Uploading Details
                    {
                        if (localSP.getString("Sync_Status", "").toString().equals("Success")) {
                            rescheduleUploadDialogbox(context, title);
                            Toast.makeText(getApplicationContext(), objects.get(position).getName() + " selected",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please do the Initial sync to activate this app",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;

                    case 3: // SQLite Database
                    {
                        if (localSP.getString("Sync_Status", "").toString().equals("Success")) {
                            sqliteDatabaseDialogbox(context, title);
                            Toast.makeText(getApplicationContext(), objects.get(position).getName() + " selected",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please do the Initial sync to activate this app",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;

                    case 4: // Sales Representative Details
                    {
                        if (localSP.getString("Sync_Status", "").toString().equals("Success")) {
                            repsDetailsDialogbox(context, title);
                            Toast.makeText(getApplicationContext(), objects.get(position).getName() + " selected",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please do the Initial sync to activate this app",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;

                    case 5: // Sales Rep Route
                    {
                        if (localSP.getString("Sync_Status", "").toString().equals("Success")) {
                            routeAreaDialogbox(context, title);
                            Toast.makeText(getApplicationContext(), objects.get(position).getName() + " selected",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please do the Initial sync to activate this app",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;
                }

            }
        });
    }

    public boolean validate(String mac) {
        Pattern p = Pattern.compile("^([a-fA-F0-9]{2}[:-]){5}[a-fA-F0-9]{2}$");
        Matcher m = p.matcher(mac);
        return m.find();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.act_settings_menu_next, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // next
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

//            case R.id.action_next:
//                // here want chk conditions
//                SalRepDS ds = new SalRepDS(getApplicationContext());
//                if (!ds.getCurrentRepCode().equals("")
//                        && localSP.getString("Sync_Status", "").toString().equals("Success")) {
//
//                    if (localSP.getString("first_login", "").toString().equals("Success")) {
//
//                        Intent mainActivity = new Intent(SettingsActivity.this, MainActivity.class);
//                        startActivity(mainActivity);
//                        finish();
//
//                    } else {
//                        Intent mainActivity = new Intent(SettingsActivity.this, LoginActivity.class);
//                        startActivity(mainActivity);
//                        finish();
//                    }
//                } else { // if(localSP.getString("Sync_Status",
//                    // "").toString().equals(""))
//                    Toast.makeText(getApplicationContext(), "Please do the Initial sync to activate this app.",
//                            Toast.LENGTH_LONG).show();
//                }
            // return true;
        }
        return false;
    }

    // Title bar color
    @SuppressLint("NewApi")
    private void setTitleBarColor() {
        ActionBar bar = getActionBar();
//        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4682B4")));
        // title
        //  createCutomActionBarTitle("System Configuration");
    }

    @SuppressLint("NewApi")
    private void createCutomActionBarTitle(String title) {

//        this.getActionBar().setDisplayShowCustomEnabled(true);
//        this.getActionBar().setDisplayShowTitleEnabled(false);
//
//        LayoutInflater inflator = LayoutInflater.from(this);
//        View v = inflator.inflate(R.layout.custom_action_bar, null);
//
//        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/coopbl.ttf");
//
//        TextView tvTitle = (TextView) v.findViewById(R.id.titleFragment1);
//        tvTitle.setText(title);
//        tvTitle.setTypeface(tf);
//
//        // assign the view to the actionbar
//        this.getActionBar().setCustomView(v);
    }

    // Alerts DialogBox

    private void syncTwoDialogbox(final Context context, String title) {

        final String sp_url = localSP.getString("URL", "").toString();
        String Dist_DB = localSP.getString("Dist_DB", "").toString();

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View promptView = layoutInflater.inflate(R.layout.settings_sync_two, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setView(promptView);

        final Spinner sp_DistDB = (Spinner) promptView.findViewById(R.id.spinner_dist_db);

        ServerDatabaseDS ds = new ServerDatabaseDS(context);

        List<String> list = ds.getAllDatabaseName();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_DistDB.setAdapter(dataAdapter);

        if (!Dist_DB.equals("")) {

            ArrayAdapter myAdap = (ArrayAdapter) sp_DistDB.getAdapter();
            int spinnerPosition = myAdap.getPosition(Dist_DB);
            sp_DistDB.setSelection(spinnerPosition);
        }

        alertDialogBuilder.setCancelable(false).setPositiveButton("Sync", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                String distDB = sp_DistDB.getSelectedItem().toString();

                SharedPreferencesClass.setLocalSharedPreference(context, "Dist_DB", distDB);

                // Syncing tasks
                String URL = "http://" + sp_url;

                boolean connectionStatus = new ConnectionDetector(getApplicationContext()).isConnectingToInternet();

                if (connectionStatus == true) {

                    if (Patterns.WEB_URL.matcher(URL).matches()) {

                        String downLoadURL = connURLsvc + "/fItemLoc/mobile123/"
                                + localSP.getString("Dist_DB", "").toString();
                        new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FITEMLOC, URL,
                                downLoadURL).execute();

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid URL Entered. Please Enter Valid URL.",
                                Toast.LENGTH_LONG).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();

                }

            }
        });

        // create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    /**
     * IP alert dialogbox
     */
    private void syncDialogbox(final Context context, String title) {
        //

        final String sp_url = localSP.getString("URL", "").toString();
        String spConsole_DB = localSP.getString("Console_DB", "").toString();

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View promptView = layoutInflater.inflate(R.layout.settings_sync_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setView(promptView);

        final EditText serverURL = (EditText) promptView.findViewById(R.id.et_server_url);
        final Spinner sp_serverDB = (Spinner) promptView.findViewById(R.id.spinner_server_db);

        ServerDatabaseDS ds = new ServerDatabaseDS(context);

        List<String> list = ds.getAllDatabaseName();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp_serverDB.setAdapter(dataAdapter);

        if (!sp_url.equals(""))
            serverURL.setText(sp_url);

        if (!spConsole_DB.equals("")) {

            ArrayAdapter myAdap = (ArrayAdapter) sp_serverDB.getAdapter();
            int spinnerPosition = myAdap.getPosition(spConsole_DB);
            sp_serverDB.setSelection(spinnerPosition);
        }

        // setup a dialog window
        alertDialogBuilder.setCancelable(false).setPositiveButton("Sync", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                // Do something
                String selectedDB = sp_serverDB.getSelectedItem().toString();

                SharedPreferencesClass.setLocalSharedPreference(context, "Console_DB", selectedDB);

                if (localSP.getString("MAC_Address", "No MAC Address").equals("No MAC Address")) {
                    GetMacAddress macAddress = new GetMacAddress();
                    SharedPreferencesClass.setLocalSharedPreference(context, "MAC_Address",
                            macAddress.getMacAddress(getApplicationContext()));
                }

                // Syncing tasks
                String URL = "http://" + sp_url;
                boolean connectionStatus = new ConnectionDetector(getApplicationContext()).isConnectingToInternet();
                if (connectionStatus == true) {

                    if (Patterns.WEB_URL.matcher(URL).matches()) {

                        try {

                            String FSALREP_URL = connURLsvc + "/fmSalRep/mobile123/"
                                    + localSP.getString("Console_DB", "").toString() + "/"
                                    + localSP.getString("MAC_Address", "").toString().replace(":", "");
                            // String
                            // FSALREP_URL=connURLsvc+"/fSalRep/mobile123/"+localSP.getString("Console_DB",
                            // "").toString()+"/f07959533ca3";
                            Log.v("## Testing URL ##", FSALREP_URL);

                            new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FMSALREP, URL,
                                    FSALREP_URL).execute();

                        } catch (Exception e) {

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid URL Entered. Please Enter Valid URL.",
                                Toast.LENGTH_LONG).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();

                }

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });

        // create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    /**
     * Printer Config alert dialog box
     */
    @SuppressWarnings("unused")
    private void printerDialogbox(final Context context, String title) {
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View promptView = layoutInflater.inflate(R.layout.settings_printer_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setView(promptView);

        final EditText serverURL = (EditText) promptView.findViewById(R.id.et_mac_address);

        serverURL.setText(localSP.getString("printer_mac_address", "").toString());

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Do something
                if (validate(serverURL.getText().toString().toUpperCase())) {
                    SharedPreferencesClass.setLocalSharedPreference(context, "printer_mac_address",
                            serverURL.getText().toString().toUpperCase());
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Invalid MAC Address Entered. Please Enter Valid MAC Address.", Toast.LENGTH_LONG).show();

                    // dialog.cancel();
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });

        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    /**
     * reschedule upload Config alert dialogbox
     */
    @SuppressWarnings("unused")
    private void rescheduleUploadDialogbox(final Context context, String title) {
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View promptView = layoutInflater.inflate(R.layout.settings_reschedule_uploading_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setView(promptView);

        EditText serverURL = (EditText) promptView.findViewById(R.id.et_mac_address);
        final DatePicker fromDatePicker = (DatePicker) promptView.findViewById(R.id.datePicker1);
        final DatePicker ToDatePicker = (DatePicker) promptView.findViewById(R.id.datePicker2);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        Log.v("Date", "From " + getDateFromDatePicker(fromDatePicker) + " To " + getDateFromDatePicker(ToDatePicker));

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        try {

                            Date date1 = dateFormat.parse(getDateFromDatePicker(fromDatePicker));
                            Date date2 = dateFormat.parse(getDateFromDatePicker(ToDatePicker));

                            if (date1.before(date2)) {
                                int hed = new OrdHedDS(context).DeleteOldOrders(getDateFromDatePicker(fromDatePicker), getDateFromDatePicker(ToDatePicker));

                                if (hed > 0) {
                                    int det = new OrdDetDS(context).DeleteOldOrders(getDateFromDatePicker(fromDatePicker), getDateFromDatePicker(ToDatePicker));
                                    int FreeIss = new OrdFreeIssueDS(context).DeleteOldOrders(getDateFromDatePicker(fromDatePicker), getDateFromDatePicker(ToDatePicker));
                                    Toast.makeText(getApplicationContext(), hed + " Orders Deleted Successfully", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), "No Orders To Delete For The Selected Date Range", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid Date Range", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception ex) {

                        } finally {

                        }


                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();


                            }
                        });

        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    /**
     * after download completed.
     */
    @Override
    public void onTaskCompleted(TaskType taskType, String result) {

        String URL = "http://" + localSP.getString("URL", "").toString(); // Server
        // Download
        // URL
        SalRepDS ds = new SalRepDS(getApplicationContext());

        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH) + 1;
        DecimalFormat df_month = new DecimalFormat("00");

        switch (taskType) {

            case FITEMLOC:
                Log.v("FITEMLOC", result.toString());

                String FFREESLAB_URL = connURLsvc + "/FItenrDet/mobile123/" + localSP.getString("Console_DB", "").toString()+ "/" + ds.getCurrentRepCode() + "/" + cyear
                        + "/" + df_month.format((double) cmonth);
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FITENRDET, URL, FFREESLAB_URL)
                        .execute();

                break;
            case FITENRDET:
                Log.v("FITENRHED", result.toString());
            {

                ControlDS controlDS = new ControlDS(getApplicationContext());
                if (controlDS.getSysType() == 1) {

                    String FITENRHED_URL = connURLsvc + "/fitenrHed/mobile123/"
                            + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode() + "/" + cyear
                            + "/" + df_month.format((double) cmonth);
                    Log.v("FITENRHED_URL", FITENRHED_URL);
                    new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FITENRHED, URL, FITENRHED_URL)
                            .execute();

                }
            }
            break;

            case FITENRHED:
                Log.v("FITENRHED", result.toString());
            {

                ControlDS controlDS = new ControlDS(getApplicationContext());
                if (controlDS.getSysType() == 2) {

                    String FITEDEBDET_URL = connURLsvc + "/fIteDebDet/mobile123/"
                            + localSP.getString("Dist_DB", "").toString() + "/" + ds.getCurrentRepCode() + "/" + cyear + "/"
                            + df_month.format((double) cmonth);
                    new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FITEDEBDET, URL, FITEDEBDET_URL)
                            .execute();

                } else if (controlDS.getSysType() == 1) {

                    String FITEDEBDET_URL = connURLsvc + "/fIteDebDet/mobile123/"
                            + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode() + "/" + cyear
                            + "/" + df_month.format((double) cmonth);
                    new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FITEDEBDET, URL, FITEDEBDET_URL)
                            .execute();

                }
            }
            break;

            case FITEDEBDET:
                Log.v("FITEDEBDET", result.toString());

                String FITEMPRI_URL = connURLsvc + "/fItemPri/mobile123/" + localSP.getString("Console_DB", "").toString()
                        + "/" + new SalRepDS(getApplicationContext()).getCurrentRepCode();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FITEMPRI, URL, FITEMPRI_URL)
                        .execute();

                break;

            case FITEMPRI:
                Log.v("FITEMPRI", result.toString());

                String FITEM_URL = connURLsvc + "/fItems/mobile123/" + localSP.getString("Console_DB", "").toString() + "/"
                        + new SalRepDS(getApplicationContext()).getCurrentRepCode();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FITEMS, URL, FITEM_URL).execute();

                break;

            case FITEMS: {
                String FCOMPANYSETTING_URL = connURLsvc + "/fCompanySettingM/mobile123/"
                        + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FCOMPANYSETTING, URL,
                        FCOMPANYSETTING_URL).execute();

            }
            break;
            case FCOMPANYSETTING:
                Log.v("FCOMPANYSETTING", result.toString());
                SalRepDS ds2 = new SalRepDS(getApplicationContext());
                String FrepDalo_URL = connURLsvc + "/FrepDalo/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + ds2.getCurrentRepCode();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FREPDALO, URL, FrepDalo_URL)
                        .execute();
                break;

            case FREPDALO:
                Log.v("FREPDALO", result.toString());


                SalRepDS sal = new SalRepDS(getApplicationContext());
                String FSup_URL = connURLsvc + "/fSupplier/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + sal.getCurrentRepCode();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FSUPPLIER, URL, FSup_URL)
                        .execute();
                break;

            case FSUPPLIER:
                Log.v("FSUPPLIER", result.toString());

                String FAREA_URL = connURLsvc + "/fArea/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FAREA, URL, FAREA_URL).execute();

                break;

            case FAREA:
                Log.v("FAREA", result.toString());
            {
                ControlDS controlDS = new ControlDS(getApplicationContext());
                SalRepDS repds = new SalRepDS(getApplicationContext());
                if (controlDS.getSysType() == 1) {

//                    String FLOCATIONS_URL = connURLsvc + "/fmLocations/mobile123/"
//                            + localSP.getString("Console_DB", "").toString() + "/" + repds.getCurrentRepCode();
//                    new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FLOCATIONS, URL, FLOCATIONS_URL)
//                            .execute();
//
//                }
//            }
//            break;
//
//            case FLOCATIONS: {
//                Log.v("FLOCATIONS", result.toString());

                    String FCOMPANYBRANCH_URL = connURLsvc + "/FCompanyBranch/mobile123/"
                            + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
                    new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FCOMPANYBRANCH, URL,
                            FCOMPANYBRANCH_URL).execute();
                }
            }
            break;
            case FCOMPANYBRANCH:
                Log.v("FCOMPANYBRANCH", result.toString());

                String FREASON_URL = connURLsvc + "/fReason/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FREASON, URL, FREASON_URL).execute();

                break;

            case FREASON: {
                Log.v("FREASON", result.toString());

                String FROUTE_URL = connURLsvc + "/fmRoute/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
                // String
                // FROUTE_URL=connURLsvc+"/fRoute/mobile123/"+localSP.getString("Console_DB",
                // "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FROUTE, URL, FROUTE_URL).execute();
            }
            break;

            case FROUTE: {
                Log.v("FROUTE", result.toString());

                // String
                // FBANK_URL=connURLsvc+"/fBank/mobile123/"+localSP.getString("Console_DB",
                // "").toString();
                // new Downloader(SettingsActivity.this, SettingsActivity.this,
                // TaskType.FBANK, URL, FBANK_URL).execute();

                ControlDS controlDS = new ControlDS(getApplicationContext());
                if (controlDS.getSysType() == 1) {

                    String FBANK_URL = connURLsvc + "/fExpense/mobile123/" + localSP.getString("Console_DB", "").toString();
                    new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FEXPENSE, URL, FBANK_URL).execute();

                    Log.v("TAG", "option 1");

                }

            }
            break;

//            case FBANK:
//                Log.v("FBANK", result.toString()); {
//
//                ControlDS controlDS = new ControlDS(getApplicationContext());
//                if (controlDS.getSysType() == 1) {
//
//                    String FDDBNOTE_URL = connURLsvc + "/fDdbNoteWithCondition/mobile123/"+ localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
//                    new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FDDBNOTE, URL, FDDBNOTE_URL)
//                            .execute();
//
//                }
//            }
//            break;

//            case FDDBNOTE:
//                Log.v("FDDBNOTE", result.toString());
//
//                String FEXPENSE_URL = connURLsvc + "/fExpense/mobile123/" + localSP.getString("Console_DB", "").toString();
//                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FEXPENSE, URL, FEXPENSE_URL)
//                        .execute();
//
//                break;
            case FEXPENSE:
                Log.v("FEXPENSE", result.toString());

                String FTOWN_URL = connURLsvc + "/fTown/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FTOWN, URL, FTOWN_URL).execute();

                break;

            case FTOWN:
                Log.v("FTOWN", result.toString());

                String FROUTEDET_URL = connURLsvc + "/fmRouteDet/mobile123/" + localSP.getString("Console_DB", "").toString()
                        + "/" + ds.getCurrentRepCode();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FROUTEDET, URL, FROUTEDET_URL)
                        .execute();
                break;
            case FROUTEDET:
                Log.v("FROUTEDET", result.toString());

                String FTYPE_URL = connURLsvc + "/fType/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FTYPE, URL, FTYPE_URL).execute();

                break;
            case FTYPE:
                Log.v("FTYPE", result.toString());

                String FGROUP_URL = connURLsvc + "/fGroup/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FGROUP, URL, FGROUP_URL).execute();

                break;
            case FGROUP:
                Log.v("FGROUP", result.toString());

                String FBRAND_URL = connURLsvc + "/fbrand/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FBRAND, URL, FBRAND_URL).execute();

                break;

            case FBRAND:
                Log.v("FBRAND", result.toString());

                String FINVDETL3_URL = connURLsvc + "/RepLastThreeInvDet/mobile123/"
                        + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FINVDETL3, URL, FINVDETL3_URL)
                        .execute();

                break;
            case FINVDETL3:
                Log.v("FINVHEDL3", result.toString());
            {

                String FCOST_URL = connURLsvc + "/fcost/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FCOST, URL, FCOST_URL).execute();
            }
            break;

            case FCOST:
                Log.v("FCOST", result.toString());
            {


                String FREPLOC_URL = connURLsvc + "/fRepLoc/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FREPLOC, URL, FREPLOC_URL).execute();

            }

            break;

            case FREPLOC:
                Log.v("FREPLOC", result.toString());
            {
                String FMITEMS_URL = connURLsvc + "/Fmitems/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FMITEMS, URL, FMITEMS_URL).execute();


            }
            break;
            case FMITEMS:
                Log.v("FMITEMS-", result.toString());

                String FMITEMS_DET_URL = connURLsvc + "/FmitemsDet/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FMITEMS_DET, URL, FMITEMS_DET_URL).execute();
                break;

            case FMITEMS_DET:
                Log.v("FMITEMS_DET", result.toString());

                String FCOUNTRYMGR_URL = connURLsvc + "/FCountrymgr/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FCOUNTRYMGR, URL, FCOUNTRYMGR_URL)
                        .execute();
                break;

            case FCOUNTRYMGR:

                Log.v("FMAREA", result.toString());

                String FMAREA_URL = connURLsvc + "/FmArea/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FMAREA, URL, FMAREA_URL)
                        .execute();
                break;
            case FMAREA:
                Log.v("FMAREASUB", result.toString());

                String FMAREASUB_URL = connURLsvc + "/fmAreaSub/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FMAREASUB, URL, FMAREASUB_URL)
                        .execute();
                break;
            case FMAREASUB:
                Log.v("FMDEBTOR", result.toString());

                String FMDEBTOR_URL = connURLsvc + "/FmDebtor/mobile123/" + localSP.getString("Console_DB", "").toString()+"/"+new FmSalRepDS(this).getCurrentRepCode().trim();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FMDEBTOR, URL, FMDEBTOR_URL)
                        .execute();
                break;
            case FMDEBTOR:
                Log.v("FMDEBTORDET", result.toString());

                String FMDEBTORDET_URL = connURLsvc + "/fmDebDet/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FMDEBTORDET, URL, FMDEBTORDET_URL)
                        .execute();
                break;
            case FMDEBTORDET:
                Log.v("FMEXP_GRP", result.toString());

                String FMEXP_GRP_URL = connURLsvc + "/fExpGrp/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FMEXP_GRP, URL, FMEXP_GRP_URL)
                        .execute();
                break;
            case FMEXP_GRP:
                Log.v("FITNDEBDET", result.toString());
                String FMISS_SUBDET_URL = connURLsvc + "/FmissSubDet/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FMISS_SUBDET, URL, FMISS_SUBDET_URL).execute();
                break;
            case FMISS_SUBDET:
                Log.v("FMISS_SUBDET", result.toString());
                String FMISS_HED_URL = connURLsvc + "/fmisshed/mobile123/" + localSP.getString("Console_DB", "").toString()+"/"+new FmSalRepDS(this).getCurrentRepCode().trim();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FMISS_HED, URL, FMISS_HED_URL).execute();
                break;

            case FMISS_HED:
                Log.v("FMISS_HED", result.toString());
                String FMISS_DET_URL = connURLsvc + "/fmissdet/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FMISS_DET, URL, FMISS_DET_URL).execute();
                break;
            case FMISS_DET:
                Log.v("FMISS_DET", result.toString());
                String FORDSTAT_URL = connURLsvc + "/FOrdStat/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
                new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FORDSTAT, URL, FORDSTAT_URL).execute();
                break;
            case FORDSTAT:
                Log.v("FREPLOC", result.toString());

                SharedPreferencesClass.setLocalSharedPreference(getApplicationContext(), "Sync_Status", "Success");
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                Toast.makeText(getApplicationContext(), "Sync has been completed successfully!", Toast.LENGTH_LONG).show();
                btncontinue.setVisibility(View.VISIBLE);


                break;

            case FMSALREP:
                try {
                    if (!result.toString().equals("")) {
                        if (Integer.parseInt(result.toString()) > 0) {
                            Log.v("FSALREP", result.toString());

                            String FCONTROL_URL = connURLsvc + "/fControl/mobile123/"
                                    + localSP.getString("Console_DB", "").toString();
                            new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FCONTROL, URL,
                                    FCONTROL_URL).execute();

                        } else {
                            Toast.makeText(getApplicationContext(), "Validation Failed!", Toast.LENGTH_LONG).show();
                        }
                        // }else if(result.toString().equals("")){
                        // Toast.makeText(getApplicationContext(), "Can't Find
                        // device MAC Address", Toast.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(), "Please try again
                        // later!", Toast.LENGTH_LONG).show();

                        //
                    } else if (result.toString().equals("FileNotFound")) {
                        Toast.makeText(getApplicationContext(), "Please try again!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.v("Exception", e.toString());
                    Toast.makeText(getApplicationContext(), "Validation Failed!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Invalid MAC Address or Invalid DB!", Toast.LENGTH_LONG).show();
                }
                break;

            case FCONTROL:
                Log.v("FCONTROL", result.toString());
                if (Integer.parseInt(result.toString()) == 1) {
                    ControlDS controlDS = new ControlDS(getApplicationContext());
                    SalRepDS repDS = new SalRepDS(this);
                    if (controlDS.getSysType() == 1) {

                        String downLoadURL = connURLsvc + "/fmItemLoc/mobile123/"
                                + localSP.getString("Console_DB", "").toString() + "/" + repDS.getCurrentRepLocCode();
                        new Downloader(SettingsActivity.this, SettingsActivity.this, TaskType.FITEMLOC, URL, downLoadURL)
                                .execute();

                    }

                } else if (Integer.parseInt(result.toString()) == 2) {

                    syncTwoDialogbox(SettingsActivity.this, "Please Select Dist DB");

                } else {
                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_LONG).show();
                }

                break;
            default:
                break;
        }

    }

    @SuppressWarnings({"deprecation", "unused"})
    public String getDateFromDatePicker(DatePicker datePicker) {

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear() - 1900;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatedDate = sdf.format(new Date(year, month, day));

        return formatedDate;

    }

    /**
     * SQLite Database Config alert dialogbox
     */
    @SuppressWarnings("unused")
    private void sqliteDatabaseDialogbox(final Context context, String title) {
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View promptView = layoutInflater.inflate(R.layout.settings_sqlite_database_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setView(promptView);

        final Button b_backups = (Button) promptView.findViewById(R.id.b_backups);
        final Button b_restore = (Button) promptView.findViewById(R.id.b_restore);

        b_backups.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.v("Backups", "OnClick");
                SQLiteBackUp backUp = new SQLiteBackUp(getApplicationContext());
                backUp.exportDB();
            }
        });

        b_restore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.v("Restore", "OnClick");
                LayoutInflater layoutInflater = LayoutInflater.from(context);

                View promptView = layoutInflater.inflate(R.layout.settings_sqlite_password_layout, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptView);

                final EditText password = (EditText) promptView.findViewById(R.id.et_password);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (password.getText().toString().toString().equals("adminrs@dspl")) {
                                    Intent myIntent = new Intent(context, ImportActivity.class);
                                    startActivity(myIntent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Invalid Password.", Toast.LENGTH_LONG).show();

                                    //dialog.cancel();
                                }

                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();


                                    }
                                });

                AlertDialog alertD = alertDialogBuilder.create();

                alertD.show();
                /*Intent myIntent = new Intent(context,ImportActivity.class);
                startActivity(myIntent);*/

            }
        });

        alertDialogBuilder.setCancelable(false).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });

        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    /**
     * Reps details alert dialogbox
     */
    @SuppressWarnings("unused")
    private void repsDetailsDialogbox(final Context context, String title) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View promptView = layoutInflater.inflate(R.layout.settings_reps_details_layout, null);

        final EditText etUserName = (EditText) promptView.findViewById(R.id.et_rep_username);
        final EditText etRepCode = (EditText) promptView.findViewById(R.id.et_rep_code);
        final EditText etPreFix = (EditText) promptView.findViewById(R.id.et_rep_prefix);
        final EditText etLocCode = (EditText) promptView.findViewById(R.id.et_rep_loc_code);
        final EditText etAreaCode = (EditText) promptView.findViewById(R.id.et_rep_area_code);
        final EditText etDealCode = (EditText) promptView.findViewById(R.id.et_rep_deal_code);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setView(promptView);

        SalRepDS ds = new SalRepDS(getApplicationContext());

        ArrayList<FmSalRep> Vre = ds.getSaleRepDetails();

        for (FmSalRep salRep : Vre) {
            etUserName.setText(salRep.getRepCodem());
            etRepCode.setText(salRep.getRepCodem());
            etPreFix.setText(salRep.getPrefix());
            etLocCode.setText(salRep.getRepCodem());
            etAreaCode.setText(salRep.getAreascode());
            etDealCode.setText(salRep.getAreascode());
        }

        alertDialogBuilder.setCancelable(false)
                // .setPositiveButton("OK", new
                // DialogInterface.OnClickListener() {
                // public void onClick(DialogInterface dialog, int id) {
                //
                //
                //
                //
                //
                // }
                // })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });

        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    /**
     * Reps details alert dialogbox
     */
    @SuppressWarnings("unused")
    private void routeAreaDialogbox(final Context context, String title) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View promptView = layoutInflater.inflate(R.layout.settings_route_plan, null);
        final Spinner sp_route_area = (Spinner) promptView.findViewById(R.id.sp_route_area);
        final EditText route_code = (EditText) promptView.findViewById(R.id.route_code);

        final RouteDS ds = new RouteDS(getApplicationContext());
        SalRepDS repDS = new SalRepDS(getApplicationContext());

        List<String> list = ds.getAllRouteByRepCode(repDS.getCurrentRepCode());

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp_route_area.setAdapter(dataAdapter);

        String currentRoute = sp_route_area.getSelectedItem().toString();

        if (!currentRoute.equals("")) {

            ArrayAdapter myAdap = (ArrayAdapter) sp_route_area.getAdapter();
            int spinnerPosition = myAdap.getPosition(localSP.getString("Current_Route", "").toString());
            sp_route_area.setSelection(spinnerPosition);
        }

        if (!currentRoute.equals(""))
            route_code.setText(ds.getRouteCodeByRoute(currentRoute));

        sp_route_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String currentRoute = sp_route_area.getSelectedItem().toString();

                if (!currentRoute.equals(""))
                    route_code.setText(ds.getRouteCodeByRoute(currentRoute));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setView(promptView);

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                SharedPreferencesClass.setLocalSharedPreference(getApplicationContext(), "Current_Route",
                        sp_route_area.getSelectedItem().toString());

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });

        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

}
