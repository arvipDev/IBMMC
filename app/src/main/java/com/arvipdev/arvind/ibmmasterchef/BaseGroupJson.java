package com.arvipdev.arvind.ibmmasterchef;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class BaseGroupJson {

    public String gpJson (ArrayList<BaseGroup> gps) {

        try{
            JSONObject main = new JSONObject();
            JSONArray groups = new JSONArray();
            
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("gp_name", group.getName());

            JSONArray jsonCand = new JSONArray();
            for(BaseCandidate cand: group.getCandidates()){
                JSONObject jsonObj2 = new JSONObject();
                jsonObj2.put("c_name", cand.getName());
                jsonObj2.put("c_id", cand.getEmp_Id());
                jsonCand.put(jsonObj2);
            }
            jsonObj.put("candidates", jsonCand);
            groups.put(jsonObj);
            main.put("groups", groups);
            return main.toString();
        } catch(JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
