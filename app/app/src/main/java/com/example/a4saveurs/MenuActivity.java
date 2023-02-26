package com.example.a4saveurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClick(View v){
        switch (v.getId()){
            case(R.id.login):
                startActivity(new Intent(this, LogActivity.class));
                break;
            case(R.id.signup):
                startActivity(new Intent(this, SignActivity.class));
                break;
        }
    }
}