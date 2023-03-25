package com.example.fastbooking.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fastbooking.R;

public class BookFragment extends Fragment {
    private static String number;
    private Button btn;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_book, container, false);

        return rootView;
    }

    public void onClick(View v) {
        btn = rootView.findViewById(v.getId());
        number = btn.getText().toString();
        Toast.makeText(getActivity(), number, Toast.LENGTH_SHORT).show();
    }
}