package com.example.simplequizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.simplequizapp.databinding.FragmentGameLostBinding
import com.example.simplequizapp.databinding.FragmentGameWonBinding

class GameLostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameLostBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_lost,container,false)
        binding.tryAgainButton.setOnClickListener { view->
            view.findNavController().navigate(GameLostFragmentDirections.actionGameLostFragmentToGameFragment())
        }
        return binding.root
    }
}