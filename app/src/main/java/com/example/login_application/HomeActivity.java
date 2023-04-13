package com.example.login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private TextView textView;
    private Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = (TextView) findViewById(R.id.textView);
        signOut = (Button) findViewById(R.id.btnsignout);
        textView.setText("Hello " + LoginActivity.getLoggedInUser()+ "!\n" + "You are a gandu!");

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.setLoggedInUser("");
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this,"Logged Out Successfully!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}