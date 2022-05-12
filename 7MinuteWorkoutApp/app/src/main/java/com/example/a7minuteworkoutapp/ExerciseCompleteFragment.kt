package com.example.a7minuteworkoutapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.a7minuteworkoutapp.databinding.FragmentExerciseCompleteBinding
import java.text.SimpleDateFormat
import java.util.*

class ExerciseCompleteFragment : Fragment() {
    lateinit var binding:FragmentExerciseCompleteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseCompleteBinding.inflate(inflater,container,false)
        binding.finishButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.getDefault())
            val dateTime = dateTimeFormat.format(calendar.time)
            HistoryDatabase(requireContext()).onAddTuple(dateTime)
            it.findNavController().navigate(ExerciseCompleteFragmentDirections.actionExerciseCompleteFragmentToTitleFragment())
        }
        return binding.root
    }
}