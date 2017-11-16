package com.example.sergey.courseproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sergey.courseproject.UserHelper;
import com.example.sergey.courseproject.entities.User;

/**
 * Created by sergey on 11/9/17.
 */

public class SqliteHelper extends SQLiteOpenHelper implements UserHelper {

    private static final String TAG = "SqliteHelper";
    private static final String DATABASE_NAME = "autostation.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase mDb;

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String usersTableSqlQuery = "CREATE TABLE " +
                UsersDbContract.TABLE_NAME + "( " +
                UsersDbContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UsersDbContract.COLUMN_EMAIL + " TEXT NOT NULL, " +
                UsersDbContract.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                UsersDbContract.COLUMN_ROLE + " TEXT NOT NULL, " +
                UsersDbContract.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                "); ";
        db.execSQL(usersTableSqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String usersUpdateSqlQuery = "DROP TABLE IF EXISTS " + UsersDbContract.TABLE_NAME;
        db.execSQL(usersUpdateSqlQuery);
    }

    public long addUser(User user) {
        mDb = getWritableDatabase();
        Log.d(TAG, "addUser: user's email " + user.getEmail());
        Cursor usersWithTheSameEmail = mDb.query(UsersDbContract.TABLE_NAME,
                null,
                UsersDbContract.COLUMN_EMAIL + " =" + "\"" + user.getEmail() + "\"",
                null,
                null,
                null,
                null);
        long id = -1;

        Log.d(TAG, "addUser: users with the same email " + usersWithTheSameEmail.getCount());
        if (usersWithTheSameEmail.getCount() == 0) {
            ContentValues cv = new ContentValues();
            cv.put(UsersDbContract.COLUMN_EMAIL, user.getEmail());
            cv.put(UsersDbContract.COLUMN_PASSWORD, user.getPassword());
            cv.put(UsersDbContract.COLUMN_ROLE, user.getRole());
            id = mDb.insert(UsersDbContract.TABLE_NAME, null, cv);
        }
        usersWithTheSameEmail.close();
        if (id < 0) {
            Log.d(TAG, "addUser: user wasn't successfully added!");
        }
        return id;
    }


}
