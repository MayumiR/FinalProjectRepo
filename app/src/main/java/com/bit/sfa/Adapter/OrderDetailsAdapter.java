package com.bit.sfa.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bit.sfa.DataControl.FmItemDS;
import com.bit.sfa.Models.OrdDet;
import com.bit.sfa.R;

import java.util.ArrayList;


public class OrderDetailsAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    ArrayList<OrdDet> list;
    Context context;
    //ArrayList<FreeHed> arrayList;

    public OrderDetailsAdapter(Context context, ArrayList<OrdDet> list){
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;

    }
    @Override
    public int getCount() {
        if (list != null) return list.size();
        return 0;
    }
    @Override
    public OrdDet getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView ==null) {
            viewHolder = new ViewHolder();
            convertView =inflater.inflate(R.layout.row_order_details,parent,false);
            viewHolder.lblItem = (TextView) convertView.findViewById(R.id.row_item);
            viewHolder.lblQty = (TextView) convertView.findViewById(R.id.row_cases);
            viewHolder.lblAMt = (TextView) convertView.findViewById(R.id.row_piece);
            viewHolder.showStatus=(TextView)convertView.findViewById(R.id.row_free_status);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.lblItem.setText(new FmItemDS(convertView.getContext()).getItemNameByCode(list.get(position).getFORDDET_ITEM_CODE()) + " - " + list.get(position).getFORDDET_ITEM_CODE());
        viewHolder.lblQty.setText(list.get(position).getFORDDET_QTY());
        viewHolder.lblAMt.setText("0.0");

//        FreeHedDS freeHedDS = new FreeHedDS(context);
//        arrayList = freeHedDS.getFreeIssueItemDetailByRefno(list.get(position).getFTRANSODET_ITEMCODE(),"" );
//        for(FreeHed freeHed:arrayList){
//            int itemQty = (int) Float.parseFloat(freeHed.getFFREEHED_ITEM_QTY());
//            int enterQty = (int) Float.parseFloat(list.get(position).getFTRANSODET_QTY());
//
//            if(enterQty<itemQty){
//                //other products------this procut has't free items
//                viewHolder.showStatus.setBackgroundColor(Color.WHITE);
//            }else{
//                //free item eligible product
//                viewHolder.showStatus.setBackground(context.getResources().getDrawable(R.drawable.ic_free_b));
//            }
//
//
//        }


        return convertView;
    }
    private  static  class  ViewHolder{
        TextView lblItem;
        TextView lblQty;
        TextView lblAMt;
        TextView showStatus;

    }

}
