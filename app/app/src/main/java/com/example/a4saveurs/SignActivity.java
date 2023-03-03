package com.example.a4saveurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a4saveurs.Data.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignActivity extends AppCompatActivity {
    private String USER_KEY = "User";
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    public void onClick(View v){
        String id = mDataBase.getKey();
        String username = ((EditText) findViewById(R.id. username)).getText().toString();
        String password = ((EditText) findViewById(R.id. password)).getText().toString();
        User user = new User(username,password, id);
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
            mDataBase.push().setValue(user);
            startActivity(new Intent(this,LogActivity.class));
        }
        else Toast.makeText(this, "Empty username or password", Toast.LENGTH_SHORT).show();
    }
}