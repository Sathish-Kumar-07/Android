package com.example.bujjipottihacchiii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus){
            hideSystemUI()
        }
    }
    private fun hideSystemUI(){
        window.decorView.systemUiVisibility=(
                View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
    private fun showSystemUI(){
        window.decorView.systemUiVisibility=(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
    fun ImageChange(view: View) {
        var r = (1..9).random()
        var change = when (r) {
            1 -> R.drawable.a
            2 -> R.drawable.b
            3 -> R.drawable.c
            4 -> R.drawable.d
            5 -> R.drawable.e
            6 -> R.drawable.f
            7 -> R.drawable.g
            8 -> R.drawable.i
            else -> R.drawable.h

        }
        findViewById<ImageView>(R.id.bgImage).setImageResource(change)
    }
}
