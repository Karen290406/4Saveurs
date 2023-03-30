package com.example.fastbooking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fastbooking.R;
import com.example.fastbooking.classes.Review;
import com.example.fastbooking.ReviewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewsActivity extends AppCompatActivity {
    private Button btn;
    private ArrayList<Review> reviews = new ArrayList<>();
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        btn = findViewById(R.id.btn_leave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReviewsActivity.this, LeaveReviewActivity.class));
            }
        });

        // удаляем предыдущие записи таблиц из списка
        reviews.clear();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tablesRef = database.getReference("Reviews");

        tablesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Review> reviewList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Review review = childSnapshot.getValue(Review.class);
                    reviewList.add(review);
                }
                reviews.clear();
                reviews.addAll(reviewList);
                ReviewAdapter adapter = new ReviewAdapter(ReviewsActivity.this, reviews);
                listview = findViewById(R.id.list_view);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Обработка ошибок при чтении данных из Firebase
            }
        });
    }
}