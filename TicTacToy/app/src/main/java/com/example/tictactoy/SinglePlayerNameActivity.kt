package com.example.tictactoy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class SinglePlayerNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player_name)
    }

    fun playBtn(view: View) {
        val intent=Intent(this,SingleGameActivity::class.java)
        intent.putExtra("SinglePlayerName",findViewById<EditText>(R.id.SingleplayerName).text.toString())
        startActivity(intent)
        finish()
    }
}