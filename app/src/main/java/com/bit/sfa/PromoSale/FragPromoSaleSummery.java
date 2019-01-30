package com.bit.sfa.PromoSale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bit.sfa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragPromoSaleSummery extends Fragment {


    public FragPromoSaleSummery() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_promo_sale_summery, container, false);
    }

}
