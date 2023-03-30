package com.example.fastbooking.classes;

import java.util.Comparator;

public class TableComparator implements Comparator<Table> {
    @Override
    public int compare(Table table1, Table table2) {
        return Integer.compare(table1.getChair_number(), table2.getChair_number());
    }
}