package com.example.week9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button inStoBtn;
    Button exStoBtn;
    Button sqliteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!DataSPManager.getFirstInstalled()){
            Toast.makeText(this, "First installed", Toast.LENGTH_LONG).show();
            DataSPManager.setFirstInstalled(true);
        }
        inStoBtn = (Button) findViewById(R.id.inStoButton);
        exStoBtn = (Button) findViewById(R.id.exStoButton);
        sqliteBtn = (Button) findViewById(R.id.SQLitebutton);
        inStoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InternalStorageActivity.class);
                startActivity(intent);
            }
        });
        exStoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExternalStorageActivity.class);
                startActivity(intent);
            }
        });
        sqliteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SQLiteActivity.class);
                startActivity(intent);
            }
        });
    }
}