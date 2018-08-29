package com.arvipdev.arvind.ibmmasterchef.com.service.ibmmasterchef;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;

public class CustomGroupAdapter extends ArrayAdapter<String> implements View.OnClickListener{

    public CustomGroupAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void onClick(View view) {

    }
}
