package com.example.diceroll

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_roll:Button = findViewById(R.id.btn_roll)
        val btn_restart:Button = findViewById(R.id.btn_restart)
        val image_dice:ImageView = findViewById(R.id.image_dice)
        btn_roll.setOnClickListener {
            image_dice.setImageResource(when((1..6).random()){
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            })
        }
        btn_restart.setOnClickListener {
            image_dice.setImageResource(R.drawable.empty_dice)
        }
    }
}