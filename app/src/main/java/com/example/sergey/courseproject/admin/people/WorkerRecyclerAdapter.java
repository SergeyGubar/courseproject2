package com.example.sergey.courseproject.admin.people;

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
 * Created by sgubar on 11/20/17.
 */

public class WorkerRecyclerAdapter extends RecyclerView.Adapter<WorkerRecyclerAdapter.WorkerViewHolder> {


    private Context mCtx;
    private List<Worker> mData;

    public WorkerRecyclerAdapter(Context ctx, List<Worker> data) {
        mCtx = ctx;
        mData = data;
    }

    @Override
    public WorkerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View v = inflater.inflate(R.layout.worker_item, parent, false);
        return new WorkerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WorkerViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class WorkerViewHolder extends RecyclerView.ViewHolder {

        private TextView mStationIdTextView;
        private TextView mFullNameTextView;
        private TextView mWorkerIdTextView;

        public WorkerViewHolder(View itemView) {
            super(itemView);
            mStationIdTextView = itemView.findViewById(R.id.worker_station_text_view);
            mFullNameTextView = itemView.findViewById(R.id.worker_full_name_text_view);
            mWorkerIdTextView = itemView.findViewById(R.id.worker_id_text_view);
        }

        public void bind(Worker worker) {
            mStationIdTextView.setText(String.valueOf(worker.getStationId()));
            mFullNameTextView.setText(String.valueOf(worker.getFullName()));
            mWorkerIdTextView.setText(String.valueOf(worker.getId()));
        }

    }
}
