package com.example.sergey.courseproject.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sergey.courseproject.db.contracts.WorkerDbContract;
import com.example.sergey.courseproject.entities.Worker;
import com.example.sergey.courseproject.helpers.SqliteHelper;

/**
 * Created by sgubar on 11/17/17.
 */

public class WorkerRepository {

    private static final String TAG = "WorkerRepository";
    private SQLiteDatabase mDb;
    private SqliteHelper mHelper;


    public WorkerRepository(Context ctx) {
        mHelper = new SqliteHelper(ctx);
    }

    public long addWorker(Worker user) {
        mDb = mHelper.getWritableDatabase();
        Log.d(TAG, "addWorker: user's email " + user.getEmail());
        Cursor usersWithTheSameEmail = mDb.query(WorkerDbContract.TABLE_NAME,
                null,
                WorkerDbContract.COLUMN_EMAIL + " =" + "\"" + user.getEmail() + "\"",
                null,
                null,
                null,
                null);
        long id = -1;

        Log.d(TAG, "addWorker: users with the same email " + usersWithTheSameEmail.getCount());
        if (usersWithTheSameEmail.getCount() == 0) {
            ContentValues cv = new ContentValues();
            cv.put(WorkerDbContract.COLUMN_EMAIL, user.getEmail());
            cv.put(WorkerDbContract.COLUMN_PASSWORD, user.getPassword());
            cv.put(WorkerDbContract.COLUMN_ROLE, user.getRole());
            id = mDb.insert(WorkerDbContract.TABLE_NAME, null, cv);
        }
        usersWithTheSameEmail.close();
        if (id < 0) {
            Log.d(TAG, "addWorker: user wasn't successfully added!");
        }
        return id;
    }
}
