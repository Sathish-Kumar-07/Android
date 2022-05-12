package com.example.ageminuteapp

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.ageminuteapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.btnDate.setOnClickListener { view->
            val myCalendar = Calendar.getInstance()
            val year = myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val day = myCalendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog=DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
                changeSelectedDateTextView(selectedDate)
                changeAgeInMinutesTextView(selectedDate)
            },
                year,
                month,
                day)
            datePickerDialog.datePicker.maxDate = Date().time
            datePickerDialog.show()
        }
    }
    private fun changeAgeInMinutesTextView(selectedDate: String) {
        val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val theDate = sdf.parse(selectedDate)
        val selectedDateInMinutes = theDate!!.time / 60000
        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
        val currentDateToMinutes = currentDate!!.time / 60000
        binding.ageInMinutesTextView.text = (currentDateToMinutes - selectedDateInMinutes).toString()
    }
    private fun changeSelectedDateTextView(selectedDate: String) {
        binding.selectedDateTextView.text = selectedDate
    }
}
