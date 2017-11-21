package com.example.sergey.courseproject.admin.people;

import android.content.Context;

import com.example.sergey.courseproject.entities.Worker;
import com.example.sergey.courseproject.repositories.WorkerRepository;

import java.util.List;

/**
 * Created by sgubar on 11/20/17.
 */

public class WorkerActivityPresenter {
    private Context mCtx;
    private WorkerActivityApi mApi;
    private WorkerRepository mWorkerRepository;

    public WorkerActivityPresenter(Context ctx, WorkerActivityApi api) {
        mCtx = ctx;
        mApi = api;
        mWorkerRepository = new WorkerRepository(ctx);
    }

    public List<Worker> getWorkers() {
        return mWorkerRepository.getAllWorkers();
    }

}
