package com.arvipdev.arvind.ibmmasterchef;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> groups = new ArrayList<>();
    private String gp_name;
    private ListView group_LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Button add_gp = (Button) findViewById(R.id.add_gp);
        add_gp.setOnClickListener(this);

        Button delete_gp = (Button) findViewById(R.id.delete_gp);
        delete_gp.setOnClickListener(this);

        group_LV = (ListView) findViewById(R.id.mainListView);

        Log.d("List Size", "" + groups.isEmpty());

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.add_gp:
                createDialog();
                break;
            case R.id.delete_gp:
                deleteDialog();
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
                if(gp_name.matches("")){
                    Toast.makeText(getApplicationContext(), "Please enter a valid title for the group", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("Name", gp_name);
                    addToList(gp_name);
                    Log.d("out", "" + groups.size());
                }

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

    private void deleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter group's title");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);


        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gp_name = input.getText().toString();
                if(gp_name.matches("")){
                    Toast.makeText(getApplicationContext(), "Please enter a valid title for the group", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("Name", gp_name);
                    deleteGroups(gp_name);
                    Log.d("out", "" + groups.size());
                }

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

    private void addToList(String gp_name){
        groups.add(gp_name);
        storeGroups(groups);
    }

    private void storeGroups (ArrayList<String> groupsList){
        SharedPreferences prefs = getSharedPreferences("group", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        try{
            editor.putString("GroupList",  ObjectSerializer.serialize(groupsList));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    private ArrayList<String> getGroups () {
        ArrayList<String> groupsList   = new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences("User", Context.MODE_PRIVATE);
        try {
            groupsList = (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString("GroupList", ObjectSerializer.serialize(new ArrayList())));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groupsList;
    }

    private ArrayList<String> deleteGroups (String gpName) {
        ArrayList<String> groupsList = getGroups();
        groupsList.remove(gpName);
        storeGroups(groupsList);
        groups = groupsList;
        return groupsList;
    }

}
