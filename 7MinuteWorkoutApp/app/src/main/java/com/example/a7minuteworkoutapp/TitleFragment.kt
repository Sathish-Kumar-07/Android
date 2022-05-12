package com.example.a7minuteworkoutapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.a7minuteworkoutapp.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    lateinit var binding:FragmentTitleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTitleBinding.inflate(inflater,container,false)
        binding.startCustomButton.setOnClickListener {
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToWorkOutFragment())
        }
        setHasOptionsMenu(true)
        binding.bmiCalculatorButton.setOnClickListener{
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToBMICalculatorFragment())
        }
        binding.historyButton.setOnClickListener {
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToHistoryFragment())
        }
        return binding.root
    }

}