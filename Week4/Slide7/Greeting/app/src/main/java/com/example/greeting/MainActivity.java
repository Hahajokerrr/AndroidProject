package com.example.greeting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int MY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonSendMessage = findViewById(R.id.button_sendMessage);
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    public void sendMessage() {
        EditText editTextName = findViewById(R.id.editTextName);
        String fullName = editTextName.getText().toString();
        String message = "Hello, Please say hello to me!";

        Intent intent = new Intent(this, GreetingActivity.class);
        intent.putExtra("fullname", fullName);
        intent.putExtra("message", message);

        this.startActivityForResult(intent, MY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView textFeedback = findViewById(R.id.feedback);
        if(resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            String feedback = data.getStringExtra("feedback");
            textFeedback.setText(feedback);
        } else {
            textFeedback.setText("!?");
        }
    }

}