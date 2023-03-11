package com.example.fastbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        etUsername = findViewById(R.id. username);
        etPassword = findViewById(R.id. password);
        etEmail = findViewById(R.id. email);

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference table_user=database.getReference("User");

        Button btnSignup = findViewById(R.id.btn_sign);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog mDialog = new ProgressDialog(RegActivity.this);
                mDialog.setMessage("Please Wait..");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(etUsername.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(RegActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }else{
                            mDialog.dismiss();
                            User user = new User(etUsername.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
                            table_user.child(etUsername.getText().toString()).setValue(user);
                            startActivity(new Intent(RegActivity.this, LogActivity.class));
                            Toast.makeText(RegActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(RegActivity.this, "Database error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}