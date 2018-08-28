package com.arvipdev.arvind.ibmmasterchef;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

class BaseGroupJson {

    String gpJson(ArrayList<BaseGroup> gps) {

        try{
            JSONArray groups = new JSONArray();
            Log.d("json", "" + gps.size());
            for (BaseGroup gp: gps){
                JSONObject group = new JSONObject();
                group.put("group_name", gp.getName());
                group.put("time_stamp", gp.getTimeStamp());
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
