package com.example.sergey.courseproject.admin.workers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.sergey.courseproject.R;

/**
 * Created by sgubar on 11/20/17.
 */

public class WorkersActivity extends AppCompatActivity implements DeleteCallback, WorkersActivityApi {
    private RecyclerView mRecyclerView;
    private WorkersActivityPresenter mPresenter;
    private static final String TAG = "WorkersActivity";
    private WorkersRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_recycler);
        mPresenter = new WorkersActivityPresenter(this, this);
        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mAdapter = new WorkersRecyclerAdapter(this, mPresenter.getWorkers(), this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.swapData(mPresenter.getWorkers());
    }


    @Override
    public void deleteWorker(long id) {
        mPresenter.deleteWorker(id);
        mAdapter.swapData(mPresenter.getWorkers());
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
