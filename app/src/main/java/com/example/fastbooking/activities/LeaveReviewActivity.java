package com.example.fastbooking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fastbooking.R;
import com.example.fastbooking.classes.Review;
import com.example.fastbooking.classes.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LeaveReviewActivity extends AppCompatActivity {
    private User user;
    private String reviewText, username;
    private float reviewRating;
    private Review review;
    private Button btn_submit;
    private static final String TAG = "ReviewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_review);
        user = LogActivity.getUser();
        btn_submit = findViewById(R.id. btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Reviews");

                username = user.getUsername();
                reviewText = ((EditText) findViewById(R.id.special_requests)).getText().toString();
                reviewRating = ((RatingBar) findViewById(R.id.rating_bar)).getRating();
                review = new Review(username, reviewRating, reviewText);

                myRef.child(username).setValue(review, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error != null) {
                            Log.d(TAG, "Failed to write review to database: " + error.getMessage());
                        } else {
                            Log.d(TAG, "Review saved successfully to database.");
                            startActivity(new Intent(LeaveReviewActivity.this, MainActivity.class));
                        }
                    }
                });
            }
        });
    }
}