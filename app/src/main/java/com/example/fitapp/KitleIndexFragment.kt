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
import com.example.fitapp.databinding.KitleIndeksHesaplaBinding
import com.example.fitapp.databinding.MainMenuBinding


class KitleIndexFragment : Fragment(R.layout.kitle_indeks_hesapla) {

    private var _binding: KitleIndeksHesaplaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = KitleIndeksHesaplaBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.hesaplaButton.setOnClickListener{

            if(!binding.kadinRadioButton.isChecked && !binding.erkekRadioButton.isChecked)
            {
                val newFragment =
                    InfoDialogFragment("Lütfen cinsiyetinizi seçiniz")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener
            }

            var boy = binding.boyTextBox.text.toString().toFloat() / 100.0f

            var kilo = binding.kiloTextBox.text.toString().toFloat()

            val indeks = kilo / (boy * boy)

            val newFragment =
                InfoDialogFragment("Vücut kitle endeksiniz: $indeks")
            newFragment.show(parentFragmentManager, "info")

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}