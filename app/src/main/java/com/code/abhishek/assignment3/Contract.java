package com.code.abhishek.assignment3;

import android.provider.BaseColumns;

/**
 * Created by ABHISHEK GUPTA on 9/28/2016.
 */
public final class Contract {
        private Contract(){}
    public static class dbentry implements BaseColumns{

        public static final String DATABASE_NAME = "db";
        public static final String TABLE_NAME = "Student";
        public static final String DATA_ROLL = "rollno";
        public static final String DATA_NAME = "name";
        public static final String DATA_SEM = "sem";
        public static final String DATA_PHONE = "phone";


    }

}
