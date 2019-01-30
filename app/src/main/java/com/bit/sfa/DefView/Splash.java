package com.bit.sfa.DefView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bit.sfa.DataControl.DownloadTaskListener;
import com.bit.sfa.DataControl.Downloader;
import com.bit.sfa.DataControl.SalRepDS;
import com.bit.sfa.DataControl.ServerDatabaseDS;
import com.bit.sfa.Models.ServerDatabase;
import com.bit.sfa.R;
import com.bit.sfa.Settings.ConnectionDetector;
import com.bit.sfa.Settings.DatabaseHelper;
import com.bit.sfa.Settings.SettingsActivity;
import com.bit.sfa.Settings.SharedPreferencesClass;
import com.bit.sfa.Settings.TaskType;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Splash extends AppCompatActivity implements DownloadTaskListener {

    private ImageView logo;
    private static int SPLASH_TIME_OUT = 2000;
    private Context context = this;
    DatabaseHelper db;
    SharedPreferences localSP;
    private String spURL;
    private static ProgressDialog progressDialog;
    private String TAG = "DSS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        db=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase KFD_MEDI_;
        KFD_MEDI_ = db.getWritableDatabase();
        db.onUpgrade(KFD_MEDI_, 1, 2);

        logo = (ImageView)findViewById(R.id.logo);

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_up);
        logo.startAnimation(animation1);


        localSP = getSharedPreferences(SharedPreferencesClass.SETTINGS,0);

        if(!localSP.getString("URL", "").equals("")){

            new Handler().postDelayed(new Runnable() {

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    // close this activity
                    SalRepDS ds= new SalRepDS(getApplicationContext());
                    System.out.println("DSS"+ds.getCurrentRepCode());
                    if(!ds.getCurrentRepCode().equals("") && localSP.getString("Sync_Status", "").toString().equals("Success")){

                        Intent intent = new Intent(context, Home.class);
                        startActivity(intent);
                        finish();

                        overridePendingTransition(R.anim.in, R.anim.exit);

                    }else{

                        Intent mainActivity = new Intent(Splash.this, SettingsActivity.class);
                        startActivity(mainActivity);
                        finish();
                        overridePendingTransition(R.anim.in, R.anim.exit);

                    }
                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);

        }else{
            alertDialogbox(context);
        }

}
    private void alertDialogbox(final Context context){
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View promptView = layoutInflater.inflate(R.layout.ip_connection, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Please enter server URL");
        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);

        final EditText input = (EditText) promptView.findViewById(R.id.et_ip);

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //new PrefetchData().execute();
                        boolean connectionStatus = new ConnectionDetector(Splash.this).isConnectingToInternet();
                        spURL=input.getText().toString().trim();
                        String URL ="http://"+input.getText().toString().trim();

                        if (Patterns.WEB_URL.matcher(URL).matches()){

                            if(connectionStatus == true){
                                String downLoadURL="/KFDWebServices/KFDWebServicesRest.svc/GetdatabaseNames/mobile123";
                                new Downloader(Splash.this, Splash.this, TaskType.DATABASENAME,URL,downLoadURL).execute();

                            }else{

                                Toast.makeText(Splash.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                                reCallActivity();
                            }
                        }else{

                            Toast.makeText(Splash.this, "Invalid URL Entered. Please Enter Valid URL.", Toast.LENGTH_LONG).show();
                            reCallActivity();
                        }

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,	int id) {
                                dialog.cancel();

                                Splash.this.finish();
                            }
                        });

        // create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();

    }

    public void reCallActivity(){
        Intent mainActivity = new Intent(Splash.this, Splash.class);
        startActivity(mainActivity);
    }
    @Override
    public void onTaskCompleted(TaskType taskType, String result) {
        switch (taskType) {
            case DATABASENAME:

                new PrefetchData().execute(result);

                break;

            default:
                break;
        }
    }
    private class PrefetchData extends AsyncTask<String, Integer, Boolean> {

        int totalRecords=0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Splash.this);
            progressDialog.setTitle("Prefetching data...");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... arg0) {

            try {
                int recordCount = 0;
                String jsonLine= arg0[0];

                ServerDatabaseDS ds =new ServerDatabaseDS(Splash.this);
                ArrayList<ServerDatabase> list =new ArrayList<ServerDatabase>();
                JSONObject jsonResponse = new JSONObject(jsonLine);
                JSONArray jsonArray = jsonResponse.getJSONArray("GetdatabaseNamesResult");
                totalRecords=jsonArray.length();
                Log.v(TAG, "Array Length Server DB :" + jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++)
                {

                    ServerDatabase serverDatabase =new ServerDatabase();
                    JSONObject jObject = (JSONObject) jsonArray.get(i);
                    String sdbname = jObject.getString("Name");
                    Log.v(TAG, "DB Name:  "+sdbname);

                    serverDatabase.setDatabaseName(sdbname);
                    list.add(serverDatabase);

                    ++recordCount;
                    publishProgress(recordCount);
                }

                int db_result =ds.createOrUpdateServerDB(list);
                if(db_result>0){
                    return true;
                }else{
                    return false;
                }


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        }

        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            progressDialog.setMessage("Prefetching data..." + progress[0] + "/" + totalRecords);

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            if(result){

                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                SharedPreferencesClass.setLocalSharedPreference(Splash.this, "URL", spURL);

                Intent mainActivity = new Intent(Splash.this, SettingsActivity.class);
                startActivity(mainActivity);
                finish();

            }else{

                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                reCallActivity();

            }


        }

    }
}