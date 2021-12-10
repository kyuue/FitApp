package com.example.fitapp

import android.annotation.SuppressLint
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
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.fitapp.databinding.*
import java.lang.Exception


class KaloriHesapFragment : Fragment(R.layout.main_menu) {

    private var _binding: KaloriHesaplaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = KaloriHesaplaBinding.inflate(inflater, container, false)

        return binding.root

    }

    fun UpdateBesinListesi()
    {
        binding!!.BesinContainer.removeAllViews()

        for(besinObject in besinListesi) {

            if(!binding.searchTextBox.text.isNullOrEmpty() && !besinObject.name.contains(binding.searchTextBox.text))
                continue

            val listElementBinding =
                BesinListElementBinding.inflate(layoutInflater, binding!!.BesinContainer, true)

            var unit = besinObject.unit

            unit = if (unit == "U") "1 adet" else if (unit == "G") "100 gr" else "100 ml"

            var name = besinObject.name

            listElementBinding.nameOfBesin.text = "$unit $name"

            listElementBinding.addToListButton.setOnClickListener {

                val besin = eklenenBesinListesi.find { it.name == besinObject.name }

                if (besin == null)
                {
                    eklenenBesinListesi.add(besinObject)
                }
                else
                {
                    besin.count++
                }

                UpdateEklenenBesinListesi()
            }
        }
    }

    lateinit var besinListesi: MutableList<Besin>

    val eklenenBesinListesi: MutableList<Besin> = mutableListOf<Besin>()

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        try {

            val db = MainActivity.DatabaseObject.readableDatabase

            val cursor = db.query(
                "besinler",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
            )

            besinListesi = mutableListOf<Besin>()

            while (cursor.moveToNext()) {


                val name = cursor.getString(cursor.getColumnIndex("name"))

                var unit = cursor.getString(cursor.getColumnIndex("unit"))

                //unit = if (unit == "U") "1 adet" else if (unit == "G") "100 gr" else "100 ml"

                var cal = cursor.getInt(cursor.getColumnIndex("cal"))

                var besinObject = Besin(name, unit, cal)

                besinListesi.add(besinObject)
            }

            cursor.close()

        } catch (e: Exception) {
            val newFragment = InfoDialogFragment(e.toString())
            newFragment.show(parentFragmentManager, "info")
        }

        UpdateBesinListesi()

        binding.searchTextBox.addTextChangedListener{
            try {

                UpdateBesinListesi()

            }catch (e: Exception)
            {

            }
        }
    }

    fun UpdateEklenenBesinListesi() {

        binding.EklenenBesinContainer.removeAllViews()

        for (besin in eklenenBesinListesi) {
            val listElementBinding = EklenenBesinListElementBinding.inflate(
                layoutInflater,
                binding!!.EklenenBesinContainer,
                true
            )

            val name = besin.name

            var unit = besin.unit

            unit = if (unit == "U") "1 adet" else if (unit == "G") "100 gr" else "100 ml"

            var cal = besin.cal

            listElementBinding.besinName.text = "$unit $name"

            listElementBinding.calCount.text = "$cal  cal"

            listElementBinding.adetTextBox.setText(besin.count.toString())

            listElementBinding.removeFromListButton.setOnClickListener {

                besin.count = 1

                eklenenBesinListesi.remove(besin)

                UpdateEklenenBesinListesi()
            }

            listElementBinding.adetTextBox.addTextChangedListener {

                try {
                    besin.count = it.toString().toInt()
                    UpdateToplamKaloriText()
                } catch (e: Exception) {

                }
            }

        }

        UpdateToplamKaloriText()
    }

    fun UpdateToplamKaloriText() {
        var totalCal = 0

        for (besin in eklenenBesinListesi) {
            totalCal += besin.cal * besin.count
        }

        binding.toplamKaloriText.text = "Toplam Kalori\n${totalCal}cal"
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}