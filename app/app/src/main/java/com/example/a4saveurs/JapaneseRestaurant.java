package com.example.a4saveurs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class JapaneseRestaurant extends AppCompatActivity {
    String[][] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_japanese_restaurant);
    }

    @Override
    protected void onResume() {
        super.onResume();
        data = Data.getData(
                "C:\\Users\\karen\\GitHub\\4Saveurs\\app\\app\\src\\main\\java\\com\\example\\a4saveurs\\Data\\Japanese_Restaurant_time");
    }

    public void onClick(View v){
    }
}