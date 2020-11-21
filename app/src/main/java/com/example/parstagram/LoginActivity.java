package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            //goTimeLineActivity();
            goMainActivity();
        }


        
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login Button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick sign up Button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                signupUser(username, password);

            }
        });


    }

    private void signupUser(String username, String password) {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    // Sign up didn't succeed. Look at the ParseException to figure out what went wrong
                    Toast.makeText(LoginActivity.this,"Problem With Sign Up", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Issue with Sign Up", e);
                    return;
                }

                //goTimeLineActivity();
                goMainActivity();
                Toast.makeText(LoginActivity.this,"Sign Up Success", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user" + username);

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    Toast.makeText(LoginActivity.this,"Problem With Login", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Issue with Login", e);
                    return;
                }

                //goTimeLineActivity();
                goMainActivity();
                Toast.makeText(LoginActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goTimeLineActivity() {
        Intent i = new Intent(this, TimeLineActivity.class);
        startActivity(i);
        finish();
    }
}
