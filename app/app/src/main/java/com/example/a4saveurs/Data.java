package com.example.a4saveurs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class Data {
    public static long getLineCountByReader(String fileName) throws IOException {
        try (LineNumberReader lnr = new LineNumberReader(new BufferedReader(new FileReader(fileName)))) {
            while (lnr.readLine() != null) ;
            return lnr.getLineNumber();
        }
    }

    public static int getLineLength(String filePath) throws IOException {
        Scanner scanFile = new Scanner(new File(filePath));
        int maxLength = 0;
        for (int i = 0; i < getLineCountByReader(filePath); i++){
            if (maxLength < scanFile.next().split(" ").length){
                maxLength = i;
            }
        }
        return maxLength;
    }

    public static String[][] createData(String filePath) throws IOException {
        Scanner scanFile = new Scanner(new File(filePath));
        String[][] data = new String[
                (int) getLineCountByReader(filePath)
                ][
                getLineLength(filePath)];
        for (int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++){
                data[i][j] = scanFile.next();
            }
        }
        return data;
    }

    public static String[][] getData(String filePath){
        String[][] data;
        int linesCount = 0;
        int lineLength = 0;
        try {
            lineLength = Data.getLineLength(
                    filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            linesCount = (int) Data.getLineCountByReader(
                    filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = new String[(int) linesCount][lineLength];
        try {
            data = Data.createData(
                    filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
