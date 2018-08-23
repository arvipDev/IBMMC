package com.arvipdev.arvind.ibmmasterchef;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> group = new ArrayList<>();
    private String gp_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Button add_gp = (Button) findViewById(R.id.add_gp);
        add_gp.setOnClickListener(this);

        Button delete_gp = (Button) findViewById(R.id.delete_gp);
        delete_gp.setOnClickListener(this);

        Button edit_gp = (Button) findViewById(R.id.edit_gp);
        edit_gp.setOnClickListener(this);

        Log.d("List Size", "" + group.isEmpty());

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.add_gp:
                createDialog();
                break;
            case R.id.delete_gp:
                break;
            case R.id.edit_gp:
                break;
        }
    }

    private void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);


        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gp_name = input.getText().toString();
                Log.d("Name", gp_name);
                addToList(gp_name);
                Log.d("out", "" + group.size());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private ArrayList<String> addToList(String gp_name){
        group.add(gp_name);
        return group;
    }

}
