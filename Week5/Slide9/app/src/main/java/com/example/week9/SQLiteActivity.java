package com.example.week9;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class SQLiteActivity extends AppCompatActivity {
    private EditText etName, etAge;
    private Button btnAdd, btnShow, btnDelete;
    private ListView listView;
    private PersonDbHelper dbHelper;
    private SQLiteDatabase database;
    private ArrayAdapter<String> adapter;
    private List<String> peopleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        btnDelete = findViewById(R.id.btnDelete);
        listView = findViewById(R.id.listView);

        dbHelper = new PersonDbHelper(this);
        database = dbHelper.getWritableDatabase();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPerson();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPeople();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePerson();
            }
        });
    }

    private void addPerson() {
        String name = etName.getText().toString();
        String ageStr = etAge.getText().toString();

        if (name.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Enter name and age", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageStr);

        ContentValues values = new ContentValues();
        values.put(PersonContract.PersonEntry.COLUMN_NAME, name);
        values.put(PersonContract.PersonEntry.COLUMN_AGE, age);

        long rowId = database.insert(PersonContract.PersonEntry.TABLE_NAME, null, values);

        if (rowId != -1) {
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etAge.setText("");
        } else {
            Toast.makeText(this, "Fail to add", Toast.LENGTH_SHORT).show();
        }
    }


    private void showPeople() {
        peopleList.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM " + PersonContract.PersonEntry.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(PersonContract.PersonEntry.COLUMN_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(PersonContract.PersonEntry.COLUMN_AGE));
                peopleList.add(name + ", " + age + " tuổi");
            } while (cursor.moveToNext());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, peopleList);
        listView.setAdapter(adapter);
        cursor.close();
    }

    private void deletePerson() {
        String nameToDelete = etName.getText().toString();
        String ageToDeleteStr = etAge.getText().toString();

        if (nameToDelete.isEmpty() || ageToDeleteStr.isEmpty()) {
            Toast.makeText(this, "Enter name and age", Toast.LENGTH_SHORT).show();
            return;
        }

        int ageToDelete = Integer.parseInt(ageToDeleteStr);

        int deletedRows = database.delete(
                PersonContract.PersonEntry.TABLE_NAME,
                PersonContract.PersonEntry.COLUMN_NAME + " = ? AND " +
                        PersonContract.PersonEntry.COLUMN_AGE + " = ?",
                new String[]{nameToDelete, String.valueOf(ageToDelete)}
        );

        if (deletedRows > 0) {
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            showPeople();
        } else {
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
        }
    }
}
