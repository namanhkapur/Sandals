package com.sandals.sandals;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashSet;

public class CreatedGroup extends AppCompatActivity {

    String groupName;
    Group theGroup;
    HashSet<People> groupMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.created_group);
        String name = getIntent().getStringExtra("group");
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCreated);
//        setSupportActionBar(toolbar);
//        getActionBar().setTitle(name);

        theGroup = GroupActivity.getGroup(name);
        groupName = theGroup.getName();
    }

}
