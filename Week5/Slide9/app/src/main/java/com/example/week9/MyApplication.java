package com.example.week9;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataSPManager.init(getApplicationContext());
    }
}
