package com.example.week9;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    public static final String SHARED_PREFERENCES = "MY_SHARED_PREFERENCES";
    private Context context;

    public MySharedPreferences(Context context) {
        this.context = context;
    }

    public void putBoolVal(String key, boolean val){
        SharedPreferences sharedPreferences = (SharedPreferences) context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, val);
        editor.apply();
    }

    public boolean getBoolVal(String key){
        SharedPreferences sharedPreferences = (SharedPreferences) context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }
}
