package com.example.fastbooking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogActivity extends AppCompatActivity {
    private Button btn_sign;
    private EditText etUsername, etPassword;
    private static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        btn_sign = findViewById(R.id.btn_sign);
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog mDialog = new ProgressDialog(LogActivity.this);
                mDialog.setMessage("Please Wait..");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot DataSnapshot) {

                        if (DataSnapshot.child(etUsername.getText().toString()).exists()) {

                            mDialog.dismiss();
                            user = DataSnapshot.child(etUsername.getText().toString()).getValue(User.class);

                            assert user != null;
                            if (user.getPassword().equals(etPassword.getText().toString())) {
                                startActivity(new Intent(LogActivity.this, MainActivity.class));
                                Toast.makeText(LogActivity.this, "Signed in Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LogActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mDialog.dismiss();
                            Toast.makeText(LogActivity.this, "User doesn't exist ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
    }

    public void onClick(View v) {
        startActivity(new Intent(LogActivity.this, RegActivity.class));
    }

    public static User getUser(){
        return user;
    }
}