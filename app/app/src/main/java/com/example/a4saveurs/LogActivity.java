package com.example.a4saveurs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a4saveurs.Data.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity {
    private static String username, password;
    private List<User> listData;
    private String USER_KEY = "User";
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        listData = new ArrayList<>();
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        getDataFromDB();
    }

    public void onClick(View v){
        username = ((EditText) findViewById(R.id. username)).getText().toString();
        password = ((EditText) findViewById(R.id. password)).getText().toString();
        for (User ld : listData) {
            if(ld.name == username && ld.password == password){
                startActivity(new Intent(this,MainActivity.class));
                break;
            }
        }
        Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT);
    }

    public static String getUsername(){
        return username;
    }

    public static String getPassword(){
        return password;
    }

    private void getDataFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (listData.size() > 0) listData.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    assert user != null;
                    listData.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDataBase.addValueEventListener(vListener);
    }
}