package com.bit.sfa.PromoSale;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.bit.sfa.DataControl.FmDebtorDS;
import com.bit.sfa.DataControl.FmisshedDS;
import com.bit.sfa.DataControl.OrdDetDS;
import com.bit.sfa.DataControl.OrdHedDS;
import com.bit.sfa.DataControl.UploadTaskListener;
import com.bit.sfa.DataControl.UtilityContainer;
import com.bit.sfa.Models.PreSalesMapper;
import com.bit.sfa.R;
import com.bit.sfa.Settings.TaskType;

import java.util.ArrayList;
import java.util.List;

public class UploadPromoOrder extends AsyncTask<ArrayList<PreSalesMapper>, Integer, ArrayList<PreSalesMapper>> {

    // Shared Preferences variables
    public static final String SETTINGS = "SETTINGS";
    public static SharedPreferences localSP;
    Context context;
    ProgressDialog dialog;
    UploadTaskListener taskListener;
    TaskType taskType;
    int totalRecords;

    public UploadPromoOrder(Context context, UploadTaskListener taskListener, TaskType taskType) {

        this.context = context;
        this.taskListener = taskListener;
        this.taskType = taskType;

        localSP = context.getSharedPreferences(SETTINGS, Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        // dialog.setTitle("Uploading records");
        dialog.show();
    }

    @Override
    protected ArrayList<PreSalesMapper> doInBackground(ArrayList<PreSalesMapper>... params) {

        int recordCount = 0;
        publishProgress(recordCount);

        ArrayList<PreSalesMapper> RCSList = params[0];
        totalRecords = RCSList.size();

        final String sp_url = localSP.getString("URL", "").toString();
        String URL = "http://" + sp_url;

        for (PreSalesMapper c : RCSList) {
//            try {
//                FileWriter writer = new FileWriter(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + "test.txt");
//                writer.write(new Gson().toJson(c));
//                writer.close();
//            } catch (Exception e) {
//
//            }
            try {
                List<String> List = new ArrayList<String>();
                String sJsonHed = new Gson().toJson(c);
                List.add(sJsonHed);
                String sURL = URL + "/KFDWebServices/KFDWebServicesRest.svc/insertPromoOrder";
                boolean bStatus = UtilityContainer.mHttpManager(sURL, List.toString());
               // boolean bStatus = UtilityContainer.mHttpManager(sURL, new Gson().toJson(c));

                if (bStatus) {
                    c.setSynced(true);
                } else {
                    c.setSynced(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            ++recordCount;
            publishProgress(recordCount);
        }
        return RCSList;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        dialog.setMessage("Uploading.. PromoOrder Record " + values[0] + "/" + totalRecords);
    }

    @Override
    protected void onPostExecute(ArrayList<PreSalesMapper> RCSList) {

        super.onPostExecute(RCSList);
        List<String> list = new ArrayList<>();

        if (RCSList.size() > 0) {
            list.add("PROMO ORDER SUMMARY\n");
            list.add("----------------------------------------------\n");
        }

        int i = 1;
        for (PreSalesMapper c : RCSList) {
            new OrdHedDS(context).updateIsSynced(c);
            new FmisshedDS(context).updateIssueSync(c);
            new OrdDetDS(context).InactiveStatusUpdate(c.getFORDHED_REFNO());

            if (c.isSynced()) {
                list.add(i + ". " + c.getFORDHED_REFNO() + " (" + new FmDebtorDS(context).getSelectedCustomerByCode(c.getFORDHED_DEB_CODE()) + ")" + " --> Success\n");

            } else {
                list.add(i + ". " + c.getFORDHED_REFNO() + " (" + new FmDebtorDS(context).getSelectedCustomerByCode(c.getFORDHED_DEB_CODE()) + ")" + " --> Failed\n");
            }
            i++;
        }

        dialog.dismiss();
        taskListener.onTaskCompleted(taskType, list);
    }

}
