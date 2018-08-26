package com.arvipdev.arvind.ibmmasterchef;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ChefActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.add_gp:
                break;
            case R.id.delete_gp:
                break;
        }
    }


}
