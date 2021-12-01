package com.example.fitapp

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import com.example.fitapp.databinding.ContentMainBinding
import android.content.Intent
import android.view.View
import com.example.fitapp.databinding.EgitmenEkleBinding


class EgitmenEkleActivity : Activity() {

    private lateinit var binding: EgitmenEkleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EgitmenEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}