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

import java.io.FileOutputStream;
import java.util.ArrayList;


public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<BaseGroup> groups = new ArrayList<>();
    private BaseGroup group;
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
        group = new BaseGroup();

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
        boolean contains = true;
        BaseGroupJson jsonConv = new BaseGroupJson();

        if (name.matches(""))
            Toast.makeText(getApplicationContext(), "Enter a valid group name.", Toast.LENGTH_SHORT).show();
        else if(groups.size() == 0) {
            group.setName(name);
            groups.add(group);
            jsonConv.gpJson(group);
            Log.d("Name", group.getName());
            Log.d("out", "" + groups.size());
        } else {
            for (BaseGroup gp : groups) {
                Log.d("inside for  ", name);
                if(gp.getName().matches(name)){
                    Log.d("Group exists: ", name);
                    Log.d("out", "" + groups.size());
                    contains = true;
                    Toast.makeText(getApplicationContext(), "Group already exists, enter another name.", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    contains = false;
                    Log.d("new: ", name);
                }
            }
            if(!contains){
                group.setName(name);
                groups.add(group);
                Log.d("Name", group.getName());
                Log.d("out", "" + groups.size());
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
}
