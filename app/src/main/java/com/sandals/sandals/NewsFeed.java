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
import java.util.HashSet;
import java.util.List;

public class NewsFeed extends AppCompatActivity {

    HashSet<Integer> existingUsers = new HashSet<>();
    HashSet<Integer> existingNames = new HashSet<>();
    HashSet<Integer> existingStatuses = new HashSet<>();
    HashSet<Integer> existingPics = new HashSet<>();
    private List<People> users = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<String> statuses = new ArrayList<>();
    private EditText editStatus;
    private Spinner spinner;
    private ArrayAdapter<People> adapter;
    private ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_feed);

        populateLists();
        populateUsers();
        populateListView();
    }

    public void populateLists() {
        names.add("Landry Jackson");
        names.add("Finley Nguyen");
        names.add("Jamie Park");
        names.add("Sam Kenner");
        names.add("Cameran Payton");
        names.add("Kendall Smith");
        names.add("Riley Chen");
        names.add("Jordan Needham");
        names.add("Morgan Ali");
        names.add("Remy Davidson");
        names.add("Alex Parker");

        statuses.add("I am feeling troubled");
        statuses.add("It's 4AM!");
        statuses.add("That midterm though");
        statuses.add("Not feeling great today");
        statuses.add("What a great day!");
        statuses.add("Airplanes are cool");
        statuses.add("Don't bother me!");
        statuses.add("Anyone down for dinner?");
        statuses.add("Please help");
        statuses.add("So much homework...");
        statuses.add("Go Mavs!!!");
        statuses.add("Can't believe this!!");
        statuses.add("Anyone feeling lonely?");
        statuses.add("Ten days - no drinks!");
        statuses.add("That party was lit");

    }

    public int random(int num) {
        return (int) Math.round(Math.random() * num);
    }

    public void addUser(People person) {
        users.add(person);
    }

    private void populateUsers() {

        People Namanh = new People("Namanh Kapur");
        Namanh.setUserID(R.mipmap.namanh);
        Namanh.setStatus("Check out my new app, Sandal!", 50);
        Namanh.setMood(4);
        Namanh.setPhoneNumber(Long.parseLong("8322770363"));
        addUser(Namanh);

        for (int i = 0; i < 7; i++) {

            int randName = random(names.size() - 1);
            while (existingNames.contains(randName)) {
                randName = random(names.size() - 1);
            }
            existingNames.add(randName);
            People person = new People(names.get(randName));

            int randStatus = random(statuses.size() - 1);
            while (existingStatuses.contains(randStatus)) {
                randStatus = random(statuses.size() - 1);
            }
            existingStatuses.add(randStatus);
            person.setStatus(statuses.get(randStatus), 50);

            int randPic = random(9);
            while (existingPics.contains(randPic)) {
                randPic = random(9);
            }
            existingPics.add(randPic);

            int imgPath = 0;

            switch (randPic) {
                case 0:
                    imgPath = R.mipmap.p0;
                    break;
                case 1:
                    imgPath = R.mipmap.p1;
                    break;
                case 2:
                    imgPath = R.mipmap.p2;
                    break;
                case 3:
                    imgPath = R.mipmap.p3;
                    break;
                case 4:
                    imgPath = R.mipmap.p4;
                    break;
                case 5:
                    imgPath = R.mipmap.p5;
                    break;
                case 6:
                    imgPath = R.mipmap.p6;
                    break;
                case 7:
                    imgPath = R.mipmap.p7;
                    break;
                case 8:
                    imgPath = R.mipmap.p8;
                    break;
                default:
                    imgPath = R.mipmap.p9;
            }

            person.setUserID(imgPath);
            person.setMood(random(8));
            person.setPhoneNumber(Long.parseLong("8322770363"));
            addUser(person);

        }
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
                            //dataAdapter.notifyDataSetChanged();
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
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.news_feed, parent, false);
            }

            // find people to work with
            People currentPerson = users.get(position);

//            if (currentPerson.getName().equals("Namanh Kapur")){
//                currentPerson = users.get(0);
//            }
//            else {
//                int randPerson = random(users.size() - 1);
//                while (existingUsers.contains(randPerson)) {
//                    randPerson = random(users.size() - 1);
//                }
//                existingUsers.add(randPerson);
//                currentPerson = users.get(randPerson);
//            }
//            if (currentPerson.getName().equals("Namanh Kapur")) {
//                currentPerson = users.get(0);
//            } else {
//                while (existingUsers.contains(randPerson)) {
//                    randPerson = random(users.size() - 1);
//                }
//                existingUsers.add(randPerson);
//                currentPerson = users.get(randPerson);
//            }

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
