package com.example.sergey.courseproject.admin.workers.cross;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergey.courseproject.R;
import com.example.sergey.courseproject.entities.Bus;
import com.example.sergey.courseproject.entities.Worker;
import com.example.sergey.courseproject.repositories.BusesRepository;
import com.example.sergey.courseproject.repositories.WorkerRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CrossQueryActivity extends AppCompatActivity {

    private WorkerRepository mWorkerRepository;
    private BusesRepository mBusesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_query);

        mWorkerRepository = new WorkerRepository(this);
        mBusesRepository = new BusesRepository(this);


        File sd = Environment.getExternalStorageDirectory();
        String csvFile = "crossQueryReport.xls";
        File directory = new File(sd.getAbsolutePath());

        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        List<String> allBusesBrands = mBusesRepository.getAllBusesBrands();

        try {
            File file = new File(directory, csvFile);
            WorkbookSettings settings = new WorkbookSettings();
            settings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook = Workbook.createWorkbook(file, settings);
            WritableSheet sheet = workbook.createSheet("crossquery", 0);
            List<Worker> drivers = mWorkerRepository.getWorkersWithRole("driver", null);

            for (int i = 0; i < allBusesBrands.size(); i++) {
                sheet.addCell(new Label(i + 1, 0, allBusesBrands.get(i)));
            }

            int y = 1;
            for (Worker driver : drivers) {

                sheet.addCell(new Label(0, y, String.valueOf(driver.getFullName())));
                List<Bus> thisDriverBuses = mBusesRepository.getBusesForSpecificDriver(driver.getId());

                HashMap<String, Integer> frequencyDictionary = new HashMap<>();

                for (Bus bus : thisDriverBuses) {
                    if (frequencyDictionary.containsKey(bus.getBrand())) {
                        frequencyDictionary.put(bus.getBrand(), frequencyDictionary.get(bus.getBrand()) + 1);
                    } else {
                        frequencyDictionary.put(bus.getBrand(), 1);
                    }
                }

                for (int x = 0; x < allBusesBrands.size(); x++) {
                    int quantity = 0;
                    if (frequencyDictionary.containsKey(allBusesBrands.get(x))) {
                        quantity = frequencyDictionary.get(allBusesBrands.get(x));
                    }
                    sheet.addCell(new Label(x + 1, y, String.valueOf(quantity)));
                }
                y++;

            }
            Toast.makeText(this, "Query formed!", Toast.LENGTH_SHORT).show();
            workbook.write();
            workbook.close();

        } catch (Exception ex) {
            //Ignore
        }


    }
}
