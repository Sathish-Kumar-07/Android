package com.example.a7minuteworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.a7minuteworkoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.exerciseToolBar)
        val navController = supportFragmentManager.findFragmentById(binding.titleNavHost.id)!!.findNavController()
        NavigationUI.setupActionBarWithNavController(this,navController)
        binding.exerciseToolBar.title = "7 Minute Exercise App"
        binding.exerciseToolBar.setTitleTextAppearance(this,R.style.AldrichFontAppearance)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(binding.titleNavHost.id).navigateUp()
    }
}