package com.example.fastbooking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private String[] mData;

    public MyAdapter(String[] data) {
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String item = mData[position];
        holder.mTitleTextView.setText(item);
        holder.mUrlTextView.setText("http://" + item);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;
        TextView mUrlTextView;

        MyViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.item_title);
            mUrlTextView = itemView.findViewById(R.id.item_url);
        }
    }
}