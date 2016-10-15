package com.sandals.sandals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boolean isRegistered = getSharedPreferences("isRegistered", 0).getBoolean("isRegistered", false);
        boolean isLoggedIn = getSharedPreferences("isLoggedIn", 0).getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            Intent goToGroup = new Intent(MainActivity.this, GroupActivity.class);
            startActivity(goToGroup);
        } else {
            Intent goToLogIn = new Intent(MainActivity.this, LoginNew.class);
            startActivity(goToLogIn);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
