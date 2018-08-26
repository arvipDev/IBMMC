package com.arvipdev.arvind.ibmmasterchef;
import java.util.ArrayList;

public class BaseGroup extends ArrayList<BaseGroup> {
    private String name;
    private ArrayList<BaseCandidate> candidates;

    public BaseGroup(String name, ArrayList<BaseCandidate> candidates) {
        this.candidates = candidates;
        this.name = name;
    }

    public BaseGroup(){};


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<BaseCandidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<BaseCandidate> candidates) {
        this.candidates = candidates;
    }
}
