package com.example.week9;

import android.content.Context;

public class DataSPManager {
    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
    private static DataSPManager instance;
    private MySharedPreferences mySharedPreferences;
    public static void init(Context context){
        instance = new DataSPManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataSPManager getInstance() {
        if (instance == null){
            instance = new DataSPManager();
        }
        return instance;
    }

    public static void setFirstInstalled(boolean isFirst){
        DataSPManager.getInstance().mySharedPreferences.putBoolVal(PREF_FIRST_INSTALL, isFirst);
    }

    public static boolean getFirstInstalled(){
        return DataSPManager.getInstance().mySharedPreferences.getBoolVal(PREF_FIRST_INSTALL);
    }
}
