package com.arvipdev.arvind.ibmmasterchef;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;


public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<BaseGroup> groups;
    private BaseGroup group;
    private ListView group_LV;
    private BaseGroupJson groupJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Button add_gp = (Button) findViewById(R.id.add_gp);
        add_gp.setOnClickListener(this);

        Button delete_gp = (Button) findViewById(R.id.delete_gp);
        delete_gp.setOnClickListener(this);

        group_LV = (ListView) findViewById(R.id.mainListView);
        groups = new ArrayList<>();
        groupJson = new BaseGroupJson();

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
                addToList (input.getText().toString());
                writeJson(groupJson.gpJson(groups));
                print(groups);
                readJson();
                Log.d("read", readJson());
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
        Log.d("out", "" + groups.size());

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);


        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeFromList(input.getText().toString());
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

    private void addToList (String name){
        if(groups.size() == 0 && !name.matches("")){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            group = new BaseGroup(name, timestamp.getTime());
            groups.add(group);
        } else if (groups.size() > 0 && !name.matches("")) {
            for (BaseGroup gp : groups) {
                if(gp.getName().matches(name)){
                    Toast.makeText(getApplicationContext(), "Group already exists, enter another name.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            group = new BaseGroup(name, timestamp.getTime());
            groups.add(group);
        } else {
            if(name.matches("")){
                Toast.makeText(getApplicationContext(), "Enter a valid group name.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void removeFromList (String name) {
        int index = -1;
        for (BaseGroup gp : groups) {
            if(gp.getName().matches(name))
                index = groups.indexOf(gp);
            else Toast.makeText(getApplicationContext(), "Please enter a valid title for the group", Toast.LENGTH_SHORT).show();
        }
        if (index > -1){
            groups.remove(index);
            Log.d("Name", "" + groups.get(index).getName());
            Log.d("out", "" + groups.size());
        }
    }

    private void writeJson (String json){
        String filename = "config.json";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readJson () {
        String filename = "config.json";
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput(filename)));
            String inputString;
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString).append("\n");
            }
            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private void print (ArrayList<BaseGroup> gp){
        Log.d("size", "" + gp.size());
        for(BaseGroup bp: gp){
            Log.d("name", bp.getName());
            Log.d("ts",""+ bp.getTimeStamp());
        }
    }
}
