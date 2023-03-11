package com.example.fastbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fastbooking.LogActivity;
import com.example.fastbooking.MyAdapter;
import com.example.fastbooking.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private String username, password, email;
    private FrameLayout buttonContainer;
//    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = LogActivity.getUsername();
        password = LogActivity.getPassword();
        email = LogActivity.getEmail();
        ((TextView) findViewById(R.id. username)).setText(username);

//        mRecyclerView = findViewById(R.id.recycler_view);
//
//        MyAdapter adapter = new MyAdapter(data);
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(adapter);

        buttonContainer = findViewById(R.id.button_container);

        buttonContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyDialogFragment();
            }
        });
    }

    public void onClick(View v){
        startActivity(new Intent(this, SelectTable.class));
    }

    private void showMyDialogFragment() {
        FragmentManager fm = getSupportFragmentManager();
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(fm, "MyDialogFragment");
    }
}