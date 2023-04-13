package com.example.login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button  signIn, signUp;
    private static String loggedInUser = "";
    DBHelper DB;

    public static String getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(String s) {
        loggedInUser = s;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        signIn = (Button) findViewById(R.id.btnsignin1);
        signUp = (Button) findViewById(R.id.btnsignup1);
        DB = DBHelper.getInstance(LoginActivity.this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(DB.checkUsernameExists(user) && (DB.checkUsernamePassword(user,pass))) {
                        loggedInUser = user;
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,"Incorrect Username or Password!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}