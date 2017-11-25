package com.example.sergey.courseproject.admin.people;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sergey.courseproject.R;

/**
 * Created by sgubar on 11/20/17.
 */

public class WorkerActivity extends AppCompatActivity implements WorkerActivityApi {
    private RecyclerView mRecyclerView;
    private WorkerActivityPresenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_recycler);
        mPresenter = new WorkerActivityPresenter(this, this);
        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        WorkerRecyclerAdapter adapter = new WorkerRecyclerAdapter(this, mPresenter.getWorkers());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

    }


    @Override
    public RecyclerView getPeopleRecyclerView() {
        return mRecyclerView;
    }
}
