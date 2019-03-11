package com.bit.sfa.PromoSale;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.Toast;

import com.bit.sfa.Adapter.GiftDetailsAdapter;
import com.bit.sfa.Adapter.NewGiftItemAdapter;
import com.bit.sfa.Adapter.NewPreProductAdapter;
import com.bit.sfa.Adapter.OrderDetailsAdapter;
import com.bit.sfa.DataControl.FmItemDS;
import com.bit.sfa.DataControl.FmisshedDS;
import com.bit.sfa.DataControl.OrdDetDS;
import com.bit.sfa.DataControl.OrdHedDS;
import com.bit.sfa.DataControl.PreProductDS;
import com.bit.sfa.DataControl.UploadTaskListener;
import com.bit.sfa.DataControl.UtilityContainer;
import com.bit.sfa.view.ActivityHome;
import com.bit.sfa.Models.Fmisshed;
import com.bit.sfa.Models.OrdDet;
import com.bit.sfa.Models.OrdHed;
import com.bit.sfa.Models.PreProduct;

import com.bit.sfa.Models.PreSalesMapper;
import com.bit.sfa.R;
import com.bit.sfa.Settings.NetworkUtil;
import com.bit.sfa.Settings.ReferenceNum;
import com.bit.sfa.Settings.SharedPref;
import com.bit.sfa.Settings.TaskType;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Rashmi 2018-08-20
 */
public class FragPromoSaleHeaderDet extends Fragment implements UploadTaskListener{

    private static final String TAG = "FragPromoSaleHeaderDet";
    public View view;
    public SharedPref mSharedPref;
    private ListView lv_pre_order_detlv,lvFreeIssue_Pre;
    private ImageButton pre_Product_btn, pre_disc_btn, btnSave;
    private  String RefNo;
    public ActivityHome mainActivity;
    int seqno = 0, index_id = 0;
    ArrayList<PreProduct> PreproductList = null, selectedItemList = null;
    ArrayList<Fmisshed> issueList = null,displayIssueList = null;
    ArrayList<OrdDet> orderList;
    SweetAlertDialog pDialog;
    private  MyReceiver r;
    private OrdHed tmpsoHed=null;  //from re oder creation
    private FloatingActionButton next;
    List<String> resultList;



    public FragPromoSaleHeaderDet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.sales_management_pre_sales_details_new, container, false);
        lv_pre_order_detlv = (ListView) view.findViewById(R.id.lvProducts_Pre);
        lvFreeIssue_Pre = (ListView) view.findViewById(R.id.lvFreeIssue_Pre);
        pre_Product_btn = (ImageButton) view.findViewById(R.id.btnPreSalesProduct);
        pre_disc_btn = (ImageButton) view.findViewById(R.id.btnPreSalesDisc);
        btnSave = (ImageButton) view.findViewById(R.id.btnSave);
       // next = (FloatingActionButton) view.findViewById(R.id.fab);
        seqno = 0;
        mSharedPref = new SharedPref(getActivity());
        RefNo = new ReferenceNum(getActivity()).getCurrentRefNo(getResources().getString(R.string.NumVal));
        mainActivity = (ActivityHome) getActivity();
        tmpsoHed=new OrdHed();
        resultList = new ArrayList<>();
        //------------------------------------------------------------------------------------------------------------------------

        showData();
        showGiftData();
        pre_Product_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoardingPreProductFromDB().execute();
            }
        });
//------------------------------------------------------------------------------------------------------------------------------------
        pre_disc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoardingGiftItemsFromDB().execute();
            }
        });

