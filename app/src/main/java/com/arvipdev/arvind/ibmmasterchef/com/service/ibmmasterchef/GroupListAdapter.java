package com.arvipdev.arvind.ibmmasterchef.com.service.ibmmasterchef;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arvipdev.arvind.ibmmasterchef.R;
import com.arvipdev.arvind.ibmmasterchef.com.model.ibmmasterchef.BaseGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GroupListAdapter extends ArrayAdapter<BaseGroup> {

    private Context mContext;
    private ArrayList<BaseGroup> groups = new ArrayList<>();

    public GroupListAdapter(@NonNull Context context, ArrayList<BaseGroup> list) {
        super(context, 0 , list);
        mContext = context;
        groups = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.group_row,parent,false);

        BaseGroup group = groups.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.group_name_tv);
        name.setText(group.getName());

        return listItem;
    }

    public void refreshEvents(ArrayList<BaseGroup> gp) {
        this.groups.clear();
        this.groups.addAll(gp);
        notifyDataSetChanged();
    }
}