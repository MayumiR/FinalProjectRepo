package com.bit.sfa.Expenses;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bit.sfa.R;

/**
 * Created by Sathiyaraja on 7/26/2018.
 */

public class FragExpenses extends Fragment {

    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lay_expenses, container, false);

        //set main header
        getActivity().setTitle("Non Productives");


        return view;
    }
}
