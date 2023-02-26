package com.example.a4saveurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ChineseRestaurant extends AppCompatActivity {
    String[][] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_restaurant);
    }

    @Override
    protected void onResume() {
        super.onResume();
        data = Data.getData(
                "C:\\Users\\karen\\GitHub\\4Saveurs\\app\\app\\src\\main\\java\\com\\example\\a4saveurs\\Data\\Chinese_Restaurant_time");
    }

    public void onClick(View v){
        Toast.makeText(this, data[0][0], Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}