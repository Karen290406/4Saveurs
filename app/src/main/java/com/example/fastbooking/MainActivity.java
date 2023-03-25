package com.example.fastbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_book:
                startActivity(new Intent(this, BookActivity.class));
                break;
            case R.id. btn_cart:
                startActivity(new Intent(this, CartActivity.class));
                break;
            case R.id.btn_reviews:
                startActivity(new Intent(this, ReviewsActivity.class));
                break;
        }
    }
}