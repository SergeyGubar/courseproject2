package com.example.sergey.courseproject.admin.people;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sgubar on 11/20/17.
 */

public class PeopleRecyclerAdapter extends RecyclerView.Adapter<PeopleRecyclerAdapter.PeopleViewHolder>{


    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class PeopleViewHolder extends RecyclerView.ViewHolder {

        public PeopleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
