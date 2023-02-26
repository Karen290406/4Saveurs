package com.example.a4saveurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        switch (v.getId()){
            case(R.id.italian):
                startActivity(new Intent(this, ItalianRestaurant.class));
                break;
            case(R.id.japanese):
                startActivity(new Intent(this, JapaneseRestaurant.class));
                break;
            case(R.id.chinese):
                startActivity(new Intent(this, ChineseRestaurant.class));
                break;
            case(R.id.indian):
                startActivity(new Intent(this, IndianRestaurant.class));
                break;
            case(R.id.profile):
                startActivity(new Intent(this, Profile.class));
                break;
        }
    }
}