package com.sandals.sandals;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by namanh on 10/15/2016.
 */

public class Populate extends Activity {

    private List<People> users = new ArrayList<People>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
    }

    public void addUser(People person) {
        users.add(person);
    }

}
