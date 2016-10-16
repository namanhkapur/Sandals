package com.sandals.sandals;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Kevin on 10/15/16.
 */

public class CallActivity extends AppCompatActivity {
    private TextView nameView;
    private Button call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Intent intent = getIntent();
        long num = intent.getLongExtra("number", 0);
        String name = intent.getStringExtra("name");
        final String numString = Long.toString(num);

        nameView = (TextView) findViewById(R.id.nameView);
        call = (Button) findViewById(R.id.call_button);
        call.setText("Call " + name);
        nameView.setText(name + ": " + Long.toString(num));
        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + numString));
                startActivity(intent);
            }
        });

    }
}
