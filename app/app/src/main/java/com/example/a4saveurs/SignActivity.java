package com.example.a4saveurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignActivity extends AppCompatActivity {
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
    }

    public void onClick(View v){
        username = ((EditText) findViewById(R.id. username)).getText().toString();
        password = ((EditText) findViewById(R.id. password)).getText().toString();
        startActivity(new Intent(this,LogActivity.class));
    }
}