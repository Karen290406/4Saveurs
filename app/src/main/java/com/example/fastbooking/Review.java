package com.example.fastbooking;

public class Review {
    private String reviewText, username;
    private float reviewRating;

    public Review() {
    }

    public Review( String username, float reviewRating, String reviewText) {
        this.reviewText = reviewText;
        this.username = username;
        this.reviewRating = reviewRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(float reviewRating) {
        this.reviewRating = reviewRating;
    }
}
