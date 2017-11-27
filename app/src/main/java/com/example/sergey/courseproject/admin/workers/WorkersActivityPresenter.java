package com.example.sergey.courseproject.admin.workers;

import android.content.Context;

import com.example.sergey.courseproject.entities.Worker;
import com.example.sergey.courseproject.repositories.WorkerRepository;

import java.util.List;

/**
 * Created by sgubar on 11/20/17.
 */

public class WorkersActivityPresenter {
    private Context mCtx;
    private WorkersActivityApi mApi;
    private WorkerRepository mWorkerRepository;

    public WorkersActivityPresenter(Context ctx, WorkersActivityApi api) {
        mCtx = ctx;
        mWorkerRepository = new WorkerRepository(ctx);
        mApi = api;
    }

    public List<Worker> getWorkers() {
        return mWorkerRepository.getAllWorkers();
    }

    public void deleteWorker(long id) {
        if (mWorkerRepository.deleteWorker(id)) {
            mApi.showToast("Delete success");
        } else {
            mApi.showToast("Delete fail");
        }
    }

}
