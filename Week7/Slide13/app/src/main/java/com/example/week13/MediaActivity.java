package com.example.week13;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MediaActivity extends AppCompatActivity {
    Button startBtn, stopBtn;
    private BoundService boundService;
    private boolean isServiceConnected = false;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.Mybinder myBinder = (BoundService.Mybinder) iBinder;
            boundService = (BoundService) myBinder.getBoundService();
            try {
                boundService.startMusic();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isServiceConnected = false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        startBtn = findViewById(R.id.startButton);
        stopBtn = findViewById(R.id.stopButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBoundService();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopBoundService();
            }
        });
    }

    private void startBoundService(){
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    private void stopBoundService(){
        if (isServiceConnected) {
            unbindService(serviceConnection);
            isServiceConnected = false;
        }
    }
}