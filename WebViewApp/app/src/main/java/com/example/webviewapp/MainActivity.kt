package com.example.webviewapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.webviewapp.databinding.ActivityMainBinding
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleIntent()
    }

    private fun handleIntent() {
        val intent = this.intent
        val data = intent.data
        var url: URL? = null
        try{
            url = URL(data?.scheme,data?.host,data?.path)
        }catch (e:Exception){
            e.printStackTrace()
        }
        binding.webView.loadUrl(url.toString())
    }
}