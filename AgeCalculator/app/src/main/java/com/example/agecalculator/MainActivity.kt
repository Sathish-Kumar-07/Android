package com.example.agecalculator

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.widget.*
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    var myYear:Int = 0
    var myMonth:Int=0
    var myDay:Int=0
    val myCalendar = Calendar.getInstance()
    val current_Year: Int = myCalendar.get(Calendar.YEAR)
    val current_Month: Int = myCalendar.get(Calendar.MONTH)
    val current_Day: Int = myCalendar.get(Calendar.DAY_OF_MONTH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottonDate =findViewById<Button>(R.id.btnDate)
        bottonDate.setOnClickListener() {
            val datePicker = DatePickerDialog(this@MainActivity, this@MainActivity, current_Year, current_Month, current_Day)
            datePicker.show()

        }
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myYear = year
        myMonth = month + 1
        myDay = dayOfMonth
        var age:Int=0
        findViewById<TextView>(R.id.selectedDate).text = "Date Of Birth: $myDay-$myMonth-$myYear"
        if (myMonth<=current_Month ){
            if (myMonth==current_Month && myDay>=current_Day){
                age=current_Year-myYear+1
            }
            else{
                age=current_Year-myYear
            }
        }
        else{
            age=current_Year-myYear
        }
        findViewById<TextView>(R.id.calculatedAge).text = "Age:$age"
    }


}
