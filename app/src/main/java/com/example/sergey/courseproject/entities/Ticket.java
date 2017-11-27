package com.example.sergey.courseproject.entities;

/**
 * Created by sgubar on 11/17/17.
 */

public class Ticket {
    private int mId;
    private int mJourneyId;
    private int mSeat;

    public Ticket(int id, int journeyId, int seat) {
        mId = id;
        mJourneyId = journeyId;
        mSeat = seat;
    }

    public int getId() {
        return mId;
    }

    public int getJourneyId() {
        return mJourneyId;
    }

    public int getSeat() {
        return mSeat;
    }
}
