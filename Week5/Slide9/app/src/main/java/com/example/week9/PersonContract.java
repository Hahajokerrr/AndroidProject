package com.example.week9;
import android.provider.BaseColumns;

public class PersonContract {
    private PersonContract() {}

    public static class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME = "people";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";}
}
