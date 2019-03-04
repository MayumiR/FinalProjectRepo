package com.bit.sfa.PromoSale;


import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bit.sfa.DataControl.FmDebtorDS;
import com.bit.sfa.DataControl.FmSalRepDS;
import com.bit.sfa.DataControl.LocationsDS;
import com.bit.sfa.DataControl.OrdHedDS;
import com.bit.sfa.DataControl.RouteDS;
import com.bit.sfa.DefView.Home;
import com.bit.sfa.Models.OrdHed;
import com.bit.sfa.R;
import com.bit.sfa.Settings.ReferenceNum;
import com.bit.sfa.Settings.SharedPref;
import com.bit.sfa.Settings.SharedPreferencesClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.bit.sfa.Settings.SharedPreferencesClass.getLocalSharedPreference;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragPromoSaleHeader extends Fragment {


    View view;
    private FloatingActionButton next;
    public static final String SETTINGS = "SETTINGS";
    public static EditText ordno, date, mNo, deldate, remarks;

    public TextView route, costcenter;
    private TextView cusName;
    MyReceiver r;
    //SharedPreferencesClass localSP;


    public FragPromoSaleHeader() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_frag_promo_sale_header, container, false);

        next = (FloatingActionButton) view.findViewById(R.id.fab);

        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy"); //change this
        String formattedDate = simpleDateFormat.format(d);
        Home home = new Home();
        ReferenceNum referenceNum = new ReferenceNum(getActivity());
     //   localSP = new SharedPreferencesClass();

        ordno = (EditText) view.findViewById(R.id.editTextOrdno);
        date = (EditText) view.findViewById(R.id.editTextDate);
        mNo        = (EditText) view.findViewById(R.id.editTextManualNo);
        deldate    = (EditText) view.findViewById(R.id.editTextdelDate);
        remarks    = (EditText) view.findViewById(R.id.editTextRemarks);
        costcenter = (TextView) view.findViewById(R.id.editTextcostCenter);
        route = (TextView) view.findViewById(R.id.editTextRoute);
        cusName = (TextView) view.findViewById(R.id.textViewCustomer);


     //   cusName.setText(home.SAcustomer);
       // route.setText(StaticData.Route);
        //rashmi
