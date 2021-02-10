package com.example.tictactoy

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class GameMode_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_mode_)
    }

    fun BtnModePlayer(view: View){
        val Btn=view as Button
        if (Btn.text.toString()=="MultiPlayer") {
            startActivity(Intent(this, MultiplayerNameActivity::class.java))
        }
        else startActivity(Intent(this,SinglePlayerNameActivity::class.java))
        finish()
    }
}