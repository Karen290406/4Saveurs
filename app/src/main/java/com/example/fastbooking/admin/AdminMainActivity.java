package com.example.fastbooking.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fastbooking.R;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tables:
                startActivity(new Intent(this, AdminTablesActivity.class));
                break;
        }
    }
}