//------------------------------------------------------------------------------------------------------------------------------------
        lv_pre_order_detlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                OrdDet tranSODet=orderList.get(position);
               // popEditDialogBox(tranSODet);

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Upload(getActivity());
            }
        });
        //---------------------------------------------------------------------------------------------------------------------------------
        return view;
    }

    //-------------------------rashmi 2018-08-20----------------------------------------------------------------------------------------------------------------

    public void showData() {
        try {
            lv_pre_order_detlv.setAdapter(null);
            orderList = new OrdDetDS(getActivity()).getAllOrderDetails(RefNo);
            lv_pre_order_detlv.setAdapter(new OrderDetailsAdapter(getActivity(), orderList));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    //-------------------------rashmi 2018-08-20----------------------------------------------------------------------------------------------------------------

    public void showGiftData() {
        try {
            lvFreeIssue_Pre.setAdapter(null);
            displayIssueList = new FmisshedDS(getActivity()).getIssuesByRefNo(RefNo);
            lvFreeIssue_Pre.setAdapter(new GiftDetailsAdapter(getActivity(), displayIssueList));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
//------------------------------show gift items------------------------------------------------------------------------------------------
public void GiftItemDialogBox() {
    LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
    View promptView = layoutInflater.inflate(R.layout.issue_dialog_layout, null);
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    alertDialogBuilder.setView(promptView);

    final ListView lvProducts = (ListView) promptView.findViewById(R.id.lv_product_list);
    final SearchView search = (SearchView) promptView.findViewById(R.id.et_search);

    lvProducts.clearTextFilter();
    issueList = new FmisshedDS(getActivity()).getIssuesByDebCode(new SharedPref(getActivity()).getGlobalVal("PrekeyCusCode"));
    lvProducts.setAdapter(new NewGiftItemAdapter(getActivity(), issueList));

    alertDialogBuilder.setCancelable(false).setNegativeButton("DONE", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {

           // selectedItemList = new PreProductDS(getActivity()).getSelectedItems();
            updateFmIssHed(issueList);
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialog.cancel();
        }
    });

    AlertDialog alertDialog = alertDialogBuilder.create();
    alertDialog.show();
    alertDialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
            PreproductList = new PreProductDS(getActivity()).getAllItems(query);
            lvProducts.setAdapter(new NewPreProductAdapter(getActivity(), PreproductList));
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            PreproductList.clear();
            PreproductList = new PreProductDS(getActivity()).getAllItems(newText);
            lvProducts.setAdapter(new NewPreProductAdapter(getActivity(), PreproductList));

            return false;
        }
    });

}
    //-------------------------------------- show pre product dialog----------------------------------------------------------------------

    public void PreProductDialogBox() {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.product_dialog_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        final ListView lvProducts = (ListView) promptView.findViewById(R.id.lv_product_list);
        final SearchView search = (SearchView) promptView.findViewById(R.id.et_search);

        lvProducts.clearTextFilter();
        PreproductList = new PreProductDS(getActivity()).getAllItems("");
        lvProducts.setAdapter(new NewPreProductAdapter(getActivity(), PreproductList));

        alertDialogBuilder.setCancelable(false).setNegativeButton("DONE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                selectedItemList = new PreProductDS(getActivity()).getSelectedItems();
                updateSOeDet(selectedItemList);
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                PreproductList = new PreProductDS(getActivity()).getAllItems(query);
                lvProducts.setAdapter(new NewPreProductAdapter(getActivity(), PreproductList));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                PreproductList.clear();
                PreproductList = new PreProductDS(getActivity()).getAllItems(newText);
                lvProducts.setAdapter(new NewPreProductAdapter(getActivity(), PreproductList));

                return false;
            }
        });

    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
    public void updateSOeDet(final ArrayList<PreProduct> list) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Fetch Data Please Wait.");
                pDialog.setCancelable(false);
                pDialog.show();

                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {

                int i = 0;
                new OrdDetDS(getActivity()).restData(RefNo);

                for (PreProduct product : list) {
                    i++;
                    mUpdatePrsSales("0", product.getPREPRODUCT_ITEMCODE(), product.getPREPRODUCT_QTY(), i + "", product.getPREPRODUCT_QOH());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(pDialog.isShowing()){
                    pDialog.dismiss();
                }

                showData();
            }

        }.execute();
    }
    //------------------------------------------------------update OrderRefNo------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------
    public void updateFmIssHed(final ArrayList<Fmisshed> list) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Fetch Data Please Wait.");
                pDialog.setCancelable(false);
                pDialog.show();

                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {

                int i = 0;
               // new OrdDetDS(getActivity()).restData(RefNo);

                for (Fmisshed fmisshed : list) {
                    i++;
                    new FmisshedDS(getActivity()).updateOrdRefNo(fmisshed.getRefNo(),RefNo);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(pDialog.isShowing()){
                    pDialog.dismiss();
                }

                showGiftData();
            }

        }.execute();
    }

    //------------------------------------------------------update TranSODet tbl------------------------------------------------------------------------------------

    public void mUpdatePrsSales(String id, String itemCode, String Qty, String seqno, String qoh) {
        OrdDet SODet = new OrdDet();
        ArrayList<OrdDet> SOList = new ArrayList<OrdDet>();
        SODet.setFORDDET_ID(id + "");
        SODet.setFORDDET_AMT("0");
        SODet.setFORDDET_BAL_QTY(Qty);
        SODet.setFORDDET_QTY(Qty);
        SODet.setFORDDET_B_AMT("0");
        SODet.setFORDDET_DIS_AMT("0");
        SODet.setFORDDET_BP_DIS_AMT("0");
        SODet.setFORDDET_BT_TAX_AMT("0");
        SODet.setFORDDET_TAX_AMT("0");
        SODet.setFORDDET_DIS_AMT("0");
        SODet.setFORDDET_DIS_PER("0");
        SODet.setFORDDET_ITEM_CODE(itemCode);
        SODet.setFORDDET_PICE_QTY(Qty);
        SODet.setFORDDET_REFNO(RefNo);
        SODet.setFORDDET_SELL_PRICE("0");
        SODet.setFORDDET_B_SELL_PRICE("0");
        SODet.setFORDDET_TAX_COM_CODE("");
        SODet.setFORDDET_BT_SELL_PRICE("0");
        SODet.setFORDDET_T_SELL_PRICE("0");
        SODet.setFORDDET_TXN_DATE(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        SODet.setFORDDET_IS_ACTIVE("1");
        SODet.setFORDDET_RECORD_ID("");
        SODet.setFORDDET_P_DIS_AMT("0");
        SODet.setFORDDET_QOH(qoh);
        SODet.setFORDDET_TYPE("SA");
        SODet.setFORDDET_SEQNO(seqno + "");
        SOList.add(SODet);
        new OrdDetDS(getActivity()).createOrUpdateOrdDet(SOList);

    }

    @Override
    public void onTaskCompleted(TaskType taskType, List<String> list) {
        resultList.addAll(list);

        switch (taskType) {



            case UPLOAD_PROMO_ORDER:{
                String msg = "";
                for (String s : resultList) {
                    msg += s;
                }
                resultList.clear();
                mUploadResult(msg);
            }
            break;

            default:
                break;
        }
    }
    public void mUploadResult(String message) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setTitle("Upload Summary");

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                UtilityContainer.mLoadFragment(new PromoSaleManagement(), getActivity());
                dialog.cancel();
            }
        });
        AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
        alertD.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }
    //---------------------------------LoardingPreProductFromDB----------------------------------------------------------------------------------------
