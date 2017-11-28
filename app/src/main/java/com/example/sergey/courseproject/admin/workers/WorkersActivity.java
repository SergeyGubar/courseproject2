package com.example.sergey.courseproject.admin.workers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sergey.courseproject.R;
import com.example.sergey.courseproject.db.contracts.WorkerDbContract;

import java.util.ArrayList;

/**
 * Created by sgubar on 11/20/17.
 */

public class WorkersActivity extends AppCompatActivity implements DeleteCallback, WorkersActivityApi {
    private RecyclerView mRecyclerView;
    private WorkersActivityPresenter mPresenter;
    private static final String TAG = "WorkersActivity";
    private WorkersRecyclerAdapter mAdapter;
    private EditText mStationFilterEditText;
    private Button mFilterButton;
    private Spinner mRoleFilterSpinner;
    private Spinner mOrderSpinner;
    private Button mRemoveFiltersButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_recycler);
        mPresenter = new WorkersActivityPresenter(this, this);
        mRecyclerView = findViewById(R.id.recycler_view);
        mStationFilterEditText = findViewById(R.id.station_filter_edit_text);
        mFilterButton = findViewById(R.id.filter_button);
        mRoleFilterSpinner = findViewById(R.id.role_spinner);
        mOrderSpinner = findViewById(R.id.order_by_spinner);
        mRemoveFiltersButton = findViewById(R.id.clear_button);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles_items_with_all,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<CharSequence> list = new ArrayList<>();
        list.add("ID");
        list.add("Station id");
        list.add("Role");
        list.add("Name");

        ArrayAdapter<CharSequence> orderAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, list);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mOrderSpinner.setAdapter(orderAdapter);
        mRoleFilterSpinner.setAdapter(adapter);

        mFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    filter(mRoleFilterSpinner.getSelectedItem().toString().toLowerCase(),
                            mOrderSpinner.getSelectedItem().toString(),
                            mStationFilterEditText.getText().toString());
            }
        });

        mRemoveFiltersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initWorkers();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mAdapter = new WorkersRecyclerAdapter(this, mPresenter.getWorkers(null), this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initWorkers();
    }

    public void initWorkers() {
        mAdapter.swapData(mPresenter.getWorkers(null));
    }

    public void filter(String role, String orderBy, String station) {
        String orderByFilter;

        switch (orderBy.toLowerCase()) {
            case "name":
                orderByFilter = WorkerDbContract.COLUMN_FULL_NAME;
                break;
            case "station id":
                orderByFilter = WorkerDbContract.COLUMN_STATION_ID;
                break;
            case "role":
                orderByFilter = WorkerDbContract.COLUMN_ROLE;
                break;
            default:
                orderByFilter = WorkerDbContract._ID;
                break;
        }
        if (!role.toLowerCase().equals("all")) {
            if (station == null || station.equals("")) {
                mAdapter.swapData(mPresenter.getWorkersWithRole(role, orderByFilter));
            } else {
                mAdapter.swapData(mPresenter.getWorkerWithRoleAndStation(role, orderByFilter, station));
            }
        } else {
            if (station == null || station.equals("")) {
                mAdapter.swapData(mPresenter.getWorkers(orderByFilter));
            } else {
                mAdapter.swapData(mPresenter.getWorkersWithStation(orderByFilter, station));
            }
        }
    }

    @Override
    public void deleteWorker(long id) {
        mPresenter.deleteWorker(id);
        mAdapter.swapData(mPresenter.getWorkers(null));
    }



    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
