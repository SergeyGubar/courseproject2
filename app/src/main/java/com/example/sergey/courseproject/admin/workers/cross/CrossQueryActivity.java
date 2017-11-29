package com.example.sergey.courseproject.admin.workers.cross;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergey.courseproject.R;
import com.example.sergey.courseproject.entities.Bus;
import com.example.sergey.courseproject.entities.Worker;
import com.example.sergey.courseproject.repositories.BusesRepository;
import com.example.sergey.courseproject.repositories.WorkerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class CrossQueryActivity extends AppCompatActivity {

    private WorkerRepository mWorkerRepository;
    private BusesRepository mBusesRepository;
    private TextView mResultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_query);
        mWorkerRepository = new WorkerRepository(this);
        mBusesRepository = new BusesRepository(this);
        mResultTextView = findViewById(R.id.result_text_view);
        List<Worker> drivers = mWorkerRepository.getWorkersWithRole("driver", null);


        for (Worker driver : drivers) {
            StringBuilder result = new StringBuilder("Driver " + String.valueOf(driver.getId()) + " " + String.valueOf(driver.getFullName()) + " drives: \n");
            List<Bus> thisDriverBuses = mBusesRepository.getBusesForSpecificDriver(driver.getId());

            HashMap<String, Integer> frequencyDictionary = new HashMap<>();

            for (Bus bus : thisDriverBuses) {
                if (frequencyDictionary.containsKey(bus.getBrand())) {
                    frequencyDictionary.put(bus.getBrand(), frequencyDictionary.get(bus.getBrand()) + 1);
                } else {
                    frequencyDictionary.put(bus.getBrand(), 1);
                }
            }

            for (Map.Entry<String, Integer> entry : frequencyDictionary.entrySet()) {
                result.append(entry.getKey() + " : " + entry.getValue() + "\n");
            }
            mResultTextView.append(result.toString());
        }
    }
}
