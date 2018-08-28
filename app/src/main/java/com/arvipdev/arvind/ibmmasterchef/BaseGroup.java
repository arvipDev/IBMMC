package com.arvipdev.arvind.ibmmasterchef;
import java.util.ArrayList;

public class BaseGroup extends ArrayList<BaseGroup> {
    private String name;
    private long time_stamp;

    public BaseGroup(String name, long time_stamp) {
        this.time_stamp = time_stamp;
        this.name = name;
    }

    public BaseGroup(){};


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeStamp() {
        return time_stamp;
    }

    public void setTimeStamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }
}
