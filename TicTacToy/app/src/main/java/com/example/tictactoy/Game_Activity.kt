package com.example.tictactoy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class Game_Activity : AppCompatActivity() {
    var Clicks = 1
    var Player1= arrayListOf<Int>()
    var Player2= arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_)
    }
    fun btnClick(view: View) {
        val Btn = view as Button
        val Num:Int
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
        if (Clicks % 2 == 0) {
            Btn.text = "O"
            Player2.add(Num)
        } else {
            Btn.text = "X"
            Player1.add(Num)
        }
        Clicks++
        Btn.isClickable=false
        WinnerDecider()
    }


    fun WinnerDecider() {
        if (setOf<Int>(1,2,3).all {Player1.contains(it)} || setOf<Int>(4,5,6).all {Player1.contains(it)} || setOf<Int>(7,8,9).all {Player1.contains(it)} || setOf<Int>(1,5,9).all {Player1.contains(it)} || setOf<Int>(3,5,7).all {Player1.contains(it)}  || setOf<Int>(1,4,7).all {Player1.contains(it)} || setOf<Int>(2,5,8).all { Player1.contains(it) } || setOf<Int>(3,6,9).all { Player1.contains(it) }){
            val winner=intent.getStringExtra("Player1")
            Toast.makeText(applicationContext,"$winner Wins", Toast.LENGTH_SHORT).show()
            WinnerActivity(winner!!.toString())

        }

        if (setOf<Int>(1,2,3).all {Player2.contains(it)} || setOf<Int>(4,5,6).all {Player2.contains(it)} || setOf<Int>(7,8,9).all {Player2.contains(it)} || setOf<Int>(1,5,9).all {Player2.contains(it)} || setOf<Int>(3,5,7).all {Player2.contains(it)} || setOf<Int>(1,4,7).all {Player2.contains(it)} || setOf<Int>(2,5,8).all { Player2.contains(it) } || setOf<Int>(3,6,9).all { Player2.contains(it) }){
            val winner=intent.getStringExtra("Player2")
            Toast.makeText(applicationContext,"$winner Wins", Toast.LENGTH_SHORT).show()
            WinnerActivity(winner!!.toString())
        }
        if(Clicks>9){
            WinnerActivity("Draw")
        }
    }
    fun WinnerActivity(Winner:String){
        val winningPage= Intent(this,Wining_Activity::class.java)
        winningPage.putExtra("Winner",Winner)
        startActivity(winningPage)
        finish()
    }
}