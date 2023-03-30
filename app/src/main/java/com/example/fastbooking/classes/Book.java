package com.example.fastbooking.classes;

public class Book {
    private int guestsNumber, table_number;
    private String specialRequests, time, date;

    public Book() {
    }

    public Book(int table_number, int guestsNumber, String date, String time, String specialRequests) {
        this.guestsNumber = guestsNumber;
        this.table_number = table_number;
        this.specialRequests = specialRequests;
        this.time = time;
        this.date = date;
    }

    public int getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(int guestsNumber) {
        this.guestsNumber = guestsNumber;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTable_number() {
        return table_number;
    }

    public void setTable_number(int table_number) {
        this.table_number = table_number;
    }
}
