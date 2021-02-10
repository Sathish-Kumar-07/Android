package com.example.tictactoy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Wining_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wining_)
        var intent1=intent
        var Winner=intent1.getStringExtra("Winner")
        if(Winner!="Draw") findViewById<TextView>(R.id.WinnerText).text=Winner + " Wins"
        else findViewById<TextView>(R.id.WinnerText).text="Draw"
    }

    fun BtnStart(view: View) {
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun BtnExit(view: View) {
        finish()
    }
}