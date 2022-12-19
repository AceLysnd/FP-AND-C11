package com.ace.c11flight.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.ace.c11flight.R
import java.util.*

class SelectDateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_date)

        val date = findViewById<DatePicker>(R.id.calender)as DatePicker
        val Kalender : Calendar = Calendar.getInstance()
        date.init(Kalender.get(Calendar.YEAR),Kalender.get(Calendar.MONTH),Kalender.get(Calendar.DAY_OF_MONTH),{view,year,monthOfYear,dayOfMonth->
            Toast.makeText(applicationContext,"#"+ date.dayOfMonth +"-"+ date.month +"-" + date.year+"#", Toast.LENGTH_SHORT).show()
        })
    }
}