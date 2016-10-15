package com.sandals.sandals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        Button regButton = (Button) findViewById(R.id.Register);
        Button logInButton = (Button) findViewById(R.id.Login);
        regButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent regIntent = new Intent(LoginNew.this, RegisterActivity.class);
                startActivity(regIntent);
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // authenticate
            }
        });
    }
}
