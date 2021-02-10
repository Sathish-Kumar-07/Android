package com.example.tictactoy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random

class SingleGameActivity : AppCompatActivity() {
    var Clicks = 1
    var Player1= arrayListOf<Int>()
    var Player2= arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_game)
    }
    fun btnClick(view: View) {
        val Btn = view as Button
        var Num:Int
        Num=when(Btn.id){
            R.id.B1 -> 1
            R.id.B2 -> 2
            R.id.B3 -> 3
            R.id.B4 -> 4
            R.id.B5 -> 5
            R.id.B6 -> 6
            R.id.B7 -> 7
            R.id.B8 -> 8
            else ->9
        }
        if (Clicks%2!=0){
            Btn.text="X"
            Player1.add(Num)
            Btn.isClickable=false
        }
        if (!WinnerDecider()) {
            Num = ComputerChoice()
            val btn: Button = when (Num) {
                1 -> findViewById<Button>(R.id.B1)
                2 -> findViewById<Button>(R.id.B2)
                3 -> findViewById<Button>(R.id.B3)
                4 -> findViewById<Button>(R.id.B4)
                5 -> findViewById<Button>(R.id.B5)
                6 -> findViewById<Button>(R.id.B6)
                7 -> findViewById<Button>(R.id.B7)
                8 -> findViewById<Button>(R.id.B8)
                else -> findViewById<Button>(R.id.B9)
            }
            btn.text = "O"
            Player2.add(Num)
            btn.isClickable = false
            WinnerDecider()
        }
        Clicks+=2


    }
    fun ComputerChoice():Int{
        var LeftChoice= arrayListOf<Int>()
        for(i in 1..9){
            if (!(i in Player1 || i in Player2)){
                LeftChoice.add(i)
            }
        }
        return LeftChoice.random()
    }


    fun WinnerDecider():Boolean{
        if (setOf<Int>(1,2,3).all {Player1.contains(it)} || setOf<Int>(4,5,6).all {Player1.contains(it)} || setOf<Int>(7,8,9).all {Player1.contains(it)} || setOf<Int>(1,5,9).all {Player1.contains(it)} || setOf<Int>(3,5,7).all {Player1.contains(it)}  || setOf<Int>(1,4,7).all {Player1.contains(it)} || setOf<Int>(2,5,8).all { Player1.contains(it) } || setOf<Int>(3,6,9).all { Player1.contains(it) }){
            val winner=intent.getStringExtra("SinglePlayerName")
            Toast.makeText(applicationContext,"$winner Wins", Toast.LENGTH_SHORT).show()
            WinnerActivity(winner!!.toString())
            return true
        }

        if (setOf<Int>(1,2,3).all {Player2.contains(it)} || setOf<Int>(4,5,6).all {Player2.contains(it)} || setOf<Int>(7,8,9).all {Player2.contains(it)} || setOf<Int>(1,5,9).all {Player2.contains(it)} || setOf<Int>(3,5,7).all {Player2.contains(it)} || setOf<Int>(1,4,7).all {Player2.contains(it)} || setOf<Int>(2,5,8).all { Player2.contains(it) } || setOf<Int>(3,6,9).all { Player2.contains(it) }){
            Toast.makeText(applicationContext,"Computer Wins", Toast.LENGTH_SHORT).show()
            val winner="Computer"
            WinnerActivity(winner!!.toString())
            return true
        }
        if(Clicks>9){
            WinnerActivity("Draw")
            return true
        }
        return false

    }
    fun WinnerActivity(Winner:String){
        val winningPage= Intent(this,Wining_Activity::class.java)
        winningPage.putExtra("Winner",Winner)
        startActivity(winningPage)
        finish()
    }
}