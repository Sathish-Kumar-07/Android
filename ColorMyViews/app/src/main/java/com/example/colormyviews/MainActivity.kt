 package com.example.colormyviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.colormyviews.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {
     lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setListeners()
    }
    private fun setListeners(){
        val clickableViews:List<View> = listOf(binding.textBoxOne,
            binding.textBoxTwo,
            binding.textBoxThree,
            binding.textBoxFour,
            binding.textBoxFive,
            binding.layout,
            binding.btnBlue,
            binding.btnGreen,
            binding.btnRed)
        clickableViews.forEach { layoutViewId->
            layoutViewId.setOnClickListener { view->
                makeColored(view)
            }
        }

    }
     private fun makeColored(view:View){
         when(view.id){
             binding.textBoxOne.id -> view.setBackgroundResource(R.color.purple_200)
             binding.textBoxTwo.id -> view.setBackgroundResource(R.color.purple_500)
             binding.textBoxThree.id -> view.setBackgroundResource(R.color.purple_700)
             binding.textBoxFour.id -> view.setBackgroundResource(R.color.teal_200)
             binding.textBoxFive.id -> view.setBackgroundResource(R.color.teal_700)
             binding.btnBlue.id -> binding.layout.setBackgroundResource(R.color.blue)
             binding.btnGreen.id -> binding.layout.setBackgroundResource(R.color.Green)
             binding.btnRed.id -> binding.layout.setBackgroundResource(R.color.red)
             else -> view.setBackgroundResource(R.color.yellow)
         }
     }
}