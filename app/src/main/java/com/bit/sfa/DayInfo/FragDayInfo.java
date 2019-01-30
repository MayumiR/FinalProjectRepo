package com.bit.sfa.DayInfo;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bit.sfa.DataControl.SalRepDS;
import com.bit.sfa.DataControl.TourDS;
import com.bit.sfa.DataControl.UtilityContainer;
import com.bit.sfa.DefView.FragHome;
import com.bit.sfa.DefView.Home;
import com.bit.sfa.Models.Tour;
import com.bit.sfa.PromoSale.PromoSaleManagement;
import com.bit.sfa.R;
import com.bit.sfa.Settings.SharedPref;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sathiyaraja on 7/9/2018.
 */

public class FragDayInfo extends Fragment implements View.OnClickListener {

    private View view;
    private EditText editTextDate, editTextStime, editTextVehicle, editTextDriver, editTextAsst, editTextSkm, editTextRoute, editTextEtime, editTextEkm, editTextDistance;
    public static SharedPreferences localSP;
    public static final String SETTINGS = "SETTINGS";
    public LinearLayout dayEnd;
    ArrayList<Tour> oTours = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.day_info, container, false);

        getActivity().setTitle("Add Day Info");

        localSP = getActivity().getSharedPreferences(SETTINGS, Context.MODE_WORLD_READABLE + 0);
        //initializations
        editTextDate = (EditText) view.findViewById(R.id.editTextDate);
        editTextStime = (EditText) view.findViewById(R.id.editTextStime);
        editTextVehicle = (EditText) view.findViewById(R.id.editTextVehicle);
        editTextDriver = (EditText) view.findViewById(R.id.editTextDriver);
        editTextAsst = (EditText) view.findViewById(R.id.editTextAsst);
        editTextSkm = (EditText) view.findViewById(R.id.editTextSkm);
        editTextRoute = (EditText) view.findViewById(R.id.editTextRoute);
        dayEnd = (LinearLayout) view.findViewById(R.id.dayEnd);

        editTextEtime = (EditText) view.findViewById(R.id.editTextEtime);
        editTextEkm = (EditText) view.findViewById(R.id.editTextEkm);
        editTextDistance = (EditText) view.findViewById(R.id.editTextDistance);

        editTextDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        editTextStime.setText(new SimpleDateFormat("hh:mm a").format(new Date()));

        Button buttonStart = (Button) view.findViewById(R.id.buttonStart);
        Button buttonEnd = (Button) view.findViewById(R.id.buttonEnd);

        buttonStart.setOnClickListener(this);
        buttonEnd.setOnClickListener(this);

        oTours = new TourDS(getActivity()).getIncompleteRecord();
        //show day end after validation

        if (oTours.size() > 0) {

            Tour tour = oTours.get(0);
            if (tour == null) {

            } else {

                dayEnd.setVisibility(View.VISIBLE);

                editTextDate.setText(tour.getFTOUR_DATE());

                editTextStime.setText(tour.getFTOUR_S_TIME());

                editTextVehicle.setText(tour.getFTOUR_VEHICLE());
                editTextVehicle.setEnabled(false);

                editTextDriver.setText(tour.getFTOUR_DRIVER());
                editTextDriver.setEnabled(false);

                editTextAsst.setText(tour.getFTOUR_ASSIST());
                editTextAsst.setEnabled(false);

                editTextSkm.setText(tour.getFTOUR_S_KM());
                editTextSkm.setEnabled(false);

                editTextRoute.setText(tour.getFTOUR_ROUTE());
                editTextRoute.setEnabled(false);

                editTextEtime.setText(new SimpleDateFormat("hh:mm a").format(new Date()));
            }
        } else {

        }

        editTextEkm.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (editTextEkm.getText().length() > 0)
                    editTextDistance.setText(String.format("%.1f", Double.parseDouble(editTextEkm.getText().toString()) - Double.parseDouble(editTextSkm.getText().toString())));


            }
        });

        System.out.println(">>>" + new TourDS(getActivity()).isDayEnd(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
        if (new TourDS(getActivity()).isDayEnd(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {

        } else {

        }

        //DISABLED BACK NAVIGATION
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("", "keyCode: " + keyCode);
                Home.navigation.setVisibility(View.VISIBLE);

                if( keyCode == KeyEvent.KEYCODE_BACK ) {
                    Toast.makeText(getActivity(), "Back button disabled!", Toast.LENGTH_SHORT).show();
                    return true;
                }else if ((keyCode == KeyEvent.KEYCODE_HOME)) {

                    getActivity().finish();

                    return true;

                } else {
                    return false;
                }
            }
        });


        return view;
    }


    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.buttonStart:

                if (editTextDate.length() > 0 && editTextStime.length() > 0 && editTextVehicle.length() > 0 && editTextDriver.length() > 0 && editTextAsst.length() > 0) {
                    TourDS tourDS = new TourDS(getActivity());

                    Tour tour = new Tour();
                    tour.setFTOUR_DATE(editTextDate.getText().toString());
                    tour.setFTOUR_S_TIME(editTextStime.getText().toString());
                    tour.setFTOUR_VEHICLE(editTextVehicle.getText().toString());
                    tour.setFTOUR_DRIVER(editTextDriver.getText().toString());
                    tour.setFTOUR_ASSIST(editTextAsst.getText().toString());
                    tour.setFTOUR_S_KM(editTextSkm.getText().toString());
                    tour.setFTOUR_ROUTE(editTextRoute.getText().toString());
                    tour.setFTOUR_IS_SYNCED("0");
                    tour.setFTOUR_MAC(localSP.getString("MAC_Address", "No MAC Address").toString());
                    tour.setFTOUR_REPCODE(new SalRepDS(getActivity()).getCurrentRepCode());

                    //insert into db
                    long result = tourDS.InsertUpdateTourData(tour);
                    System.out.println("long" + result);
                    if (result > 0) {

                        SharedPref sharedPref = new SharedPref(getActivity());
                        sharedPref.setGlobalVal("dayStart", "Y");

                        Toast.makeText(getActivity(), "Day start info saved! ", Toast.LENGTH_SHORT).show();
                        clearTextFields();
                        UtilityContainer.mLoadFragment(new PromoSaleManagement(), getActivity());
                    }
                } else {
                    Toast.makeText(getActivity(), "Fill in the fields!", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.buttonEnd:

                if (Double.parseDouble(editTextEkm.getText().toString()) < Double.parseDouble(editTextSkm.getText().toString())) {
                    Toast.makeText(getActivity(), "Invalid end time!Unable to calculate distance ", Toast.LENGTH_SHORT).show();
                } else {


                    if (editTextEkm.getText().toString().length() > 0) {
                        Tour tour = new Tour();
                        Tour oTour = oTours.get(0);
                        tour.setFTOUR_DATE(oTour.getFTOUR_DATE());
                        tour.setFTOUR_ROUTE(oTour.getFTOUR_ROUTE());
                        tour.setFTOUR_S_KM(oTour.getFTOUR_S_KM());
                        tour.setFTOUR_S_TIME(oTour.getFTOUR_S_TIME());
                        tour.setFTOUR_VEHICLE(oTour.getFTOUR_VEHICLE());
                        tour.setFTOUR_F_TIME(new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date()));
                        tour.setFTOUR_F_KM(editTextEkm.getText().toString());
                        tour.setFTOUR_DISTANCE(editTextDistance.getText().toString());
                        tour.setFTOUR_DRIVER(oTour.getFTOUR_DRIVER());
                        tour.setFTOUR_ASSIST(oTour.getFTOUR_ASSIST());

                        tour.setFTOUR_IS_SYNCED("0");
                        tour.setFTOUR_MAC(localSP.getString("MAC_Address", "No MAC Address").toString());
                        tour.setFTOUR_REPCODE(new SalRepDS(getActivity()).getCurrentRepCode());

                        new TourDS(getActivity()).InsertUpdateTourData(tour);
                        clearTextFields();
                        Toast.makeText(getActivity(), "Tour End info saved! ", Toast.LENGTH_SHORT).show();
                        UtilityContainer.mLoadFragment(new FragHome(), getActivity());

                    } else {
                        Toast.makeText(getActivity(), "Fill in the fields!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            default:
                break;
        }

    }

    private void clearTextFields() {

//        editTextDate.setText("");
        editTextEkm.setText("");
        editTextVehicle.setText("");
        editTextDriver.setText("");
        editTextAsst.setText("");
        editTextSkm.setText("");
        editTextRoute.setText("");
        editTextEtime.setText("");
        editTextDistance.setText("");
        editTextRoute.setText("");

    }
}
