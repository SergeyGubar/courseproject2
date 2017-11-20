package com.example.sergey.courseproject.admin.people;

import android.content.Context;

/**
 * Created by sgubar on 11/20/17.
 */

public class PeopleActivityPresenter {
    private Context mCtx;
    private PeopleActivityApi mApi;

    public PeopleActivityPresenter(Context ctx, PeopleActivityApi api) {
        mCtx = ctx;
        mApi = api;
    }
}
