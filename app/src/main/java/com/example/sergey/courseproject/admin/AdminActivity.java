package com.example.sergey.courseproject.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.sergey.courseproject.R;
import com.example.sergey.courseproject.admin.buses.BusesActivity;
import com.example.sergey.courseproject.admin.statistics.StatisticsActivity;
import com.example.sergey.courseproject.admin.workers.cross.CrossQueryActivity;
import com.example.sergey.courseproject.admin.workers.search.WorkerSearchActivity;
import com.example.sergey.courseproject.admin.workers.WorkersActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        findViewById(R.id.people_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, WorkersActivity.class));
            }
        });

        findViewById(R.id.buses_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, BusesActivity.class));
            }
        });

        findViewById(R.id.cross_query_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(AdminActivity.this, CrossQueryActivity.class));
            }
        });

        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, WorkerSearchActivity.class));
            }
        });

        findViewById(R.id.statistics_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, StatisticsActivity.class));
            }
        });

    }
}
