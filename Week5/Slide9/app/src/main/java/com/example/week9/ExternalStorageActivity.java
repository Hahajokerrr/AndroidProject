package com.example.week9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalStorageActivity extends AppCompatActivity {
    private static final String FILE_NAME = "externalText.txt";
    EditText mEditText;
    Button saveBtn;
    Button loadBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        mEditText = findViewById(R.id.textInputEditText);
        isExternalStorageWritable();
        isExternalStorageReadable();
        saveBtn = (Button) findViewById(R.id.saveButton);
        loadBtn = (Button) findViewById(R.id.loadButton);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile(view);
            }
        });
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private boolean isExternalStorageWritable(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            Log.i("State", "Writable!!");
            return true;
        } else {
            Log.i("State", "Not Writable!!");
            return false;
        }
    }
    private boolean isExternalStorageReadable(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())){
            Log.i("State", "Readable!!");
            return true;
        } else {
            return false;
        }
    }

    public void writeFile(View v){
        if (isExternalStorageWritable()){
            File textFile = new File(Environment.getExternalStorageDirectory(), FILE_NAME.toString());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(textFile);
                fos.write(mEditText.getText().toString().getBytes());
                mEditText.getText().clear();
                Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            Toast.makeText(this, "Cannot write", Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}