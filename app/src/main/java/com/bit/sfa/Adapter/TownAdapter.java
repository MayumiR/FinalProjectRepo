package com.bit.sfa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bit.sfa.Models.Town;
import com.bit.sfa.R;

import java.util.ArrayList;

/**
 * Created by Rashmi.
 */

public class TownAdapter extends ArrayAdapter<Town> {

    Context context;
    ArrayList<Town> list;

    public TownAdapter(Context context, ArrayList<Town> list) {
        super(context, R.layout.row_non_productive_header, list);
        this.context = context;
        this.list = list;
        // TODO Auto-generated constructor stub
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        LayoutInflater inflater = null;
        View row = null;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.item_listview, parent, false);

        TextView townCodeTxt = (TextView) row.findViewById(R.id.tv_item_code);
        TextView twonName = (TextView) row.findViewById(R.id.TextView01);

        townCodeTxt.setText(list.get(position).getFTOWN_CODE());
        twonName.setText(list.get(position).getFTOWN_NAME());
        return row;
    }

}
