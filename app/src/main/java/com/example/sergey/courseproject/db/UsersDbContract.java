package com.example.sergey.courseproject.db;

import android.provider.BaseColumns;

/**
 * Created by sergey on 11/9/17.
 */

public class UsersDbContract implements BaseColumns {
    private UsersDbContract() {

    }

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_TIMESTAMP = "timestamp";
}
