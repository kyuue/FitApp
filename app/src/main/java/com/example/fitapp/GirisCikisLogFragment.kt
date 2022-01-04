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
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.fitapp.databinding.*
import java.lang.Exception
import java.sql.DriverManager
import java.text.SimpleDateFormat


class GirisCikisLogFragment : Fragment(R.layout.giris_cikis_log) {

    private var _binding: GirisCikisLogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = GirisCikisLogBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        try {

            val con = DatabaseHelper.createConnection()

            val stmt = con?.prepareStatement("SELECT * FROM entry_exit")

            val cursor = stmt?.executeQuery()


            while(cursor?.next() == true) {
                val listElementBinding = MusteriEntryListElementBinding.inflate(layoutInflater, binding!!.EntryListContainer, true)

                val customerId = cursor.getInt(cursor.findColumn("customer_id"))

                val date = cursor.getTimestamp(cursor.findColumn("date"))

                val type = cursor.getBoolean(cursor.findColumn("type"))

                val name = DatabaseHelper.getCustomerName(customerId)

                listElementBinding.idText.text = customerId.toString()

                listElementBinding.nameText.text = name

                listElementBinding.typeText.text = if (type) "çıkış" else "giriş"

                val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm")

                listElementBinding.dateText.text = sdf.format(date)
            }

            cursor?.close()

            con?.close()

        } catch (e: Exception) {
            val newFragment = InfoDialogFragment(e.toString())
            newFragment.show(parentFragmentManager, "info")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}