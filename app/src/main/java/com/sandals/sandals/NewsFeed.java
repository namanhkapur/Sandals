package com.sandals.sandals;

/**
 * Created by namanh on 10/15/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsFeed extends AppCompatActivity {

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
        People Namanh = new People("Namanh Kapur");
        People Kevin = new People("Kevin Lin");
        People Casey = new People("Casey Takeda");
        People Hasnain = new People("Hasnain Ali");
        People Anthony = new People("Anthony Cho");

        Namanh.setUserID(R.mipmap.namanh);
        Namanh.setStatus("Check out my new app, Sandals!", 50);
        Namanh.setMood(5);

        Kevin.setUserID(R.mipmap.kevin);
        Kevin.setStatus("I need more caffeine...", 50);
        Kevin.setMood(1);

        Casey.setUserID(R.mipmap.casey);
        Casey.setStatus("Airplanes", 50);
        Casey.setMood(5);

        Hasnain.setUserID(R.mipmap.hasnain);
        Hasnain.setStatus("Made another half court shot!", 50);
        Hasnain.setMood(6);

        Anthony.setUserID(R.mipmap.anthony);
        Anthony.setStatus("I'm really good looking", 50);
        Anthony.setMood(4);

        addUser(Namanh);
        addUser(Kevin);
        addUser(Casey);
        addUser(Hasnain);
        addUser(Anthony);
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

            // mood
            ImageView personMood = (ImageView)itemView.findViewById(R.id.p1_emotion) ;
            imageView.setImageResource(/*currentPerson.getMood()*/ currentPerson.getUserID());

            // name
            TextView personName = (TextView)itemView.findViewById(R.id.p1_name);
            personName.setText(currentPerson.getName());

            // status
            TextView personStatus = (TextView)itemView.findViewById(R.id.p1_status);
            personStatus.setText(currentPerson.getStatus());

            // time stamp with date
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm MM/dd/yyyy");
            String format = simpleDateFormat.format(new Date());
            TextView personTimeStamp = (TextView)itemView.findViewById(R.id.p1_statustime);
            personTimeStamp.setText(format);

            return itemView;
        }

    }
}
