package com.bit.sfa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bit.sfa.DataControl.FmisshedDS;
import com.bit.sfa.DataControl.PreProductDS;
import com.bit.sfa.Models.Fmisshed;
import com.bit.sfa.Models.PreProduct;
import com.bit.sfa.R;

import java.util.ArrayList;

/**
 * Created by rashmi on 23/8/2018.
 */

public class NewGiftItemAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Fmisshed> giftArrayList;
    Context context;
    private String preText = null;


    public NewGiftItemAdapter(Context context, ArrayList<Fmisshed> giftArrayList) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.giftArrayList = giftArrayList;
    }


    @Override
    public int getCount() {
        if (giftArrayList != null)
            return giftArrayList.size();
        return 0;
    }

    @Override
    public Fmisshed getItem(int position) {
        return giftArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row_gift_item, parent, false);

            viewHolder.lnStripe = (LinearLayout) convertView.findViewById(R.id.lnProductStripe);

            viewHolder.ItemName = (TextView) convertView.findViewById(R.id.row_itemname);
            viewHolder.RefNo = (TextView) convertView.findViewById(R.id.row_refno);
            viewHolder.IsIssue = (CheckBox) convertView.findViewById(R.id.row_is_issue);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Fmisshed issue = getItem(position);

        viewHolder.RefNo.setText(issue.getRefNo());
        viewHolder.ItemName.setText(issue.getRemarks());
        if(issue.getIsIssue().equals("1")) {
            viewHolder.IsIssue.setChecked(true);
        } else {
            viewHolder.IsIssue.setChecked(false);
        }
//--------------------------------------------------------------------------------------------------------------------------
        viewHolder.IsIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(viewHolder.IsIssue.isChecked())
                new FmisshedDS(context).updateIssueFlag(issue.getRefNo(), "1");
            else
                new FmisshedDS(context).updateIssueFlag(issue.getRefNo(), "0");



                        //*Change colors*//**//*
                        if (viewHolder.IsIssue.isChecked())
                            viewHolder.lnStripe.setBackground(context.getResources().getDrawable(R.drawable.custom_textbox_new));
                        else
                            viewHolder.lnStripe.setBackground(context.getResources().getDrawable(R.drawable.custom_textbox));

            }
        });


        //-------------------------------------------------------------------------------------------------------------------------------------------
              /*Change colors*/
        if (viewHolder.IsIssue.isChecked()) {
            viewHolder.lnStripe.setBackground(context.getResources().getDrawable(R.drawable.custom_textbox_new));

        } else {
            viewHolder.lnStripe.setBackground(context.getResources().getDrawable(R.drawable.custom_textbox));
        }



        return convertView;
    }

    @Override
    public int getItemViewType(final int position) {
        return position;
    }

    private static class ViewHolder {
        LinearLayout lnStripe;

        TextView ItemName;
        TextView RefNo;
        CheckBox IsIssue;
//        TextView lblQty;
//        ImageButton btnPlus;
//        ImageButton btnMinus;
    }


}
