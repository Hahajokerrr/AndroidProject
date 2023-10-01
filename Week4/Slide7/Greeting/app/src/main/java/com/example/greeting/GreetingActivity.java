package com.example.greeting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

public class GreetingActivity extends AppCompatActivity {

    private String fullName;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        Intent intent = this.getIntent();
        this.fullName = intent.getStringExtra("fullname");
        this.message = intent.getStringExtra("message");
        TextView textView = findViewById(R.id.textViewMessage);
        textView.setText(this.message);
    }

    public void goBack() {
        this.onBackPressed();
    }

    public void finish() {
        Intent data = new Intent();
        String feedback = "OK, hello " + this.fullName + ". How are you?";
        data.putExtra("feedback", feedback);

        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}