package com.example.a7minuteworkoutapp

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a7minuteworkoutapp.databinding.FragmentBMICalculatorBinding
import java.util.*

class BMICalculatorFragment : Fragment(),TextToSpeech.OnInitListener {
    private lateinit var binding : FragmentBMICalculatorBinding
    private lateinit var textToSpeech: TextToSpeech
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        textToSpeech = TextToSpeech(requireContext(),this)
        binding = FragmentBMICalculatorBinding.inflate(inflater,container,false)
        binding.calculateBMIButton.setOnClickListener {
            if (binding.BMITypeRadioGroup.checkedRadioButtonId == binding.metricUnitRadioButton.id)
                metricUnitResult()
            else
                usUnitResult()
        }
        binding.BMITypeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == binding.metricUnitRadioButton.id){
                binding.weightTextInputLayout.visibility = View.VISIBLE
                binding.heightTextInputLayout.visibility = View.VISIBLE
                binding.feetInchLayout.visibility = View.GONE
                binding.poundTextInputLayout.visibility = View.GONE
                binding.resultLinearLayout.visibility = View.GONE
            }else{
                binding.weightTextInputLayout.visibility = View.GONE
                binding.heightTextInputLayout.visibility = View.GONE
                binding.feetInchLayout.visibility = View.VISIBLE
                binding.poundTextInputLayout.visibility = View.VISIBLE
                binding.resultLinearLayout.visibility = View.GONE
            }
        }
        return binding.root
    }
    private fun metricUnitResult(){
        binding.apply {
            if (weightTextInputValue.text!!.isNotEmpty() || heightTextInputValue.text!!.isNotEmpty()) {
                if (weightTextInputValue.text.toString().toFloat()<0f || heightTextInputValue.text.toString().toFloat() < 0f){
                    Toast.makeText(requireContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show()
                }else {
                    val resultBMI = String.format(
                        "%.3f", (
                                weightTextInputValue.text.toString().toFloat() / ((heightTextInputValue.text.toString()
                                    .toFloat() / 100) * 2)
                                )
                    ).toFloat()
                    resultBMITextView.text = resultBMI.toString()
                    val resultInfoText = resultInfo(resultBMI)
                    resultInfoTextView.text = resultInfoText
                    textToSpeech.speak(resultInfoText, TextToSpeech.QUEUE_FLUSH, null)
                    binding.resultLinearLayout.visibility = View.VISIBLE
                }
            }else{
                binding.resultLinearLayout.visibility = View.GONE
            }
        }
    }
    private fun usUnitResult(){
        binding.apply {
            if (poundTextInputValue.text!!.isNotEmpty() || feetTextInputValue.text!!.isNotEmpty() || inchTextInputValue.text!!.isNotEmpty()) {
                if (poundTextInputValue.text.toString().toFloat() < 0f ||
                    feetTextInputValue.text.toString().toFloat() < 0f ||
                    inchTextInputValue.text.toString().toFloat() < 0f){
                    Toast.makeText(requireContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show()
                }else {
                    val resultBMI = String.format(
                        "%.3f", (
                                (poundTextInputValue.text.toString().toFloat() * 703)/ (Math.pow(feetTextInputValue.text.toString()
                                    .toFloat() * 12 + inchTextInputValue.text.toString().toFloat().toDouble(),2.0)))
                                )
                    .toFloat()
                    resultBMITextView.text = resultBMI.toString()
                    val resultInfoText = resultInfo(resultBMI)
                    resultInfoTextView.text = resultInfoText
                    textToSpeech.speak(resultInfoText, TextToSpeech.QUEUE_FLUSH, null)
                    binding.resultLinearLayout.visibility = View.VISIBLE
                }
            }else{
                binding.resultLinearLayout.visibility = View.GONE
            }
        }
    }
    private fun resultInfo(result:Float):String{
        return if(result < 18.5f) " Oops! Under Weight"
        else if (result < 25f) "Congrats Normal Weight"
        else if (result < 30f) "OMG! Over Weight"
        else "Ohh Oops Obese Weight"
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = textToSpeech!!.setLanguage(Locale.getDefault())
            if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                Toast.makeText(requireContext(),"Text To Speech Failed", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(requireContext(),"Text To Speech Failed", Toast.LENGTH_SHORT).show()
        }
    }
}