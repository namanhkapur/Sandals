package com.sandals.sandals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Button regButton = (Button) findViewById(R.id.buttonRegister);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });
    }

    private void attemptRegister() {
        // register

        // Casey made this change here
        Intent goToGroup = new Intent(RegisterActivity.this, Group.class);
        startActivity(goToGroup);
    }
}
