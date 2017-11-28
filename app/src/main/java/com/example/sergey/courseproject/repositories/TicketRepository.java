package com.example.sergey.courseproject.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.sergey.courseproject.db.contracts.TicketDbContract;
import com.example.sergey.courseproject.entities.Ticket;
import com.example.sergey.courseproject.helpers.SQLiteHelper;

/**
 * Created by sgubar on 11/17/17.
 */

public class TicketRepository {
    private Context mCtx;
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDb;

    public TicketRepository(Context ctx) {
        mCtx = ctx;
        mHelper = new SQLiteHelper(mCtx);
    }

    public long addTicket(Ticket ticket) {
        //TODO : correct ticket add
        mDb = mHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TicketDbContract.COLUMN_JOURNEY_ID, ticket.getJourneyId());
        cv.put(TicketDbContract.COLUMN_SEAT_NUMBER, ticket.getSeatNumber());

        return mDb.insert(TicketDbContract.TABLE_NAME,
                null,
                cv);

    }


}
