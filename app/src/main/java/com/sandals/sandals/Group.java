package com.sandals.sandals;

import android.widget.ImageView;

import java.util.HashSet;

public class Group {
    private String name;
    private int number_members;
    private int groupID;
    private HashSet<People> members;
    private String groupPhotoURL;

    public Group(String name) {
        this.name = name;
        this.groupPhotoURL = "";
        this.members = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setImage(String v) { groupPhotoURL = v; }

    public void addMember(People p) { members.add(p); }

    public void setMembersSize(int n) { number_members = n; }

    public void setGroupId(int i) { groupID = i; }

    public int getIconID() {
        return groupID;
    }

    public String toString() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return name != null ? name.equals(group.name) : group.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
