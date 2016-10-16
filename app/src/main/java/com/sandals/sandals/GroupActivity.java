package com.sandals.sandals;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GroupActivity extends AppCompatActivity {

    ListView myGroups;
    ArrayList<HashMap<String, Object>> groupsData;
    HashSet<Group> allGroups = new HashSet<>();
    private EditText editGroupName;
    private boolean hasImported = false;

    private ArrayList<String> list = new ArrayList<>();

    static HashMap<String, Group> groupMeGroups = new HashMap<>();
    static HashMap<String, Group> createdGroups = new HashMap<>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    static protected Group getGroup(String n) {
        if (groupMeGroups.containsKey(n)) {
            return groupMeGroups.get(n);
        } else {
            return createdGroups.get(n);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton group = (FloatingActionButton) findViewById(R.id.create);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GroupActivity.this);
                builder.setTitle(R.string.create_new_group);
                editGroupName = new EditText(GroupActivity.this);
                builder.setView(editGroupName);

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // User clicked OK button

                        Group newGroup = new Group(editGroupName.getText().toString());

                        if (!groupMeGroups.containsKey(newGroup.getName()) && !createdGroups.containsKey(newGroup.getName())) {
                            allGroups.add(newGroup);
                            list.add(0, newGroup.getName());
                            createdGroups.put(newGroup.getName(), newGroup);

                            ArrayAdapter adapter = new ArrayAdapter<>(GroupActivity.this, android.R.layout.simple_list_item_1, list);
                            myGroups = (ListView) findViewById(R.id.myGroups);
                            myGroups.setAdapter(adapter);
                            myGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView parent, View view, int position, long id) {
                                    String groupName = (String) parent.getItemAtPosition(position);
                                    Toast.makeText(getBaseContext(),groupName + "is selected",
                                            Toast.LENGTH_SHORT).show();

                                    // Go to NewsFeed
                                    if (groupMeGroups.containsKey(groupName)) {
                                        Intent goToGroup = new Intent(GroupActivity.this, NewsFeed.class);
                                        startActivity(goToGroup);
                                    } else {
                                        // Go to created class
                                        Intent goToGroup = new Intent(GroupActivity.this, CreatedGroup.class);
                                        goToGroup.putExtra("group", groupName);
                                        startActivity(goToGroup);
                                    }

                                }

                            });
                        }
                    }
                });

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GroupActivity.this);
                builder.setTitle(R.string.import_from_group_me);

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (hasImported) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(GroupActivity.this);
                            builder.setTitle(R.string.already_imported);
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User acknowledgement

                                }
                            });

                            return;
                        }

                        // User wants to import!
                        hasImported = true;
                        HttpResponse response;
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        try {
                            HttpClient client = new DefaultHttpClient();
                            HttpGet request = new HttpGet();

                            // Casey: 4c87fed074db013488c2001f5e6f38da
                            // Kevin: kQeWcJ58iUzUc3pjB67MB7ClKTwrjSAwpkXVJ9SO

                            // Requesting data!
                            request.setURI(new URI("https://api.groupme.com/v3/groups?token=kQeWcJ58iUzUc3pjB67MB7ClKTwrjSAwpkXVJ9SO"));
                            response = client.execute(request);
                            String data = convertStreamToString(response.getEntity().getContent());
                            JSONObject json = new JSONObject(data);
                            Map<String, Object> retMap = new HashMap<>();

                            if (json != JSONObject.NULL) {
                                retMap = toMap(json);
                            }

                            // Pulls all groups!
                            groupsData = (ArrayList<HashMap<String, Object>>) retMap.get("response");

                            // Create groups
                            createGroups();

                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        } catch (ClientProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void createGroups() {
        Group addGroup;
        People member;
        HashMap<String, Object> g;
        ArrayList<HashMap<String, Object>> groupMembers;
        Iterator<HashMap<String, Object>> groups = groupsData.iterator();

        while (groups.hasNext()) {
            g = groups.next();

            // If existing name exists, move on.
            addGroup = new Group((String) g.get("name"));
            if (groupMeGroups.containsKey(addGroup.getName()) || createdGroups.containsKey(addGroup.getName())) {
                continue;
            }
            if (!g.get("image_url").equals(null)) {
                addGroup.setImage((String) g.get("image_url"));
            }
            addGroup.setGroupId(Integer.parseInt((String) g.get("group_id")));
            groupMembers = (ArrayList<HashMap<String, Object>>) g.get("members");

            for (HashMap<String, Object> p : groupMembers) {
                member = new People((String) p.get("nickname"));
                if (!p.get("image_url").equals(null)) {
                    member.setIconID((String) p.get("image_url"));
                }
                member.setUserID(Integer.parseInt((String) p.get(
                        "user_id")));
                addGroup.addMember(member);
            }
            allGroups.add(addGroup);
            groupMeGroups.put(addGroup.getName(), addGroup);
            list.add((String) g.get("name"));
        }
        myGroups =  (ListView) findViewById(R.id.myGroups);
        ArrayAdapter adapter = new ArrayAdapter<>(GroupActivity.this, android.R.layout.simple_list_item_1, list);
        myGroups.setAdapter(adapter);

        myGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String groupName = (String) parent.getItemAtPosition(position);
                Toast.makeText(getBaseContext(),groupName + "is selected",
                        Toast.LENGTH_SHORT).show();

                // Go to NewsFeed
                if (groupMeGroups.containsKey(groupName)) {
                    Intent goToGroup = new Intent(GroupActivity.this, NewsFeed.class);
                    startActivity(goToGroup);
                } else {
                    // Go to created class
                    Intent goToGroup = new Intent(GroupActivity.this, CreatedGroup.class);
                    goToGroup.putExtra("group", groupName);
                    startActivity(goToGroup);
                }

            }

        });
    }


    private static String convertStreamToString(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 1024);
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                inputStream.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    private static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    private static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Group Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
