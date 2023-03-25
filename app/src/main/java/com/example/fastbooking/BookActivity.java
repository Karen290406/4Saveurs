package com.example.fastbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BookActivity extends AppCompatActivity {
    private Spinner spinner_time, spinner_date;
    private Button btn;
    private String specialRequests, time, date;
    private int guestsNumber;
    private CheckBox checkBox;
    private ArrayList<Table> tables = new ArrayList<>();
    private Book book = null;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        user = LogActivity.getUser();

        btn = findViewById(R.id.btn_submit);

        // получаем сегодняшнюю дату
        Date today = new Date();

        // получаем завтрашнюю дату
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        // получаем послезавтрашнюю дату
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date dayAfterTomorrow = calendar.getTime();

        // форматируем даты в нужный формат
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String tomorrowString = dateFormat.format(tomorrow);
        String dayAfterTomorrowString = dateFormat.format(dayAfterTomorrow);

        // создаем массив с датами
        String[] dates = new String[]{tomorrowString, dayAfterTomorrowString};

        spinner_date = findViewById(R.id.spinner_date);
        ArrayAdapter<String> adapter_date = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dates);
        adapter_date.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_date.setAdapter(adapter_date);

        // создаем массив с временем
        String[] times = {"13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"};

        spinner_time = findViewById(R.id.spinner_time);
        ArrayAdapter<String> adapter_time = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, times);
        adapter_time.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_time.setAdapter(adapter_time);

        checkBox = findViewById(R.id.checkBox);

        // удаляем предыдущие записи таблиц из списка
        tables.clear();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tablesRef = database.getReference("Tables");

        tablesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Table> tablesList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Table table = childSnapshot.getValue(Table.class);
                    tablesList.add(table);
                }
                tables.addAll(tablesList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Обработка ошибок при чтении данных из Firebase
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guestsNumber = Integer.parseInt(((EditText) findViewById(R.id.guests_number)).getText().toString());
                specialRequests = ((EditText) findViewById(R.id.special_requests)).getText().toString();
                time = spinner_time.getSelectedItem().toString();
                date = spinner_date.getSelectedItem().toString();


                if (checkBox.isChecked()) {
                    if (guestsNumber > tables.get(3).getChair_number()) {
                        Toast.makeText(BookActivity.this, "We have no coupes with this number of chairs", Toast.LENGTH_LONG).show();
                    } else{
                        for (int i = 0; i < 4; i++) {
                            Table table = tables.get(i);
                            if (table.getChair_number() >= guestsNumber) {
                                ArrayList<String> table_date = table.getReserved_date();
                                ArrayList<String> table_time = table.getReserved_time();

                                if (!table_date.contains(date)) {
                                    book = new Book(table.getNumber(), guestsNumber, date, time, specialRequests);

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("Book");

                                    myRef.child(user.getUsername()).setValue(book, new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                            if (error != null) {
                                                Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(BookActivity.this, Integer.toString(table.getNumber()), Toast.LENGTH_SHORT).show();
                                                table_date.add(date);
                                                table_time.add(time);
                                                table.setReserved_date(table_date);
                                                table.setReserved_time(table_time);

                                                FirebaseDatabase table_database = FirebaseDatabase.getInstance();
                                                DatabaseReference table_myRef = table_database.getReference("Tables");

                                                table_myRef.child(Integer.toString(table.getNumber())).setValue(table, new DatabaseReference.CompletionListener() {
                                                    @Override
                                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                                                    }
                                                });

                                                startActivity(new Intent(BookActivity.this, MainActivity.class));
                                            }
                                        }
                                    });
                                } else if (checkDate(table_date, table_time, date, time)) {
                                    book = new Book(table.getNumber(), guestsNumber, date, time, specialRequests);

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("Book");

                                    myRef.child(user.getUsername()).setValue(book, new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                            if (error != null) {
                                                Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(BookActivity.this, Integer.toString(table.getNumber()), Toast.LENGTH_SHORT).show();
                                                table_date.add(date);
                                                table_time.add(time);
                                                table.setReserved_date(table_date);
                                                table.setReserved_time(table_time);

                                                FirebaseDatabase table_database = FirebaseDatabase.getInstance();
                                                DatabaseReference table_myRef = table_database.getReference("Tables");

                                                table_myRef.child(Integer.toString(table.getNumber())).setValue(table, new DatabaseReference.CompletionListener() {
                                                    @Override
                                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                                                    }
                                                });

                                                startActivity(new Intent(BookActivity.this, MainActivity.class));
                                            }
                                        }
                                    });
                                }
                            }
                            if (book != null) {
                                break;
                            }
                        }
                }
                }else if(guestsNumber > tables.get(tables.size()-1).getChair_number()){
                    Toast.makeText(BookActivity.this, "We have no tables with this number of chairs please select coupe", Toast.LENGTH_LONG).show();
                }
                else {
                    for (int i = 4; i < tables.size(); i++) {
                        Table table = tables.get(i);
                        if (table.getChair_number() >= guestsNumber) {
                            ArrayList<String> table_date = table.getReserved_date();
                            ArrayList<String> table_time = table.getReserved_time();

                            if (!table_date.contains(date)) {
                                book = new Book(table.getNumber(), guestsNumber, date, time, specialRequests);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("Book");

                                myRef.child(user.getUsername()).setValue(book, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if (error != null) {
                                            Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(BookActivity.this, Integer.toString(table.getNumber()), Toast.LENGTH_SHORT).show();
                                            table_date.add(date);
                                            table_time.add(time);
                                            table.setReserved_date(table_date);
                                            table.setReserved_time(table_time);

                                            FirebaseDatabase table_database = FirebaseDatabase.getInstance();
                                            DatabaseReference table_myRef = table_database.getReference("Tables");

                                            table_myRef.child(Integer.toString(table.getNumber())).setValue(table, new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                                                }
                                            });

                                            startActivity(new Intent(BookActivity.this, MainActivity.class));
                                        }
                                    }
                                });
                            } else if(checkDate(table_date, table_time, date, time)){
                                book = new Book(table.getNumber(), guestsNumber, date, time, specialRequests);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("Book");

                                myRef.child(user.getUsername()).setValue(book, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if (error != null) {
                                            Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(BookActivity.this, Integer.toString(table.getNumber()), Toast.LENGTH_SHORT).show();
                                            table_date.add(date);
                                            table_time.add(time);
                                            table.setReserved_date(table_date);
                                            table.setReserved_time(table_time);

                                            FirebaseDatabase table_database = FirebaseDatabase.getInstance();
                                            DatabaseReference table_myRef = table_database.getReference("Tables");

                                            table_myRef.child(Integer.toString(table.getNumber())).setValue(table, new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                                                }
                                            });

                                            startActivity(new Intent(BookActivity.this, MainActivity.class));
                                        }
                                    }
                                });
                            }
                        }
                        if(book != null){
                            break;
                        }
                    }
                }
                if(book == null){
                    Toast.makeText(BookActivity.this, "We have no free tables at this time please select another time", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static boolean isTimeDifferenceGreaterThan3Hours(String time1, String time2) {
        try {
            // Разбиваем строки time1 и time2 на часы и минуты
            String[] time1Parts = time1.split(":");
            String[] time2Parts = time2.split(":");

            // Преобразуем значения часов и минут в минуты
            int time1Minutes = Integer.parseInt(time1Parts[0]) * 60 + Integer.parseInt(time1Parts[1]);
            int time2Minutes = Integer.parseInt(time2Parts[0]) * 60 + Integer.parseInt(time2Parts[1]);

            // Вычисляем разницу между временами в минутах
            int timeDifference = Math.abs(time1Minutes - time2Minutes);

            // Проверяем, является ли разница больше 180 минут (т.е. 3 часов)
            if (timeDifference >= 180) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean checkDate(ArrayList<String> table_date, ArrayList<String> table_time, String date, String time){
        boolean t = true;
        for(int i = 0; i < table_date.size(); i++){
            if(table_date.get(i) == date && !isTimeDifferenceGreaterThan3Hours(table_time.get(i), time)){
                t = false;
            }
        }
        return t;
    }
}