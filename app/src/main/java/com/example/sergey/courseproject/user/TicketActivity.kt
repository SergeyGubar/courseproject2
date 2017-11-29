package com.example.sergey.courseproject.user

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.admin.workers.WorkerEditActivity.EXTRA_KEY
import com.example.sergey.courseproject.db.contracts.TicketDbContract
import com.example.sergey.courseproject.repositories.TicketRepository
import jxl.Workbook
import jxl.WorkbookSettings
import jxl.write.Label
import java.io.File
import java.util.*

class TicketActivity : AppCompatActivity() {

    private val ticketRepository by lazy {
        TicketRepository(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        val ticketId = intent.extras.getBundle(EXTRA_KEY).getLong(TICKET_ID_KEY)

        val ticket = ticketRepository.getTicketById(ticketId)

        val permissions = Array(2, { i -> i.toString() })
        permissions[0] = Manifest.permission.READ_EXTERNAL_STORAGE
        permissions[1] = Manifest.permission.WRITE_EXTERNAL_STORAGE

        ActivityCompat.requestPermissions(this, permissions,0)

        val sd = Environment.getExternalStorageDirectory()
        val csvFile = "myData.xls"

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
            sheet.addCell(Label(0, 0, TicketDbContract._ID))
            sheet.addCell(Label(1, 0, TicketDbContract.COLUMN_JOURNEY_ID))
            sheet.addCell(Label(2, 0, TicketDbContract.COLUMN_SEAT_NUMBER))
            sheet.addCell(Label(3, 0, TicketDbContract.COLUMN_TIMESTAMP))


            sheet.addCell(Label(0, 1, ticket.id.toString()))
            sheet.addCell(Label(1, 1, ticket.journeyId.toString()))
            sheet.addCell(Label(2, 1, ticket.seatNumber.toString()))
            sheet.addCell(Label(3, 1, ticket.timeStamp.toString()))

            workbook.write()
            workbook.close()
            Toast.makeText(this, "Excel report done!", Toast.LENGTH_SHORT).show()

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


    companion object {
        const val TICKET_ID_KEY = "ticketid"
        const val EXTRA_KEY = "extra"
        fun makeIntent(context: Context, ticketId: Long): Intent {
            val extra = Bundle()
            extra.putLong(TICKET_ID_KEY, ticketId)
            val intent = Intent(context, TicketActivity::class.java)
            intent.putExtra(EXTRA_KEY, extra)
            return intent
        }
    }
}
