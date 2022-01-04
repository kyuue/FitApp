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
import com.example.fitapp.databinding.MusteriEkleBinding
import java.sql.DriverManager
import java.sql.SQLException


class MusteriEkleFragment : Fragment(R.layout.musteri_ekle) {

    private var _binding: MusteriEkleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MusteriEkleBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        binding?.musteriEkleButton?.setOnClickListener {

            if (binding?.nameInput?.text?.length!! > 20) {
                val newFragment =
                    InfoDialogFragment("İsim 20 karakterden uzun olamaz.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (!binding?.nameInput?.text?.matches(Regex("^[a-zA-ZğüşöçıİĞÜŞÖÇ ]+$"))!!) {
                val newFragment =
                    InfoDialogFragment("İsim sayı içeremez.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener
            }

            if (binding?.surNameInput?.text?.length!! > 20) {
                val newFragment =
                    InfoDialogFragment("Soyisim 20 karakterden uzun olamaz.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (!binding?.surNameInput?.text?.matches(Regex("^[a-zA-ZğüşöçıİĞÜŞÖÇ ]+$"))!!) {
                val newFragment =
                    InfoDialogFragment("Soyisim sayı içeremez.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener
            }

            if (binding?.phoneInput?.text?.length!! != 11) {
                val newFragment =
                    InfoDialogFragment("Telefon numarası 11 karakterli olacak şekilde girilmeli.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (!binding?.phoneInput?.text?.matches(Regex("^[0-9]+$"))!!) {
                val newFragment =
                    InfoDialogFragment("Telefon numarası sadece sayı içermelidir")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener
            }

            if (binding?.tcNoInput?.text?.length!! != 11) {
                val newFragment =
                    InfoDialogFragment("TC Kimlik No 11 karakterli olacak şekilde girilmeli.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (!binding?.tcNoInput?.text?.matches(Regex("^[0-9]+$"))!!) {
                val newFragment =
                    InfoDialogFragment("TC Kimlik No sadece sayı içermelidir")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener
            }

            try {

                val con = DatabaseHelper.createConnection()

                val preparedStatement =
                    con?.prepareStatement("INSERT INTO customer(identity_number, name, surname, phone_number) VALUES(?, ?, ?, ?)")

                preparedStatement?.setString(1, binding?.tcNoInput?.text?.toString())
                preparedStatement?.setString(2, binding?.nameInput?.text?.toString())
                preparedStatement?.setString(3, binding?.surNameInput?.text?.toString())
                preparedStatement?.setString(4, binding?.phoneInput?.text?.toString())

                preparedStatement?.execute()

                preparedStatement?.close()

                con?.close()

                val newFragment =
                    InfoDialogFragment("Müşteri başarıyla eklendi.")
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