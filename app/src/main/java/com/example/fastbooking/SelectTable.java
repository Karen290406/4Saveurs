package com.example.fastbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SelectTable extends AppCompatActivity {
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private String[] mNumbers = new String[20];
    private static String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);

        for (int i = 0; i < mNumbers.length; i++) {
            mNumbers[i] = String.valueOf(i + 1);
        }

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mNumbers);

        mListView = findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                number = mAdapter.getItem(position);
                Toast.makeText(SelectTable.this, number, Toast.LENGTH_SHORT).show();
//                showTimeFragment();
            }
        });
    }

    public static String getNumber() {
        return number;
    }

//    private void showTimeFragment() {
//        FragmentManager fm = getSupportFragmentManager();
//        TimeFragment timeFragment = new TimeFragment();
//        timeFragment.show(fm, "TimeFragment");
//    }
}