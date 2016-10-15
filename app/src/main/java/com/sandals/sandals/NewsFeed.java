package com.sandals.sandals;

/**
 * Created by namanh on 10/15/2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsFeed extends Activity {

    private List<People> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        populateUsers();
    }

    public void addUser(People person) {
        users.add(person);
    }

    private void populateUsers() {
        People Bob = new People("Bob", 0);
        People Alice = new People("Alice", 2);
        addUser(Bob);
        addUser(Alice);
    }

    public void populateListView() {
        ArrayAdapter<People> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.feed);
        list.setAdapter(adapter);
    }
}
