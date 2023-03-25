package com.example.fastbooking;

import java.util.ArrayList;

public class Table {
    private int number, chair_number;
    private ArrayList<String> reserved_time, reserved_date;

    public Table() {
    }

    public Table( int number, int chair_number, ArrayList<String> reserved_time, ArrayList<String> reserved_date) {
        this.number = number;
        this.chair_number = chair_number;
        if (reserved_time.size() == 0){
            reserved_time.add("Null");
        }
        this.reserved_time = reserved_time;
        if (reserved_date.size() == 0){
            reserved_date.add("Null");
        }
        this.reserved_date = reserved_time;
    }

    public ArrayList<String> getReserved_date() {
        return reserved_date;
    }

    public void setReserved_date(ArrayList<String> reserved_date) {
        this.reserved_date = reserved_date;
    }

    public ArrayList<String> getReserved_time() {
        return reserved_time;
    }

    public void setReserved_time(ArrayList<String> reserved_time) {
        this.reserved_time = reserved_time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getChair_number() {
        return chair_number;
    }

    public void setChair_number(int chair_number) {
        this.chair_number = chair_number;
    }
}
