package com.example.sergey.courseproject.admin.statistics

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.db.contracts.WorkerDbContract
import com.example.sergey.courseproject.repositories.WorkerRepository
import jxl.Workbook
import jxl.WorkbookSettings
import jxl.write.Label
import kotlinx.android.synthetic.main.activity_experience_statistics.*
import java.io.File
import java.util.*

class ExperienceStatistics : AppCompatActivity() {

    private val workerRepository by lazy {
        WorkerRepository(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experience_statistics)

        generate_stat_exp.setOnClickListener {
            makeStatQuery(exp_cup_edit_text.text.toString().toInt())
        }
    }

    private fun makeStatQuery(exp: Int) {
        val result = workerRepository.getStatByExp(exp)
        val permissions = Array(2, { i -> i.toString() })
        permissions[0] = Manifest.permission.READ_EXTERNAL_STORAGE
        permissions[1] = Manifest.permission.WRITE_EXTERNAL_STORAGE

        ActivityCompat.requestPermissions(this, permissions, 0)

        val sd = Environment.getExternalStorageDirectory()
        val csvFile = "experienceReport.xls"
        val directory = File(sd.absolutePath)

        if (!directory.isDirectory) {
            directory.mkdirs()
        }
        try {
            val file = File(directory, csvFile)
            val wbSettings = WorkbookSettings()
            wbSettings.locale = Locale("en", "EN")
            val workbook = Workbook.createWorkbook(file, wbSettings)
            val sheet = workbook.createSheet("ticketList", 0)
            sheet.addCell(Label(0, 0, "Experience"))
            sheet.addCell(Label(1, 0, "Count drivers"))

            var count = 0
            while (result.moveToNext()) {
                sheet.addCell(Label(0, count + 1, result.getInt(result.getColumnIndex(WorkerDbContract.COLUMN_EXPERIENCE)).toString()))
                sheet.addCell(Label(1, count + 1, result.getInt(result.getColumnIndex("CountStat")).toString()))
                count++
            }
            Toast.makeText(this, "Excel report done", Toast.LENGTH_SHORT).show()
            workbook.write()
            workbook.close()

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}
