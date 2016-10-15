package com.sandals.sandals;

/**
 * Created by namanh on 10/15/2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsFeed extends Activity {

    private List<People> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_feed);

        populateUsers();
        populateListView();
    }

    public void addUser(People person) {
        users.add(person);
    }

    private void populateUsers() {
        People Bob = new People("Bob");
        People Alice = new People("Alice");
        Alice.setUserID(R.mipmap.ic_launcher);
        Bob.setUserID(R.mipmap.ic_launcher);
        addUser(Bob);
        addUser(Alice);
    }

    public void populateListView() {
        ArrayAdapter<People> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.feed);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<People>{

        public MyListAdapter(){
            super(NewsFeed.this, R.layout.news_feed, users);
        }

        @Override
        public View getView(int position, View currentView, ViewGroup parent){
            // make sure we have a view to work with
            View itemView = currentView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.news_feed, parent, false);
            }

            // find people to work with
            People currentPerson = users.get(position);

            // fill the view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.p1_icon);

            imageView.setImageResource(currentPerson.getUserID());

            return itemView;
        }

    }
}
