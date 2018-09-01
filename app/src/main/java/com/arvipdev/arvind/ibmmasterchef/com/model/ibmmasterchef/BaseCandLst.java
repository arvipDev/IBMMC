package com.arvipdev.arvind.ibmmasterchef.com.model.ibmmasterchef;

public class BaseCandLst {

    private Long time_stamp;
    private String candidatesJSON;

    public BaseCandLst(Long time_stamp, String candidatesJSON)
    {
        this.time_stamp = time_stamp;
        this.candidatesJSON = candidatesJSON;
    }

    public BaseCandLst() {}

    public String getCandidatesJSON() {
        return candidatesJSON;
    }

    public void setCandidatesJSON(String candidatesJSON) {
        this.candidatesJSON = candidatesJSON;
    }

    public Long getTimeStamp() {
        return time_stamp;
    }

    public void setTimeStamp(Long time_stamp) {
        this.time_stamp = time_stamp;
    }
}
