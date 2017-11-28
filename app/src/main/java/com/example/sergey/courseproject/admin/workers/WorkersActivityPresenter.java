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

    public List<Worker> getWorkers(String order) {
        return mWorkerRepository.getAllWorkers(order);
    }

    public void deleteWorker(long id) {
        if (mWorkerRepository.deleteWorker(id)) {
            mApi.showToast("Delete success");
        } else {
            mApi.showToast("Delete fail");
        }
    }

    public List<Worker> getWorkersWithRole(String role, String orderBy) {
        return mWorkerRepository.getWorkersWithRole(role, orderBy);
    }

    public List<Worker> getWorkerWithRoleAndStation(String role, String orderByFilter, String station) {
        return mWorkerRepository.getWorkersWithRoleAndStation(role, orderByFilter, station);
    }

    public List<Worker> getWorkersWithStation(String orderByFilter, String station) {
        return mWorkerRepository.getWorkersWithStation(orderByFilter, station);
    }
}
