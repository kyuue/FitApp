package com.example.fitapp

import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import androidx.appcompat.app.AppCompatActivity
import com.example.fitapp.databinding.ContentMainBinding
import android.content.Intent
import android.R
import android.app.PendingIntent.getActivity
import android.util.Log
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {

    companion object{
        public  lateinit var DatabaseObject : DatabaseHelper
    }

    private lateinit var binding: ContentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DatabaseObject = DatabaseHelper(applicationContext)
    }

}