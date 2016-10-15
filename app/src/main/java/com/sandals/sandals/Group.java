package com.sandals.sandals;

import java.util.HashSet;

/**
 * Created by Casey_The_Magic on 10/15/16.
 */

public class Group {
    private String name;
    private int number_members;
    private int groupID;
    private HashSet<People> members;

    public Group(String name, int num, int id) {
        this.name = name;
        this.number_members = num;
        this.groupID = id;
    }

    public String getName() {
        return name;
    }

    public int getIconID() {
        return groupID;
    }
}
