package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun Digit(view: View) {
        findViewById<TextView>(R.id.tvExp).text = findViewById<TextView>(R.id.tvExp).text.toString() + (view as Button).text.toString()
        lastNumeric = true
    }

    fun Clear(view: View) {
        findViewById<TextView>(R.id.tvExp).text = ""
        findViewById<TextView>(R.id.tvResult).text=""
        lastNumeric = true
        lastDot = false
    }

    fun Delete(view: View) {
        val len = findViewById<TextView>(R.id.tvExp).text.toString().length
        findViewById<TextView>(R.id.tvExp).text = findViewById<TextView>(R.id.tvExp).text.toString().slice(0 until len - 1)

    }

    fun Dot(view: View) {
        if (lastNumeric && !lastDot) {
            findViewById<TextView>(R.id.tvExp).text = findViewById<TextView>(R.id.tvExp).text.toString() + "."
            lastNumeric = false
            lastDot = true
        }
    }

    fun Operator(view: View) {
        val op=(view as Button)
        if ((lastNumeric && !lastDot) || (op.text.toString() == "-" && lastNumeric && !lastDot)  || ( lastNumeric)) {
            findViewById<TextView>(R.id.tvExp).text = findViewById<TextView>(R.id.tvExp).text.toString() + op.text.toString()
            lastNumeric = false
            lastDot = false
    }
    }
    fun EqualTo(view: View){
        var EvalList = ArrayList<String>()
        var Result=""
        var Equation=findViewById<TextView>(R.id.tvExp).text.toString()
        if (Equation.length==0){
            findViewById<TextView>(R.id.tvResult).text="0"
        }
        else if (Equation[0]=='-' && !( "+" in Equation||"*" in Equation || "/" in Equation)){
            findViewById<TextView>(R.id.tvResult).text=Equation
        }
        else if (Equation[Equation.length-1] in "/*-+"){
            findViewById<TextView>(R.id.tvResult).text="Invalid Input"
        }
        else {
            var start = 0
            for (i in 1..Equation.length - 1) {
                if (Equation[i] in "+-*/") {
                    EvalList.add(Equation.slice(start until i))
                    EvalList.add(Equation[i].toString())
                    start = i + 1
                }
            }
            EvalList.add(Equation.slice(start until Equation.length))
            if (EvalList[0]=="-"){
                EvalList[0]=EvalList[0]+EvalList[1]
                EvalList.removeAt(1)
            }
            Result = EvalList[0]
            var Precedence = listOf<String>("*", "/", "+", "-")
            var i = 0
            while (EvalList.size > 1) {
                if (Precedence[i] in EvalList) {
                    val index = EvalList.indexOf(Precedence[i])
                    Result = when (EvalList[index]) {
                        "+" -> (EvalList[index - 1].toDouble() + EvalList[index + 1].toDouble()).toString()
                        "-" -> (EvalList[index - 1].toDouble() - EvalList[index + 1].toDouble()).toString()
                        "*" -> (EvalList[index - 1].toDouble() * EvalList[index + 1].toDouble()).toString()
                        "/" -> (EvalList[index - 1].toDouble() / EvalList[index + 1].toDouble()).toString()
                        else -> Result
                    }
                    EvalList[index - 1] = Result
                    EvalList.removeAt(index)
                    EvalList.removeAt((index))
                } else i++
            }
            findViewById<TextView>(R.id.tvResult).text = Result
            findViewById<TextView>(R.id.tvExp).text=""
            lastNumeric=true
            lastDot=false
        }
    }
}



