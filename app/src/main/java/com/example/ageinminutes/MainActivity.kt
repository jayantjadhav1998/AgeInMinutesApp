package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
        }

        @RequiresApi(Build.VERSION_CODES.N)
        fun clickDatePicker(view: View) {
            val myCalender = Calendar.getInstance()
            val year = myCalender.get(Calendar.YEAR)
            val month = myCalender.get(Calendar.MONTH)
            val day = myCalender.get(Calendar.DAY_OF_MONTH)
           val dpd= DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                    //Toast.makeText(this, "chosen year is $selectedYear month is $selectedMonth and day is $selectedDayOfMonth", Toast.LENGTH_LONG).show()

                    val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                    tvSelectedDate.setText(selectedDate)
                    val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                    val theDate=sdf.parse(selectedDate)

                    val selecteDateInMinutes=theDate!!.time/60000
                    val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMinutes=currentDate!!.time/60000
                    val differenceInMinute=currentDateInMinutes-selecteDateInMinutes
                    tvSelectedDateInMinutes.setText(differenceInMinute.toString())
                },
                year,
                month,
                day)
            dpd.datePicker.setMaxDate(Date().time-86400000)
            dpd.show()
        }
    }

