package com.example.login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username, password, repassword;
    private Button signup, signin;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = DBHelper.getInstance(MainActivity.this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(MainActivity.this,"Please enter all the fields!",Toast.LENGTH_SHORT).show();
                } else if (pass.equals(repass)) {

                    if (!DB.checkUsernameExists(user)) {
                        if (DB.insertData(user,pass)) {
                            Toast.makeText(MainActivity.this,"User added successfully!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        } else {
                            //Todo: Add better error handling.
                            Toast.makeText(MainActivity.this,"Error! Please try again!",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this,"This user already exists! Please sign in!",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this,"Passwords do not match!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}