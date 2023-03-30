package com.example.fastbooking.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fastbooking.classes.Book;
import com.example.fastbooking.R;
import com.example.fastbooking.classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartActivity extends AppCompatActivity {
    private Book book;
    private static final String TAG = "CartActivity";
    private TextView date_time, guests_number, table_number, special_requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        User user = LogActivity.getUser();

        date_time = findViewById(R.id.time_date);
        guests_number = findViewById(R.id.guests_number);
        table_number = findViewById(R.id.table_number);
        special_requests = findViewById(R.id.special_requests);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Book");

        myRef.child(user.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                book = dataSnapshot.getValue(Book.class);
                assert book != null;
                date_time.setText(book.getDate() + " " + book.getTime());
                guests_number.setText(book.getGuestsNumber() + " guests");
                table_number.setText("Table № " + book.getTable_number());
                special_requests.setText("Special requests \n" + book.getSpecialRequests());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadBook:onCancelled", databaseError.toException());
            }
        });
    }
}