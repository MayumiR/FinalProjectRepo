package com.bit.sfa.PromoSale;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.bit.sfa.DataControl.IResponseListener;
import com.bit.sfa.DataControl.UtilityContainer;
import com.bit.sfa.R;


/**
 * Created by Sathiyaraja on 6/22/2018.
 */

public class FragSalesOrders extends Fragment {

    private Toolbar detail_toolbar;
    private FloatingActionButton fab;
    private FragmentActivity myContext;
    private ListView salesOrderLst;

    IResponseListener listener;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_sales_order, container, false);

        //set title
        getActivity().setTitle("SFA");

        //initializations
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        salesOrderLst = (ListView) view.findViewById(R.id.sales_lv);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilityContainer.mLoadFragment(new PromoSaleManagement(), getActivity());
//                Fragment promosale = new PromoSaleManagement();
//                getFragmentManager().beginTransaction().replace(
//                        R.id.fragmentContainer, promosale)
//                        .commit();
            }
        });

        //DISABLED BACK NAVIGATION
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("", "keyCode: " + keyCode);
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Toast.makeText(getActivity(), "Back Navigationid is disable", Toast.LENGTH_SHORT).show();
                    return true;
                } else if ((keyCode == KeyEvent.KEYCODE_HOME)) {

                    getActivity().finish();

                    return true;

                } else {
                    return false;
                }
            }
        });


        this.salesOrderLst.setEmptyView(view.findViewById(android.R.id.empty));


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
        try {
            listener = (IResponseListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onButtonPressed");
        }
    }




}
