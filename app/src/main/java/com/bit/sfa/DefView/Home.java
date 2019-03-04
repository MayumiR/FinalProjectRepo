package com.bit.sfa.DefView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bit.sfa.Customer.AsyncTaskListener;
import com.bit.sfa.Customer.CustomerRegMain;
import com.bit.sfa.Customer.UploadNewCustomer;
import com.bit.sfa.DataControl.ControlDS;
import com.bit.sfa.DataControl.DownloadTaskListener;
import com.bit.sfa.DataControl.Downloader;
import com.bit.sfa.DataControl.FmSalRepDS;
import com.bit.sfa.DataControl.IResponseListener;
import com.bit.sfa.DataControl.NewCustomerDS;
import com.bit.sfa.DataControl.OrdHedDS;
import com.bit.sfa.DataControl.SQLiteBackUp;
import com.bit.sfa.DataControl.SalRepDS;
import com.bit.sfa.DataControl.ServerDatabaseDS;
import com.bit.sfa.DataControl.UploadTaskListener;
import com.bit.sfa.DataControl.UtilityContainer;
import com.bit.sfa.DayInfo.FragDayInfo;
import com.bit.sfa.Expenses.FragExpenses;
import com.bit.sfa.Models.Debtor;
import com.bit.sfa.Models.FmDebtor;
import com.bit.sfa.Models.FmSalRep;
import com.bit.sfa.Models.NewCustomer;
import com.bit.sfa.Models.OrdHed;
import com.bit.sfa.Models.PreSalesMapper;
import com.bit.sfa.Models.RouteDS;
import com.bit.sfa.PromoSale.FragSalesOrders;
import com.bit.sfa.PromoSale.PromoSaleManagement;
import com.bit.sfa.PromoSale.UploadPromoOrder;
import com.bit.sfa.R;

import com.bit.sfa.Settings.ConnectionDetector;
import com.bit.sfa.Settings.ContentItem;
import com.bit.sfa.Settings.GetMacAddress;
import com.bit.sfa.Settings.ImportActivity;
import com.bit.sfa.Settings.ListViewDataAdapter;
import com.bit.sfa.Settings.SettingsActivity;
import com.bit.sfa.Settings.SharedPref;
import com.bit.sfa.Settings.SharedPreferencesClass;
import com.bit.sfa.Settings.TaskType;
import com.bit.sfa.Settings.UserSessionManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.bit.sfa.Settings.TaskType.UPLOAD_NEW_CUSTOMER;

public class Home extends AppCompatActivity implements AsyncTaskListener, DownloadTaskListener , IResponseListener, UploadTaskListener{

    private TextView mTextMessage;
    public static SharedPreferences localSP;
    public static final String SETTINGS = "SETTINGS";
    // web service connection URL (SVC)
    public static String connURLsvc = "/KFDWebServices/KFDWebServicesRest.svc";
    //debtor
    public Debtor selectedDebtor = null;
    public FmDebtor mselectedDebtor = null;
    public boolean FreeTapped = false;
    //fordhed
    public OrdHed selectedOrdHed = null;
    //ftranHed
    public int cusPosition = 0;
    public int gpsseq = 0;
    private Context context = this;
    private String RepRouteCode;
    public String TAG = "Home";

    public static String SAcustomer,SAroute;
    List<String> resultList;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    changeFragment(0);

                    return true;
                case R.id.navigation_sales:

                    salesMenu();

                    return true;
                case R.id.navigation_tools:
                    managementTools();

