package com.arvipdev.arvind.ibmmasterchef;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.arvipdev.arvind.ibmmasterchef.com.model.ibmmasterchef.BaseGroup;
import com.arvipdev.arvind.ibmmasterchef.com.service.ibmmasterchef.DatabaseHandler;
import com.arvipdev.arvind.ibmmasterchef.com.service.ibmmasterchef.GroupListAdapter;

import java.sql.Timestamp;
import java.util.ArrayList;


public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<BaseGroup> groups;
    private DatabaseHandler group_DB;
    private GroupListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Button add_gp = (Button) findViewById(R.id.add_gp);
        add_gp.setOnClickListener(this);

        Button delete_gp = (Button) findViewById(R.id.delete_gp);
        delete_gp.setOnClickListener(this);

        ListView group_LV = (ListView) findViewById(R.id.mainListView);
        groups = new ArrayList<>();
        group_DB = new DatabaseHandler(this);

        groups = group_DB.getAllvalues();
        adapter = new GroupListAdapter(this, groups);
        group_LV.setAdapter(adapter);
        group_LV.setOnItemClickListener(this);
        Log.d("groups size", "" + groups.size());
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, CandidateActivity.class);
        long ts = group_DB.getAllvalues().get(i).getTimeStamp();
        intent.putExtra("group_ts", ts);
        Log.d("extra", "" + ts);
        startActivity(intent);
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
                addToList(input.getText().toString());
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

    private void deleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter group's title");
        Log.d("out", "" + groups.size());

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);


        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ArrayList<BaseGroup> delete_gp = group_DB.getAllvalues();
                for (BaseGroup group : delete_gp) {
                    if (group.getName().matches(input.getText().toString())) {
                        deleteFromList(input.getText().toString());
                    }
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNeutralButton("Delete all", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                group_DB.deleteAll();
                adapter.refreshEvents(group_DB.getAllvalues());
            }
        });
        builder.show();
    }

    private void addToList (String name){
        BaseGroup group;
        if(group_DB.getAllvalues().size() == 0 && !name.matches("")){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            group = new BaseGroup(name, timestamp.getTime());
            Log.d("2 name", group.getName());
            Log.d("2 input", name);
            groups = group_DB.add(group);
            adapter.refreshEvents(group_DB.getAllvalues());
        } else if (group_DB.getAllvalues().size() > 0 && !name.matches("")) {
            for (BaseGroup gp : group_DB.getAllvalues()) {
                Log.d("1 name", gp.getName());
                Log.d("1 input", name);
                if(gp.getName().matches(name)){
                    Toast.makeText(getApplicationContext(), "Group already exists, enter another name.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            group = new BaseGroup(name, timestamp.getTime());
            groups = group_DB.add(group);
            adapter.refreshEvents(group_DB.getAllvalues());
        } else {
            if(name.matches("")){
                Toast.makeText(getApplicationContext(), "Enter a valid group name.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void deleteFromList (String name){
        if(group_DB.getAllvalues().size() == 0 ){
            Toast.makeText(getApplicationContext(), "No entries to delete from.", Toast.LENGTH_SHORT).show();
        } else if (name.matches("")) {
            Toast.makeText(getApplicationContext(), "Enter a valid group name.", Toast.LENGTH_LONG).show();
        }else if (group_DB.getAllvalues().size() > 0 && !name.matches("")) {
            for (BaseGroup gp : group_DB.getAllvalues()) {
                Log.d("1 name", gp.getName());
                Log.d("1 input", name);
                if(gp.getName().matches(name)){
                    group_DB.deleteFromDB(gp);
                    Log.d("inside for", "got it");
                    adapter.refreshEvents(group_DB.getAllvalues());
                    return;
                }
            }
        }else Toast.makeText(getApplicationContext(), "Group does not exist, enter another name.", Toast.LENGTH_LONG).show();
    }
}
