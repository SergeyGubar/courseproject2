package com.example.sergey.courseproject.admin.workers.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergey.courseproject.R;
import com.example.sergey.courseproject.entities.Worker;

import java.util.List;

/**
 * Created by sgubar on 11/29/17.
 */

public class SpecificWorkersRecyclerAdapter extends RecyclerView.Adapter<SpecificWorkersRecyclerAdapter.SpecificWorkerViewHolder> {
    private Context mCtx;
    private List<Worker> mData;
    private static final int WORKER_NOT_HIGHLIGHTED = 0;
    private static final int WORKER_HIGHLIGHTED = 1;
    private LayoutInflater mInflater;

    public SpecificWorkersRecyclerAdapter(Context ctx, List<Worker> data) {
        mCtx = ctx;
        mInflater = LayoutInflater.from(mCtx);
        mData = data;
    }

    @Override
    public SpecificWorkerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == WORKER_HIGHLIGHTED) {
            v = mInflater.inflate(R.layout.worker_item_highlighted, parent, false);
        } else {
            v = mInflater.inflate(R.layout.worker_item, parent, false);
        }
        return new SpecificWorkerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SpecificWorkerViewHolder holder, int position) {
        holder.bind(position);
    }

    public void swapData(List<Worker> newData) {
        mData = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).isHighlighted()) {
            return WORKER_HIGHLIGHTED;
        } else {
            return WORKER_NOT_HIGHLIGHTED;
        }
    }

    class SpecificWorkerViewHolder extends RecyclerView.ViewHolder {
        private TextView mStationIdTextView;
        private TextView mFullNameTextView;
        private TextView mWorkerIdTextView;
        private TextView mWorkerRoleTextView;

        public SpecificWorkerViewHolder(View itemView) {
            super(itemView);
            mStationIdTextView = itemView.findViewById(R.id.worker_station_text_view);
            mFullNameTextView = itemView.findViewById(R.id.worker_full_name_text_view);
            mWorkerIdTextView = itemView.findViewById(R.id.worker_id_text_view);
            mWorkerRoleTextView = itemView.findViewById(R.id.worker_role_text_view);
        }

        public void bind(int position) {
            Worker worker = mData.get(position);
            mStationIdTextView.setText(String.valueOf(worker.getStationId()));
            mFullNameTextView.setText(String.valueOf(worker.getFullName()));
            mWorkerIdTextView.setText(String.valueOf(worker.getId()));
            mWorkerRoleTextView.setText(String.valueOf(worker.getRole()));
        }
    }
}
