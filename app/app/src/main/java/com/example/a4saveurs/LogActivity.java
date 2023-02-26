package com.example.a4saveurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogActivity extends AppCompatActivity {
    public static String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }

    public void onClick(View v){
        username = ((EditText) findViewById(R.id. username)).getText().toString();
        password = ((EditText) findViewById(R.id. password)).getText().toString();
        startActivity(new Intent(this,MainActivity.class));
    }

    public static String getUsername(){
        return username;
    }

    public static String getPassword(){
        return password;
    }
}