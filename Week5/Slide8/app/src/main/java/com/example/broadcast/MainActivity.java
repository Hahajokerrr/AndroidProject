package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("com.example.ACTION_MY_EVENT");
        intent.putExtra("message", "Hi everyone");

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(view -> {
            sendBroadcast(intent);
        });

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        MyBroadcastReceiver receiver = new MyBroadcastReceiver();

        IntentFilter intentFilter = new IntentFilter("com.example.ACTION_MY_EVENT");
        intentFilter.addAction(getPackageName() + "com.example.ACTION_MY_EVENT");
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }
}