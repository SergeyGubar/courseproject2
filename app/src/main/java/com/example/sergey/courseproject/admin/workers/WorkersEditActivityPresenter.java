package com.example.sergey.courseproject.admin.workers;

import android.content.Context;

import com.example.sergey.courseproject.entities.Worker;
import com.example.sergey.courseproject.repositories.WorkerRepository;

/**
 * Created by sgubar on 11/23/17.
 */

public class WorkersEditActivityPresenter {
    private Context mCtx;
    private WorkersActivityApi mApi;
    private WorkerRepository mWorkerRepository;

    public WorkersEditActivityPresenter(Context ctx, WorkersActivityApi api) {
        mCtx = ctx;
        mApi = api;
        mWorkerRepository = new WorkerRepository(mCtx);
    }

    public void editWorker(Worker worker) {
        boolean wasUpdated = mWorkerRepository.editWorker(worker);
        if(!wasUpdated) {
            mApi.showToast("Fail");
        } else {
            mApi.showToast("Success");
        }
    }
}
