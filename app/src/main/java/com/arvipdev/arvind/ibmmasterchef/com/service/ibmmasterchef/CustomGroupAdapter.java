package com.arvipdev.arvind.ibmmasterchef.com.service.ibmmasterchef;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arvipdev.arvind.ibmmasterchef.R;
import com.arvipdev.arvind.ibmmasterchef.com.model.ibmmasterchef.BaseGroup;

import java.util.ArrayList;

public class CustomGroupAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<BaseGroup> groups;

    public CustomGroupAdapter(Activity context, ArrayList<BaseGroup> groups) {
        super(context, R.layout.group_row);
        this.context=context;
        this.groups = groups;

    }

    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.group_row, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        titleText.setText(groups.get(position).getName());

        return rowView;

    };
}