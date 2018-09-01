package com.arvipdev.arvind.ibmmasterchef.com.model.ibmmasterchef;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BaseCandJson {

    public String cJson(ArrayList<BaseCandidate> gps) {
        try{
            JSONArray groups = new JSONArray();
            Log.d("json", "" + gps.size());
            for (BaseCandidate gp: gps){
                JSONObject group = new JSONObject();
                group.put("group_name", gp.getName());
                group.put("time_stamp", gp.getEmp_Id());
                groups.put(group);
            }
            JSONObject jsonGroup = new JSONObject();
            jsonGroup.put("groups", groups);
            Log.d("JSON", jsonGroup.toString());
            return jsonGroup.toString();
        } catch(JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