//        route.setText(""+home.SAroute);
//        date.setText(formattedDate);
//        ordno.setText(referenceNum.getCurrentRefNo(getResources().getString(R.string.NumVal)));


        deldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MDatePicker newFragment = new MDatePicker();
                newFragment.show(getFragmentManager(), "date picker");
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validate tabs
                if (PromoSaleManagement.isheader == true) {
                    PromoSaleManagement.viewPager.setCurrentItem(2);
                }

            }
        });


        return view;
    }

    public static class MDatePicker extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
        }

        private DatePickerDialog.OnDateSetListener dateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int i, int i1, int i2) {

                        deldate.setText(view.getDayOfMonth() + "-" + (view.getMonth() + 1) + "-" + view.getYear());
                    }

                };
    }
    /*-*-*-*-*-*-rashmi 2018/08/07*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    private String currentTime() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    /*-*-*-*-*-*Rashmi 2018-8-17-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public void SaveSalesHeader() {

        if (ordno.getText().length() > 0) {

            //mSharedPref.setGlobalVal("PrekeyCostPos", String.valueOf(spnCostCenter.getSelectedItemPosition()));

            Home activity = (Home) getActivity();
            RouteDS routeDS = new  RouteDS(getActivity());
            FmSalRepDS repDS=new FmSalRepDS(activity);
            LocationsDS locDS = new LocationsDS(activity);
            OrdHed hed =new OrdHed();
            String AppVersion = "";

            try{
                PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
                AppVersion = pInfo.versionName;

            }catch (Exception e){
                e.printStackTrace();
            }

            if(activity.selectedOrdHed !=null)
                hed =activity.selectedOrdHed;//set already enter values objects

            hed.setFORDHED_REFNO(ordno.getText().toString());
            hed.setFORDHED_DEB_CODE(new SharedPref(getActivity()).getGlobalVal("PrekeyCusCode"));
            hed.setFORDHED_ADD_DATE(date.getText().toString());
            hed.setFORDHED_DELV_DATE(deldate.getText().toString());
            hed.setFORDHED_ROUTE_CODE(new SharedPref(getActivity()).getGlobalVal("preKeyRoute"));
            hed.setFORDHED_MANU_REF(mNo.getText().toString());
            hed.setFORDHED_REMARKS(remarks.getText().toString());
           // hed.setFORDHED_ADD_MACH(localSP.getString("MAC_Address", "No MAC Address").toString());
            hed.setFORDHED_ADD_MACH(getLocalSharedPreference(getActivity(),"MAC_Address","No MAC Address"));
            hed.setFORDHED_ADD_USER(repDS.getCurrentRepCode());
            hed.setFORDHED_APP_DATE("1992-12-31");
            hed.setFORDHED_APPSTS("1");
            hed.setFORDHED_APP_USER(repDS.getCurrentRepCode());
            hed.setFORDHED_CUR_CODE("LKR");
            hed.setFORDHED_CUR_RATE("1.00");
            hed.setFORDHED_IS_ACTIVE("1");
            hed.setFORDHED_TXN_DATE(""+currentTime());
            hed.setFORDHED_TXN_TYPE("30");
            hed.setFORDHED_LOC_CODE(locDS.getMRepLocation(new FmSalRepDS(getActivity()).getCurrentRepCode()));
            hed.setFORDHED_COST_CODE(locDS.getMRepCostCode(new FmSalRepDS(getActivity()).getCurrentRepCode()));
            hed.setFORDHED_RECORD_ID(AppVersion);

            activity.selectedOrdHed = hed;//new updated object (new data + already enter data)

            SharedPreferencesClass.setLocalSharedPreference(activity, "SO_Start_Time",currentTime());

            ArrayList<OrdHed> ordHedList=new ArrayList<OrdHed>();
            OrdHedDS ordHedDS =new  OrdHedDS(getActivity());
            //head
            ordHedList.add(activity.selectedOrdHed);
            ordHedDS.createOrUpdateOrdHed(ordHedList);
        }
    }
    /*-*Rashmi 2018-08-17-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public void mRefreshHeader() {

        if (new SharedPref(getActivity()).getGlobalVal("PrekeyCustomer").equals("Y")) {
            Home home = new Home();
//            issueList = new FmisshedDS(getActivity()).getIssuesByDebCode(new SharedPref(getActivity()).getGlobalVal("PrekeyCusCode"));
//
//            List<String> issues = new ArrayList<String>();
//            /* Merge group code with group name to the list */
//            issues.add("-SELECT REFNO-");
//            for (Fmisshed iss : issueList) {
//                issues.add(iss.getRefNo());
//            }
//
//            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getActivity(),
//                    android.R.layout.simple_spinner_item, issues);
//            dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spnIssueRefNos.setAdapter(dataAdapter3);

            date.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            route.setText(new SharedPref(getActivity()).getGlobalVal("preKeyRoute")+" - "+new RouteDS(getActivity()).getRouteNameByRCode(new SharedPref(getActivity()).getGlobalVal("preKeyRoute")));
            deldate.setEnabled(true);
            remarks.setEnabled(true);
            mNo.setEnabled(true);
            cusName.setText(new SharedPref(getActivity()).getGlobalVal("PrekeyCusName"));
           // String debCode= new SharedPref(getActivity()).getGlobalVal("PrekeyCusCode");

            if (home.selectedOrdHed != null) {
                if (home.mselectedDebtor == null)
                    home.mselectedDebtor = new FmDebtorDS(getActivity()).getSelectedCustomerByCode(home.selectedOrdHed.getFORDHED_DEB_CODE());

                cusName.setText(home.mselectedDebtor.getDebNameM());
                ordno.setText(home.selectedOrdHed.getFORDHED_REFNO());
                deldate.setText(home.selectedOrdHed.getFORDHED_DELV_DATE());
                mNo.setText(home.selectedOrdHed.getFORDHED_MANU_REF());
                remarks.setText(home.selectedOrdHed.getFORDHED_REMARKS());
                costcenter.setText(home.selectedOrdHed.getFORDHED_COST_CODE());

            } else {

                ordno.setText(new ReferenceNum(getActivity()).getCurrentRefNo(getResources().getString(R.string.NumVal)));
                //costcenter.setText(new SharedPref(getActivity()).getGlobalVal("preKeyCostCode"));
                deldate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                SaveSalesHeader();
            }

        } else {
            Toast.makeText(getActivity(), "Select a customer to continue...", Toast.LENGTH_SHORT).show();
            remarks.setEnabled(false);
            mNo.setEnabled(false);
            deldate.setEnabled(false);
        }

    }
    /*-*-*-*-Rashmi 2018-08-17*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(r);
    }

    /*-*-*-*-*-*-Rashmi 2018-08-17*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public void onResume() {
        super.onResume();
        r = new FragPromoSaleHeader.MyReceiver();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(r, new IntentFilter("TAG_HEADER"));
    }


    /*-*-*-*-*-Rashmi 2018-08-17*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            FragPromoSaleHeader.this.mRefreshHeader();
        }
    }
    /*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
}
