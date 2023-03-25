package com.example.fastbooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewAdapter extends ArrayAdapter<Review> {

    public ReviewAdapter(Context context, ArrayList<Review> reviews) {
        super(context, 0, reviews);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Review review = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_review, parent, false);
        }
        TextView textName = convertView.findViewById(R.id.text_name);
        RatingBar ratingBar = convertView.findViewById(R.id.rating_bar);
        TextView textComment = convertView.findViewById(R.id.text_comment);

        textName.setText(review.getUsername());
        ratingBar.setRating(review.getReviewRating());
        textComment.setText(review.getReviewText());

        return convertView;
    }
}