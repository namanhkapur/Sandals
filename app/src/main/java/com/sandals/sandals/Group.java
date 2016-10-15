package com.sandals.sandals;

import android.widget.ImageView;

import java.util.HashSet;

public class Group {
    private String name;
    private int number_members;
    private int groupID;
    private HashSet<People> members;
    private ImageView groupPhoto;

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImage(ImageView v) { groupPhoto = v; }

    public void addMember(People p) { members.add(p); }

    public void setMembersSize(int n) { number_members = n; }

    public void setGroupId(int i) { groupID = i; }

    public int getIconID() {
        return groupID;
    }
}
