package com.example.fitapp

import android.app.Activity
import android.content.ContentValues
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
import com.example.fitapp.databinding.BesinEkleBinding
import com.example.fitapp.databinding.EgitmenEkleBinding
import com.example.fitapp.databinding.MainMenuBinding
import java.sql.SQLException


class BesinEkleFragment : Fragment(R.layout.besin_ekle) {

    private var _binding: BesinEkleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = BesinEkleBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding?.urunuEkleButton?.setOnClickListener {

            if (binding?.besinIsmiTextBox?.text?.length!! > 20) {
                val newFragment =
                    InfoDialogFragment("İsim 20 karakterden uzun olamaz.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (!binding?.besinIsmiTextBox?.text?.matches(Regex("^[a-zA-ZğüşöçıİĞÜŞÖÇ ]+$"))!!) {
                val newFragment =
                    InfoDialogFragment("İsim sayı içeremez.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener
            }

            if (!binding?.icecekRadioButton.isChecked && !binding?.yiyecekRadioButton.isChecked) {
                val newFragment =
                    InfoDialogFragment("Tür seçimi yapmalısınız.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener
            }

            if (!binding?.birimAdetRadioButton.isChecked && !binding?.birimGramRadioButton.isChecked && !binding?.birimLitreRadioButton.isChecked) {
                val newFragment =
                    InfoDialogFragment("Birim seçimi yapmalısınız.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener
            }

            if (binding?.kaloriMiktariTextBox.text?.length!! > 6) {
                val newFragment =
                    InfoDialogFragment("Kalori 6 karakterden uzun olamaz.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            //val db = MainActivity.DatabaseObject.writableDatabase

            try {


                val con = DatabaseHelper.createConnection()

                val preparedStatement =
                    con?.prepareStatement("INSERT INTO besinler(name, type, unit, cal) VALUES(?, ?, ?, ?)")


                val type = if (binding?.icecekRadioButton.isChecked) "I" else "Y"

                val unit =
                    if (binding?.birimAdetRadioButton.isChecked) "U" else if (binding?.birimGramRadioButton.isChecked) "G" else "L"

                var cal = binding?.kaloriMiktariTextBox.text.toString().toInt()

                preparedStatement?.setString(1, binding?.besinIsmiTextBox?.text?.toString())
                preparedStatement?.setString(2, type)
                preparedStatement?.setString(3, unit)
                preparedStatement?.setInt(4, cal)

                preparedStatement?.execute()

                preparedStatement?.close()

                con?.close()

                val newFragment =
                    InfoDialogFragment("Besin başarıyla eklendi.")
                newFragment.show(parentFragmentManager, "info")
            } catch (e: SQLException) {
                val newFragment = InfoDialogFragment(e.toString())
                newFragment.show(parentFragmentManager, "info")
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}