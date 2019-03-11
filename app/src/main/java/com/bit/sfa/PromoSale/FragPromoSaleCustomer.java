package com.bit.sfa.PromoSale;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bit.sfa.Adapter.CustomerAdapter;
import com.bit.sfa.DataControl.FmDebtorDS;
import com.bit.sfa.DataControl.IResponseListener;
import com.bit.sfa.DataControl.RouteDS;
import com.bit.sfa.DataControl.SalRepDS;
import com.bit.sfa.view.ActivityHome;
import com.bit.sfa.Models.FmDebtor;
import com.bit.sfa.R;
import com.bit.sfa.Settings.SharedPref;
import com.bit.sfa.Settings.StaticData;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragPromoSaleCustomer extends Fragment {

    ListView lvCustomers;
    View view;
    ArrayList<FmDebtor> customerList;
    public String TAG = "FragPromoSaleCustomer";
    public static final String SETTINGS = "SETTINGS";
    public static SharedPreferences localSP;
    SharedPref mSharedPref;
    IResponseListener listener;
    Button all,route;
    public FragPromoSaleCustomer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_frag_promo_sale_customer, container, false);
        localSP = getActivity().getSharedPreferences(SETTINGS, 0);
        //initializations
        lvCustomers = (ListView) view.findViewById(R.id.cus_lv);
        all = (Button) view.findViewById(R.id.all_cust);
        route = (Button) view.findViewById(R.id.route_wise_cust);
        mSharedPref = new SharedPref(getActivity());
        //data controls
        RouteDS ds = new RouteDS(getActivity());
        final FmDebtorDS debtorDS = new FmDebtorDS(getActivity());
        SalRepDS repDS = new SalRepDS(getActivity());

        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH) + 1;
        String month = null;
        if (cmonth < 10) {
            month = "0" + cmonth;
        } else {
            month = "" + cmonth;
        }
        DecimalFormat df_month = new DecimalFormat("00");

        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy"); //change this
        final String formattedDate = simpleDateFormat.format(d);


        //get today's route
        final String current_route = ds.getRouteFromItenary(formattedDate);
        System.out.println(TAG + current_route);
        StaticData.Route = current_route;

        //get route customers

        lvCustomers.clearTextFilter();
        customerList = null;
        customerList = debtorDS.getAllCustomers();
        lvCustomers.setAdapter(new CustomerAdapter(getActivity(), customerList));


        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"All Customers",Toast.LENGTH_LONG).show();
                lvCustomers.clearTextFilter();
                customerList = new FmDebtorDS(getActivity()).getAllCustomers();
                lvCustomers.setAdapter(new CustomerAdapter(getActivity(), customerList));
            }
        });

        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Route Customers",Toast.LENGTH_LONG).show();
                lvCustomers.clearTextFilter();
                customerList = new FmDebtorDS(getActivity()).getRouteCustomers(formattedDate,current_route);
                lvCustomers.setAdapter(new CustomerAdapter(getActivity(), customerList));
            }
        });


        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int x = android.provider.Settings.Global.getInt(getActivity().getContentResolver(), android.provider.Settings.Global.AUTO_TIME, 0);
                if (x > 0){
                    //customerList = debtorDS.getRouteCustomers(current_route);
                    FmDebtor debtor = customerList.get(i);
                    ActivityHome home = new ActivityHome();
                    String selectedDebtor = debtor.getDebNameM();
                    System.out.println("selectedDebtor" + ActivityHome.SAcustomer);
                    home.mselectedDebtor = debtor;
                    ActivityHome.SAcustomer = selectedDebtor;
                    ActivityHome.SAroute = debtor.getRouteCode();
                    PromoSaleManagement.iscustomer = true;
                    new SharedPref(getActivity()).setGlobalVal("preKeyRoute" ,debtor.getRouteCode());
                    new SharedPref(getActivity()).setGlobalVal("PrekeyCusCode", debtor.getDebCodeM());
                    navigateToHeader(i);
                }else{
                    android.widget.Toast.makeText(getActivity(), "Please tick the 'Automatic Date and Time' option to continue..", android.widget.Toast.LENGTH_LONG).show();
                    /* Show Date/time settings dialog */
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS), 0);
                }
            }
        });
        return view;
    }
    /*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public void navigateToHeader(int position) {
        ActivityHome home = new ActivityHome();
        FmDebtor debtor = customerList.get(position);
        String selectedDebtor = debtor.getDebNameM();
        System.out.println("selectedDebtor" + ActivityHome.SAcustomer);
        home.mselectedDebtor = debtor;
        mSharedPref.setGlobalVal("PrekeyCustomer", "Y");
        new SharedPref(getActivity()).setGlobalVal("PrekeyCusCode", debtor.getDebCodeM());
        new SharedPref(getActivity()).setGlobalVal("PrekeyCusName", debtor.getDebNameM());
        new SharedPref(getActivity()).setGlobalVal("preKeyRoute" ,debtor.getRouteCode());
        listener.moveNextFragment_Pre();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (IResponseListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onButtonPressed");
        }
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.all_cust:
//                Toast.makeText(getActivity(),"All Customers",Toast.LENGTH_LONG).show();
//                lvCustomers.clearTextFilter();
//                customerList = new FmDebtorDS(getActivity()).getRouteCustomers("");
//                lvCustomers.setAdapter(new CustomerAdapter(getActivity(), customerList));
//
//            case R.id.route_wise_cust:
//                Toast.makeText(getActivity(),"Route Customers",Toast.LENGTH_LONG).show();
//                lvCustomers.clearTextFilter();
//                customerList = new FmDebtorDS(getActivity()).getAllCustomers();
//                lvCustomers.setAdapter(new CustomerAdapter(getActivity(), customerList));
//                //prodcutDetailsDialogbox();
//
//                break;
//            default:
//        }
//    }
}
