package com.bit.sfa.PromoSale;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bit.sfa.view.ActivityHome;
import com.bit.sfa.R;


public class PromoSaleManagement extends Fragment {

    private FragmentActivity myContext;

    public static boolean iscustomer = false;
    public static boolean isheader = true;
    public static boolean ishdetails = false;
    public static boolean issummery = false;
    public static ViewPager viewPager;
    public static TabLayout tabLayout;

    public PromoSaleManagement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_promo_sale_management, container, false);

        //set title
        getActivity().setTitle("New Promo Order");
        getActivity().setTitle("SFA");


        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Customer"));
        tabLayout.addTab(tabLayout.newTab().setText("Header"));
        tabLayout.addTab(tabLayout.newTab().setText("Header Details"));
       // tabLayout.addTab(tabLayout.newTab().setText("Summary"));

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        // mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        viewPager.setAdapter(new PagerAdapter(myContext.getSupportFragmentManager(), tabLayout.getTabCount()));

        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                tabLayout.getTabAt(position).select();
                //   Log.d("<======>","click position "+ position);

                //if status =1 show image Details Tab bar
                    //if status =0 hide image Details Tab bar
//                    if (position == 3)
//                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent("TAG_SUMMARY"));
//                    else
                        if (position == 1)
                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent("TAG_HEADER"));
                    else if (position == 2)
                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent("TAG_DETAILS"));


            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //		tabLayout.setupWithViewPager(viewPager);
//		tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (iscustomer == false) {
                    Toaster("Please select Customer First");
                    viewPager.setCurrentItem(0);
                }  else {
                    viewPager.setCurrentItem(tab.getPosition());
                }

//                else if (isheader == false) {
//                    Toaster("Please fill header details");
//                    viewPager.setCurrentItem(1);
//                } else if (ishdetails == false) {
//                    Toaster("Please add Items");
//                    viewPager.setCurrentItem(2);
//                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //DISABLED BACK NAVIGATION
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("", "keyCode: " + keyCode);
                ActivityHome.navigation.setVisibility(View.VISIBLE);

                if (keyCode == KeyEvent.KEYCODE_BACK) {

//					if(backpress == 2){
//						Home.navigation.setVisibility(View.VISIBLE);
//					}else{
//						Toast.makeText(getActivity(),"Back Navigationid is disable,Press again to enable Home menu", Toast.LENGTH_SHORT).show();
//					}

                    return true;
                } else if ((keyCode == KeyEvent.KEYCODE_HOME)) {

                    getActivity().finish();

                    return true;

                } else {
                    return false;
                }
            }
        });

        return view;

    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public void mMoveToHeader() {
        viewPager.setCurrentItem(1);
    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new FragPromoSaleCustomer();
                case 1:
                    return new FragPromoSaleHeader();
                case 2:
                    return new FragPromoSaleHeaderDet();
//                case 3:
//                    return new FragPromoSaleSummery();
                default:
                    return new FragPromoSaleCustomer();
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    public void Toaster(String msg) {

        Toast.makeText(myContext, msg, Toast.LENGTH_SHORT).show();
    }

}
