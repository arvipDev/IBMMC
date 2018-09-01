package com.arvipdev.arvind.ibmmasterchef;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arvipdev.arvind.ibmmasterchef.com.model.ibmmasterchef.BaseCandJson;
import com.arvipdev.arvind.ibmmasterchef.com.model.ibmmasterchef.BaseCandidate;
import com.arvipdev.arvind.ibmmasterchef.com.model.ibmmasterchef.BaseGroup;
import com.arvipdev.arvind.ibmmasterchef.com.service.ibmmasterchef.DatabaseHandlerCand;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CandidateActivity extends AppCompatActivity implements View.OnClickListener {

    private long ts;
    private BaseCandJson baseCandJson;
    private ArrayList<BaseCandidate> baseCandList;
    private DatabaseHandlerCand cand_DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button add_c = (Button)findViewById(R.id.add_c);
        add_c.setOnClickListener(this);

        Button delete_c = (Button)findViewById(R.id.delete_c);
        delete_c.setOnClickListener(this);

        Button back_c = (Button)findViewById(R.id.back_c);
        back_c.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
            ts = (Long) bd.get("group_ts");
        Log.d("Time Stamp inside Cand", "" + ts);

        baseCandList = new ArrayList<>();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_c:
                createDialog();
                break;
            case R.id.delete_c:
                //deleteDialog();
                break;
            case R.id.back_c:
                Intent intent = new Intent(this, StatisticsActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Participant's Name");

        final EditText name = new EditText(this);
        name.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(name);

        final EditText id = new EditText(this);
        id.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(id);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //addToList(name.getText().toString(), id.getText().toString());
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

    private void addToList (String name, String id){
        if(cand_DB.getAllvalues().size() == 0 && !name.matches("")){
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

/*
    private void deleteDialog(){
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
                for (BaseGroup group: delete_gp){
                    if (group.getName().matches(input.getText().toString())){
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

    private void print (ArrayList<BaseGroup> gp){
        Log.d("size", "" + gp.size());
        for(BaseGroup bp: gp){
            Log.d("name", bp.getName());
            Log.d("ts",""+ bp.getTimeStamp());
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
*/

}
