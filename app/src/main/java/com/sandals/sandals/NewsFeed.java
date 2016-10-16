package com.sandals.sandals;

/**
 * Created by namanh on 10/15/2016.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsFeed extends AppCompatActivity {

    private List<People> users = new ArrayList<>();
    private EditText editStatus;
    private Spinner spinner;
    private ArrayAdapter<People> adapter;
    private ArrayAdapter<String> dataAdapter;

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
        Namanh.setStatus("Check out my new app, Sandals!", 100);
        Namanh.setMood(5);
        Namanh.setPhoneNumber(Long.parseLong("8322770363"));

        Kevin.setUserID(R.mipmap.kevin);
        Kevin.setStatus("I need more caffeine...", 100);
        Kevin.setMood(1);
        Kevin.setPhoneNumber(Long.parseLong("7143264413"));

        Casey.setUserID(R.mipmap.casey);
        Casey.setStatus("Airplanes", 100);
        Casey.setMood(5);
        Casey.setPhoneNumber(Long.parseLong("5623166537"));

        Hasnain.setUserID(R.mipmap.hasnain);
        Hasnain.setStatus("Made another half court shot!", 100);
        Hasnain.setMood(6);
        Hasnain.setPhoneNumber(Long.parseLong("8323824287"));

        Anthony.setUserID(R.mipmap.anthony);
        Anthony.setStatus("I'm really good looking", 100);
        Anthony.setMood(4);
        Anthony.setPhoneNumber(Long.parseLong("7138288185"));

        addUser(Namanh);
        addUser(Kevin);
        addUser(Casey);
        addUser(Hasnain);
        addUser(Anthony);
    }

    public void populateListView() {
        adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.feed);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                if (position == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewsFeed.this);
                    builder.setTitle("How Are You Feeling?");
                    editStatus = new EditText(NewsFeed.this);
                    editStatus.setHint("Let you group know your emotion");
                    spinner = new Spinner(NewsFeed.this);

                    ArrayList<String> list = new ArrayList<String>();
                    list.add("Happy");
                    list.add("Mad");
                    list.add("Nervous");
                    list.add("Surprised");
                    list.add("Excited");
                    list.add("Confused");
                    list.add("Afraid");
                    list.add("Love");
                    list.add("Sad");


                    dataAdapter = new ArrayAdapter<String>(NewsFeed.this, android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(dataAdapter);

                    LinearLayout ll = new LinearLayout(NewsFeed.this);
                    ll.setOrientation(LinearLayout.VERTICAL);
                    ll.addView(editStatus);
                    spinner.setPadding(5, 10, 5, 10);

                    ll.addView(spinner);
                    builder.setView(ll);
                    builder.setCancelable(false);

                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            users.get(0).setStatus(editStatus.getText().toString(), 100);
                            users.get(0).setMood(spinner.getSelectedItemPosition());
                            dataAdapter.notifyDataSetChanged();
                            adapter.notifyDataSetChanged();
                        }
                    });

                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {
                    Intent callIntent = new Intent(NewsFeed.this, CallActivity.class);
                    callIntent.putExtra("number", users.get(position).getPhoneNumber());
                    callIntent.putExtra("name", users.get(position).getName());
                    startActivity(callIntent);
                }

            }

        });
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
            personMood.setImageResource(currentPerson.getMood());

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
