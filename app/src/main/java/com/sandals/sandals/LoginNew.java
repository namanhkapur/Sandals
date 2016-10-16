package com.sandals.sandals;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginNew extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText editEmail;
    private EditText editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        mAuth = FirebaseAuth.getInstance();

        Button regButton = (Button) findViewById(R.id.Register);
        Button logInButton = (Button) findViewById(R.id.Login);

        editEmail = (EditText) findViewById(R.id.LoginEmail);
        editPassword = (EditText) findViewById(R.id.LoginPassword);

        regButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent regIntent = new Intent(LoginNew.this, RegisterActivity.class);
                startActivity(regIntent);
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signIn(editEmail.getText().toString(), editPassword.getText().toString());
            }
        });

        Button test = (Button) findViewById(R.id.NewsFeed);
        test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginNew.this, NewsFeed.class);
                startActivity(intent);
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                } else {
                    // User is signed out
                }
            }
        };

    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            //Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginNew.this, "Failed to Log In",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(LoginNew.this, "Logged In!",
                                    Toast.LENGTH_SHORT).show();
                            Intent groupIntent = new Intent(LoginNew.this, GroupActivity.class);
                            startActivity(groupIntent);

                        }


                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
