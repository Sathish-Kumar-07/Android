package com.example.tictactoy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MultiplayerNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplayer_name)
    }

    fun playBtn(view: View) {
        val intent= Intent(this,Game_Activity::class.java)
        intent.putExtra("Player1",findViewById<EditText>(R.id.PlayerName1).text!!.toString())
        intent.putExtra("Player2",findViewById<EditText>(R.id.PlayerName2).text!!.toString())
        startActivity(intent)
        finish()
    }
}