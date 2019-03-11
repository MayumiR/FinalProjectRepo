package com.bit.sfa.view;

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

public class ActivitySplash extends AppCompatActivity{

    private ImageView logo;
    private static int SPLASH_TIME_OUT = 2000;
    private Context context = this;
    DatabaseHelper db;
    SharedPreferences localSP;
    private String spURL;
    private static ProgressDialog progressDialog;
    private String TAG = "ActivitySplash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        db=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase SFA;
        SFA = db.getWritableDatabase();
        db.onUpgrade(SFA, 1, 2);

        logo = (ImageView)findViewById(R.id.logo);

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_up);
        logo.startAnimation(animation1);

        boolean connectionStatus = new ConnectionDetector(ActivitySplash.this).isConnectingToInternet();
        localSP = getSharedPreferences(SharedPreferencesClass.SETTINGS,0);
//user data aragenanm  no need to check,
        // login wela nam kelinma customer list ekata
        if(connectionStatus){

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

                        Intent intent = new Intent(context, ActivityHome.class);
                        startActivity(intent);
                        finish();

                        overridePendingTransition(R.anim.in, R.anim.exit);

                    }else{

                        Intent mainActivity = new Intent(ActivitySplash.this, SettingsActivity.class);
                        startActivity(mainActivity);
                        finish();
                        overridePendingTransition(R.anim.in, R.anim.exit);

                    }
                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);

        }else{
           Toast.makeText(this,"No internet connection..",Toast.LENGTH_LONG).show();
        }

}


    public void reCallActivity(){
        Intent mainActivity = new Intent(ActivitySplash.this, ActivitySplash.class);
        startActivity(mainActivity);
    }

    private class PrefetchData extends AsyncTask<String, Integer, Boolean> {

        int totalRecords=0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ActivitySplash.this);
            progressDialog.setTitle("Validating...");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... arg0) {

            try {
                int recordCount = 0;
                String jsonLine= arg0[0];

                ServerDatabaseDS ds =new ServerDatabaseDS(ActivitySplash.this);
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

               //set user details to shared prefferences

                Intent mainActivity = new Intent(ActivitySplash.this, SettingsActivity.class);
                startActivity(mainActivity);
                finish();

            }else{

                Toast.makeText(getApplicationContext(), "Invalid Mac Id", Toast.LENGTH_SHORT).show();
                reCallActivity();

            }


        }

    }
}
