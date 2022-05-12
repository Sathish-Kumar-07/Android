package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        listOf(
            binding.btnZero,
            binding.btnOne,
            binding.btnTwo,
            binding.btnThree,
            binding.btnFour,
            binding.btnFive,
            binding.btnSix,
            binding.btnSeven,
            binding.btnEight,
            binding.btnNine).forEach {
            it.setOnClickListener { view->
                addToEquation(view)
            }
        }
        binding.btnClr.setOnClickListener{
            onClear()
        }
        binding.btnDot.setOnClickListener{
            onDecimalPoint(it)
        }
        listOf(binding.btnPlus,
            binding.btnMinus,
            binding.btnMultiply,
            binding.btnDivide).forEach {
                onOperator(it)
        }
    }

    private fun onOperator(view: View) {
        binding.operatorText.text = (view as Button).text.toString()
    }

    private fun onDecimalPoint(view: View) {

    }

    private fun addToEquation(view: View){
        val add:Button = view as Button
    }

    private fun onClear(){
        binding.equationText1.text= ""
        binding.equationText2.text=""
    }
}