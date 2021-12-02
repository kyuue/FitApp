package com.example.fitapp

import android.app.Activity
import android.os.Bundle
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
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.fitapp.databinding.EgitmenEkleBinding
import com.example.fitapp.databinding.MainMenuBinding


class MainMenuFragment : Fragment(R.layout.main_menu) {

    private var _binding: MainMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MainMenuBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding?.goToEgitmenEkleButton?.setOnClickListener{
            findNavController().navigate(R.id.egitmenEkleFragment)
        }

        binding?.goToEgitmenSec?.setOnClickListener{
            findNavController().navigate(R.id.egitmenSecFragment)
        }

        binding?.goToBesinEkleButton?.setOnClickListener{
            findNavController().navigate(R.id.besinEkleFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}