public class LoardingPreProductFromDB extends AsyncTask<Object, Object, ArrayList<PreProduct>> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Fetch Data Please Wait.");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected ArrayList<PreProduct> doInBackground(Object... objects) {

        if (new PreProductDS(getActivity()).tableHasRecords()) {
            PreproductList = new PreProductDS(getActivity()).getAllItems("");
        } else {
            PreproductList =new FmItemDS(getActivity()).getAllItemForPreSales("",RefNo);

            //     PreproductList =new ItemsDS(getActivity()).getAllItemForPreSales("","txntype ='21'",RefNo, new SalRepDS(getActivity()).getCurrentLocCode(),mainActivity.selectedDebtor.getFDEBTOR_PRILLCODE());
            new PreProductDS(getActivity()).insertOrUpdateProducts(PreproductList);
            //---------re Order Temp product  list added for  fProducts_pre table-----------------dhanushika-------------------------------
            if(tmpsoHed!=null) {
                ArrayList<OrdDet> detArrayList = tmpsoHed.getSoDetArrayList();
                if (detArrayList != null) {
                    for (int i = 0; i < detArrayList.size(); i++) {
                        String tmpItemName = detArrayList.get(i).getFORDDET_ITEM_CODE();
                        String tmpQty = detArrayList.get(i).getFORDDET_QTY();
                        //Update Qty in  fProducts_pre table
                        int count = new PreProductDS(getActivity()).updateProductQtyFor(tmpItemName, tmpQty);
                        if (count > 0) {
                            // Log.d("InsertOrUpdate", "success");
                        } else {
                            // Log.d("InsertOrUpdate", "Failed");
                        }

                    }
                }
            }
            //----------------------------------------------------------------------------

        }
        return PreproductList;
    }


    @Override
    protected void onPostExecute(ArrayList<PreProduct> preProducts) {
        super.onPostExecute(preProducts);

        if(pDialog.isShowing()){
            pDialog.dismiss();
        }

        PreProductDialogBox();

    }
}
//------------------------------------------------------------------------------------------------------------------------
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
//rashmi - 2018-08-30

    public void Upload(final Context context) {

        if (new OrdDetDS(getActivity()).getItemCountForUpload(RefNo) > 0 || new FmisshedDS(getActivity()).getIssuesByDebCode(new SharedPref(getActivity()).getGlobalVal("PrekeyCusCode")).size() > 0) {
            // check if issue hed > 0 for this customer via passing debcode and if those are issue
            // if has and not issue cannot upload order..give popup message

            String checkIssue = new FmisshedDS(getActivity()).checkIssuedByDebCode(new SharedPref(getActivity()).getGlobalVal("PrekeyCusCode"));
            int issueCount = Integer.parseInt(checkIssue.split("-")[0]);
            int giftCount = Integer.parseInt(checkIssue.split("-")[1]);
            if(giftCount>0 && issueCount==0){
                // give alert for you have gift items
                // Do you wish to continue order without issue gift items?
                //yes ----> upload only order .....No  cancel dialog

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("You have gift items.\n Do you wish to continue order without issue gift items?..");

                alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialogBuilder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @SuppressWarnings("unchecked")
                    public void onClick(DialogInterface dialog, int id) {

                        new TaskRunner().execute();

                    }

                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertD = alertDialogBuilder.create();

                alertD.show();
            }else{

            new TaskRunner().execute();
                //upload both gift and sample data

            }



        }else{
            Toast.makeText(getActivity(), "No Records to upload !", android.widget.Toast.LENGTH_LONG).show();
        }
    }
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    class TaskRunner extends AsyncTask<String, String, String> {



        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            new FmisshedDS(getActivity()).InactiveStatusUpdate(RefNo);//// 0 =  not issue, 1 =  ActiveIssue, 2 =  syncedIssue
            Log.v(">>1>>","FmisshedDS.InactiveStatusUpdate(RefNo)");
            new OrdHedDS(getActivity()).InactiveStatusUpdate(RefNo);
            Log.v(">>2>>","OrdHedDS.InactiveStatusUpdate(RefNo)");
            new OrdDetDS(getActivity()).InactiveStatusUpdate(RefNo);

            Log.v(">>3>>","OrdDetDS.InactiveStatusUpdate(RefNo)");
            new PreProductDS(getActivity()).mClearTables();
            //new OrdDetDS(getActivity()).UpdateTaxDetails(RefNo);
//            Log.v(">>3>>","UpdateTaxDetails(RefNo)");

            final ActivityHome activity = (ActivityHome) getActivity();

            activity.mselectedDebtor = null;
            activity.selectedDebtor = null;
            activity.selectedOrdHed = null;
            activity.SAroute = null;
            activity.SAcustomer = null;

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ActivityHome home = new ActivityHome();
            try {

                if (NetworkUtil.isNetworkAvailable(getActivity())) {

                    OrdHedDS hedDS = new OrdHedDS(getActivity());

                    ArrayList<PreSalesMapper> ordHedList = hedDS.getAllUnSyncOrdHed();
                    /* If records available for upload then */
                    if (ordHedList.size() <= 0)
                        Toast.makeText(getActivity(), "No Records to upload !", Toast.LENGTH_LONG).show();
                    else{
                        new UploadPromoOrder(getActivity(), FragPromoSaleHeaderDet.this, TaskType.UPLOAD_PROMO_ORDER).execute(ordHedList);
                        Log.v(">>8>>","UploadPreSales execute finish");
                        new ReferenceNum(getActivity()).NumValueUpdate(getResources().getString(R.string.NumVal));

                    }
                } else
                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();


            }catch(Exception e){
                Log.v("Exception in sync order",e.toString());
            }

             mSharedPref.clear_shared_pref();
             home.mselectedDebtor = null;
             home.selectedOrdHed = null;

        }
    }
    //---------------------------------LoardingPreProductFromDB----------------------------------------------------------------------------------------
    public class LoardingGiftItemsFromDB extends AsyncTask<Object, Object, ArrayList<Fmisshed>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Fetch Data Please Wait.");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected ArrayList<Fmisshed> doInBackground(Object... objects) {

            issueList = new FmisshedDS(getActivity()).getIssuesByDebCode(new SharedPref(getActivity()).getGlobalVal("PrekeyCusCode"));
            Log.d("ISSUELIST>>>>>",issueList.toString());
            return issueList;
        }


        @Override
        protected void onPostExecute(ArrayList<Fmisshed> gifts) {
            super.onPostExecute(gifts);

            if(pDialog.isShowing()){
                pDialog.dismiss();
            }

            GiftItemDialogBox();

        }
    }
//------------------------------------------------------------------------------------------------------------------------

    @Override
    public void onResume() {
        super.onResume();
        r = new MyReceiver();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(r, new IntentFilter("TAG_DETAILS"));
    }
    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(r);
    }
    /*-*-*-*-*-Rashmi 2018-08-20*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            FragPromoSaleHeaderDet.this.mToggleTextbox();
        }
    }
    /*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public void mToggleTextbox() {

        if (mSharedPref.getGlobalVal("PrekeyCustomer").equals("Y")) {
            pre_Product_btn.setEnabled(true);
            // from PreSalesAdapter----- for re oder creation
            Bundle mBundle = getArguments();
            if (mBundle != null) {
                tmpsoHed = (OrdHed) mBundle.getSerializable("order");
            }
            showData();
            showGiftData();
            selectedItemList = new PreProductDS(getActivity()).getSelectedItems();
            if(selectedItemList !=null &&  !selectedItemList.isEmpty()){
                updateSOeDet(selectedItemList);
            }
        } else {
            Toast.makeText(getActivity(), "Select a customer to continue...", Toast.LENGTH_SHORT).show();
            pre_Product_btn.setEnabled(false);
        }
    }
}
