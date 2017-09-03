package com.codehex2k17.rahul.quickzfinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by vidhant on 13/3/17.
 */

public class customlistview extends ArrayAdapter<String> {
    private String[] barcodeids;
    private String[] nameitems;
    private String[] finalprices;
    public static TextView textViewId;

    private Activity context;


    public customlistview(Activity context, String[] barcodeids, String[] nameitems, String[] finalprices) {
        super(context, R.layout.activity_cart, barcodeids);
        this.context = context;
        this.barcodeids = barcodeids;
        this.nameitems = nameitems;
        this.finalprices = finalprices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listviewfile, null, true);
        textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);


        textViewId.setText(barcodeids[position]);
        textViewName.setText(nameitems[position]);
        textViewPrice.setText(finalprices[position]);


        return listViewItem;
    }





}