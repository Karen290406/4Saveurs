package com.example.fastbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectTableActivity extends AppCompatActivity {
    private static String number;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);
    }

    public void onClick(View v) {
        btn = findViewById(v.getId());
        number = btn.getText().toString();
        Toast.makeText(this, number, Toast.LENGTH_SHORT).show();
    }

    public static String getNumber() {
        return number;
    }
}