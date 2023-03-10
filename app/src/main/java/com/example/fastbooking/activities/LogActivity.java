package com.example.fastbooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fastbooking.R;

public class LogActivity extends AppCompatActivity {
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }

    public void onClick(View v){
        switch (v.getId()){
            case (R. id. btn_sign):
                username = ((EditText) findViewById(R.id. username)).getText().toString();
                password = ((EditText) findViewById(R.id. password)).getText().toString();
                startActivity(new Intent(this, MenuActivity.class));
                break;
            case (R.id. btn_reg):
                startActivity(new Intent(this, RegActivity.class));
                break;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}