package com.example.fastbooking.classes;

import java.util.ArrayList;

public class Table {
    private int number, chair_number;
    private ArrayList<String> reserved_time, reserved_date;
    private boolean coupe;

    public Table() {
    }

    public Table(int number, int chair_number, ArrayList<String> reserved_time, ArrayList<String> reserved_date, boolean coupe) {
        this.number = number;
        this.chair_number = chair_number;
        this.reserved_time = reserved_time;
        this.reserved_date = reserved_date;
        this.coupe = coupe;
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

    public ArrayList<String> getReserved_time() {
        return reserved_time;
    }

    public void setReserved_time(ArrayList<String> reserved_time) {
        this.reserved_time = reserved_time;
    }

    public ArrayList<String> getReserved_date() {
        return reserved_date;
    }

    public void setReserved_date(ArrayList<String> reserved_date) {
        this.reserved_date = reserved_date;
    }

    public boolean isCoupe() {
        return coupe;
    }

    public void setCoupe(boolean coupe) {
        this.coupe = coupe;
    }
}