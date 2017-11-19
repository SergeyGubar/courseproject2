package com.example.sergey.courseproject.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sergey.courseproject.db.contracts.WorkerDbContract;
import com.example.sergey.courseproject.entities.Worker;
import com.example.sergey.courseproject.helpers.SQLiteHelper;

/**
 * Created by sgubar on 11/17/17.
 */

public class WorkerRepository {

    private static final String TAG = "WorkerRepository";
    private SQLiteDatabase mDb;
    private SQLiteHelper mHelper;


    public WorkerRepository(Context ctx) {
        mHelper = new SQLiteHelper(ctx);
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
            cv.put(WorkerDbContract.COLUMN_FULL_NAME,user.getFullName());
            id = mDb.insert(WorkerDbContract.TABLE_NAME, null, cv);
        }
        usersWithTheSameEmail.close();
        if (id < 0) {
            Log.d(TAG, "addWorker: user wasn't successfully added!");
        }
        return id;
    }

    public String getWorkerRole(Worker worker) {
        mDb = mHelper.getReadableDatabase();
        Cursor workers = mDb.query(
                WorkerDbContract.TABLE_NAME,
                null,
                WorkerDbContract.COLUMN_EMAIL +  " = " + "\"" + worker.getEmail() + "\"" + " AND " +
                        WorkerDbContract.COLUMN_PASSWORD + " = " + "\"" + worker.getPassword() + "\"",
                null,
                null,
                null,
                null
        );
        String role = "";
        if (workers.getCount() > 0) {
            workers.moveToFirst();
            role = workers.getString(workers.getColumnIndex(WorkerDbContract.COLUMN_ROLE));
            workers.close();
        }
        return role;

    }
}
