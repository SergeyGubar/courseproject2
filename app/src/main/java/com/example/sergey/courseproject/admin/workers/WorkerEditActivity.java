package com.example.sergey.courseproject.admin.workers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sergey.courseproject.R;
import com.example.sergey.courseproject.entities.Worker;

public class WorkerEditActivity extends AppCompatActivity implements WorkersActivityApi {
    private static final String TAG = "WorkerEditActivity";
    public static final String EXTRA_KEY = "extra";

    public static final String WORKER_ID_KEY = "workerid";
    public static final String WORKER_NAME_KEY = "workerName";
    public static final String WORKER_PERSONAL_DATA_KEY = "workerpesonaldata";
    public static final String WORKER_EXPERIENCE_KEY = "workerexperience";
    public static final String WORKER_SALARY_KEY = "workersalary";
    public static final String WORKER_TELEPHONE_KEY = "workertelephone";
    public static final String WORKER_ROLE_KEY = "workerrole";
    public static final String WORKER_STATION_ID_KEY = "workerstationid";
    public static final String WORKER_EMAIL_KEY = "workeremail";
    public static final String WORKER_PASSWORD = "workerpassword";


    private Button mSaveButton;
    private EditText mNameEditText;
    private EditText mPersonalEditText;
    private EditText mExperienceEditText;
    private EditText mSalaryEditText;
    private EditText mTelephoneEditText;
    private EditText mIdEditText;
    private EditText mStationdEditText;
    private EditText mRoleEditText;
    private WorkersEditActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_edit);
        Bundle extra = getIntent().getBundleExtra(EXTRA_KEY);
        mNameEditText = findViewById(R.id.worker_name_edit_text);
        mPersonalEditText = findViewById(R.id.worker_personal_edit_text);
        mExperienceEditText = findViewById(R.id.worker_experience_edit_text);
        mSalaryEditText = findViewById(R.id.worker_salary_edit_text);
        mTelephoneEditText = findViewById(R.id.worker_telephone_edit_text);
        mIdEditText = findViewById(R.id.worker_id_edit_text);
        mStationdEditText = findViewById(R.id.worker_station_id_edit_text);
        mRoleEditText = findViewById(R.id.worker_role_edit_text);

        mPresenter = new WorkersEditActivityPresenter(this, this);


        // MENYA ZASTAVILI YA NE VINOVAT :(((
        int workerId = 0;
        String workerName = "";
        int personalData = 0;
        int workerExperience = 0;
        int workerSalary = 0;
        String workerTelephone = "Telephone";
        String workerRole = "";
        int workerStationId = 0;
        final String workerEmail = extra.getString(WORKER_EMAIL_KEY, "email");
        final String workerPassword = extra.getString(WORKER_PASSWORD, "password");

        if (extra != null) {
            workerId = extra.getInt(WORKER_ID_KEY, 0);
            workerName = extra.getString(WORKER_NAME_KEY, "John");
            personalData = extra.getInt(WORKER_PERSONAL_DATA_KEY, 0);
            workerExperience = extra.getInt(WORKER_EXPERIENCE_KEY, 0);
            workerSalary = extra.getInt(WORKER_SALARY_KEY, 0);
            workerTelephone = extra.getString(WORKER_TELEPHONE_KEY, "0");
            workerRole = extra.getString(WORKER_ROLE_KEY, "admin");
            workerStationId = extra.getInt(WORKER_STATION_ID_KEY, 0);
        } else {
            Log.d(TAG, "onCreate: Bundle wasn't received");
        }
        mIdEditText.setText(String.valueOf(workerId));
        mNameEditText.setText(workerName);
        mExperienceEditText.setText(String.valueOf(workerExperience));
        mSalaryEditText.setText(String.valueOf(workerSalary));
        mTelephoneEditText.setText(String.valueOf(workerTelephone));
        mPersonalEditText.setText(String.valueOf(personalData));
        mRoleEditText.setText(String.valueOf(workerRole));
        mStationdEditText.setText(String.valueOf(workerStationId));

        mSaveButton = findViewById(R.id.save_changes_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Worker worker = new Worker(Integer.valueOf(mIdEditText.getText().toString()),
                        mNameEditText.getText().toString(),
                        Integer.valueOf(mPersonalEditText.getText().toString()),
                        Integer.valueOf(mSalaryEditText.getText().toString()),
                        Integer.valueOf(mExperienceEditText.getText().toString()),
                        mTelephoneEditText.getText().toString(),
                        Integer.valueOf(mStationdEditText.getText().toString()),
                        mRoleEditText.getText().toString(),
                        workerEmail,
                        workerPassword);
                mPresenter.editWorker(worker);
            }
        });

    }

    public static Intent makeIntent(Context ctx, Worker worker) {
        Bundle extra = new Bundle();
        extra.putInt(WORKER_ID_KEY, worker.getId());
        extra.putString(WORKER_NAME_KEY, worker.getFullName());
        extra.putInt(WORKER_PERSONAL_DATA_KEY, worker.getPersonalData());
        extra.putInt(WORKER_SALARY_KEY, worker.getSalary());
        extra.putInt(WORKER_EXPERIENCE_KEY, worker.getYearsExperience());
        extra.putString(WORKER_TELEPHONE_KEY, worker.getTelephoneNumber());
        extra.putString(WORKER_ROLE_KEY, worker.getRole());
        extra.putInt(WORKER_STATION_ID_KEY, worker.getStationId());
        extra.putString(WORKER_EMAIL_KEY, worker.getEmail());
        extra.putString(WORKER_PASSWORD, worker.getPassword());
        Intent intent = new Intent(ctx, WorkerEditActivity.class);
        intent.putExtra(EXTRA_KEY, extra);
        return intent;

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
