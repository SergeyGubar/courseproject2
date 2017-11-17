package com.example.sergey.courseproject.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sergey.courseproject.UserHelper;

import com.example.sergey.courseproject.db.contracts.WorkerDbContract;
import com.example.sergey.courseproject.entities.Worker;

/**
 * Created by sergey on 11/9/17.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    private static final String TAG = "SqliteHelper";
    private static final String DATABASE_NAME = "autostation.db";
    private static final int DATABASE_VERSION = 1;

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initializeWorkersTable(db);
    }


    public void initializeWorkersTable(SQLiteDatabase db) {
        final String usersTableSqlQuery = "CREATE TABLE " +
                WorkerDbContract.TABLE_NAME + "( " +
                WorkerDbContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WorkerDbContract.COLUMN_EMAIL + " TEXT NOT NULL, " +
                WorkerDbContract.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                WorkerDbContract.COLUMN_ROLE + " TEXT NOT NULL, " +
                WorkerDbContract.COLUMN_FULL_NAME + " TEXT NOT NULL, " +
                WorkerDbContract.COLUMN_EXPIRIENCE + " INTEGER, " +
                WorkerDbContract.COLUMN_PERSONAL_DATA + " INTEGER, " +
                WorkerDbContract.COLUMN_STATION_ID + " INTEGER NOT NULL, " +
                WorkerDbContract.COLUMN_TELEPHONE + " INTEGER, " +
                WorkerDbContract.COLUMN_SALLARY + " INTEGER, " +
                WorkerDbContract.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                "); ";
        db.execSQL(usersTableSqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String usersUpdateSqlQuery = "DROP TABLE IF EXISTS " + WorkerDbContract.TABLE_NAME;
        db.execSQL(usersUpdateSqlQuery);
    }


}
