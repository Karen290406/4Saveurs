package com.example.a4saveurs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    String username, password, VIP;
    TextView profileName;
    Button VIPtimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        username = LogActivity.getUsername();
        password = LogActivity.getPassword();
        profileName = findViewById(R.id. profileName);
        VIPtimer = findViewById(R.id.VIPtimer);
        profileName.setText(username);
        VIPtimer.setText(VIP);
    }
}