package com.example.sergey.courseproject.admin.people;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.sergey.courseproject.R;

/**
 * Created by sgubar on 11/20/17.
 */

public class PeopleActivity extends AppCompatActivity implements PeopleActivityApi {
    private RecyclerView mRecyclerView;
    private PeopleActivityPresenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        mRecyclerView = findViewById(R.id.people_recycler_view);
        mPresenter = new PeopleActivityPresenter(this, this);
    }
}
