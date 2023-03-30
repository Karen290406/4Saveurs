package com.example.fastbooking.admin;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fastbooking.R;
import com.example.fastbooking.classes.Table;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminTablesActivity extends AppCompatActivity {
    private ArrayList<Table> tables = new ArrayList<>();
    private Button btn_delete, btn_edit;
    private String number_table, number_chair;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tables);

        btn_delete = findViewById(R.id.btn_delete);
        btn_edit = findViewById(R.id.btn_edit);

        checkBox = findViewById(R.id.checkBox);


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((EditText) findViewById(R.id.tables_number)).getText().toString().length() ==  0) {
                    Toast.makeText(AdminTablesActivity.this, "Please enter table number", Toast.LENGTH_SHORT).show();
                } else {
                    tables.clear();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference tablesRef = database.getReference("Tables");

                    tablesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ArrayList<Table> tablesList = new ArrayList<>();
                            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                Table table = childSnapshot.getValue(Table.class);
                                tablesList.add(table);
                            }
                            tables.addAll(tablesList);

                            number_table = ((EditText) findViewById(R.id.tables_number)).getText().toString();

                            if (tables.get(Integer.parseInt(number_table) - 1).getReserved_date().size() == 1 && tables.get(Integer.parseInt(number_table) - 1).getReserved_time().size() == 1) {
                                tables.remove(Integer.parseInt(number_table) - 1);
                            } else {
                                Toast.makeText(AdminTablesActivity.this, "We have order on this table. Please select another table", Toast.LENGTH_SHORT).show();
                            }

                            for (int i = 0; i < tables.size(); i++) {
                                tables.get(i).setNumber(i + 1);
                            }
                            upload(tables, AdminTablesActivity.this);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tables.clear();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference tablesRef = database.getReference("Tables");

                tablesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<Table> tablesList = new ArrayList<>();
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            Table table = childSnapshot.getValue(Table.class);
                            tablesList.add(table);
                        }
                        tables.addAll(tablesList);

                        number_table = ((EditText) findViewById(R.id.tables_number)).getText().toString();
                        number_chair = ((EditText) findViewById(R.id.number_of_chairs)).getText().toString();

                        if (number_table.length() == 0 && number_chair.length() != 0) {
                            ArrayList<String> date = new ArrayList<>();
                            ArrayList<String> time = new ArrayList<>();
                            date.add("Null");
                            time.add("Null");
                            tables.add(new Table(tables.size() + 1, Integer.parseInt(number_chair), time, date, checkBox.isChecked()));
                            upload(tables, AdminTablesActivity.this);
                        } else if (number_table.length() != 0 && number_chair.length() != 0) {
                            tables.get(Integer.parseInt(number_table) - 1).setChair_number(Integer.parseInt(number_chair));
                            tables.get(Integer.parseInt(number_table) - 1).setCoupe(checkBox.isChecked());
                            upload(tables, AdminTablesActivity.this);
                        } else {
                            Toast.makeText(AdminTablesActivity.this, "Please enter the number of chairs", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    private static void upload(ArrayList<Table> tables, Context context) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Tables");

        myRef.setValue(null); // удалить все таблицы

        for (Table table : tables) {
            myRef.child(Integer.toString(table.getNumber())).setValue(table);
        }
        Toast.makeText(context, "Данные успешно загружены", Toast.LENGTH_SHORT).show();
    }

//    private static void deleteAllItemsInFolder(String folderName) {
//        DatabaseReference folderRef = FirebaseDatabase.getInstance().getReference().child(folderName);
//
//        folderRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
//                    itemSnapshot.getRef().removeValue();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }
}