                    return true;
                case R.id.navigation_logout:
                    Logout();
                    return true;
            }
            return false;
        }
    };
    public static BottomNavigationView navigation;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        resultList = new ArrayList<>();
        //set home frgament
        changeFragment(0);

        localSP = getSharedPreferences(SETTINGS, 0);

        //get rep details and save
        SalRepDS repDS = new SalRepDS(context);
        ArrayList<FmSalRep> salReps = repDS.getSaleRepDetails();
        FmSalRep salRep = salReps.get(0);
        SharedPreferencesClass.setLocalSharedPreference(context, "SR", salRep.getRepCodem());

    }

    public void Logout() {

        final Dialog Ldialog = new Dialog(Home.this);
        Ldialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Ldialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Ldialog.setContentView(R.layout.logout);

        Ldialog.show();
        //logout
        Ldialog.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSessionManager sessionManager = new UserSessionManager(context);
                sessionManager.Logout();
                finish();

            }
        });


    }

    public void managementTools() {
        final Dialog dialog = new Dialog(Home.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.management_tools);

        //sync
        dialog.findViewById(R.id.Sync).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                syncMasterDataDialog(context);
            }
        });

        //upload
        dialog.findViewById(R.id.Upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Upload Transactions To ERP
                syncDialog(context);
            }
        });


        dialog.show();

    }

    private void syncDialog(final Context context) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage("Are you sure, Do you want to upload data?");

        alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialogBuilder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @SuppressWarnings("unchecked")
            public void onClick(DialogInterface dialog, int id) {

                boolean connectionStatus = new ConnectionDetector(context).isConnectingToInternet();
                if (connectionStatus == true) {

                    NewCustomerDS customerDS = new NewCustomerDS(context);
                    ArrayList<NewCustomer> newCustomers = customerDS.getUnsyncRecord();

                    new UploadNewCustomer(context, Home.this, UPLOAD_NEW_CUSTOMER, newCustomers).execute();

                } else
                    Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    private void syncMasterDataDialog(final Context context) {
        final String sp_url = localSP.getString("URL", "").toString();
        // String spConsole_DB = localSP.getString("Console_DB", "").toString();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage("Are you sure, Do you want to Sync Master Data?");

        alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialogBuilder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @SuppressWarnings("unchecked")
            public void onClick(DialogInterface dialog, int id) {
                if (localSP.getString("MAC_Address", "No MAC Address").equals("No MAC Address")) {
                    GetMacAddress macAddress = new GetMacAddress();
                    SharedPreferencesClass.setLocalSharedPreference(context, "MAC_Address",
                            macAddress.getMacAddress(context));
                }

                String URL = "http://" + sp_url;

                boolean connectionStatus = new ConnectionDetector(context).isConnectingToInternet();
                if (connectionStatus == true) {

                    if (isAllUploaded()) {
                        dialog.dismiss();
                        try {

                            String FSALREP_URL = connURLsvc + "/fmSalRep/mobile123/"
                                    + localSP.getString("Console_DB", "").toString() + "/"
                                    + localSP.getString("MAC_Address", "").toString().replace(":", "");
                            // String
                            // FSALREP_URL=connURLsvc+"/fSalRep/mobile123/"+localSP.getString("Console_DB",
                            // "").toString()+"/f07959533ca3";
                            Log.v("## Testing URL ##", FSALREP_URL);

                            new Downloader(Home.this, Home.this, TaskType.FMSALREP, URL,
                                    FSALREP_URL).execute();
                        } catch (Exception e) {
                            Log.e("## ErrorIn2ndSync ##", e.toString());
                        }
                    } else {
                        Toast.makeText(context, "Please Upload All Transactions", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    private boolean isAllUploaded() {
        Boolean allUpload = false;
        OrdHedDS hedDS = new OrdHedDS(context);

        ArrayList<PreSalesMapper> ordHedList = hedDS.getAllUnSyncOrdHed();
        // FDayExpHedDS expHedDS = new FDayExpHedDS(getActivity());
        // ArrayList<expen> ordHedList = hedDS.getAllUnSyncOrdHed();

        if (ordHedList.isEmpty()) {
            allUpload = true;
        } else {
            allUpload = false;
        }

        return allUpload;
    }

    public void salesMenu() {

        final Dialog dialog = new Dialog(Home.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.sales_menu);

        final Button psale, dayinfo, new_cus, expense;
        psale = (Button) dialog.findViewById(R.id.presale);
        new_cus = (Button) dialog.findViewById(R.id.new_cus);
        expense = (Button) dialog.findViewById(R.id.expense);
        dayinfo = (Button) dialog.findViewById(R.id.dinfo);

        psale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPref sharedPref = new SharedPref(context);
                if (sharedPref.getGlobalVal("dayStart").equalsIgnoreCase("Y")) {
                    changeFragment(1);
                } else {
                    Toast.makeText(context, "Please add the Day start entry first", Toast.LENGTH_SHORT).show();
                }


                dialog.dismiss();
            }
        });

        new_cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPref sharedPref = new SharedPref(context);
                if (sharedPref.getGlobalVal("dayStart").equalsIgnoreCase("Y")) {
                    changeFragment(2);
                } else {
                    Toast.makeText(context, "Please add the Day start entry first", Toast.LENGTH_SHORT).show();
                }


                dialog.dismiss();
            }
        });


        dayinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(4);
                dialog.dismiss();
            }
        });

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(5);
                dialog.dismiss();
            }
        });


        dialog.show();

    }

    @SuppressLint("ResourceType")
    public void settingsMenu() {

        final Dialog dialog = new Dialog(Home.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.sett_menu);


        SharedPreferencesClass preferencesClass = new SharedPreferencesClass();


        TypedArray icons = getResources().obtainTypedArray(R.array.listView_icons_for_settingd);

        final ArrayList<ContentItem> objects = new ArrayList<ContentItem>();

        objects.add(new ContentItem("Sync Configuration", "config url, server database and header database configuration", icons.getResourceId(0, -1)));
        // objects.add(new ContentItem("Printer Configuration", "Enter your MAC address to connect", icons.getResourceId(1, -1)));
        // objects.add(new ContentItem("Reset Order Details", "Please enter From date and To date", icons.getResourceId(2, -1)));
        objects.add(new ContentItem("SQLite Database", "DB backups and restore", icons.getResourceId(3, -1)));
        objects.add(new ContentItem("Sales Representative Details", "Reps informations", icons.getResourceId(4, -1)));
        objects.add(new ContentItem("Sales Rep Route", "Route area and code", icons.getResourceId(5, -1)));

        ListViewDataAdapter adapter = new ListViewDataAdapter(getApplicationContext(), objects);

        ListView lv = (ListView) dialog.findViewById(R.id.settings_list_view);

        lv.setAdapter(adapter);

        dialog.findViewById(R.id.Closebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view2, int position, long id) {

                String title = objects.get(position).getName().toString();
                Context context = Home.this;

                switch (position) {
                    case 0: // Sync Configuration


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

//                                                Intent myIntent = new Intent(context, ImportActivity.class);
//                                                startActivity(myIntent);

                                                syncDialogbox(Home.this, "Sync Configuration");

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

                        }
                        break;

                    case 1: // DB
                    {
//
                        if (localSP.getString("Sync_Status", "").toString().equals("Success")) {
                            sqliteDatabaseDialogbox(context, title);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please do the Initial sync to activate this app",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;

                    case 2: // Sales Representative Details
                    {
                        ViewRepProfile();
                    }
                    break;

                    case 3: // Sales Rep Route
                    {
                        viewRouteInfo();
                    }
                    break;

                    case 4: // Sales Representative Details
                    {

                    }
                    break;

                    case 5: // Sales Rep Route
                    {


                    }
                    break;
                }

            }
        });

        dialog.show();

    }

    public void ViewRepProfile() {
        final Dialog repDialog = new Dialog(context);
        repDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        repDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        repDialog.setCancelable(false);
        repDialog.setCanceledOnTouchOutside(false);
        repDialog.setContentView(R.layout.rep_profile);

        //initializations
        SalRepDS salRepDS = new SalRepDS(context);
        ArrayList<FmSalRep> salReps = salRepDS.getSaleRepDetails();

        FmSalRep rep = salReps.get(0);
        RepRouteCode = rep.getRouteCode(); // change this to route code

        TextView repname = (TextView) repDialog.findViewById(R.id.repname);
        repname.setText(rep.getRepNamem());
        TextView repcode = (TextView) repDialog.findViewById(R.id.repcode);
        repcode.setText(rep.getLocCode());
        TextView repPrefix = (TextView) repDialog.findViewById(R.id.repPrefix);
        repPrefix.setText(rep.getPrefix());
        TextView locCode = (TextView) repDialog.findViewById(R.id.locCode);
        locCode.setText(rep.getRepTele());
        TextView areaCode = (TextView) repDialog.findViewById(R.id.areaCode);
        areaCode.setText(rep.getAreascode());
        TextView dealCode = (TextView) repDialog.findViewById(R.id.dealCode);
        dealCode.setText(rep.getAreascode());

        //close
        repDialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repDialog.dismiss();
            }
        });

        repDialog.show();
    }

    public void viewRouteInfo() {
        final Dialog repDialog = new Dialog(context);
        repDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        repDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        repDialog.setCancelable(false);
        repDialog.setCanceledOnTouchOutside(false);
        repDialog.setContentView(R.layout.rep_route_profile);

        //initializations
        RouteDS routeDS = new RouteDS(context);
        String routes = routeDS.getRouteNameByCode(RepRouteCode);

        TextView routeName = (TextView) repDialog.findViewById(R.id.routeName);
        routeName.setText(routes);
        TextView routeCode = (TextView) repDialog.findViewById(R.id.routeCode);
        routeCode.setText(RepRouteCode);


        //close
        repDialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repDialog.dismiss();
            }
        });

        repDialog.show();
    }

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
                SQLiteBackUp backUp = new SQLiteBackUp(getApplicationContext());
                backUp.exportDB();
            }
        });

        b_restore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

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
                if (selectedDB.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Select a Database", Toast.LENGTH_LONG).show();
                } else {

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

                                String FSALREP_URL = connURLsvc + "/fSalRep/mobile123/"
                                        + localSP.getString("Console_DB", "").toString() + "/"
                                        + localSP.getString("MAC_Address", "").toString().replace(":", "");


                                new Downloader(Home.this, Home.this, TaskType.FMSALREP, URL, FSALREP_URL).execute();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                settingsMenu();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * To load fragments for sample
     *
     * @param position menu index
     */
    private void changeFragment(int position) {

        Fragment newFragment = null;

        if (position == 0) {
         //   newFragment = new FragHome();
            UtilityContainer.mLoadFragment(new FragHome(), Home.this);
        } else if (position == 1) {
            UtilityContainer.mLoadFragment(new FragSalesOrders(), Home.this);
        } else if (position == 2) {
            UtilityContainer.mLoadFragment( new CustomerRegMain(), Home.this);
         //   newFragment = new CustomerRegMain();

        } else if (position == 4) {
           // newFragment = new FragDayInfo();
            UtilityContainer.mLoadFragment(new FragDayInfo(), Home.this);
        } else if (position == 5) {
            UtilityContainer.mLoadFragment(new FragExpenses(), Home.this);

        }

//
//        getFragmentManager().beginTransaction().replace(
//                R.id.fragmentContainer, newFragment)
//                .commit();
    }


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
                new Downloader(Home.this, Home.this, TaskType.FITENRDET, URL, FFREESLAB_URL)
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
                    new Downloader(Home.this, Home.this, TaskType.FITENRHED, URL, FITENRHED_URL)
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
                    new Downloader(Home.this, Home.this, TaskType.FITEDEBDET, URL, FITEDEBDET_URL)
                            .execute();

                } else if (controlDS.getSysType() == 1) {

                    String FITEDEBDET_URL = connURLsvc + "/fIteDebDet/mobile123/"
                            + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode() + "/" + cyear
                            + "/" + df_month.format((double) cmonth);
                    new Downloader(Home.this, Home.this, TaskType.FITEDEBDET, URL, FITEDEBDET_URL)
                            .execute();

                }
            }
            break;

            case FITEDEBDET:
                Log.v("FITEDEBDET", result.toString());

                String FITEMPRI_URL = connURLsvc + "/fItemPri/mobile123/" + localSP.getString("Console_DB", "").toString()
                        + "/" + new SalRepDS(getApplicationContext()).getCurrentRepCode();
                new Downloader(Home.this, Home.this, TaskType.FITEMPRI, URL, FITEMPRI_URL)
                        .execute();

                break;

            case FITEMPRI:
                Log.v("FITEMPRI", result.toString());

                String FITEM_URL = connURLsvc + "/fItems/mobile123/" + localSP.getString("Console_DB", "").toString() + "/"
                        + new SalRepDS(getApplicationContext()).getCurrentRepCode();
                new Downloader(Home.this, Home.this, TaskType.FITEMS, URL, FITEM_URL).execute();

                break;

            case FITEMS: {
                String FCOMPANYSETTING_URL = connURLsvc + "/fCompanySettingM/mobile123/"
                        + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FCOMPANYSETTING, URL,
                        FCOMPANYSETTING_URL).execute();

            }
            break;
            case FCOMPANYSETTING:
                Log.v("FCOMPANYSETTING", result.toString());
                SalRepDS ds2 = new SalRepDS(getApplicationContext());
                String FrepDalo_URL = connURLsvc + "/FrepDalo/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + ds2.getCurrentRepCode();
                new Downloader(Home.this, Home.this, TaskType.FREPDALO, URL, FrepDalo_URL)
                        .execute();
                break;

            case FREPDALO:
                Log.v("FREPDALO", result.toString());


                SalRepDS sal = new SalRepDS(getApplicationContext());
                String FSup_URL = connURLsvc + "/fSupplier/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + sal.getCurrentRepCode();
                new Downloader(Home.this, Home.this, TaskType.FSUPPLIER, URL, FSup_URL)
                        .execute();
                break;

            case FSUPPLIER:
                Log.v("FSUPPLIER", result.toString());

                String FAREA_URL = connURLsvc + "/fArea/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FAREA, URL, FAREA_URL).execute();

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
                    new Downloader(Home.this, Home.this, TaskType.FCOMPANYBRANCH, URL,
                            FCOMPANYBRANCH_URL).execute();
                }
            }
            break;
            case FCOMPANYBRANCH:
                Log.v("FCOMPANYBRANCH", result.toString());

                String FREASON_URL = connURLsvc + "/fReason/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FREASON, URL, FREASON_URL).execute();

                break;

            case FREASON: {
                Log.v("FREASON", result.toString());

                String FROUTE_URL = connURLsvc + "/fmRoute/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
                // String
                // FROUTE_URL=connURLsvc+"/fRoute/mobile123/"+localSP.getString("Console_DB",
                // "").toString();
                new Downloader(Home.this, Home.this, TaskType.FROUTE, URL, FROUTE_URL).execute();
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
                    new Downloader(Home.this, Home.this, TaskType.FEXPENSE, URL, FBANK_URL).execute();

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
                new Downloader(Home.this, Home.this, TaskType.FTOWN, URL, FTOWN_URL).execute();

                break;

            case FTOWN:
                Log.v("FTOWN", result.toString());

                String FROUTEDET_URL = connURLsvc + "/fmRouteDet/mobile123/" + localSP.getString("Console_DB", "").toString()
                        + "/" + ds.getCurrentRepCode();
                new Downloader(Home.this, Home.this, TaskType.FROUTEDET, URL, FROUTEDET_URL)
                        .execute();
                break;
            case FROUTEDET:
                Log.v("FROUTEDET", result.toString());

                String FTYPE_URL = connURLsvc + "/fType/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FTYPE, URL, FTYPE_URL).execute();

                break;
            case FTYPE:
                Log.v("FTYPE", result.toString());

                String FGROUP_URL = connURLsvc + "/fGroup/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FGROUP, URL, FGROUP_URL).execute();

                break;
            case FGROUP:
                Log.v("FGROUP", result.toString());

                String FBRAND_URL = connURLsvc + "/fbrand/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FBRAND, URL, FBRAND_URL).execute();

                break;

            case FBRAND:
                Log.v("FBRAND", result.toString());

                String FINVDETL3_URL = connURLsvc + "/RepLastThreeInvDet/mobile123/"
                        + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
                new Downloader(Home.this, Home.this, TaskType.FINVDETL3, URL, FINVDETL3_URL)
                        .execute();

                break;
            case FINVDETL3:
                Log.v("FINVHEDL3", result.toString());
            {

                String FCOST_URL = connURLsvc + "/fcost/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
                new Downloader(Home.this, Home.this, TaskType.FCOST, URL, FCOST_URL).execute();
            }
            break;

            case FCOST:
                Log.v("FCOST", result.toString());
            {


                String FREPLOC_URL = connURLsvc + "/fRepLoc/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
                new Downloader(Home.this, Home.this, TaskType.FREPLOC, URL, FREPLOC_URL).execute();

            }

            break;

            case FREPLOC:
                Log.v("FREPLOC", result.toString());
            {
                String FMITEMS_URL = connURLsvc + "/Fmitems/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FMITEMS, URL, FMITEMS_URL).execute();


            }
            break;
            case FMITEMS:
                Log.v("FMITEMS-", result.toString());

                String FMITEMS_DET_URL = connURLsvc + "/FmitemsDet/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FMITEMS_DET, URL, FMITEMS_DET_URL).execute();
                break;

            case FMITEMS_DET:
                Log.v("FMITEMS_DET", result.toString());

                String FCOUNTRYMGR_URL = connURLsvc + "/FCountrymgr/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FCOUNTRYMGR, URL, FCOUNTRYMGR_URL)
                        .execute();
                break;

            case FCOUNTRYMGR:

                Log.v("FMAREA", result.toString());

                String FMAREA_URL = connURLsvc + "/FmArea/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FMAREA, URL, FMAREA_URL)
                        .execute();
                break;
            case FMAREA:
                Log.v("FMAREASUB", result.toString());

                String FMAREASUB_URL = connURLsvc + "/fmAreaSub/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FMAREASUB, URL, FMAREASUB_URL)
                        .execute();
                break;
            case FMAREASUB:
                Log.v("FMDEBTOR", result.toString());

                String FMDEBTOR_URL = connURLsvc + "/FmDebtor/mobile123/" + localSP.getString("Console_DB", "").toString()+"/"+new FmSalRepDS(this).getCurrentRepCode().trim();
                new Downloader(Home.this, Home.this, TaskType.FMDEBTOR, URL, FMDEBTOR_URL)
                        .execute();
                break;
            case FMDEBTOR:
                Log.v("FMDEBTORDET", result.toString());

                String FMDEBTORDET_URL = connURLsvc + "/fmDebDet/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FMDEBTORDET, URL, FMDEBTORDET_URL)
                        .execute();
                break;
            case FMDEBTORDET:
                Log.v("FMEXP_GRP", result.toString());

                String FMEXP_GRP_URL = connURLsvc + "/fExpGrp/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FMEXP_GRP, URL, FMEXP_GRP_URL)
                        .execute();
                break;
            case FMEXP_GRP:
                Log.v("FITNDEBDET", result.toString());
                String FMISS_SUBDET_URL = connURLsvc + "/FmissSubDet/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FMISS_SUBDET, URL, FMISS_SUBDET_URL).execute();
                break;
            case FMISS_SUBDET:
                Log.v("FMISS_SUBDET", result.toString());
                String FMISS_HED_URL = connURLsvc + "/fmisshed/mobile123/" + localSP.getString("Console_DB", "").toString()+"/"+new FmSalRepDS(this).getCurrentRepCode().trim();
                new Downloader(Home.this, Home.this, TaskType.FMISS_HED, URL, FMISS_HED_URL).execute();
                break;

            case FMISS_HED:
                Log.v("FMISS_HED", result.toString());
                String FMISS_DET_URL = connURLsvc + "/fmissdet/mobile123/" + localSP.getString("Console_DB", "").toString();
                new Downloader(Home.this, Home.this, TaskType.FMISS_DET, URL, FMISS_DET_URL).execute();
                break;
            case FMISS_DET:
                Log.v("FMISS_DET", result.toString());
                String FORDSTAT_URL = connURLsvc + "/FOrdStat/mobile123/" + localSP.getString("Console_DB", "").toString() + "/" + ds.getCurrentRepCode();
                new Downloader(Home.this, Home.this, TaskType.FORDSTAT, URL, FORDSTAT_URL).execute();
                break;
            case FORDSTAT:
                Log.v("FREPLOC", result.toString());

                SharedPreferencesClass.setLocalSharedPreference(getApplicationContext(), "Sync_Status", "Success");
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                Toast.makeText(getApplicationContext(), "Sync has been completed successfully!", Toast.LENGTH_LONG).show();
                //btncontinue.setVisibility(View.VISIBLE);


                break;

            case FMSALREP:
                try {
                    if (!result.toString().equals("")) {
                        if (Integer.parseInt(result.toString()) > 0) {
                            Log.v("FSALREP", result.toString());

                            String FCONTROL_URL = connURLsvc + "/fControl/mobile123/"
                                    + localSP.getString("Console_DB", "").toString();
                            new Downloader(Home.this, Home.this, TaskType.FCONTROL, URL,
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
                        new Downloader(Home.this, Home.this, TaskType.FITEMLOC, URL, downLoadURL)
                                .execute();

                    }

                }
//                else if (Integer.parseInt(result.toString()) == 2) {
//
//                    syncTwoDialogbox(SettingsActivity.this, "Please Select Dist DB");
//
//                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_LONG).show();
                }

                break;

            default:
                break;
        }
    }
    public void mUploadResult(String message) {

        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(Home.this);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setTitle("Upload Summary");

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                UtilityContainer.mLoadFragment(new PromoSaleManagement(), Home.this);
                dialog.cancel();
            }
        });
        android.app.AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
        alertD.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }
    public void bottomNav(Boolean cmd) {

        if (cmd == true) {

            navigation.setVisibility(View.GONE);
        } else {
            navigation.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTaskCompleted(TaskType taskType) {
       // resultList.addAll(tempList);

        switch (taskType) {

            case UPLOAD_NEW_CUSTOMER: {
                Toast.makeText(getApplicationContext(), "Customer records uploaded successfully!", Toast.LENGTH_LONG).show();
                ArrayList<PreSalesMapper> list = new OrdHedDS(Home.this).getAllUnSyncOrdHed();
                new UploadPromoOrder(Home.this, Home.this, TaskType.UPLOAD_PROMO_ORDER).execute(list);
            }
            break;

            default:
                break;
        }

    }

    @Override
    public void moveNextFragment_Pre() {
        FragmentManager manager = getSupportFragmentManager();
         PromoSaleManagement frag = (PromoSaleManagement) manager.findFragmentByTag(PromoSaleManagement.class.getSimpleName());
        frag.mMoveToHeader();
    }

    @Override
    public void onTaskCompleted(TaskType taskType, List<String> list) {
        resultList.addAll(list);

        switch (taskType) {



            case UPLOAD_PROMO_ORDER:{
                String msg = "";
                if(!resultList.isEmpty()) {
                    for (String s : resultList) {
                        msg += s;
                    }
                    resultList.clear();
                    mUploadResult(msg);
                }else{
                    msg = "Nothing to upload";
                    mUploadResult(msg);
                }
            }
            break;

            default:
                break;
        }
    }
}
